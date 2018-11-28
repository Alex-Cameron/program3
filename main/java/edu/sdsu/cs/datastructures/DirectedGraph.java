package edu.sdsu.cs.datastructures;

import java.util.List;

public class DirectedGraph<V> implements IGraph<V> {

    @Override
    public void add(V vertexName) {

    }

    @Override
    public void connect(V start, V destination) {

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
