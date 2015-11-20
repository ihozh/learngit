package src;
import java.util.ArrayList;
import java.util.List;

public class gpuElement {
	private List<Element> value;
	public gpuElement() {
		value = new ArrayList<Element>();
	}
	public gpuElement(List<Element> e) {
		value = new ArrayList<Element> (e);
	}

	public List<Element> getValue() {
		return value;
	}
}
