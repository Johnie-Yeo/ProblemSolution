import unittest


class Solution(object):
    def winnerOfGame(self, colors):
        """
        :type colors: str
        :rtype: bool
        """


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        colors = "AAABABB"
        expect = True

        result = self.solution.winnerOfGame(colors)
        self.assertEqual(result, expect)

    def test_case2(self):
        colors = "AA"
        expect = False

        result = self.solution.winnerOfGame(colors)
        self.assertEqual(result, expect)

    def test_case3(self):
        colors = "ABBBBBBBAAA"
        expect = False

        result = self.solution.winnerOfGame(colors)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()