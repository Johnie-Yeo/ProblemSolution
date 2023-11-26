import unittest

# fail! - memory exceed
class Solution(object):
    def decodeAtIndex(self, s, k):
        cur = ""
        i = 0
        j = 0
        while i < k:
            if s[j].islower():
                cur += s[j]
                i += 1
                j += 1
            elif s[j].isdigit():
                tmp = ""
                for d in range(int(s[j])-1):
                    tmp += cur
                cur += tmp
                i += len(tmp)
                j += 1
        return cur[k-1]


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "leet2code3"
        k = 10
        expect = "o"

        result = self.solution.decodeAtIndex(s, k)
        self.assertEqual(result, expect)

    def test_case2(self):
        s = "ha22"
        k = 5
        expect = "h"

        result = self.solution.decodeAtIndex(s, k)
        self.assertEqual(result, expect)

    def test_case3(self):
        s = "a2345678999999999999999"
        k = 1
        expect = "a"

        result = self.solution.decodeAtIndex(s, k)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()