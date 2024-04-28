import unittest
from typing import List


class Solution:
    def sortColors(self, nums: List[int]) -> None:
        nums.sort()


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [2,0,2,1,1,0]
        expect = [0,0,1,1,2,2]
        self.solution.sortColors(nums)
        self.assertEqual(expect, nums)

    def test_case2(self):
        nums = [2,0,1]
        expect = [0,1,2]
        self.solution.sortColors(nums)
        self.assertEqual(expect, nums)


if __name__ == '__main__':
    unittest.main()
