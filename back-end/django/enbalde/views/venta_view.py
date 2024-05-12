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
            pago = request.data.get('pago')
            transaccion = request.data.get('transaccion')

            if pago == Venta.TipoPago.STRIPE_PAGO_A_PAGAR:
                transaccion = self._cobrar_en_stripe(carrito, envio)
                total = self._calcular_total_de_carrito(carrito) + envio.monto
                venta = Venta(numero=numero, comprobante=comprobante, fecha=fecha, total=total, carrito=carrito, envio=envio,
                            pago=pago, transaccion=transaccion)
                serializer = VentaSerializer(venta)
                return Response(serializer.data, status=status.HTTP_202_ACCEPTED)
            else:
                ultima_venta = Venta.objects.last()
                if ultima_venta is not None:
                    nuevo_comprobante = ultima_venta.comprobante + 1
                    comprobante = nuevo_comprobante % 1000
                    numero = ultima_venta.numero + int(nuevo_comprobante / 1000)

                total = self._calcular_total_de_carrito(carrito) + envio.monto
            
                venta = Venta(numero=numero, comprobante=comprobante, fecha=fecha, total=total, carrito=carrito, envio=envio,
                            pago=pago, transaccion=transaccion)
                venta.save()
                carrito.comprado = True
                carrito.save()
                serializer = VentaSerializer(venta)
                return Response(serializer.data, status=status.HTTP_200_OK)
        except Exception as ex:
            return Response(status=status.HTTP_400_BAD_REQUEST)

    def _calcular_total_de_carrito(self, carrito: Carrito):
        total = 0
        selecciones = Seleccion.objects.filter(carrito=carrito)
        for seleccion in selecciones:
            total += seleccion.articulo.precio * seleccion.cantidad

        return total

    def _cobrar_en_stripe(self, carrito: Carrito, envio: Envio):
        line_items = []
        selecciones = Seleccion.objects.filter(carrito=carrito)
        for seleccion in selecciones:
            line_item = {
                "price_data": {
                    "currency": "ars",
                    "unit_amount": int(float(seleccion.articulo.precio) * 100),
                    "product_data": {
                        "name": seleccion.articulo.nombre,
                        "images": ["https://t3.ftcdn.net/jpg/00/57/08/46/360_F_57084608_ciyjhtwgdKSjeZwhDTNDyuMdWik8gNF9.jpg"],
                    },
                },
                "quantity": seleccion.cantidad if seleccion.cantidad > 0 else 1,
            }

            line_items.append(line_item)

        if envio.monto > 0:
            line_item = {
                "price_data": {
                    "currency": "ars",
                    "unit_amount": int(envio.monto) * 100,
                    "product_data": {
                        "name": envio.nombre,
                        "images": ["https://t3.ftcdn.net/jpg/00/57/08/46/360_F_57084608_ciyjhtwgdKSjeZwhDTNDyuMdWik8gNF9.jpg"],
                    },
                },
                "quantity": 1
            }
                
            line_items.append(line_item)

        checkout_session = stripe.checkout.Session.create(
            payment_method_types=["card"],
            line_items=line_items,
            metadata={"product_id": seleccion.articulo.id},
            mode="payment",
            success_url="http://localhost:4200/success?session_id={CHECKOUT_SESSION_ID}",
            cancel_url="http://localhost:4200/cancel?session_id={CHECKOUT_SESSION_ID}",
        )

        return checkout_session.id
