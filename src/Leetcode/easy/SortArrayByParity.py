import unittest


class Solution(object):
    def sortArrayByParity(self, nums):
        odd = []
        even = []
        for num in nums:
            if num % 2 == 1:
                odd.append(num)
            else:
                even.append(num)
        return even + odd


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [3,1,2,4]
        expect = [2,4,3,1]

        result = self.solution.sortArrayByParity(nums)
        self.assertEqual(result, expect)

    def test_case2(self):
        nums = [0]
        expect = [0]

        result = self.solution.sortArrayByParity(nums)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()