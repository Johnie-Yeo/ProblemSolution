# fail!

import unittest
from typing import List


class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        count = 0
        comb = {0}

        while amount not in comb:
            if max(list(comb)) > amount:
                return -1

            tmp = []
            for coin in coins:
                for c in comb:
                    tmp.append(c + coin)
            comb.update(tmp)

            count += 1

        return count


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        coins = [1,2,5]
        amount = 11
        expect = 3
        result = self.solution.coinChange(coins, amount)
        self.assertEqual(expect, result)

    def test_case2(self):
        coins = [2]
        amount = 3
        expect = -1
        result = self.solution.coinChange(coins, amount)
        self.assertEqual(expect, result)

    def test_case3(self):
        coins = [1]
        amount = 0
        expect = 0
        result = self.solution.coinChange(coins, amount)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()