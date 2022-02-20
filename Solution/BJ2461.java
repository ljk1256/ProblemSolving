package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2461 { // 모든 학급을 1차원배열에 놓고 경렬 한뒤 학급별 대표 선수 만큼 슬라이딩 윈도우(윈도우 안에 모든 학급이 있는지 확인 후, 있다면 거기서 최대 - 최소 값 갱신) 하는 방법도 있다
	
	static class Player {
		
		int clsNum; // 학급 번호
		int index; // 해당 학급에서 위치하는 인덱스
		int stat; // 능력치
		
		public Player(int clsNum, int index, int stat) {
			super();
			this.clsNum = clsNum;
			this.index = index;
			this.stat = stat;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] allPlayers = new int [N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				allPlayers[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) { // 학급별 능력이 오름차순 정렬
			Arrays.sort(allPlayers[i]);
		}
		
		int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
		PriorityQueue<Player> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.stat, o2.stat));
		
		for(int i=0; i<N; i++) { // 최초 각 학급별 0번째에 있는 최소 능력치 큐에 저장
			if(minVal > allPlayers[i][0]) minVal = allPlayers[i][0];
			if(maxVal < allPlayers[i][0]) maxVal = allPlayers[i][0];
			pq.offer(new Player(i, 0, allPlayers[i][0]));
		}
		
		int minSub = Integer.MAX_VALUE; // 능력치 최소 저장 변수
		
		while(true) { // 시간 복잡도      O(N * M * logN)
			Player temp = pq.poll();
			int clsNum = temp.clsNum;
			int index = temp.index;
			int stat = temp.stat;
			
			minSub = Math.min(minSub, maxVal - stat);
			
			if(index < allPlayers[clsNum].length-1) {
				if(allPlayers[clsNum][index+1] > maxVal) maxVal = allPlayers[clsNum][index+1]; // 현재 큐에 존재하는 최소 능력치 학급에서 다음 순번으로 능력치가 낮은 선수 큐에 저장
				pq.offer(new Player(clsNum, index+1, allPlayers[clsNum][index+1]));
			}
			
			if(index+1 == allPlayers[clsNum].length) break; // 제일 작은 능력치가 그 학급의 끝번호라면 나머지 학급의 능력치는 이거보다 크기에 최소는 모두 탐색 완료
		}
		
		System.out.println(minSub);
	}

}
