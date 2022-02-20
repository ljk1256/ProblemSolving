package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2527 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				
				int x3 = Integer.parseInt(st.nextToken());
				int y3 = Integer.parseInt(st.nextToken());
				
				int x4 = Integer.parseInt(st.nextToken());
				int y4 = Integer.parseInt(st.nextToken());
				
				if((x2 == x3 && y2 == y3) || (x2 == x3 && y1 == y4) || (x1 == x4 && y1 == y4) || (x1 == x4 && y2 == y3)) {
					System.out.println("c");
					continue;
				}
				else if((y1 < y4 && y4 < y2 && x1 == x4) || (y1 < y3 && y4 < y2 && x1 == x4) || (y1 < y3 && y3 < y2 && x1 == x4) || (y1 > y3 && y2 < y4 && x1 == x4)) {
					System.out.println("b");
					continue;
				}
				else if((y3 < y2 && y2 < y4 && x2 == x3) || (y1 < y3 && y4 < y2 && x2 == x3) || (y1 < y4 && y4 < y2 && x2 == x3) || (y3 < y1 && y2 < y4 && x2 == x3)) {
					System.out.println("b");
					continue;
				}
				else if((x3 < x1 && x1 < x4 && y1 == y4) || (x1 < x3 && x4 < x2 && y1 == y4) || (x1 < x3 && x3 < x2 && y1 == y4) || (x3 < x1 && x2 < x4 && y1 == y4)) {
					System.out.println("b");
					continue;
				}
				else if((x1 < x4 && x4 < x2 && y2 == y3) || (x1 < x3 && x4 < x2 && y2 == y3) || (x1 < x3 && x3 < x2 && y2 == y3) || (x3 < x1 && x2 < x4 && y2 == y3)) {
					System.out.println("b");
					continue;
				}
				else if (y4 < y1 || y2 < y3 || x4 < x1 || x2 < x3) {
					System.out.println("d");
					continue;
				}
				else {
					System.out.println("a");
					continue;
				}
			}
	}

}
