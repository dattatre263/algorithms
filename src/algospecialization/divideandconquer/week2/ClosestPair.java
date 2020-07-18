package algospecialization.divideandconquer.week2;

import edu.princeton.cs.algs4.Point2D;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;

public class ClosestPair {

  // closest pair of points and their Euclidean distance
  private Point2D best1, best2;
  private double bestDistance = Double.POSITIVE_INFINITY;
  private Point2D[] aux;
  private Point2D[] X;
  private Point2D[] Y;

  /**
   * Computes the closest pair of points in the specified array of points.
   *
   * @param points the array of points
   * @throws IllegalArgumentException if {@code points} is {@code null} or if any entry in {@code
   *     points[]} is {@code null}
   */
  public ClosestPair(Point2D[] points) {
    X = new Point2D[points.length];
    Y = new Point2D[points.length];
    aux = new Point2D[points.length];
    for (int i = 0; i < points.length; i++) X[i] = points[i];
    Arrays.sort(X, Point2D.X_ORDER);
    for (int i = 0; i < points.length; i++) Y[i] = X[i];
    closest(0, X.length - 1);
  }

  // find closest pair of points in pointsByX[lo..hi]
  // precondition:  pointsByX[lo..hi] and pointsByY[lo..hi] are the same sequence of points
  // precondition:  pointsByX[lo..hi] sorted by x-coordinate
  // postcondition: pointsByY[lo..hi] sorted by y-coordinate
  private double closest( int start, int end) {
    if (end <= start) return Double.POSITIVE_INFINITY;

    int mid = start + (end - start) / 2;
    Point2D median = X[mid];

    // compute closest pair with both endpoints in left subarray or both in right subarray
    double delta1 = closest(start, mid);
    double delta2 = closest(mid + 1, end);
    double delta = Math.min(delta1, delta2);

    // merge back so that pointsByY[lo..hi] are sorted by y-coordinate
    merge(start, mid, end);

    // aux[0..m-1] = sequence of points closer than delta, sorted by y-coordinate
    int m = 0;
    for (int i = start; i <= end; i++) {
      if (Math.abs(Y[i].x() - median.x()) < delta) aux[m++] = Y[i];
    }

    // compare each point to its neighbors with y-coordinate closer than delta
    for (int i = 0; i < m; i++) {
      // a geometric packing argument shows that this loop iterates at most 7 times
      for (int j = i + 1; (j < m) && (aux[j].y() - aux[i].y() < delta); j++) {
        double distance = aux[i].distanceTo(aux[j]);
        if (distance < delta) {
          delta = distance;
          if (distance < bestDistance) {
            bestDistance = delta;
            best1 = aux[i];
            best2 = aux[j];
            // StdOut.println("better distance = " + delta + " from " + best1 + " to " + best2);
          }
        }
      }
    }
    return delta;
  }

  /**
   * Returns one of the points in the closest pair of points.
   *
   * @return one of the two points in the closest pair of points; {@code null} if no such point
   *     (because there are fewer than 2 points)
   */
  public Point2D either() {
    return best1;
  }

  /**
   * Returns the other point in the closest pair of points.
   *
   * @return the other point in the closest pair of points {@code null} if no such point (because
   *     there are fewer than 2 points)
   */
  public Point2D other() {
    return best2;
  }

  /**
   * Returns the Eucliden distance between the closest pair of points.
   *
   * @return the Euclidean distance between the closest pair of points {@code
   *     Double.POSITIVE_INFINITY} if no such pair of points exist (because there are fewer than 2
   *     points)
   */
  public double distance() {
    return bestDistance;
  }

  // is v < w ?
  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
  // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
  private  void merge(int lo, int mid, int hi) {
    // copy to aux[]
    for (int k = lo; k <= hi; k++) {
      aux[k] = Y[k];
    }

    // merge back to a[]
    int i = lo, j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid) Y[k] = aux[j++];
      else if (j > hi) Y[k] = aux[i++];
      else if (less(aux[j], aux[i])) Y[k] = aux[j++];
      else Y[k] = aux[i++];
    }
  }

  /**
   * Unit tests the {@code ClosestPair} data type. Reads in an integer {@code n} and {@code n}
   * points (specified by their <em>x</em>- and <em>y</em>-coordinates) from standard input;
   * computes a closest pair of points; and prints the pair to standard output.
   *
   * @param args the command-line arguments
   */
  //  public static void main(String[] args) {
  //    int n = StdIn.readInt();
  //    Point2D[] points = new Point2D[n];
  //    for (int i = 0; i < n; i++) {
  //      double x = StdIn.readDouble();
  //      double y = StdIn.readDouble();
  //      points[i] = new Point2D(x, y);
  //    }
  //    ClosestPair closest = new ClosestPair(points);
  //    StdOut.println(closest.distance() + " from " + closest.either() + " to " + closest.other());
  //  }
  public static void main(String[] args) throws IOException {
    String inputfile = args[0];
    Iterator<String> it = Files.lines(Paths.get(inputfile)).iterator();
    Point2D[] points = new Point2D[100];
    int i = 0;
    while (it.hasNext()) {
      points[i++] = new StringToPoint().apply(it.next());
    }
    ClosestPair closest = new ClosestPair(points);
    System.out.println(closest.distance() + " from " + closest.either() + " to " + closest.other());
  }

  static class StringToPoint implements Function<String, Point2D> {
    @Override
    public Point2D apply(String xy) {
      double x = Double.valueOf(xy.substring(xy.indexOf(".") - 1, xy.indexOf(" ")));
      double y = Double.valueOf(xy.substring(xy.lastIndexOf(".") - 1, xy.indexOf("]")));
      Point2D point = new Point2D(x, y);
      return point;
    }
  }
}
