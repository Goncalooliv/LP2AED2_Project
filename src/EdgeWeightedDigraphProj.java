import edu.princeton.cs.algs4.*;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * The {@code EdgeWeightedDigraph} class represents a edge-weighted
 * digraph of vertices named 0 through <em>V</em> - 1, where each
 * directed edge is of type {@link DirectedEdgeProj} and has a real-valued weight.
 * It supports the following two primary operations: add a directed edge
 * to the digraph and iterate over all of edges incident from a given vertex.
 * It also provides methods for returning the indegree or outdegree of a
 * vertex, the number of vertices <em>V</em> in the digraph, and
 * the number of edges <em>E</em> in the digraph.
 * Parallel edges and self-loops are permitted.
 * <p>
 * This implementation uses an <em>adjacency-lists representation</em>, which
 * is a vertex-indexed array of {@link BagProj} objects.
 * It uses &Theta;(<em>E</em> + <em>V</em>) space, where <em>E</em> is
 * the number of edges and <em>V</em> is the number of vertices.
 * All instance methods take &Theta;(1) time. (Though, iterating over
 * the edges returned by {@link #adj(int)} takes time proportional
 * to the outdegree of the vertex.)
 * Constructing an empty edge-weighted digraph with <em>V</em> vertices
 * takes &Theta;(<em>V</em>) time; constructing an edge-weighted digraph
 * with <em>E</em> edges and <em>V</em> vertices takes
 * &Theta;(<em>E</em> + <em>V</em>) time.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class EdgeWeightedDigraphProj implements Serializable {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;                // number of nodes in this digraph
    private int E;                      // number of ways in this digraph
    private BagProj<DirectedEdgeProj>[] adj;    // adj[v] = adjacency list for vertex v
    private int[] indegree;             // indegree[v] = indegree of vertex v

    LinkedList<Integer> adjacencyList[];

    /**
     * Initializes an empty edge-weighted digraph with {@code V} vertices and 0 edges.
     *
     * @param V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public EdgeWeightedDigraphProj(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (BagProj<DirectedEdgeProj>[]) new BagProj[V];
        for (int v = 0; v < V; v++)
            adj[v] = new BagProj<DirectedEdgeProj>();

        adjacencyList = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjacencyList[i] = new LinkedList<>();
        }

    }

    /**
     * Initializes a random edge-weighted digraph with {@code V} vertices and <em>E</em> edges.
     *
     * @param V the number of vertices
     * @param E the number of edges
     * @throws IllegalArgumentException if {@code V < 0}
     * @throws IllegalArgumentException if {@code E < 0}
     */
    public EdgeWeightedDigraphProj(int V, int E) {
        this(V);
        if (E < 0) throw new IllegalArgumentException("Number of edges in a Digraph must be non-negative");
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01 * StdRandom.uniform(100);
            double averageTime = 0.01 * StdRandom.uniform(100);
            DirectedEdgeProj e = new DirectedEdgeProj(v, w, weight, averageTime);
            addEdge(e);
        }
    }

    /**
     * Returns the number of vertices in this edge-weighted digraph.
     *
     * @return the number of vertices in this edge-weighted digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this edge-weighted digraph.
     *
     * @return the number of edges in this edge-weighted digraph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V));
    }

    /**
     * Adds the directed edge {@code e} to this edge-weighted digraph.
     *
     * @param e the edge
     * @throws IllegalArgumentException unless endpoints of edge are between {@code 0}
     *                                  and {@code V-1}
     */
    public void addEdge(DirectedEdgeProj e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;

        adjacencyList[e.from()].addFirst(e.to());
        adjacencyList[e.to()].addFirst(e.from());
    }


    /**
     * Returns the directed edges incident from vertex {@code v}.
     *
     * @param v the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdgeProj> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns all directed edges in this edge-weighted digraph.
     * To iterate over the edges in this edge-weighted digraph, use foreach notation:
     * {@code for (DirectedEdge e : G.edges())}.
     *
     * @return all edges in this edge-weighted digraph, as an iterable
     */
    public Iterable<DirectedEdgeProj> edges() {
        BagProj<DirectedEdgeProj> list = new BagProj<DirectedEdgeProj>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdgeProj e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a string representation of this edge-weighted digraph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     * followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdgeProj e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Percorre o grafo para ver se ele é ou nao conexo
     * Sendo conexo, um grafo que de qualquer node consegue chegar a todos os outros
     */
    public void isConnected() {

        LinkedList<Integer>[] adjacencyList = this.adjacencyList;

        // Take a boolean visited array
        boolean[] visited = new boolean[this.V];

        // Start the DFS from vertex 1
        DFS(1, adjacencyList, visited);

        // Check if all the vertices are visited
        // Set connected to False if one node is unvisited
        boolean connected = true;

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                connected = false;
                break;
            }
        }

        if (connected) {
            System.out.println("Graph is connected");
        } else {
            System.out.println("Graph is disconnected");
        }
    }

    /**
     * Depth First Search usado para atravessar o grafo.
     * Começa no vertice source , marca o node como visitado e passa para um vertice adjacente nao marcado como visitado
     * Usamos isto no metodo isConnected para ver se o grafo é ou nao conexo
     *
     */
    public void DFS(int source, LinkedList<Integer>[] adjacencyList, boolean[] visited){

        // Mark the vertex visited as True
        visited[source] = true;

        // Travel the adjacent neighbours
        for (int i = 0; i <adjacencyList[source].size() ; i++) {

            int neighbour = adjacencyList[source].get(i);

            if(!visited[neighbour]){
                // Call DFS from neighbour
                DFS(neighbour, adjacencyList, visited);
            }
        }
    }

}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
