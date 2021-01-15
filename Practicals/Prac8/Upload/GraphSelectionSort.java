/**
 * Name: Liam Burgess
 * Student Number: u18015001
 */
import java.util.ArrayList;

//the fastest way would be to iterate through the entire edge array and set the lowest vertice to a variable
//after the variable has been found remove the edges and add to other array

public class GraphSelectionSort {

    private ArrayList<Integer> SortArray;
    private ArrayList<Vertex> VertArray;
    private ArrayList<Edge> edges;
    int NoVertices, sort;
    /**
     * Initialize a new object using the array of Edges
     * @param edg
     */
    public GraphSelectionSort(Edge[] edg) {
        edges = new ArrayList<>();
        VertArray = new ArrayList<>();
        SortArray = new ArrayList<>();
        
        for(int i=0; i<edg.length; i++) {
            edges.add(edg[i]);
        }
        
        for(int i=0; i<edg.length; i++) {
            if (VertArray.contains(edg[i].source) == false) {
                VertArray.add(edg[i].source);
            }
            if (VertArray.contains(edg[i].target) == false) {
                VertArray.add(edg[i].target);
            }
        }
    }

    /**
     * Return the array of sorted values.
     * @return 
     */
    public Integer[] getSorted() {
        if(SortArray.isEmpty())
            return new Integer[0];
        else {
            Integer ret[] = new Integer[SortArray.size()];
            for(int i=0; i<SortArray.size(); i++) {
                ret[i] = SortArray.get(i);
            }
            return ret;
        }
    }

    /**
     * Return the edges that are still in the graph
     * @return 
     */
    public Edge[] getEdges() {
        if(edges.isEmpty())
            return new Edge[0];
        else {
            Edge ret[] = new Edge[edges.size()];
            for(int i=0; i<edges.size(); i++) {
                ret[i] = edges.get(i);
            }
            return ret;
        }
    }

    /**
     * Remove the vertex with the lowest value from the graph
     * and append it to the sorted array.
     */
    public void doSortIteration() {
        if(isSorted()==true|| edges.isEmpty())
            return;
        //----------------------------------------------------------------------
        int i=0;
        Vertex v = VertArray.get(i);
        for(i=0; i<NoVertices; i++) {
            if(v.val>VertArray.get(i).val)
                v = VertArray.get(i);
        }
        RemoveEdges(v);
        VertArray.remove(v);
        SortArray.add(v.val);
    }

    /**
     * Return true if all elements are sorted and the graph contains no more vertices.
     * @return 
     */
    public Boolean isSorted() {
        return VertArray.isEmpty()==true;
    }
    
    /*
    Remove all edges containing removed vertex 
    */
    public void RemoveEdges(Vertex v) {
        for (int i=0; i<edges.size(); i++) {
            if (edges.get(i).source == v || edges.get(i).target == v) {
                edges.remove(i);
            }
        }
    }
}
