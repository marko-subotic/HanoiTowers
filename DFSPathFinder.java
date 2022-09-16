import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

class DFSPathFinder {
    private boolean[] marked;  // marked[v] = whether there is a path from the source to vertex 'v'
    private int[] edgeTo;      // edgeTo[v] = the last edge on the path from the source to vertex 'v'
    private final int source;  // source vertex
    
    public DFSPathFinder(Digraph G, int s) {
        // ADD YOUR CODE HERE. IMPLEMENT DFS HERE!
        if(s<0 || s>=G.V()){
            throw new IllegalArgumentException();
        }
        edgeTo = new int [G.V()];
        marked = new boolean[G.V()];
        source = s;
        
        ArrayList<Integer> neighbors;

        Stack<Integer> depthFirst = new Stack<Integer>();
        depthFirst.push(source);
        int current = s;
        while(!depthFirst.empty()){
            current = depthFirst.pop();
            marked[current] = true;
            neighbors = adjToVertex(G,current);
            if(!visitedNeighbors(neighbors)){
                int next = pickUnvisitedNeighbor(neighbors);
                depthFirst.push(current);
                depthFirst.push(next);
                edgeTo[next] = current;
            }
        }
        
    }
    
    private int pickUnvisitedNeighbor(ArrayList<Integer> neighbors){
        int i = 0;
        while(i<neighbors.size()){
            if(!marked[neighbors.get(i)]){
                return neighbors.get(i);
            }
            i++;
        }
        return -1;
    }
    
    private ArrayList<Integer> adjToVertex(Digraph G, int s){
        Iterable<Integer> iterable = G.adj(s);
        ArrayList<Integer> list = new ArrayList<>();
        for (int element : iterable)
            list.add(element);
            
        return list;
    }
    
    private boolean visitedNeighbors(ArrayList<Integer> neighbors){
        for(int i = 0; i < neighbors.size();i++){
            if(!marked[neighbors.get(i)]){
                return false;
            }
        }
        return true;
    }
    
    /** Returns whether there exists a directed path from the source vertex and vertex {@code v}. */
    public boolean hasPathTo(int v) {
        // ADD YOUR CODE HERE.
        if(v<0 || v>=marked.length){
            throw new IllegalArgumentException();
        }
        if(marked[v]==true){
            return true;
        }
        return false;
    }
    
    /** Returns a path between the source vertex {@code s} and vertex {@code v}, or {@code null} if no such path. */
    public Iterable<Integer> pathTo(int v) {
        // ADD YOUR CODE HERE.
        if(!hasPathTo(v)){
            return null;
        }
        ArrayList<Integer> rtrn = new ArrayList<Integer>(0);
        int current = v;
        //rtrn.add(current);
        while(edgeTo[current]!=source){
            rtrn.add(0,edgeTo[current]);
            current = edgeTo[current];
        }
        if(edgeTo[v]==source){
            rtrn.add(source);
        }
        return rtrn;
    }
}

class Digraph {
    private int V;
    private int E;
    private List<Integer>[] edges;

    public Digraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        }
        this.V = V;
        this.E = 0;
        this.edges = (List<Integer>[]) new List[V];
    }
    
    /** Adds the directed edge v→w to this graph. */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        if (edges[v] == null) {
            edges[v] = new ArrayList<Integer>();
        }
        edges[v].add(w);
        E++;
    }
    
    /** Returns the vertices adjacent to vertex {@code v}. */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        if (edges[v] == null) {
            edges[v] = new ArrayList<Integer>();
        }
        return edges[v];
    }
    
    /** Returns the number of edges in this graph. */
    public int E() {
        return E;
    }
    
    /** Returns the number of vertices in this graph. */
    public int V() {
        return V;
    }
    
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }
}

class Runner
{

	public static void main(String[] args)
	{
		Digraph G = new Digraph(8);
		G.addEdge(0, 1);
		G.addEdge(1, 4);
		G.addEdge(4, 3);
		G.addEdge(4, 7);
		G.addEdge(5, 2);
		G.addEdge(7, 5);
		G.addEdge(7, 6);
		
		int s = 0;
		DFSPathFinder dfs = new DFSPathFinder(G, s);
		for (int i = 0; i < G.V(); ++i) {
		    System.out.println("Path from 0 to " + i + ": " + dfs.pathTo(i));
		}
	}
}
