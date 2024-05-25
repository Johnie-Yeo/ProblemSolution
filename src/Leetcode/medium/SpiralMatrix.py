import unittest
from typing import List


class Solution:
    def spiralOrder(self, matrix: List[List[int]]) -> List[int]:
        move = [[0, 1], [1, 0], [0, -1], [-1, 0]]

        check = set()
        result = []

        point = (0, 0)
        moveIdx = 0

        while True:
            result.append(matrix[point[0]][point[1]])
            check.add(point)

            nextPoint = (point[0] + move[moveIdx][0], point[1] + move[moveIdx][1])
            cnt = 0
            while nextPoint in check or (nextPoint[0] < 0 or nextPoint[0] >= len(matrix) or nextPoint[1] < 0 or nextPoint[1] >= len(matrix[0])):
                moveIdx += 1
                moveIdx %= 4
                nextPoint = (point[0] + move[moveIdx][0], point[1] + move[moveIdx][1])
                cnt += 1
                if cnt > 4:
                    return result

            point = nextPoint


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        matrix = [[1,2,3],[4,5,6],[7,8,9]]
        expect = [1,2,3,6,9,8,7,4,5]
        result = self.solution.spiralOrder(matrix)
        self.assertEqual(expect, result)

    def test_case2(self):
        matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        expect = [1,2,3,4,8,12,11,10,9,5,6,7]
        result = self.solution.spiralOrder(matrix)
        self.assertEqual(expect, result)
