# Assignment 3
# Motivation
Numeric differentiation and integration are tasks, where practicality is dictated by the possibility of using a powerful computer. Performing numeric differentiation or integration in sufficient precision manually is at least tedious for most practical tasks, but using a computer seems to be an obvious choice. Since the differentiation and integration algorithms are independent of the particular function that is being processed, it is possible to construct a generic program that can deal with any input function. We are going to represent a function by an instance of a certain class. In order to be able to integrate all functions using a single algorithm, it will be necessary to enforce certain functionality: in particular, determining the function value for some value of its parameter. This common behavior will be captured by the IFunction interface.

# Task 1 (debugging) (up to 2 points)
Following program introduces elementary components of the integration software: an interface, one function and an integrator. The program, however, contains syntactic and semantic errors. Find them, fix them, and verify the corectness of the program.

```
public class Exercise03 {
	public static void main(String[] args) {
		LinearFunction lf = new LinearFunction(0, 1);
		Integrator integrator = new Integrator();
		integrator.setDelta(0.01);
		double integral = integrator.integrate(lf, 0, 10);
		System.out.println(integral);
	}
}

interface IFunction {
	double valueAt(double p);
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
		result += (p-b) * v;
		return result;
	}
	
	void setDelta(double d)
	{
		this.delta = delta;
	}
	
}

class LinearFunction implements IFunction{
	double k, q;
	
	double getValue(double p) {
		return(k*x+q);
	}
	
	public LinearFunction(double k, double q) {
		this.k = k;
		this.q = q;
	}
}
```

# Task 2 (adding more functions, numeric derivation) (up to 4 points)
* Add a QuadraticPolynomial class, representing a quadratic polynomial. Implement a constructor that accepts the polynomial coefficients. Next, implement everything that is needed in order to be able to use the numeric integration by the Integrator class. Verify the program by computing integrals of known exact value.
* Add a function differentiate(double p) to the interface IFunction, which returns the value of the first derivative at point p.
* Create an abstract class AbstractFunction, which provides an impelmentation of this method using numeric differentiation. An analytic derivation ca be approximated by the forward difference, i.e. f'(x) ~= [f(x+h) - f(x)]/h for some small value of h. The reliability of th approximation can be related to the change of the approximation when the step h is lowered. In numeric differentiation, use step size h = 0.01 and check, that when h is lowered to one half of its current size, the approximated derivation value is not changed by more than epsilon, where epsilon is an attribute of the AbstractFunction class that can be set by the setEpsilon(double epsilon) method. Add the attribute and method to the class. If the change of the approximation is larger than epsilon, use the halved step size and repeat the verification, until the condition is met.
* Modify the QuadraticPolynomial class, so that it uses the numeric differentiation and check the correctness of the result.
* Modify the LinearFunction class, so that it uses the numeric differentiation, or construct a specific implementation of the differentiate() method using analytic derivation, if you know how to do that.
