import unittest


class Solution:
    def longestValidParentheses(self, s: str) -> int:
        openStack = []
        check = [False] * (len(s)+1)

        for i in range(len(s)):
            p = s[i]
            if p == "(":
                openStack.append(i)
            else:
                if openStack:
                    check[i] = True
                    check[openStack.pop()] = True

        maxLen = 0
        start = 0
        for i in range(len(check)):
            if not check[i]:
                maxLen = max(maxLen, i - start)
                start = i+1

        return maxLen


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "(()"
        expect = 2
        result = self.solution.longestValidParentheses(s)
        self.assertEqual(expect, result)

    def test_case2(self):
        s = ")()())"
        expect = 4
        result = self.solution.longestValidParentheses(s)
        self.assertEqual(expect, result)

    def test_case3(self):
        s = ""
        expect = 0
        result = self.solution.longestValidParentheses(s)
        self.assertEqual(expect, result)

    def test_case4(self):
        s = "()(()"
        expect = 2
        result = self.solution.longestValidParentheses(s)
        self.assertEqual(expect, result)