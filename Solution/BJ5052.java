package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ5052 {
	
	static class TrieNode {
		
		TrieNode[] child;
		boolean isLast;
		
		public TrieNode() {
			super();
			this.child = new TrieNode [10]; // 0 ~ 9 까지 번호
			this.isLast = false; // false 는 아직 뒤에 번호가 남아있다는 의미
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0; i<T; i++) {
			int n = Integer.parseInt(br.readLine());
			String[] input = new String [n];
			String answer = "YES";
			
			for(int j=0; j<n; j++) {
				input[j] = br.readLine();
			}
			
			Arrays.sort(input); // 번호가 짧은 길이부터 넣으면 일관성 체크가능
			TrieNode root = new TrieNode();
			TrieNode node;
			
			bp : for(int j=0; j<n; j++) {
				 String s = input[j];
				 node = root;
					
					for(int k=0; k<s.length(); k++) {
						int number = s.charAt(k) - '0';
						if(node.isLast && node.child[number] != null) { // 접두어가 같은 	번호가 존재
							answer = "NO";
							break bp;
						}
						
						if(node.child[number] == null) { // 해당 번호가 없다면
							node.child[number] = new TrieNode();
						}
						
						if(k == s.length() - 1) { // 모든 번호를 다 저장했을 경우 해당 깊이에 번호 끝임을 저장
							node.isLast = true;
						}
						else {
							node = node.child[number];
						}
					}
				}
			System.out.println(answer);
		}
		br.close(); // 객체다 사용했다면 닫는 습관
	}

}

/*class TrieNode {
	
	private Map<Character, TrieNode> childNodes = new HashMap<>();
	private boolean isLastChar;
	
	public Map<Character, TrieNode> getChildNodes() {
		return childNodes;
	}
	public void setChildNodes(Map<Character, TrieNode> childNodes) {
		this.childNodes = childNodes;
	}
	public boolean isLastChar() {
		return isLastChar;
	}
	public void setLastChar(boolean isLastChar) {
		this.isLastChar = isLastChar;
	}
	
}

class Trie {
	
	private TrieNode rootNode;

	public Trie() {
		super();
		rootNode = new TrieNode();
	}
	
	void insert(String word) {
		TrieNode node = this.rootNode;
		
		for(int i=0; i<word.length(); i++) {
			node = node.getChildNodes().computeIfAbsent(word.charAt(i), c -> new TrieNode()); 
		}
		node.setLastChar(true);
	}
	
	boolean contains(String word) {
		
		TrieNode node = this.rootNode;
		
		for(int i=0; i<word.length(); i++) {
			char c = word.charAt(i);
			node = node.getChildNodes().get(c);
			
			if(node == null) {
				return false;
			}
		}
		return node.isLastChar();
	}
	
}*/
