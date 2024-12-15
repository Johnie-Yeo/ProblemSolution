import unittest


class Solution:
    def longestPalindrome(self, s: str) -> str:
        odd = []
        even = []

        for i in range(len(s)-1):
            odd.append((i, i))
            if s[i] == s[i+1]:
                even.append((i, i+1))

        result = s[even[0][0]:even[0][1]+1] if even else s[0]

        while odd:
            cur = []
            for (i, j) in odd:
                if i <= 0 or j >= len(s)-1:
                    continue
                if s[i-1] == s[j+1]:
                    cur.append((i-1, j+1))
            if cur:
                result = s[cur[0][0]:cur[0][1]+1]
            odd = cur

        while even:
            cur = []
            for (i, j) in even:
                if i <= 0 or j >= len(s)-1:
                    continue
                if s[i-1] == s[j+1]:
                    cur.append((i-1, j+1))
            if cur and len(result) < len(s[cur[0][0]:cur[0][1]+1]):
                result = s[cur[0][0]:cur[0][1]+1]
            even = cur

        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "babad"
        expect = "bab"
        result = self.solution.longestPalindrome(s)
        self.assertEqual(expect, result)

    def test_case2(self):
        s = "cbbd"
        expect = "bb"
        result = self.solution.longestPalindrome(s)
        self.assertEqual(expect, result)

    def test_case3(self):
        s = "a"
        expect = "a"
        result = self.solution.longestPalindrome(s)
        self.assertEqual(expect, result)

    def test_case4(self):
        s = "ac"
        expect = "a"
        result = self.solution.longestPalindrome(s)
        self.assertEqual(expect, result)

    def test_case5(self):
        s = "aa"
        expect = "aa"
        result = self.solution.longestPalindrome(s)
        self.assertEqual(expect, result)

    def test_case6(self):
        s = "xdxtfdaarvvznrptwicdldmrmwbdrmyppvamdvofujthfcmkcugvodmlvubgvodectwzparprifwgwfvddlrfdnrpspirtyvxqvbqiglugbmzoyzcfkvbjdrdqqxxzutebgoacczuhopvzjfrnfsylgfgkbmbjqqyggtdjcvjbvpspkjcezanajjzabfidndfdpkuamwvxrbpxzoivlnkwdxedtfnmvicmzebwktpktokibeycbpqzejddwnvimmbzupyxwmrgdbmcujadfexcchdkfvkxsdwkuwuxzhpnjgmqbmidcwywjgcsbydixyxcclcbrzjvrmlrzgmbviifllouykovscaufvxovwmmgubshtoizbwtcpqzwchtkmkjfneuybfglywfrorhmfdgvjdsmegtoytsivnuaceszpfsxgddbweckgziahkslykgdkztmpapnoyawqtyrdcuzaxcohohapektyfbfhrsdnjbgjvwvqpcikdnlkdogsinkfpymkkdburnbksnqfjgjlacqpfqlhsjhhoccdkrjipqwzsxmpjughaqchzlrqkogkryqkuuxhzchovebzgeekuflcgvxugnxcvugqlstmnljlvxonkybmzjmnsvvwfztcplgikptnppbzeygbmdsyimsntveojwsejmastiovbctdkdlfvpyzihhxishtveflnmamlnzqroxknrrkkfpveyzvvasdznykygrpbfkbinrrvheekeumlvlgalqelspvpiydqkwduckimyhpzsxlcpkbvgwmwnasdxuupdhcmxjoushcvcnjyrmuemuydyywpvzhkxsqszaqhnbhjwsokkpployomoawtr";
        expect = "fwgwf"
        result = self.solution.longestPalindrome(s)
        self.assertEqual(expect, result)
