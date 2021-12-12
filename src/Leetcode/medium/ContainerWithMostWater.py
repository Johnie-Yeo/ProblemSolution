import unittest
from typing import List


class Solution:
    def maxArea(self, height: List[int]) -> int:
        size = len(height)

        maxArea = 0
        leftIndex = 0
        rightIndex = size-1

        while leftIndex is not None and rightIndex is not None and leftIndex < rightIndex:
            area = (rightIndex - leftIndex) * min(height[leftIndex], height[rightIndex])
            maxArea = max(maxArea, area)
            if height[leftIndex] < height[rightIndex]:
                leftIndex = self.moveToNextHigher(height, leftIndex, 1)
            else:
                rightIndex = self.moveToNextHigher(height, rightIndex, -1)

        return maxArea

    def moveToNextHigher(self, height, index, p) -> int:
        cur = height[index]
        index += p
        while 0 <= index < len(height):
            if height[index] > cur:
                return index
            index += p


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
        expect = 49
        result = self.solution.maxArea(height)
        self.assertEqual(result, expect)

    def test_case2(self):
        height = [1, 1]
        expect = 1
        result = self.solution.maxArea(height)
        self.assertEqual(result, expect)

    def test_case3(self):
        height = [4, 3, 2, 1, 4]
        expect = 16
        result = self.solution.maxArea(height)
        self.assertEqual(result, expect)

    def test_case4(self):
        height = [1, 2, 1]
        expect = 2
        result = self.solution.maxArea(height)
        self.assertEqual(result, expect)

    def test_case5(self):
        height = [1,8,6,2,5,4,8,25,7]
        expect = 49
        result = self.solution.maxArea(height)
        self.assertEqual(result, expect)

    def test_case6(self):
        height = [1,8,6,100,2,100,8,25,7]
        expect = 200
        result = self.solution.maxArea(height)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()
