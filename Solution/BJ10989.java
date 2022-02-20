package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ10989 {
	// 출력 속도는   BufferedWriter > StringBuilder > System.out.println 순서이다. 다만, BufferedWriter 는 다중 처리시 안정성을 보장하지 못한다
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int [10001];
		while(N-->0) {
			arr[Integer.parseInt(br.readLine())]++; // 정렬에서 해당 숫자만 알아도 정렬이 된다면 배열로 카운팅 하는것도 좋은 방법이다
		}
		
		for(int i=1; i<10001; i++) {
			if(arr[i] > 0) { // 한번이라도 입력을 받았다면
				while(arr[i]-->0) {
					sb.append(i).append("\n");
				}
			}
		}
		sb.setLength(sb.length()-1);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}
