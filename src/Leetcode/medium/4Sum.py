import unittest


class Solution(object):
    def fourSum(self, nums, target):
        n = len(nums)
        result = []
        nums.sort()
        for a in range(n):
            for d in range(a+1, n):
                b = a + 1
                c = d - 1
                while b < c:
                    cur = nums[a] + nums[b] + nums[c] + nums[d]
                    if cur < target:
                        b += 1
                    elif cur > target:
                        c -= 1
                    else:
                        tmp = [nums[a],nums[b],nums[c],nums[d]]
                        if tmp not in result:
                            result.append(tmp)
                        b += 1
                        c -= 1
        return result



class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        nums = [1,0,-1,0,-2,2]
        target = 0
        expect = [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        result = self.solution.fourSum(nums, target)
        self.assertEqual(expect, result)

    def test_case2(self):
        nums = [2,2,2,2,2]
        target = 8
        expect = [[2,2,2,2]]
        result = self.solution.fourSum(nums, target)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()