import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utility {
	public static void parseElements(List<Element> eleList, BufferedReader br) {
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				if (line.contains("Rules:"))
					return;
				if (line.length() == 0)
					continue;
				Element tmpEle = new Element(line);
				eleList.add(tmpEle);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void parseRules(List<ExecRules> erList, BufferedReader br) {
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				if (line.length() == 0)
					continue;
				line = line.trim();
				ExecRules er = new ExecRules();
				er.addRule(new Rule(line));
				erList.add(er);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
