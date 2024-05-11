from django.db import models
from django.core.validators import MinValueValidator


class Configuracion(models.Model):
    id = models.AutoField(primary_key=True)
    nombre = models.CharField(max_length=64, blank=False)
    valor = models.CharField(max_length=1024, blank=False)

    class Meta:
        db_table = "Configuracion"
        verbose_name = "Valores por defecto del sistema"
        verbose_name_plural = "Configuraciones"

    def __unicode__(self):
        return self.nombre

    def __str__(self):
        return self.nombre
