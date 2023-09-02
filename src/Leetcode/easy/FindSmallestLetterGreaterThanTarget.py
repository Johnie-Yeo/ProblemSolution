import unittest


class Solution(object):
    def nextGreatestLetter(self, letters, target):
        for letter in letters:
            if letter > target:
                return letter
        return letters[0]

class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        letters = ["c","f","j"]
        target = "a"
        expect = "c"
        result = self.solution.nextGreatestLetter(letters, target)
        self.assertEqual(expect, result)

    def test_case2(self):
        letters = ["c","f","j"]
        target = "c"
        expect = "f"
        result = self.solution.nextGreatestLetter(letters, target)
        self.assertEqual(expect, result)

    def test_case3(self):
        letters = ["x","x","y","y"]
        target = "z"
        expect = "x"
        result = self.solution.nextGreatestLetter(letters, target)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()