// Name:
// Student number:
import java.util.ArrayList;
import java.util.List;
 
public class Graph {
	
	private List<Vertex> verticesList;

	public Graph() {
		this.verticesList = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		this.verticesList.add(vertex);
	}

	public void reset() {
		for(Vertex vertex : verticesList) {
			vertex.setVisited(false);
			vertex.setPredecessor(null);
			vertex.setDistance(Double.MAX_VALUE);
		}
	}

	////// Implement the methods below this line //////

	public List<Vertex> getShortestPath(Vertex sourceVertex, Vertex targetVertex) {

		// Your code here
	}

	public double getShortestPathDistance(Vertex sourceVertex, Vertex targetVertex) {

		// Your code here
	}

}