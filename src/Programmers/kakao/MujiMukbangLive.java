package Programmers.kakao;

import Test.Test;

import java.util.*;

public class MujiMukbangLive {
    public static void main(String[] args) {
        new MujiMukbangLive().test();
    }

    private void test() {
        int[] food_times;
        long k;
        int expect, result;

        food_times = new int[]{3, 1, 2};
        k = 5L;
        expect = 1;
        result = solution(food_times, k);
        Test.test(result, expect).printResult();

        food_times = new int[]{1, 2, 3};
        k = 2L;
        expect = 3;
        result = solution(food_times, k);
        Test.test(result, expect).printResult();


        food_times = new int[]{3, 2, 1};
        k = 2L;
        expect = 3;
        result = solution(food_times, k);
        Test.test(result, expect).printResult();

        food_times = new int[]{3, 2, 8, 5, 4};
        k = 8L;
        expect = 4;
        result = solution(food_times, k);
        Test.test(result, expect).printResult();
    }

    private class Food implements Comparable<Food>{
        private int index;
        private int remainTime;

        public Food(int index, int remainTime) {
            this.index = index;
            this.remainTime = remainTime;
        }

        public boolean isEmpty() {
            return this.remainTime == 0;
        }

        public void eat(int value) {
            this.remainTime -= value;
        }

        @Override
        public int compareTo(Food o) {
            if(this.remainTime == o.remainTime){
                return this.index - o.index;
            }
            return this.remainTime - o.remainTime;
        }
    }

    public int solution(int[] food_times, long k) {
        List<Food> list = initList(food_times);

        int prev = 0;
        while(!list.isEmpty()) {
            int size = list.size();
            int minValue = list.get(0).remainTime - prev;
            prev = list.get(0).remainTime;
            long value = (long)minValue * size;
            if(value > k) {
                int index = (int) (k % size);
                Collections.sort(list, Comparator.comparingInt(o -> o.index));
                return list.get(index).index;
            }
            list.remove(0);
            k -= value;
        }

        return -1;
    }

    private List<Food> initList(int[] food_times) {
        List<Food> list = new LinkedList<>();

        int size = food_times.length;
        for(int i = 0; i < size; i++) {
            Food food = new Food(i+1, food_times[i]);
            list.add(food);
        }

        Collections.sort(list);

        return list;
    }
}
