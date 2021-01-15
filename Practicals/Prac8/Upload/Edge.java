/**
 * Name: Liam Burgess
 * Student Number: u18015001
 */

 public class Edge {

    public Vertex source;
    public Vertex target;

    public Edge(Vertex s, Vertex t) {
        source = s;
        target = t;
    }
    
    public String toString() {
        String sline = "Connects "+ source.val+ " to "+target.val;
        return sline;
    }
}