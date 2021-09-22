import unittest
from typing import List, Tuple


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        negatives = sorted(list(filter(lambda num: num < 0, nums)))
        positives = sorted(list(filter(lambda num: num > 0, nums)))
        zeros = list(filter(lambda num: num == 0, nums))

        result = []
        # 2 n, 1 p, 0 z
        negativeMap = self.getSumMap(negatives)
        positiveSet = set(positives)
        for cur in positiveSet:
            for tup in negativeMap.get(-cur, []):
                result.append([*tup, cur])
        # 1 n, 2 p, 0 z
        positiveMap = self.getSumMap(positives)
        negativeSet = set(negatives)
        for cur in negativeSet:
            for tup in positiveMap.get(-cur, []):
                result.append([cur, *tup])
        # 1 n, 1 p, 1 z
        if len(zeros) > 0:
            for cur in positiveSet:
                if -cur in negativeSet:
                    result.append([-cur, 0, cur])
        # 0 n, 0 p, 3 z
        if len(zeros) >= 3:
            result.append([0, 0, 0])

        return result

    def getSumMap(self, arr):
        result = {}
        for i in range(len(arr)):
            for j in range(i+1, len(arr)):
                key = arr[i] + arr[j]
                value = result.get(key, set())
                value.add((arr[i], arr[j]))
                result[key] = value

        return result


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