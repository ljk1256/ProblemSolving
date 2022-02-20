package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BJ14725 {
	
	static class Trie {
		Node root;
		
		public Trie() {
			this.root = new Node("", new HashMap<>());
		}
		
		public void add(String[] targetWord, Node tempRoot, int cnt) {
			if(cnt == targetWord.length) return;
			
			if(tempRoot.childNodes.containsKey(targetWord[cnt])) {
				Node nextNode = tempRoot.childNodes.get(targetWord[cnt]);
				add(targetWord, nextNode, cnt+1);
			}
			else {
				tempRoot.childNodes.put(targetWord[cnt], new Node(targetWord[cnt], new HashMap<>()));
				Node nextNode = tempRoot.childNodes.get(targetWord[cnt]);
				add(targetWord, nextNode, cnt+1);
			}
		}
		
		public void searchAll(Node tempRoot, int cnt) {
			if(cnt != 0) {
				for(int i=1; i<cnt; i++) {
					builder.append("--");
				}
				builder.append(tempRoot.food).append("\n");
			}
			
			Collection<Node> nodeList = tempRoot.childNodes.values();
			Iterator<Node> iterator = nodeList.iterator();
			ArrayList<Node> list = new ArrayList<>();
			
			while(iterator.hasNext()) list.add(iterator.next());
			Collections.sort(list, new Comparator<Node>() {

				@Override
				public int compare(Node o1, Node o2) {
					return o1.compareTo(o2);
				}
				
			});
			
			for(int i=0; i<list.size(); i++) {
				Node nextNode = list.get(i);
				searchAll(nextNode, cnt+1);
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		
		String food;
		HashMap<String, Node> childNodes;
		
		public Node(String food, HashMap<String, Node> childNodes) {
			super();
			this.food = food;
			this.childNodes = childNodes;
		}

		@Override
		public int compareTo(Node o) {
			return this.food.compareTo(o.food);
		}
		
	}
	
	static StringBuilder builder;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		String[][] inputs = new String [N][];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			
			inputs[i] = new String [k];
			for(int j=0; j<k; j++) {
				inputs[i][j] = st.nextToken();
			}
		}
		
		Trie trie = new Trie();
		
		for(int i=0; i<N; i++) {
			trie.add(inputs[i], trie.root, 0);
		}
		
		builder = new StringBuilder();
		trie.searchAll(trie.root, 0);
		
		System.out.println(builder.toString());
	}

}
