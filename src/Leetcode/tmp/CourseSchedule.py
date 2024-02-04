class Graph:
    def __init__(self):
        self.adj_list  = {}

    def add_edge(self,v1,v2):
        if self.adj_list.get(v1) is not None and  self.adj_list.get(v2) is not None:
            self.adj_list[v1].append(v2)

    def add_vertex(self,v1):
        if not self.adj_list.get(v1,False):
            self.adj_list[v1]=[]


    def create_graph(self,prerequisites)-> bool:
        for v1,v2 in prerequisites:
            if v1 == v2:
                return False
            self.add_vertex(v1)
            self.add_vertex(v2)
            self.add_edge(v1,v2)
        return True


    def dfs(self):
        visited = {}
        def traverse(current_vertex):

            visited[current_vertex] = 1
            if self.adj_list[current_vertex]:
                for vertex in self.adj_list[current_vertex]:
                    if  not visited.get(vertex,False):
                        a= traverse(vertex)
                        if a == False:
                            return False
                    else:
                        return False

            self.adj_list[current_vertex] =[]
            del visited[current_vertex]
            return True

        for vertex  in self.adj_list.keys():
            out = traverse(vertex)
            if out == False:
                return False

        return out


class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        graph = Graph()
        if not prerequisites:
            return True
        check = graph.create_graph(prerequisites)
        if not check:
            return False
        result = graph.dfs()
        return result
        