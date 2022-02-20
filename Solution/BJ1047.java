package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1047 {
	
	static class Tree {
		
		int x;
		int y;
		int fenceCnt;
		int dis;
		int number;
		
		public Tree(int x, int y, int fenceCnt, int dis, int number) {
			super();
			this.x = x;
			this.y = y;
			this.fenceCnt = fenceCnt;
			this.dis = dis;
			this.number = number;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Tree> list = new ArrayList<>();
		int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int fencecnt = Integer.parseInt(st.nextToken());
			
			list.add(new Tree(x, y, fencecnt, 0, i));
			
			if(x < minX) minX = x;
			if(y < minY) minY = y;
			if(x > maxX) maxX = x;
			if(y > maxY) maxY = y;
		}
		
		int midX = (maxX - minX) / 2;
		int midY = (maxY - minY) / 2;
		
		PriorityQueue<Tree> pq = new PriorityQueue<>(new Comparator<Tree>() {

			@Override
			public int compare(Tree o1, Tree o2) {
				if(o1.fenceCnt == o2.fenceCnt) return o2.dis - o1.dis;
				return o2.fenceCnt - o1.fenceCnt;
			}
			
		});
		
		for(int i=0; i<list.size(); i++) {
			Tree temp = list.get(i);
			temp.dis = Math.abs(midX - temp.x) + Math.abs(midY - temp.y);
			
			pq.offer(new Tree(temp.x, temp.y, temp.fenceCnt, temp.dis, temp.number));
		}
		
		int answer = 0;
		int fenceCnt = 0;
		boolean[] selected = new boolean [N];
		
		while(!pq.isEmpty()) {
			Tree temp = pq.poll();
			fenceCnt += temp.fenceCnt;
			answer++;
			selected[temp.number] = true;
			
			/*for(int i=list.size()-1; i>= 0; i--) {
				Tree removeTree = list.get(i);
				if(removeTree.x == temp.x && removeTree.y == temp.y) {
					selected[temp.number] = true;
					break;
				}
			}*/
			
			minX = Integer.MAX_VALUE;
			minY = Integer.MAX_VALUE;
			maxX = Integer.MIN_VALUE;
			maxY = Integer.MIN_VALUE;
			for(int i=0; i<list.size(); i++) {
				if(selected[i]) continue;
				Tree liveTree = list.get(i);
				
				if(liveTree.x < minX) minX = liveTree.x;
				if(liveTree.y < minY) minY = liveTree.y;
				if(liveTree.x > maxX) maxX = liveTree.x;
				if(liveTree.y > maxY) maxY = liveTree.y;
			}
			
			int needFence = (maxX - minX) * (maxY - minY);
			if(fenceCnt >= needFence) break;
		}
		
		System.out.println(answer);
	}

}
