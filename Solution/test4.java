package Solution;

import java.util.ArrayList;
import java.util.Collections;

public class test4 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		
		list.add("+");
		list.add("-");
		list.add(" ");
		
		Collections.sort(list);
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}

}
