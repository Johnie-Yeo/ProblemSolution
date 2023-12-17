import unittest
from functools import reduce

class Solution(object):
    def knightDialer(self, n):
        nextPoints = {
            0: [4, 6],
            1: [6, 8],
            2: [7, 9],
            3: [4, 8],
            4: [0, 3, 9],
            5: [],
            6: [0, 1, 7],
            7: [2, 6],
            8: [1, 3],
            9: [2, 4]
        }

        points = {
            0: 1,
            1: 1,
            2: 1,
            3: 1,
            4: 1,
            5: 1,
            6: 1,
            7: 1,
            8: 1,
            9: 1,
        }

        for i in range(n-1):
            tmp = {}
            for point in points.keys():
                for nextPoint in nextPoints[point]:
                    count = tmp.get(nextPoint, 0)
                    tmp[nextPoint] = count + 1
            points = tmp

        return sum(points.values())


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        n = 1
        expect = 10

        result = self.solution.knightDialer(n)
        self.assertEqual(result, expect)

    def test_case2(self):
        n = 2
        expect = 20

        result = self.solution.knightDialer(n)
        self.assertEqual(result, expect)

    def test_case3(self):
        n = 3131
        expect = 136006598

        result = self.solution.knightDialer(n)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()