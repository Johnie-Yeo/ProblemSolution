import unittest
from typing import List


class Solution:
    def accountsMerge(self, accounts: List[List[str]]) -> List[List[str]]:
        result1 = self.merge(accounts)
        result2 = self.merge(result1)
        while len(result1) != len(result2):
            result1 = result2
            result2 = self.merge(result2)
        return result2

    def merge(self, accounts: List[List[str]]) -> List[List[str]]:
        addressBook = dict()
        for account in accounts:
            name = account[0]
            addresses = account[1:]

            if name not in addressBook:
                addressBook[name] = set(addresses)
            else:
                updated = False
                while not updated and name in addressBook:
                    for address in addresses:
                        if address in addressBook[name]:
                            addressBook[name].update(addresses)
                            updated = True
                            break
                    name = "_" + name
                if not updated:
                    addressBook[name] = set(addresses)

        result = []
        for name, address in addressBook.items():
            while name[0] == "_":
                name = name[1:]
            cur = [name]
            cur += sorted(list(address))
            result.append(cur)

        return result


class TestSolution(unittest.TestCase):
    def setUp(self):
        self.solution = Solution()

    def test_case1(self):
        accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        expect = [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        result = self.solution.accountsMerge(accounts)
        self.assertEqual(expect, result)

    def test_case2(self):
        accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
        expect = [["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"], ["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"], ["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"], ["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"], ["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
        result = self.solution.accountsMerge(accounts)
        self.assertEqual(expect, result)


if __name__ == '__main__':
    unittest.main()