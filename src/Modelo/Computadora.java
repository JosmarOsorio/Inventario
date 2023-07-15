// ejercicio 1 sumativa 4 .cpp : Programa que muestra el inventario de una tienda de computadoras. Por: Josmar Osorio y Stephania Salmeron. Para: Programación II-UBA.

package Modelo;//Creamos el package modelo para declarar las variables y el constrctor de estas

public class Computadora {//Creamos la clase Computadora con todas las variables que trabajaremos
    String marca;
    String procesador;
    String sistemaoperativo;

    //Creamos el constructor de cada variable
    public Computadora () {
        super();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }


    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public String getSistemaoperativo() {
        return sistemaoperativo;
    }

    public void setSistemaoperativo(String sistemaoperativo) {
        this.sistemaoperativo = sistemaoperativo;
    }

}