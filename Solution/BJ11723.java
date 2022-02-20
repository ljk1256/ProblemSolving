package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ11723 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			
			if(command.equals("add")) set.add(Integer.parseInt(st.nextToken()));
			else if(command.equals("remove")) set.remove(Integer.parseInt(st.nextToken()));
			else if(command.equals("check")) {
				if(set.contains(Integer.parseInt(st.nextToken()))) bw.write(String.valueOf(1));
				else bw.write(String.valueOf(0));
				bw.write("\n");
			}
			else if(command.equals("toggle")) {
				int number = Integer.parseInt(st.nextToken());
				if(set.contains(number)) set.remove(number);
				else set.add(number);
			}
			else if(command.equals("all")) {
				for(int i=1; i<21; i++) set.add(i);
			}
			else set.clear();
		}
		
		bw.flush();
		bw.close();
	}

}
