package Leetcode.medium;

import Test.Test;

import java.util.*;
import java.util.stream.Collectors;

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        new LetterCombinationsOfAPhoneNumber().test();
    }

    private void test() {
        String digits = "23";
        List<String> expect = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        List<String> result = letterCombinations(digits);
        Test.test(result, expect).printResult();

        digits = "";
        expect = Arrays.asList();
        result = letterCombinations(digits);
        Test.test(result, expect).printResult();

        digits = "2";
        expect = Arrays.asList("a","b","c");
        result = letterCombinations(digits);
        Test.test(result, expect).printResult();
    }

    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Integer, List<String>> map = phoneMap();
        List<Integer> digitList = Arrays.stream(digits.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Queue<Integer> queue = new LinkedList<>(digitList);
        List<String> result = getCombination(queue, map);

        return result;
    }

    private List<String> getCombination(Queue<Integer> queue, Map<Integer, List<String>> map) {
        int cur = queue.poll();
        List<String> list = map.get(cur);
        if(queue.isEmpty()) {
            return list;
        }

        List<String> result = new ArrayList<>();
        for (String e : list) {
            List<String> comb = getCombination(new LinkedList<>(queue), map).stream()
                    .map(a -> e + a)
                    .collect(Collectors.toList());
            result.addAll(comb);
        }
        return result;
    }

    private Map<Integer, List<String>> phoneMap() {
        Map<Integer, List<String>> map = new HashMap<>();
        map.put(2, Arrays.asList("a", "b", "c"));
        map.put(3, Arrays.asList("d", "e", "f"));
        map.put(4, Arrays.asList("g", "h", "i"));
        map.put(5, Arrays.asList("j", "k", "l"));
        map.put(6, Arrays.asList("m", "n", "o"));
        map.put(7, Arrays.asList("p", "q", "r", "s"));
        map.put(8, Arrays.asList("t", "u", "v"));
        map.put(9, Arrays.asList("w", "x", "y", "z"));
        return map;
    }
}
