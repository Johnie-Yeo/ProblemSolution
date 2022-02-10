import unittest
from typing import List


class Solution:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        result = []
        current = []
        index = 0
        candidates.sort()
        self.getResult(candidates, index, target, current, result)
        return result

    def getResult(self, candidates, index, target, current, result):
        if target == 0:
            result.append(current.copy())
            return

        for idx in range(index, len(candidates)):
            candidate = candidates[idx]
            if candidate <= target:
                current.append(candidate)
                self.getResult(candidates, idx, target-candidate, current, result)
                del current[-1]


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        candidates = [2,3,6,7]
        target = 7
        expect = [[2,2,3],[7]]
        result = self.solution.combinationSum(candidates, target);
        self.assertEqual(expect, result)

    def test_case2(self):
        candidates = [2,3,5]
        target = 8
        expect = [[2,2,2,2],[2,3,3],[3,5]]
        result = self.solution.combinationSum(candidates, target);
        self.assertEqual(expect, result)

    def test_case3(self):
        candidates = [2]
        target = 1
        expect = []
        result = self.solution.combinationSum(candidates, target);
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()
