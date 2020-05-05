package BOJ;

import Test.Test;

import java.util.Scanner;

public class Decomposition{
    public static void main(String[] args) {
//        new Main().solve();
        new Decomposition().test();
    }
    public void test(){
        Test<Integer> test = new Test<Integer>();

        int N = 216;
        int result = getMinimumConstructor(N);
        int expect = 198;
        test.test(result, expect).printResult();
    }
    public void solve(){
        Scanner kb = new Scanner(System.in);

        int N = kb.nextInt();
        int result = getMinimumConstructor(N);
        System.out.println(result);
        kb.close();
    }

    private int getMinimumConstructor(int n) {
        int constructor = getStart(n);
        while(constructor < n){
            int decomposition = getDecomposition(constructor);
            if(decomposition == n){
                return constructor;
            }else{
                constructor++;
            }
        }
        return 0;
    }

    private int getDecomposition(int n) {
        int decomposition = n;
        while(n > 0){
            decomposition += n%10;
            n /= 10;
        }
        return decomposition;
    }

    private int getStart(int n) {
        int compare = 1;
        while(n > compare * 10){
            compare *= 10;
        }
        return compare / 10;
    }
}