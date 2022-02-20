package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ17478 {
	
	static StringBuilder builder;
	static int N;
	
	private static void recursive(int cnt) {
		String prefix = "";
		for(int i=0; i<cnt; i++) {
			prefix += "____";
		}
		
		if(cnt == N) {			
			builder.append(prefix).append("\"재귀함수가 뭔가요?\"").append("\n");
			builder.append(prefix).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"").append("\n");
			builder.append(prefix).append("라고 답변하였지.").append("\n");
			return;
		}
		
		builder.append(prefix).append("\"재귀함수가 뭔가요?\"").append("\n");
		builder.append(prefix).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.").append("\n");
		builder.append(prefix).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.").append("\n");
		builder.append(prefix).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"").append("\n");
		
		recursive(cnt+1);
		
		builder.append(prefix).append("라고 답변하였지.").append("\n");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		builder = new StringBuilder();
		
		builder.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		
		recursive(0);
		System.out.println(builder.toString());
	}

}
