import unittest


class Solution(object):
    def shiftGrid(self, grid, k):
        for i in range(k):
            self.shift(grid)
        return grid

    def shift(self, grid):
        m = len(grid)
        n = len(grid[0])

        for i in range(m):
            prev = grid[i][n-1]
            for j in range(n):
                tmp = grid[i][j]
                grid[i][j] = prev
                prev = tmp

        prev = grid[m-1][0]
        for i in range(m):
            tmp = grid[i][0]
            grid[i][0] = prev
            prev = tmp


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        grid = [[1,2,3],[4,5,6],[7,8,9]]
        k = 1
        expect = [[9,1,2],[3,4,5],[6,7,8]]
        result = self.solution.shiftGrid(grid, k)
        self.assertEqual(expect, result)

    def test_case2(self):
        grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]]
        k = 4
        expect = [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
        result = self.solution.shiftGrid(grid, k)
        self.assertEqual(expect, result)

    def test_case3(self):
        grid = [[1,2,3],[4,5,6],[7,8,9]]
        k = 9
        expect = [[1,2,3],[4,5,6],[7,8,9]]
        result = self.solution.shiftGrid(grid, k)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()