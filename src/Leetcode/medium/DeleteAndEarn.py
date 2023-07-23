import unittest


class Solution(object):
    def deleteAndEarn(self, nums):
        nums.sort()
        count = dict()
        for i in nums:
            count[i] = count.get(i, 0) + 1

        targets = list(count.keys())
        targets.sort()

        result = []

        for i in range(len(targets)):
            cur = targets[i] * count[targets[i]]
            if i > 0 and targets[i]-1 not in targets:
                tmp = result[i-2] if i > 1 else 0
                cur += max(result[i-1], tmp)
            elif i > 1 and targets[i]-1 in targets:
                tmp = result[i-3] if i > 2 else 0
                cur += max(result[i-2], tmp)
            result.append(cur)

        if len(result) > 1:
            return max(result[-1], result[-2])
        return result[0]


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [3,4,2]
        expect = 6
        result = self.solution.deleteAndEarn(nums)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [2,2,3,3,3,4]
        expect = 9
        result = self.solution.deleteAndEarn(nums)
        self.assertEqual(expect, result)

    def test_case3(self):
        nums = [1]
        expect = 1
        result = self.solution.deleteAndEarn(nums)
        self.assertEqual(expect, result)

    def test_case4(self):
        nums = [1,1,1,2,4,5,5,5,6]
        expect = 18
        result = self.solution.deleteAndEarn(nums)
        self.assertEqual(expect, result)

    def test_case5(self):
        nums = [8,3,4,7,6,6,9,2,5,8,2,4,9,5,9,1,5,7,1,4]
        expect = 61
        result = self.solution.deleteAndEarn(nums)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()