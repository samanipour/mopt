/*
 * Developer  : Ali Samanipour
 * Contact    : AliSamanipour.Official@Gmail.com
 * GitHub     : https://github.com/samanipour/
 * Description: An objective function and Solution Space for Airplane Wing Design problem
 * References :
         * the costValue is calculated based on Prandtl's equation:
         * Miroslav ˇCervenka and Ivan Zelinka. Application of evolutionary algorithm on aerodynamic wing optimisation. 
         * In Proceedings of the 2nd European Computing Conference (ECC’08), Malta, September 11–13, 2008.
         * URL http://www.wseas.us/e-library/conferences/2008/malta/ecc/ecc53.pdf.
 */
package problems.airplaneWing;
import interfaces.IObjectiveFunction;

public class AirplaneWingObjective implements IObjectiveFunction<AirplaneWing> {
    // Define any necessary fields or constructor here
    AirplaneWing wing;

    @Override
    public double compute(AirplaneWing candidate) {

        double airfoilShapeFactor = candidate.getAirfoilShapeFactor(); // Airfoil shape factor (e.g., thickness-to-chord ratio)

        // Calculate lift and drag coefficients (based on Prandtl's equation)
        double liftCoefficient = computeLiftCoefficient(airfoilShapeFactor);
        double dragCoefficient = computeDragCoefficient(airfoilShapeFactor);

        // Consider structural stability (e.g., flutter, buckling)
        double stabilityFactor = computeStabilityFactor(wing);

        // Combine lift and drag
        double costValue = liftCoefficient - dragCoefficient + stabilityFactor;

        return costValue;
    }

    // Implement methods for lift and drag coefficient calculations
    private double computeLiftCoefficient(double airfoilShapeFactor) {
        // liftCoefficient = 2 * π * airfoilShapeFactor
        return 2 * Math.PI * airfoilShapeFactor;
    }

    private double computeDragCoefficient(double airfoilShapeFactor) {
        // dragCoefficient = 0.02 * airfoilShapeFactor
        return 0.02 * airfoilShapeFactor;
    }

    private double computeStabilityFactor(AirplaneWing wing) {
        // stabilityFactor = wingSpan / wingChord
        return wing.getWingSpan() / wing.getWingChord(); // Adjust as needed for your specific model
    }
}
