import unittest
from typing import List


class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        prefix = nums.copy()
        suffix = list(reversed(nums.copy()))

        prev = 1
        for idx in range(len(prefix)):
            prefix[idx] = prefix[idx] * prev
            prev = prefix[idx]

        prev = 1
        for idx in range(len(suffix)):
            suffix[idx] = suffix[idx] * prev
            prev = suffix[idx]
        suffix = list(reversed(suffix))

        result = []
        for i in range(len(nums)):
            if i == 0:
                result.append(suffix[i+1])
            elif i == len(nums) - 1:
                result.append(prefix[i-1])
            else:
                result.append(prefix[i-1] * suffix[i+1])
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [1,2,3,4]
        expect = [24,12,8,6]
        result = self.solution.productExceptSelf(nums)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [-1,1,0,-3,3]
        expect = [0,0,9,0,0]
        result = self.solution.productExceptSelf(nums)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()