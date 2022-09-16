import java.util.List;
import java.util.ArrayList;

class AdjacencyMatrixGraph {
    private int V;  // Number of vertices.
    private int E;  // Number of edges.
    private ArrayList<Integer>[] edges;
    
    /** Initializes a graph with {@code V} vertices. */
    public AdjacencyMatrixGraph(int Ver) {
        if(Ver<0){
            throw new IllegalArgumentException();
        }
        V=Ver;
        edges = new ArrayList[Ver];
        for(int i = 0; i < Ver; i++){
            edges[i] = new ArrayList<Integer>(Ver);
        }
    }
    
    /** Adds the undirected edge v-w to this graph. */
    public void addEdge(int v, int w) {
        // ADD YOUR CODE HERE.
        if(v<0 || v>=V){
            throw new IllegalArgumentException();
        } else if(w<0 || w>=V){
            throw new IllegalArgumentException();
        }
        edges[v].set(w,1);
        edges[w].set(v,1);
        /*
        edges[v][w] = 1;
        edges[w][v] = 1;*/
        E++;
    }
    
    
    /** Returns the vertices adjacent to vertex {@code v}. */
    public Iterable<Integer> adj(int v) {
        // ADD YOUR CODE HERE.
        if(v<0 || v>=V){
            throw new IllegalArgumentException();
        }
        return null;//new edges.iterator();
    }
    
    /** Returns the number of edges in this graph. */
    public int E() {
        return E;
        // ADD YOUR CODE HERE.
    }
    
    /** Returns the number of vertices in this graph. */
    public int V() {
        // ADD YOUR CODE HERE.
        return V;
    }
}

class TestHelper {
    static int[] toArray(Iterable<Integer> iterable) {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        for (int element : iterable) list.add(element);
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; ++i) result[i] = list.get(i);
        return result;
    }

    static int[][] getInternalAdjacencyMatrix(AdjacencyMatrixGraph graph) throws ClassNotFoundException, IllegalAccessException {
        java.lang.reflect.Field edgesField = null;
        for (java.lang.reflect.Field field : get2DArrayFields()) {
            if (field.getName().equals("edges")) {
                edgesField = field;
                break;
            }
        }
        if (edgesField == null) {
            throw new IllegalStateException("Missing int[][] edges field");
        }
        edgesField.setAccessible(true);
        return (int[][]) edgesField.get(graph);
    }

    private static java.util.List<java.lang.reflect.Field> get2DArrayFields() {
        java.util.List<java.lang.reflect.Field> arrayFields = new java.util.ArrayList<java.lang.reflect.Field>();
        java.lang.reflect.Field[] fields = AdjacencyMatrixGraph.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType().isArray()
                    && fields[i].getType().getComponentType().isArray()
                    && fields[i].getType().getComponentType().getComponentType().equals(Integer.TYPE)) {
                arrayFields.add(fields[i]);
            }
        }
        return arrayFields;
    }
}

class Runner
{
	public static int[] runTest()
	{
		AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(10);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(1, 8);
		return TestHelper.toArray(graph.adj(1));
	}

	public static void main(String[] args)
	{
		int[] returnVal = runTest();
	}
}
