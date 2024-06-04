from rest_framework import serializers
from ..models import Usuario
from .common import quitar_clave_de_respuesta


class RegistroSerializer(serializers.ModelSerializer):
    nombre = serializers.CharField(required=True, source="first_name", error_messages={'required': 'El nombre es requerido.', 'blank': 'El nombre no puede estar vacío.'})
    apellido = serializers.CharField(required=True, source="last_name", error_messages={'required': 'El apellido es requerido.', 'blank': 'El apellido no puede estar vacío.'})
    usuario = serializers.CharField(required=True, source="username", error_messages={'required': 'El usuario es requerido.', 'blank': 'El usuario no puede estar vacío.'})
    clave = serializers.CharField(required=True, source="password", error_messages={'required': 'La clave es requerida.', 'blank': 'La clave no puede estar vacía.'})
    direccion = serializers.CharField(required=True, error_messages={'required': 'La dirección es requerida.', 'blank': 'La dirección no puede estar vacía.'})
    telefono = serializers.CharField(required=False)
    email = serializers.CharField(required=True, error_messages={'required': 'El e-mail es requerido.', 'blank': 'El e-mail no puede estar vacío.'})
    tipo = serializers.IntegerField(required=True, error_messages={'required': 'El tipo es requerido.', 'invalid': 'Se necesita un tipo válido.'})
    observaciones = serializers.CharField(required=False)

    class Meta:
        model = Usuario
        fields = ["nombre", "apellido", "usuario", "clave", "direccion", "telefono", "email", "tipo", "observaciones"]

    def create(self, validated_data):
        self._validar_que_no_exista_usuario(validated_data["username"])

        usuario = Usuario(username=validated_data["username"], password=validated_data["password"],
                          tipo=validated_data["tipo"], first_name=validated_data["first_name"],
                          last_name=validated_data["last_name"], email=validated_data["email"],
                          direccion=validated_data["direccion"], telefono=validated_data["telefono"],
                          observaciones="")
        usuario.set_password(validated_data["password"])
        usuario.save()
        return usuario

    def to_representation(self, instance):
        return quitar_clave_de_respuesta(super().to_representation(instance))

    def _validar_que_no_exista_usuario(self, username):
        existe = Usuario.objects.filter(username=username)
        if existe:
            raise serializers.ValidationError("El nombre de usuario seleccionado ya existe")
