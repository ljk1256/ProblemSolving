package Solution;

public class BJ4673 {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		boolean[] checked = new boolean [10001];
		
		for(int i=1; i<10000; i++) {
			int temp = 0;
			char[] splits = String.valueOf(i).toCharArray();
			for(int j=0; j<splits.length; j++) {
				temp += (splits[j] - '0');
			}
			temp += i;
			if(temp <= 10000) checked[temp] = true;
		}
		
		for(int i=1; i<10001; i++) {
			if(!checked[i]) sb.append(i).append("\n");
		}
		
		sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
	}

}
