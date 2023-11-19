import unittest


class Solution(object):
    def winnerOfGame(self, colors):

        alice = list(filter(lambda color: len(color) > 2, colors.split("B")))
        bob = list(filter(lambda color: len(color) > 2, colors.split("A")))

        alice = sum(list(map(lambda color: len(color) - 2, alice)))
        bob = sum(list(map(lambda color: len(color) - 2, bob)))

        return True if alice > bob else False



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