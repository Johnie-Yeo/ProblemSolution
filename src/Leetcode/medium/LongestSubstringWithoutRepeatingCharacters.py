import unittest


class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        count = {}
        start = 0
        index = 0
        result = min(len(s), 1)
        for c in s:
            result = max(result, index - start)
            if count.get(c, None) is not None:
                start = max(start, count[c]+1)
            count[c] = index
            index = index+1
        result = max(result, index - start)
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "abcabcbb"
        expect = 3
        result = self.solution.lengthOfLongestSubstring(s)
        self.assertEqual(expect, result)

    def test_case2(self):
        s = "bbbbb"
        expect = 1
        result = self.solution.lengthOfLongestSubstring(s)
        self.assertEqual(expect, result)

    def test_case3(self):
        s = "pwwkew"
        expect = 3
        result = self.solution.lengthOfLongestSubstring(s)
        self.assertEqual(expect, result)

    def test_case_4(self):
        s = " "
        expect = 1
        result = self.solution.lengthOfLongestSubstring(s)
        self.assertEqual(expect, result)

    def test_case_5(self):
        s = ""
        expect = 0
        result = self.solution.lengthOfLongestSubstring(s)
        self.assertEqual(expect, result)

    def test_case_6(self):
        s = "a"
        expect = 1
        result = self.solution.lengthOfLongestSubstring(s)
        self.assertEqual(expect, result)

    def test_case7(self):
        s = "au"
        expect = 2
        result = self.solution.lengthOfLongestSubstring(s)
        self.assertEqual(expect, result)


    def test_case8(self):
        s = "abba"
        expect = 2
        result = self.solution.lengthOfLongestSubstring(s)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()
