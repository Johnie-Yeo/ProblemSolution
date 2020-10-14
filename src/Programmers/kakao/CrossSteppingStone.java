package Programmers.kakao;

import Test.OldTest;

public class CrossSteppingStone{
    public static void main(String[] args) {
        new CrossSteppingStone().test();
    }
    public void test(){
        OldTest test = new OldTest();

        int[] stones;
        int k, result, expect;

        stones = new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        k = 3;
        result = solution(stones, k);
        expect = 3;
        test.test(result, expect).printResult();

        stones = new int[200000];
        for(int i = 0; i < 200000; i++){
            stones[i] = 200000-i;
        }
        k = 100000;
        result = solution(stones, k);
        expect = 100000;
        test.test(result, expect).printResult();

        stones = new int[200000];
        for(int i = 0; i < 200000; i++){
            stones[i] = i+1;
        }
        k = 100000;
        result = solution(stones, k);
        expect = 100000;
        test.test(result, expect).printResult();

        stones = new int[200000];
        for(int i = 0; i < 200000; i++){
            stones[i] = 1;
        }
        k = 100000;
        result = solution(stones, k);
        expect = 1;
        test.test(result, expect).printResult();
    }
    public int solution(int[] stones, int k) {
        int min = 1;
        int max = 200000000;
        int length = stones.length;

        int result = 0;
        while(min <= max) {
            int mid = (min + max) / 2;

            int[] tmp = cross(stones, mid, length);

            if(isFeasible(tmp, k)) {
                result = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return result;
    }

    private boolean isFeasible(int[] stones, int k) {
        int count = 0;
        for(int stone : stones) {
            if(stone >= 0) {
                count = 0;
            } else {
                count++;
                if(count >= k) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[] cross(int[] stones, int friends, int length) {
        int[] result = new int[length];
        for(int i = 0; i < length; i++) {
            result[i] = stones[i] - friends;
        }
        return result;
    }
}