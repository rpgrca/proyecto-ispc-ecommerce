from django.utils import timezone
from rest_framework import viewsets
from rest_framework.request import Request
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated, AllowAny
from rest_framework import status
from ..models import Carrito, Seleccion, Venta, Envio
from ..serializers import VentaSerializer

from django.http import JsonResponse
import stripe

stripe.api_key = 'sk_test_51PFOaeP6TR0oZusw00hGwxE6sSb1Ll0wwDh4oBGGVDQUSGTsfOK4GvBlrid9lxvZiHJVK4h1I3X9fuPTKsrcAdsY00itLSq0tC'

class VentaViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAuthenticated, AllowAny]
    queryset = Venta.objects.all()
    serializer_class = VentaSerializer

    def create(self, request: Request, *args, **kwargs):
        try:
            carrito = Carrito.objects.get(pk=int(request.data.get("carrito")))
            envio = Envio.objects.get(pk=int(request.data.get("envio")))
            fecha = timezone.now()
            numero = 1
            comprobante = 1

            ultima_venta = Venta.objects.last()
            if ultima_venta is not None:
                nuevo_comprobante = ultima_venta.comprobante + 1
                comprobante = nuevo_comprobante % 1000
                numero = ultima_venta.numero + (nuevo_comprobante / 1000)

            total = self._calcular_total_de_carrito(carrito) + envio.monto
            pago = request.data.get('pago')
            transaccion = request.data.get('transaccion')
            venta = Venta(numero=numero, comprobante=comprobante, fecha=fecha, total=total, carrito=carrito, envio=envio,
                          pago=pago, transaccion=transaccion)
            venta.save()
            carrito.comprado = True
            carrito.save()
            serializer = VentaSerializer(venta)
            return Response(serializer.data, status=status.HTTP_200_OK)
        except Exception:
            return Response(status=status.HTTP_400_BAD_REQUEST)

    def _calcular_total_de_carrito(self, carrito: Carrito):
        total = 0
        selecciones = Seleccion.objects.filter(carrito=carrito)
        for seleccion in selecciones:
            total += seleccion.articulo.precio * seleccion.cantidad

        return total

    def create(self, request: Request):
        items = request.data.get("items", [])
        line_items = []

        for item in items:
            line_item = {
                "price_data": {
                    "currency": "usd",
                    "unit_amount": int(float(item.get("articulo").get("precio", "0")) * 100),
                    "product_data": {
                        "name": item.get("articulo").get("nombre"),
                        "images": ["https://t3.ftcdn.net/jpg/00/57/08/46/360_F_57084608_ciyjhtwgdKSjeZwhDTNDyuMdWik8gNF9.jpg"],
                    },
                },
                "quantity": item.get("cantidad", 1),
            }

            line_items.append(line_item)

        checkout_session = stripe.checkout.Session.create(
            payment_method_types=["card"],
            line_items=line_items,
            metadata={"product_id": item.get('id')},
            mode="payment",
            success_url="http://localhost:4200/success",
            cancel_url="http://localhost:4200/cancel",
        )

        return JsonResponse({"id": checkout_session.id})
