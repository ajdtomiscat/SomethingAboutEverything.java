import java.util.*;
public class treemap {
    public static void main(String[] args) {
        TreeMap<String, Integer> tm = new TreeMap<>();
        tm.put("India", 120);
        tm.put("China", 140);
        tm.put("US", 50);
        tm.put("Nepal", 10);
        System.out.println(tm);
        System.out.println(tm.firstKey());
        System.out.println(tm.lastKey());
    }
}
