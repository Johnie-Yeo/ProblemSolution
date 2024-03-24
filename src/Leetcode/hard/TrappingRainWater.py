import unittest
from typing import List


class Solution:
    def trap(self, height: List[int]) -> int:
        left = []
        right = []

        for i in range(len(height)):
            if i > 1:
                leftTmp = max(height[i-1], left[i-1])
                rightTmp = max(height[-i], right[0])
            elif i == 1:
                leftTmp = height[i-1]
                rightTmp = height[-i]
            elif i == 0:
                leftTmp = 0
                rightTmp = 0
            left.append(leftTmp)
            right.insert(0, rightTmp)

        result = []
        for i in range(len(height)):
            cur = max(min(left[i], right[i]) - height[i], 0)
            result.append(cur)

        return sum(result)


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        height = [0,1,0,2,1,0,1,3,2,1,2,1]
        expect = 6
        result = self.solution.trap(height)
        self.assertEqual(expect, result)

    def test_case2(self):
        height = [4,2,0,3,2,5]
        expect = 9
        result = self.solution.trap(height)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()