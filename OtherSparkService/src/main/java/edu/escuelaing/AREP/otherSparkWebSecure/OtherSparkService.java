package edu.escuelaing.AREP.otherSparkWebSecure;

import spark.Filter;
import spark.Request;
import spark.Response;

import static spark.Spark.*;
import static spark.Spark.get;

/**
 * Hello world!
 *
 */
public class OtherSparkService
{
    public static void main(String[] args) {
        port(getPort());
        secure("keystores/ecikeystore.p12", "areplab7", "keystores/myTrustStore", "areplab7");
        get("/home", (req, res) -> HomePage(req, res));

    }

    private static String HomePage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body >"
                + "<Center>"
                + "</br>"
                + "</br>"
                + "<h2>Bienvenido</h2>"
                + "</br>"
                + "</br>"
                + "<form>"
                + "Otro servicio de Spark"
                + "  </br>"
                + "</br>"
                + "</form>"
                + "</Center>"
                + "</body>"
                + "</html>";
        return pageContent;
    }


    /**
     * Metodo que retorna el puerto por el cual esta corriendo la aplicacion
     *
     * @return un entero que refiere al puerto
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 7000;

    }
}
