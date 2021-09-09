import unittest
from functools import reduce
from typing import List


class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        shortest_word = reduce(lambda acc, cur: acc if len(acc) < len(cur) else cur, strs)
        result = ""
        for index, c in enumerate(list(shortest_word)):
            if reduce(lambda acc, cur: acc and (cur[index] == c), strs, True):
                result += c
            else:
                break
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        test_input = ["flower", "flow", "flight"]
        expect = "fl"
        result = self.solution.longestCommonPrefix(test_input)
        self.assertEqual(result, expect)

    def test_case2(self):
        test_input = ["dog", "racecar", "car"]
        expect = ""
        result = self.solution.longestCommonPrefix(test_input)
        self.assertEqual(result, expect)

    def test_case3(self):
        test_input = ["catdog", "cat"]
        expect = "cat"
        result = self.solution.longestCommonPrefix(test_input)
        self.assertEqual(result, expect)

    def test_case4(self):
        test_input = ["cat"]
        expect = "cat"
        result = self.solution.longestCommonPrefix(test_input)
        self.assertEqual(result, expect)

    def test_case5(self):
        test_input = ["cir","car"]
        expect = "c"
        result = self.solution.longestCommonPrefix(test_input)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()
