import unittest


class Solution(object):
    def checkStraightLine(self, coordinates):
        coordinates.sort()
        diff = [coordinates[0][0]-coordinates[1][0], coordinates[0][1] - coordinates[1][1]]

        for i in range(len(coordinates)-1):
            x = coordinates[i][0] - coordinates[i+1][0]
            y = coordinates[i][1] - coordinates[i+1][1]

            if (x == 0 and diff[0] == 0) or (y == 0 and diff[1] == 0):
                continue
            elif (x == 0 and diff[0] != 0) or (y == 0 and diff[1] != 0):
                return False
            elif (diff[0]*1.0) / x != (diff[1]*1.0) / y:
                return False
        return True


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
        expect = True
        result = self.solution.checkStraightLine(coordinates)
        self.assertEqual(expect, result)

    def test_case2(self):
        coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
        expect = False
        result = self.solution.checkStraightLine(coordinates)
        self.assertEqual(expect, result)

    def test_case3(self):
        coordinates = [[0,0],[0,1],[0,-1]]
        expect = True
        result = self.solution.checkStraightLine(coordinates)
        self.assertEqual(expect, result)

    def test_case4(self):
        coordinates = [[1,1],[2,2],[2,0]]
        expect = False
        result = self.solution.checkStraightLine(coordinates)
        self.assertEqual(expect, result)

    def test_case5(self):
        coordinates = [[98,0],[99,5000],[100,9999]]
        expect = False
        result = self.solution.checkStraightLine(coordinates)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()