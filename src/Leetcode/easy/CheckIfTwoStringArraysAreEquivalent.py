import unittest


class Solution(object):
    def arrayStringsAreEqual(self, word1, word2):
        return "".join(word1) == "".join(word2)


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        word1 = ["ab", "c"]
        word2 = ["a", "bc"]
        expect = True

        result = self.solution.arrayStringsAreEqual(word1, word2)
        self.assertEqual(result, expect)

    def test_case2(self):
        word1 = ["a", "cb"]
        word2 = ["ab", "c"]
        expect = False

        result = self.solution.arrayStringsAreEqual(word1, word2)
        self.assertEqual(result, expect)

    def test_case3(self):
        word1 = ["abc", "d", "defg"]
        word2 = ["abcddefg"]
        expect = True

        result = self.solution.arrayStringsAreEqual(word1, word2)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()