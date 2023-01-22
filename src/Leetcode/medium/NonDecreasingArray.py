import unittest


class Solution(object):
    def checkPossibility(self, nums):
        count = 0
        for i in range(len(nums)):
            if i == 0:
                continue
            if nums[i-1] > nums[i]:
                count += 1
                if count > 1:
                    return False
                prev = nums[i-1]
                nums[i-1] = nums[i]
                if i > 1 and nums[i-2] > nums[i-1]:
                    nums[i-1] = prev
                    nums[i] = prev
        return True


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [4, 2, 3]
        expect = True
        result = self.solution.checkPossibility(nums)
        self.assertEqual(result, expect)

    def test_case2(self):
        nums = [4, 2, 1]
        expect = False
        result = self.solution.checkPossibility(nums)
        self.assertEqual(result, expect)

    def test_case3(self):
        nums = [3, 4, 2, 3]
        expect = False
        result = self.solution.checkPossibility(nums)
        self.assertEqual(result, expect)

    def test_case4(self):
        nums = [5, 7, 1, 8]
        expect = True
        result = self.solution.checkPossibility(nums)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()