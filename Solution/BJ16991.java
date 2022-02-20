package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ16991 {
	
	static class Coordinates {
		
		int x;
		int y;
		
		public Coordinates(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static ArrayList<Coordinates> corList;
	static int N;
	static int MAX;
	static double[][] bitMask;
	
	private static double TSP(int start, int visited) { // 1 > 2 > 3 > 4 > 1 가 최소라면  3 > 4 > 1 > 2 > 3 도 최소  (참)
		if(visited == MAX) return makeDistance(corList.get(0), corList.get(start)); // 모든곳을 방문한 경우 >> 마지막 도착지에서 출발지로 거리만 계산해서 반환해준다
		
		if(bitMask[start][visited] != Double.MAX_VALUE) return bitMask[start][visited]; // 이미 최소거리가 계산됐다면
		
		for(int i=0; i<N; i++) { // 비트의 최대값 N-1 보다 작아야한다
			int next = 1 << i;
			
			if((visited & next) == 0) { // 방문 하지 않은 곳이라면
				next = visited | next; // 다음 방문지로 갱신
				bitMask[start][visited] = Math.min(bitMask[start][visited], TSP(i, next) + makeDistance(corList.get(start), corList.get(i)));
			}
		}
		return bitMask[start][visited];
	}
	
	private static double makeDistance(Coordinates o1, Coordinates o2) { // 두 점 사이 거리 공식
		int x = Math.abs(o2.x - o1.x);
		int y = Math.abs(o2.y - o1.y);
		
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		MAX = (1 << N)-1;
		
		corList = new ArrayList<>(N);
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			corList.add(new Coordinates(x, y)); // 좌표 리스트 입력
		}
		
		bitMask = new double [N][MAX];
		for(int i=0; i<N; i++) { // 거리 초기화
			Arrays.fill(bitMask[i], Double.MAX_VALUE);
		}
		
		System.out.println(TSP(0, 1)); // (0, 방문한 곳은 1로 비트마스킹 한 값) 을 의미
	}

}
