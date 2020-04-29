package BOJ;

import Test.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class TurnTheDisk{
    private class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private final int[] dirX = {-1, 0, 1, 0};
    private final int[] dirY = {0, -1, 0, 1};
    private final int CLOCKWISE = 0;
    private final int COUNTERCLOCKWISE = 1;

    public static void main(String[] args) {
        TurnTheDisk app = new TurnTheDisk();
        app.test();
//        app.solve();
    }
    private void test(){
        Test<Integer> test = new Test<Integer>();

        int N, M, T;
        int[][] map;
        int[] x, d, k;
        int expect, result;

        N = 4; M = 4; T = 1;
        map = new int[][]{
                {1, 1, 2, 3},
                {5, 2, 4, 2},
                {3, 1, 3, 5},
                {2, 1, 3, 2}
        };
        x = new int[T];
        d = new int[T];
        k = new int[T];
        x[0] = 2; d[0] = 0; k[0] = 1;
        result = turnDisk(N, M, T, map, x, d, k);
        expect = 30;
        test.test(result, expect).printResult();

        N = 4; M = 4; T = 2;
        map = new int[][]{
                {1, 1, 2, 3},
                {5, 2, 4, 2},
                {3, 1, 3, 5},
                {2, 1, 3, 2}
        };
        x = new int[T];
        d = new int[T];
        k = new int[T];
        x[0] = 2; d[0] = 0; k[0] = 1;
        x[1] = 3; d[1] = 1; k[1] = 3;
        result = turnDisk(N, M, T, map, x, d, k);
        expect = 22;
        test.test(result, expect).printResult();

        N = 4; M = 4; T = 3;
        map = new int[][]{
                {1, 1, 2, 3},
                {5, 2, 4, 2},
                {3, 1, 3, 5},
                {2, 1, 3, 2}
        };
        x = new int[T];
        d = new int[T];
        k = new int[T];
        x[0] = 2; d[0] = 0; k[0] = 1;
        x[1] = 3; d[1] = 1; k[1] = 3;
        x[2] = 2; d[2] = 0; k[2] = 2;
        result = turnDisk(N, M, T, map, x, d, k);
        expect = 22;
        test.test(result, expect).printResult();

        N = 4; M = 4; T = 4;
        map = new int[][]{
                {1, 1, 2, 3},
                {5, 2, 4, 2},
                {3, 1, 3, 5},
                {2, 1, 3, 2}
        };
        x = new int[T];
        d = new int[T];
        k = new int[T];
        x[0] = 2; d[0] = 0; k[0] = 1;
        x[1] = 3; d[1] = 1; k[1] = 3;
        x[2] = 2; d[2] = 0; k[2] = 2;
        x[3] = 3; d[3] = 1; k[3] = 1;
        result = turnDisk(N, M, T, map, x, d, k);
        expect = 0;
        test.test(result, expect).printResult();

        N = 3; M = 4; T = 4;
        map = new int[][]{
                {1, 1, 2, 3},
                {5, 2, 4, 2},
                {3, 1, 3, 5}
        };
        x = new int[T];
        d = new int[T];
        k = new int[T];
        x[0] = 2; d[0] = 0; k[0] = 1;
        x[1] = 3; d[1] = 1; k[1] = 3;
        x[2] = 2; d[2] = 0; k[2] = 2;
        x[3] = 3; d[3] = 1; k[3] = 1;
        result = turnDisk(N, M, T, map, x, d, k);
        expect = 0;
        test.test(result, expect).printResult();
    }

    private void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int M = kb.nextInt();
        int T = kb.nextInt();
        int[][] map = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = kb.nextInt();
            }
        }

        int[] x = new int[T];
        int[] d = new int[T];
        int[] k = new int[T];
        for(int i = 0; i < T; i++){
            x[i] = kb.nextInt();
            d[i] = kb.nextInt();
            k[i] = kb.nextInt();
        }

        int result = turnDisk(N, M, T, map, x, d, k);
        System.out.println(result);

        kb.close();
    }
    private int turnDisk(int n, int m, int t, int[][] map, int[] x, int[] d, int[] k) {
        for(int i = 0; i < t; i++){
            ArrayList<Integer> indices = getIndices(n, x[i]);
            for(int index : indices){
                rotate(m, map[index], d[i], k[i]);
            }
            if(!remove(n, m, map)){
                average(n, m, map);
            }
        }
        int sum = getSum(n, m, map);
        return sum;
    }

    private void average(int n, int m, int[][] map) {
        double aver = getAverage(n, m, map);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] > 0){
                    if(map[i][j] > aver){
                        map[i][j]--;
                    }else if(map[i][j] < aver){
                        map[i][j]++;
                    }
                }
            }
        }
    }

    private double getAverage(int n, int m, int[][] map) {
        int sum = getSum(n, m, map);
        int count = getCount(n, m, map);
        return (double)sum / count;
    }

    private int getCount(int n, int m, int[][] map) {
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] > 0){
                    count++;
                }
            }
        }
        return count;
    }

    private int getSum(int n, int m, int[][] map) {
        int sum = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                sum += map[i][j];
            }
        }
        return sum;
    }

    private boolean remove(int n, int m, int[][] map) {
        boolean result = false;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int count = removeSame(n, m, map, i, j);
                if(count > 0){
                    result = true;
                }
            }
        }
        return result;
    }

    private int removeSame(int n, int m, int[][] map, int i, int j) {
        int curValue = map[i][j];
        if(curValue == 0){
            return 0;
        }
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(i, j));

        int size = 0;

        while(!queue.isEmpty()){
            Point cur = queue.pollFirst();

            for(int d = 0; d < 4; d++){
                int x = cur.x + dirX[d];
                int y = cur.y + dirY[d];

                if(y < 0){
                    y = m-1;
                }else if(y >= m){
                    y = 0;
                }else if(x < 0 || x >= n || map[x][y] == 0){
                    continue;
                }

                if(map[x][y] == curValue){
                    queue.add(new Point(x, y));
                    map[x][y] = 0;
                    size++;
                }
            }
        }
        if(size > 0){
            map[i][j] = 0;
        }
        return size;
    }

    private ArrayList<Integer> getIndices(int n, int x) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if((i+1) % x == 0){
                list.add(i);
            }
        }
        return list;
    }

    private void rotate(int m, int[] row, int d, int k){
        int[] back = getBack(m, row, d, k);
        int[] front = getFront(m, row, d, k);
        setFrontBack(row, front, back, m);
    }

    private int[] getBack(int m, int[] row, int d, int k) {
        int[]back;
        int size = 0;
        if(isClockwise(d)){
            size = m-k;
            back = new int[size];
            for(int i = 0; i < size; i++){
                back[i] = row[i];
            }
        }else if(isCounterclockwise(d)){
            size = k;
            back = new int[size];
            for(int i = 0; i < size; i++){
                back[i] = row[i];
            }
        }else{
            return null;
        }
        return back;
    }

    private int[] getFront(int m, int[] row, int d, int k) {
        int[]front;
        int size = 0;
        if(isClockwise(d)){
            size = k;
            front = new int[size];
            for(int i = 0; i < size; i++){
                front[i] = row[m-k+i];
            }
        }else if(isCounterclockwise(d)){
            size = m-k;
            front = new int[size];
            for(int i = 0; i < size; i++){
                front[i] = row[k+i];
            }
        }else{
            return null;
        }
        return front;
    }

    private void setFrontBack(int[] row, int[] front, int[] back, int size) {
        ArrayList<Integer> list = append(front, back);
        for(int i = 0; i < size; i++){
            row[i] = list.get(i);
        }
    }

    private ArrayList<Integer> append(int[] front, int[] back) {
        ArrayList<Integer> list = new ArrayList<>();

        list = addArrayToList(list, front);
        list = addArrayToList(list, back);

        return list;
    }

    private ArrayList<Integer> addArrayToList(ArrayList<Integer> list, int[] arr) {
        for(int element : arr){
            list.add(element);
        }
        return list;
    }

    private boolean isClockwise(int d){
        return d == CLOCKWISE;
    }
    private boolean isCounterclockwise(int d){
        return d == COUNTERCLOCKWISE;
    }
}