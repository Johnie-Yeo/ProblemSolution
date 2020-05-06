package Programmers;

import Test.OldestTest;

public class PathToSchool{
    public static void main(String[] args) {
        new PathToSchool().test();
    }
    private void test(){
        OldestTest test = new OldestTest();

        int m = 4;
        int n = 3;
        int[][] puddles = {{2, 2}};
        int expect = 4;
        int result = solution(m, n, puddles);
        test.test(result, expect);

        m = 4;
        n = 3;
        puddles = new int[0][0];
        expect = 10;
        result = solution(m, n, puddles);
        test.test(result, expect);

        m = 3;
        n = 4;
        puddles = new int[][]{{2,1},{1,2}};
        expect = 0;
        result = solution(m, n, puddles);
        test.test(result, expect);

        m = 4;
        n = 3;
        puddles = new int[][]{{4,2},{3,3}};
        expect = 0;
        result = solution(m, n, puddles);
        test.test(result, expect);

        m = 5;
        n = 3;
        puddles = new int[][]{{2,1},{2,2}, {4,2}, {4,3}};
        expect = 0;
        result = solution(m, n, puddles);
        test.test(result, expect);
    }
    public final int PUDDLE = -1;
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = setMap(m, n, puddles);
        int MOD= 1000000007;
        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(map[i][j] != PUDDLE){
                    int left = map[i][j-1] == PUDDLE ? 0 : map[i][j-1];
                    int up = map[i-1][j] == PUDDLE ? 0 : map[i-1][j];
                    map[i][j] = (left + up) % MOD;
                }
            }
        }

        return map[n-1][m-1];
    }

    private int[][] setMap(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];

        for(int[] puddle : puddles){
            int x = puddle[0] - 1;
            int y = puddle[1] - 1;
            map[y][x] = PUDDLE;
        }

        for(int i = 0; i < n; i++){
            if(map[i][0] == PUDDLE){
                break;
            }
            map[i][0] = 1;
        }
        for(int i = 0; i < m; i++){
            if(map[0][i] == PUDDLE){
                break;
            }
            map[0][i] = 1;
        }

        return map;
    }
}