import unittest


class Solution(object):
    def longestDiverseString(self, a, b, c):
        result = ""
        targetMap = {'a':a, 'b':b, 'c':c}
        seq = 0
        while targetMap['a'] > 0 or targetMap['b'] > 0 or targetMap['c'] > 0:
            target = self.getTarget(targetMap, seq)
            if target is None:
                break
            elif len(result) < 2:
                result += target
                targetMap[target] = targetMap[target] - 1
                seq = 0
            elif result[-1] != target or result[-2] != target:
                result += target
                targetMap[target] = targetMap[target] - 1
                seq = 0
            else:
                seq += 1
        return result

    def getTarget(self, targetMap, seq):
        tmp = sorted(targetMap.items(), key=lambda x: x[1], reverse=True)
        if tmp[seq][1] > 0:
            return tmp[seq][0]
        else:
            return None


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        a = 1
        b = 1
        c = 7
        expect = "ccaccbcc"
        result = self.solution.longestDiverseString(a, b, c)
        self.assertEqual(expect, result)

    def test_case2(self):
        a = 7
        b = 1
        c = 0
        expect = "aabaa"
        result = self.solution.longestDiverseString(a, b, c)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()
