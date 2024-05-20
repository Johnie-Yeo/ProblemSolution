import unittest
import heapq


class Node:
    def __init__(self, val, idx):
        self.val = val
        self.idx = [idx]

    def __lt__(self, other):
        if len(other.idx) < len(self.idx):
            return True
        elif len(other.idx) == len(self.idx) and other.idx[-1] < self.idx[-1]:
            return True
        else:
            return False

    def addIndex(self, idx):
        self.idx.append(idx)

    def pop(self):
        del self.idx[-1]
        return self.val

    def isEmpty(self):
        return len(self.idx) == 0


class FreqStack:

    def __init__(self):
        self.stack = []
        self.queue = []
        self.map = {}

    def push(self, val: int) -> None:
        idx = len(self.stack)
        self.stack.append(val)
        if self.map.get(val):
            self.map.get(val).addIndex(idx)
            heapq.heapify(self.queue)
        else:
            node = Node(val, idx)
            heapq.heappush(self.queue, node)
            self.map[val] = node

    def pop(self) -> int:
        target = heapq.heappop(self.queue)
        result = target.pop()
        del self.map[result]
        if not target.isEmpty():
            heapq.heappush(self.queue, target)
            self.map[result] = target
        return result


# Your FreqStack object will be instantiated and called as such:
# obj = FreqStack()
# obj.push(val)
# param_2 = obj.pop()


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.app = FreqStack()

    def test_case1(self):
        self.app.push(5)
        self.app.push(7)
        self.app.push(5)
        self.app.push(7)
        self.app.push(4)
        self.app.push(5)
        result = self.app.pop()
        expect = 5
        self.assertEqual(expect, result)
        result = self.app.pop()
        expect = 7
        self.assertEqual(expect, result)
        result = self.app.pop()
        expect = 5
        self.assertEqual(expect, result)
        result = self.app.pop()
        expect = 4
        self.assertEqual(expect, result)

    def test_case2(self):
        self.app.push(4)
        self.app.push(0)
        self.app.push(9)
        self.app.push(3)
        self.app.push(4)
        self.app.push(2)
        result = self.app.pop()
        expect = 4
        self.assertEqual(expect, result)
        self.app.push(6)
        result = self.app.pop()
        expect = 6
        self.assertEqual(expect, result)
        self.app.push(1)
        result = self.app.pop()
        expect = 1
        self.assertEqual(expect, result)
        self.app.push(1)
        result = self.app.pop()
        expect = 1
        self.assertEqual(expect, result)
        self.app.push(4)
        result = self.app.pop()
        expect = 4
        self.assertEqual(expect, result)
        result = self.app.pop()
        expect = 2
        self.assertEqual(expect, result)
        result = self.app.pop()
        expect = 3
        self.assertEqual(expect, result)
        result = self.app.pop()
        expect = 9
        self.assertEqual(expect, result)
        result = self.app.pop()
        expect = 0
        self.assertEqual(expect, result)
        result = self.app.pop()
        expect = 4
        self.assertEqual(expect, result)