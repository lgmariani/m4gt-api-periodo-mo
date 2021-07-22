package com.prosegur.m4gt;

import com.google.gson.*;


/*
Clase Peticion
Tiene 1 metodo publico: (+setters y getters)

* devuelveJson: Convierte los atributos de la clase en un JSON.

Lautaro Gaviola/Cristian Cantero
*/

public class Peticion {

    private String Idorg;
    private String Dtstart;
    private String Dtend;
    private String ID;
    private String Operacion;
    private String url;

    public void validateData() {
        String Faltas="";

        if (Idorg.isEmpty() || Idorg.equalsIgnoreCase("#")) {
            Faltas=Faltas+"Idorg";
        }

        if (Dtstart.isEmpty() || Dtstart.equalsIgnoreCase("#")) {
            Faltas=Faltas+"Dtstart";
        }

        if (Dtend.isEmpty() || Dtend.equalsIgnoreCase("#")) {
            Faltas=Faltas+"Dtend";
        }

        if (ID.isEmpty() || ID.equalsIgnoreCase("#")) {
            Faltas=Faltas+"ID";
        }

        if (Operacion.isEmpty() || Operacion.equalsIgnoreCase("#")) {
            Faltas=Faltas+"Operacion";
        }

        if (url.isEmpty() || url.equalsIgnoreCase("#")) {
            Faltas=Faltas+"url";
        }

        if (Faltas.length() > 1) {
            throw new IllegalArgumentException("Faltan parametros: " + Faltas);
        }

    }

    public String devuelveJson() {

        JsonObject ObjetoPrincipal = new JsonObject();


        ObjetoPrincipal.addProperty("idorg", getIdorg());
        ObjetoPrincipal.addProperty("dtstart", getDtstart());
        ObjetoPrincipal.addProperty("dtend", getDtend());
        ObjetoPrincipal.addProperty("id", getID());
        ObjetoPrincipal.addProperty("operation", getOperacion());

        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        Json = gson.toJson(ObjetoPrincipal);
        return Json;
    }

    private String Json;

    public String getIdorg() {
        return Idorg;
    }

    public void setIdorg(String idorg) {
        Idorg = idorg;
    }

    public String getDtstart() {
        return Dtstart;
    }

    public void setDtstart(String dtstart) {
        Dtstart = dtstart;
    }

    public String getDtend() {
        return Dtend;
    }

    public void setDtend(String dtend) {
        Dtend = dtend;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOperacion() {
        return Operacion;
    }

    public void setOperacion(String operacion) {
        Operacion = operacion;
    }

    public String getJson() {
            return Json;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
