import unittest
from typing import List
from queue import PriorityQueue


class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        result = []
        cnt = {}
        que = PriorityQueue()

        start = 0
        end = k
        curList = nums[start:end - 1]
        for cur in curList:
            if cur in cnt:
                cnt[cur] = cnt[cur] + 1
            else:
                cnt[cur] = 1
                que.put(cur * -1)

        for i in range(len(nums) - k + 1):
            start = i
            end = i+k - 1
            cur = nums[end]

            if cur in cnt:
                cnt[cur] = cnt[cur] + 1
            else:
                cnt[cur] = 1
                que.put(cur * -1)

            target = que.get() * -1
            while target not in cnt:
                target = que.get() * -1
            result.append(target)
            que.put(target * -1)

            if cnt[nums[start]] > 1:
                cnt[nums[start]] = cnt[nums[start]] - 1
            else:
                del cnt[nums[start]]

        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [1,3,-1,-3,5,3,6,7]
        k = 3
        expect = [3,3,5,5,6,7]
        result = self.solution.maxSlidingWindow(nums, k)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [1]
        k = 1
        expect = [1]
        result = self.solution.maxSlidingWindow(nums, k)
        self.assertEqual(expect, result)
