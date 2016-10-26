import java.util.*;

/**
 * Created by matthewtduffin on 25/10/2016.
 */
public class WeightedGraph {
  ArrayList<GraphNode> nodes;
  Random random;
  int size;

  ArrayList<GraphNode> settled, unsettled;
  HashMap<GraphNode, Integer> distanceFromSource;
  HashMap<GraphNode, String> pathFromScource;

  public WeightedGraph(int numOfNodes) {
    this.size = numOfNodes;
    this.random = new Random();

    this.nodes = new ArrayList<>();

    this.makeConnections();

  }

  private void makeConnections() {
    for (int i=0; i < this.size; i++) {
      GraphNode newNode = new GraphNode(i);
      for (GraphNode g : nodes) {
        if (random.nextBoolean()) {
          g.connect(newNode,random.nextInt(99)+1);
        }
      }
      this.nodes.add(newNode);
    }
  }

  public String dijkstras(int start, int end) {
    //input checks for start and end values
    if (start < 0 || start > this.size || end < 0 || end > this.size) {
      return "Invalid parameters, start and end must be between 0 and "+size;
    } else if (start == end) {
      return "Distance: 0\nPath: null";
    }

    //main body of algorithm
    settled = new ArrayList<>(); unsettled = new ArrayList<>();
    distanceFromSource = new HashMap<>(); pathFromScource = new HashMap<>();

    GraphNode source = nodes.get(start), destination = nodes.get(end);

    unsettled.add(source);
    distanceFromSource.put(source,0);
    pathFromScource.put(source, ""+start);

    while (!unsettled.isEmpty()) {
      GraphNode current = getLowestUnsettledWeightNode();
      if (current.equals(destination)) {
        return "Distance from "+start+" to "+end+": "+distanceFromSource.get(current) +
                  "\nPath taken: "+pathFromScource.get(current);
      }
      moveToSettled(current);
      addNeighbours(current);
    }

    return "No path exists between "+start+" and "+end;
  }

  private GraphNode getLowestUnsettledWeightNode() {
    GraphNode lowest = unsettled.get(0);
    int lowestDistance = distanceFromSource.get(lowest);

    for (GraphNode n : unsettled) {
      if (distanceFromSource.get(n) < lowestDistance) {
        lowest = n;
        lowestDistance = distanceFromSource.get(n);
      }
    }

    return lowest;
  }

  private void moveToSettled(GraphNode n) {
    unsettled.remove(n);
    settled.add(n);
    n.settled = true;
  }

  private void addNeighbours(GraphNode current) {
    for (GraphNode g : current.neighbours.keySet()) {
      if (!g.settled) {
        int currentDistance = distanceFromSource.get(g) == null ? Integer.MAX_VALUE : distanceFromSource.get(g);
        int newDistance = distanceFromSource.get(current) + current.distanceTo(g);
        if (newDistance < currentDistance) {
          distanceFromSource.put(g, newDistance);
          String newPath = pathFromScource.get(current)+", "+g.id;
          pathFromScource.put(g, newPath);
          if (!unsettled.contains(g)) {
            unsettled.add(g);
          }
        }
      }
    }
  }

  @Override
  public String toString() {
    String s = "*";
    for (GraphNode g : this.nodes) {
      s+="   "+g.id;
    }
    s+="\n";
    for (GraphNode g : this.nodes) {
      s+=g.id;
      for (GraphNode h : this.nodes) {
        if (g.distanceTo(h) > 9) {
          s+="  "+g.distanceTo(h);
        } else if (g.distanceTo(h) >= 0) {
          s+="   "+g.distanceTo(h);
        } else {
          s+="   0";
        }
      }
      s+="\n";
    }

    return s;
  }
}
