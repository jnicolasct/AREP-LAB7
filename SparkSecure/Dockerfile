FROM openjdk:8


WORKDIR /usrapp/bin


ENV PORT 6000


COPY SparkSecure/target/classes /usrapp/bin/classes
COPY SparkSecure/target/dependency /usrapp/bin/dependency

COPY SparkSecure/keystores /usrapp/bin/SparkSecure/keystores
COPY OtherSparkService/keystores /usrapp/bin/OtherSparkService/keystores

CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.AREP.sparkWebSecure.SecureSparkServicesApp"]