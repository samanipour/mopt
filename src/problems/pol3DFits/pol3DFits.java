/*
 * Developer  : Ali Samanipour
 * Contact    : AliSamanipour.Official@Gmail.com
 * GitHub     : https://github.com/samanipour/
 * Description: An objective function and Solution Space for Finding 3 Degree Polynomial that Fits to a sample data set problem
 * assume f(x)= β+α1*x+α2*x^2+α3*x^3
 * where β=x[0], α1=x[1], α2=x[2], α3=x[3]
 */
package problems.pol3DFits;

import interfaces.IObjectiveFunction;

public class pol3DFits implements IObjectiveFunction<double[]>{
    double[][] dataPoints;
    public pol3DFits(double[][] dataPointsArg){
        super();
        this.dataPoints=dataPointsArg;
    }
    
    @Override
    public double compute(double[] x) {
        double fx;
        double error=0;
        for(int i=0;i<dataPoints.length;i++){
            double xValue = dataPoints[i][0];
            double yValue = dataPoints[i][1];
            fx = x[0] + x[1]* xValue+ x[2]*(Math.pow(xValue,2)) + x[3]*(Math.pow(xValue,3));
            error += Math.abs(fx-yValue);
        }
        return error;
    }

}
