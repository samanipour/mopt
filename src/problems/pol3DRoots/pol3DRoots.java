/*
 * Developer  : Ali Samanipour
 * Contact    : AliSamanipour.Official@Gmail.com
 * GitHub     : https://github.com/samanipour/
 * Description: An objective function and Solution Space for Finding 3 Degree Polynomial Roots problem with permeation-based solution candidates
 * assume f(x)= β+α1*x+α2*x^2+α3*x^3
 * */
package problems.pol3DRoots;

import interfaces.IObjectiveFunction;

public class pol3DRoots implements IObjectiveFunction<double[]>{
    double beta;
    double alpha1;
    double alpha2;
    double alpha3;
    public pol3DRoots(double beta_arg, double alpha1_arg, double alpha2_arg, double alpha3_arg){
        super();
        this.beta = beta_arg;
        this.alpha1=alpha1_arg;
        this.alpha2=alpha2_arg;
        this.alpha3=alpha3_arg;
    }
    @Override
    public double compute(double[] x) {
        double fx;
        fx = beta + alpha1*x[1] + alpha2*(Math.pow(x[2],2)) + alpha3*(Math.pow(x[3],3));
        return fx;
    }

}
