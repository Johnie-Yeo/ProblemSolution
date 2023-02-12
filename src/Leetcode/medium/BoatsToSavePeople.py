import unittest


class Solution(object):
    def numRescueBoats(self, people, limit):
        people.sort()
        people.reverse()
        p2 = len(people)-1
        count = 0
        for p1 in range(len(people)):
            if p2 < p1:
                break
            if people[p1] + people[p2] <= limit:
                p2 -= 1
            count += 1
        return count


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        people = [1,2]
        limit = 3
        expect = 1
        result = self.solution.numRescueBoats(people, limit)
        self.assertEqual(expect, result)

    def test_case2(self):
        people = [3,2,2,1]
        limit = 3
        expect = 3
        result = self.solution.numRescueBoats(people, limit)
        self.assertEqual(expect, result)

    def test_case3(self):
        people = [3,5,3,4]
        limit = 5
        expect = 4
        result = self.solution.numRescueBoats(people, limit)
        self.assertEqual(expect, result)

    def test_case4(self):
        people = [2,49,10,7,11,41,47,2,22,6,13,12,33,18,10,26,2,6,50,10]
        limit = 50
        expect = 11
        result = self.solution.numRescueBoats(people, limit)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()