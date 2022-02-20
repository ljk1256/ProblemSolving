package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ14425 {
	
	static class Node {
		
		boolean isEnd;
		HashMap<Character, Node> childNodes;
		
		public Node(boolean isEnd, HashMap<Character, Node> childNodes) {
			super();
			this.isEnd = isEnd;
			this.childNodes = childNodes;
		}
		
	}
	
	static class Trie {
		Node root;
		
		public Trie() {
			this.root = new Node(false, new HashMap<>());
		}
		
		public void add(String targetWord, Node tempRoot, int cnt) {
			if(cnt == targetWord.length()) {
				tempRoot.isEnd = true;
				return;
			}
			
			if(tempRoot.childNodes.containsKey(targetWord.charAt(cnt))) {
				Node nextNode = tempRoot.childNodes.get(targetWord.charAt(cnt));
				add(targetWord, nextNode, cnt+1);
			}
			else {
				tempRoot.childNodes.put(targetWord.charAt(cnt), new Node(false, new HashMap<>()));
				Node nextNode = tempRoot.childNodes.get(targetWord.charAt(cnt));
				add(targetWord, nextNode, cnt+1);
			}
		}
		
		public boolean isContains(String targetWord, Node tempRoot, int cnt) {
			if(cnt == targetWord.length()) {
				return tempRoot.isEnd;
			}
			
			if(tempRoot.childNodes.containsKey(targetWord.charAt(cnt))) {
				Node nextNode = tempRoot.childNodes.get(targetWord.charAt(cnt));
				return isContains(targetWord, nextNode, cnt+1);
			}
			else return false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Trie trie = new Trie();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashSet<String> hashSet = new HashSet<>();
		for(int i=0; i<N; i++) {
			hashSet.add(br.readLine());
		}
		
		int answer = 0;
		for(int i=0; i<M; i++) {
			if(hashSet.contains(br.readLine())) answer++;
		}
		
		System.out.println(answer);
	}

}
