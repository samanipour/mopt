/*
 * Developer  : Ali Samanipour
 * Contact    : AliSamanipour.Official@Gmail.com
 * GitHub     : https://github.com/samanipour/
 * Description: An objective function and Solution Space for Stock Prediction problem
 * 
 */
package problems.stockPrediction;

import interfaces.IObjectiveFunction;

public class StockPrediction implements IObjectiveFunction<double[]>{
    double[] actualPrices;
    double[] predictedPrice;

    public StockPrediction(double[] actualPricesArg){
        super();
        this.actualPrices=actualPricesArg;
    }

    @Override
    public double compute(double[] x) {
        this.predictedPrice=x;
        double mse = calculateMSE(x);
        return mse;
    }

    private double calculateMSE(double[] solutionCandidate) {
        // Calculate the MSE between predicted and actual stock prices
        double sumSquaredErrors = 0.0;
        int n = actualPrices.length;

        for (int i = 0; i < n; i++) {
            double error = this.predictedPrice[i] - actualPrices[i];
            sumSquaredErrors += Math.pow(error, 2);
        }

        double mse = sumSquaredErrors / n;
        return mse;
    }
}
