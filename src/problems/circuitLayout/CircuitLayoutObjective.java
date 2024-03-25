/*
 * Developer  : Ali Samanipour
 * Contact    : AliSamanipour.Official@Gmail.com
 * GitHub     : https://github.com/samanipour/
 * Description: An objective function and Solution Space for the circuit layout problem with permeation-based solution candidates
 */

package problems.circuitLayout;
import java.util.ArrayList;
import interfaces.IObjectiveFunction;

public class CircuitLayoutObjective implements IObjectiveFunction<int[][]> {
    public int circuitSize; // Number of grid cells
    public ArrayList<ArrayList<Integer>> connections; // List of connections; each list represents connected chip IDs
    public double gridCellSize; // Size of each cell in the grid

    public CircuitLayoutObjective(int circuitSizeArg, ArrayList<ArrayList<Integer>> connectionsArg,double girdCellSizeArg){
        super();
        this.circuitSize=circuitSizeArg;
        this.connections=connectionsArg;
        this.gridCellSize=girdCellSizeArg;
    }

    @Override
    public double compute(int[][] x) {
        // If k is assigned to x[i][j], it means a chip with ID k is placed at position (i, j) in the grid.
        // We'll calculate the Monte Carlo distance between x[i][j] (chip k) and x[z][m] (chip w).

        double totalTrackLength = 0.0;

        for (int k = 0; k < connections.size(); k++) {
            ArrayList<Integer> connectedChips = connections.get(k);
            int chipX = -1; // Initialize with invalid values
            int chipY = -1;

            // Find the position (x, y) of chip k
            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < x[0].length; j++) {
                    if (x[i][j] == k) {
                        chipX = i;
                        chipY = j;
                        break;
                    }
                }
            }

            if (chipX != -1 && chipY != -1) {
                // Calculate the distance to connected chips
                for (int w : connectedChips) {
                    int otherChipX = -1;
                    int otherChipY = -1;

                    // Find the position (x, y) of chip w
                    for (int i = 0; i < x.length; i++) {
                        for (int j = 0; j < x[0].length; j++) {
                            if (x[i][j] == w) {
                                otherChipX = i;
                                otherChipY = j;
                                break;
                            }
                        }
                    }

                    if (otherChipX != -1 && otherChipY != -1) {
                        // Calculate the Manhattan distance (Monte Carlo distance)
                        double distance = Math.abs(chipX - otherChipX) * gridCellSize
                                + Math.abs(chipY - otherChipY) * gridCellSize;
                        totalTrackLength += distance;
                    }
                }
            }
        }

        return totalTrackLength;
    }
}
