package BOJ;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Scanner;

public class Puzzle {
	public final int[] available = {-3,1,3,-1};
	public static void main(String[] args) {
		new Puzzle().solve();
	}
	public void solve() {
		Scanner kb = new Scanner(System.in);
		
		StringBuilder puzzle = new StringBuilder();
		int empty = 0;
		
		for(int i = 0; i < 9; i++) {
			int value = kb.nextInt();
			if(value == 0) {
				empty = i;
			}
			puzzle.append(value);
		}
		
		kb.close();
		int result = getMinimumMove(puzzle, empty);
		System.out.println(result);
	}
	public class State{
		StringBuilder puzzle;
		int emptyIndex, count;
		public State(StringBuilder puzzle, int empty, int count){
			this.puzzle = new StringBuilder();
			this.puzzle.append(puzzle.toString());
			this.emptyIndex= empty;
			this.count = count;
		}
	}
	public int getMinimumMove(StringBuilder puzzle, int emptyIndex) {
		HashSet<String> puzzleList = new HashSet<>();
		puzzleList.add(puzzle.toString());
		ArrayDeque<State> queue = new ArrayDeque<>();
		queue.add(new State(puzzle, emptyIndex, 0));
		String complete = "123456780";
		
		if(puzzle.toString().contentEquals(complete)) {
			return 0;
		}
		
		while(!queue.isEmpty()) {
			State cur = queue.pop();
			
			emptyIndex = cur.emptyIndex;
			int count = cur.count;
			StringBuilder curPuzzle = cur.puzzle;
			
			for(int i = 0; i < 4; i++) {
				int next = emptyIndex + available[i];
				if(next < 0 || next > 8 ||
					(i == 1 && next == 3) || (i == 1 && next == 6) ||
					(i == 3 && next == 2) || (i == 3 && next == 5)) {
					continue;
				}
				
				StringBuilder clone = new StringBuilder();
				clone.append(curPuzzle);
				char movingValue = clone.charAt(next);
				
				clone.setCharAt(emptyIndex, movingValue);
				clone.setCharAt(next, '0');
				
				if(clone.toString().equals(complete)) {
					return count+1;
				}
				if(!puzzleList.contains(clone.toString())) {
					queue.add(new State(clone, next, count+1));
					puzzleList.add(clone.toString());
				}
			}
		}
		return -1;
	}
}
