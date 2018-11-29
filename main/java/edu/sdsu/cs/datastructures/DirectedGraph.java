package edu.sdsu.cs.datastructures;

import java.util.LinkedList;
import java.util.List;

public class DirectedGraph<V> implements IGraph<V> {
    TreeMap<V, LinkedList<V>> adjacencyList = new TreeMap<V, LinkedList<V>>();

    public DirectedGraph(){
    }

    public DirectedGraph(IGraph<V> data){
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
        if (!adjacencyList.containsKey(start)){
            throw new java.util.NoSuchElementException("There is no element matching the start, add first");
        }
        adjacencyList.get(start).add(destination);
    }

    @Override
    public void clear() {
        adjacencyList = new TreeMap<V, LinkedList<V>>();
    }

    @Override
    public boolean contains(V label) {
        return false;
    }

    @Override
    public void disconnect(V start, V destination) {
        adjacencyList.get(start).remove(destination);
    }

    @Override
    public boolean isConnected(V start, V destination) {
        return adjacencyList.get(start).contains(destination);
    }

    @Override
    public Iterable<V> neighbors(V vertexName) {
        return adjacencyList.get(vertexName);
    }

    @Override
    public void remove(V vertexName) {
        adjacencyList.remove(vertexName);
    }

    @Override
    public List<V> shortestPath(V start, V destination) {
        return null;
    }

    @Override
    public int size() {
        return adjacencyList.size();
    }

    @Override
    public Iterable<V> vertices() {
        return (List)adjacencyList;
    }

    @Override
    public IGraph<V> connectedGraph(V origin) {
        return null;
    }