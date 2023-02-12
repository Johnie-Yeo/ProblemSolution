import unittest


class Solution(object):
    def find132pattern(self, nums):
        secondMax = float('-inf')
        stack = []

        nums.reverse()
        for num in nums:
            if num < secondMax:
                return True

            while stack and stack[-1] < num:
                secondMax = stack.pop()

            stack.append(num)
        return False


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [1, 2, 3, 4]
        expect = False
        result = self.solution.find132pattern(nums)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [3, 1, 4, 2]
        expect = True
        result = self.solution.find132pattern(nums)
        self.assertEqual(expect, result)

    def test_case3(self):
        nums = [-1, 3, 2, 0]
        expect = True
        result = self.solution.find132pattern(nums)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()