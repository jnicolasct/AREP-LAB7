package edu.escuelaing.AREP.URLReader;

import java.io.*;
import java.net.*;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class UrlReader {

    /**
     * Metodo que genera la conexion y verifica la seguridad de esta
     * @param url la URL a la cual se le va a generar la conexion
     * @return el string de respuesta, que cotiene una pagina html
     */
    public static String urlprueba(String url) {
        String vista = "";
        try {

            // Create a file and a password representation
            //File trustStoreFile = new File("../OtherServiceArep/keystores/myTrustStore");
            File trustStoreFile = new File("OtherSparkService/keystores/myTrustStore");
            char[] trustStorePassword = "areplab7".toCharArray();

            // Load the trust store, the default type is "pkcs12", the alternative is "jks"
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);

            // Get the singleton instance of the TrustManagerFactory
            TrustManagerFactory tmf = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());

            // Itit the TrustManagerFactory using the truststore object
            tmf.init(trustStore);

            //Print the trustManagers returned by the TMF
            //only for debugging
            for (TrustManager t : tmf.getTrustManagers()) {
                System.out.println(t);
            }

            //Set the default global SSLContext so all the connections will use it
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);

            vista = readURL(url);

            // We can now read this URL
            //readURL("https://localhost:5000");
            // This one can't be read because the Java default truststore has been
            // changed.
            //readURL("https://www.google.com");
        } catch (KeyStoreException ex) {
            Logger.getLogger(UrlReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UrlReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UrlReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UrlReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateException ex) {
            Logger.getLogger(UrlReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(UrlReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vista;
    }


    /**
     * Metodo que recive realiza la request hacia el segundo servicio
     * @param sitetoread la URL a la cual se va a hacer la request
     * @return la respuesta del request, que contiene una pagina html
     */
    public static String readURL(String sitetoread) {
        String vista = "";
        try {
            javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                    new javax.net.ssl.HostnameVerifier() {

                        public boolean verify(String hostname,
                                              javax.net.ssl.SSLSession sslSession) {
                            if (hostname.equals("localhost")) {
                                return true;
                            }
                            return false;
                        }
                    });
            // Crea el objeto que representa una URL2
            URL siteURL = new URL(sitetoread);
            // Crea el objeto que URLConnection
            URLConnection urlConnection = siteURL.openConnection();
            // Obtiene los campos del encabezado y los almacena en un estructura Map
            Map<String, List<String>> headers = urlConnection.getHeaderFields();
            // Obtiene una vista del mapa como conjunto de pares <K,V>
            // para poder navegarlo
            Set<Map.Entry<String, List<String>>> entrySet = headers.entrySet();
            // Recorre la lista de campos e imprime los valores
            for (Map.Entry<String, List<String>> entry : entrySet) {
                String headerName = entry.getKey();

                //Si el nombre es nulo, significa que es la linea de estado
                if (headerName != null) {
                    System.out.print(headerName + ":");
                }
                List<String> headerValues = entry.getValue();
                for (String value : headerValues) {
                    System.out.print(value);
                }
                System.out.println("");
            }

            System.out.println("-------message-body------");
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                vista += inputLine;
                System.out.println(inputLine);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return vista;
    }

}
