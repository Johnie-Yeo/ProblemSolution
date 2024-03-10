# fail! - timeout

import unittest
import copy


class Solution:
    def minWindow(self, s: str, t: str) -> str:
        result = ""

        targetList = list(t)
        targetSet = set(t)
        targetDict = {key: targetList.count(key) for key in targetSet}

        for i in range(len(s) - len(t) + 1):
            if s[i] not in targetDict:
                continue

            curDict = copy.deepcopy(targetDict)

            j = i
            cur = ""
            while curDict and j < len(s):
                if s[j] in curDict:
                    if curDict[s[j]] == 1:
                        del curDict[s[j]]
                    else:
                        curDict[s[j]] = curDict[s[j]] - 1
                cur += s[j]
                j += 1
            if not curDict:
                if not result or len(result) > len(cur):
                    result = cur
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "ADOBECODEBANC"
        t = "ABC"
        expect = "BANC"
        result = self.solution.minWindow(s, t)
        self.assertEqual(expect, result)

    def test_case2(self):
        s = "a"
        t = "a"
        expect = "a"
        result = self.solution.minWindow(s, t)
        self.assertEqual(expect, result)

    def test_case3(self):
        s = "a"
        t = "aa"
        expect = ""
        result = self.solution.minWindow(s, t)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()
