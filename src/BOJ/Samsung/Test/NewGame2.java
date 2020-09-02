package BOJ.Samsung.Test;

import Test.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class NewGame2{
    public static void main(String[] args) {
        new NewGame2().test();
//        new Main().solve();
    }
    private void test(){
        String input;
        int expect;

        input = "4 4\n" +
                "0 0 2 0\n" +
                "0 0 1 0\n" +
                "0 0 1 2\n" +
                "0 2 0 0\n" +
                "2 1 1\n" +
                "3 2 3\n" +
                "2 2 1\n" +
                "4 1 2";
        expect = -1;
        testCase(input, expect);

        input = "4 4\n" +
                "0 0 0 0\n" +
                "0 0 0 0\n" +
                "0 0 0 0\n" +
                "0 0 0 0\n" +
                "1 1 1\n" +
                "1 2 1\n" +
                "1 3 1\n" +
                "1 4 1";
        expect = 1;
        testCase(input, expect);

        input = "4 4\n" +
                "0 0 0 0\n" +
                "0 0 0 0\n" +
                "0 0 0 0\n" +
                "0 0 0 0\n" +
                "1 1 1\n" +
                "1 2 1\n" +
                "1 3 1\n" +
                "2 4 3";
        expect = 1;
        testCase(input, expect);

        input = "4 4\n" +
                "0 0 0 0\n" +
                "0 0 0 0\n" +
                "0 0 0 0\n" +
                "0 0 0 0\n" +
                "1 1 1\n" +
                "1 2 1\n" +
                "1 3 1\n" +
                "3 3 3";
        expect = 2;
        testCase(input, expect);

        input = "6 10\n" +
                "0 1 2 0 1 1\n" +
                "1 2 0 1 1 0\n" +
                "2 1 0 1 1 0\n" +
                "1 0 1 1 0 2\n" +
                "2 0 1 2 0 1\n" +
                "0 2 1 0 2 1\n" +
                "1 1 1\n" +
                "2 2 2\n" +
                "3 3 4\n" +
                "4 4 1\n" +
                "5 5 3\n" +
                "6 6 2\n" +
                "1 6 3\n" +
                "6 1 2\n" +
                "2 4 3\n" +
                "4 2 1";
        expect = 7;
        testCase(input, expect);
    }

    private void testCase(String input, int expect){
        OldTest test = new OldTest();

        String[] parsed = input.split("\n", 2);

        int N = Integer.parseInt(parsed[0].split(" ")[0]);
        int K = Integer.parseInt(parsed[0].split(" ")[1]);

        parsed = parsed[1].split("\n");
        int[][] map = new int[N][];
        for(int i = 0; i < N; i++){
            map[i] = InputParser.parseStringToIntArray(parsed[i]);
        }

        int[][] horse = new int[K][];
        for(int i = 0 ; i < K; i++){
            horse[i] = InputParser.parseStringToIntArray(parsed[i+N]);
        }
        int result = gameResult(N, K, map, horse);
        test.test(result, expect).printResult();
    }

    private void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int K = kb.nextInt();
        int[][] map = new int[N][N];
        for(int i = 0 ; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = kb.nextInt();
            }
        }
        int[][] horse = new int[K][3];
        for(int i = 0; i < K; i++){
            for(int j = 0; j < 3; j++){
                horse[i][j] = kb.nextInt();
            }
        }
        int result = gameResult(N, K, map, horse);
        System.out.println(result);
    }

    private final int WHITE = 0;
    private final int RED = 1;
    private final int BLUE = 2;
    private final int[] dirX = {0, 0, -1, 1};
    private final int[] dirY = {1, -1, 0, 0};

    private class Point{
        int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(new Object[]{this.x, this.y});
        }

        @Override
        public boolean equals(Object obj) {
            Point point = (Point)obj;
            return (this.x == point.x && this.y == point.y);
        }

        public Point move(int dir) {
            int x = this.x + dirX[dir];
            int y = this.y + dirY[dir];
            return new Point(x, y);
        }

        public Point clone(){
            return new Point(this.x, this.y);
        }
    }

    private class Horse{
        int index;
        Point point;
        int dir;
        public Horse(int index, Point p, int dir){
            this.index = index;
            this.point = p;
            this.dir = dir;
        }

        public Point move() {
            Point point = this.point.move(this.dir);
            return point;
        }

        public void reverseDir(){
            if(this.dir % 2 == 0){
                this.dir++;
            }else{
                this.dir--;
            }
        }
    }

    private int gameResult(int n, int k, int[][] map, int[][] horses) {
        ArrayList<Horse> horseList = getHorseList(horses);
        HashMap<Point, ArrayList<Integer>> mapStatus = initMapStatus(horseList);

        for(int i = 1; i <= 1000; i++){
            if(move(mapStatus, n, map, horseList)){
                return i;
            }
        }
        return -1;
    }

    private boolean move(HashMap<Point, ArrayList<Integer>> mapStatus, int n, int[][] map, ArrayList<Horse> horseList) {
        int size = horseList.size();


        for(int i = 0; i < size; i++){
            Horse horse = horseList.get(i);

            Point next = horse.move();
            int block = getBlockColor(map, next, n);
            switch (block){
                case WHITE:
                    whiteMove(horseList, horse, next, mapStatus);
                    break;
                case RED:
                    redMove(horseList, horse, next, mapStatus);
                    break;
                case BLUE:
                    blueMove(map, n, horseList, horse, next, mapStatus);
                    break;
            }
            if(isFinished(mapStatus)){
                return true;
            }
        }
        return false;
    }

    private void whiteMove(ArrayList<Horse> horseList, Horse horse, Point next, HashMap<Point, ArrayList<Integer>> mapStatus) {
        Point curPoint = horse.point;
        ArrayList<Integer> curList = getCurList(mapStatus, curPoint, horse.index);

        ArrayList<Integer> nextList = getPointOfList(mapStatus, next);
        nextList.addAll(curList);
        mapStatus.put(next, nextList);

        for(int index : curList){
            horseList.get(index).point = next.clone();
        }
    }

    private ArrayList<Integer> getPointOfList(HashMap<Point, ArrayList<Integer>> map, Point point){
        return map.get(point) == null ? new ArrayList<>() : map.get(point);
    }

    private ArrayList<Integer> getCurList(HashMap<Point, ArrayList<Integer>> mapStatus, Point curPoint, int target) {
        ArrayList<Integer> list = new ArrayList<>(mapStatus.get(curPoint));
        int index = getIndex(list, target);

        ArrayList<Integer> head = new ArrayList<>();
        for(int i = 0; i < index; i++){
            int cur = list.remove(0);
            head.add(cur);
        }
        mapStatus.put(curPoint, head);
        return list;
    }

    private int getIndex(ArrayList<Integer> list, int target) {
        int index = 0;
        for(int elem : list){
            if(elem == target){
                return index;
            }
            index++;
        }
        return -1;
    }

    private void redMove(ArrayList<Horse> horseList, Horse horse, Point next, HashMap<Point, ArrayList<Integer>> mapStatus) {
        Point curPoint = horse.point;
        ArrayList<Integer> curList = getCurList(mapStatus, curPoint, horse.index);
        curList = reverseList(curList);

        ArrayList<Integer> nextList = getPointOfList(mapStatus, next);
        nextList.addAll(curList);
        mapStatus.put(next, nextList);

        for(int index : curList){
            horseList.get(index).point = next.clone();
        }
    }

    private ArrayList<Integer> reverseList(ArrayList<Integer> list) {
        ArrayList<Integer> result = new ArrayList<>();

        for(int elem : list){
            result.add(0, elem);
        }
        return result;
    }

    private void blueMove(int[][] map, int n, ArrayList<Horse> horseList, Horse horse, Point next, HashMap<Point, ArrayList<Integer>> mapStatus) {
        horse.reverseDir();
        int dir = horse.dir;
        next = next.move(dir).move(dir);

        int color = getBlockColor(map, next, n);
        switch (color){
            case WHITE:
                whiteMove(horseList, horse, next, mapStatus);
                break;
            case RED:
                redMove(horseList, horse, next, mapStatus);
                break;
            case BLUE:
                break;
        }
    }

    private int getBlockColor(int[][] map, Point next, int n) {
        if(isOutOfRange(next.x, next.y, n)){
            return BLUE;
        }
        return map[next.x][next.y];
    }

    private boolean isOutOfRange(int x, int y, int n) {
        return (x < 0 || y < 0 || x >= n || y >= n);
    }

    private HashMap<Point, ArrayList<Integer>> initMapStatus(ArrayList<Horse> horseList) {
        HashMap<Point, ArrayList<Integer>> map = new HashMap<>();
        for(Horse horse : horseList){
            Point point = horse.point;
            ArrayList<Integer> list = new ArrayList<>();
            list.add(horse.index);
            map.put(point, list);
        }
        return map;
    }

    private ArrayList<Horse> getHorseList(int[][] horses) {
        ArrayList<Horse> list = new ArrayList<>();

        int index = 0;
        for(int[] horse : horses){
            int x = horse[0] - 1;
            int y = horse[1] - 1;
            int dir = horse[2] - 1;
            Point point = new Point(x, y);
            Horse cur = new Horse(index++, point, dir);
            list.add(cur);
        }
        return list;
    }

    private boolean isFinished(HashMap<Point, ArrayList<Integer>> map) {
        for(Point point : map.keySet()){
            int size = map.get(point).size();
            if(size >= 4){
                return true;
            }
        }
        return false;
    }
}