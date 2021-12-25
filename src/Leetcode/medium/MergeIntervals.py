import unittest
from typing import List


class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals.sort(key=lambda elem: elem[0])
        result = []
        cur = intervals.pop(0)
        for interval in intervals:
            if cur[1] >= interval[0]:
                if cur[1] < interval[1]:
                    cur[1] = interval[1]
            else:
                result.append(cur)
                cur = interval
        result.append(cur)
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]
        expect = [[1, 6], [8, 10], [15, 18]]
        result = self.solution.merge(intervals)
        self.assertEqual(expect, result)

    def test_case2(self):
        intervals = [[1, 4], [4, 5]]
        expect = [[1, 5]]
        result = self.solution.merge(intervals)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()
