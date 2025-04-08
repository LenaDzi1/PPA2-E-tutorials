/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ppa2e_lab_03;

//////////////////////// INTRODUCTION ///////////////////
interface IShape{
    String getInfo();
    void printInfo();

}

abstract class AGeomShape implements IShape {
    int x,y;
    public AGeomShape (int x, int y){
        this.x =x;
        this.y = y;
               
    }

    @Override
    public void printInfo() {
        System.out.println(getInfo());
    }

}


class Point extends AGeomShape {

    public Point(int x, int y) {
        super(x, y);
    }

    @Override
    public String getInfo(){
        return x+ ", " +y;
    }
}

class Line extends AGeomShape {

    double length;
    
    public Line(int x, int y) {
        super(x, y);
        length = Math.sqrt(x*x+y*y);
        
    }

    @Override
    public String getInfo(){
        return "length: " +length;
    }

}


///////////////////////// DEBUGGING ///////////////////////

public class Exercise03 {
	public static void main(String[] args) {
		LinearFunction lf = new LinearFunction(0, 1);   // f(x) = 1
		Integrator integrator = new Integrator();
		integrator.setDelta(0.01);
                // first value is lower than the second one ex. 0<10 [see below]
		double integral = integrator.integrate(lf, 0, 10);
		System.out.println(integral);
                
                QuadraticPolynomial qf = new QuadraticPolynomial(2,4,1);
                double integralQ = integrator.integrate(qf, 0, 10);
                System.out.println(integralQ);
                
                double diff = qf.differentiate(10);
                System.out.println(diff); //44.0
                
                double diff2 = lf.differentiate(10);
                System.out.println(diff2); //0.0
                
                lf = new LinearFunction(2, 2);
                diff2 = lf.differentiate(10);
                System.out.println(diff2); //2.0
	}
}

interface IFunction {
	double valueAt(double p);
        double differentiate(double p);
        
}


abstract class AbstractFunction implements IFunction {
    double epsilon = 0.0001;
    double h = 0.01;
    
    public void setEpsilon (double e) {
        this.epsilon = e;

    }
    
    
    
    @Override
    public double differentiate(double p) {
        double result1;
        double result2;
        
        while(true){
            result1 = (valueAt(p+h) - valueAt(p))/h;
            h /= 2;

            result2 = (valueAt(p+h) - valueAt(p))/h;
            double diff = result2 - result1;
        
                if (Math.abs(diff)>epsilon){
                   result1 = result2 ; 
                } else  { return result1;}
      
        }
      
   }
    
}

class Integrator {
	double delta;
	
	double integrate(IFunction f, double a, double b) {
		double result = 0;		
		double p = a;
		double v = f.valueAt(p);
		while(p+delta<b) {
			// rectengles of width delta
			result += delta * v;
			p += delta;
			v = f.valueAt(p);
		}
		// now for the last rectangle, which will be narrower than the others
		result += (b-p) * v; //// b - p is like 
		return result;
	}
	
	void setDelta(double d)
	{
		this.delta = d;  ////////// change delta to d
	}
	
}

class LinearFunction extends AbstractFunction{
	double k, q;
	
        
        //// f(p) = k*p + q
        
        @Override
        public double valueAt(double p) {   //// change to right Override method from the interface
		return(k*p+q);    //////// get right value p
	}
	
	public LinearFunction(double k, double q) {
		this.k = k;
		this.q = q;
	}

  

 
}

///////////////////// TASK 2 ////////////////

class QuadraticPolynomial extends AbstractFunction {
	double a, b, c;
	
        
        //// f(p) = a*p^2 + b*p+ c
        
        @Override
        public double valueAt(double p) {   
		return(a*p*p+b*p+c);   
	}
	
	public QuadraticPolynomial(double a, double b, double c) {
		this.a = a;
		this.b = b;
                this.c = c;
                
	}

    

 
}