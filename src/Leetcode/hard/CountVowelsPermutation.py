import unittest


class Solution(object):
    def countVowelPermutation(self, n):
        # rule = {'a': ['e'], 'e': ['a', 'i'], 'i': ['a', 'e', 'o', 'u'], 'o': ['i', 'u'], 'u': ['a']}
        status = {'a': 0, 'e': 0, 'i':0, 'o':0, 'u':0}

        for i in range(n):
            if i == 0:
                status = {'a': 1, 'e': 1, 'i': 1, 'o': 1, 'u': 1}
                continue
            a = status.get('e') + status.get('i') + status.get('u')
            e = status.get('a') + status.get('i')
            i = status.get('e') + status.get('o')
            o = status.get('i')
            u = status.get('i') + status.get('o')
            status = {'a': a, 'e': e, 'i': i, 'o': o, 'u': u}
        return sum(status.values()) % 1000000007


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        n = 1
        expect = 5
        result = self.solution.countVowelPermutation(n)
        self.assertEqual(result, expect)

    def test_case2(self):
        n = 2
        expect = 10
        result = self.solution.countVowelPermutation(n)
        self.assertEqual(result, expect)

    def test_case3(self):
        n = 5
        expect = 68
        result = self.solution.countVowelPermutation(n)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()