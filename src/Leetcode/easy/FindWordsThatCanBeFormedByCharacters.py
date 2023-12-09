import unittest
from functools import reduce


class Solution(object):
    def countCharacters(self, words, chars):
        target = reduce(self.accumulate, list(chars), {})

        result = []

        for word in words:
            comb = reduce(self.accumulate, list(word), {})
            if self.includes(target, comb):
                result.append(word)

        return reduce(lambda acc, cur: acc + len(cur), result, 0)

    def accumulate(self, acc, cur):
        acc[cur] = acc.get(cur, 0)+1
        return acc

    def includes(self, a, b):
        for key in b.keys():
            if a.get(key, 0) < b[key]:
                return False
        return True


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        words = ["cat","bt","hat","tree"]
        chars = "atach"
        expect = 6

        result = self.solution.countCharacters(words, chars)
        self.assertEqual(result, expect)

    def test_case2(self):
        words = ["hello","world","leetcode"]
        chars = "welldonehoneyr"
        expect = 10

        result = self.solution.countCharacters(words, chars)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()