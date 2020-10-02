package edu.escuelaing.AREP.sparkWebSecure;

import static spark.Spark.*;

import edu.escuelaing.AREP.URLReader.UrlReader;
import spark.Filter;
import spark.Response;
import spark.Request;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SecureSparkServicesApp {

    private static Map<String, String> userPasswords = new HashMap<>();
    private static boolean firtLoggin = true;

    /**
     * Funcion main que crea un servicio REST en spark
     * @param args parammetros de funcion main en java
     */

    public static void main(String[] args) {
        port(getPort());
        secure("SparkSecure/keystores/ecikeystore.p12", "areplab7", "SparkSecure/keystores/myTrustStore", "areplab7");
        get("/hello", (req, res) -> "Hello Heroku");
        userPasswords.put("nicolas.ct@mail.com", "areplab7");
        userPasswords.put("admin@mail.com", "admin");
        get("/", (req, res) -> LoginPage(req, res));

        before("/home", new Filter() {
            @Override
            public void handle(Request request, Response response) {
                String user = request.queryParams("user");
                String password = request.queryParams("password");

                String dbPassword = userPasswords.get(user);
                if (!(password != null && password.equals(dbPassword))) {
                    firtLoggin = false;

                    response.redirect("/");

                }
            }
        });

        post("/home", (req, res) -> HomePage(req, res));

    }

    /**
     * Metodo que retonra la pagina de login en fromato string
     * @param req request hecha por el usuario
     * @param res response dada al usuario
     * @return pagina html en formato string
     */
    private static String LoginPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<script>\n"
                + "function boton(){ if(!"
                + firtLoggin
                +"){alert(\"Autenticacion fallida intente de nuevo\");}}"
                + "</script>\n"
                + "<body onload=\"boton()\">"
                + "<Center>"
                + "</br>"
                + "</br>"
                + "<h2>Login</h2>"
                + "</br>"
                + "</br>"
                + "<form method=\"post\" action=\"/home\">"
                + "  User:<br>"
                + "  <input type=\"text\" name=\"user\">"
                + "  </br>"
                + "</br>"
                + "  password:<br>"
                + "  <input type=\"password\" name=\"password\">"
                + "</br>"
                + "</br>"
                + "  <input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</Center>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    /**
     * Metodo que hace el request hacia el segundo servicio alojado segunda maquina virtual en AWS
     * @param req request hecha por el usuario
     * @param res response dada al usuario
     * @return un string con la pagina que contiene el segundo servicio
     */
    private static String HomePage(Request req, Response res) {
        String home = "";
        try {
            URL url = new URL("https://ec2-34-224-66-113.compute-1.amazonaws.com:17000/home");
            home = UrlReader.urlprueba(url.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return home;
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
        return 6000;

    }
}


