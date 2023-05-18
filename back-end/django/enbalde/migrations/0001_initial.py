# Generated by Django 4.2.1 on 2023-05-18 22:19

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Carrito',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
            ],
            options={
                'verbose_name': 'Carrito de compra',
                'verbose_name_plural': 'Carritos',
                'db_table': 'Carrito',
            },
        ),
        migrations.CreateModel(
            name='Envio',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('nombre', models.CharField(max_length=40)),
                ('monto', models.DecimalField(decimal_places=2, max_digits=10, max_length=10)),
            ],
            options={
                'verbose_name': 'Tipos de envios disponibles',
                'verbose_name_plural': 'Envios',
                'db_table': 'Envio',
            },
        ),
        migrations.CreateModel(
            name='Oferta',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('nombre', models.CharField(max_length=40)),
                ('descuento', models.DecimalField(decimal_places=2, max_digits=4, max_length=4)),
                ('fecha_vencimiento', models.DateField()),
            ],
            options={
                'verbose_name': 'Ofertas de productos',
                'verbose_name_plural': 'Ofertas',
                'db_table': 'Oferta',
            },
        ),
        migrations.CreateModel(
            name='Producto',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('nombre', models.CharField(max_length=200)),
                ('descripcion', models.CharField(max_length=200)),
                ('precio', models.DecimalField(decimal_places=2, max_digits=10, max_length=10)),
                ('costo', models.DecimalField(decimal_places=2, max_digits=10, max_length=10)),
                ('alicuota', models.DecimalField(decimal_places=2, max_digits=10, max_length=10)),
                ('imagen', models.CharField(max_length=512)),
                ('cantidad', models.IntegerField(default=0)),
            ],
            options={
                'verbose_name': 'Productos del negocio',
                'verbose_name_plural': 'Productos',
                'db_table': 'Producto',
            },
        ),
        migrations.CreateModel(
            name='TipoArticulo',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('nombre', models.CharField(max_length=40)),
            ],
            options={
                'verbose_name': 'Tipos de articulos disponibles',
                'verbose_name_plural': 'TipoArticulos',
                'db_table': 'TipoArticulo',
            },
        ),
        migrations.CreateModel(
            name='Usuario',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('tipo_usuario', models.IntegerField()),
                ('nombre', models.CharField(max_length=40)),
                ('apellido', models.CharField(max_length=40)),
                ('direccion', models.CharField(max_length=100)),
                ('usuario', models.CharField(max_length=40)),
                ('clave', models.CharField(max_length=40)),
                ('email', models.CharField(max_length=45)),
                ('observaciones', models.CharField(max_length=200)),
            ],
            options={
                'verbose_name': 'Listado de usuarios',
                'verbose_name_plural': 'Usuarios',
                'db_table': 'Usuario',
            },
        ),
        migrations.CreateModel(
            name='Venta',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('numero', models.IntegerField()),
                ('comprobante', models.IntegerField()),
                ('fecha', models.DateField()),
                ('neto', models.DecimalField(decimal_places=2, max_digits=10, max_length=10)),
                ('monto_iva', models.DecimalField(decimal_places=2, max_digits=10, max_length=10)),
                ('no_gravado', models.DecimalField(decimal_places=2, max_digits=10, max_length=10)),
                ('total', models.DecimalField(decimal_places=2, max_digits=10, max_length=10)),
                ('id_carrito', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='enbalde.carrito')),
                ('id_envio', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='enbalde.envio')),
                ('id_usuario', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='enbalde.usuario')),
            ],
            options={
                'verbose_name': 'Listado de Ventas',
                'verbose_name_plural': 'Ventas',
                'db_table': 'Venta',
            },
        ),
        migrations.CreateModel(
            name='Seleccion',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('cantidad', models.PositiveIntegerField(default=0)),
                ('id_carrito', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='enbalde.carrito')),
                ('id_producto', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='enbalde.producto')),
            ],
            options={
                'verbose_name': 'Seleccion de Productos',
                'verbose_name_plural': 'Selecciones',
                'db_table': 'Seleccion',
            },
        ),
        migrations.AddField(
            model_name='producto',
            name='id_tipo',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='enbalde.tipoarticulo'),
        ),
        migrations.CreateModel(
            name='OfertaProducto',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False)),
                ('id_articulo', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='enbalde.producto')),
                ('id_oferta', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='enbalde.oferta')),
            ],
            options={
                'verbose_name': 'Productos por Oferta',
                'verbose_name_plural': 'OfertaProductos',
                'db_table': 'OfertaProducto',
            },
        ),
    ]