package org.appCoches.modelo;

public class Coche {
    String matricula;
    String modelo;
    int antiguedad;
    double precio;

    public Coche(String matricula, String modelo, int antiguedad, double precio) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.antiguedad = antiguedad;
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "matricula=" + matricula +
                ", modelo='" + modelo + '\'' +
                ", años=" + antiguedad + '\'' +
                ", precio=" + precio +
                '}';
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}
