import unittest
from typing import List


class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        expressions = self.splitExpression(p)
        index = 0
        for i in range(len(expressions)):
            expression = expressions[i]
            cur = expression[0]
            if "*" in expression:
                while index < len(s):
                    if cur == '.' or cur == s[index]:
                        index = index+1
                    else:
                        break
            else:
                if cur == '.' or cur == s[index]:
                    index = index+1

            if index >= len(s):
                if len(list(filter(lambda exp: '*' not in exp, expressions[i+1:len(expressions)]))) > 0:
                    return False
                return True

        return False

    def splitExpression(self, p) -> List[str]:
        result = []
        cur = ''
        for exp in p:
            if exp.isalpha() or exp == '.':
                if len(cur) > 0:
                    result.append(cur)
                cur = exp
            else:
                cur += exp
        result.append(cur)
        return result

class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "aa"
        p = "a"
        expect = False
        result = self.solution.isMatch(s, p)
        self.assertEqual(expect, result)

    def test_case2(self):
        s = "aa"
        p = "a*"
        expect = True
        result = self.solution.isMatch(s, p)
        self.assertEqual(expect, result)

    def test_case3(self):
        s = "ab"
        p = ".*"
        expect = True
        result = self.solution.isMatch(s, p)
        self.assertEqual(expect, result)

    def test_case4(self):
        s = "aab"
        p = "c*a*b"
        expect = True
        result = self.solution.isMatch(s, p)
        self.assertEqual(expect, result)

    def test_case5(self):
        s = "mississippi"
        p = "mis*is*p*."
        expect = False
        result = self.solution.isMatch(s, p)
        self.assertEqual(expect, result)

    def test_case6(self):
        s = "mississippi"
        p = "mis*is*ip*."
        expect = True
        result = self.solution.isMatch(s, p)
        self.assertEqual(expect, result)

    def test_case6(self):
        s = "ab"
        p = ".*c"
        expect = False
        result = self.solution.isMatch(s, p)
        self.assertEqual(expect, result)