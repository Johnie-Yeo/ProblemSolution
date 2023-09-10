import unittest
from math import sqrt


class Solution(object):
    def maximumDetonation(self, bombs):
        result = 0
        for idx in range(len(bombs)):
            cur = self.detonate(idx, bombs)
            result = max(result, cur)
        return result

    def detonate(self, startIdx, bombs):
        result = 0

        queue = [bombs[startIdx]]
        remainedBombs = [row[:] for row in bombs]
        del remainedBombs[startIdx]

        while queue:
            nextQueue = []
            for bomb in queue:
                result += 1
                targetIdx = set()
                for idx in range(len(remainedBombs)):
                    if self.getDistance(bomb, remainedBombs[idx]) <= bomb[2]:
                        targetIdx.add(idx)
                nextIdx = reversed(sorted(targetIdx))
                for idx in nextIdx:
                    nextQueue.append(remainedBombs[idx])
                    del remainedBombs[idx]
            queue = nextQueue

        return result

    def getDistance(self, p1, p2):
        return sqrt( (p1[0] - p2[0])**2 + (p1[1] - p2[1])**2 )


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        bombs = [[2,1,3],[6,1,4]]
        expect = 2
        result = self.solution.maximumDetonation(bombs)
        self.assertEqual(expect, result)

    def test_case2(self):
        bombs = [[1,1,5],[10,10,5]]
        expect = 1
        result = self.solution.maximumDetonation(bombs)
        self.assertEqual(expect, result)

    def test_case3(self):
        bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
        expect = 5
        result = self.solution.maximumDetonation(bombs)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()