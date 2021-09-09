import unittest
from functools import reduce


class Solution:
    def romanToInt(self, s: str) -> int:
        symbol = {"I": 1, "V": 5, "X": 10, "L": 50, "C": 100, "D": 500, "M": 1000}

        number_list = list(map(lambda i: symbol[i], list(s)))
        value_list = list(map(lambda i: self.get_value(number_list, i), range(len(number_list))))
        result = reduce(lambda acc, cur: acc+cur, value_list)
        return result

    def get_value(self, number_list, index) -> int:
        if len(number_list) <= index + 1:
            return number_list[index]
        return number_list[index] if number_list[index] >= number_list[index + 1] else -number_list[index]


class TestSolutions(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        test_input = "III"
        expect = 3
        result = self.solution.romanToInt(test_input)
        self.assertEqual(result, expect)

    def test_case2(self):
        test_input = "IV"
        expect = 4
        result = self.solution.romanToInt(test_input)
        self.assertEqual(result, expect)

    def test_case3(self):
        test_input = "IX"
        expect = 9
        result = self.solution.romanToInt(test_input)
        self.assertEqual(result, expect)

    def test_case4(self):
        test_input = "LVIII"
        expect = 58
        result = self.solution.romanToInt(test_input)
        self.assertEqual(result, expect)

    def test_case5(self):
        test_input = "MCMXCIV"
        expect = 1994
        result = self.solution.romanToInt(test_input)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()
