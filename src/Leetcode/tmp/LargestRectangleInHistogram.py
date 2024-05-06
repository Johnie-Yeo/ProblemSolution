# Failed!

import unittest
from typing import List


class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:

        i = 0
        largestRectangle = heights[i]

        while i < len(heights):
            start = i

            prev = heights[start]
            rectangle = heights[i]
            largestRectangle = max(largestRectangle, rectangle)

            if prev == 0:
                i += 1
                continue

            for j in range(len(heights) - start - 1):
                cur = j + start + 1

                if prev < heights[cur]:
                    rectangle += prev
                else:
                    i += 1
                    prev = heights[cur]
                    rectangle = (cur - start + 1) * heights[cur]

                largestRectangle = max(largestRectangle, rectangle)

            i += 1

        return largestRectangle


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        heights = [2,1,5,6,2,3]
        expect = 10
        result = self.solution.largestRectangleArea(heights)
        self.assertEqual(expect, result)

    def test_case2(self):
        heights = [2, 4]
        expect = 4
        result = self.solution.largestRectangleArea(heights)
        self.assertEqual(expect, result)

    def test_case3(self):
        heights = [0, 2, 0]
        expect = 2
        result = self.solution.largestRectangleArea(heights)
        self.assertEqual(expect, result)

    def test_case3(self):
        heights = [2,1,4,5,1,3,3]
        expect = 8
        result = self.solution.largestRectangleArea(heights)
        self.assertEqual(expect, result)