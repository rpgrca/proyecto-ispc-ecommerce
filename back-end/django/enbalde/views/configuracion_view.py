from rest_framework import viewsets
from rest_framework.permissions import IsAdminUser
from ..models import Configuracion
from ..serializers import ConfiguracionSerializer


class ConfiguracionViewSet(viewsets.ModelViewSet):
    permission_classes = [IsAdminUser]
    queryset = Configuracion.objects.all()
    serializer_class = ConfiguracionSerializer
