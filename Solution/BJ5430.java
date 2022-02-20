package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BJ5430 {
	
	static StringBuilder sb;
	
	private static void print(boolean isReft, Deque<Integer> dq) {
		Deque<Integer> temp = dq;
		sb.append("[");
		
		if(temp.size() > 0) { // 0일 경우 null이 입력되기 때문에 없을 경우 [] 출력해야한다.
			
			if(isReft) {
				sb.append(temp.pollFirst());
				
				while(!temp.isEmpty()) {
					sb.append(",").append(temp.pollFirst());
				}
			}
			
			else {
				sb.append(temp.pollLast());
				
				while(!temp.isEmpty()) {
					sb.append(",").append(temp.pollLast());
				}
			}
		}
		
		sb.append("]");
	}
	
	private static void AC(char[] cmd, Deque<Integer> dq) {
		
		Deque<Integer> temp = dq;
		boolean isReft = true; // true 는 커서가 왼쪽을 바라 볼때, false 는 오른쪽
		for(char c : cmd) {
			
			if(c == 'R') { // R이 나올경우 현재 상황에서 반전
				isReft = !isReft;
				continue;
			}
			
			if(isReft) { // D가 나올경우 커서가 어디를 바라보는지 부터 분기필요
				
				if(temp.pollFirst() == null) { // deque.pollFirst() 호출 자체가 뽑는거기에 조건문 속에 넣어도 문자가 있다면 뽑힌다.
					sb.append("error");
					return;
				}
			}
			else {
				
				if(temp.pollLast() == null) { 
					sb.append("error");
					return;
				}
			}
			
		}
		print(isReft, temp);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			String command = br.readLine();
			int size = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), "[,]");
			ArrayDeque<Integer> deque = new ArrayDeque<>();
			
			for(int j=0; j<size; j++) {
				deque.offer(Integer.parseInt(st.nextToken()));
			}
			
			char[] cmd = command.toCharArray();
			
			sb = new StringBuilder();
			AC(cmd, deque);
			System.out.println(sb.toString());
		}
	}

}
