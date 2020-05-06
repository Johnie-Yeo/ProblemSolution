package BOJ;

import Test.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FourIntegerToMakeSum0{
    public static void main(String[] args) throws IOException {
//        new Main().solve();
        new FourIntegerToMakeSum0().test();
    }
    public void test(){
        Test<Long> test = new Test<Long>();

        int n;
        int[][] data;
        long result, expect;

        n = 6;
        data = new int[][]{
                {-45, 22, 42, - 16},
                {-41, -27, 56, 30},
                {-36, 53, -37, 77},
                {-36, 30, -75, -46},
                {26, -38, -10, 62},
                {-32, -54, -6, 45}
        };
        result = getNumberOfCombination(n, data);
        expect = 5;
        test.test(result, expect).printResult();

        n = 4000;
        data = new int[n][4];
        result = getNumberOfCombination(n, data);
        expect = 256000000000000L;
        test.test(result, expect).printResult();

        n = 4000;
        data = new int[n][4];
        int number = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < 4; j++){
                data[i][j] = number++;
            }
        }
        result = getNumberOfCombination(n, data);
        expect = 0;
        test.test(result, expect).printResult();
    }
    private void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());
        int[][] data = new int[n][4];
        for(int i = 0; i < n; i++){
            String[] numbers = br.readLine().split(" ");
            data[i] = toIntArray(numbers);
        }

        long result = getNumberOfCombination(n, data);
        System.out.println(result);

        br.close();
    }

    private int[] toIntArray(String[] numbers) {
        int[] arr = new int[4];
        for(int i = 0; i < 4; i++){
            arr[i] = Integer.parseInt(numbers[i]);
        }
        return arr;
    }

    private long getNumberOfCombination(int n, int[][] data) {
        int[] prevList = getList(n, data, 0);
        int[] postList = getList(n, data, 2);

        long count = 0;
        for(int sum : prevList){
            int target = sum * -1;
            long start = getStartPoint(postList, target);
            long end = getEndPoint(postList, target);
            if(start < 0){
                continue;
            }
            long searchCount = end - start + 1L;
            count += searchCount;
        }
        return count;
    }

    private long getStartPoint(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        long index = -1;

        while(start <= end){
            int middle = (start+end) / 2;

            if(arr[middle] == target){
                index = middle;
                end = middle - 1;
            }else if(arr[middle] > target){
                end = middle - 1;
            }else{
                start = middle + 1;
            }
        }
        return index;
    }

    private long getEndPoint(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        long index = -1;

        while(start <= end){
            int middle = (start+end) / 2;

            if(arr[middle] == target){
                index = middle;
                start = middle + 1;
            }else if(arr[middle] > target){
                end = middle - 1;
            }else{
                start = middle + 1;
            }
        }
        return index;
    }

    private int[] getList(int n, int[][] data, int index) {
        int[] list = new int[n*n];
        int cur = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int sum = data[i][index] + data[j][index+1];
                list[cur++] = sum;
            }
        }
        if(index == 0){
            return list;
        }
        Arrays.sort(list);
        return list;
    }
}