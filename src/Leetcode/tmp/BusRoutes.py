# failed!

import unittest
from typing import List


class Solution:
    def numBusesToDestination(self, routes: List[List[int]], source: int, target: int) -> int:

        if source == target:
            return 0

        buses = {}
        stops = {}
        for i in range(len(routes)):
            buses[i] = routes[i]
            for route in routes[i]:
                if route not in stops:
                    stops[route] = []
                stops[route].append(i)

        targetBus = stops[source]
        currentStop = set()
        for busIdx in targetBus:
            currentStop.update(list(filter(lambda e: e != source, buses[busIdx])))

        cnt = 1
        while target not in currentStop:
            nextStop = set()
            for stop in currentStop:
                targetBus = stops[stop]
                for busIdx in targetBus:
                    nextStop.update(list(filter(lambda e: e != source, buses[busIdx])))
            prev = currentStop
            currentStop = nextStop
            if prev == currentStop:
                return -1
            cnt += 1
        return cnt


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        routes = [[1, 2, 7], [3, 6, 7]]
        source = 1
        target = 6
        expect = 2
        result = self.solution.numBusesToDestination(routes, source, target)
        self.assertEqual(expect, result)

    def test_case2(self):
        routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]]
        source = 15
        target = 12
        expect = -1
        result = self.solution.numBusesToDestination(routes, source, target)
        self.assertEqual(expect, result)
