import unittest


class Solution(object):
    def validPalindrome(self, s):
        left = 0
        right = len(s)-1
        while left <= right:
            if s[left] != s[right]:
                s1 = s[:left] + s[left+1:]
                s2 = s[:right] + s[right+1:]
                return s1 == s1[::-1] or s2 == s2[::-1]
            left += 1
            right -= 1
        return True


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "aba"
        expect = True
        result = self.solution.validPalindrome(s)
        self.assertEqual(expect, result)

    def test_case2(self):
        s = "abca"
        expect = True
        result = self.solution.validPalindrome(s)
        self.assertEqual(expect, result)

    def test_case3(self):
        s = "abc"
        expect = False
        result = self.solution.validPalindrome(s)
        self.assertEqual(expect, result)

if __name__ == '__main__':
    unittest.main()