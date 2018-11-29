package edu.sdsu.cs.datastructures;

import java.util.*;

public class DirectedGraph<V> implements IGraph<V> {
    TreeMap<V, LinkedList<V>> adjacencyList = new TreeMap<V, LinkedList<V>>();

    public DirectedGraph(){
    }

    public String toString(){
        return "Temp String";
    }

    @Override
    public void add(V vertexName) {
        if (!adjacencyList.containsKey(vertexName)) {
            adjacencyList.put(vertexName, new LinkedList<V>());
        }
    }

    @Override
    public void connect(V start, V destination) {
        if (!adjacencyList.containsKey(start) || !adjacencyList.containsKey(destination)){
            throw new java.util.NoSuchElementException("There is no element matching the start or destination");
        }
        adjacencyList.get(start).add(destination);
    }

    @Override
    public void clear() {
        adjacencyList = new TreeMap<V, LinkedList<V>>();
    }

    @Override
    public boolean contains(V label) {
        return adjacencyList.containsKey(label);
    }

    @Override
    public void disconnect(V start, V destination) {
        if (!adjacencyList.containsKey(start) || !adjacencyList.containsKey(destination)){
            throw new java.util.NoSuchElementException("There is no element matching the start or destination");
        }
        adjacencyList.get(start).remove(destination);
    }

    @Override
    public boolean isConnected(V start, V destination) {
        return adjacencyList.get(start).contains(destination);
    }

    @Override
    public Iterable<V> neighbors(V vertexName) {
        if (!adjacencyList.containsKey(vertexName)){
            throw new java.util.NoSuchElementException("The vertex is not present in the graph");
        }
            return adjacencyList.get(vertexName);
    }

    @Override
    public void remove(V vertexName) {
        if (!adjacencyList.containsKey(vertexName)){
            throw new java.util.NoSuchElementException("The vertex is not present in the graph");
        }
        adjacencyList.remove(vertexName);
    }

    @Override
    public List<V> shortestPath(V start, V destination) {
        if (!adjacencyList.containsKey(start) || !adjacencyList.containsKey(destination)){
            throw new java.util.NoSuchElementException("There is no element matching the start, add first");
        }
//        TreeMap<V, List<V>> paths = new TreeMap<V, List<V>>();
//        LinkedList<V> visited = new LinkedList<V>();
//        PriorityQueue<V> toBeVisited = new PriorityQueue<V>();
//        toBeVisited.add(start);
//        while(!toBeVisited.isEmpty()) {
//
//        }
          return null;
    }

    @Override
    public int size() {
        return adjacencyList.size();
    }

    @Override
    public Iterable<V> vertices() {
        List<V> vertices = new LinkedList<V>(adjacencyList.keySet());
        return vertices;
    }

    @Override
    public IGraph<V> connectedGraph(V origin) {
        LinkedList<V> or = adjacencyList.get(origin);
        LinkedList<V> added = new LinkedList<V>();
        PriorityQueue<V> toBeAdded = new PriorityQueue<V>();
        toBeAdded.addAll(adjacencyList.get(origin));
        DirectedGraph<V> graph = new DirectedGraph<V>();
        while(!toBeAdded.isEmpty()){
            V vertex = toBeAdded.remove();
            added.add(vertex);
            graph.add(vertex);
            for(V destination : adjacencyList.get(vertex)){
                graph.connect(vertex, destination);
                if(!added.contains(destination)){
                    toBeAdded.add(destination);
                }
            }

        }
        return graph;
    }
}