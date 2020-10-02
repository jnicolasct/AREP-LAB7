FROM openjdk:8


WORKDIR /usrapp/bin


ENV PORT 7000


COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

COPY /keystores /usrapp/bin/keystores

CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.AREP.otherSparkWebSecure.OtherSparkService"]