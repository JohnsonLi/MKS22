
//DFS
import java.util.*;

public class FrontierStack implements Frontier {
    private Stack<Location> locations = new Stack<>();

    public void add(Location n) {
        locations.push(n);
    }

    public Location next() {
        return locations.pop();
    }

    public boolean hasNext() {
        return locations.size() >= 1;
    }

}