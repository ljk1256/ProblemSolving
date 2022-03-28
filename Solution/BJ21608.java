package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ21608 {
	
	static class Candidate {
		
		int x;
		int y;
		int emptyCnt;
		int favoriteCnt;
		
		public Candidate(int x, int y, int emptyCnt, int favoriteCnt) {
			super();
			this.x = x;
			this.y = y;
			this.emptyCnt = emptyCnt;
			this.favoriteCnt = favoriteCnt;
		}
		
	}
	
	static class Student {
		
		int num;
		int favorite;
		
		public Student(int num, int favorite) {
			super();
			this.num = num;
			this.favorite = favorite;
		}
		
	}
	
	static class Table {
		
		int studentNum;
		HashSet<Integer> favoriteTable;
		
		public Table(int studentNum, HashSet<Integer> favoriteTable) {
			super();
			this.studentNum = studentNum;
			this.favoriteTable = favoriteTable;
		}
		
	}
	
	static int N;
	static Student[][] map;
	static ArrayList<Candidate> candidateList;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	private static void arrange(Table table) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				if(map[i][j] == null) { // 빈 자리 인 경우
					int empty = 0;
					int favorite = 0;
					int x = i;
					int y = j;
					
					for(int k=0; k<4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						
						if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
						
						if(map[nx][ny] == null) empty++;
						else {
							int isFavorite = map[nx][ny].num;
							if(table.favoriteTable.contains(isFavorite)) favorite++; // 인접한 학생의 번호가 좋아하는 학생이라면
						}
					}
					
					candidateList.add(new Candidate(i, j, empty, favorite)); // 배정 받을 수 있는 후보군 등록
				}
			}
		}
		
		Collections.sort(candidateList, new Comparator<Candidate>() {
			@Override
			public int compare(Candidate o1, Candidate o2) {
				if(o1.favoriteCnt != o2.favoriteCnt) return o2.favoriteCnt - o1.favoriteCnt; // 좋아하는 학생이 많은 순으로
				else {
					if(o1.emptyCnt != o2.emptyCnt) return o2.emptyCnt - o1.emptyCnt; // 빈 공간이 많은 순으로
					else {
						if(o1.x != o2.x) return o1.x - o2.x; // 행 번호가 앞서는 순으로
						else return o1.y - o2.y; // 열 번호가 앞서는 순으로
					}
				}
			}
		});
		
		Candidate selected = candidateList.get(0); // 제일 우선 순위로 뽑는다
		
		map[selected.x][selected.y] = new Student(table.studentNum, selected.favoriteCnt);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 교실의 크기
		
		map = new Student [N][N]; // 자리 배정
		ArrayList<Table> tableList = new ArrayList<>();
		
		for(int i=0; i<N*N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int studentNum = Integer.parseInt(st.nextToken()); // 학생 번호
			HashSet<Integer> favoriteTable = new HashSet<>();
			
			for(int j=0; j<4; j++) { // 좋아하는 학생의 번호
				favoriteTable.add(Integer.parseInt(st.nextToken()));
			}
			
			tableList.add(new Table(studentNum, favoriteTable));
		}
		
		candidateList = new ArrayList<>();
		
		for(int i=0; i<tableList.size(); i++) { // 정해진 순번대로 학생 자리 배정
			Table tempStudent = tableList.get(i);
			candidateList.clear();
			arrange(tempStudent);
		}
		
		int answer = 0;
		
		for(int i=0; i<N; i++) { // 만족도 조사
			for(int j=0; j<N; j++) {
				int favoriteCnt = 0;
				Table searchTable = null;
				
				for(int k=0; k<tableList.size(); k++) {
					if(map[i][j].num == tableList.get(k).studentNum) {
						searchTable = tableList.get(k);
						break;
					}
				}
				
				int x = i;
				int y = j;
				
				for(int k=0; k<4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					
					if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					
					if(searchTable.favoriteTable.contains(map[nx][ny].num)) favoriteCnt++;
				}
				
				if(favoriteCnt == 0) answer += 0;
				else if(favoriteCnt == 1) answer += 1;
				else if(favoriteCnt == 2) answer += 10;
				else if(favoriteCnt == 3) answer += 100;
				else answer += 1000;
			}
		}
		
		System.out.println(answer);
	}

}
