package Programmers;

import Test.OldestTest;

public class Immigration {
    public static void main(String[] args) {
        new Immigration().test();
    }
    private void test(){
        OldestTest test = new OldestTest();

        int n = 6;
        int[] times = {7, 10};
        long expect = 28;
        long result = solution(n, times);
        test.test(result, expect);

        n = 6;
        times = new int[]{7, 10, 1000000000};
        expect = 28;
        result = solution(n, times);
        test.test(result, expect);

        n = 6;
        times = new int[]{7, 10, 99999999, 1000000000};
        expect = 28;
        result = solution(n, times);
        test.test(result, expect);

        n = 6;
        times = new int[]{7, 10, 20};
        expect = 21;
        result = solution(n, times);
        test.test(result, expect);

        n = 6;
        times = new int[]{7, 10000000, 20000};
        expect = 42;
        result = solution(n, times);
        test.test(result, expect);

        n = 6;
        times = new int[]{1000, 1002, 1003};
        expect = 2006;
        result = solution(n, times);
        test.test(result, expect);

        n = 8;
        times = new int[]{7, 10, 20};
        expect = 30;
        result = solution(n, times);
        test.test(result, expect);

        n = 4;
        times = new int[]{1,1,1,1};
        expect = 1;
        result = solution(n, times);
        test.test(result, expect);

        n = 1000000000;
        times = new int[]{1000000000};
        expect = 1000000000000000000L;
        result = solution(n, times);
    }
    public long solution(int n, int[] times) {
        long result = Long.MAX_VALUE;
        long min = 0;
        long max = getMaxTime(times, n);
        while(min <= max){
            long mid = (min + max) / 2;
            long numOfPeoplePossibleToPassImmigrate
                    = getNumOfPeoplePossibleToPassImmigrate(mid, times);
            if(numOfPeoplePossibleToPassImmigrate >= n){
                result = Math.min(result, mid);
                if(max == mid){
                    break;
                }
                max = mid;
            }else{
                if(min == mid){
                    min++;
                    continue;
                }
                min = mid;
            }
        }
        return result;
    }

    private long getNumOfPeoplePossibleToPassImmigrate(long mid, int[] times) {
        long people = 0;
        for(int time : times){
            long tmp = mid / time;
            people += tmp;
        }
        return people;
    }

    private long getMaxTime(int[] times, int n) {
        //가장 짧은 시간의 검사대에서 모든 사람이 검사받는게 최악
        //어차피 여기서 병렬적으로 처리되서 계속 줄어들 것이므로
        long min = Long.MAX_VALUE;
        for(int time : times){
            min = Math.min(min, time);
        }
        return min * n;
    }
}
