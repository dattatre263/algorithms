package leetcode.amzn.treesgraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Courses {


  public boolean canFinish(int numCourses, int[][] prerequisites) {
    //build a directed graph
    //topological sort
    return true;
  }



  public static void main(String[] args) {
    Courses courses = new Courses();
    courses.canFinish(2, new int[][] {{0, 1}, {1, 0}});
  }
}
