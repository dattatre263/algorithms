package algospecialization.divideandconquer.week1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

/**
 * function multiply(x, y) Input: Positive integers x and y, in binary Output: Their product n =
 * max(size of x, size of y) if n = 1: return xy xL, xR = leftmost ⌈n/2⌉, rightmost ⌊n/2⌋ bits of x
 * yL, yR = leftmost ⌈n/2⌉, rightmost ⌊n/2⌋ bits of y P1 =multiply(xL,yL) P2 =multiply(xR,yR) P3
 * =multiply(xL +xR,yL +yR) return P1 × 2**n +(P3 −P1 −P2)× 2**n/2 +P2
 */
public class Karatsuba {

  private static BigInteger calculate(BigInteger x, BigInteger y) {
    int maxLength = Math.max(x.toString().length(), y.toString().length());
    //    base case
    if (maxLength == 1)
      return x.multiply(y);

    if(maxLength % 2 != 0) maxLength--;

    int m = maxLength/2 ;

    BigInteger xL = x.divide(BigInteger.valueOf(10).pow(m));
    BigInteger xR = x.mod(BigInteger.valueOf(10).pow(m));

    BigInteger yL = y.divide(BigInteger.valueOf(10).pow(m));
    BigInteger yR = y.mod(BigInteger.valueOf(10).pow(m));


    BigInteger p1 = calculate(xL, yL);
    BigInteger p2 = calculate(xR, yR);
    BigInteger p3 = calculate(xL.add(xR), yL.add(yR));

    BigInteger p4 = p3.subtract(p1).subtract(p2);
    return p1.multiply(BigInteger.valueOf(10).pow(maxLength))
        .add(p4.multiply(BigInteger.valueOf(10).pow(m)))
        .add(p2);
  }

  private static int maxLength(BigInteger x, BigInteger y){
    return Math.max(x.toString().length(), y.toString().length());
  }

  public static void main(String[] args) throws IOException {
    String path = args[0];
    BufferedReader reader = new BufferedReader(new FileReader(path));
    BigInteger x = new BigInteger(reader.readLine());
    BigInteger y = new BigInteger(reader.readLine());
    System.out.println(calculate(x,y));
  }
}
