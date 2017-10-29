package io;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		OutputGenerator o = new OutputGenerator();
		List<String[]> p = new ArrayList<String[]>();
		String[] a = {"0", "heeloo"};
		String[] b = {"1", "dies"};
		String[] c = {"3", "Monday"};
		
		p.add(a);
		p.add(b);
		p.add(c);
		
		o.problemSetOutput(p);

	}

}
