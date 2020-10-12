package Programmers;

import Test.Test;

import java.util.*;
import java.util.stream.Collectors;

public class SteppingStone {
    public static void main(String[] args) {
        new SteppingStone().test();
    }

    private void test() {
        int dist = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        int expect = 4;
        int result = solution(dist, rocks, n);
        Test.test(result, expect).printResult();
    }

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        List<Integer> rockList = Arrays.stream(rocks).boxed().collect(Collectors.toList());
        rockList.add(distance);
        int result = binarySearch(rockList, distance, n);
        return result;
    }

    private int binarySearch(List<Integer> rockList, int distance, int n) {
        int left = 0;
        int right = distance;
        int result = 0;

        while(left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            int prev = 0;

            for(int rock : rockList) {
                if(rock - prev < mid) {
                    count++;
                } else {
                    prev = rock;
                }
            }

            if(count <= n) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid-1;
            }
        }

        return result;
    }
}
