package algospecialization.greedyandspanningtrees.datastructure;

import java.util.Objects;

public class Key implements Comparable<Key> {
  private int attribute;
  private int len;

  public int getAttribute() {
    return attribute;
  }

  public int getLen() {
    return len;
  }

  public Key(int attribute, int len){
    this.attribute = attribute;
    this.len = len;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Key key = (Key) o;
    return attribute == key.attribute &&
        len == key.len;
  }

  @Override
  public int hashCode() {
    return Objects.hash(attribute, len);
  }

  @Override
  public int compareTo(Key o) {
    if(this.len < o.len) return -1;
    if(this.len > o.len) return 1;
    return 0;
  }
}
