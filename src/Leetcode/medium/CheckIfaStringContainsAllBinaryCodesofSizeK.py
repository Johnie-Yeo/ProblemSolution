import unittest


class Solution(object):
    def hasAllCodes(self, s, k):
        comb = set()
        for i in range(len(s)-k+1):
            sub = s[i: i+k]
            comb.add(sub)

        count = 2**k

        return len(comb) == count


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "00110110"
        k = 2
        expect = True
        result = self.solution.hasAllCodes(s, k)
        self.assertEqual(expect, result)

    def test_case2(self):
        s = "0110"
        k = 1
        expect = True
        result = self.solution.hasAllCodes(s, k)
        self.assertEqual(expect, result)

    def test_case3(self):
        s = "0110"
        k = 2
        expect = False
        result = self.solution.hasAllCodes(s, k)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()