import unittest
from typing import List, Tuple


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        nums.sort()
        result = set()
        for index in range(len(nums)):
            if nums[index] > 0:
                break

            i = index+1
            j = len(nums)-1
            while i < j:
                threeSum = nums[index] + nums[i] + nums[j]
                if threeSum == 0:
                    result.add((nums[index], nums[i], nums[j]))
                    i += 1
                    j -= 1
                elif threeSum < 0:
                    i += 1
                elif threeSum > 0:
                    j -= 1

        return list(map(lambda tup: list(tup), result))


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [-1,0,1,2,-1,-4]
        expect = [[-1,-1,2],[-1,0,1]]
        result = self.solution.threeSum(nums)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = []
        expect = []
        result = self.solution.threeSum(nums)
        self.assertEqual(expect, result)

    def test_case3(self):
        nums = [0]
        expect = []
        result = self.solution.threeSum(nums)
        self.assertEqual(expect, result)

    def test_case4(self):
        nums = [0, 0, 0, 0]
        expect = [[0, 0, 0]]
        result = self.solution.threeSum(nums)
        self.assertEqual(expect, result)

    def test_case5(self):
        nums = [3,0,-2,-1,1,2]
        expect = [[-2,-1,3],[-2,0,2],[-1,0,1]]
        result = self.solution.threeSum(nums)
        self.assertEqual(expect, result)