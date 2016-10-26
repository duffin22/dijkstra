import java.util.Scanner;

/**
 * Created by matthewtduffin on 25/10/2016.
 */
public class Main {

  public static void main(String[] args) {
    System.out.println("How many nodes would you like?");
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    new Dijkstra(n);
  }

}
