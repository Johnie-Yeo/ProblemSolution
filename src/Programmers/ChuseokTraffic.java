package Programmers;
import java.util.ArrayList;
import java.util.StringTokenizer;
//1TC ����
//�������Լ� �߸��ƴµ� ���ĵ� �ȵ�
public class ChuseokTraffic {
	public static void main(String[] args) {
		new ChuseokTraffic().solve();
	}
	public void solve() {
		String[] lines = {
				"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"
		};
		
		int result = solution(lines);
		System.out.println(result);
	}
	public int solution(String[] lines) {
		ArrayList<myDate> trafficList = new ArrayList<>();
		int max= 0;
		
		for(String line : lines) {
			trafficList.add(new myDate(line));
		}
		
		//add startList
		ArrayList<myDate> startList = findStartPoint(trafficList);		
		
		for(myDate start : startList) {
			int amount = amountOfTrafficInRange(start, trafficList);
			if(amount > max) {
				max = amount;
			}
		}
		
		return max;
	}
	private ArrayList<myDate> findStartPoint(ArrayList<myDate> trafficList) {
		ArrayList<myDate> startList = new ArrayList<>();
		
		for(myDate tmp : trafficList) {
			startList.add(tmp.clone());
			tmp.second -= 0.999;
			if(tmp.second < 0) {
				tmp.second += 60.0;
				tmp.minute--;
			}
			if(tmp.minute < 0) {
				tmp.minute += 60;
				tmp.hour--;
			}
			if(tmp.hour < 0) {
				continue;
			}
			startList.add(tmp);
		}
		
		return startList;
	}
	public class myDate{
		int hour;
		int minute;
		double second;
		double period;
		myDate finish;
		
		public myDate(String cur) {
			StringTokenizer token = new StringTokenizer(cur, " ");
			token.nextToken();//useless date
			String time = token.nextToken();
			String period = token.nextToken();
			
			token = new StringTokenizer(time, ":");
			this.hour = Integer.parseInt(token.nextToken());
			this.minute = Integer.parseInt(token.nextToken());
			this.second = Double.parseDouble(token.nextToken());
			
			this.period = Double.parseDouble(period.substring(0, period.length()-1));
			myDate start = this.remove();
			
			this.finish = this.clone();
			this.hour = start.hour;
			this.minute = start.minute;
			this.second = start.second;
		}
		public myDate(int hour, int minute, double second) {
			this.hour = hour;
			this.minute = minute;
			this.second = second;
		}
		private myDate remove() {
			int hour = this.hour;
			int min = this.minute;
			double sec = this.second - this.period;
			sec *= 1000;
			sec += 1;
			sec /= 1000;
			sec = (float) sec;
			
			if(sec < 0) {
				sec += 60.0;
				min--;
			}
			if(min < 0) {
				min += 60;
				hour--;
			}
			if(hour < 0){
                hour = 0;
                min = 0;
                sec = 0;
            }
			
			return new myDate(hour, min, sec);//������
		}
		public myDate clone() {
			return new myDate(this.hour, this.minute, this.second);
		}
		public int comareTo(myDate o) {
			if(this.hour > o.hour) {
				return 1;
			}else if(this.hour < o.hour) {
				return -1;
			}else {
				if(this.minute > o.minute) {
					return 1;
				}else if(this.minute < o.minute) {
					return -1;
				}else {
					if(this.second > o.second) {
						return 1;
					}else if(this.second < o.second) {
						return -1;
					}else {
						return 0;
					}
				}
			}
		}
	}
	public int amountOfTrafficInRange(myDate startPoint, ArrayList<myDate> trafficList) {
		int amount = 0;
		
		for(myDate data : trafficList) {
			if(data.comareTo(startPoint) <= 0 && data.finish.comareTo(startPoint) >= 0) {
				amount++;
			}
		}
		
		return amount;
	}
}
