package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class BJ1893 {
	
	// 바보같이 hashmap에서 entry로 다시 value 로 key를 찾으려 하지말고
	// 처음부터 2개를 선언하면 된다!
	// Hashmap<Key, Value>, Hashmap<Value, Key> 로 두개를 선언해서 크로스 로 넣어주면 메모리를 많이 사용할 순 있어도 시간적으로 많은 이득을 볼 수 있다
	
	private static char[] makePattern(HashMap<Character, Integer> map, char[] origin, int push) {
		int originSize = origin.length;
		char[] new_pattern = new char[originSize];
		
		Set<Entry<Character, Integer>> entryset = map.entrySet();
		for(int i=0; i<originSize; i++) {
			int idx = (map.get(origin[i]) + push) % map.size();
			
			for(Entry<Character, Integer> entry : entryset) {
				if(entry.getValue().equals(idx)) {
					new_pattern[i] = entry.getKey();
					break;
				}
			}
		}
		
		return new_pattern;
	}
	
	private static void Solve(char[] order, char[] origin, char[] password, StringBuilder builder) {
		HashMap<Character, Integer> ordermap = new HashMap<>();
		for(int j=0; j<order.length; j++) {
			ordermap.put(order[j], j);
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		for(int pushcnt=0; pushcnt<order.length; pushcnt++) {
			char[] new_pattern = makePattern(ordermap, origin, pushcnt);
			
			if(KMP(password, new_pattern)) {
				list.add(pushcnt);
				continue;
			}
		}
		
		if(list.isEmpty()) builder.append("no solution").append("\n");
		else if(list.size() == 1) builder.append("unique: ").append(list.get(0)).append("\n");
		else {
			builder.append("ambiguous: ");
			for(int i=0; i<list.size(); i++) {
				builder.append(list.get(i)).append(" ");
			}
			builder.setLength(builder.length()-1);
			builder.append("\n");
		}
		
	}
	
	private static boolean KMP(char[] password, char[] pattern) { // 단 한번만 나올때만 true 리턴한다
		int passwordSize = password.length;
		int patternSize = pattern.length;
		int[] table = makeTable(pattern);
		int index = 0;
		boolean flag = false;
		
		for(int i=0; i<passwordSize; i++) {
			while(index > 0 && password[i] != pattern[index]) index = table[index-1];
			if(password[i] == pattern[index]) {
				if(index == patternSize-1) {
					if(!flag) {
						flag = true;
						index = table[index];
					}
					else return false;
				}
				else index++;
			}
		}
		
		return flag;
	}
	
	private static int[] makeTable(char[] pattern) {
		int patterSize = pattern.length;
		int[] table = new int [patterSize];
		int index = 0;
		
		for(int i=1; i<patterSize; i++) {
			while(index > 0 && pattern[i] != pattern[index]) index = table[index-1];
			if(pattern[i] == pattern[index]) table[i] = ++index;
		}
		
		return table;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			char[] order = br.readLine().toCharArray();
			char[] original = br.readLine().toCharArray();
			char[] password = br.readLine().toCharArray();
			
			Solve(order, original, password, sb);
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
