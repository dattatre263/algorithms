package algospecialization.divideandconquer.week2;

import edu.princeton.cs.algs4.Point2D;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;

public class ClosestpairFunctional {

  private double minDistance = Double.POSITIVE_INFINITY;
  private Point2D a = null;
  private Point2D b = null;
  private Point2D[] aux;
  private Point2D[] X;
  private Point2D[] Y;

  /**
   * @param points Pseudocode a) Using mergeSort ( Arrays.sort() ) sort the points based on the X
   *     axis b) Divide the ponts Array until base case a) Base case is when
   */
  public ClosestpairFunctional(Point2D[] points) {
    X = new Point2D[points.length];
    Y = new Point2D[points.length];
    aux = new Point2D[points.length];
    for (int i = 0; i < points.length; i++) X[i] = points[i];
    Arrays.sort(X, Point2D.X_ORDER);
    for (int i = 0; i < points.length; i++) Y[i] = X[i];
    closest(0, X.length - 1);
  }

  public double closest(int start, int end) {
    if (start >= end) {
      return Double.POSITIVE_INFINITY;
    }
    int mid = start + ((end - start) / 2);
    Point2D median = X[mid];
    double d1 = closest(start, mid);
    double d2 = closest(mid + 1, end);
    double delta = Math.min(d1, d2);
    merge(start, mid, end);
    int m = 0;
    for (int i = start; i <= end; i++) {
      if (Math.abs(Y[i].x() - median.x()) < delta) {
        aux[m++] = Y[i];
      }
    }
    for (int i = 0; i < m; i++) {
      for (int j = i + 1; (j < m) && (aux[j].y() - aux[i].y() < delta); j++) {
        double distance = aux[i].distanceTo(aux[j]);
        if (distance < delta) {
          delta = distance;
          if (distance < minDistance) {
            minDistance = delta;
            a = aux[i];
            b = aux[j];
            // StdOut.println("better distance = " + delta + " from " + best1 + " to " + best2);
          }
        }
      }
    }
    return delta;
  }

  private static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  private void merge(int start, int mid, int end) {
    for (int k = start; k <= end; k++) aux[k] = Y[k];

    int i = start;
    int j = mid + 1;
    for (int k = start; k <= end; k++) {
      if (i > mid) Y[k] = aux[j++];
      else if (j > end) Y[k] = aux[i++];
      else if (less(aux[j], aux[i])) Y[k] = aux[j++];
      else Y[k] = aux[i++];
    }
  }

  public static void main(String[] args) throws IOException {
    String inputfile = args[0];
    Point2D[] points =
        Files.lines(Paths.get(inputfile)).map(new StringToPoint()).toArray(Point2D[]::new);

    ClosestpairFunctional closest = new ClosestpairFunctional(points);
    System.out.println(closest.minDistance + " from " + closest.a + " to " + closest.b);
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
