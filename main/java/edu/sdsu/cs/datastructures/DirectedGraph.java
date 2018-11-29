package edu.sdsu.cs.datastructures;

import java.util.LinkedList;
import java.util.List;

public class DirectedGraph<V> implements IGraph<V> {

    List<V> adjacencyList = new LinkedList<>();

    public DirectedGraph(){
    }

    public DirectedGraph(IGraph<V> data){
    }

    public class Vertex {
        public V name;
        List<V> adjacencyList = new LinkedList();

        public Vertex(V name){
            this.name = name;
        }
    }

    public class Edge {
        public Vertex start;
        public Vertex end;
        public double weight;

        public Edge(Vertex source, Vertex destination, double cost){
            this.start = source;
            this.end = destination;
            this.weight = cost;
        }
    }

    public String toString(){
        return "Temp String";
    }

    @Override
    public void add(V vertexName) {
        if (!adjacencyList.contains(vertexName)) {
            Vertex v = new Vertex(vertexName);
        }
    }

    @Override
    public void connect(V start, V destination) {
        if (!adjacencyList.contains(start) || !adjacencyList.contains(destination)){
            throw new java.util.NoSuchElementException("Either the source or destination does not exist");
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(V label) {
        return false;
    }

    @Override
    public void disconnect(V start, V destination) {

    }

    @Override
    public boolean isConnected(V start, V destination) {
        return false;
    }

    @Override
    public Iterable<V> neighbors(V vertexName) {
        return null;
    }

    @Override
    public void remove(V vertexName) {

    }

    @Override
    public List<V> shortestPath(V start, V destination) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterable<V> vertices() {
        return null;
    }

    @Override
    public IGraph<V> connectedGraph(V origin) {
        return null;
    }
}
