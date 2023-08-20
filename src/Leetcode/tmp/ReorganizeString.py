class Solution:
    def reorganizeString(self, s: str) -> str:
        dic=Counter(s)
        heap=[[-count,char] for char,count in dic.items()]
        heapq.heapify(heap)
        prev,output=None,""
        while prev or heap:
            if prev and not heap:
                return ""
            count,char=heapq.heappop(heap)
            count+=1
            output+=char
            if prev:
                heapq.heappush(heap,prev)
                prev=None
            if count!=0:
                prev=[count,char]
        return output