package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ23291 { // 나중에 달팽이 풀이로도 풀어보자
	
	static int N;
	static int K;
	static int max, min;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[] result; // 정리가 된 어항의 물고기
	static int[][] stack1; // 물고기 수 조절 전
	static int[][] stack2; // 물고기 수 조절 후
	static Queue<Integer>[] arr;
	
	private static boolean isStop() {
		
		max = Integer.MIN_VALUE; 
		min = Integer.MAX_VALUE;
		
		for(int i=1; i<N+1; i++) {
			if(result[i] > max) max = result[i];
			if(result[i] < min) min = result[i];
		}
		
		if(max - min <= K) return true;
		else return false;
		
	}
	
	private static void first() { // 첫번째 공중부양 수행
		
		for(int i=1; i<N+1; i++) { // 제일 적은 어항에
			if(result[i] == min) result[i]++; // 한마리 추가
		}
		
		for(int i=0; i<N+1; i++) { // 큐 초기화
			arr[i] = new LinkedList<>();
		}
		
		for(int i=1; i<N+1; i++) { // 큐에 초기값 넣기
			arr[i].offer(result[i]);
		}
		
		int idx = 0, width = 0, height = 0;
		while(N - idx >= arr[idx].size()) { // 오른쪽 아래 바닥에 어항이 있을때까지 수행, 오답시 조건 다시확인 필요
			
			if(!arr[1].isEmpty()) { // 맨 처음 어항에 물고기가 있다면
				arr[2].offer(arr[1].poll()); // 1칸 올려놓는다
				idx = 2;
				width = 1;
				height = 2;
			}
			
			else {
				for(int i=idx; i>=idx-width+1; i--) {
					int temp = idx+1; // 어항을 쌓아야 하는 다음위치
					while(!arr[i].isEmpty()) {
						arr[temp].offer(arr[i].poll());
						temp++;
					}
				}
					
				width = height; // 다음 가로 길이는 이전 세로높이랑 같다
				idx += height; // 다음 시작점으로 갱신
				height = arr[idx].size(); // 높이 갱신
			}
		}
		
		stack1 = new int [N+1][N+1];
		for(int i=1; i<N+1; i++) {
			if(!arr[i].isEmpty()) { // 현재 쌓인 어항 모습 그대로 배열에 담는다
				int point = N;
				while(!arr[i].isEmpty()) {
					stack1[point--][i] = arr[i].poll();
				}	
			}
		}
		
		stack2 = new int [N+1][N+1];
		for(int i=0; i<N+1; i++) {
			for(int j=0; j<N+1; j++) {
				if(stack1[i][j] != 0) { // 물고기를 발견 했다면
					int x = i;
					int y = j;
					
					for(int k=0; k<4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						
						if(nx < 1 || nx > N || ny < 1 || ny > N || stack1[nx][ny] == 0) continue;
						
						int sub = Math.abs(stack1[x][y] - stack1[nx][ny]);
						if(sub >= 5) { // 두 어항의 차이가 5이상이면
							sub = sub / 5; // 5로 나눈다
							if(stack1[x][y] > stack1[nx][ny]) { // 현재 어항의 물고기가 더 많다면
								stack2[x][y] -= sub; // 물고기를 옮겨준다
								stack2[nx][ny] += sub;
							}
						}
					}
				}
			}
		}
		
		for(int i=0; i<N+1; i++) {
			for(int j=0; j<N+1; j++) {
				stack1[i][j] += stack2[i][j]; // 이동시킨 물고기 동시에 갱신
			}
		}
		
		Queue<Integer> line_q = new LinkedList<>(); // 다시 1열로 만들기 위해 큐에 저장
		for(int c=0; c<N+1; c++) {
			for(int r=N; r>0; r--) {
				if(stack1[r][c] != 0) { // 물고기가 있는 어항을 발견했다면  오답시 만약 어항의 물고기가 0이 될 수 있다면 고려해야함
					line_q.offer(stack1[r][c]);
				}
			}
		}
		
		int i = 1;
		while(!line_q.isEmpty()) { // 다시 1열로 만들어준다
			result[i++] = line_q.poll();
		}
		
	}
	
	private static void second() { // 두번째 공중부양 수행 N/2 180도 회전
		Queue<Integer> up_q = new LinkedList<>(); // N/2 회전 시키는 배열
		Queue<Integer> down_q = new LinkedList<>(); // 회전 안시키는 배열
		int[] first_turn = new int [N];
		for(int i=0; i<N; i++) { // 첫번째 회전을 위한 배열 복사
			first_turn[i] = result[i+1];
		}
		
		int idx = (N/2) - 1;
		for(int i=idx; i>=0; i--) { // 180도 회전해서 위로 올라갈 어항
			up_q.offer(first_turn[i]);
		}
		
		for(int i=idx+1; i<N; i++) { // 바닥에 깔릴 어항
			down_q.offer(first_turn[i]);
		}
		
		int[][] second_turn = new int [2][idx+1];
		for(int i=0; i<idx+1; i++) {
			second_turn[0][i] = up_q.poll();
			second_turn[1][i] = down_q.poll();
		}
		
		idx = (N/4) - 1; // 두번째 회전을 위해 다시 반으로 나눈다
		for(int i=0; i<2; i++) {
			for(int j=idx; j>=0; j--) {
				up_q.offer(second_turn[i][j]);
			}
		}
		
		for(int i=0; i<2; i++) {
			for(int j=idx+1; j<second_turn[0].length; j++) {
				down_q.offer(second_turn[i][j]);
			}
		}
		
		int[][] result_turn = new int [4][idx+1];
		for(int i=1; i>=0; i--) { // 회전해서 위에 올리는 어항
			for(int j=0; j<idx+1; j++) {
				result_turn[i][j] = up_q.poll();
			}
		}
		
		for(int i=2; i<4; i++) { // 그대로 밑에 깔리는 어항
			for(int j=0; j<idx+1; j++) {
				result_turn[i][j] = down_q.poll();
			}
		}
		
		int[][] stack3 = new int [4][idx+1];
		for(int i=0; i<4; i++) { // 물고기 조절 작업 수행
			for(int j=0; j<idx+1; j++) {
				int x = i;
				int y = j;
					
				for(int k=0; k<4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
						
					if(nx < 0 || nx > 3 || ny < 0 || ny > idx) continue;
						
					int sub = Math.abs(result_turn[x][y] - result_turn[nx][ny]);
					if(sub >= 5) { // 두 어항의 차이가 5이상이면
						sub = sub / 5; // 5로 나눈다
						if(result_turn[x][y] > result_turn[nx][ny]) { // 현재 어항의 물고기가 더 많다면
							stack3[x][y] -= sub; // 물고기를 옮겨준다
							stack3[nx][ny] += sub;
						}
					}
				}
			}
		}
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<idx+1; j++) {
				result_turn[i][j] += stack3[i][j]; // 이동시킨 물고기 동시에 갱신
			}
		}
		
		Queue<Integer> line_q = new LinkedList<>(); // 마지막 1열로 정렬하기위해 큐에 담는다
		for(int c=0; c<idx+1; c++) {
			for(int r=3; r>=0; r--) {
				line_q.offer(result_turn[r][c]);
			}
		}
		
		for(int i=1; i<N+1; i++) { // 1열로 정렬
			result[i] = line_q.poll();
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 어항크기
		K = Integer.parseInt(st.nextToken()); // 최대 - 최소
		
		st = new StringTokenizer(br.readLine());
		
		result = new int [N+1];
		arr = new LinkedList [N+1]; // 인덱스 일치
		for(int i=1; i<N+1; i++) {
			result[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		while(!isStop()) { // K 이하가 될때까지 수행
			first();
			second();
			answer++;
		}
		
		System.out.println(answer);
	}

}
