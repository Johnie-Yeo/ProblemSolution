from typing import List
import unittest


class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        sumVal = sum(nums)
        if sumVal % 2 == 1:
            return False

        targetValue = sumVal / 2
        comb = set()
        for num in nums:
            for e in list(comb):
                comb.add(e+num)
            comb.add(num)

        return targetValue in comb


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [1, 5, 11, 5]
        expect = True
        result = self.solution.canPartition(nums)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [1, 2, 3, 5]
        expect = False
        result = self.solution.canPartition(nums)
        self.assertEqual(expect, result)

    def test_case3(self):
        nums = [2, 2, 3, 5]
        expect = False
        result = self.solution.canPartition(nums)
        self.assertEqual(expect, result)

    def test_case4(self):
        nums = [3,3,3,4,5]
        expect = True
        result = self.solution.canPartition(nums)
        self.assertEqual(expect, result)

    def test_case5(self):
        nums = [1,5,11,3]
        expect = False
        result = self.solution.canPartition(nums)
        self.assertEqual(expect, result)
