import unittest
from typing import List


class Solution(object):
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        result = []

        tmpStart = None
        tmpEnd = None
        isInserted = False

        if not intervals:
            return [newInterval]

        for interval in intervals:
            if interval[1] < newInterval[0]:
                result.append(interval)
            if interval[0] > newInterval[1]:
                if isInserted is False:
                    if tmpStart is None:
                        tmpStart = newInterval[0]
                        tmpEnd = newInterval[1]
                    isInserted = True
                    result.append([tmpStart, tmpEnd])
                result.append(interval)
            elif interval[1] >= newInterval[0] and tmpStart is None:
                tmpStart = min(interval[0], newInterval[0])
                tmpEnd = max(interval[1], newInterval[1])
            elif tmpStart is not None:
                tmpEnd = max(interval[1], newInterval[1])

        if tmpStart is not None and isInserted is False:
            result.append([tmpStart, tmpEnd])

        if tmpStart is None:
            result.append(newInterval)

        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        intervals = [[1, 3], [6, 9]]
        newInterval = [2, 5]
        expect = [[1, 5], [6, 9]]

        result = self.solution.insert(intervals, newInterval)
        self.assertEqual(result, expect)

    def test_case2(self):
        intervals = [[1, 2], [3, 5], [6, 7], [8, 10], [12, 16]]
        newInterval = [4, 8]
        expect = [[1, 2], [3, 10], [12, 16]]

        result = self.solution.insert(intervals, newInterval)
        self.assertEqual(result, expect)

    def test_case3(self):
        intervals = []
        newInterval = [5, 7]
        expect = [[5, 7]]

        result = self.solution.insert(intervals, newInterval)
        self.assertEqual(result, expect)

    def test_case4(self):
        intervals = [[1, 5]]
        newInterval = [2, 3]
        expect = [[1, 5]]

        result = self.solution.insert(intervals, newInterval)
        self.assertEqual(result, expect)

    def test_case5(self):
        intervals = [[1, 5]]
        newInterval = [6, 8]
        expect = [[1, 5], [6, 8]]

        result = self.solution.insert(intervals, newInterval)
        self.assertEqual(result, expect)

    def test_case6(self):
        intervals = [[1, 5]]
        newInterval = [0, 0]
        expect = [[0, 0],[1, 5]]

        result = self.solution.insert(intervals, newInterval)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()
