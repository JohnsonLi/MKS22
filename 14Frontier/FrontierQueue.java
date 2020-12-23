
//BFS
import java.util.*;

public class FrontierQueue implements Frontier {
    private LinkedList<Location> locations = new LinkedList<>();

    public void add(Location n) {
        locations.addLast(n);
    }

    public Location next() {
        return locations.removeFirst();
    }

    public boolean hasNext() {
        return locations.size() >= 1;
    }
}