import unittest


class Solution(object):
    def wiggleSort(self, nums):
        nums.sort()
        mid = (len(nums) - 1) // 2
        nums[::2], nums[1::2] = nums[mid::-1], nums[:mid:-1]
        return nums


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [1,5,1,1,6,4]
        result = self.solution.wiggleSort(nums)
        expect = [1,6,1,5,1,4]
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [1,3,2,2,3,1]
        result = self.solution.wiggleSort(nums)
        expect = [2,3,1,3,1,2]
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()