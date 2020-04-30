package BOJ;

import Test.Test;

import java.util.ArrayDeque;
import java.util.Scanner;

public class MovingPipe1{
    private final int[] dirX = {0, 1, 1};
    private final int[] dirY = {1, 0, 1};
    private final int RIGHT = 0;
    private final int DOWN = 1;
    private final int DIAGONAL = 2;
    private final int WALL = 1;

    public static void main(String[] args) {
//        new MovingPipe1().solve();
        new MovingPipe1().test();
    }

    private void test() {
        Test<Integer> test = new Test<Integer>();

        int N;
        int[][] map;
        int result, expect;

        N = 3;
        map = new int[][]{
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        result = getNumberOfSolution(N, map);
        expect = 1;
        test.test(result, expect).printResult();

        N = 4;
        map = new int[][]{
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        result = getNumberOfSolution(N, map);
        expect = 3;
        test.test(result, expect).printResult();

        N = 5;
        map = new int[][]{
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        result = getNumberOfSolution(N, map);
        expect = 0;
        test.test(result, expect).printResult();

        N = 6;
        map = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        result = getNumberOfSolution(N, map);
        expect = 13;
        test.test(result, expect).printResult();
    }

    private void solve() {
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = kb.nextInt();
            }
        }

        kb.close();

        int result = getNumberOfSolution(N, map);
        System.out.println(result);
    }

    private int getNumberOfSolution(int n, int[][] map) {
        int[][][] pipe = initPipe(n);

        for(int i = 0; i < n; i++){
            for(int j = 1; j < n; j++){
                if(!isWall(i, j, map)){
                    putPipe(RIGHT, pipe, map, i, j, n);
                    putPipe(DOWN,pipe, map, i, j, n);
                    putDiagonalPipe(pipe, map, i, j, n);
                }
            }
        }

        int sum = getSum(pipe[n-1][n-1]);
        return sum;
    }

    private void putDiagonalPipe(int[][][] pipe, int[][] map, int i, int j, int n) {
        int x = i + dirX[RIGHT];
        int y = j + dirY[RIGHT];
        if(!isOutOfRange(x, y, n) && isWall(x, y, map)){
            return;
        }
        x = i + dirX[DOWN];
        y = j + dirY[DOWN];
        if(!isOutOfRange(x, y, n) && isWall(x, y, map)){
            return;
        }

        x = i + dirX[DIAGONAL];
        y = j + dirY[DIAGONAL];
        if(isOutOfRange(x, y, n) || isWall(x, y, map)){
            return;
        }
        pipe[x][y][DIAGONAL] = getSum(pipe[i][j]);
    }

    private void putPipe(int dir, int[][][] pipe, int[][] map, int i, int j, int n) {
        int x = i + dirX[dir];
        int y = j + dirY[dir];
        if(isOutOfRange(x, y, n) || isWall(x, y, map)){
            return;
        }
        pipe[x][y][dir] = pipe[i][j][dir] + pipe[i][j][DIAGONAL];
    }

    private boolean isWall(int x, int y, int[][] map) {
        return map[x][y] == WALL;
    }

    private boolean isOutOfRange(int x, int y, int n) {
        return (x < 0 || y < 0 || x >= n || y >= n);
    }


    private int getSum(int[] arr) {
        int sum = 0;
        for(int element : arr){
            sum += element;
        }
        return sum;
    }

    private int[][][] initPipe(int n){
        int[][][] pipe = new int[n][n][3];

        pipe[0][1][RIGHT] = 1;

        return pipe;
    }
}