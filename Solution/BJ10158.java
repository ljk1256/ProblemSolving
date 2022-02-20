package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ10158 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		
		int time = Integer.parseInt(br.readLine());
		
		if( ((x + time)/width) % 2 != 0 ) {
			x = width - ((x + time) % width);
		}
		else {
			x = (x + time) % width;
		}
		
		if( ((y + time)/height) % 2 != 0 ) {
			y = height - ((y + time) % height);
		}
		else {
			y = (y + time) % height;
		}
		sb.append(x).append(" ").append(y);
		bw.write(sb.toString());
		bw.flush();
	}

}
