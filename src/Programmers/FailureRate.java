package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Test.Test;

public class FailureRate {
	public static void main(String[] args) {
		new FailureRate().solve();
	}
	public void solve() {
		Test test = new Test();
		int N = 5;
		int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
		int[] expect = {3,4,2,1,5};
		int[] result = solution(N, stages);
		test.test(result, expect);
		
		N = 4;
		stages = new int[] {4,4,4,4,4};
		expect = new int[] {4,1,2,3};
		result = solution(N, stages);
		test.test(result, expect);		
	}
	public class StageFailureRate implements Comparable<StageFailureRate>{
		int stage;
		double rate;
		public StageFailureRate(int stage, double rate) {
			this.stage = stage;
			this.rate = rate;
		}
		@Override
		public int compareTo(StageFailureRate o) {
			if(this.rate > o.rate) {
				return -1;
			}else if(this.rate < o.rate) {
				return 1;
			}else {
				return this.stage - o.stage;
			}
		}
	}
	public int[] solution(int N, int[] stages) {
        ArrayList<StageFailureRate> failureRate = new ArrayList<>();
      
        Arrays.sort(stages);
        
        int numberOfPlayer = stages.length;
        
        int currentStage = 1;
        int numberOfPeopleStuckInStage = 0;
        int numberOfRestPeople = numberOfPlayer;
        int index = 0;
        
        while(true) {
        	if(currentStage > N) {
        		break;
        	}
        	if(index >= numberOfPlayer ||stages[index] > currentStage) {
        		double rate = (double)numberOfPeopleStuckInStage / numberOfRestPeople;
        		failureRate.add(new StageFailureRate(currentStage, rate));
        		currentStage++;
        		numberOfRestPeople -= numberOfPeopleStuckInStage;
        		numberOfPeopleStuckInStage = 0;
        	}else if(stages[index] == currentStage) {
        		numberOfPeopleStuckInStage++;
        		index++;
        	}
        }
          
        Collections.sort(failureRate);
        
        int[] result = new int[N];
        for(int i = 0; i < N; i++) {
        	result[i] = failureRate.get(i).stage;
        }
		return result;
    }
}
