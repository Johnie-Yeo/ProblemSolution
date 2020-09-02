package BOJ.Samsung.Test;

import Test.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Gerrymandering2{
    public static void main(String[] args) {
        new Gerrymandering2().test();
//        new Main().solve();
    }
    public void test(){
        String input;
        int expect;

        input = "6\n" +
                "1 2 3 4 1 6\n" +
                "7 8 9 1 4 2\n" +
                "2 3 4 1 1 3\n" +
                "6 6 6 6 9 4\n" +
                "9 1 9 1 9 5\n" +
                "1 1 1 1 9 9";
        expect = 18;
        testCase(input, expect);

        input = "6\n" +
                "5 5 5 5 5 5\n" +
                "5 5 5 5 5 5\n" +
                "5 5 5 5 5 5\n" +
                "5 5 5 5 5 5\n" +
                "5 5 5 5 5 5\n" +
                "5 5 5 5 5 5";
        expect = 20;
        testCase(input, expect);

        input = "8\n" +
                "1 2 3 4 5 6 7 8\n" +
                "2 3 4 5 6 7 8 9\n" +
                "3 4 5 6 7 8 9 1\n" +
                "4 5 6 7 8 9 1 2\n" +
                "5 6 7 8 9 1 2 3\n" +
                "6 7 8 9 1 2 3 4\n" +
                "7 8 9 1 2 3 4 5\n" +
                "8 9 1 2 3 4 5 6";
        expect = 23;
        testCase(input, expect);

        input = "20\n" +
                "1 2 3 4 5 6 7 8 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "2 3 4 5 6 7 8 9 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "3 4 5 6 7 8 9 1 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "4 5 6 7 8 9 1 2 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "5 6 7 8 9 1 2 3 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "6 7 8 9 1 2 3 4 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "7 8 9 1 2 3 4 5 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "8 9 1 2 3 4 5 6 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
        expect = 23;
        testCase(input, expect);
    }

    private void testCase(String input, int expect) {
        OldTest test = new OldTest();
        String[] parsed = input.split("\n", 2);
        int N = Integer.parseInt(parsed[0]);
        int[][] map = InputParser.parseStringTo2DIntArray(parsed[1]);
        int result = gerrymandering(N, map);
        test.test(result, expect).printResult();
    }

    public void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int[][] map = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = kb.nextInt();
            }
        }
        int result = gerrymandering(N, map);
        System.out.println(result);
        kb.close();
    }

    private class Border{
        int x, y;
        int d1, d2;
        public Border(int x, int y, int d1, int d2){
            this.x = x;
            this.y = y;
            this.d1 = d1;
            this.d2 = d2;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new Object[]{ this.x, this.y, this.d1, this.d2});
        }

        @Override
        public boolean equals(Object obj) {
            Border o = (Border)obj;
            return (this.x == o.x && this.y == o.y && this.d1 == o.d1 && this.d2 == o.d2);
        }
    }
    private int gerrymandering(int n, int[][] people) {
        HashSet<Border> borderInfoComb = getBorderInfoComb(n);

        int min = Integer.MAX_VALUE;
        for(Border border : borderInfoComb){
            int[][] clone = clone(people);
            int[] population = getEachAreasPopulation(border, clone, n);
            int diff = getMaxMinDiff(population);
            min = Math.min(diff, min);
        }
        return min;
    }

    private HashSet<Border> getBorderInfoComb(int n) {
        int d1 = 1;
        int d2 = 1;
        int x = 0;
        int y = 1;
        HashSet<Border> set = new HashSet<>();
        dfs(x, y, d1, d2, n, set);
        return set;
    }

    private void dfs(int x, int y, int d1, int d2, int n, HashSet<Border> set) {
        Border border = new Border(x, y, d1, d2);
        if(isOutOfRange(x, y, d1, d2, n) || set.contains(border)){
            return;
        }
        set.add(border);

        dfs(x, y,d1+1, d2, n, set);
        dfs(x, y, d1,d2+1, n, set);
        dfs(x+1, y, d1, d2, n, set);
        dfs(x, y+1, d1, d2, n, set);
    }

    private boolean isOutOfRange(int x, int y, int d1, int d2, int n) {
        return (x+d1+d2 >= n || y - d1 < 0 || y + d2 >= n);
    }

    private int[][] clone(int[][] people) {
        return Arrays.stream(people).map(arr -> Arrays.stream(arr).toArray()).toArray(int[][]::new);
    }

    private int[] getEachAreasPopulation(Border border, int[][] people, int n) {
        int[] population = new int[5];
        population[4] = getFifthArea(border, people, n);

        population[0] = getFirstArea(border, people);
        population[1] = getSecondArea(border, people, n);
        population[2] = getThirdArea(border, people, n);
        population[3] = getForthArea(border, people, n);

        return population;
    }

    private int getForthArea(Border border, int[][] people, int n) {
        int count = 0;
        for(int i = (border.x + border.d2+1); i < n; i++){
            for(int j = (border.y - border.d1 + border.d2); j < n; j++){
                count += people[i][j];
            }
        }
        return count;
    }

    private int getThirdArea(Border border, int[][] people, int n) {
        int count = 0;
        for(int i = (border.x + border.d1); i < n; i++){
            for(int j = 0; j < (border.y - border.d1 + border.d2); j++){
                count += people[i][j];
            }
        }
        return count;
    }

    private int getSecondArea(Border border, int[][] people, int n) {
        int count = 0;
        for(int i = 0; i <= (border.x + border.d2); i++){
            for(int j = border.y+1; j < n; j++){
                count += people[i][j];
            }
        }
        return count;
    }

    private int getFirstArea(Border border, int[][] people) {
        int count = 0;
        for(int i = 0; i < (border.x + border.d1); i++){
            for(int j = 0; j <= border.y; j++){
                count += people[i][j];
            }
        }
        return count;
    }

    private class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point)obj;
            return (this.x == p.x && this.y == p.y);
        }

        public Point clone(){
            return new Point(this.x, this.y);
        }
    }
    private int getFifthArea(Border border, int[][] people, int n) {
        int count = 0;
        ArrayList<Point> line = getLine(border);
        Point end = new Point(border.x + border.d2, border.y + border.d2);
        while(!line.get(0).equals(end)){
            for(Point point : line){
                int x = point.x;
                int y = point.y;
                count += people[x][y];
                people[x][y] = 0;
            }
            int size = line.size();
            for(int i = 0; i < size-1; i++){
                int x = line.get(i).x+1;
                int y = line.get(i).y;
                count += people[x][y];
                people[x][y] = 0;
            }

            line = moveDown(line);
        }
        for(Point point : line){
            int x = point.x;
            int y = point.y;
            count += people[x][y];
            people[x][y] = 0;
        }
        return count;
    }

    private ArrayList<Point> moveDown(ArrayList<Point> line) {
        ArrayList<Point> list = new ArrayList<>();

        for(Point point : line){
            int x = point.x + 1;
            int y = point.y + 1;
            Point next = new Point(x, y);
            list.add(next);
        }

        return list;
    }

    private ArrayList<Point> getLine(Border border) {
        ArrayList<Point> list = new ArrayList<>();
        Point start = new Point(border.x, border.y);
        Point end = new Point(border.x + border.d1, border.y - border.d1);

        while(!start.equals(end)){
            list.add(start.clone());
            start.x += 1;
            start.y -= 1;
        }
        list.add(end);

        return list;
    }

    private int getMaxMinDiff(int[] population) {
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int people : population){
            max = Math.max(people, max);
            min = Math.min(people, min);
        }
        return max - min;
    }
}
