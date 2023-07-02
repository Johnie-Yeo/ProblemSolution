import unittest


class Solution(object):
    def singleNumber(self, nums):
        s = set()
        for num in nums:
            if num in s:
                s.remove(num)
            else:
                s.add(num)
        return list(s)[0]


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [2,2,1]
        result = self.solution.singleNumber(nums)
        expect = 1
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [4,1,2,1,2]
        result = self.solution.singleNumber(nums)
        expect = 4
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()