# Failed Time Exceed!

from typing import List, Set
import unittest


class Solution:
    def canPartition(self, nums: List[int]) -> bool:
        totalSum = sum(nums)

        if totalSum % 2 == 1:
            return False

        target = totalSum / 2
        comb = set()
        if nums:
            comb.add(nums[0])
        comb = self.getComb(nums, 1, comb, target)

        return target in comb


    def getComb(self, nums: List[int], idx: int, comb: Set[int], target: int) -> Set[int]:
        if idx >= len(nums):
            return comb

        curTarget = list(comb)
        curComb = set(curTarget)
        result = set()
        for num in curTarget:
            if nums[idx] <= target:
                curComb.add(nums[idx])
            if num + nums[idx] <= target:
                curComb.add(num + nums[idx])
            result.update(self.getComb(nums, idx+1, curComb, target))
        return result




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
