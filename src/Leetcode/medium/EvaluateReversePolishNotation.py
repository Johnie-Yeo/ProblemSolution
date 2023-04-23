import unittest


class Solution(object):
    def evalRPN(self, tokens):
        stack = []

        for token in tokens:
            if self.isOperator(token):
                b = stack.pop()
                a = stack.pop()
                tmp = self.operate(a, b, token)
                stack.append(tmp)
            else:
                stack.append(token)
        return int(stack[0])

    def isOperator(self, token):
        return token in ["+", "-", "*", "/"];

    def operate(self, a, b, token):
        a = int(a)
        b = int(b)
        if token == "+":
            return str(a+b)
        elif token == "-":
            return str(a-b)
        elif token == "*":
            return str(a*b)
        elif token == "/":
            tmp = a // b
            if tmp < 0 and a % b != 0:
                tmp += 1
            return str(tmp)
        else:
            # error
            return -1


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        tokens = ["2","1","+","3","*"]
        expect = 9
        result = self.solution.evalRPN(tokens)
        self.assertEqual(expect, result)

    def test_case2(self):
        tokens = ["4","13","5","/","+"]
        expect = 6
        result = self.solution.evalRPN(tokens)
        self.assertEqual(expect, result)

    def test_case3(self):
        tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
        expect = 22
        result = self.solution.evalRPN(tokens)
        self.assertEqual(expect, result)

    def test_case4(self):
        tokens = ["4","-2","/","2","-3","-","-"]
        expect = -7
        result = self.solution.evalRPN(tokens)
        self.assertEqual(expect, result)

if __name__ == '__main__':
    unittest.main()