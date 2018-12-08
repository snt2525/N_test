import java.util.*;
public class MDST_Machine {
	static final int ARRMAX = 10000000;
	String[] code = {"D", "M", "S", "T"};
	int[] visit;
	int minCalCnt;
	String minCalCode;
	
	MDST_Machine(){
		this.visit = new int[ARRMAX];
		Arrays.fill(visit, Integer.MAX_VALUE);
		this.minCalCnt = Integer.MAX_VALUE;
		this.minCalCode = "";
	}
	
	public void DFS(String calCode, int cnt, int num) {         //연산코드, 연산 횟수, 숫자
		if(minCalCnt <= cnt) 						            //이미 찾은 최소 연산수 보다 같거나 크면 return; 재연산 방지
			return; 
		
		if(num == 1) { 										    //자연 수 1이 만들어졌을 때, 연산 코드와 연산 횟수 저장			
			minCalCnt = cnt;
			minCalCode = calCode;
			return;
		}
		
		for(int i = 0; i < 4; i++) { 						    //사전순으로 찾기 위핸 DMST 연산 순서로
			int tmpNum = num;			
			if(i == 0) { 									    //D : 나누기 2 
				tmpNum /= 2;
			}else if(i == 1) {  							    //M : 곱하기 2
				tmpNum *= 2;
			}else if(i == 2) { 								    //S : 오름차순
				tmpNum = sort(tmpNum, 0);
			}else if(i == 3) { 								    //T : 내림차순
				tmpNum = sort(tmpNum, 1);
			}
			if(tmpNum > 9999999 || visit[tmpNum] < cnt + 1) continue;
			visit[tmpNum] = cnt + 1;
			DFS(calCode + code[i], cnt + 1, tmpNum);	
		}
	}
	
	public int sort(int tmpNum, int order) {
		if(tmpNum < 10)                                         // 한자리 수 일 때 연산 하지 않는다.
			return tmpNum;
		
		int result = 0;
		LinkedList<Integer> tmp = new LinkedList<Integer>();
		
		while(tmpNum > 0) {                                     //자리 수 분리하기
			tmp.add(tmpNum % 10);
			tmpNum = tmpNum/10;
		}
		
		Collections.sort(tmp);
		int size = tmp.size();		
		if(order == 0) {                                        //오름 차순
			for(int i = 0 ;i < size; i++) 
				result += tmp.removeLast() * (int)Math.pow(10, i);
				
		}else if(order == 1) {                                  //내림차순
			for(int i = 0 ;i < size; i++) 
				result += tmp.removeFirst() * (int)Math.pow(10, i);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		MDST_Machine m = new MDST_Machine();
		
		m.DFS("", 0, n);
		
		if(m.minCalCode == "")
			System.out.println("NO ANSWER");
		else
			System.out.println(m.minCalCode);
	}
}
