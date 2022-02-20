package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16235 {
	
	static int N;
	static int[][] nutri;
	static int[][] ground;
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	static Queue<Tree> q;
	static List<Tree> list;
	
	static class Tree implements Comparable<Tree>{
		
		int x;
		int y;
		int age;
		
		public Tree(int x, int y, int age) {
			super();
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
		
	}
	
	private static void year() {
		
		//봄
		int spring = list.size();
		q = new LinkedList<>();
		
		Collections.sort(list); // 어린 순서 대로 뽑기위해 정렬
		for(int i=0; i<list.size(); i++) {
			q.offer(list.get(i));
		}
		list.clear(); // 복사 했으니 초기화
		
		Queue<Tree> dead = new LinkedList<>(); // 봄에 죽는 나무 저장
		while(spring > 0) {
			Tree temp = q.poll();
			
			if(ground[temp.x][temp.y] < temp.age) { // 땅에 나무 나이보다 영양분이 없다면 죽는다
				dead.offer(new Tree(temp.x, temp.y, temp.age));
				spring--;
				continue;
			}
			
			ground[temp.x][temp.y] -= temp.age; // 나이 만큼 영양분을 먹는다
			list.add(new Tree(temp.x, temp.y, temp.age+1));
			spring--;
		}
		
		//여름
		int summer = dead.size();
		while(summer > 0) {
			Tree temp = dead.poll();
			
			ground[temp.x][temp.y] += (temp.age / 2); // 나이 절반 만큼 양분으로 변함
			summer--;
		}
		
		//가을
		int autumn = list.size();
		for(int i=0; i<list.size(); i++) {
			q.offer(list.get(i));
		}
		list.clear(); // 복사 했으니 초기화
		
		while(autumn > 0) {
			Tree temp = q.poll();
			
			if(temp.age % 5 == 0) { // 나무 나이가 5의 배수라면
				for(int i=0; i<8; i++) {
					int nx = temp.x + dx[i];
					int ny = temp.y + dy[i];
					
					if(nx < 1 || nx >= N+1 || ny < 1 || ny >= N+1) continue; // 범위를 벗어난경우
					
					list.add(new Tree(nx, ny, 1)); // 나무 번식
				}
				list.add(new Tree(temp.x, temp.y, temp.age)); // 번식 후 원래 나무 넣는다
			}
			else {
				list.add(new Tree(temp.x, temp.y, temp.age)); // 번식할 나이가 아니라면 다시 넣는다
			}
			autumn--;
		}
		
		//겨울
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				ground[i][j] += nutri[i][j]; // 기계가 양분 공급
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		nutri = new int [N+1][N+1];
		ground = new int [N+1][N+1];
		for(int i=1; i<N+1; i++) { // 영양분 입력
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				nutri[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<N+1; i++) { // 나무 심는 땅 양분초기화
			for(int j=1; j<N+1; j++) {
				ground[i][j] = 5;
			}
		}
		
		list = new ArrayList<Tree>();
		for(int i=0; i<M; i++) { // 초기 나무 심기
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
				
			list.add(new Tree(x, y, age));
		}
		
		while(K > 0) {
			year();
			K--;
		}
		
		System.out.println(list.size());
	}

}
