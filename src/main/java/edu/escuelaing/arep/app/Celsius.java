package edu.escuelaing.arep.app;

public class Celsius {

    private double grados;

    public Celsius(double grados) {
        this.grados = grados;
    }

    public double getGrados() {
        return grados;
    }

    public double convertToFahrenheit(){
        return (this.grados *9/5)+32;
    }

}
