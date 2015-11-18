import java.util.ArrayList;
import java.util.List;

public class gpuElement {
	private String name;
	private List<Integer> value;
	public gpuElement(String n, List<Integer> v) {
		name = n;
		value = new ArrayList<Integer> (v);
		
	}
	public String getName() {
		return name;
	}
	public List<Integer> getValue() {
		return value;
	}
}
