import unittest
from typing import List
import heapq


class Point:
    def __init__(self, x: int, y: int):
        self.x = x
        self.y = y

    def __lt__(self, other):
        if self.x ** 2 + self.y ** 2 < other.x ** 2 + other.y ** 2:
            return True
        else:
            return False


class Solution:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        heap = []
        for point in points:
            p = Point(point[0], point[1])
            heapq.heappush(heap, p)
        result = []
        for i in range(k):
            cur = heapq.heappop(heap)
            result.append([cur.x, cur.y])
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        points = [[1,3],[-2,2]]
        k = 1
        expect = [[-2,2]]
        result = self.solution.kClosest(points, k)
        self.assertEqual(expect, result)

    def test_case2(self):
        points = [[3,3],[5,-1],[-2,4]]
        k = 2
        expect = [[3,3],[-2,4]]
        result = self.solution.kClosest(points, k)
        self.assertEqual(expect, result)



if __name__ == '__main__':
    unittest.main()
