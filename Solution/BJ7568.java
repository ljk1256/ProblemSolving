package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ7568 {
	
	static ArrayList<Human> list;
	
	static class Human {
		
		int weight;
		int height;
		int rank;
		
		public Human(int weight, int height, int rank) {
			super();
			this.weight = weight;
			this.height = height;
			this.rank = rank;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList<>(N);
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			list.add(new Human(weight, height, 0));
		}
		
		for(int i=0; i<list.size(); i++) {
			Human select = list.get(i);
			int rank = 1;
			for(int j=0; j<list.size(); j++) {
				if(i == j) continue;
				Human temp = list.get(j); // 자기보다 큰 애들만 등수 매긴다
				if(select.height < temp.height && select.weight < temp.weight) rank++;
			}
			select.rank = rank;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			sb.append(list.get(i).rank).append(" ");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
