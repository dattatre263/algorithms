package leetcode.amzn.oa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.omg.CORBA.INTERNAL;

public class Solution1 {

  static class  Entity implements Comparable<Entity> {
    Long User;
    Long count;

    @Override
    public int compareTo(Entity o) {
      return this.count.compareTo(o.count);
    }
  }

  public static List<String> processLogs(List<String> logs, int threshold) {
    // Write your code here
    if(logs == null || logs.isEmpty()) return new ArrayList<>();
    Map<Long, Long> map = new HashMap<>();
    PriorityQueue<Entity> excessUsers =
        new PriorityQueue<>(Collections.reverseOrder());
    for (String log : logs) {
      if(log != null && !log.isEmpty()){
        String[] splits = log.split(" ");
        if(splits.length == 3){
          try{
            Long sender = Long.parseLong(splits[0]);
            Long receiver = Long.parseLong(splits[1]);
            addUserToMap(sender, receiver, map);
          }catch (Exception e){
            System.out.println(" bad input");
          }
        }
      }
    }
    for (Map.Entry<Long,Long> entry : map.entrySet()) {
      Entity record = new Entity();
      record.User = entry.getKey();
      record.count = entry.getValue();
      excessUsers.add(record);
    }
    List<String> ans = new ArrayList<>();
    boolean done = false;
    while (!done){
      Entity entity = excessUsers.poll();
      if(entity != null){
        if(entity.count >= threshold){
          ans.add(entity.User.toString());
        }else {
          done = true;
        }
      }

    }

    return ans;
  }

  private static void addUserToMap(Long sender, Long receiver, Map<Long, Long> map) {
    if (map.containsKey(sender)) {
      map.put(sender, map.get(sender) + 1);
    } else {
      map.put(sender, 1L);
    }
    if (!sender.equals(receiver)) {
      if (map.containsKey(receiver)) {
        map.put(receiver, map.get(receiver) + 1);
      } else {
        map.put(receiver, 1L);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    String a = "88 99 200";
    String b = "88 99 300";
    String c = "99 32 100";
    String d = "12 12 15";
    List<String> list = new ArrayList<>();
    list.add(a);
    list.add(b);
    list.add(c);
    list.add(d);
    Solution1.processLogs(list,2);

  }
}
