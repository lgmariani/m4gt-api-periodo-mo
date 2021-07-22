package com.prosegur.m4gt;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.nio.charset.StandardCharsets;


/*
Clase Main
Tiene 2 métodos estáticos:

* main: Implementada para probar por línea de comandos. llama a ejecutarRequest.
* ejecutarRequest: Recibe los elemtentos del JSON a enviar como parámetros,
                   usa la función Peticion.devuelveJson para generar JSON y
                   lo envía a través la función ManejadorJson.
* ManejadorJson: Toma el JSON generado y lo envía a través de un POST HTTP.

Lautaro Gaviola/Cristian Cantero
*/

//



public class Main {

    public final String USER_AGENT = "Mozilla/5.0";

    public static void main(
        String[] args) {

        ejecutarRequest(args[0],args[1],args[2],args[3],args[4],args[5]);

    }

    public static String ejecutarRequest(
            String url,
            String Idorg,
            String Dtstart,
            String Dtend,
            String ID,
            String Operacion
    ) {
        String ResponseBody;
        String SalidaJson;
        Peticion m = new Peticion();

        m.setUrl(url);
        //A partir de aca lo que es propio de cada registro
        m.setIdorg(Idorg);
        m.setDtstart(Dtstart);
        m.setDtend(Dtend);
        m.setID(ID);
        m.setOperacion(Operacion);

        m.validateData();

        try {
            SalidaJson = m.devuelveJson();
            ResponseBody = ManejadorJson(m);

            System.out.println("----------------JSON ENVIADO-------------");
            System.out.println(SalidaJson);
            System.out.println("----------------RESPONSE BODY------------");
            System.out.println(ResponseBody);

            return ResponseBody;

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "m4_error - see stacktrace 1:" + e.toString();
        }
    }



    public static String ManejadorJson(Peticion m) {

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(m.getUrl());
            StringEntity params = new StringEntity(m.getJson(), ContentType.APPLICATION_JSON);
            request.setEntity(params);

            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();

            //Obtengo el body de la respuesta.
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

            EntityUtils.consume(responseEntity);
            ((Closeable) response).close();

            //Devuelvo el Body a quien haya hecho la llamada (sea Meta4 o linea de comando)
            return responseBody;

        } catch (Exception ex) {
            ex.printStackTrace();
            return "m4_error - see stacktrace 2";
        }
    }
}
