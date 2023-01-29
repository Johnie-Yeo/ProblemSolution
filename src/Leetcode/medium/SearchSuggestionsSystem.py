import unittest


class Node(object):
    def __init__(self, key=None, data = None):
        self.key = key
        self.data = data
        self.children = {}


class Trie:
    def __init__(self):
        self.head = Node()

    def insert(self, word):
        current_node = self.head

        for char in word:
            if char not in current_node.children:
                current_node.children[char] = Node(char)
            current_node = current_node.children[char]
        current_node.data = word

    def suggest_words(self, searchWord):
        current_node = self.head
        words = []

        for p in searchWord:
            if p in current_node.children:
                current_node = current_node.children[p]
            else:
                return None

        current_node = [current_node]
        next_node = []
        while True:
            for node in current_node:
                if node.data:
                    words.append(node.data)
                next_node.extend(list(node.children.values()))
            if len(next_node) != 0:
                current_node = next_node
                next_node = []
            else:
                break

        return sorted(words)[:3]


class Solution(object):
    def suggestedProducts(self, products, searchWord):
        result = []

        trie = Trie()
        for product in products:
            trie.insert(product)

        target = ""
        for word in searchWord:
            target += word
            searchResult = trie.suggest_words(target)
            result.append(searchResult)
        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        products = ["mobile","mouse","moneypot","monitor","mousepad"]
        searchWord = "mouse"
        expect = [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]

        result = self.solution.suggestedProducts(products, searchWord)
        self.assertEqual(result, expect)

    def test_case2(self):
        products = ["havana"]
        searchWord = "havana"
        expect = [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]

        result = self.solution.suggestedProducts(products, searchWord)
        self.assertEqual(result, expect)


if __name__ == '__main__':
    unittest.main()