import unittest
import re


class Solution:
    def myAtoi(self, s: str) -> int:
        maxValue = 2147483647
        minValue = -2147483648
        s = s.strip()
        isPlus = False if s and s[0] == '-' else True
        s = re.sub(r'^[\+\-]', '', s)
        result = "0"
        for c in s:
            if '0' <= c <= '9':
                result += c
            else:
                break

        result = int(result) if isPlus else int(result) * -1

        if result > maxValue:
            return maxValue
        elif result < minValue:
            return minValue
        else:
            return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "42"
        expect = 42
        result = self.solution.myAtoi(s)
        self.assertEqual(expect, result)

    def test_case2(self):
        s = " -042"
        expect = -42
        result = self.solution.myAtoi(s)
        self.assertEqual(expect, result)

    def test_case3(self):
        s = "1337c0d3"
        expect = 1337
        result = self.solution.myAtoi(s)
        self.assertEqual(expect, result)

    def test_case4(self):
        s = "0-1"
        expect = 0
        result = self.solution.myAtoi(s)
        self.assertEqual(expect, result)

    def test_case5(self):
        s = "words and 987"
        expect = 0
        result = self.solution.myAtoi(s)
        self.assertEqual(expect, result)

    def test_case6(self):
        s = "-91283472332"
        expect = -2147483648
        result = self.solution.myAtoi(s)
        self.assertEqual(expect, result)

    def test_case7(self):
        s = "   +0 123"
        expect = 0
        result = self.solution.myAtoi(s)
        self.assertEqual(expect, result)

    def test_case9(self):
        s = "   -042"
        expect = -42
        result = self.solution.myAtoi(s)
        self.assertEqual(expect, result)