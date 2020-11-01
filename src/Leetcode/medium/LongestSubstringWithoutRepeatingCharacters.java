package Leetcode.medium;

import Test.Test;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        new LongestSubstringWithoutRepeatingCharacters().test();
    }

    private void test() {
        String s;
        int expect, result;

        s = "abcabcbb";
        expect = 3;
        result = lengthOfLongestSubstring(s);
        Test.test(result, expect).printResult();

        s = "bbbbb";
        expect = 1;
        result = lengthOfLongestSubstring(s);
        Test.test(result, expect).printResult();

        s = "pwwkew";
        expect = 3;
        result = lengthOfLongestSubstring(s);
        Test.test(result, expect).printResult();

        s = "";
        expect = 0;
        result = lengthOfLongestSubstring(s);
        Test.test(result, expect).printResult();

        s = " ";
        expect = 1;
        result = lengthOfLongestSubstring(s);
        Test.test(result, expect).printResult();

        s = "a";
        expect = 1;
        result = lengthOfLongestSubstring(s);
        Test.test(result, expect).printResult();
    }

    public int lengthOfLongestSubstring(String s) {
        char[] arr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int start = 0;
        int max = 0;

        for(int i = 0; i < s.length(); i++) {
            char cur = arr[i];
            if(map.get(cur) == null) {
                map.put(cur, i);
            } else {
                int length = i - start;
                max = Math.max(length, max);
                i = map.get(cur);
                start = i+1;
                map = new HashMap<>();
            }
        }

        int length = s.length() - start;
        max = Math.max(length, max);

        return max;
    }
}
