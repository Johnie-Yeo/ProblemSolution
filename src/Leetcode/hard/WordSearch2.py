import unittest
from typing import List, Set


class Node(object):
    def __init__(self, key, data=None):
        self.key = key
        self.data = data
        self.children = {}


class Trie(object):
    def __init__(self):
        self.head = Node(None)

    def insert(self, inputStr):
        curNode = self.head

        for c in inputStr:
            if c not in curNode.children:
                curNode.children[c] = Node(c)
            curNode = curNode.children[c]

        curNode.data = inputStr


class Solution:
    def findWords(self, board: List[List[str]], words: List[str]) -> List[str]:
        result = set()

        trie = Trie()
        for word in words:
            trie.insert(word)

        for i in range(len(board)):
            for j in range(len(board[0])):
                result.update(self.checkWords((i, j), board, trie.head))

        return list(result)

    def checkWords(self, point: tuple, board: List[List[str]], trieNode: Node) -> Set[str]:
        dirs = [(1, 0), (0, 1), (-1, 0), (0, -1)]

        result = set()

        if point[0] < 0 or point[1] < 0 or point[0] >= len(board) or point[1] >= len(board[0]):
            return result

        letter = board[point[0]][point[1]]
        curNode = trieNode.children.get(letter)

        if curNode is None:
            return result

        if curNode.data is not None:
            result.add(curNode.data)
            curNode.data = None

        tmp, board[point[0]][point[1]] = board[point[0]][point[1]], "#"
        for d in dirs:
            nextPoint = (point[0] + d[0], point[1] + d[1])
            result.update(self.checkWords(nextPoint, board, curNode))

        board[point[0]][point[1]] = tmp

        if not curNode.children:
            trieNode.children.pop(letter)

        return result


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
        board = [
            ["a", "b", "c"],
            ["a", "e", "d"],
            ["a", "f", "g"]
        ]
        words = ["abcdefg", "gfedcbaaa", "eaabcdgfa", "befa", "dgc", "ade"]
        expect = ["abcdefg", "befa", "eaabcdgfa", "gfedcbaaa"]
        result = self.solution.findWords(board, words)
        self.assertEqual(set(expect), set(result))

    def test_case5(self):
        board = [
            ["e", "e", "c", "d", "b", "b", "c", "b", "c", "d", "e"],
            ["c", "e", "e", "a", "d", "d", "e", "c", "c", "c", "b"],
            ["b", "e", "a", "c", "d", "a", "a", "b", "c", "d", "c"],
            ["e", "d", "e", "d", "c", "c", "e", "b", "d", "e", "e"],
            ["b", "b", "b", "a", "b", "d", "b", "b", "b", "a", "a"],
            ["e", "e", "b", "e", "c", "c", "a", "b", "e", "e", "c"],
            ["b", "a", "b", "c", "b", "d", "a", "d", "c", "d", "a"],
            ["d", "b", "a", "e", "a", "c", "e", "a", "d", "e", "c"]
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

    def test_case7(self):
        board = [["a","b"]]
        words = ["ba"]
        expect = ["ba"]
        result = self.solution.findWords(board, words)
        self.assertEqual(set(expect), set(result))

    def test_case8(self):
        board = [["m", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"],
                 ["n", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"],
                 ["o", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"],
                 ["p", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"],
                 ["q", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"],
                 ["r", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"],
                 ["s", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"],
                 ["t", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"],
                 ["u", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"],
                 ["v", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"],
                 ["w", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a"],
                 ["x", "y", "z", "a", "a", "a", "a", "a", "a", "a", "a", "a"]]
        words = ["aaaaaaaaaa", "aaaaaaaaab", "aaaaaaaaac", "aaaaaaaaad", "aaaaaaaaae", "aaaaaaaaaf", "aaaaaaaaag",
                 "aaaaaaaaah", "aaaaaaaaai", "aaaaaaaaaj", "aaaaaaaaak", "aaaaaaaaal", "aaaaaaaaam", "aaaaaaaaan",
                 "aaaaaaaaao", "aaaaaaaaap", "aaaaaaaaaq", "aaaaaaaaar", "aaaaaaaaas", "aaaaaaaaat", "aaaaaaaaau",
                 "aaaaaaaaav", "aaaaaaaaaw", "aaaaaaaaax", "aaaaaaaaay", "aaaaaaaaaz", "aaaaaaaaba", "aaaaaaaabb",
                 "aaaaaaaabc", "aaaaaaaabd", "aaaaaaaabe", "aaaaaaaabf", "aaaaaaaabg", "aaaaaaaabh", "aaaaaaaabi",
                 "aaaaaaaabj", "aaaaaaaabk", "aaaaaaaabl", "aaaaaaaabm", "aaaaaaaabn", "aaaaaaaabo", "aaaaaaaabp",
                 "aaaaaaaabq", "aaaaaaaabr", "aaaaaaaabs", "aaaaaaaabt", "aaaaaaaabu", "aaaaaaaabv", "aaaaaaaabw",
                 "aaaaaaaabx", "aaaaaaaaby", "aaaaaaaabz", "aaaaaaaaca", "aaaaaaaacb", "aaaaaaaacc", "aaaaaaaacd",
                 "aaaaaaaace", "aaaaaaaacf", "aaaaaaaacg", "aaaaaaaach", "aaaaaaaaci", "aaaaaaaacj", "aaaaaaaack",
                 "aaaaaaaacl", "aaaaaaaacm", "aaaaaaaacn", "aaaaaaaaco", "aaaaaaaacp", "aaaaaaaacq", "aaaaaaaacr",
                 "aaaaaaaacs", "aaaaaaaact", "aaaaaaaacu", "aaaaaaaacv", "aaaaaaaacw", "aaaaaaaacx", "aaaaaaaacy",
                 "aaaaaaaacz", "aaaaaaaada", "aaaaaaaadb", "aaaaaaaadc", "aaaaaaaadd", "aaaaaaaade", "aaaaaaaadf",
                 "aaaaaaaadg", "aaaaaaaadh", "aaaaaaaadi", "aaaaaaaadj", "aaaaaaaadk", "aaaaaaaadl", "aaaaaaaadm",
                 "aaaaaaaadn", "aaaaaaaado", "aaaaaaaadp", "aaaaaaaadq", "aaaaaaaadr", "aaaaaaaads", "aaaaaaaadt",
                 "aaaaaaaadu", "aaaaaaaadv", "aaaaaaaadw", "aaaaaaaadx", "aaaaaaaady", "aaaaaaaadz", "aaaaaaaaea",
                 "aaaaaaaaeb", "aaaaaaaaec", "aaaaaaaaed", "aaaaaaaaee", "aaaaaaaaef", "aaaaaaaaeg", "aaaaaaaaeh",
                 "aaaaaaaaei", "aaaaaaaaej", "aaaaaaaaek", "aaaaaaaael", "aaaaaaaaem", "aaaaaaaaen", "aaaaaaaaeo",
                 "aaaaaaaaep", "aaaaaaaaeq", "aaaaaaaaer", "aaaaaaaaes", "aaaaaaaaet", "aaaaaaaaeu", "aaaaaaaaev",
                 "aaaaaaaaew", "aaaaaaaaex", "aaaaaaaaey", "aaaaaaaaez", "aaaaaaaafa", "aaaaaaaafb", "aaaaaaaafc",
                 "aaaaaaaafd", "aaaaaaaafe", "aaaaaaaaff", "aaaaaaaafg", "aaaaaaaafh", "aaaaaaaafi", "aaaaaaaafj",
                 "aaaaaaaafk", "aaaaaaaafl", "aaaaaaaafm", "aaaaaaaafn", "aaaaaaaafo", "aaaaaaaafp", "aaaaaaaafq",
                 "aaaaaaaafr", "aaaaaaaafs", "aaaaaaaaft", "aaaaaaaafu", "aaaaaaaafv", "aaaaaaaafw", "aaaaaaaafx",
                 "aaaaaaaafy", "aaaaaaaafz", "aaaaaaaaga", "aaaaaaaagb", "aaaaaaaagc", "aaaaaaaagd", "aaaaaaaage",
                 "aaaaaaaagf", "aaaaaaaagg", "aaaaaaaagh", "aaaaaaaagi", "aaaaaaaagj", "aaaaaaaagk", "aaaaaaaagl",
                 "aaaaaaaagm", "aaaaaaaagn", "aaaaaaaago", "aaaaaaaagp", "aaaaaaaagq", "aaaaaaaagr", "aaaaaaaags",
                 "aaaaaaaagt", "aaaaaaaagu", "aaaaaaaagv", "aaaaaaaagw", "aaaaaaaagx", "aaaaaaaagy", "aaaaaaaagz",
                 "aaaaaaaaha", "aaaaaaaahb", "aaaaaaaahc", "aaaaaaaahd", "aaaaaaaahe", "aaaaaaaahf", "aaaaaaaahg",
                 "aaaaaaaahh", "aaaaaaaahi", "aaaaaaaahj", "aaaaaaaahk", "aaaaaaaahl", "aaaaaaaahm", "aaaaaaaahn",
                 "aaaaaaaaho", "aaaaaaaahp", "aaaaaaaahq", "aaaaaaaahr", "aaaaaaaahs", "aaaaaaaaht", "aaaaaaaahu",
                 "aaaaaaaahv", "aaaaaaaahw", "aaaaaaaahx", "aaaaaaaahy", "aaaaaaaahz", "aaaaaaaaia", "aaaaaaaaib",
                 "aaaaaaaaic", "aaaaaaaaid", "aaaaaaaaie", "aaaaaaaaif", "aaaaaaaaig", "aaaaaaaaih", "aaaaaaaaii",
                 "aaaaaaaaij", "aaaaaaaaik", "aaaaaaaail", "aaaaaaaaim", "aaaaaaaain", "aaaaaaaaio", "aaaaaaaaip",
                 "aaaaaaaaiq", "aaaaaaaair", "aaaaaaaais", "aaaaaaaait", "aaaaaaaaiu", "aaaaaaaaiv", "aaaaaaaaiw",
                 "aaaaaaaaix", "aaaaaaaaiy", "aaaaaaaaiz", "aaaaaaaaja", "aaaaaaaajb", "aaaaaaaajc", "aaaaaaaajd",
                 "aaaaaaaaje", "aaaaaaaajf", "aaaaaaaajg", "aaaaaaaajh", "aaaaaaaaji", "aaaaaaaajj", "aaaaaaaajk",
                 "aaaaaaaajl", "aaaaaaaajm", "aaaaaaaajn", "aaaaaaaajo", "aaaaaaaajp", "aaaaaaaajq", "aaaaaaaajr",
                 "aaaaaaaajs", "aaaaaaaajt", "aaaaaaaaju", "aaaaaaaajv", "aaaaaaaajw", "aaaaaaaajx", "aaaaaaaajy",
                 "aaaaaaaajz", "aaaaaaaaka", "aaaaaaaakb", "aaaaaaaakc", "aaaaaaaakd", "aaaaaaaake", "aaaaaaaakf",
                 "aaaaaaaakg", "aaaaaaaakh", "aaaaaaaaki", "aaaaaaaakj", "aaaaaaaakk", "aaaaaaaakl", "aaaaaaaakm",
                 "aaaaaaaakn", "aaaaaaaako", "aaaaaaaakp", "aaaaaaaakq", "aaaaaaaakr", "aaaaaaaaks", "aaaaaaaakt",
                 "aaaaaaaaku", "aaaaaaaakv", "aaaaaaaakw", "aaaaaaaakx", "aaaaaaaaky", "aaaaaaaakz", "aaaaaaaala",
                 "aaaaaaaalb", "aaaaaaaalc", "aaaaaaaald", "aaaaaaaale", "aaaaaaaalf", "aaaaaaaalg", "aaaaaaaalh",
                 "aaaaaaaali", "aaaaaaaalj", "aaaaaaaalk", "aaaaaaaall", "aaaaaaaalm", "aaaaaaaaln", "aaaaaaaalo",
                 "aaaaaaaalp", "aaaaaaaalq", "aaaaaaaalr", "aaaaaaaals", "aaaaaaaalt", "aaaaaaaalu", "aaaaaaaalv",
                 "aaaaaaaalw", "aaaaaaaalx", "aaaaaaaaly", "aaaaaaaalz", "aaaaaaaama", "aaaaaaaamb", "aaaaaaaamc",
                 "aaaaaaaamd", "aaaaaaaame", "aaaaaaaamf", "aaaaaaaamg", "aaaaaaaamh", "aaaaaaaami", "aaaaaaaamj",
                 "aaaaaaaamk", "aaaaaaaaml", "aaaaaaaamm", "aaaaaaaamn", "aaaaaaaamo", "aaaaaaaamp", "aaaaaaaamq",
                 "aaaaaaaamr", "aaaaaaaams", "aaaaaaaamt", "aaaaaaaamu", "aaaaaaaamv", "aaaaaaaamw", "aaaaaaaamx",
                 "aaaaaaaamy", "aaaaaaaamz", "aaaaaaaana", "aaaaaaaanb", "aaaaaaaanc", "aaaaaaaand", "aaaaaaaane",
                 "aaaaaaaanf", "aaaaaaaang", "aaaaaaaanh", "aaaaaaaani", "aaaaaaaanj", "aaaaaaaank", "aaaaaaaanl",
                 "aaaaaaaanm", "aaaaaaaann", "aaaaaaaano", "aaaaaaaanp", "aaaaaaaanq", "aaaaaaaanr", "aaaaaaaans",
                 "aaaaaaaant", "aaaaaaaanu", "aaaaaaaanv", "aaaaaaaanw", "aaaaaaaanx", "aaaaaaaany", "aaaaaaaanz",
                 "aaaaaaaaoa", "aaaaaaaaob", "aaaaaaaaoc", "aaaaaaaaod", "aaaaaaaaoe", "aaaaaaaaof", "aaaaaaaaog",
                 "aaaaaaaaoh", "aaaaaaaaoi", "aaaaaaaaoj", "aaaaaaaaok", "aaaaaaaaol", "aaaaaaaaom", "aaaaaaaaon",
                 "aaaaaaaaoo", "aaaaaaaaop", "aaaaaaaaoq", "aaaaaaaaor", "aaaaaaaaos", "aaaaaaaaot", "aaaaaaaaou",
                 "aaaaaaaaov", "aaaaaaaaow", "aaaaaaaaox", "aaaaaaaaoy", "aaaaaaaaoz", "aaaaaaaapa", "aaaaaaaapb",
                 "aaaaaaaapc", "aaaaaaaapd", "aaaaaaaape", "aaaaaaaapf", "aaaaaaaapg", "aaaaaaaaph", "aaaaaaaapi",
                 "aaaaaaaapj", "aaaaaaaapk", "aaaaaaaapl", "aaaaaaaapm", "aaaaaaaapn", "aaaaaaaapo", "aaaaaaaapp",
                 "aaaaaaaapq", "aaaaaaaapr", "aaaaaaaaps", "aaaaaaaapt", "aaaaaaaapu", "aaaaaaaapv", "aaaaaaaapw",
                 "aaaaaaaapx", "aaaaaaaapy", "aaaaaaaapz", "aaaaaaaaqa", "aaaaaaaaqb", "aaaaaaaaqc", "aaaaaaaaqd",
                 "aaaaaaaaqe", "aaaaaaaaqf", "aaaaaaaaqg", "aaaaaaaaqh", "aaaaaaaaqi", "aaaaaaaaqj", "aaaaaaaaqk",
                 "aaaaaaaaql", "aaaaaaaaqm", "aaaaaaaaqn", "aaaaaaaaqo", "aaaaaaaaqp", "aaaaaaaaqq", "aaaaaaaaqr",
                 "aaaaaaaaqs", "aaaaaaaaqt", "aaaaaaaaqu", "aaaaaaaaqv", "aaaaaaaaqw", "aaaaaaaaqx", "aaaaaaaaqy",
                 "aaaaaaaaqz", "aaaaaaaara", "aaaaaaaarb", "aaaaaaaarc", "aaaaaaaard", "aaaaaaaare", "aaaaaaaarf",
                 "aaaaaaaarg", "aaaaaaaarh", "aaaaaaaari", "aaaaaaaarj", "aaaaaaaark", "aaaaaaaarl", "aaaaaaaarm",
                 "aaaaaaaarn", "aaaaaaaaro", "aaaaaaaarp", "aaaaaaaarq", "aaaaaaaarr", "aaaaaaaars", "aaaaaaaart",
                 "aaaaaaaaru", "aaaaaaaarv", "aaaaaaaarw", "aaaaaaaarx", "aaaaaaaary", "aaaaaaaarz", "aaaaaaaasa",
                 "aaaaaaaasb", "aaaaaaaasc", "aaaaaaaasd", "aaaaaaaase", "aaaaaaaasf", "aaaaaaaasg", "aaaaaaaash",
                 "aaaaaaaasi", "aaaaaaaasj", "aaaaaaaask", "aaaaaaaasl", "aaaaaaaasm", "aaaaaaaasn", "aaaaaaaaso",
                 "aaaaaaaasp", "aaaaaaaasq", "aaaaaaaasr", "aaaaaaaass", "aaaaaaaast", "aaaaaaaasu", "aaaaaaaasv",
                 "aaaaaaaasw", "aaaaaaaasx", "aaaaaaaasy", "aaaaaaaasz", "aaaaaaaata", "aaaaaaaatb", "aaaaaaaatc",
                 "aaaaaaaatd", "aaaaaaaate", "aaaaaaaatf", "aaaaaaaatg", "aaaaaaaath", "aaaaaaaati", "aaaaaaaatj",
                 "aaaaaaaatk", "aaaaaaaatl", "aaaaaaaatm", "aaaaaaaatn", "aaaaaaaato", "aaaaaaaatp", "aaaaaaaatq",
                 "aaaaaaaatr", "aaaaaaaats", "aaaaaaaatt", "aaaaaaaatu", "aaaaaaaatv", "aaaaaaaatw", "aaaaaaaatx",
                 "aaaaaaaaty", "aaaaaaaatz", "aaaaaaaaua", "aaaaaaaaub", "aaaaaaaauc", "aaaaaaaaud", "aaaaaaaaue",
                 "aaaaaaaauf", "aaaaaaaaug", "aaaaaaaauh", "aaaaaaaaui", "aaaaaaaauj", "aaaaaaaauk", "aaaaaaaaul",
                 "aaaaaaaaum", "aaaaaaaaun", "aaaaaaaauo", "aaaaaaaaup", "aaaaaaaauq", "aaaaaaaaur", "aaaaaaaaus",
                 "aaaaaaaaut", "aaaaaaaauu", "aaaaaaaauv", "aaaaaaaauw", "aaaaaaaaux", "aaaaaaaauy", "aaaaaaaauz",
                 "aaaaaaaava", "aaaaaaaavb", "aaaaaaaavc", "aaaaaaaavd", "aaaaaaaave", "aaaaaaaavf", "aaaaaaaavg",
                 "aaaaaaaavh", "aaaaaaaavi", "aaaaaaaavj", "aaaaaaaavk", "aaaaaaaavl", "aaaaaaaavm", "aaaaaaaavn",
                 "aaaaaaaavo", "aaaaaaaavp", "aaaaaaaavq", "aaaaaaaavr", "aaaaaaaavs", "aaaaaaaavt", "aaaaaaaavu",
                 "aaaaaaaavv", "aaaaaaaavw", "aaaaaaaavx", "aaaaaaaavy", "aaaaaaaavz", "aaaaaaaawa", "aaaaaaaawb",
                 "aaaaaaaawc", "aaaaaaaawd", "aaaaaaaawe", "aaaaaaaawf", "aaaaaaaawg", "aaaaaaaawh", "aaaaaaaawi",
                 "aaaaaaaawj", "aaaaaaaawk", "aaaaaaaawl", "aaaaaaaawm", "aaaaaaaawn", "aaaaaaaawo", "aaaaaaaawp",
                 "aaaaaaaawq", "aaaaaaaawr", "aaaaaaaaws", "aaaaaaaawt", "aaaaaaaawu", "aaaaaaaawv", "aaaaaaaaww",
                 "aaaaaaaawx", "aaaaaaaawy", "aaaaaaaawz", "aaaaaaaaxa", "aaaaaaaaxb", "aaaaaaaaxc", "aaaaaaaaxd",
                 "aaaaaaaaxe", "aaaaaaaaxf", "aaaaaaaaxg", "aaaaaaaaxh", "aaaaaaaaxi", "aaaaaaaaxj", "aaaaaaaaxk",
                 "aaaaaaaaxl", "aaaaaaaaxm", "aaaaaaaaxn", "aaaaaaaaxo", "aaaaaaaaxp", "aaaaaaaaxq", "aaaaaaaaxr",
                 "aaaaaaaaxs", "aaaaaaaaxt", "aaaaaaaaxu", "aaaaaaaaxv", "aaaaaaaaxw", "aaaaaaaaxx", "aaaaaaaaxy",
                 "aaaaaaaaxz", "aaaaaaaaya", "aaaaaaaayb", "aaaaaaaayc", "aaaaaaaayd", "aaaaaaaaye", "aaaaaaaayf",
                 "aaaaaaaayg", "aaaaaaaayh", "aaaaaaaayi", "aaaaaaaayj", "aaaaaaaayk", "aaaaaaaayl", "aaaaaaaaym",
                 "aaaaaaaayn", "aaaaaaaayo", "aaaaaaaayp", "aaaaaaaayq", "aaaaaaaayr", "aaaaaaaays", "aaaaaaaayt",
                 "aaaaaaaayu", "aaaaaaaayv", "aaaaaaaayw", "aaaaaaaayx", "aaaaaaaayy", "aaaaaaaayz", "aaaaaaaaza",
                 "aaaaaaaazb", "aaaaaaaazc", "aaaaaaaazd", "aaaaaaaaze", "aaaaaaaazf", "aaaaaaaazg", "aaaaaaaazh",
                 "aaaaaaaazi", "aaaaaaaazj", "aaaaaaaazk", "aaaaaaaazl", "aaaaaaaazm", "aaaaaaaazn", "aaaaaaaazo",
                 "aaaaaaaazp", "aaaaaaaazq", "aaaaaaaazr", "aaaaaaaazs", "aaaaaaaazt", "aaaaaaaazu", "aaaaaaaazv",
                 "aaaaaaaazw", "aaaaaaaazx", "aaaaaaaazy", "aaaaaaaazz"]
        expect = [
            'aaaaaaaaaa',
            'aaaaaaaaab',
            'aaaaaaaaac',
            'aaaaaaaaad',
            'aaaaaaaaae',
            'aaaaaaaaaf',
            'aaaaaaaaag',
            'aaaaaaaaah',
            'aaaaaaaaai',
            'aaaaaaaaaj',
            'aaaaaaaaak',
            'aaaaaaaaal',
            'aaaaaaaaan',
            'aaaaaaaaao',
            'aaaaaaaaap',
            'aaaaaaaaaq',
            'aaaaaaaaar',
            'aaaaaaaaas',
            'aaaaaaaaat',
            'aaaaaaaaau',
            'aaaaaaaaav',
            'aaaaaaaaaw',
            'aaaaaaaaay',
            'aaaaaaaaaz',
            'aaaaaaaabc',
            'aaaaaaaabm',
            'aaaaaaaacb',
            'aaaaaaaacd',
            'aaaaaaaadc',
            'aaaaaaaade',
            'aaaaaaaaed',
            'aaaaaaaaef',
            'aaaaaaaafe',
            'aaaaaaaafg',
            'aaaaaaaagf',
            'aaaaaaaagh',
            'aaaaaaaahg',
            'aaaaaaaahi',
            'aaaaaaaaih',
            'aaaaaaaaij',
            'aaaaaaaaji',
            'aaaaaaaajk',
            'aaaaaaaakj',
            'aaaaaaaakl',
            'aaaaaaaalk',
            'aaaaaaaanm',
            'aaaaaaaano',
            'aaaaaaaaon',
            'aaaaaaaaop',
            'aaaaaaaapo',
            'aaaaaaaapq',
            'aaaaaaaaqp',
            'aaaaaaaaqr',
            'aaaaaaaarq',
            'aaaaaaaars',
            'aaaaaaaasr',
            'aaaaaaaast',
            'aaaaaaaats',
            'aaaaaaaatu',
            'aaaaaaaaut',
            'aaaaaaaauv',
            'aaaaaaaavu',
            'aaaaaaaavw',
            'aaaaaaaawv',
            'aaaaaaaawx',
            'aaaaaaaayx',
            'aaaaaaaayz',
            'aaaaaaaaza',
            'aaaaaaaazy'
        ]
        result = self.solution.findWords(board, words)
        self.assertEqual(set(expect), set(result))
