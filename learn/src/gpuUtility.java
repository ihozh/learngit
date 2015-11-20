package src;
import java.util.ArrayList;
import java.util.List;

public class gpuUtility {

	public static native int[][] caSimNoRank(int[][] ele, String[][] Rrule, String[][] Lrule);
	public static native String getString(String arg);
	public static native int[] getArray(int[] args);
	//public native static int[][] caSimRank(int[][] ele);
	//public native static int[][] raSimProc(int[][] ele);
	static{
		try {
			System.loadLibrary("gpuUtility");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
