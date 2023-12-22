import heapq

class Solution(object):
    def networkDelayTime(self, times, n, k):
        # create an empty graph
        # go through times and set up the graph with edges 
        graph = {}
        for i in range(1, n + 1):
            graph[i] = {}
        for u, v, w in times:
            if v not in graph[u]:
                graph[u][v] = w
            else:
                graph[u][v] = min(graph[u][v], w)
        # start with a heap and initialize some distances
        # do the Dijkstra algorthm using heap
        heap = [(0, k)]
        distances = {}
        while heap:
            time, node = heapq.heappop(heap)
            # check if we already went through this node
            if node in distances:
                continue
            # taking note the time it takes to get there
            distances[node] = time
            # check neighbors and update heap
            for neighbor, weight in graph[node].items():
                heapq.heappush(heap, (time + weight, neighbor))
    # if we reached all nodes, return the longest time else not possible
        if len(distances) == n:
            return max(distances.values())
        else:
            return -1
