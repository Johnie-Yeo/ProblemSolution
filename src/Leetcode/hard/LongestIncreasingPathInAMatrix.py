import unittest
from typing import List, Dict, Tuple


class Solution:
    def longestIncreasingPath(self, grid: List[List[int]]) -> int:
        result = 0

        memo = {}

        for i in range(len(grid)):
            for j in range(len(grid[0])):
                result = max(result, self.getIncresingPath(grid, (i, j), memo))

        return result

    def getIncresingPath(self, grid: List[List[int]], startPoint: Tuple, memo: Dict[tuple, int]) -> int:

        if startPoint in memo:
            return memo[startPoint]

        dirs = [[1, 0], [0, 1], [-1, 0], [0, -1]]

        result = 1

        for d in dirs:
            nextPoint = (startPoint[0] + d[0], startPoint[1] + d[1])
            if nextPoint[0] < 0 or nextPoint[0] >= len(grid) or nextPoint[1] < 0 or nextPoint[1] >= len(grid[0]):
                continue
            elif grid[nextPoint[0]][nextPoint[1]] > grid[startPoint[0]][startPoint[1]]:
                result = max(result, self.getIncresingPath(grid, nextPoint, memo) + 1)

        memo[startPoint] = result

        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        matrix = [[9,9,4],[6,6,8],[2,1,1]]
        expect = 4
        result = self.solution.longestIncreasingPath(matrix)
        self.assertEqual(expect, result)