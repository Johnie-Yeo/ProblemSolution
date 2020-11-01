package Leetcode.hard;

import Test.Test;

import java.util.ArrayList;
import java.util.List;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        new MedianOfTwoSortedArrays().test();
    }

    private void test() {
        int[] nums1, nums2;
        double expect, result;

        nums1 = new int[]{1, 3};
        nums2 = new int[]{2};
        expect =  2.00000;
        result = findMedianSortedArrays(nums1, nums2);
        Test.test(result, expect).printResult();

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        expect =  2.50000;
        result = findMedianSortedArrays(nums1, nums2);
        Test.test(result, expect).printResult();

        nums1 = new int[]{0, 0};
        nums2 = new int[]{0, 0};
        expect =  0.00000;
        result = findMedianSortedArrays(nums1, nums2);
        Test.test(result, expect).printResult();

        nums1 = new int[]{2};
        nums2 = new int[]{};
        expect =  2.00000;
        result = findMedianSortedArrays(nums1, nums2);
        Test.test(result, expect).printResult();
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = merge(nums1, nums2);

        double result = getMedianValue(list);

        return result;
    }

    private double getMedianValue(List<Integer> list) {
        if(list.size() % 2 == 1) {
            return (double)list.get(list.size()/2);
        } else {
            int a = list.get(list.size()/2-1);
            int b = list.get(list.size()/2);

            return ((double)(a+b) / 2.0);
        }
    }

    private List<Integer> merge(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();

        int index1 = 0;
        int index2 = 0;

        while(index1 < nums1.length || index2 < nums2.length) {
            if(index1 >= nums1.length) {
                list.add(nums2[index2]);
                index2++;
            } else if(index2 >= nums2.length) {
                list.add(nums1[index1]);
                index1++;
            } else {
                if(nums1[index1] > nums2[index2]) {
                    list.add(nums2[index2]);
                    index2++;
                } else {
                    list.add(nums1[index1]);
                    index1++;
                }
            }
        }

        return list;
    }
}
