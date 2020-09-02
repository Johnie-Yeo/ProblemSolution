package BOJ.Samsung.Test;

import Test.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Gear14891 {
	public static void main(String[] args) {
//		new Main().solve();
		new Gear14891().test();
	}

	private void test() {
		OldTest test = new OldTest();

		String input;
		int expect;

		input = "10101111\n" +
				"01111101\n" +
				"11001110\n" +
				"00000010\n" +
				"2\n" +
				"3 -1\n" +
				"1 1";
		expect = 7;
		testCase(test, input, expect);

		input = "11111111\n" +
				"11111111\n" +
				"11111111\n" +
				"11111111\n" +
				"3\n" +
				"1 1\n" +
				"2 1\n" +
				"3 1";
		expect = 15;
		testCase(test, input, expect);

		input = "10001011\n" +
				"10000011\n" +
				"01011011\n" +
				"00111101\n" +
				"5\n" +
				"1 1\n" +
				"2 1\n" +
				"3 1\n" +
				"4 1\n" +
				"1 -1";
		expect = 6;
		testCase(test, input, expect);

		input = "10010011\n" +
				"01010011\n" +
				"11100011\n" +
				"01010101\n" +
				"8\n" +
				"1 1\n" +
				"2 1\n" +
				"3 1\n" +
				"4 1\n" +
				"1 -1\n" +
				"2 -1\n" +
				"3 -1\n" +
				"4 -1";
		expect = 5;
		testCase(test, input, expect);
	}

	private void testCase(OldTest test, String input, int expect) {
		String head = input.substring(0, 35);
		String tail = input.substring(36);
		String[] gears = head.split("\n");
		String[] parsed = tail.split("\n", 2);
		int N = Integer.parseInt(parsed[0]);
		int[][] cycles = InputParser.parseStringTo2DIntArray(parsed[1]);
		int result = getResult(gears, N, cycles);
		test.test(result, expect).printResult();
	}

	private void solve(){
		Scanner kb = new Scanner(System.in);

		String[] gears = new String[4];
		for(int i = 0 ; i < 4; i++){
			gears[i] = kb.next();
		}
		int N = kb.nextInt();
		int[][] cycles = new int[N][2];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < 2; j++){
				cycles[i][j] = kb.nextInt();
			}
		}
		kb.close();
		int result = getResult(gears, N, cycles);
		System.out.println(result);
	}

	private final int CLOCK_WISE 		 = 1;
	private final int COUNTER_CLOCK_WISE = -1;
	private class Gear{
		int[] poles;
		public Gear(String gear){
			poles = this.charArrToIntArr(gear);
		}
		public int getTop(){
			return this.poles[0];
		}
		public int getRight(){
			return this.poles[2];
		}
		public int getLeft(){
			return this.poles[6];
		}
		private int[] charArrToIntArr(String str){
			int[] result = Arrays.stream(str.split(""))
								.mapToInt(e -> Integer.parseInt(e))
								.toArray();
			return result;
		}
		public void cycle(int dir){
			switch (dir){
				case CLOCK_WISE:
					this.clockCycle();
					return;
				case COUNTER_CLOCK_WISE:
					this.counterClockCycle();
					return;
			}
		}

		private void counterClockCycle() {
			int tmp = this.poles[0];
			for(int i = 0; i < 7; i++){
				this.poles[i] = this.poles[i+1];
			}
			this.poles[7] = tmp;
		}

		private void clockCycle() {
			int tmp = this.poles[7];
			for(int i = 6; i >= 0; i--){
				this.poles[i+1] = this.poles[i];
			}
			this.poles[0] = tmp;
		}
	}
	private int getResult(String[] gears, int n, int[][] cycles) {
		ArrayList<Gear> gearList = getGearList(gears);

		for(int i = 0; i < n; i++){
			int[] dir = getDirections(cycles[i], gearList);
			gearList = cycle(dir, gearList);
		}

		int result = getScore(gearList);
		return result;
	}

	private int[] getDirections(int[] cycle, ArrayList<Gear> gearList) {
		int start = cycle[0] - 1;
		int dir = cycle[1];
		int size = gearList.size();
		int[] directions = new int[size];
		directions[start] = dir;

		int prevIndex = start;
		int leftIndex = start - 1;
		while(leftIndex >= 0){
			Gear prev = gearList.get(prevIndex);
			Gear left = gearList.get(leftIndex);
			if(prev.getLeft() == left.getRight()){
				directions[leftIndex] = 0;
				break;
			}else{
				directions[leftIndex] = directions[prevIndex] * -1;
			}
			prevIndex--;
			leftIndex--;
		}

		prevIndex = start;
		int rightIndex = start + 1;
		while(rightIndex < size){
			Gear prev = gearList.get(prevIndex);
			Gear right = gearList.get(rightIndex);
			if(prev.getRight() == right.getLeft()){
				directions[rightIndex] = 0;
				break;
			}else{
				directions[rightIndex] = directions[prevIndex] * -1;
			}
			prevIndex++;
			rightIndex++;
		}
		return directions;
	}

	private ArrayList<Gear> cycle(int[] directions, ArrayList<Gear> gearList) {
		int size = directions.length;

		for(int i = 0; i < size; i++){
			int dir = directions[i];
			gearList.get(i).cycle(dir);
		}
		return gearList;
	}

	private int getScore(ArrayList<Gear> gearList) {
		int index = 0;
		int result = 0;
		for(Gear gear : gearList){
			result += (pow2(index) * gear.getTop());
			index++;
		}
		return result;
	}

	private int pow2(int n){
		int result = (int) Math.pow(2, n);
		return result;
	}

	private ArrayList<Gear> getGearList(String[] gears) {
		ArrayList<Gear> list = new ArrayList<>();

		for(String gear : gears){
			Gear cur = new Gear(gear);
			list.add(cur);
		}

		return list;
	}
}

