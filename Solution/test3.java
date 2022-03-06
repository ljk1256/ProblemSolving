package Solution;

import java.io.BufferedReader;
import java.io.IOException;

public class test3 {

	public static void main (String[] args) throws IOException {
        BufferedReader br = new Bufferdreader(new InputStreamReader(System.in));
        
        String input = br.readLine();
        int[] alphabet = new int [26];
        
        for(int i=0; i<input.length(); i++) alphabet[input.charAt(i) - 'a']++;
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<26; i++) sb.append(alphabet[i]).append(" ");
        
        sb.setLength(sb.length() - 1);
        System.out.println(sb.toString());
	}

}
