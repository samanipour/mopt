/*
 * Developer  : Ali Samanipour
 * Contact    : AliSamanipour.Official@Gmail.com
 * GitHub     : https://github.com/samanipour/
 * Description: An objective function and Solution Space for the Job Shop Scheduling problem with permeation-based solution candidates
 * References:
     * https://thomasweise.github.io/oa/optimization_algorithms.pdf
     * https://optimization.cbe.cornell.edu/index.php?title=Job_shop_scheduling
     * https://en.wikipedia.org/wiki/Job-shop_scheduling
 */

package problems.jobShopScheduling;

import interfaces.IObjectiveFunction;

public class JobShopSchedulingObjective implements IObjectiveFunction<int[]> {
    private final int[][] processingTimes; // Matrix of processing times for jobs and machines
    public JobShopSchedulingObjective(int[][] processingTimes) {
        this.processingTimes = processingTimes;
    }

    @Override
    public double compute(int[] schedule) {
        // Calculate the makespan (total duration) based on the given schedule
        int numJobs = processingTimes.length;
        int numMachines = processingTimes[0].length;

        int[] machineFinishTime = new int[numMachines]; // Finish time for each machine
        for (int i = 0; i < numJobs; i++) {
            int job = schedule[i]; // Job to be scheduled
            for (int j = 0; j < numMachines; j++) {
                int machine = j; // Machine assigned to the operation
                int startTime = Math.max(machineFinishTime[machine], i > 0 ? machineFinishTime[machine] : 0);
                machineFinishTime[machine] = startTime + processingTimes[job][machine];
            }
        }

        // Find the maximum finish time across all machines
        int makespan = 0;
        for (int finishTime : machineFinishTime) {
            makespan = Math.max(makespan, finishTime);
        }

        return makespan;
    }

}
/* 
public class Main {
    public static void main(String[] args) {
        // Define the processing times matrix
        int[][] processingTimes = {
            {3, 2}, // Job J1: [M1, M2]
            {4, 3}, // Job J2: [M1, M2]
            {2, 5}  // Job J3: [M1, M2]
        };

        // Create an instance of the ObjectiveFunction
        IObjectiveFunction objectiveFunction = new ObjectiveFunction(processingTimes);

        // Example schedule: J1 -> M1, J2 -> M2, J3 -> M1
        int[] schedule = {0, 1, 0};

        // Calculate the makespan using the objective function
        double makespan = objectiveFunction.compute(schedule);

        System.out.println("Hypothetical Schedule:");
        System.out.println("J1 -> M1");
        System.out.println("J2 -> M2");
        System.out.println("J3 -> M1");
        System.out.println("Makespan: " + makespan);
    }
}
*/