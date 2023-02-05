import unittest


class NumMatrix(object):

    def __init__(self, matrix):
        self.matrix = [[0] * len(matrix[0]) for i in range(len(matrix))]

        for i in range(len(matrix)):
            for j in range(len(matrix[0])):
                self.matrix[i][j] = matrix[i][j] \
                        + (self.matrix[i][j - 1] if j > 0 else 0) \
                        + (self.matrix[i - 1][j] if i > 0 else 0) \
                        - (self.matrix[i-1][j-1] if (i > 0 and j > 0) else 0)

    def sumRegion(self, row1, col1, row2, col2):
        return self.matrix[row2][col2] \
               - (self.matrix[row1 - 1][col2] if row1 > 0 else 0) \
               - (self.matrix[row2][col1 - 1] if col1 > 0 else 0) \
               + (self.matrix[row1-1][col1-1] if (row1 > 0 and col1 > 0) else 0)


# Your NumMatrix object will be instantiated and called as such:
# obj = NumMatrix(matrix)
# param_1 = obj.sumRegion(row1,col1,row2,col2)


class TestSolution(unittest.TestCase):

    def test_case1(self):
        # ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
        inputQuery = [
            [
                [
                    [3, 0, 1, 4, 2],
                    [5, 6, 3, 2, 1],
                    [1, 2, 0, 1, 5],
                    [4, 1, 0, 1, 7],
                    [1, 0, 3, 0, 5]
                ]
            ],
            [2, 1, 4, 3],
            [1, 1, 2, 2],
            [1, 2, 2, 4]
        ]

        expects = [None, 8, 11, 12]

        self.numMatrix = NumMatrix(inputQuery[0][0])

        queries = inputQuery[1:]
        for i in range(len(queries)):
            query = queries[i]
            result = self.numMatrix.sumRegion(query[0], query[1], query[2], query[3])
            self.assertEqual(expects[i + 1], result)


if __name__ == '__main__':
    unittest.main()

# [3, 0, 1, 4, 2]
# [5, 6, 3, 2, 1]
# [1, 2, 0, 1, 5]
# [4, 1, 0, 1, 7]
# [1, 0, 3, 0, 5]

# [3,   3,  4,  8, 10]
# [8,  14, 18, 24, 27]
# [9,  17, 21, 28, 36]
# [13, 22, 26, 34, 49]
# [14, 23, 30, 38, 58]