package Leetcode.medium;

import Test.Test;

import java.util.*;

public class GenerateParentheses {
    public static void main(String[] args) {
        new GenerateParentheses().test();
    }

    private void test() {
        int n = 3;
        List<String> expect = Arrays.asList("((()))","(()())","(())()","()(())","()()()");
        List<String> result = generateParenthesis(n);
        Test.test(result, expect).printResult();

        n = 1;
        expect = Arrays.asList("()");
        result = generateParenthesis(n);
        Test.test(result, expect).printResult();
    }

    public List<String> generateParenthesis(int n) {
        String cur = "";
        Map<String, Integer> map = new HashMap<>();
        map.put("(", n);
        map.put(")", n);

        return dfs(map, cur);
    }

    private List<String> dfs(Map<String, Integer> map, String cur) {
        int openCount = map.get("(");
        int closeCount = map.get(")");

        List<String> result = new ArrayList<>();

        if(openCount > 0) {
            map.put("(", openCount-1);
            result.addAll(dfs(map, cur+"("));
            map.put("(", openCount);
        }
        if(closeCount > 0) {
            map.put(")", closeCount-1);
            result.addAll(dfs(map, cur + ")"));
            map.put(")", closeCount);
        }

        if(openCount == 0 && closeCount == 0 && isValidParenthesis(cur)) {
            result.add(cur);
        }

        return result;
    }

    private boolean isValidParenthesis(String s) {
        int result = 0;

        for (char c : s.toCharArray()) {
            if(c == '(') {
                result++;
            } else {
                result--;
                if(result < 0) {
                    return false;
                }
            }
        }
        return result == 0;
    }
}
