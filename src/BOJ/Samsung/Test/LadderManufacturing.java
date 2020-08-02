package BOJ.Samsung.Test;

import Test.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class LadderManufacturing{
    public static void main(String[] args) {
//        new Main().solve();
        new LadderManufacturing().test();
    }

    private void test() {
        Test test = new Test();

        String input;
        int expect;

        input = "2 0 3";
        expect = 0;
        testCase(test, input, expect);

        input = "2 1 3\n" +
                "1 1";
        expect = 1;
        testCase(test, input, expect);

        input = "5 5 6\n" +
                "1 1\n" +
                "3 2\n" +
                "2 3\n" +
                "5 1\n" +
                "5 4";
        expect = 3;
        testCase(test, input, expect);

        input = "6 5 6\n" +
                "1 1\n" +
                "3 2\n" +
                "1 3\n" +
                "2 5\n" +
                "5 5";
        expect = 3;
        testCase(test, input, expect);

        input = "5 8 6\n" +
                "1 1\n" +
                "2 2\n" +
                "3 3\n" +
                "4 4\n" +
                "3 1\n" +
                "4 2\n" +
                "5 3\n" +
                "6 4";
        expect = -1;
        testCase(test, input, expect);

        input = "5 12 6\n" +
                "1 1\n" +
                "1 3\n" +
                "2 2\n" +
                "2 4\n" +
                "3 1\n" +
                "3 3\n" +
                "4 2\n" +
                "4 4\n" +
                "5 1\n" +
                "5 3\n" +
                "6 2\n" +
                "6 4";
        expect = -1;
        testCase(test, input, expect);

        input = "5 6 6\n" +
                "1 1\n" +
                "3 1\n" +
                "5 2\n" +
                "4 3\n" +
                "2 3\n" +
                "1 4";
        expect = 2;
        testCase(test, input, expect);

//        input = "10 10 30\n" +
//                "1 1\n" +
//                "3 1\n" +
//                "5 2\n" +
//                "4 3\n" +
//                "2 3\n" +
//                "1 4\n" +
//                "7 2\n" +
//                "8 7\n" +
//                "6 6\n" +
//                "3 8\n";
//        expect = -1;
//        testCase(test, input, expect);

        input = "4 3 4 \n" +
                "1 1\n" +
                "2 2\n" +
                "1 3";
        expect = 3;
        testCase(test, input, expect);

//        input = "10 5 30\n" +
//                "30 9\n" +
//                "3 2\n" +
//                "2 3\n" +
//                "5 1\n" +
//                "5 4";
//        expect = -1;
//        testCase(test, input, expect);

        input = "4 3 4\n" +
                "1 1\n" +
                "2 2\n" +
                "1 3";
        expect = 3;
        testCase(test, input, expect);

        input = "4 6 7\n" +
                "1 1\n" +
                "3 3\n" +
                "4 2\n" +
                "5 3\n" +
                "7 1\n" +
                "7 3";
        expect = 2;
        testCase(test, input, expect);

        input = "4 1 4\n" +
                "3 2";
        expect = 1;
        testCase(test, input, expect);

        input = "4 7 5\n" +
                "1 1\n" +
                "2 2\n" +
                "1 3\n" +
                "3 2\n" +
                "4 1\n" +
                "5 1\n" +
                "4 3";
        expect = -1;
        testCase(test, input, expect);

        input = "4 3 4\n" +
                "1 1\n" +
                "2 2\n" +
                "3 3";
        expect = -1;
        testCase(test, input, expect);

        input = "2 0 2";
        expect = 0;
        testCase(test, input, expect);

        input = "4 1 4\n" +
                "3 2";
        expect = 1;
        testCase(test, input, expect);
    }

    private void testCase(Test test, String input, int expect) {
        String[] parsed = input.split("\n", 2);
        int N = Integer.parseInt(parsed[0].split(" ")[0]);
        int M = Integer.parseInt(parsed[0].split(" ")[1]);
        int H = Integer.parseInt(parsed[0].split(" ")[2]);
        int[][] horizontalInfo;
        if(parsed.length > 1){
            String body = parsed[1];
            horizontalInfo = InputParser.parseStringTo2DIntArray(body);
        }else{
            horizontalInfo = new int[0][2];
        }
        int result = getMinAdditionalHorizontalLineToManufacture(N, M, H, horizontalInfo);
        test.test(result, expect).printResult();
    }

    private void solve() {
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int M = kb.nextInt();
        int H = kb.nextInt();
        int[][] horizontalInfo = new int[M][2];
        for(int i = 0; i < M; i++){
            for(int j = 0; j < 2; j++){
                horizontalInfo[i][j] = kb.nextInt();
            }
        }
        kb.close();
        int result = getMinAdditionalHorizontalLineToManufacture(N, M, H, horizontalInfo);
        System.out.println(result);
    }

    private class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Point getLeft(){
            return new Point(this.x, this.y-1);
        }

        public Point getRight(){
            return new Point(this.x, this.y+1);
        }

        public void goRight(){
            this.y++;
        }

        public void goLeft(){
            this.y--;
        }

        public void goDown(){
            this.x++;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.x, this.y});
        }

        @Override
        public boolean equals(Object obj) {
            Point o = (Point) obj;
            return (this.x == o.x && this.y == o.y);
        }
    }
    private int getMinAdditionalHorizontalLineToManufacture(int col, int m, int row, int[][] horizontalInfo) {
        HashSet<Point> horizontals = getHorizontalSet(horizontalInfo);
        ArrayList<Point> availableSpots  = getAvailableSpot(horizontals, row, col);

        int availableSpotsSize = availableSpots.size();
        int limit = Math.min(availableSpotsSize, 3);
        for(int i = 0; i <= limit; i++){
            if(isFeasible(horizontals, availableSpots, row, col, i)){
                return i;
            }
        }
        return -1;
    }

    private boolean isFeasible(HashSet<Point> cur, ArrayList<Point> potential, int row, int col, int count) {
        int add = 0;
        boolean result = dfs(cur, potential, row, col, count, add);
        return result;
    }

    private boolean dfs(HashSet<Point> cur, ArrayList<Point> potential, int row, int col, int limit, int add) {
        if(add >= limit){
            if(isManufactured(cur, row, col)){
                return true;
            }else{
                return false;
            }
        }
        ArrayList<Point> clone = new ArrayList<>(potential);
        for(Point point : potential){
            clone.remove(0);
            if(isPuttable(cur, point)){
                cur.add(point);
                boolean tmp = dfs(cur, clone, row, col, limit, add+1);
                if(tmp){
                    return true;
                }
                cur.remove(point);
            }
        }
        return false;
    }

    private boolean isManufactured(HashSet<Point> cur, int row, int col) {
        for(int i = 0; i < col; i++){
            int end = getEnd(cur, i, row);
            if(end != i){
                return false;
            }
        }
        return true;
    }

    private int getEnd(HashSet<Point> cur, int index, int row) {
        Point point = new Point(0, index);
        while(point.x < row){
            if(cur.contains(point)){
                point.goRight();
            }else if(cur.contains(point.getLeft())){
                point.goLeft();
            }
            point.goDown();
        }
        return point.y;
    }

    private ArrayList<Point> getAvailableSpot(HashSet<Point> set, int n, int m) {
        ArrayList<Point> result = new ArrayList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m-1; j++){
                Point p = new Point(i, j);
                if(isPuttable(set, p)){
                    result.add(p);
                }
            }
        }

        return result;
    }

    private boolean isPuttable(HashSet<Point> set, Point p) {
        return !(set.contains(p) || set.contains(p.getRight()) || set.contains(p.getLeft()));
    }


    private HashSet<Point> getHorizontalSet(int[][] horizontalInfo) {
        HashSet<Point> set = new HashSet<>();

        for(int[] row : horizontalInfo){
            int x = row[0] - 1;
            int y = row[1] - 1;
            Point p = new Point(x, y);
            set.add(p);
        }
        return set;
    }

}