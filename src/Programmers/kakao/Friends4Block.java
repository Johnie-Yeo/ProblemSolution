package Programmers.kakao;

import Test.Test;

import java.util.Arrays;

public class Friends4Block {
    public static void main(String[] args) {
        new Friends4Block().test();
    }

    private void test() {
        int m, n;
        String[] board;
        int result, expect;

        m = 4; n = 5;
        board = new String[]{"CCBDE", "AAADE", "AAABF", "CCBBF"};
        expect = 14;
        result = solution(m, n, board);
        Test.test(result, expect).printResult();

        m = 6; n = 6;
        board = new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        expect = 15;
        result = solution(m, n, board);
        Test.test(result, expect).printResult();
    }

    public int solution(int m, int n, String[] input_board) {
        char[][] board = Arrays.stream(input_board).map(str -> str.toCharArray()).toArray(char[][]::new);
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){

            }
        }
        return 0;
    }
}
