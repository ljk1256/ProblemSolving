package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17471 {
	
	static boolean[] set;
	static Area[] arr;
	static int answer = Integer.MAX_VALUE;
	static int N;
	
	private static void solve(int cnt) {
		if(cnt == N) {
			ArrayList<Integer> first = new ArrayList<>();
			ArrayList<Integer> second = new ArrayList<>();
			
			for(int i=1; i<=N; i++) {
				if(set[i]) {
					first.add(i);
				}
				else {
					second.add(i);
				}
			}
			if(first.size() + second.size() != N || first.size() == 0 || second.size() == 0) { // 집합이 3이상이거나 공집합이 존재하는 경우 유효하지 않음
				return;
			}
			
			if(isValid(first, 1) && isValid(second, 2)) { // 선거구가 제대로 나누어 졌다면
				int first_sum = 0, second_sum = 0;
				for(int i=0; i<first.size(); i++) {
					first_sum += arr[first.get(i)].human;
				}
				for(int i=0; i<second.size(); i++) {
					second_sum += arr[second.get(i)].human;
				}
				
				answer = Math.min(answer, Math.abs(first_sum - second_sum));
			}
			return;
		}
		
		set[cnt] = true; // true 는 first
		solve(cnt+1);
		
		set[cnt] = false; // false 는 second
		solve(cnt+1);
	}
	
	private static boolean isValid(ArrayList<Integer> selected, int flag) { // flag 1은 first, 2는 second
		Queue<Integer> q = new LinkedList<>();
		q.offer(selected.get(0)); // 아무거나 넣어도 어차피 같은 지역이라면 연결되기에 상관없음
		boolean[] visited = new boolean [N+1];
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			visited[temp] = true;	
			for(int i=0; i<arr[temp].list.size(); i++) {
				int iscan = arr[temp].list.get(i);
					
				if(flag == 1 && !visited[iscan] && set[iscan] || flag == 2 && !visited[iscan] && !set[iscan]) { // flag 1은 first, 2는 second
					visited[iscan] = true;
					q.offer(iscan);
				}
			}
		}
			
		for(int i=0; i<selected.size(); i++) {
			if(!visited[selected.get(i)]) { // 뽑은 부분집합의 조합 중 인접하지 않는게 존재한다면 유효하지않음
				return false;
			}
		}
		return true; 
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new Area [N+1];
		set = new boolean [N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) { // 인구수 저장
			int human = Integer.parseInt(st.nextToken());
			arr[i] = new Area(human, new ArrayList<Integer>()); // 구역 객체 생성
		}
		
		for(int i=1; i<=N; i++) { // 인접한 구역 저장
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int j=0; j<size; j++) { // 무향 그래프 이므로 반대 구역에도 저장
				int link = Integer.parseInt(st.nextToken());
				arr[i].list.add(link);
				arr[link].list.add(i);
			}
		}
		
		solve(1);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

}

class Area {
	
	int human; // 인구수
	ArrayList<Integer> list; // 인접한 구역 저장
	
	public Area(int human, ArrayList<Integer> list) {
		super();
		this.human = human;
		this.list = list;
	}
	
}
