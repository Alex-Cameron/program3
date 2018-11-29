package edu.sdsu.cs.datastructures;

import java.util.*;

public class DirectedGraph<V> implements IGraph<V> {
    TreeMap<V, LinkedList<V>> adjacencyList = new TreeMap<V, LinkedList<V>>();

    public DirectedGraph(){
    }

    public DirectedGraph(IGraph<V> data){
    }

    public String toString(){
        String out = "Graph with " + adjacencyList.size() + " vertecies\n";
        V keys[] = (V[]) adjacencyList.keySet().toArray();

        for(int i = 0; i < keys.length; i++){
            out += i + ": " + keys[i]+ "\n";
            for(V dest : adjacencyList.get(keys[i])){
                out += "    -" + dest +"\n";
            }
        }
        return out;
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
            throw new java.util.NoSuchElementException("There is no element matching the start[" + start + "] or the end[" + destination + "], add first");
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
        if(adjacencyList.get(start) == null || adjacencyList.get(destination) == null){
            throw new NoSuchElementException("Start or end was not found");
        }
        adjacencyList.get(start).remove(destination);
    }

    @Override
    public boolean isConnected(V start, V destination) {
        return adjacencyList.get(start).contains(destination);
    }

    @Override
    public Iterable<V> neighbors(V vertexName) {
        List<V> neighbors = adjacencyList.get(vertexName);
        if(neighbors != null) return neighbors;
        throw new NoSuchElementException();
    }

    @Override
    public void remove(V vertexName) {

        if(adjacencyList.containsKey(vertexName)){
            adjacencyList.remove(vertexName);
        } else {
            throw new NoSuchElementException();
        }
    }

    //    public static Comparator<LinkedList> pathComparator = new Comparator<LinkedList>(){
//
//        @Override
//        public int compare(LinkedList<V> c1, LinkedList<> c2) {
//            return (int) (c1.getId() - c2.getId());
//        }
//    };
    @Override
    public List<V> shortestPath(V start, V destination) {
        TreeMap<V, LinkedList<V>> paths = new TreeMap<V, LinkedList<V>>();
        LinkedList<V> visited = new LinkedList<V>();
        PriorityQueue<V> toBeVisited = new PriorityQueue<V>();
        toBeVisited.add(start);
        paths.put(start, new LinkedList<V>());
        while(!toBeVisited.isEmpty()) {
            V node = toBeVisited.remove();
            LinkedList<V> path = paths.get(node);
//            if(paths.get(node) != null && paths.get(node).size() > 0){
//                path = new LinkedList(paths.get(node));
//            } else {
//                path = new LinkedList<V>();
//            }
//            path.add(node);
            if(node == destination){
                return path;
            } else {
                LinkedList<V> neighbors = adjacencyList.get(node);
                for(V neighbor : neighbors){
                    if(paths.containsKey(neighbor)){
                        if(paths.get(neighbor).size() > path.size()){
                            paths.put(neighbor, path);
                        }
                    } else {
                        path.add(neighbor);
                        paths.put(neighbor, path);
                        visited.add(neighbor);
                        for(V SecondNeighbor : adjacencyList.get(neighbor)){
                            if(!visited.contains(SecondNeighbor)){
                                toBeVisited.add(SecondNeighbor);
                            }
                        }
                    }

                }
            }

        }
        return null;
    }

    @Override
    public int size() {
        return adjacencyList.size();
    }

    @Override
    public Iterable<V> vertices() {
        return adjacencyList.keySet();
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