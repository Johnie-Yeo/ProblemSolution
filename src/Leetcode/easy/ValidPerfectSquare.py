import unittest


class Solution(object):
    def isPerfectSquare(self, num):
        length = num ** 0.5
        return length == int(length)


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        num = 16
        result = self.solution.isPerfectSquare(num)
        expect = True
        self.assertEqual(expect, result)

    def test_case2(self):
        num = 14
        result = self.solution.isPerfectSquare(num)
        expect = False
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()