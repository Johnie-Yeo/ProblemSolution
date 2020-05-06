package BOJ;

import Test.OldTest;

import java.util.Scanner;

public class LadderManufacturing{
    private class Point {
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Point next(int col, int row){
            if(this == null){
                return null;
            }
            Point next = new Point(this.x, this.y+1);

            if(next.y > col - 2){
                next.x++;
                next.y = -1;
                return next.next(col, row);
            }else if(next.x >= row){
                return null;
            }
            return next;
        }
    }
    public static void main(String[] args) {
//        new LadderManufacturing().solve();
        new LadderManufacturing().test();
    }
    public void test(){
        OldTest<Integer> test = new OldTest<Integer>();
        int N, M, H;
        int[][] map;
        int result, expect;
        int[][] points;

        N = 2; M = 0; H = 3;
        map = new int[H][N];
        result = getMinimumExtraHorizon(N, H, map);
        expect = 0;
        test.test(result, expect).printResult();

        N = 4; M = 6; H = 7;
        map = new int[H][N];
        points = new int[][]{
                {1, 1},
                {3, 3},
                {4, 2},
                {5, 3},
                {7, 1},
                {7, 3}
        };
        for(int []point : points){
            int x = point[0]-1;
            int y = point[1]-1;
            map[x][y] = 1;
        }
        result = getMinimumExtraHorizon(N, H, map);
        expect = 2;
        test.test(result, expect).printResult();

        N = 2; M = 1; H = 3;
        map = new int[H][N];
        points = new int[][]{
                {1, 1}
        };
        for(int []point : points){
            int x = point[0]-1;
            int y = point[1]-1;
            map[x][y] = 1;
        }
        result = getMinimumExtraHorizon(N, H, map);
        expect = 1;
        test.test(result, expect).printResult();

        N = 5; M = 5; H = 6;
        map = new int[H][N];
        points = new int[][]{
                {1, 1},
                {3, 2},
                {2, 3},
                {5, 1},
                {5, 4}
        };
        for(int []point : points){
            int x = point[0]-1;
            int y = point[1]-1;
            map[x][y] = 1;
        }
        result = getMinimumExtraHorizon(N, H, map);
        expect = 3;
        test.test(result, expect).printResult();

        N = 6; M = 5; H = 6;
        map = new int[H][N];
        points = new int[][]{
                {1, 1},
                {3, 2},
                {1, 3},
                {2, 5},
                {5, 5}
        };
        for(int []point : points){
            int x = point[0]-1;
            int y = point[1]-1;
            map[x][y] = 1;
        }
        result = getMinimumExtraHorizon(N, H, map);
        expect = 3;
        test.test(result, expect).printResult();

        N = 5; M = 8; H = 6;
        map = new int[H][N];
        points = new int[][]{
                {1, 1},
                {2, 2},
                {3, 3},
                {4, 4},
                {3, 1},
                {4, 2},
                {5, 3},
                {6, 4}
        };
        for(int []point : points){
            int x = point[0]-1;
            int y = point[1]-1;
            map[x][y] = 1;
        }
        result = getMinimumExtraHorizon(N, H, map);
        expect = -1;
        test.test(result, expect).printResult();

        N = 5; M = 12; H = 6;
        map = new int[H][N];
        points = new int[][]{
                {1, 1},
                {1, 3},
                {2, 2},
                {2, 4},
                {3, 1},
                {3, 3},
                {4, 2},
                {4, 4},
                {5, 1},
                {5, 3},
                {6, 2},
                {6, 4}
        };
        for(int []point : points){
            int x = point[0]-1;
            int y = point[1]-1;
            map[x][y] = 1;
        }
        result = getMinimumExtraHorizon(N, H, map);
        expect = -1;
        test.test(result, expect).printResult();

        N = 5; M = 6; H = 6;
        map = new int[H][N];
        points = new int[][]{
                {1, 1},
                {3, 1},
                {5, 2},
                {4, 3},
                {2, 3},
                {1, 4}
        };
        for(int []point : points){
            int x = point[0]-1;
            int y = point[1]-1;
            map[x][y] = 1;
        }
        result = getMinimumExtraHorizon(N, H, map);
        expect = 2;
        test.test(result, expect).printResult();

        N = 4; M = 2; H = 2;
        map = new int[H][N];
        points = new int[][]{
                {2, 1},
                {2, 3},
        };
        for(int []point : points){
            int x = point[0]-1;
            int y = point[1]-1;
            map[x][y] = 1;
        }
        result = getMinimumExtraHorizon(N, H, map);
        expect = 2;
        test.test(result, expect).printResult();
    }
    public void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int M = kb.nextInt();
        int H = kb.nextInt();

