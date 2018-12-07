import java.util.Scanner;

public class radius_search {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();               //전체 POI 개수
		double[][] poi = new double[n][2];  //POI의 좌표
		
		for(int i = 0;i < n; i++) {
			poi[i][0] = sc.nextDouble();
			poi[i][1] = sc.nextDouble();
		}
		
		int m = sc.nextInt();               //검색 횟수  
		for(int i = 0;i < m; i++) {
			int resultCnt = 0;	   			//일정 반경 안에 포함되는 POI의 총 개수
			double cX = sc.nextDouble();    //중심 좌표 x
			double cY = sc.nextDouble();    //중심 좌표 y
			double cR = sc.nextDouble();    //반경 r
			
			for(int j = 0;j < n;j++) {     
				if(Math.sqrt(Math.pow(cX - poi[j][0], 2) + Math.pow(cY - poi[j][1], 2)) <= cR)   //두 점 사이의 거리 계산
					resultCnt++;	
			}			
			
			System.out.println(resultCnt);
		}		
		
	}
}
