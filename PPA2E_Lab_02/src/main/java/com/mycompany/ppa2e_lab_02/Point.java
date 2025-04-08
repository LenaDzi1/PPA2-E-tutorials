/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ppa2e_lab_02;


public class Point {
    private double x;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    private double y;
    
    public Point (double x, double y){
        this.x = x;
        this.y = y;
        
    }
    
    public double distanceTo(Point other){
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx*dx + dy*dy);
        
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
    
    
    
    
}
