import unittest
from typing import List


class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        return self.permutation(nums, [])

    def permutation(self, nums: List[int], comb: List[int]) -> List[List[int]]:
        if not nums:
            return [comb]

        result = []

        for i in range(len(nums)):
            tmp = nums[i]
            del nums[i]
            cur = self.permutation(nums, comb + [tmp])

            nums.insert(i, tmp)
            result += cur

        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [1,2,3]
        expect = [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        result = self.solution.permute(nums)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [0,1]
        expect = [[0,1],[1,0]]
        result = self.solution.permute(nums)
        self.assertEqual(expect, result)

    def test_case3(self):
        nums = [1]
        expect = [[1]]
        result = self.solution.permute(nums)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()
