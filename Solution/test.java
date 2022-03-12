package Solution;

import java.util.*;

class test {

    static class Point {
        int x;
        int y;
        int dir;
        int marking;

        public Point(int x, int y, int dir, int marking) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.marking = marking;
        }
    }

    static int [][] clock = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int [][] unclock = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static boolean[][] visited;
    static boolean flag;

    public int[][] solution(int n, boolean clockwise) {
        int[][] answer = new int [n][n];
        visited = new boolean [n][n];
        flag = false;
        
        test st = new test();

        ArrayList<Point> list = st.makePoint(n, clockwise);

        while(!flag) {
            for(int i=0; i<list.size(); i++) {
                move(clockwise, list.get(i), answer);
            }
            
            /*for(int i=0; i<6; i++) {
        		for(int j=0; j<6; j++) {
        			System.out.print(answer[i][j] + " ");
        		}
        		System.out.println();
        	}
            System.out.println();*/
        }
        return answer;
    }
    
    public ArrayList<Point> makePoint(int n, boolean clockwise) {
        ArrayList<Point> pointList = new ArrayList<>();

        if(clockwise) {
            pointList.add(new Point(0, 0, 0, 1));
            pointList.add(new Point(0, n-1, 1, 1));
            pointList.add(new Point(n-1, n-1, 2, 1));
            pointList.add(new Point(n-1, 0, 3, 1));
        }
        else {
            pointList.add(new Point(0, 0, 0, 1));
            pointList.add(new Point(n-1, 0, 1, 1));
            pointList.add(new Point(n-1, n-1, 2, 1));
            pointList.add(new Point(0, n-1, 3, 1));
        }

        return pointList;
    }

    public void move(boolean clockwise, Point point, int[][] answer) {
        if(!visited[point.x][point.y]) {
            answer[point.x][point.y] = point.marking;
            visited[point.x][point.y] = true;

            if(clockwise) { // 시계방향
                int nx = point.x + clock[point.dir][0];
                int ny = point.y + clock[point.dir][1];

                if(visited[nx][ny]) { // 방향전환
                    point.dir = (point.dir + 1) % 4;
                    point.x = point.x + clock[point.dir][0];
                    point.y = point.y + clock[point.dir][1];
                    point.marking++;
                }
                else {
                    point.x = nx;
                    point.y = ny;
                    point.marking++;
                }
            }
            else { // 반시계
                int nx = point.x + unclock[point.dir][0];
                int ny = point.y + unclock[point.dir][1];

                if(visited[nx][ny]) { // 방향전환
                    point.dir = (point.dir + 1) % 4;
                    point.x = point.x + unclock[point.dir][0];
                    point.y = point.y + unclock[point.dir][1];
                    point.marking++;
                }
                else {
                    point.x = nx;
                    point.y = ny;
                    point.marking++;
                }
            }
            
        }

        else flag = true; // 중심점에 도착했다면 더 이상 진행하지 않는다
    }
    
    public static void main(String[] args) {
    	test test = new test();
    	
    	int[][] result = test.solution(6, false);
    	
    	for(int i=0; i<6; i++) {
    		for(int j=0; j<6; j++) {
    			System.out.print(result[i][j] + " ");
    		}
    		System.out.println();
    	}
    	
    }
}
