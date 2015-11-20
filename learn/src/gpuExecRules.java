package src;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class gpuExecRules {
	private List<ExecRules> gpuRuleLst;
	public gpuExecRules() {
		gpuRuleLst = new ArrayList<ExecRules>();
	}
	public gpuExecRules(List<ExecRules> T) {
		gpuRuleLst = new ArrayList<ExecRules> (T);
	}
	/*public void addExecRule(ExecRules rule,int run) {
		for (int i=0;i<run;i++) {
			Collections.shuffle(rule.getExecRules());
			ExecRules tmpR = new ExecRules(rule.getExecRules());
			gpuRuleLst.add(tmpR);
		}
	}*/
	public void addExecRule(ExecRules execrules) {
		gpuRuleLst.add(execrules);
	}
	public List<ExecRules> getgpuExecRules() {
		return gpuRuleLst;
	}
}
