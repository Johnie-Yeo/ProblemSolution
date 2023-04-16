import unittest


class Solution(object):
    def minDominoRotations(self, tops, bottoms):
        cnt = {}
        for i in range(len(tops)):
            cur = []
            top = tops[i]
            if top in cnt:
                cur = cnt[top]
            cur.append(i)
            cnt[top] = cur

        result = -1
        for key in cnt.keys():
            item = self.reverseList(cnt[key], len(tops))
            cur = True
            for i in item:
                if bottoms[i] != key:
                    cur = False
            if cur is True:
                if result == -1:
                    result = len(item)
                else:
                    result = min(result, len(item))

        cnt = {}
        for i in range(len(bottoms)):
            cur = []
            bottom = bottoms[i]
            if bottom in cnt:
                cur = cnt[bottom]
            cur.append(i)
            cnt[bottom] = cur

        for key in cnt.keys():
            item = self.reverseList(cnt[key], len(bottoms))
            cur = True
            for i in item:
                if tops[i] != key:
                    cur = False
            if cur is True:
                if result == -1:
                    result = len(item)
                else:
                    result = min(result, len(item))

        return result

    def reverseList(self, idxList, length):
        result = []
        for i in range(length):
            if i not in idxList:
                result.append(i)
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        tops = [2,1,2,4,2,2]
        bottoms = [5,2,6,2,3,2]
        expect = 2
        result = self.solution.minDominoRotations(tops, bottoms)
        self.assertEqual(expect, result)

    def test_case2(self):
        tops = [3,5,1,2,3]
        bottoms = [3,6,3,3,4]
        expect = -1
        result = self.solution.minDominoRotations(tops, bottoms)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()