package BOJ.Samsung.Test;

import Test.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DiceYut{
    public static void main(String[] args) {
//        new Main().solve();
        new DiceYut().test();
    }
    private void test(){
        String input;
        int expect;

        input = "1 2 3 4 1 2 3 4 1 2";
        expect = 190;
        testCase(input, expect);

        input = "1 1 1 1 1 1 1 1 1 1";
        expect = 133;
        testCase(input, expect);

        input = "5 1 2 3 4 5 5 3 2 4";
        expect = 214;
        testCase(input, expect);

        input = "5 5 5 5 5 5 5 5 5 5";
        expect = 130;
        testCase(input, expect);

        input = "5 5 5 5 5 1 1 1 1 1";
        expect = 167;
        testCase(input, expect);

        input = "5 5 5 5 5 2 2 2 2 2";
        expect = 160;
        testCase(input, expect);

        input = "5 5 5 5 5 2 2 1 3 3";
        expect = 161;
        testCase(input, expect);
    }

    private void testCase(String input, int expect) {
        Test test = new Test();

        int[] arr = InputParser.parseStringToIntArray(input);
        int result = getMaxScore(arr);
        test.test(result, expect).printResult();
    }

    private void solve(){
        Scanner kb = new Scanner(System.in);

        int[] input = new int[10];
        for(int i = 0; i < 10; i++){
            input[i] = kb.nextInt();
        }

        int result = getMaxScore(input);
        System.out.println(result);
    }

    private class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Point clone(){
            return new Point(this.x, this.y);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.x, this.y});
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point)obj;
            return (this.x == p.x && this.y == p.y);
        }
    }
    private int getMaxScore(int[] input) {
        int[][] map = initMap();

        ArrayList<Point> horses = initHorse();
        int result = dfs(input, 0, map, horses, 0);

        return result;
    }

    private int dfs(int[] input, int index, int[][] map, ArrayList<Point> horses, int cur) {
        int max = 0;

        if(index >= 10){
            return cur;
        }

        ArrayList<Point> list = clone(horses);

        int loopIndex = 0;
        for(Point horse : horses){
            if(isEndPoint(horse)){
                continue;
            }
            Point next = move(horse, input[index]);
            if(!isEndPoint(next) && contain(horses, next)){
                continue;
            }

            list.remove(horse);
            list.add(loopIndex, next);

            int added = cur + map[next.x][next.y];
            int result = dfs(input, index+1, map, list, added);
            max = Math.max(max, result);

            list.remove(next);
            list.add(loopIndex, horse);

            loopIndex++;
        }

        return max;
    }

    private boolean contain(ArrayList<Point> horses, Point point) {
        if(horses.contains(point)){
            return true;
        }
        HashSet<Point> centers = getCenter();
        if(centers.contains(point)){
            for(Point horse : horses){
                if(centers.contains(horse)){
                    return true;
                }
            }
        }
        HashSet<Point> last = getLast();
        if(last.contains(point)){
            for(Point horse : horses){
                if(last.contains(horse)){
                    return true;
                }
            }
        }

        HashSet<Point> last30 = get30();
        if(last30.contains(point)){
            for(Point horse : horses){
                if(last30.contains(horse)){
                    return true;
                }
            }
        }

        HashSet<Point> last35 = get35();
        if(last35.contains(point)){
            for(Point horse : horses){
                if(last35.contains(horse)){
                    return true;
                }
            }
        }

        return false;
    }

    private HashSet<Point> get30() {
        HashSet<Point> set = new HashSet<>();

        Point p2 = new Point(1, 5);
        Point p3 = new Point(2, 4);
        Point p4 = new Point(3, 5);

        set.add(p2);
        set.add(p3);
        set.add(p4);

        return set;
    }

    private HashSet<Point> get35() {
        HashSet<Point> set = new HashSet<>();

        Point p2 = new Point(1, 6);
        Point p3 = new Point(2, 5);
        Point p4 = new Point(3, 6);

        set.add(p2);
        set.add(p3);
        set.add(p4);

        return set;
    }

    private HashSet<Point> getLast() {
        HashSet<Point> set = new HashSet<>();

        Point p1 = new Point(0, 20);
        Point p2 = new Point(1, 7);
        Point p3 = new Point(2, 6);
        Point p4 = new Point(3, 7);

        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);

        return set;
    }

    private HashSet<Point> getCenter() {
        HashSet<Point> set = new HashSet<>();

        Point p1 = new Point(1, 4);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(3, 4);

        set.add(p1);
        set.add(p2);
        set.add(p3);

        return set;
    }

    private boolean isEndPoint(Point point) {
        HashSet<Point> set = new HashSet<>();
        Point p1 = new Point(0, 21);
        Point p2 = new Point(1, 8);
        Point p3 = new Point(2, 7);
        Point p4 = new Point(3, 8);

        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);

        return set.contains(point);
    }

    private ArrayList<Point> clone(ArrayList<Point> list) {
        return list.stream().map(point -> point.clone()).collect(Collectors.toCollection(ArrayList::new));
    }

    private Point move(Point horse, int i) {
        int x = horse.x;
        int y = horse.y + i;

        if(x == 0 && y > 0 && y < 20 && y % 5 == 0){
            return new Point(y/5, 0);
        }else if(x == 0 && y >= 22){
            return new Point(x, 21);
        }else if((x == 1 || x == 3) && y >= 8){
            return new Point(x, 8);
        }else if(x == 2 && y >= 7){
            return new Point(x, 7);
        }else{
            return new Point(x, y);
        }
    }

    private ArrayList<Point> initHorse() {
        ArrayList<Point> horses = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            horses.add(new Point(0, 0));
        }
        return horses;
    }

    private int[][] initMap() {
        int[][] map = new int[4][];
        map[0] = getBaseMap();
        map[1] = new int[]{10, 13, 16, 19, 25, 30, 35, 40, 0};
        map[2] = new int[]{20,     22, 24, 25, 30, 35, 40, 0};
        map[3] = new int[]{30, 28, 27, 26, 25, 30, 35, 40, 0};

        return map;
    }

    private int[] getBaseMap() {
        int[] arr = new int[22];
        for(int i = 0; i < 21; i++){
            arr[i] = 2 * i;
        }
        return arr;
    }

}