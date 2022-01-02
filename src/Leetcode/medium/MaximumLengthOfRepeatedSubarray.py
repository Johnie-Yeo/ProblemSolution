import unittest
from typing import List


class Solution:
    def findLength(self, nums1: List[int], nums2: List[int]) -> int:

        if len(nums1) < len(nums2):
            nums1, nums2 = nums2, nums1

        cur = [nums1.pop()]
        result = 0
        index = 0
        while len(cur) > 0:
            duplicatedLength = self.getDuplicatedLength(index, cur, nums2)
            result = max(duplicatedLength, result)

            if len(cur) <= len(nums2) and len(nums1) > 0:
                cur = [nums1.pop(), *cur]
                if len(cur) > len(nums2):
                    cur.pop()
                continue
            if len(nums1) == 0:
                cur.pop()
                index += 1

        return result

    def getDuplicatedLength(self, index,  nums1, nums2) -> int:
        length = 0
        cur = 0
        for i in range(index, index+len(nums1)):
            if nums1[i-index] == nums2[i]:
                cur += 1
            else:
                length = max(length, cur)
                cur = 0
        return max(length, cur)



class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums1 = [1,2,3,2,1]
        nums2 = [3,2,1,4,7]
        expect = 3
        result = self.solution.findLength(nums1, nums2)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums1 = [0,0,0,0,0]
        nums2 = [0,0,0,0,0]
        expect = 5
        result = self.solution.findLength(nums1, nums2)
        self.assertEqual(expect, result)

    def test_case3(self):
        nums1 = [0,0,0,0,1]
        nums2 = [1,0,0,0,0]
        expect = 4
        result = self.solution.findLength(nums1, nums2)
        self.assertEqual(expect, result)

    def test_case4(self):
        nums1 = [1,2,3,2,1,4]
        nums2 = [3,2,1,4,7]
        expect = 4
        result = self.solution.findLength(nums1, nums2)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()
