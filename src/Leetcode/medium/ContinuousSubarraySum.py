import unittest


class Solution(object):
    def checkSubarraySum(self, nums, k):
        s = set()
        curSum = 0
        prevSum = 0
        for num in nums:
            curSum += num
            curSum %= k
            if curSum in s:
                return True
            s.add(prevSum)
            prevSum = curSum
        return False


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [23, 2, 4, 6, 7]
        k = 6
        expect = True
        result = self.solution.checkSubarraySum(nums, k)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [23,2,6,4,7]
        k = 6
        expect = True
        result = self.solution.checkSubarraySum(nums, k)
        self.assertEqual(expect, result)

    def test_case3(self):
        nums = [23,2,6,4,7]
        k = 13
        expect = False
        result = self.solution.checkSubarraySum(nums, k)
        self.assertEqual(expect, result)

    def test_case4(self):
        nums = [23,6,9]
        k = 6
        expect = False
        result = self.solution.checkSubarraySum(nums, k)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()