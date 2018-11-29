import sun.awt.image.ImageWatched;

import java.util.*;

public class DirectedGraph<V> implements IGraph<V> {
    TreeMap<V, LinkedList<V>> adjacencyList = new TreeMap<V, LinkedList<V>>();

    public DirectedGraph() {
    }

    public DirectedGraph(IGraph<V> data) {
    }

    public String toString() {
        String out = "Graph with " + adjacencyList.size() + " vertecies\n";
        V keys[] = (V[]) adjacencyList.keySet().toArray();

        for (int i = 0; i < keys.length; i++) {
            out += i + ": " + keys[i] + "\n";
            for (V dest : adjacencyList.get(keys[i])) {
                out += "    -" + dest + "\n";
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
        if (!adjacencyList.containsKey(start) || !adjacencyList.containsKey(destination)) {
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
        if (adjacencyList.get(start) == null || adjacencyList.get(destination) == null) {
            throw new NoSuchElementException("Start or end was not found");
        }
        adjacencyList.get(start).remove(destination);
    }

    @Override
    public boolean isConnected(V start, V destination) {
        if(!adjacencyList.containsKey(start) || !adjacencyList.containsKey(destination)){
            throw new NoSuchElementException();
        }
        return shortestPath(start, destination) != null;
    }

    @Override
    public Iterable<V> neighbors(V vertexName) {
        List<V> neighbors = adjacencyList.get(vertexName);
        if (neighbors != null) return neighbors;
        throw new NoSuchElementException();
    }

    @Override
    public void remove(V vertexName) {

        if (adjacencyList.containsKey(vertexName)) {
            adjacencyList.remove(vertexName);
        } else {
            throw new NoSuchElementException();
        }
    }
    @Override
    public List<V> shortestPath(V start, V destination) {
        if(!adjacencyList.containsKey(start) || !adjacencyList.containsKey(destination)){
            throw new NoSuchElementException();
        }
        LinkedList<V> unvisited = new LinkedList<V>(adjacencyList.keySet());
        TreeMap<V, LinkedList<V>> distances = new TreeMap<V, LinkedList<V>>();
        PriorityQueue<V> toBeVisited = new PriorityQueue<V>();
        toBeVisited.add(start);

        for(V key : unvisited){
            distances.put(key, null);
        }
        distances.put(start, new LinkedList<V>());
        //
        while(toBeVisited.size() > 0){
            V node = toBeVisited.remove();
            if(node.equals(destination)){
                LinkedList<V> toReturn = new LinkedList<V>();
                toReturn.add(start);
                toReturn.addAll(distances.get(node));
                return toReturn;
            }
            if(!unvisited.contains(node)) continue;
            unvisited.remove(node);
            LinkedList<V> path = distances.get(node);
            for(V neighbor : adjacencyList.get(node)){
                if(distances.get(neighbor) == null || distances.get(neighbor).size() > path.size()){
                    path.add(neighbor);
                    distances.put(neighbor, new LinkedList<V>(path));
                    path.remove(neighbor);
                    if(unvisited.contains(neighbor)){
                        toBeVisited.add(neighbor);
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
        toBeAdded.add(origin);
        DirectedGraph<V> graph = new DirectedGraph<V>();
        while(!toBeAdded.isEmpty()){
            V vertex = toBeAdded.remove();
            added.add(vertex);
            graph.add(vertex);
            for(V destination : adjacencyList.get(vertex)){
                added.add(destination);
                graph.add(destination);
                graph.connect(vertex, destination);
                toBeAdded.add(destination);
            }

        }
        return graph;
    }
}
