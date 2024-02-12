class Solution:
    def findPaths(self, m: int, n: int, c: int, i: int, j: int) -> int:
        MOD = 10**9 + 7

        @cache
        def f(i, j, c):
            if not (0 <= i < m and 0 <= j < n):
                return 1
            elif c:
                return sum(f(i+q,j,c-1) + f(i,j+q,c-1) for q in (-1,1))%MOD

            return 0

        return f(i, j, c)