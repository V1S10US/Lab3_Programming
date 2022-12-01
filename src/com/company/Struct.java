package com.company;

public class Struct {
    private double coefficient;
    private int xdegree;
    private int ydegree;
    private int zdegree;
    public  Struct(){}
    public Struct (double c, int x, int y, int z){
        this.coefficient = c;
        this.xdegree = x;
        this.ydegree = y;
        this.zdegree = z;
    }
    public void Output(){
        System.out.println(coefficient);
        System.out.println(xdegree);
        System.out.println(ydegree);
        System.out.println(zdegree);
    }
    public double getCoefficient(){
        return coefficient;
    }
    public void changeCoefficient(double c){
        coefficient = c;
    }
    public void changeXdegree(int x){
        xdegree = x;
    }
    public int getXdegree(){
        return xdegree;
    }
    public void changeYdegree(int y){
        ydegree = y;
    }
    public int getYdegree(){
        return ydegree;
    }
    public void changeZdegree(int z){
        zdegree = z;
    }
    public int getZdegree(){
        return zdegree;
    }
}
