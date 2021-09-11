import unittest


class Point:
    def __init__(self, x, y):
        self.x = x
        self.y = y

    def move(self, x, y):
        self.x += x
        self.y += y


class Solution:
    def convert(self, s: str, numRows: int) -> str:
        matrix = self.initMatrix(s, numRows)

        point = Point(0, 0)
        for c in s:
            matrix[point.x][point.y] = c
            self.move(point, numRows)
        result = ''
        for row in matrix:
            result = result + ''.join(row)
        return result

    def initMatrix(self, s:str, numRows: int) -> list:
        numCols = self.getNumCols(s, numRows)
        matrix = []
        for i in range(numRows):
            col = []
            for j in range(numCols):
                col.append("")
            matrix.append(col)
        return matrix

    def getNumCols(self, s:str, numRows: int) -> int:
        unitSize = numRows * 2 - 2 or 1
        unitCount = len(s) // unitSize
        unitColSize = numRows - 1 or 1

        remainWordCount = len(s) - unitSize * unitCount
        remainUnitCols = remainWordCount - numRows + 1
        if remainUnitCols <= 0:
            remainUnitCols = 1

        return unitColSize * unitCount + remainUnitCols

    def move(self, point: Point, numRows: int):
        if numRows == 1:
            point.move(0, 1)
        elif point.x < numRows-1 and point.y % (numRows-1) == 0:
            point.move(1, 0)
        else:
            point.move(-1, 1)


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        s = "PAYPALISHIRING"
        numRows = 3
        expect = "PAHNAPLSIIGYIR"
        result = self.solution.convert(s, numRows)
        self.assertEqual(result, expect)

    def test_case2(self):
        s = "PAYPALISHIRING"
        numRows = 4
        expect = "PINALSIGYAHRPI"
        result = self.solution.convert(s, numRows)
        self.assertEqual(result, expect)

    def test_case3(self):
        s = "A"
        numRows = 1
        expect = "A"
        result = self.solution.convert(s, numRows)
        self.assertEqual(result, expect)

    def test_case4(self):
        s = "AA"
        numRows = 1
        expect = "AA"
        result = self.solution.convert(s, numRows)
        self.assertEqual(result, expect)

    def test_case5(self):
        s = "AA"
        numRows = 2
        expect = "AA"
        result = self.solution.convert(s, numRows)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()
