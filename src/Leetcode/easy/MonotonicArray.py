import unittest


class Solution(object):
    def isMonotonic(self, nums):
        diff = []
        for i in range(len(nums)-1):
            if nums[i] != nums[i+1]:
                diff.append(nums[i] - nums[i+1])
            if len(diff) > 1 and diff[0] * diff[-1] < 0:
                return False
        if len(diff) > 1 and diff[0] * diff[-1] < 0:
            return False
        return True


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [1,2,2,3]
        expect = True

        result = self.solution.isMonotonic(nums)
        self.assertEqual(result, expect)

    def test_case2(self):
        nums = [6,5,4,4]
        expect = True

        result = self.solution.isMonotonic(nums)
        self.assertEqual(result, expect)

    def test_case3(self):
        nums = [1,3,2]
        expect = False

        result = self.solution.isMonotonic(nums)
        self.assertEqual(result, expect)

    def test_case4(self):
        nums = [1,1,1]
        expect = True

        result = self.solution.isMonotonic(nums)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()