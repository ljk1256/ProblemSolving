package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1041 {
	
	// 데이터 선언만 long 형으로 하고 연산 숫자가 int 라면 계산 시점은 int로 계산 한 뒤 long형으로 들어간다 >> 즉, 오버플로우를 막을 수 없다
	// BigInteger 사용법 익히기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());
		
		if(N == 1) {
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			pq.offer(A);
			pq.offer(B);
			pq.offer(C);
			pq.offer(D);
			pq.offer(E);
			pq.offer(F);
			
			int answer = 0;
			for(int i=0; i<5; i++) answer += pq.poll();
			System.out.println(answer);
		}
		else {
			ArrayList<Integer> thirdPartList = new ArrayList<>();
			ArrayList<Integer> secondPartList = new ArrayList<>();
			ArrayList<Integer> firstPartList = new ArrayList<>();
			
			firstPartList.add(A);
			firstPartList.add(B);
			firstPartList.add(C);
			firstPartList.add(D);
			firstPartList.add(E);
			firstPartList.add(F);
			
			secondPartList.add(A+E);
			secondPartList.add(A+B);
			secondPartList.add(A+C);
			secondPartList.add(A+D);
			secondPartList.add(B+C);
			secondPartList.add(B+D);
			secondPartList.add(B+F);
			secondPartList.add(D+E);
			secondPartList.add(D+F);
			secondPartList.add(C+E);
			secondPartList.add(C+F);
			secondPartList.add(E+F);
			
			thirdPartList.add(A+E+C);
			thirdPartList.add(A+E+D);
			thirdPartList.add(A+B+C);
			thirdPartList.add(A+B+D);
			thirdPartList.add(F+E+C);
			thirdPartList.add(F+E+D);
			thirdPartList.add(F+B+C);
			thirdPartList.add(F+B+D);
			
			Collections.sort(firstPartList);
			Collections.sort(secondPartList);
			Collections.sort(thirdPartList);
			
			BigInteger area = BigInteger.valueOf(N);
			BigInteger totalArea = area.multiply(BigInteger.valueOf(N));
			totalArea = totalArea.multiply(BigInteger.valueOf(5));
			
			BigInteger firstPartArea = totalArea.subtract(BigInteger.valueOf(12));
			firstPartArea = firstPartArea.subtract(BigInteger.valueOf((2*(N-2)*4)));
			firstPartArea = firstPartArea.subtract(BigInteger.valueOf((2*(N-1)*4)));
			
			BigInteger answer = BigInteger.valueOf(0);
			
			answer = answer.add(BigInteger.valueOf(thirdPartList.get(0)*4));
			answer = answer.add(BigInteger.valueOf(secondPartList.get(0)*(N-2)*4));
			answer = answer.add(BigInteger.valueOf(secondPartList.get(0)*(N-1)*4));
			answer = answer.add(firstPartArea.multiply(BigInteger.valueOf(firstPartList.get(0))));
			
			System.out.println(answer.toString());
		}
	}

}
