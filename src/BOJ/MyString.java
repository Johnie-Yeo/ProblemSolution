package BOJ;

import Test.OldTest;

import java.util.Scanner;

public class MyString{
    public static void main(String[] args) {
        new MyString().test();
//        new Main().solve();
    }
    private void test(){
        OldTest test = new OldTest();

        String A, B;
        int result, expect;

        A = "adaabc";
        B = "aababbc";
        result = getMinimumDiff(A, B);
        expect = 2;
        test.test(result, expect).printResult();

        A = "adaabc";
        B = "aababbc";
        result = getMinimumDiff(A, B);
        expect = 2;
        test.test(result, expect).printResult();
    }

    private void solve(){
        Scanner kb = new Scanner(System.in);

        String A = kb.next();
        String B = kb.next();
        int result = getMinimumDiff(A, B);
        System.out.println(result);

        kb.close();
    }

    private int getMinimumDiff(String a, String b) {
        int size = a.length();
        int sizeDiff = b.length() - size;
        if(sizeDiff == 0){
            return getDiff(a, b, size);
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i <= sizeDiff; i++){
            String tmp = b.substring(i);
            int diff = getDiff(a, tmp, size);
            min = Math.min(diff, min);
        }
        return min;
    }

    private int getDiff(String a, String b, int size) {
        int count = 0;
        for(int i = 0; i < size; i++){
            if(a.charAt(i) != b.charAt(i)){
                count++;
            }
        }
        return count;
    }
}