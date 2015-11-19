import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class gpuSimulator {
	List<Element> eleList;
	List<ExecRules> ruleList;
	List<gpuElement> gpuEleList;
	List<gpuExecRules> gpuRuleList;
	Map<String, Element> eleMap;
	Map<String, Integer> eleMapM;
	//int[][] eleMatrix;
	//String[][] ruleLMatrix;
	//String[][] ruleRMatrix;

	public gpuSimulator() {
		eleList = new ArrayList<Element>();
		ruleList = new ArrayList<ExecRules>();
		gpuEleList = new ArrayList<gpuElement>();
		gpuRuleList = new ArrayList<gpuExecRules>();
		eleMap = new HashMap<String,Element>();
		eleMapM = new HashMap<String,Integer>();
	}
	public static void main(String[] args) {
		String info = args[0];
		int run = Integer.parseInt(args[1]);
		int cycles = Integer.parseInt(args[2]);
		gpuSimulator gpusim = new gpuSimulator();
		gpusim.readFile(info);
		//gpusim.getEleList();
		gpusim.gpuImpEle(gpusim.getEleList(),run);
		gpusim.gpuImpRules(gpusim.getRuleList(),run);
		//gpusim.getgpuEleList();
		//gpusim.showEleM();
		gpusim.getgpuRuleList();
		gpusim.caSim(run,cycles);
	}


	public void readFile (String infn) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(infn));
			Utility.parseElements(eleList,br,eleMap,eleMapM);
			Utility.parseRules(ruleList,br,eleMapM);
			//Utility.parseToMap(eleMatrix);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void showEleList () {
		for (Element element:eleList) {
			System.out.println(element.getName()+" "+element.getCurVal());
		}
	}
	public void getgpuEleList() {
		for (gpuElement gpue:gpuEleList) {
			List<Element> tmpele = gpue.getValue();
			for (Element e:tmpele) {
				System.out.print(e.getName()+" "+e.getCurVal()+" ");
			}
			System.out.println();
		}
	}

	/*public void showEleM() {

		int len = eleMatrix.length;
		int wid = eleMatrix[0].length;
		for (int i=0;i<len;i++) {
			for (int j=0;j<wid;j++) {
				System.out.print(eleMatrix[i][j]);
			}
			System.out.println();
		}

	}*/

	public List<Element> getEleList() {
		return eleList;
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
					System.out.print(rule.getLval()+" "+rule.getRval()+" |");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	public void gpuImpEle (List<Element> el, int run) {
		for (int i = 0;i < run;i++) {
			gpuElement tmpE = new gpuElement(el);
			gpuEleList.add(tmpE);
		}
	}
	public void gpuImpRules (List<ExecRules> gpurule, int run) {
		for (int i=0;i<run;i++) {
			//Collections.shuffle(gpurule);
			gpuExecRules tmpR = new gpuExecRules(gpurule);
			gpuRuleList.add(tmpR);
		}
	}
	public void caSim(int run,int cycles) {
		Utility.caSimulation(run,cycles,eleList,ruleList,
				eleMap);
	}
}
