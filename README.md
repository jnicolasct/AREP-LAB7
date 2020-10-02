# AREP-LAB7

## Insatalacion y compilacion

Para instalar el programa se debe clonar el proyecto con el comando git clone URL del proyecto o descargar el zip de este y extraer los archivos en su computador y finalamente abrirlo desde cualquier IDE

Para la compilacion, dentro del directorio del proyecto desde la cmd ejecutar el comando mvn clean install.

Debe ejecutar el comando "docker-compose up -d" con el fin de crear las dos imagenes en un contenedor de docker.

### Requisitos

  - JDK instalado
  - maven instalado
  - Docker Deskop
  
 
## Uso

Para hacer uso de la aplicacion se debe acceeder al siguiente link con puerto especifico

https://ec2-34-204-36-87.compute-1.amazonaws.com:16000/

### Crendenciales
usuario: admin@mail.com

contraseña: admin

En el video siguiente vemos com sera el uso de la pagina:

[Video](https://www.youtube.com/watch?v=Ue0ADYVYltE)

## Pruebas

La siguiente imagen muestra los servicios que esta corriendo en la maquina1 de AWS, mediante el comando "dockes ps"

![maquina1](https://github.com/jnicolasct/AREP-LAB7/blob/master/Resources/maquina1.PNG)

La siguiente imagen muestra los servicios que esta corriendo en la maquina2 de AWS, mediante el comando "dockes ps"

![maquina2](https://github.com/jnicolasct/AREP-LAB7/blob/master/Resources/maquina2.PNG)

## Reporte

El reporte fue generado mediante latex, su nombre es Media y Desviacion estadar. Se encuentra en la raiz del proyecto

[report](https://github.com/jnicolasct/AREP-LAB7/blob/master/Resources/Secure_Spark_APP.pdf)

## Autor
  Johan Nicolas Cortes Torres
