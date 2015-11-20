package src;
import java.util.List;
import java.util.ArrayList;
public class ExecRules {
	private List<Rule> ruleLst;
	
	public ExecRules(List<Rule> T) {
		ruleLst = new ArrayList<Rule>(T);
	}
	public ExecRules() {
		ruleLst = new ArrayList<Rule>();
	}
	public List<Rule> getExecRules() {
		return ruleLst;
	}
	public void addRule(Rule a) {
		ruleLst.add(a);
	}
}

