import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Collections;

public class gpuSimulator {
	List<Element> eleList;
	List<ExecRules> ruleList;
	List<gpuElement> gpuEleList;
	List<gpuExecRules> gpuRuleList;
	
	public gpuSimulator() {
		eleList = new ArrayList<Element>();
		ruleList = new ArrayList<ExecRules>();
		gpuEleList = new ArrayList<gpuElement>();
		gpuRuleList = new ArrayList<gpuExecRules>();
	}
	public static void main(String[] args) {
		String info = args[0];
		int run = Integer.parseInt(args[1]);
		gpuSimulator gpusim = new gpuSimulator();
		gpusim.readFile(info);
		gpusim.getEleList();
		gpusim.gpuImpRules(gpusim.getRuleList(),run);
		gpusim.getgpuRuleList();
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
	public List<ExecRules> getRuleList() {
		return ruleList;
	}
	public void getgpuRuleList() {
		for (gpuExecRules gpurule:gpuRuleList) {
			List<ExecRules> tmpexecrule = gpurule.getgpuExecRules();
			for (ExecRules execrules:tmpexecrule) {
				List<Rule> tmprule = execrules.getExecRules();
				for (Rule rule:tmprule) {
					System.out.print(rule.getLval()+" ");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	public void gpuImpRules (List<ExecRules> gpurule, int run) {
		for (int i=0;i<run;i++) {
			Collections.shuffle(gpurule);
			gpuExecRules tmpR = new gpuExecRules(gpurule);
			gpuRuleList.add(tmpR);
		}
	}
}
