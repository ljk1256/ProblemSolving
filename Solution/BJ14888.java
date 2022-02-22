package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ14888 {
	
	static int maxAns;
	static int minAns;
	static int N;
	static int[] numbers;
	static int[] selected;
	
	private static void permutaion(int cnt, int[] operation) {
		if(cnt == N-1) {
			int prev = calculate(numbers[0], numbers[1], selected[0]);
			for(int i=1; i<N-1; i++) {
				prev = calculate(prev, numbers[i+1], selected[i]);
			}
			
			minAns = Math.min(minAns, prev);
			maxAns = Math.max(maxAns, prev);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(operation[i] > 0) {
				operation[i]--;
				selected[cnt] = i;
				permutaion(cnt+1, operation);
				operation[i]++;
			}
		}
	}
	
	private static int calculate(int left, int right, int oper) {
		if(oper == 0) return left + right;
		else if(oper == 1) return left - right;
		else if(oper == 2) return left * right;
		else {
			if(left < 0) {
				left *= -1;
				return (left / right) * -1;
			}
			else return left / right;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		numbers = new int [N]; // 수열
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] opration = new int [4];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			opration[i] = Integer.parseInt(st.nextToken());
		}
		
		maxAns = Integer.MIN_VALUE;
		minAns = Integer.MAX_VALUE;
		selected = new int [N-1];
		
		permutaion(0, opration);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(maxAns));
		bw.write("\n");
		bw.write(String.valueOf(minAns));
		bw.flush();
		bw.close();
	}

}
