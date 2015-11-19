import java.util.ArrayList;
import java.util.List;

public class Element {
    private String name;
    private int value;
	private int curVal;
    public Element(String line) {
        String[] tmpArr = line.split("=");
        name = tmpArr[0].trim();
        value = eval(tmpArr[1].trim());
		curVal = value;
    }
    public int eval(String in) {
        if (in.toLowerCase().equals("true"))
            return 1;
        else if (in.toLowerCase().equals("false"))
            return 0;
        else {
            System.err.println("Wrong initial value!");
            return -1;
        }
    }
	public String getName() {
		return name;
	}
	public int getCurVal() {
		return curVal;
	}
	public void setCurVal(int v) {
		curVal = v;
	}
}
