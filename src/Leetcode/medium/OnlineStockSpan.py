import unittest


class StockSpanner(object):

    def __init__(self):
        self.stack = []

    def next(self, price):
        result = 1
        idx = len(self.stack) - 1
        while idx >= 0 and self.stack[idx]["price"] <= price:
            result += self.stack[idx]["count"]
            idx = self.stack[idx]["next"]

        self.stack.append({"price" : price, "count" : result, "next" : idx})

        return result


class TestSolution(unittest.TestCase):

    def test_case1(self):
        stockSpanner = StockSpanner()
        self.assertEqual(1, stockSpanner.next(100))
        self.assertEqual(1, stockSpanner.next(80))
        self.assertEqual(1, stockSpanner.next(60))
        self.assertEqual(2, stockSpanner.next(70))
        self.assertEqual(1, stockSpanner.next(60))
        self.assertEqual(4, stockSpanner.next(75))
        self.assertEqual(6, stockSpanner.next(85))


if __name__ == '__main__':
    unittest.main()