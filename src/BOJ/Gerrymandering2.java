package BOJ;

import Test.Test;

import java.util.Scanner;

public class Gerrymandering2{
    public static void main(String[] args) {
        Gerrymandering2 app = new Gerrymandering2();
        app.test();
//        app.solve();
    }
    public void test(){
        Test<Integer> test = new Test<Integer>();

        int N = 6;
        int[][] map = {
                {1, 2, 3, 4, 1, 6},
                {7, 8, 9, 1, 4, 2},
                {2, 3, 4, 1, 1, 3},
                {6, 6, 6, 6, 9, 4},
                {9, 1, 9, 1, 9, 5},
                {1, 1, 1, 1, 9, 9}
        };
        int result = gerrymandering(N, map);
        int expect = 18;
        test.test(result, expect).printResult();

        N = 6;
        map = new int[][]{
                {5, 5, 5, 5, 5, 5},
                {5, 5, 5, 5, 5, 5},
                {5, 5, 5, 5, 5, 5},
                {5, 5, 5, 5, 5, 5},
                {5, 5, 5, 5, 5, 5},
                {5, 5, 5, 5, 5, 5}
        };
        result = gerrymandering(N, map);
        expect = 20;
        test.test(result, expect).printResult();

        N = 8;
        map = new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8},
                {2, 3, 4, 5, 6, 7, 8, 9},
                {3, 4, 5, 6, 7, 8, 9, 1},
                {4, 5, 6, 7, 8, 9, 1, 2},
                {5, 6, 7, 8, 9, 1, 2, 3},
                {6, 7, 8, 9, 1, 2, 3, 4},
                {7, 8, 9, 1, 2, 3, 4, 5},
                {8, 9, 1, 2, 3, 4, 5, 6}
        };
        result = gerrymandering(N, map);
        expect = 23;
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

    private int gerrymandering(int n, int[][] people) {
        int min = Integer.MAX_VALUE;
        for(int x = 0; x < n; x++){
            for(int y = 0; y < n; y++){
                for(int d1 = 0; d1 < n-2; d1++){
                    for(int d2 = n-1-d1; d2 >= 0; d2--){
                        if(isFeasible(x, y, d1, d2, n)){
                            int[][] area = getArea(n, x, y, d1, d2);
                            int diff = getDiff(n, people, area);
                            min = Math.min(min, diff);
                        }
                    }
                }
            }
        }

        return min;
    }
    private boolean isFeasible(int x, int y, int d1, int d2, int N){
        if(
                (d1 >= 0 && d2 >= 0) &&
                (x >= 0 && x+d1+d2 > x && N-1 >= x+d1+d2) &&
                (y-d1 >= 0 && y > y-d1 && y+d2 > y && N-1 >= y+d2)
        ){
            return true;
        }
        return false;
    }
    private int[][] getArea(int n, int x, int y, int d1, int d2){
        int[][] area = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                area[i][j] = getAreaNumber(n, i, j, x, y, d1, d2);
            }
        }
        area = checkArea5(area, x, y, d1, d2, n);
        return area;
    }

    private int[][] checkArea5(int[][] area, int x, int y, int d1, int d2, int n) {
        area = checkBorder(area, x, y, d1, d2);
        area = fillArea5(area, x, y, d1, d2, n);
        return area;
    }

    private int[][] fillArea5(int[][] area, int x, int y, int d1, int d2, int n) {
        for(int i = 0; i < n; i++){
            int point = -1;
            for(int j = 0; j < n; j++){
                if(area[i][j] == 5){
                    if(point < 0){
                        point = j;
                    }else{
                        for(int index = point; index <= j; index++){
                            area[i][index] = 5;
                        }
                    }
                }
            }
        }
        return area;
    }

    private int[][] checkBorder(int[][] area, int x, int y, int d1, int d2) {
        area = checkFirstLine(area, x, y, d1, d2);
        area = checkSecondLine(area, x, y, d1, d2);
        area = checkThirdLine(area, x, y, d1, d2);
        area = checkForthLine(area, x, y, d1, d2);
        return area;
    }

    private int[][] checkForthLine(int[][] area, int x, int y, int d1, int d2) {
        for(int i = 0; i <= d1; i++){
            area[x+d2+i][y+d2-i] = 5;
        }
        return area;
    }

    private int[][] checkThirdLine(int[][] area, int x, int y, int d1, int d2) {
        for(int i = 0; i <= d2; i++){
            area[x+d1+i][y-d1+i] = 5;
        }
        return area;
    }

    private int[][] checkSecondLine(int[][] area, int x, int y, int d1, int d2) {
        for(int i = 0; i <= d2; i++){
            area[x+i][y+i] = 5;
        }
        return area;
    }

    private int[][] checkFirstLine(int[][] area, int x, int y, int d1, int d2) {
        for(int i = 0; i <= d1; i++){
            area[x+i][y-i] = 5;
        }
        return area;
    }

    private int getAreaNumber(int N, int r, int c, int x, int y, int d1, int d2) {
        int area = 0;
        if(isNumber1(N, r, c, x, y, d1, d2)){
            area = 1;
        }else if(isNumber2(N, r, c, x, y, d1, d2)){
            area = 2;
        }else if(isNumber3(N, r, c, x, y, d1, d2)){
            area = 3;
        }else if(isNumber4(N, r, c, x, y, d1, d2)){
            area = 4;
        }else{
            return -1;
        }
        return area;
    }
    private boolean isNumber1(int N, int r, int c, int x, int y, int d1, int d2){
        return ((r >= 0 && x+d1 > r) && (c >= 0 && y >= c));
    }
    private boolean isNumber2(int N, int r, int c, int x, int y, int d1, int d2){
        return ((r >= 0 && x+d2 >= r) && (c > y && N-1 >= c));
    }
    private boolean isNumber3(int N, int r, int c, int x, int y, int d1, int d2){
        return ((r >= x+d1 && N-1 >= r) && (c >= 0 && y-d1+d2 > c));
    }
    private boolean isNumber4(int N, int r, int c, int x, int y, int d1, int d2){
        return ((r > x+d2 && N-1 >= r) && (c >= y-d1+d2 && N-1 >= c));
    }
    private int getDiff(int n, int[][] population, int[][] area){
        int[] people = new int[5];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                int numberOfArea = area[i][j]-1;
                people[numberOfArea] += population[i][j];
            }
        }
        int max = getMax(people);
        int min = getMin(people);
        return max - min;
    }
    private int getMax(int[] arr){
        int max = 0;
        for(int element : arr){
            max = Math.max(max, element);
        }
        return max;
    }
    private int getMin(int[] arr){
        int min = Integer.MAX_VALUE;
        for(int element : arr){
            min = Math.min(element, min);
        }
        return min;
    }
}
