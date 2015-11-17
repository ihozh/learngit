import java.util.List;
import java.util.ArrayList;
public class ExecRules {
        private List<Integer> ruleLst;
        public ExecRules(List<Integer> T) {
                ruleLst = new ArrayList<Integer>(T);
        }
        public ExecRules() {
                ruleLst = new ArrayList<Integer>();
        }
        public List<Integer> getExecRules() {
                return ruleLst;
        }
        public void addRule(int a) {
                ruleLst.add(a);
        }
}

