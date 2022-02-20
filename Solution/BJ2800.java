package Solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

public class BJ2800 {
	
	static class Pair {
		
		int start;
		int end;
		
		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
	}
	
	static String origin;
	static StringBuilder builder;
	static HashSet<String> ansSet;
	static ArrayList<Pair> pairList;
	static boolean[] selected;
	
	private static ArrayList<Pair> makePair(char[] word) {
		Stack<Integer> stack = new Stack<>();
		ArrayList<Pair> pairList = new ArrayList<>();
		for(int i=0; i<word.length; i++) { // 스택에 넣으면서 닫는 괄호 나올 시 pop 하면서 괄호 쌍 구성
			if(word[i] == '(') stack.push(i);
			else if(word[i] == ')') pairList.add(new Pair(stack.pop(), i));
		}
		return pairList;
	}
	
	private static void makeAns(int cnt, int maxCnt, char[] input) {
		if(cnt == maxCnt) {
			boolean[] isVaild = new boolean [input.length];
			for(int i=0; i<selected.length; i++) {
				if(selected[i]) {
					Pair temp = pairList.get(i);
					isVaild[temp.start] = true;
					isVaild[temp.end] = true;
				}
			}
			
			builder.setLength(0); // 초기화
			for(int i=0; i<input.length; i++) {
				if(isVaild[i]) continue;
				builder.append(input[i]);
			}
			
			if(!builder.toString().equals(origin)) ansSet.add(builder.toString()); // 원본 문자열이 아닌경우만 정답 후보군
			return;
		}
		
		selected[cnt] = true;  // 함수를 여러번 호출 하는것도 메모리 초과를 유발 할 수 있다 >> 굳이 반복문이 필요없는 경우 이런식으로 호출
		makeAns(cnt+1, maxCnt, input);
			
		selected[cnt] = false;
		makeAns(cnt+1, maxCnt, input);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		builder = new StringBuilder();
		origin = br.readLine();
		char[] input = origin.toCharArray();
		
		ansSet = new HashSet<>();
		pairList = makePair(input);
		
		selected = new boolean [pairList.size()];
		makeAns(0, pairList.size(), input);
		
		Iterator<String> iter = ansSet.iterator();
		ArrayList<String> ansList = new ArrayList<>();
		while(iter.hasNext()) ansList.add(iter.next());
		
		Collections.sort(ansList);
		for(int i=0; i<ansList.size(); i++) {
			bw.write(ansList.get(i));
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
	}

}
