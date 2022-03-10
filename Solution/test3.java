package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class test3 {
	
	static class Data {
		
		int x;

		public Data(int x) {
			super();
			this.x = x;
		}
		
	}

	public static void main (String[] args) throws IOException {
        HashMap<Integer, Data> hm = new HashMap<>();
        
        hm.put(1, new Data(3));
        
        System.out.println(hm.get(1).x);
        
        Data temp = hm.get(1);
        temp.x = 5;
        
        System.out.println(hm.get(1).x);
	}

}
