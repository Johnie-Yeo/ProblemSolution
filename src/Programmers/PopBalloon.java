package Programmers;

import Test.Test;

import java.util.Arrays;

public class PopBalloon {
    public static void main(String[] args) {
        new PopBalloon().test();
    }

    private void test() {
        int[] a;
        int expect, result;

        a = new int[]{9, -1, -5};
        expect = 3;
        result = solution(a);
        Test.test(result, expect).printResult();

        a = new int[]{-16,27,65,-2,58,-92,-71,-68,-61,-33};
        expect = 6;
        result = solution(a);
        Test.test(result, expect).printResult();
    }
    // 내가 가장 작거나
    // 내 왼쪽 애들이 다 나보다 크고, 오른쪽 애들중에는 나보다 작은게 있거나
    // 내 왼쪽 애들 중에 나보다 작은게 있고, 오른쪽은 다 나보다 크거나
    public int solution(int[] a) {
        int count = 2;
        int length = a.length;

        int[] leftMin = new int[length];
        int[] rightMin = new int[length];

        leftMin[0] = Integer.MAX_VALUE;
        rightMin[length-1] = Integer.MAX_VALUE;

        for(int i = 1; i < length; i++) {
            leftMin[i] = Math.min(leftMin[i-1], a[i-1]);
        }
        for(int i = length - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i+1], a[i+1]);
        }

        for(int i = 1; i < length - 1; i++) {
            int left = leftMin[i];
            int right = rightMin[i];
            int current = a[i];

            if(!(left < current && right < current)){
                count++;
            }
        }

        return count;
    }

}
