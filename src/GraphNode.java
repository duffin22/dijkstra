import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by matthewtduffin on 25/10/2016.
 */
public class GraphNode {
  public int id;
  public HashMap<GraphNode, Integer> neighbours;
  public boolean settled;

  public GraphNode(int id) {
    this.id = id;
    this.neighbours = new HashMap<>();
  }

  public void connect(GraphNode g, int weight) {
    this.neighbours.put(g, weight);
    g.neighbours.put(this,weight);
  }

  public int distanceTo(GraphNode g) {
    if (this.neighbours.get(g) != null) {
      return this.neighbours.get(g);
    }
    return -1;
  }


}
