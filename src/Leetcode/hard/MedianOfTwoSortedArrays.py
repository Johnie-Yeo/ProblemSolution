import unittest
from typing import List


class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        while len(nums1) + len(nums2) > 2:
            if (nums1 and nums2 and nums1[0] < nums2[0]) or not nums2:
                del nums1[0]
            else:
                del nums2[0]
            if (nums1 and nums2 and nums1[-1] > nums2[-1]) or not nums2:
                del nums1[-1]
            else:
                del nums2[-1]

        merged = nums1 + nums2

        return sum(merged) / float(len(merged))


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums1 = [1,3]
        nums2 = [2]
        expect = 2
        result = self.solution.findMedianSortedArrays(nums1, nums2)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums1 = [1,2]
        nums2 = [3,4]
        expect = 2.5
        result = self.solution.findMedianSortedArrays(nums1, nums2)
        self.assertEqual(expect, result)

    def test_case3(self):
        nums1 = [0,0,0,0,0]
        nums2 = [-1,0,0,0,0,0,1]
        expect = 0
        result = self.solution.findMedianSortedArrays(nums1, nums2)
        self.assertEqual(expect, result)

    def test_case4(self):
        nums1 = [1]
        nums2 = [2,3]
        expect = 2
        result = self.solution.findMedianSortedArrays(nums1, nums2)
        self.assertEqual(expect, result)

    def test_case5(self):
        nums1 = [4,5,6,8,9]
        nums2 = []
        expect = 6
        result = self.solution.findMedianSortedArrays(nums1, nums2)
        self.assertEqual(expect, result)
