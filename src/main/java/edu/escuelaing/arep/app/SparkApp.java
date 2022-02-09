package edu.escuelaing.arep.app;

import spark.Spark;
import spark.utils.IOUtils;


import static spark.Spark.*;

public class SparkApp {

    public static void main(String[] args) {
        port(getPort());
        get("/hello", (req, res) -> "Hello World");
        get("/", (req, res) -> IOUtils.toString(Spark.class.getResourceAsStream("/resources/public/index.html")));
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
        //returns default port if heroku-port isn't set (i.e. on localhost)
    }



}

