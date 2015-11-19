package src;
import java.util.ArrayList;
import java.util.List;

public class gpuUtility {

	public native static int[][] caSimNoRank(int[][] ele, String[][] Rrule, String[][] Lrule);
	public native static int[][] caSimRank(int[][] ele);
	public native static int[][] raSimProc(int[][] ele);

}
