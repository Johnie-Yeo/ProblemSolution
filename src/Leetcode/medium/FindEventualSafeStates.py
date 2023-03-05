import unittest


class Solution(object):
    def eventualSafeNodes(self, graph):
        n = len(graph)
        result = []
        for i in range(n):
            if self.isSafeNode(graph, i):
                result.append(i)
        return result

    def isSafeNode(self, graph, i):

        nextList = graph[i]
        visitList = [i]

        while nextList:
            for visit in visitList:
                if visit in nextList:
                    return False
            visitList += nextList

            a = []
            for n in nextList:
                a += graph[n]
            nextList = a

        return True


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        graph = [[1,2],[2,3],[5],[0],[5],[],[]]
        expect = [2,4,5,6]
        result = self.solution.eventualSafeNodes(graph)
        self.assertEqual(expect, result)

    def test_case2(self):
        graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
        expect = [4]
        result = self.solution.eventualSafeNodes(graph)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()