import heapq
class Solution:
    def findTheCity(self, n, edges, distanceThreshold):
        # Create an adjacency list for the graph
        adj_list = {i: [] for i in range(n)}
        for frm, to, wt in edges:
            # Add an edge from 'frm' to 'to' with weight 'wt'
            adj_list[frm].append((to, wt))
            # Since the graph is undirected, add the reverse edge from 'to' to 'frm' with the same weight 'wt'
            adj_list[to].append((frm, wt))
        def dijkstra(src):
        # Initialize distances with infinity and set the source distance to 0
            dists = [float('inf')] * n
            dists[src] = 0
            # Use a priority queue (min heap) for selecting the node with the smallest distance
            pq = [(0, src)]
            while pq:
                cur_dist, cur_node = heapq.heappop(pq)
                if cur_dist > dists[cur_node]:
                    continue
                for neighbor, weight in adj_list[cur_node]:
                    new_dist = cur_dist + weight
                    if new_dist < dists[neighbor]:
                        dists[neighbor] = new_dist
                        heapq.heappush(pq, (new_dist, neighbor))

         # Count reachable cities within the threshold
            reachable_cities = sum(1 for d in dists if d <=distanceThreshold) - 1
            return reachable_cities

        min_reachable = float('inf')
        result_city = -1

        # Iterate through each city to find the result
        for city in range(n):
            curr_reachable = dijkstra(city)

            # Update result if the current city has fewer reachable cities
            if curr_reachable <= min_reachable:
                min_reachable = curr_reachable
                result_city = city

        return result_city