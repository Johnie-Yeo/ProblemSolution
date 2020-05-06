package Programmers;

import Test.OldestTest;

import java.util.Collections;
import java.util.PriorityQueue;

public class RamenFactory {
    public static void main(String[] args) {
        new RamenFactory().test();
    }
    private void test(){
        OldestTest test = new OldestTest();

        int stock = 4;
        int[] dates = {4, 10, 15};
        int[] supplies = {20, 5, 10};
        int k = 30;
        int expect = 2;
        int result = solution(stock, dates, supplies, k);
        test.test(result, expect);

        stock = 4;
        dates = new int[]{4, 10, 29};
        supplies = new int[]{20, 5, 10};
        k = 30;
        expect = 3;
        result = solution(stock, dates, supplies, k);
        test.test(result, expect);

        stock = 4;
        dates = new int[]{4, 10, 29};
        supplies = new int[]{20, 15, 30};
        k = 30;
        expect = 2;
        result = solution(stock, dates, supplies, k);
        test.test(result, expect);

        stock = 4;
        dates = new int[]{4, 10, 29};
        supplies = new int[]{20, 5, 30};
        k = 30;
        expect = 3;
        result = solution(stock, dates, supplies, k);
        test.test(result, expect);

        stock = 4;
        dates = new int[]{4, 10, 14, 29};
        supplies = new int[]{10, 5, 10, 30};
        k = 30;
        expect = 4;
        result = solution(stock, dates, supplies, k);
        test.test(result, expect);

        stock = 4;
        dates = new int[]{4, 10, 14, 29};
        supplies = new int[]{10, 6, 10, 30};
        k = 30;
        expect = 3;
        result = solution(stock, dates, supplies, k);
        test.test(result, expect);

        stock = 4;
        dates = new int[]{4, 10, 14, 29};
        supplies = new int[]{20, 6, 10, 30};
        k = 30;
        expect = 2;
        result = solution(stock, dates, supplies, k);
        test.test(result, expect);

        stock = 29;
        dates = new int[]{4, 10, 14, 29};
        supplies = new int[]{20, 6, 10, 30};
        k = 30;
        expect = 1;
        result = solution(stock, dates, supplies, k);
        test.test(result, expect);

        stock = 4;
        dates =     new int[]{4, 10, 14, 17, 20, 23, 29};
        supplies =  new int[]{20,10, 1,   1,   1, 2, 1};
        k = 30;
        expect = 2;
        result = solution(stock, dates, supplies, k);
        test.test(result, expect);

        stock = 4;
        dates =     new int[]{4, 10, 14, 17, 20, 23, 29};
        supplies =  new int[]{20, 9, 1,   1,   1, 2, 1};
        k = 30;
        expect = 2;
        result = solution(stock, dates, supplies, k);
        test.test(result, expect);
    }
    public int solution(int stock, int[] dates, int[] supplies, int k) {
        int date = 0;
        int count = 0;
        int index = 0;
        int length = dates.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(length, Collections.reverseOrder());

        while(date + stock < k){
            int consumed = getConsumedAmount(dates, index);
            int termToNextSupply = getTermToNextSupply(dates, index, k);

            date = dates[index];
            stock = stock - consumed;
            queue.add(supplies[index]);

            while(stock - termToNextSupply < 0){
                int max = queue.poll();
                count++;
                stock += max;
            }

            index++;
        }
        return count;
    }

    private int getTermToNextSupply(int[] dates, int index, int k) {
        try{
            return dates[index+1] - dates[index];
        }catch (ArrayIndexOutOfBoundsException e){
            return k - dates[index];
        }
    }

    private int getConsumedAmount(int[] dates, int index) {
        try{
            return dates[index] - dates[index-1];
        }catch(ArrayIndexOutOfBoundsException e){
            return dates[index];
        }
    }
}
