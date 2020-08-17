package BOJ;

import Test.*;

import java.util.Arrays;
import java.util.Scanner;

public class FindNumber {
    public static void main(String[] args) {
//        new Main().solve();
        new FindNumber().test();
    }

    private void test() {
        Test test = new Test();

        String input = "5\n" +
                "4 1 5 2 3\n" +
                "5\n" +
                "1 3 7 9 5";
        String expect = "1\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "1";
        testCase(test, input, expect);
    }

    private void testCase(Test test, String input, String expect) {
        String[] parsed = input.split("\n");
        int N = Integer.parseInt(parsed[0]);
        int[] A = InputParser.parseStringToIntArray(parsed[1]);
        int M = Integer.parseInt(parsed[2]);
        int[] numbers = InputParser.parseStringToIntArray(parsed[3]);

        String result = testInclude(N, A, M, numbers);
        test.test(result, expect).printResult();
    }

    private void solve() {
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int[] A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = kb.nextInt();
        }
        int M = kb.nextInt();
        int[] numbers = new int[M];
        for(int i = 0; i < M; i++){
            numbers[i] = kb.nextInt();
        }
        kb.close();

        String result = testInclude(N, A, M, numbers);
        System.out.println(result);
    }

    private String testInclude(int n, int[] a, int m, int[] numbers) {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(a);

        int index = 0;
        for(int number : numbers){
            if(isIncluded(n, a, number)){
                sb.append(1);
            }else{
                sb.append(0);
            }
            if(index < m-1){
                sb.append("\n");
            }
            index++;
        }

        String result = sb.toString();
        return result;
    }

    private boolean isIncluded(int n, int[] arr, int target) {
        int start = 0;
        int end = n-1;

        while(start <= end){
            int middle = (start + end) / 2;

            if(arr[middle] == target){
                return true;
            }else if(arr[middle] > target){
                end = middle - 1;
            }else{
                start = middle + 1;
            }
        }
        return false;
    }
}
