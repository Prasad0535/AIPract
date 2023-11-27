import collections


def dfs(graph, root):
    visited, stack = set(), collections.deque([root])
    visited.add(root)

    while stack:
        vertex = stack.pop()
        print(str(vertex) + " ", end="")
        for neighbour in graph[vertex]:
            if neighbour not in visited:
                visited.add(neighbour)
                stack.append(neighbour)

if __name__ == '_main_':

 graph = { 0: [1, 2],  1: [0, 3, 4],  2: [0], 3: [1], 4: [2, 3]}
 print("Following is Breadth First Traversal: ")
 dfs(graph, 0)