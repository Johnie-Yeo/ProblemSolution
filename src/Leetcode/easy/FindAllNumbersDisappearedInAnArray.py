import unittest


class Solution(object):
    def findDisappearedNumbers(self, nums):
        s = set()
        n = len(nums)
        for num in nums:
            s.add(num)
        result = []
        for i in range(1, n+1):
            if i not in s :
                result.append(i)
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [4,3,2,7,8,2,3,1]
        result = self.solution.findDisappearedNumbers(nums)
        expect = [5,6]
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [1,1]
        result = self.solution.findDisappearedNumbers(nums)
        expect = [2]
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()