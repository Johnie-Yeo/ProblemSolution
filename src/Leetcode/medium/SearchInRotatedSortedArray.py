import unittest
from typing import List


class Solution:
    def search(self, nums: List[int], target: int) -> int:
        for i in range(len(nums)):
            if nums[i] == target:
                return i
        return -1


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [4,5,6,7,0,1,2]
        target = 0
        expect = 4
        result = self.solution.search(nums, target)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [4,5,6,7,0,1,2]
        target = 3
        expect = -1
        result = self.solution.search(nums, target)
        self.assertEqual(expect, result)

    def test_case3(self):
        nums = [1]
        target = 0
        expect = -1
        result = self.solution.search(nums, target)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()