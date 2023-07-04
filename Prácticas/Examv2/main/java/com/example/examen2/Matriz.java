package com.example.examen2;

import java.io.Serializable;
public class Matriz implements Serializable{
    private String nombre;
    private int x,y;
    private int[][] elementos;

    public Matriz(String nombre,int[][] elementos,int x,int y){
        this.nombre=nombre;
        this.elementos=elementos;
        this.x=x;
        this.y=y;
    }

    public String getNombre() {
        return nombre;
    }

    public int[][] getElementos() {
        return elementos;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

