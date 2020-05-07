package BOJ;

import Test.Test;

import java.util.HashSet;
import java.util.Scanner;

public class DescendingNumber{
    private final long MAX = 9876543210L;
    public static void main(String[] args) {
        new DescendingNumber().solve();
//        new DescendingNumber().test();
    }
    public void test(){
        Test test = new Test();

        int N = 180000;
        long result = getNthDescendingNumber(N);
        long expect = 42;
        test.test(result, expect).printResult();
    }
    private void solve(){
        Scanner kb = new Scanner(System.in);

        int n = kb.nextInt();
        long result = getNthDescendingNumber(n);
        System.out.println(result);

        kb.close();
    }

    private long getNthDescendingNumber(int n) {
        if(n <= 10){
            return n;
        }
        HashSet<Long> set = new HashSet<>();
        long[] descNumbers = new long[n+1];

        int headIndex = 0;
        long head = descNumbers[headIndex];
        int tail = 0;
        for(int i = 0; i <= n; i++){
            long cur = makeNumber(head, tail);
            if(cur > MAX){
                return -1;
            }
            while(set.contains(cur) || !isDescendingNumber(cur, set)){
                head = descNumbers[++headIndex];
                tail = 0;
                cur = makeNumber(head, tail);
            }
            descNumbers[i] = cur;
            set.add(cur);
            tail = (tail + 1)%10;
        }

        return descNumbers[n];
    }

    private long makeNumber(long head, int tail) {
        String number = ""+head+tail;
        return Long.parseLong(number);
    }

    private boolean isDescendingNumber(long number, HashSet<Long> set) {
        if(number < 10){
            return true;
        }
        long head = getHead(number);
        int tail = getTail(number);

        if(set.contains(head) && getTail(head) > tail){
            return true;
        }

        return false;
    }

    private int getTail(long number) {
        return (int) (number % 10);
    }

    private long getHead(long number) {
        return number / 10;
    }
}