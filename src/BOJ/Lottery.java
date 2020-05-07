package BOJ;

import Test.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Lottery{
    private final int COMB_NUMBER = 6;
    public static void main(String[] args) {
//        new Lottery().solve();
        new Lottery().test();
    }
    public void test(){
        Test test = new Test();

        int k;
        int[] S;
        int[][] result, expect;
        String input;

        input = "7 1 2 3 4 5 6 7";
        k = Integer.parseInt(input.substring(0, 1));
        input = input.substring(2);
        S = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        result = getAllCombination(k, S);
        input = "1 2 3 4 5 6\n" +
                "1 2 3 4 5 7\n" +
                "1 2 3 4 6 7\n" +
                "1 2 3 5 6 7\n" +
                "1 2 4 5 6 7\n" +
                "1 3 4 5 6 7\n" +
                "2 3 4 5 6 7";
        expect = Arrays.stream(input.split("\n")).map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);
        test.test(result, expect).printResult();

        input = "8 1 2 3 5 8 13 21 34";
        k = Integer.parseInt(input.substring(0, 1));
        input = input.substring(2);
        S = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
        result = getAllCombination(k, S);
        input = "1 2 3 5 8 13\n" +
                "1 2 3 5 8 21\n" +
                "1 2 3 5 8 34\n" +
                "1 2 3 5 13 21\n" +
                "1 2 3 5 13 34\n" +
                "1 2 3 5 21 34\n" +
                "1 2 3 8 13 21\n" +
                "1 2 3 8 13 34\n" +
                "1 2 3 8 21 34\n" +
                "1 2 3 13 21 34\n" +
                "1 2 5 8 13 21\n" +
                "1 2 5 8 13 34\n" +
                "1 2 5 8 21 34\n" +
                "1 2 5 13 21 34\n" +
                "1 2 8 13 21 34\n" +
                "1 3 5 8 13 21\n" +
                "1 3 5 8 13 34\n" +
                "1 3 5 8 21 34\n" +
                "1 3 5 13 21 34\n" +
                "1 3 8 13 21 34\n" +
                "1 5 8 13 21 34\n" +
                "2 3 5 8 13 21\n" +
                "2 3 5 8 13 34\n" +
                "2 3 5 8 21 34\n" +
                "2 3 5 13 21 34\n" +
                "2 3 8 13 21 34\n" +
                "2 5 8 13 21 34\n" +
                "3 5 8 13 21 34";
        expect = Arrays.stream(input.split("\n")).map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray()).toArray(int[][]::new);
        test.test(result, expect).printResult();
    }

    public void solve(){
        Scanner kb = new Scanner(System.in);

        while(true){
            int k = kb.nextInt();
            if(k == 0){
                break;
            }
            int[] S = new int[k];
            for(int i = 0; i < k; i++){
                S[i] = kb.nextInt();
            }
            int[][] result = getAllCombination(k, S);
            for(int[] row : result){
                for(int elem : row){
                    System.out.print(elem + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
        kb.close();
    }
    private int[][] getAllCombination(int k, int[] s) {
        int index = 0;
        int combIndex = 0;
        int status = 0;
        int[] cur = new int[COMB_NUMBER];
        ArrayList<int[]> list = getAllList(k, s, cur, combIndex, index, status);
        int[][] result = to2DArray(list);
        return result;
    }

    private int[][] to2DArray(ArrayList<int[]> list) {
        int[][] result = list.stream().map(elem -> Arrays.stream(elem).toArray()).toArray(int[][]::new);
        return result;
    }

    private ArrayList<int[]> getAllList(int size, int[] arr, int[] cur, int combIndex, int index, int status) {
        ArrayList<int[]> list = new ArrayList<>();
        if(index == COMB_NUMBER){
            int[] clone = cur.clone();
            list.add(clone);
            return list;
        }

        for(int i = 0; i <= size - COMB_NUMBER - status; i++){
            int value = arr[combIndex + i];
            if(value < 0){
                continue;
            }
            cur[index] = value;
            arr[combIndex + i] = -1;
            ArrayList<int[]> tmp = getAllList(size, arr, cur, combIndex+i+1, index+1, status + i);
            arr[combIndex + i] = value;
            list.addAll(tmp);
        }
        return list;
    }
}