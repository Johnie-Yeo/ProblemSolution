import unittest
from typing import List, Set


class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        return self.trackSubset(nums, 0, [])

    def trackSubset(self, nums, idx, subset) -> List[List[int]]:
        if idx >= len(nums):
            return [list(subset)]

        result = []
        result += self.trackSubset(nums, idx+1, subset)
        subset.append(nums[idx])
        result += self.trackSubset(nums, idx+1, subset)
        subset.pop()

        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [1,2,3]
        expect = [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
        result = self.solution.subsets(nums)
        self.assertEqual(self.convert_to_set(expect), self.convert_to_set(result))

    def convert_to_set(self, targetList: List[List[int]]) -> Set[str]:
        result = []
        for target in targetList:
            result.append(",".join(list(map(lambda e: str(e), target))))
        return set(result)
