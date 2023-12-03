import unittest


class Solution(object):
    def removeDuplicateLetters(self, s):
        charDict = dict()
        for i in range(len(s)):
            charDict[s[i]] = charDict.get(s[i], [])
            charDict[s[i]].append(i)
        sortedDict = dict(sorted(charDict.items()))




class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "bcabc"
        expect = "abc"

        result = self.solution.removeDuplicateLetters(s)
        self.assertEqual(result, expect)

    def test_case2(self):
        s = "cbacdcbc"
        expect = "acdb"

        result = self.solution.removeDuplicateLetters(s)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()