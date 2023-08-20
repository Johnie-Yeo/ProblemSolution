import unittest

class Solution(object):
    def subsetsWithDup(self, nums):
        def back(nums,ans,temp):
            ans.append(temp)
            for i in range(len(nums)):
                back(nums[i+1:],ans,temp+[nums[i]])
        ans=[]
        back(nums,ans,[])
        return ans


# class TestSolution(unittest.TestCase):
#     def setUp(self):
#         self.solution = Solution()
#
#     def test_case1(self):
#         nums = [1,2,2]
#         expect = [[],[1],[1,2],[1,2,2],[2],[2,2]]
#         result = self.solution.subsetsWithDup(nums)
#         self.assertEqual(expect, result)
#
#     def test_case2(self):
#         nums = [0]
#         expect = [[], [0]]
#         result = self.solution.subsetsWithDup(nums)
#         self.assertEqual(expect, result)
#
# if __name__ == '__main__':
#     unittest.main()