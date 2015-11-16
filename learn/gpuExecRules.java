import java.util.List;
import java.util.ArrayList;

public class gpuExecRules {
    //private boolean async;
    private List<Integer> gpuRuleLst;
    public gpuExecRules() {
        gpuRuleLst = new ArrayList<Integer>();
    }
    public void addExecRule(int a, int run) {
        for (int i = 0;i<run;i++) {
            gpuRuleLst.add(a);
        }
    }
    public List<Integer> getgpuExecRules() {
        return gpuRuleLst;

    }
    public void sayHello(){
        System.out.println("Hello");
    }
}
