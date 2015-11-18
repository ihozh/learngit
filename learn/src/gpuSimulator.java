import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class gpuSimulator {
	List<Element> eleList;
	List<ExecRules> ruleList;
	
	public gpuSimulator() {
		eleList = new ArrayList<Element>();
		ruleList = new ArrayList<ExecRules>();
	}
	public static void main(String[] args) {
		String info = args[0];
		gpuSimulator gpusim = new gpuSimulator();
		gpusim.readFile(info);
		gpusim.getEleList();
		gpusim.getRuleList();
	}


	public void readFile (String infn) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(infn));
			Utility.parseElements(eleList,br);
			Utility.parseRules(ruleList,br);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void getEleList () {
		for (Element element:eleList) {
			System.out.println(element.getName()+" "+element.getValue());
		}
	}
	public void getRuleList() {
		for (ExecRules execrules:ruleList) {
			List<Rule> tmprule = execrules.getExecRules();
			for (Rule rule:tmprule) {
				System.out.println(rule.getLval());
			}
		}
	}
}
