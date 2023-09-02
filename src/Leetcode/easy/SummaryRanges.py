import unittest


class Solution(object):
    def summaryRanges(self, nums):
        start = None
        prev = None
        result = []
        for num in nums:
            if start is None:
                start = num
            elif prev + 1 != num:
                if start == prev:
                    result.append("{}".format(start))
                else:
                    result.append("{}->{}".format(start, prev))
                start = num
            prev = num

        if start is None:
            return result
        elif start == prev:
            result.append("{}".format(start))
        else:
            result.append("{}->{}".format(start, prev))
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [0, 1, 2, 4, 5, 7]
        expect = ["0->2", "4->5", "7"]
        result = self.solution.summaryRanges(nums)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [0, 2, 3, 4, 6, 8, 9]
        expect = ["0", "2->4", "6", "8->9"]
        result = self.solution.summaryRanges(nums)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()
