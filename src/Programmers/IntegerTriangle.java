package Programmers;

import Test.OldTest;

public class IntegerTriangle {
    public static void main(String[] args) {
        new IntegerTriangle().test();
    }
    private void test(){
        OldTest test = new OldTest();
        int[][] triangle = {
                {7},
                {3, 8},
                {8, 1, 0},
                {2, 7, 4, 4},
                {4, 5, 2, 6, 5}
        };
        int expect = 30;
        int result = solution(triangle);

        test.test(result, expect);

        triangle = new int[][]{
                {7}
        };
        expect = 7;
        result = solution(triangle);

        test.test(result, expect);

        triangle = new int[][]{
                {0},
                {0, 0}
        };
        expect = 0;
        result = solution(triangle);

        test.test(result, expect);
    }
    public int solution(int[][] triangle) {
        int[][] maxTriangle = buildMaxTriangle(triangle);
        int rowLength = maxTriangle.length;
        int max = getMax(triangle[rowLength-1]);

        return max;
    }

    private int getMax(int[] arr) {
        int max = 0;
        for(int element : arr){
            max = Math.max(max, element);
        }
        return max;
    }

    private int[][] buildMaxTriangle(int[][] triangle) {
        int length = triangle.length;
        for(int i = 0; i< length; i++){
            for(int j = 0; j <= i; j++){
                int max = getMaxValue(triangle, i, j);
                triangle[i][j] += max;
            }
        }
        return triangle;
    }

    private int getMaxValue(int[][] triangle, int i, int j) {
        int leftI = i-1;
        int leftJ = j-1;
        int rightI = i-1;
        int rightJ = j;
        if(hasValue(triangle, leftI, leftJ) && hasValue(triangle, rightI, rightJ)){
            return Math.max(triangle[leftI][leftJ], triangle[rightI][rightJ]);
        }else if(hasValue(triangle, leftI, leftJ)){
            return triangle[leftI][leftJ];
        }else if(hasValue(triangle, rightI, rightJ)){
            return triangle[rightI][rightJ];
        }else{
            return 0;
        }
    }

    private boolean hasValue(int[][] triangle, int i, int j) {
        int rowLength = triangle.length;
        if(i < 0 || i >= rowLength){
            return false;
        }
        int colLength = triangle[i].length;
        if(j < 0 || j >= colLength){
            return false;
        }
        return true;
    }
}
