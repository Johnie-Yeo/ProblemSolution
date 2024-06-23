import unittest


class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        maze = [[0] * n for i in range(m)]

        for i in range(m):
            for j in range(n):
                if i == 0 or j == 0:
                    maze[i][j] = 1
                else:
                    maze[i][j] = maze[i-1][j] + maze[i][j-1]

        return maze[m-1][n-1]


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        m = 3
        n = 7
        expect = 28
        result = self.solution.uniquePaths(m, n)
        self.assertEqual(expect, result)
