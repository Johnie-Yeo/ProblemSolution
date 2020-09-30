package Programmers;

import Test.Test;

public class GetBiggestSquare {
    public static void main(String[] args) {
        new GetBiggestSquare().test();
    }

    private void test() {
        int[][] board;;
        int expect, result;

        board = new int[][]{
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 1, 0}
        };
        expect = 9;
        result = solution(board);
        Test.test(result, expect).printResult();

        board = new int[][]{
                {0, 0, 1, 1},
                {1, 1, 1, 1}
        };
        expect = 4;
        result = solution(board);
        Test.test(result, expect).printResult();

        board = new int[][]{
                {1}
        };
        expect = 1;
        result = solution(board);
        Test.test(result, expect).printResult();
    }

    public int solution(int [][]board) {
        int n = board.length;
        int m = board[0].length;

        int max = board[0][0];

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(board[i][j] == 1) {
                    int min = getMin(board[i][j - 1], board[i-1][j], board[i-1][j-1]);
                    board[i][j] = min + 1;
                    max = Math.max(max, board[i][j]);
                }
            }
        }

        return max * max;
    }

    public int getMin(int a, int b, int c) {
        int min = Math.min(a, b);
        min = Math.min(min, c);

        return min;
    }


}
