import unittest


class Solution(object):
    def shortestPathBinaryMatrix(self, grid):
        queue = set()
        visited = set()
        start = (0,0)
        if grid[0][0] == 0:
            queue.add(start)
            visited.add(start)
        size = len(grid)

        result = 1

        if grid[0][0] == 0 and start[0] == size-1 and start[1] == size-1:
            return result

        while queue:
            nextPoints = set()
            result += 1
            for point in queue:
                x = point[0]
                y = point[1]

                if x - 1 >= 0 and grid[x-1][y] == 0 and (x-1, y) not in visited:
                    nextPoints.add((x-1, y))
                if x - 1 >= 0 and y + 1 < size and grid[x-1][y+1] == 0 and (x-1, y+1)not in visited:
                    nextPoints.add((x-1, y+1))
                if y + 1 < size and grid[x][y+1] == 0 and (x, y+1) not in visited:
                    nextPoints.add((x, y+1))
                if x + 1 < size and y + 1 < size and grid[x+1][y+1] == 0 and (x+1, y+1) not in visited:
                    nextPoints.add((x+1, y+1))
                if x + 1 < size and grid[x+1][y] == 0 and (x+1, y) not in visited:
                    nextPoints.add((x+1, y))
                if x + 1 < size and y - 1 >= 0 and grid[x+1][y-1] == 0 and (x+1, y-1) not in visited:
                    nextPoints.add((x+1, y-1))
                if y - 1 >= 0 and grid[x][y-1] == 0 and (x, y-1) not in visited:
                    nextPoints.add((x, y-1))
                if x - 1 >= 0 and y - 1 >= 0 and grid[x-1][y-1] == 0 and (x-1, y-1) not in visited:
                    nextPoints.add((x-1, y-1))

            for point in nextPoints:
                visited.add(point)
                if point[0] == size-1 and point[1] == size-1:
                    return result

            queue = nextPoints

        return -1


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        grid = [[0,1],[1,0]]
        expect = 2
        result = self.solution.shortestPathBinaryMatrix(grid)
        self.assertEqual(expect, result)

    def test_case2(self):
        grid = [[0,0,0],[1,1,0],[1,1,0]]
        expect = 4
        result = self.solution.shortestPathBinaryMatrix(grid)
        self.assertEqual(expect, result)

    def test_case3(self):
        grid = [[0,0,0],[1,0,0],[1,1,0]]
        expect = 3
        result = self.solution.shortestPathBinaryMatrix(grid)
        self.assertEqual(expect, result)

    def test_case4(self):
        grid = [[0]]
        expect = 1
        result = self.solution.shortestPathBinaryMatrix(grid)
        self.assertEqual(expect, result)

    def test_case5(self):
        grid = [
            [0,1,1,0,0,0],
            [0,1,0,1,1,0],
            [0,1,1,0,1,0],
            [0,0,0,1,1,0],
            [1,1,1,1,1,0],
            [1,1,1,1,1,0]
        ]
        expect = 14
        result = self.solution.shortestPathBinaryMatrix(grid)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()