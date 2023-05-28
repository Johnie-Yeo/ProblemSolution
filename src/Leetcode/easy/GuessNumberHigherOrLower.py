import unittest

class Solution(object):
    def guessNumber(self, n):
        mid = n // 2
        start = 0
        end = n
        val = guess(mid)
        while val != 0:
            if val > 0:
                start = mid+1
            elif val < 0:
                end = mid-1
            mid = (start+end) // 2
            val = guess(mid)
        return mid


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        n = 10
        pick = 6
        result = self.solution.guessNumber(n)
        expect = pick
        self.assertEqual(expect, result)

    def test_case2(self):
        n = 1
        pick = 1
        result = self.solution.guessNumber(n)
        expect = pick
        self.assertEqual(expect, result)

    def test_case3(self):
        n = 2
        pick = 1
        result = self.solution.guessNumber(n)
        expect = pick
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()