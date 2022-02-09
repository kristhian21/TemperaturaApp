package edu.escuelaing.arep.app;

import spark.Spark;
import spark.utils.IOUtils;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static spark.Spark.*;

public class SparkApp {

    public static void main(String[] args) {
        port(getPort());
        get("/hello", (req, res) -> "Hello World");
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

}

