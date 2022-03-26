package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ19238 {
	
	static class Passenger {
		
		int srcX;
		int srcY;
		int destX;
		int destY;
		
		public Passenger(int srcX, int srcY, int destX, int destY) {
			super();
			this.srcX = srcX;
			this.srcY = srcY;
			this.destX = destX;
			this.destY = destY;
		}
		
	}
	
	static class Taxi {
		
		int x;
		int y;
		int fuel;
		int cost;
		
		public Taxi(int x, int y, int fuel, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.fuel = fuel;
			this.cost = cost;
		}
		
	}
	
	static int mapSize;
	static int totalPassenger;
	static int remainFuel;
	static int[][] map;
	static Taxi taxi;
	static Passenger[][] passengersMap;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	private static Passenger searchPassenger() {
		Passenger next = null;
		
		if(passengersMap[taxi.x][taxi.y] != null) { // 바로 승객이 있다면
			Passenger passenger = passengersMap[taxi.x][taxi.y];
			next = new Passenger(passenger.srcX, passenger.srcY, passenger.destX, passenger.destY);
			passengersMap[taxi.x][taxi.y] = null;
		}
		
		else { // 아니라면 가장 가까운 승객을 찾아본다
			boolean[][] visited = new boolean [mapSize][mapSize];
			
			int minCost = Integer.MAX_VALUE;
			boolean isSearch = false;
			ArrayList<Passenger> passengerList = new ArrayList<>();
			
			Queue<Taxi> q = new LinkedList<>();
			q.offer(taxi);
			visited[taxi.x][taxi.y] = true;
			
			while(!q.isEmpty()) {
				Taxi temp = q.poll();
				int x = temp.x;
				int y = temp.y;
				
				if(passengersMap[x][y] != null) { // 승객을 찾았다면
					
					if(minCost == Integer.MAX_VALUE) { // 가장 가까운 첫 승객을 찾았다면
						minCost = temp.cost;
						isSearch = true;
						passengerList.add(new Passenger(passengersMap[x][y].srcX, passengersMap[x][y].srcY, passengersMap[x][y].destX, passengersMap[x][y].destY));
						passengersMap[x][y] = null;
					}
					else if(temp.cost <= minCost) { // 같은 거리에 있는 승객을 또 찾았다면
						passengerList.add(new Passenger(passengersMap[x][y].srcX, passengersMap[x][y].srcY, passengersMap[x][y].destX, passengersMap[x][y].destY));
						passengersMap[x][y] = null;
					}
					else break; // 가장 가까운 거리가 아닌 경우
				}
				
				for(int i=0; i<4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize || visited[nx][ny]) continue;
					
					if(map[nx][ny] == 0 && temp.fuel > 0) {
						q.offer(new Taxi(nx, ny, temp.fuel-1, temp.cost+1));
						visited[nx][ny] = true;
					}
				}
			}
			
			if(isSearch) { // 승객을 찾았다면
				Collections.sort(passengerList, new Comparator<Passenger>() {
					@Override
					public int compare(Passenger o1, Passenger o2) {
						if(o1.srcX == o2.srcX) return o1.srcY - o2.srcY;
						else return o1.srcX - o2.srcX;
					}
				});
				
				next = passengerList.get(0); // 제일 우선 순위 승객
				passengerList.remove(0); // 승객리스트에서 지움 
				
				taxi.x = next.srcX; // 택시는 승객 위치로 가서 승객을 태운다
				taxi.y = next.srcY;
				taxi.fuel -= minCost; // 문제 조건 제대로 읽는다 >> 숨어있는 조건도 생각하고 파악할 줄 알아야한다
					
				for(int i=0; i<passengerList.size(); i++) { // 남은 승객들은 다시 제자리에
					Passenger rollback = passengerList.get(i);
					passengersMap[rollback.srcX][rollback.srcY] = rollback;
				}
			}
		}
		
		return next;
	}
	
	private static void work() {
		boolean flag = true;
		
		while(totalPassenger > 0) {
			Passenger next = searchPassenger();
			
			if(next == null) { // 승객을 태우러 갈 수 없는경우
				flag = false;
				break;
			}
			
			boolean[][] visited = new boolean [mapSize][mapSize];
			Queue<Taxi> q = new LinkedList<>();
			
			q.offer(taxi);
			visited[taxi.x][taxi.y] = true;
			
			boolean isArrive = false;
			
			while(!q.isEmpty()) { // 마지막에 택시 정보 갱신
				Taxi temp = q.poll();
				int x = temp.x;
				int y = temp.y;
				
				if(x == next.destX && y == next.destY) { // 목적지까지 도착 했다면
					isArrive = true;
					temp.fuel += temp.cost*2; // 2배 연료 충전
					temp.cost = 0; // 거리 초기화
					taxi = temp; // 택시 정보 갱신
					break;
				}
				
				for(int i=0; i<4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if(nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize || visited[nx][ny]) continue;
					
					if(map[nx][ny] == 0 && temp.fuel > 0) {
						q.offer(new Taxi(nx, ny, temp.fuel-1, temp.cost+1));
						visited[nx][ny] = true;
					}
				}
			}
			
			if(isArrive) {
				totalPassenger--;
				continue;
			}
			else {
				flag = false;
				break;
			}
		}
		
		if(!flag) remainFuel = -1;
		else remainFuel = taxi.fuel;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		mapSize = Integer.parseInt(st.nextToken());
		totalPassenger = Integer.parseInt(st.nextToken());
		int firstFuel = Integer.parseInt(st.nextToken()); // 초기 연료 양
		
		map = new int [mapSize][mapSize];
		passengersMap = new Passenger[mapSize][mapSize];
		
		for(int i=0; i<mapSize; i++) { // 맵 입력
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		taxi = new Taxi(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, firstFuel, 0); // 초기 택시 입력
		
		for(int i=0; i<totalPassenger; i++) {
			st = new StringTokenizer(br.readLine());
			
			int srcX = Integer.parseInt(st.nextToken());
			int srcY = Integer.parseInt(st.nextToken());
			int destX = Integer.parseInt(st.nextToken());
			int destY = Integer.parseInt(st.nextToken());
			
			passengersMap[srcX-1][srcY-1] = new Passenger(srcX-1, srcY-1, destX-1, destY-1);
		}
		
		remainFuel = 0;
		work();
		
		System.out.println(remainFuel);
	}

}
