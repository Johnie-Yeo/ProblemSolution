package BOJ;

import Test.*;

import java.util.*;

public class TurnTheDisk{
    public static void main(String[] args) {
//        new Main().solve();
        new TurnTheDisk().test();
    }
    private void test(){
        String input;
        int expect;

        input = "4 4 1\n" +
                "1 1 2 3\n" +
                "5 2 4 2\n" +
                "3 1 3 5\n" +
                "2 1 3 2\n" +
                "2 0 1";
        expect = 30;
        testCase(input, expect);

        input = "4 4 2\n" +
                "1 1 2 3\n" +
                "5 2 4 2\n" +
                "3 1 3 5\n" +
                "2 1 3 2\n" +
                "2 0 1\n" +
                "3 1 3";
        expect = 22;
        testCase(input, expect);

        input = "4 4 3\n" +
                "1 1 2 3\n" +
                "5 2 4 2\n" +
                "3 1 3 5\n" +
                "2 1 3 2\n" +
                "2 0 1\n" +
                "3 1 3\n" +
                "2 0 2";
        expect = 22;
        testCase(input, expect);

        input = "4 4 4\n" +
                "1 1 2 3\n" +
                "5 2 4 2\n" +
                "3 1 3 5\n" +
                "2 1 3 2\n" +
                "2 0 1\n" +
                "3 1 3\n" +
                "2 0 2\n" +
                "3 1 1";
        expect = 0;
        testCase(input, expect);

        input = "4 6 3\n" +
                "1 2 3 4 5 6\n" +
                "2 3 4 5 6 7\n" +
                "3 4 5 6 7 8\n" +
                "4 5 6 7 8 9\n" +
                "2 1 4\n" +
                "3 0 1\n" +
                "2 1 2";
        expect = 26;
        testCase(input, expect);
    }

    private void testCase(String input, int expect) {
        Test test = new Test();

        String[] parsed = input.split("\n", 2);
        int N = Integer.parseInt(parsed[0].split(" ")[0]);
        int M = Integer.parseInt(parsed[0].split(" ")[1]);
        int T = Integer.parseInt(parsed[0].split(" ")[2]);

        parsed = parsed[1].split("\n");

        int[][] map = new int[N][];
        for(int i = 0; i < N; i++){
            map[i] = InputParser.parseStringToIntArray(parsed[i]);
        }

        int[][] turn = new int[T][];
        for(int i = 0; i < T; i++){
            turn[i] = InputParser.parseStringToIntArray(parsed[i+N]);
        }

        int result = turnDisk(N, M, T, map, turn);
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

        int[][] turn = new int[T][3];
        for(int i = 0; i < T; i++){
            turn[i][0] = kb.nextInt();
            turn[i][1] = kb.nextInt();
            turn[i][2] = kb.nextInt();
        }

        int result = turnDisk(N, M, T, map, turn);
        System.out.println(result);

        kb.close();
    }

    private class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private int turnDisk(int n, int m, int t, int[][] disk, int[][] turns) {
        for(int[] turn : turns){
            int x = turn[0];
            int d = turn[1];
            int k = turn[2];
            disk = turn(n, m, disk, x, d, k);
            ArrayList<Point> points = getAdjacentPointsValueSamePoint(disk, n, m);
            if(!points.isEmpty()){
                removeValue(disk, points);
            }else{
                averageOperate(disk, n, m);
            }
        }

        int result = getSum(disk);
        return result;
    }

    private void averageOperate(int[][] disk, int n, int m) {
        double average = getAverage(disk);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(disk[i][j] == 0){
                    continue;
                }
                if(disk[i][j] > average){
                    disk[i][j]--;
                }else if(disk[i][j] < average){
                    disk[i][j]++;
                }
            }
        }
    }

    private double getAverage(int[][] disk) {
        int sum = getSum(disk);
        int count = getCount(disk);
        double average = (double)sum / count;
        return average;
    }

    private int getCount(int[][] disk) {
        int count = 0;
        for(int[] row : disk){
            for(int elem : row){
                if(elem > 0){
                    count++;
                }
            }
        }
        return count;
    }

    private void removeValue(int[][] disk, ArrayList<Point> points) {
        for(Point point : points){
            int x = point.x;
            int y = point.y;
            disk[x][y] = 0;
        }
    }

    private ArrayList<Point> getAdjacentPointsValueSamePoint(int[][] disk, int n, int m) {
        ArrayList<Point> result = new ArrayList<>();

        boolean[][] check = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(check[i][j]){
                    continue;
                }
                ArrayList<Point> same = getSamePoints(disk, i, j, n, m, check);
                if(same.size() > 1){
                    result.addAll(same);
                }
            }
        }

        return result;
    }

    private ArrayList<Point> getSamePoints(int[][] disk, int i, int j, int n, int m, boolean[][] check) {
        ArrayList<Point> result = new ArrayList<>();

        final int target = disk[i][j];
        final int[] dirX = {-1, 0, 1, 0};
        final int[] dirY = {0, -1, 0, 1};

        if(target == 0){
            return result;
        }

        Queue<Point> queue = new LinkedList<>();
        Point start = new Point(i, j);
        queue.add(start);
        result.add(start);

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int d = 0; d < 4; d++){
                int x = cur.x + dirX[d];
                int y = (cur.y + dirY[d] + m) % m;
                if(x < 0 || x >= n || check[x][y]){
                    continue;
                }
                if(disk[x][y] == target){
                    check[x][y] = true;
                    Point next = new Point(x, y);
                    queue.add(next);
                    result.add(next);
                }
            }
        }

        return result;
    }

    private int[][] turn(int n, int m, int[][] disk, int x, int d, int k) {
        int[][] result = new int[n][m];

        for(int i = 0; i < n; i++){
            if((i+1) % x == 0){
                result[i] = turnDiskIndex(disk[i], d, k, m);
            }else{
                result[i] = disk[i].clone();
            }
        }

        return result;
    }

    private int[] turnDiskIndex(int[] disk, int dir, int k, int size) {
        final int CLOCKWISE = 0;
        final int COUNTER_CLOCKWISE = 1;
        switch (dir){
            case CLOCKWISE:
                return turnDiskIndexClockWise(disk, k, size);
            case COUNTER_CLOCKWISE:
                return turnDiskIndexCounterClockWise(disk, k, size);
        }
        return null;
    }

    private int[] turnDiskIndexCounterClockWise(int[] disk, int k, int size) {
        int[] result = new int[size];
        for(int i = 0; i < size; i++){
            int prev = (i + k) % size;
            result[i] = disk[prev];
        }
        return result;
    }

    private int[] turnDiskIndexClockWise(int[] disk, int k, int size) {
        int[] result = new int[size];
        for(int i = 0; i < size; i++){
            int prev = (i - k + size) % size;
            result[i] = disk[prev];
        }
        return result;
    }

    private int getSum(int[][] arr) {
        int sum = Arrays.stream(arr).mapToInt(row -> getSum(row)).sum();
        return sum;
    }

    private int getSum(int[] arr){
        int sum = Arrays.stream(arr).sum();
        return sum;
    }
}