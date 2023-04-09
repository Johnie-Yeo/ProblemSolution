import unittest


class Solution(object):
    def arrangeCoins(self, n):
        c = 1
        while n > 0:
            n -= c
            c += 1
        return c - 1 if n == 0 else c - 2


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        n = 5
        expect = 2
        result = self.solution.arrangeCoins(n)
        self.assertEqual(expect, result)

    def test_case2(self):
        n = 8
        expect = 3
        result = self.solution.arrangeCoins(n)
        self.assertEqual(expect, result)

    def test_case3(self):
        n = 1
        expect = 1
        result = self.solution.arrangeCoins(n)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()