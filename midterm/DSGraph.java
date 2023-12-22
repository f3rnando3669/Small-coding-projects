package midterm;

import java.util.LinkedList;

/**
 * This is a class to hold a graph.
 */

public class DSGraph {
    // Our graph is a DSHashMap, mapping strings to a DSArrayList of neighbors
    DSHashMap<DSArrayList<String>> graph;

    public DSGraph(){
        this.graph = new DSHashMap<>();
    }

    /**
     * Adds a vertex to the graph, if it is not already there.
     * 
     * @param v The String that represents the new vertex
     */
    public void addVertex(String v){
        // Make sure v1 exists as a vertex
        if(!graph.containsKey(v)){
            graph.put(v, new DSArrayList<String>());
        }
    }

    /**
     * Add an edge between vertices v1 and v2.
     * If v1 and/or v2 do not already exist, create them.
     * 
     * @param v1 The first vertex
     * @param v2 The second vertex
     */
    public void addEdge(String v1, String v2){
        // Make sure v1 exists as a vertex
        if(!graph.containsKey(v1)){
            graph.put(v1, new DSArrayList<String>());
        }
        // Make sure v2 exists as a vertex
        if(!graph.containsKey(v2)){
            graph.put(v2, new DSArrayList<String>());
        }
        // Add v1 and v2 to each others' neighbor lists
        graph.get(v1).add(v2);
        graph.get(v2).add(v1);
    }


/* BFS:Graph search techniches that searches for a start node layer by layer 
 * good for finding shortest path on graphs
*/

    /**
     * Determine whether or not the graph has a triangle.
     * 
     * A triangle is a set of 3 vertices {A, B, C} such that the three
     * edges (A, B), (A, C) and (B, C) are all present in the graph
     * 
     * @return true if the graph contains no triangle, otherwise false
     */
public Bolean triangleFree(){  
    //bfs through graph
    LinkedList<String> q = new LinkedList<>();   
    q.push(a);
    while(!q.isEmpty){
        String v = q.removeFirst();
        for(String nbr : graph.get(v)){
            int counts =0;
            if(visited.containskey(b)) continue;
            q.add(nbr);
            visited.put(nbr, "");
            parent.put(nbr, v);
            counts+=1;
        }
     /*every 3 vertices we check if the start of the path equals the end vertex
    * if it does it means there is a triangle 
     */
            for (int i = 0;i < n;i++) 
   for (int j = i+1;j < counts;j++) 
   if ( q.get(i)== q.get(j)) 
      for (int k = j+1;k < n;k++) 
         if (q.get(i)== q.get(k) &&  q.get(k) == q.get(j)){
                return true;
            } else {
                return false;
            }
        
        
    }
}

   
    
     
    /**
     * Produce the number of connected components in this DSGraph
     * loop over all vertices (outer loop)
     * if v is not visited
     * bfs from v
     * add one every time connect neighbhors
     * @return The number of components
     * public void Discover all compomnetns 
     * loop over vertecies
     * 
     */
   public int numberOfComponents(){
    LinkedList<String> q = new LinkedList<>();   
    DSHashMap<String> parent = new DSHashMap<>();
    DSHashMap<String> visited = new DSHashMap<>();
    int count=0;
    //bfs through the graph
        while(!q.isEmpty){
          String v = q.removeFirst();
             for(String nbr : graph.get(v)){
               if(visited.containskey(nbr)) continue;
                  q.add(nbr);
                  visited.put(nbr, "");
                        parent.put(nbr, v);
                        //add one for every neigbhor visited
                        count+=1;
                    }
                }
    return count;
   }
   
   
   
   
   
    /**
     * Decide whether or not this graph has a square.
     *
     * A square is a set of four vertices v, w, x, y such that
     * the edges (v, w), (w, x), (x, y) and (y, v) are all in this graph
     */
    public Boolean hasASquare(){
	return false;
    }
    
    
    
    
    
    
    
    
    // Each vertex maps to a list of its neighbors

    // Finds a shortest path from start to end in this graph
    
    public void shortestPath(String start, String end) {
        LinkedList<String> q = new LinkedList<>();
        DSHashMap<String> parent = new DSHashMap<>();
        // visited keeps track of vertices we've seen before
        DSHashMap<String> visited = new DSHashMap<>();
        q.push(start);

        while(!q.isEmpty){
            String v = q.removeFirst();
            for(String nbr : graph.get(v)){
                if(visited.containskey(nbr)) continue;
                q.add(nbr);
                visited.put(nbr, "");
                parent.put(nbr, v);
            }
        }
        //disover undicovered vertices
        for(String v : graph){
            if (!visited.containskey(v)){
                System.out.printf("this vertecie is unreachabel! from %s; %s/n", start, v);
            }
        }
        //if there is no path....
        if(!visited.containskey(end)){
         System.out.println(end + " is unreachable from the " + start);
        }
        //there is a path. Lets print it
        String path = "";
        String vertex = end; //where we are as we walk backward on the graph
        while(vertex != start){
            //stick the current vertex at the start if the path
            path = vertex+ "-" + path;
            //update the vertex we are at
            vertex = parent.get(vertex); //moves through the parent
        }
        path = start + "-" + path;
        System.out.println(path);
    }
}



