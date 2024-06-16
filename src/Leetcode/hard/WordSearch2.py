# failed!

import unittest
from typing import List, Set


class Node:
    def __init__(self, point: tuple = None, routes: Set[tuple] = None):
        self.point = point
        self.routes = routes


class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        result = []

        for word in words:
            if self.hasWord(board, word):
                result.append(word)

        return result

    def hasWord(self, board, word) -> bool:

        if not word:
            return True

        first = word[0]
        points = []
        for i in range(len(board)):
            for j in range(len(board[0])):
                if board[i][j] == first:
                    point = (i, j)
                    routes = set()
                    routes.add(point)
                    node = Node(point, routes)
                    points.append(node)

        if len(word) == 1:
            return len(points) > 0
        elif not points:
            return False

        dirs = [(0, 1), (0, -1), (1, 0), (-1, 0)]

        for node in points:
            curNodes = [node]
            complete = True
            for char in word[1:]:
                tmp = []
                for curNode in curNodes:
                    for d in dirs:
                        nextPoint = (curNode.point[0] + d[0], curNode.point[1] + d[1])
                        if (
                                (0 <= nextPoint[0] < len(board) and 0 <= nextPoint[1] < len(board[0])) \
                                and nextPoint not in curNode.routes \
                                and board[nextPoint[0]][nextPoint[1]] == char
                        ):
                            curRoute = set(curNode.routes)
                            curRoute.add(nextPoint)
                            nextNode = Node(nextPoint, curRoute)
                            tmp.append(nextNode)
                curNodes = tmp
                if not curNodes:
                    complete = False
                    break

            if complete:
                return True

        return False



class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        board = [["o", "a", "a", "n"], ["e", "t", "a", "e"], ["i", "h", "k", "r"], ["i", "f", "l", "v"]]
        words = ["oath", "pea", "eat", "rain"]
        expect = ["eat", "oath"]
        result = self.solution.findWords(board, words)
        self.assertEqual(set(expect), set(result))

    def test_case2(self):
        board = [["a", "b"], ["c", "d"]]
        words = ["abcb"]
        expect = []
        result = self.solution.findWords(board, words)
        self.assertEqual(set(expect), set(result))

    def test_case3(self):
        board = [["a", "a"]]
        words = ["aaa"]
        expect = []
        result = self.solution.findWords(board, words)
        self.assertEqual(set(expect), set(result))

    def test_case4(self):
        board = [["a","b","c"],["a","e","d"],["a","f","g"]]
        words = ["abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"]
        expect = ["abcdefg","befa","eaabcdgfa","gfedcbaaa"]
        result = self.solution.findWords(board, words)
        self.assertEqual(set(expect), set(result))

    def test_case5(self):
        board = [
            ["e","e","c","d","b","b","c","b","c","d","e"],
            ["c","e","e","a","d","d","e","c","c","c","b"],
            ["b","e","a","c","d","a","a","b","c","d","c"],
            ["e","d","e","d","c","c","e","b","d","e","e"],
            ["b","b","b","a","b","d","b","b","b","a","a"],
            ["e","e","b","e","c","c","a","b","e","e","c"],
            ["b","a","b","c","b","d","a","d","c","d","a"],
            ["d","b","a","e","a","c","e","a","d","e","c"]
        ]
        words = ["aeceecbee"]
        expect = ["aeceecbee"]
        result = self.solution.findWords(board, words)
        self.assertEqual(set(expect), set(result))

    def test_case6(self):
        board = [["a"]]
        words = ["a"]
        expect = ["a"]
        result = self.solution.findWords(board, words)
        self.assertEqual(set(expect), set(result))