package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ17825 {
	
	static class Horse {
		
		Node head;

		public Horse(Node head) {
			super();
			this.head = head;
		}
		
	}
	
	static class Node {
		
		int score;
		int idx; // 현재 말 위치를 위함
		boolean isBlue; // 파란색 판일경우 true
		Node[] nexts; // 다음 노드 정보
		
		public Node(int score, int idx, boolean isBlue, Node[] nexts) {
			super();
			this.score = score;
			this.idx = idx;
			this.isBlue = isBlue;
			this.nexts = nexts;
		}
		
	}
	
	static ArrayList<Integer> diceList;
	static int maxAns;
	static Horse[] horses;
	static boolean[] visited;
	static Node start;
	
	private static void playGame(int cnt, int[] selected) {
		
		if(cnt == 10) {
			int totalScore = 0;
			Arrays.fill(visited, false);
			
			for(int i=0; i<4; i++) {
				horses[i] = new Horse(start);
			}
			
			for(int i=0; i<10; i++) {
				int horseNum = selected[i];
				int diceNum = diceList.get(i);
				
				Horse selectedHorse = horses[horseNum];
				
				if(selectedHorse.head.nexts == null) continue;
				else {
					visited[selectedHorse.head.idx] = false;
					
					if(selectedHorse.head.isBlue) selectedHorse.head = selectedHorse.head.nexts[1]; // 시작점이 파란색 화살표라면
					else selectedHorse.head = selectedHorse.head.nexts[0];
					
					for(int j=0; j<diceNum-1; j++) { // 남은 칸 수 이동
						if(selectedHorse.head.nexts == null) break; // 도착을 했다면 그만간다 널 포인트 방지
						selectedHorse.head = selectedHorse.head.nexts[0];
					}
					
					if(visited[selectedHorse.head.idx]) return; // 이미 말이 있다면 조합은 무효
					
					totalScore += selectedHorse.head.score;
					visited[selectedHorse.head.idx] = true;
				}
			}
			
			maxAns = Math.max(maxAns, totalScore);
			return;
		}
		
		for(int i=0; i<4; i++) {
			selected[cnt] = i;
			playGame(cnt+1, selected);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		diceList = new ArrayList<>(); 
		
		for(int i=0; i<10; i++) { // 주사위 입력
			diceList.add(Integer.parseInt(st.nextToken()));
		}
		
		maxAns = 0;
		
		// 출발점
		start = new Node(0, 0, false, new Node[2]);
		
		// 빨간색 화살표 방향
		Node N1 = new Node(2, 1, false, new Node[2]);
		Node N2 = new Node(4, 2, false, new Node[2]);
		Node N3 = new Node(6, 3, false, new Node[2]);
		Node N4 = new Node(8, 4, false, new Node[2]);
		Node N5 = new Node(10, 5, true, new Node[2]); // 10
		Node N6 = new Node(12, 6, false, new Node[2]);
		Node N7 = new Node(14, 7, false, new Node[2]);
		Node N8 = new Node(16, 8, false, new Node[2]);
		Node N9 = new Node(18, 9, false, new Node[2]);
		Node N10 = new Node(20, 10, true, new Node[2]); // 20
		Node N11 = new Node(22, 11, false, new Node[2]);
		Node N12 = new Node(24, 12, false, new Node[2]);
		Node N13 = new Node(26, 13, false, new Node[2]);
		Node N14 = new Node(28, 14, false, new Node[2]);
		Node N15 = new Node(30, 15, true, new Node[2]); // 30
		Node N16 = new Node(32, 16, false, new Node[2]);
		Node N17 = new Node(34, 17, false, new Node[2]);
		Node N18 = new Node(36, 18, false, new Node[2]);
		Node N19 = new Node(38, 19, false, new Node[2]);
		Node N20 = new Node(40, 20, false, new Node[2]);
		Node N21 = new Node(25, 21, false, new Node[2]); // 가운데 25
		
		// 10에서 파란색 화살표 방향
		Node N22 = new Node(13, 22, false, new Node[2]);
		Node N23 = new Node(16, 23, false, new Node[2]);
		Node N24 = new Node(19, 24, false, new Node[2]);
		
		// 20에서 파란색 화살표 방향
		Node N25 = new Node(22, 25, false, new Node[2]);
		Node N26 = new Node(24, 26, false, new Node[2]);
		
		// 30에서 파란색 화살표 방향
		Node N27 = new Node(28, 27, false, new Node[2]);
		Node N28 = new Node(27, 28, false, new Node[2]);
		Node N29 = new Node(26, 29, false, new Node[2]);
		
		// 가운데 25에서 연결된 방향
		Node N30 = new Node(30, 30, false, new Node[2]);
		Node N31 = new Node(35, 31, false, new Node[2]);
		
		// 도착점
		Node end = new Node(0, 0, false, null);
		
		start.nexts[0] = N1;
		
		N1.nexts[0] = N2;
		N2.nexts[0] = N3;
		N3.nexts[0] = N4;
		N4.nexts[0] = N5;
		
		//
		N5.nexts[0] = N6;
		N5.nexts[1] = N22;
		
		//
		N6.nexts[0] = N7;
		N7.nexts[0] = N8;
		N8.nexts[0] = N9;
		N9.nexts[0] = N10;
		
		//
		N10.nexts[0] = N11;
		N10.nexts[1] = N25;
		
		//
		N11.nexts[0] = N12;
		N12.nexts[0] = N13;
		N13.nexts[0] = N14;
		N14.nexts[0] = N15;
		
		//
		N15.nexts[0] = N16;
		N15.nexts[1] = N27;
		
		//
		N16.nexts[0] = N17;
		N17.nexts[0] = N18;
		N18.nexts[0] = N19;
		N19.nexts[0] = N20;
		N20.nexts[0] = end; // 도착점
		
		// 파란색 10에서 가는 노드
		N22.nexts[0] = N23;
		N23.nexts[0] = N24;
		N24.nexts[0] = N21;
		
		// 파란색 20에서 가는 노드
		N25.nexts[0] = N26;
		N26.nexts[0] = N21;
		
		// 파란색 30에서 가는 노드
		N27.nexts[0] = N28;
		N28.nexts[0] = N29;
		N29.nexts[0] = N21;
		
		// 가운데 25에서 가는 노드
		N21.nexts[0] = N30;
		N30.nexts[0] = N31;
		N31.nexts[0] = N20;

		horses = new Horse[4]; // 시작점에 위치한 말들
		visited = new boolean [32];
		
		playGame(0, new int [10]);
		
		System.out.println(maxAns);
	}

}
