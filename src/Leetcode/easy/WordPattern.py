import unittest


class Solution(object):
    def wordPattern(self, pattern, s):

        patternMap = {}

        words = s.split(" ")

        if len(words) != len(pattern):
            return False

        for i in range(len(words)):
            target = pattern[i]
            if target not in patternMap:
                patternMap[target] = words[i]
            elif patternMap[target] != words[i]:
                return False

        if len(patternMap.keys()) != len(set(patternMap.values())):
            return False

        return True


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        pattern = "abba"
        s = "dog cat cat dog"
        result = self.solution.wordPattern(pattern, s)
        expect = True
        self.assertEqual(expect, result)

    def test_case2(self):
        pattern = "abba"
        s = "dog cat cat fish"
        result = self.solution.wordPattern(pattern, s)
        expect = False
        self.assertEqual(expect, result)

    def test_case3(self):
        pattern = "aaaa"
        s = "dog cat cat dog"
        result = self.solution.wordPattern(pattern, s)
        expect = False
        self.assertEqual(expect, result)

    def test_case4(self):
        pattern = "abba"
        s = "dog dog dog dog"
        result = self.solution.wordPattern(pattern, s)
        expect = False
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()