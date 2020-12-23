public class FrontierPriorityQueue implements Frontier {
    private MyHeap<Location> locations = new MyHeap<Location>(false);

    public void add(Location n) {
        locations.add(n);
    }

    public Location next() {
        return locations.remove();
    }

    public boolean hasNext() {
        return locations.size() >= 1;
    }
}