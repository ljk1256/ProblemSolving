package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class test3 {
	
	static class Ratio {
        int coin;
        int cost;
        double rate;

        public Ratio(int coin, int cost, double rate) {
            this.coin = coin;
            this.cost = cost;
            this.rate = rate;
        }
    }

    public int solution(int money, int[] costs) {
        int answer = 0;
        int remain = money;

        ArrayList<Ratio> list = new ArrayList<>();

        for(int i=0; i<6; i++) {
            int coin = 0;
            if(i == 0) coin = 1;
            else if(i == 1) coin = 5;
            else if(i == 2) coin = 10;
            else if(i == 3) coin = 50;
            else if(i == 4) coin = 100;
            else coin = 500;

            list.add(new Ratio(coin, costs[i], (double)costs[i]/coin));
        }

        Collections.sort(list, new Comparator<Ratio>() {
            @Override
            public int compare(Ratio o1, Ratio o2) {
                if(o1.rate > o2.rate) return 1;
                else if(o1.rate == o2.rate) {
                    return o2.coin - o1.coin;
                }
        		else return -1;
            }
        });

        for(int i=0; i<6; i++) {
            Ratio temp = list.get(i);

            if(remain >= temp.coin) {
                int quotient = remain/temp.coin;
                remain -= quotient*temp.coin;
                answer += quotient*temp.cost;
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        int[] costs = new int [6];
        
        costs[0] = 2;
        costs[1] = 2;
        costs[2] = 2;
        costs[3] = 2;
        costs[4] = 2;
        costs[5] = 2;
        
        test3 test3 = new test3();
        int result = test3.solution(1999, costs);
        
        System.out.println(result);
    }
}