        int[][] map = new int[H][N];
        for(int i = 0; i < M; i++){
            int a = kb.nextInt();
            int b = kb.nextInt();
            map[a-1][b-1] = 1;
        }
        int result = getMinimumExtraHorizon(N, H, map);
        System.out.println(result);
    }

    private int getMinimumExtraHorizon(int col, int row, int[][] map) {
        if(isFeasible(col, row, map)){
            return 0;
        }
        for(int i = 1; i <= 3; i++){
            Point[] ladders = initLadders(i, map, col, row);
            if(ladders[i-1] == null){
                return -1;
            }
            if(dfs(ladders, i, col, row, map)){
                return i;
            }
        }
        return -1;
    }

    private Point[] initLadders(int number, int[][] map, int col, int row){
        Point[] ladders = new Point[number];
        for(int i = 0; i < number; i++){
            ladders[i] = getFirstLadderPoint(map, col, row);
        }
        return ladders;
    }

    private boolean dfs(Point[] ladders, int number, int col, int row, int[][] map) {
        if(ladders == null || ladders[0] == null){
            return false;
        }
        if(isFeasible(col, row, map)){
            return true;
        }else{
            ladders = nextComb(ladders, number, col, row, map);
            return dfs(ladders, number, col, row, map);
        }
    }

    private Point[] nextComb(Point[] ladders, int number, int col, int row, int[][] map){
        int index = number-1;
        ladders[index] = getNextLadderPoint(map, col, row, ladders[index]);

        while(index > 0 && ladders[index] == null){
            index--;
            ladders[index] = getNextLadderPoint(map, col, row, ladders[index]);
            for(int i = index+1; i < number; i++){
                ladders[i] = getNextLadderFromPrev(map, col, row, ladders[i-1]);
                if(ladders[i] == null){
                    for(int j = index; j < i; j++){
                        int x = ladders[j].x;
                        int y = ladders[j].y;
                        map[x][y] = 0;
                    }
                    ladders[index] = null;
                    break;
                }
            }
        }
        return ladders;
    }

    private Point getNextLadderFromPrev(int[][] map, int col, int row, Point cur){
        Point next = cur.next(col, row);
        if(next == null){
            return null;
        }

        while(!isSettable(next, map, col)){
            next = next.next(col, row);
            if(next == null){
                return null;
            }
        }
        map[next.x][next.y] = 1;
        return next;
    }

    private Point getNextLadderPoint(int[][] map, int col, int row, Point cur){
        int x = cur.x;
        int y = cur.y;
        map[x][y] = 0;
        Point next = cur.next(col, row);
        if(next == null){
            return null;
        }

        while(!isSettable(next, map, col)){
            next = next.next(col, row);
            if(next == null){
                return null;
            }
        }
        map[next.x][next.y] = 1;
        return next;
    }

    private Point getFirstLadderPoint(int[][] map, int col, int row){
        Point cur = new Point(0, 0);
        while(!isSettable(cur, map, col)){
            cur = cur.next(col, row);
            if(cur == null){
                return null;
            }
        }
        map[cur.x][cur.y] = 1;
        return cur;
    }

    private boolean isSettable(Point cur, int[][] map, int col){
        int x = cur.x;
        int y = cur.y;
        if(
                (map[x][y] == 1) ||
                (y < col-2 && map[x][y+1] == 1) ||
                (y > 0 && map[x][y-1] == 1)
        ){
            return false;
        }
        return true;
    }

    private boolean isFeasible(int col, int row, int[][] map) {
        for(int i = 0; i < col; i++){
            int destination = getDestination(i, col, row, map);
            if(i != destination){
                return false;
            }
        }
        return true;
    }

    private int getDestination(int start, int col, int row, int[][] map) {
        int x = 0;
        int y = start;
        while(x < row){
            if(map[x][y] == 1){
                y++;
                x++;
            }else if(y > 0 && map[x][y-1] == 1){
                y--;
                x++;
            }else{
                x++;
            }
        }
        return y;
    }
}