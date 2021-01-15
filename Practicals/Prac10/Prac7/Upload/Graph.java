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

            if(sourceVertex==targetVertex) {
                List<Vertex> ret = new ArrayList<Vertex>();
                ret.add(sourceVertex);
                return ret;
            }
                
            
            if(!(this.verticesList.contains(sourceVertex) && this.verticesList.contains(targetVertex))) {
                return new ArrayList<Vertex>();
            }
            
            
            this.reset();

            sourceVertex.setDistance(0);
            double min = Double.MAX_VALUE;
            List<Vertex> copy = new ArrayList<>();
            for(int i=0; i<verticesList.size(); i++) {
                copy.add(verticesList.get(i));
            }
            
            int i=0;
            for(i=0; i<copy.size()-1; i++)
                if(copy.get(i).equals(sourceVertex))
                    break;
            
            Vertex v = copy.get(i);
            copy.remove(i);
            
            while(copy.isEmpty()==false || v!=null) {
                for(i=0; i<v.getAdjacenciesList().size(); i++) { 
                    Vertex u = v.getAdjacenciesList().get(i).getEndVertex();
                    if(u.getDistance() > v.getDistance() + v.getAdjacenciesList().get(i).getWeight()) {
                        u.setDistance(v.getDistance() + v.getAdjacenciesList().get(i).getWeight());
                        u.setPredecessor(v);
                    }
                    
                    //System.out.println(u.getDistance());
                    //System.out.println(v.getDistance() + v.getAdjacenciesList().get(i).getWeight());
                    
                    if(u==targetVertex) {
                        if(u.getDistance() < min) {
                            min = u.getDistance();
                        }
                    }	
                }
                if(copy.size()==0) {
                    v=null;
                }
                else {
                    v = copy.get(0);
                    copy.remove(0);
                }
            }
                
                    List<Vertex> ret = new ArrayList<Vertex>(), rev = new ArrayList<Vertex>();
                    
                    if(min==Double.MAX_VALUE)
                        return ret;
                    
                    
                    v = targetVertex;
                    while(v!=sourceVertex) {
                    ret.add(v);
                        v = v.getPredecessor();
                    }
                    ret.add(v);
                    for(i=ret.size()-1; i>=0; i--) {
                        rev.add(ret.get(i));
                    }
                    return rev;
                
	}

	public double getShortestPathDistance(Vertex sourceVertex, Vertex targetVertex) {
            if(sourceVertex==targetVertex)
                return 0;
            
            if(!(this.verticesList.contains(sourceVertex) && this.verticesList.contains(targetVertex)))
                return Double.MAX_VALUE;
            
            this.reset();
            sourceVertex.setDistance(0);
            double min = Double.MAX_VALUE;
            List<Vertex> copy = new ArrayList<>();
            for(int i=0; i<verticesList.size(); i++) {
                copy.add(verticesList.get(i));
            }
            
            
            int i=0;
            for(i=0; i<copy.size()-1; i++)
                if(copy.get(i).equals(sourceVertex))
                    break;
            
            Vertex v = copy.get(i);
            copy.remove(i);
            
            while(copy.isEmpty()==false || v!=null) {
                for(i=0; i<v.getAdjacenciesList().size(); i++) { 
                    Vertex u = v.getAdjacenciesList().get(i).getEndVertex();
                    if(u.getDistance() > v.getDistance() + v.getAdjacenciesList().get(i).getWeight()) {
                        u.setDistance(v.getDistance() + v.getAdjacenciesList().get(i).getWeight());
                        u.setPredecessor(v);
                    }
                    
                    //System.out.println(u.getDistance());
                    //System.out.println(v.getDistance() + v.getAdjacenciesList().get(i).getWeight());
                    
                    if(u==targetVertex) {
                        if(u.getDistance() < min) {
                            min = u.getDistance();
                        }
                    }	
                }
                if(copy.size()==0) {
                    v=null;
                }
                else {
                    v = copy.get(0);
                copy.remove(0);
                }
            }
                return min;
        }

}