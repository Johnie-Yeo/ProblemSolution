package BOJ;

import Test.*;

import java.util.Scanner;

public class RestoreArray {
    public static void main(String[] args) {
        new RestoreArray().test();
//        new RestoreArray().solve();
    }
    private void test() {
        Test test = new Test();
        String input, result, expect;

        input = "2 4 1 1\n" +
                "1 2 3 4 0\n" +
                "5 7 9 11 4\n" +
                "0 5 6 7 8";
        result = getOutput(input);
        expect = "1 2 3 4\n" +
                "5 6 7 8";
        test.test(result, expect).printResult();

        input = "3 3 2 1\n" +
                "1 2 3 0\n" +
                "4 5 6 0\n" +
                "7 9 11 3\n" +
                "0 4 5 6\n" +
                "0 7 8 9";
        result = getOutput(input);
        expect = "1 2 3\n" +
                "4 5 6\n" +
                "7 8 9";
        test.test(result, expect).printResult();

        input = "4 4 3 3\n" +
                "1 1 1 1 0 0 0\n" +
                "1 1 1 1 0 0 0\n" +
                "1 1 1 1 0 0 0\n" +
                "1 1 1 2 1 1 1\n" +
                "0 0 0 1 1 1 1\n" +
                "0 0 0 1 1 1 1\n" +
                "0 0 0 1 1 1 1";
        result = getOutput(input);
        expect = "1 1 1 1\n" +
                "1 1 1 1\n" +
                "1 1 1 1\n" +
                "1 1 1 1";
        test.test(result, expect).printResult();

        input = "4 4 2 2\n" +
                "1 1 1 1 0 0\n" +
                "1 1 1 1 0 0\n" +
                "1 1 2 2 1 1\n" +
                "1 1 2 2 1 1\n" +
                "0 0 1 1 1 1\n" +
                "0 0 1 1 1 1";
        result = getOutput(input);
        expect = "1 1 1 1\n" +
                "1 1 1 1\n" +
                "1 1 1 1\n" +
                "1 1 1 1";
        test.test(result, expect).printResult();

        input = "3 3 1 1\n" +
                "0 0 0 0\n" +
                "0 1 0 0\n" +
                "0 0 1 0\n" +
                "0 0 0 0";
        result = getOutput(input);
        expect = "0 0 0\n" +
                "0 1 0\n" +
                "0 0 0";
        test.test(result, expect).printResult();

        input = "3 3 2 1\n" +
                "1 1 100 0\n" +
                "100 100 100 0\n" +
                "200 201 201 100\n" +
                "0 100 100 100\n" +
                "0 200 200 200";
        result = getOutput(input);
        expect = "1 1 100\n" +
                "100 100 100\n" +
                "200 200 200";
        test.test(result, expect).printResult();

        input = "3 3 1 2\n" +
                "300 200 100 0 0\n" +
                "150 400 500 200 100\n" +
                "1 2 153 400 200\n" +
                "0 0 1 2 3";
        result = getOutput(input);
        expect = "300 200 100\n" +
                "150 400 200\n" +
                "1 2 3";
        test.test(result, expect).printResult();

        input = "3 3 1 1\n" +
                "1 2 3 0\n" +
                "4 6 8 3\n" +
                "7 12 14 6\n" +
                "0 7 8 9";
        result = getOutput(input);
        expect = "1 2 3\n" +
                "4 5 6\n" +
                "7 8 9";
        test.test(result, expect).printResult();
    }

    private String getOutput(String input) {
        String[] parsed = input.split("\n", 2);
        String[] tmp = parsed[0].split(" ");
        int H = Integer.parseInt(tmp[0]);
        int W = Integer.parseInt(tmp[1]);
        int X = Integer.parseInt(tmp[2]);
        int Y = Integer.parseInt(tmp[3]);


        int[][] B = InputParser.parseStringTo2DIntArray(parsed[1]);
        String result = restoreArray(H, W, X, Y, B);
        return result;
    }

    private void solve() {
        Scanner kb = new Scanner(System.in);

        int H = kb.nextInt();
        int W = kb.nextInt();
        int X = kb.nextInt();
        int Y = kb.nextInt();

        int row = H + X;
        int col = W + Y;
        int[][] B = new int[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                B[i][j] = kb.nextInt();
            }
        }

        kb.close();

        String result = restoreArray(H, W, X, Y, B);
        System.out.println(result);
    }

    private String restoreArray(int h, int w, int x, int y, int[][] b) {
        int[][] a = new int[h][w];

        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(i < x || j < y){
                    a[i][j] = b[i][j];
                }else{
                    a[i][j] = b[i][j] - a[i-x][j-y];
                }
            }
        }

        String result = parseArrayToString(a, h, w);
        return result;
    }

    private String parseArrayToString(int[][] a, int row, int col) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < row; i ++){
            for(int j = 0; j < col; j++){
                sb.append(a[i][j]);
                if(j < col-1){
                    sb.append(" ");
                }else if(i < row-1){
                    sb.append("\n");
                }
            }
        }

        return sb.toString();
    }


}
