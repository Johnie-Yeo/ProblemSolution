package BOJ;

import Test.OldTest;

import java.util.Arrays;
import java.util.Scanner;

public class MovingPipe1{
    public static void main(String[] args) {
//        new Main().solve();
        new MovingPipe1().test();
    }

    private void test() {
        OldTest test = new OldTest();

        int N;
        int[][] map;
        int result, expect;
        String input;

        N = 3;
        input = "0 0 0\n" +
                "0 0 0\n" +
                "0 0 0";
        map = parseInt2DArray(input);
        result = getNumberOfCombinationToConnectPipeToEnd(N, map);
        expect = 1;
        test.test(result, expect).printResult();

        N = 4;
        input = "0 0 0 0\n" +
                "0 0 0 0\n" +
                "0 0 0 0\n" +
                "0 0 0 0";
        map = parseInt2DArray(input);
        result = getNumberOfCombinationToConnectPipeToEnd(N, map);
        expect = 3;
        test.test(result, expect).printResult();

        N = 5;
        input = "0 0 1 0 0\n" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0\n" +
                "0 0 0 0 0";
        map = parseInt2DArray(input);
        result = getNumberOfCombinationToConnectPipeToEnd(N, map);
        expect = 0;
        test.test(result, expect).printResult();

        N = 6;
        input = "0 0 0 0 0 0\n" +
                "0 1 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0";
        map = parseInt2DArray(input);
        result = getNumberOfCombinationToConnectPipeToEnd(N, map);
        expect = 13;
        test.test(result, expect).printResult();
    }

    private void solve(){
        Scanner kb = new Scanner(System.in);

        int N = Integer.parseInt(kb.nextLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++){
            sb.append(kb.nextLine());
            sb.append("\n");
        }
        kb.close();
        int[][] map = parseInt2DArray(sb.toString());
        int result = getNumberOfCombinationToConnectPipeToEnd(N, map);
        System.out.println(result);
    }

    private int[][] parseInt2DArray(String input){
        int[][] result = Arrays.stream(input.split("\n"))
                            .map(e -> parseIntArray(e))
                            .toArray(int[][]::new);
        return result;
    }
    private int[] parseIntArray(String input){
        input = input.trim();
        int[] result = Arrays.stream(input.split(" "))
                            .mapToInt(e -> Integer.parseInt(e)).toArray();
        return result;
    }

    private final int HORIZONTAL = 0;
    private final int VERTICAL = 1;
    private final int SLASH = 2;
    private final int[] dirX = {0, -1, -1};
    private final int[] dirY = {-1, 0, -1};
    private final int EMPTY = 0;
    private final int WALL = 1;

    private int getNumberOfCombinationToConnectPipeToEnd(int n, int[][] map) {
        int [][][] dp = new int[n][n][3];
        int x = 0, y = 1;
        dp[x][y][HORIZONTAL] = 1;

        for(int i = 0; i < n; i++){
            for(int j = 2; j < n; j++){
                for(int d = 0; d < 3; d++){
                    if(d == SLASH){
                        if(isInfeasibleToSlash(map, i, j, n)){
                            continue;
                        }
                    }
                    if(map[i][j] == EMPTY){
                        dp[i][j][d] = getNumberOfPipe(dp, i, j, d, n);
                    }
                }
            }
        }

        return sum(dp[n-1][n-1]);
    }

    private boolean isInfeasibleToSlash(int[][] map, int i, int j, int n) {
        int x = i-1;
        int y = j;
        if(isOutOfRange(x, y, n) || map[x][y] == WALL){
            return true;
        }
        x = i;
        y = j-1;
        if(isOutOfRange(x, y, n) || map[x][y] == WALL){
            return true;
        }
        return false;
    }

    private int getNumberOfPipe(int[][][] map, int i, int j, int dir, int n) {
        int x = i + dirX[dir];
        int y = j + dirY[dir];
        if(isOutOfRange(x, y, n)){
            return 0;
        }
        switch(dir){
            case HORIZONTAL:
                return map[x][y][HORIZONTAL] + map[x][y][SLASH];
            case VERTICAL:
                return map[x][y][VERTICAL] + map[x][y][SLASH];
            case SLASH:
                return map[x][y][HORIZONTAL] + map[x][y][VERTICAL] + map[x][y][SLASH];
            default:
                return -1;
        }
    }

    private int sum(int[] arr){
        int sum = 0;
        for(int elem : arr){
            sum += elem;
        }
        return sum;
    }

    private boolean isOutOfRange(int x, int y, int n) {
        return (x < 0 || y < 0 || x >= n || y >= n);
    }
}