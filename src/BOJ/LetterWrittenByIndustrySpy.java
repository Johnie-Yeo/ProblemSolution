package BOJ;

import Test.Test;

import java.util.*;

public class LetterWrittenByIndustrySpy {
    public static void main(String[] args) {
//        new Main().solve();
        new LetterWrittenByIndustrySpy().solve();
    }
    private void test(){
        Test test = new Test();

        int c;
        String paper;
        int result, expect;

        c = 4;

        paper = "17";
        result = getNumberOfPrimeNumber(paper);
        expect = 3;
        test.test(result, expect).printResult();

        paper = "1276543";
        result = getNumberOfPrimeNumber(paper);
        expect = 1336;
        test.test(result, expect).printResult();

        paper = "9999999";
        result = getNumberOfPrimeNumber(paper);
        expect = 0;
        test.test(result, expect).printResult();

        paper = "011";
        result = getNumberOfPrimeNumber(paper);
        expect = 2;
        test.test(result, expect).printResult();
    }

    private void solve(){
        Scanner kb = new Scanner(System.in);

        int c = kb.nextInt();
        for(int i = 0; i < c; i++){
            String paper = kb.next();
            int result = getNumberOfPrimeNumber(paper);
            System.out.println(result);
        }
        kb.close();
    }
    private int getNumberOfPrimeNumber(String paper) {
        int[] numbers = parseNumber(paper);
        int length = numbers.length;
        int cur = 0, index = 0;
        HashSet<Integer> set = new HashSet<>();
        boolean[] check = new boolean[length];
        HashSet<Integer> result = getPrimeNumberCombinationCount(numbers, set, check, cur, index, length);
        return result.size();
    }

    private HashSet<Integer> getPrimeNumberCombinationCount(int[] numbers, HashSet<Integer> set, boolean[] check, int cur, int index, int length) {
        if(index >= length){
            return set;
        }

        for(int i = 0; i < length; i++){
            if(check[i]){
                continue;
            }
            check[i] = true;
            int number = cur*10 + numbers[i];
            if(isPrime(number)){
                set.add(number);
            }
            HashSet<Integer> tmp = getPrimeNumberCombinationCount(numbers, set, check, number, index+1, length);
            set.addAll(tmp);
            check[i] = false;
        }
        return set;
    }

    private boolean isPrime(int number){
        if(number < 2){
            return false;
        }else if(number == 2){
            return true;
        }
        for(int i = 2; i < Math.sqrt(number) + 1; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
    private int[] parseNumber(String str) {
        return str.chars().map(x -> x-'0').toArray();
    }
}
