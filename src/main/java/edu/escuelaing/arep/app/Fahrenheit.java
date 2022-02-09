package edu.escuelaing.arep.app;

public class Fahrenheit {

    private double grados;

    public Fahrenheit(double grados) {
        this.grados = grados;
    }

    public double getGrados() {
        return grados;
    }

    public double convertToCelsius(){
        return (this.grados-32)*5/9;
    }
}
