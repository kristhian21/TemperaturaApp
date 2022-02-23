package edu.escuelaing.arep.app;



import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.google.gson.Gson;
import spark.Filter;

import static spark.Spark.*;

public class SparkApp {

    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getPort());
        after((Filter) (request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET");
        });
        get("/celsius", (req, res) -> {
            res.type("application/json");
            return convertToFahrenheit(req.queryParams("celsius"));
        });
        get("/fahrenheit", (req, res) -> {
            res.type("application/json");
            return convertToCelsius(req.queryParams("fahrenheit"));
        });
        get("/", (req, res) -> renderIndex());
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
        //returns default port if heroku-port isn't set (i.e. on localhost)
    }

    private static String renderIndex() {
        try {
            URL url = SparkApp.class.getResource("/public/index.html");
            return new String(Files.readAllBytes(Paths.get(url.toURI())), Charset.defaultCharset());
        } catch (IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static String convertToCelsius(String gradosFahr){
        return new Gson().toJson(new Fahrenheit(Double.parseDouble(gradosFahr)).convertToCelsius());
    }

    private static String convertToFahrenheit(String gradosCel){
        return new Gson().toJson(new Celsius(Double.parseDouble(gradosCel)).convertToFahrenheit());
    }



}

