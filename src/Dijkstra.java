import java.util.Scanner;

/**
 * Created by matthewtduffin on 25/10/2016.
 */
public class Dijkstra {

  public Dijkstra(int size) {
    WeightedGraph graph = new WeightedGraph(size);
    System.out.println(graph.toString());
    System.out.println();
    System.out.println("Enter the nodes that you would like to travel between, separated by a space.");
    Scanner in = new Scanner(System.in);
    int start = in.nextInt();
    int end = in.nextInt();

    System.out.println(graph.dijkstras(start, end));

  }

}
