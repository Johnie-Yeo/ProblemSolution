import unittest


class Solution:
    def intToRoman(self, num: int) -> str:
        symbol = {
            1: 'I', 4: 'IV',
            5: 'V', 9: 'IX',
            10: 'X', 40: 'XL',
            50: 'L', 90: 'XC',
            100: 'C', 400: 'CD',
            500: 'D', 900: 'CM',
            1000: 'M'
        }

        result = ""
        while num > 0:
            ceiling = self.getCeiling(num, symbol)
            result += symbol[ceiling]
            num -= ceiling
        return result

    def getCeiling(self, num, symbol) -> int:
        result = 0
        for key in symbol.keys():
            if key > num:
                break
            else:
                result = key
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        num = 3
        expect = "III"
        result = self.solution.intToRoman(num)
        self.assertEqual(result, expect)

    def test_case2(self):
        num = 4
        expect = "IV"
        result = self.solution.intToRoman(num)
        self.assertEqual(result, expect)

    def test_case3(self):
        num = 9
        expect = "IX"
        result = self.solution.intToRoman(num)
        self.assertEqual(result, expect)

    def test_case4(self):
        num = 58
        expect = "LVIII"
        result = self.solution.intToRoman(num)
        self.assertEqual(result, expect)

    def test_case5(self):
        num = 1994
        expect = "MCMXCIV"
        result = self.solution.intToRoman(num)
        self.assertEqual(result, expect)

    def test_case6(self):
        num = 1000
        expect = "M"
        result = self.solution.intToRoman(num)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()
