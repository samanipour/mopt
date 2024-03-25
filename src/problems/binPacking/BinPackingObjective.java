/*
 * Developer  : Ali Samanipour
 * Contact    : AliSamanipour.Official@Gmail.com
 * GitHub     : https://github.com/samanipour/
 * Description: An objective function for the bin packing problem with permeation-based solution candidates
 */
package problems.binPacking;
import interfaces.IObjectiveFunction;

public class BinPackingObjective implements IObjectiveFunction<int[]> { 
  public final int binSize;
  public final int[] items;

  public BinPackingObjective(final int binSizeArg, final int[] itemsArg) {
    super();
    this.binSize = binSizeArg;
    this.items = itemsArg;
  }
  @Override
  public double compute(final int[] x) {
    int totalBins = 0;
    int remainingSize = 0; 
    int requiredSpace = 0;

    for (final int i : x) { 
      requiredSpace = this.items[i]; 
      if (requiredSpace > remainingSize) { 
        totalBins++; 
        remainingSize = this.binSize;
      }
      remainingSize -= requiredSpace; 
    }
    return totalBins; 
  }
}
