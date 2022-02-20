package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class BJ9202 {
	
	static class Node {
		
		boolean isLast;
		HashMap<Character, Node> childNodes;
		
		public Node(boolean isLast, HashMap<Character, Node> childNodes) {
			super();
			this.isLast = isLast;
			this.childNodes = childNodes;
		}
		
	}
	
	static class Trie {
		Node root;
		
		public Trie() {
			this.root = new Node(false, new HashMap<>());
		}
		
		public void insert(String word, Node tempRoot, int cnt) {
			if(cnt == word.length()) {
				tempRoot.isLast = true;
				return;
			}
			
			Node nextNode = tempRoot.childNodes.computeIfAbsent(word.charAt(cnt), key -> new Node(false, new HashMap<>()));
			insert(word, nextNode, cnt+1);
		}
		
		public boolean isContains(String word, Node tempRoot, int cnt) {
			if(cnt == word.length()) return tempRoot.isLast;
			if(tempRoot.childNodes.containsKey(word.charAt(cnt))) {
				Node nextNode = tempRoot.childNodes.get(word.charAt(cnt));
				return isContains(word, nextNode, cnt+1);
			}
			else return false;
		}
	}
	
	static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	static HashSet<String> ansSet;
	static StringBuilder sb;
	static Trie trie;
	static char[][] boards;
	static boolean[][] visited;
	
	private static int getScore(String word) { // 점수 반환
		int len = word.length();
		if(1<= len && len <= 2) return 0;
		else if(3<= len && len <= 4) return 1;
		else if(len == 5) return 2;
		else if(len == 6) return 3;
		else if(len == 7) return 5;
		else return 11;
	}
	
	private static void dfs(int x, int y, int cnt) {
		if(cnt > 8) return; // 문자 최대 길이는 8
		
		if(trie.isContains(sb.toString(), trie.root, 0)) ansSet.add(sb.toString()); // 정답을 구했다면 정답 Set에 추가
		
		for(int i=0; i<8; i++) { // 8방 탐색
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || visited[nx][ny]) continue; // 범위를 벗어나거나 이미 방문했던 곳이라면
			
			sb.append(boards[nx][ny]); // 문자열 하나 넣고
			visited[nx][ny] = true; // 방문 처리 하고 
			dfs(nx, ny, cnt+1); // 다음 탐색 진행
			sb.setLength(sb.length()-1);; // 백트래킹
			visited[nx][ny] = false; // 백트래킹
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		trie = new Trie();
		
		for(int i=0; i<N; i++) {
			trie.insert(br.readLine(), trie.root, 0);
		}
		
		br.readLine(); // 빈칸 없애기
		
		boards = new char [4][4];
		int b = Integer.parseInt(br.readLine()); // 보드 개수
		sb = new StringBuilder();
		
		ansSet = new HashSet<>();
		visited = new boolean [4][4];
		StringBuilder ansBuilder = new StringBuilder();
		
		for(int i=0; i<b; i++) {
			ansSet.clear(); // 정답 중복 체크를 위함, 매 게임마다 초기화
			
			for(int j=0; j<4; j++) { // 맵 입력
				boards[j] = br.readLine().toCharArray(); // 초기화 필요없이 덮어씌운다
			}
			
			for(int j=0; j<4; j++) { // 4X4 보드 완전 탐색
				for(int k=0; k<4; k++) {
					sb.append(boards[j][k]);
					visited[j][k] = true;
					dfs(j, k, 0);
					sb.setLength(sb.length()-1); // 넣었던 문자 빼주기(백트래킹)
					visited[j][k] = false; // 백트래킹
				}
			}
			
			int totalScore = 0, maxIdx = 0, maxlen = 0;
			Iterator<String> iter = ansSet.iterator(); // 중복 방지를 위해 Set에 정답 누적
			ArrayList<String> ansList = new ArrayList<>(); // 정렬을 위해 사용
			
			while(iter.hasNext()) ansList.add(iter.next());
			Collections.sort(ansList); // 사전순 정렬
			
			for(int j=0; j<ansList.size(); j++) {
				if(ansList.get(j).length() > maxlen) {
					maxlen = ansList.get(j).length();
					maxIdx = j;
				}
				
				totalScore += getScore(ansList.get(j));
			}
			
			ansBuilder.append(totalScore).append(" ").append(ansList.get(maxIdx)).append(" ").append(ansSet.size()).append("\n");
			br.readLine(); // 줄 바꿈
		}
		
		System.out.println(ansBuilder.toString());
	}

}
