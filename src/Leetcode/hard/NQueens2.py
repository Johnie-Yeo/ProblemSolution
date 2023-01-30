import unittest


class Solution(object):
    def totalNQueens(self, n):
        queens = []
        for i in range(n):
            queens.append([i, 0])

        count = 0

        while queens is not None:
            if self.is_feasible(queens, n):
                count += 1
            queens = self.move_queens(queens, n, n-1)
        return count

    def is_feasible(self, queens, n):
        for i in range(n):
            for j in range(i+1, n):
                if self.is_attachable(queens[i], queens[j]):
                    return False
        return True

    def is_attachable(self, point1, point2):
        return (
               point1[1] == point2[1] \
               or \
               point1[0] == point2[0] \
               or \
               abs(point1[0] - point2[0]) == abs(point1[1] - point2[1])
        )

    def move_queens(self, queens, n, idx):
        if idx < 0:
            return None

        queen = self.get_next_point(queens[idx], n)

        if queen is None:
            return self.move_queens(queens, n, idx-1)
        else:
            queens[idx] = queen
            while idx + 1 < n:
                queens[idx+1][1] = 0
                idx += 1
            return queens

    def get_next_point(self, point, n):
        next_point = [point[0], point[1]]
        next_point[1] += 1
        if next_point[1] >= n:
            return None
        return next_point


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        n = 4
        expect = 2
        result = self.solution.totalNQueens(n)
        self.assertEqual(expect, result)

    def test_case2(self):
        n = 1
        expect = 1
        result = self.solution.totalNQueens(n)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()