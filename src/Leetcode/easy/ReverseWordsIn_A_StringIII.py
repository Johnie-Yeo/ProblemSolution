import unittest


class Solution(object):
    def reverseWords(self, s):
        return ' '.join(list(map(lambda word: word[::-1], s.split(" "))))


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "Let's take LeetCode contest"
        expect = "s'teL ekat edoCteeL tsetnoc"

        result = self.solution.reverseWords(s)
        self.assertEqual(result, expect)

    def test_case2(self):
        s = "God Ding"
        expect = "doG gniD"

        result = self.solution.reverseWords(s)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()