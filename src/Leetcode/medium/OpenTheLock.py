class Solution:
    def openLock(self, deadends: List[str], target: str) -> int:
        if target == '0000':
            return 0
        if '0000' in deadends:
            return -1

        start = {'0000'}
        end = {target}
        stop = set(deadends)

        neighbors = {str(i): (str((i - 1) % 10), str((i + 1) % 10)) for i in range(10)}
        steps = 0
        while start and end:
            steps += 1
            if len(start) > len(end):
                start, end, = end, start
            temp = set()
            for lock in start:
                stop.add(lock)
                for i in range(4):
                    for n in neighbors[lock[i]]:
                        new_lock = lock[:i] + n + lock[i+1:]
                        if new_lock in end:
                            return steps
                        if new_lock in stop:
                            continue
                        temp.add(new_lock)
            start = temp

        return -1