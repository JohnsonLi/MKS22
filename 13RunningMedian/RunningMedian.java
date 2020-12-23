public class RunningMedian {
    private MyHeap<Double> minHeap;
    private MyHeap<Double> maxHeap;
    private double median = 0;

    public RunningMedian() {
        minHeap = new MyHeap<>(false);
        maxHeap = new MyHeap<>();
    }

    public void add(Double value) {
        if (minHeap.size() == 0 && maxHeap.size() == 0) {
            median = value;
            maxHeap.add(value);
            return;
        } else {
            if (value <= median) {
                maxHeap.add(value);
            } else {
                minHeap.add(value);
            }
        }
        if (minHeap.size() - maxHeap.size() == 0) {
            median = (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else if (Math.abs(minHeap.size() - maxHeap.size()) == 1) {
            median = minHeap.size() > maxHeap.size() ? minHeap.peek() : maxHeap.peek();
        } else if (Math.abs(minHeap.size() - maxHeap.size()) == 2) {
            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.remove());
            } else {
                maxHeap.add(minHeap.remove());
            }
            median = (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }

    public Double getMedian() {
        return median;
    }

    public int size() {
        return minHeap.size() + maxHeap.size();
    }

    // public static void main(String[] args) {
    // RunningMedian n = new RunningMedian();
    // n.add(1.0);
    // n.add(2.0);
    // n.add(5.0);
    // // n.add(587.0);
    // n.add(2131312.0);
    // System.out.println(n.size());
    // System.out.println(n.getMedian());
    // }

}
