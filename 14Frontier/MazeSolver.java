public class MazeSolver {
    private Maze maze;
    private Frontier frontier;
    private boolean animate = false;

    public MazeSolver(String mazeText) {
        maze = new Maze(mazeText);
    }

    // Default to BFS
    public boolean solve() {
        return solve(0);
    }

    // mode: required to allow for alternate solve modes.
    // 0: BFS
    // 1: DFS
    // 2: Greedy
    // 3: A*
    public boolean solve(int mode) {
        // initialize your frontier
        if (mode == 0) {
            frontier = new FrontierQueue();
        } else if (mode == 1) {
            frontier = new FrontierStack();
        } else if (mode == 2) {
            frontier = new FrontierPriorityQueue();
        } else if (mode == 3) {
            frontier = new FrontierPriorityQueue();
            maze.setAStar(true);
        }

        frontier.add(maze.getStart());
        // while there is stuff in the frontier:
        // get the next location
        // process the location to find the locations (use the maze to do this)
        // check if any locations are the end, if you found the end just return true!
        // add all the locations to the frontier
        while (frontier.hasNext()) {
            if (animate) {
                System.out.println(maze.toStringColor());
            }

            Location location = frontier.next();
            if (location.getX() == maze.getEnd().getX() && location.getY() == maze.getEnd().getY()) {
                while (location != null) {
                    if (location.getX() == maze.getEnd().getX() && location.getY() == maze.getEnd().getY()) {
                        maze.set(location.getX(), location.getY(), 'E');
                    } else if (location.equals(maze.getStart())) {
                        maze.set(location.getX(), location.getY(), 'S');
                    } else {
                        maze.set(location.getX(), location.getY(), '@');
                    }
                    if(animate){
                        System.out.println(maze.toStringColor());

                    }
                    location = location.getPrevious();
                }
                return true;
            }

            maze.set(location.getX(), location.getY(), '.');

            for (Location n : maze.getNeighbors(location)) {
                if (n != null) {
                    maze.set(n.getX(), n.getY(), '?');
                    frontier.add(n);
                }
            }
        }

        // when there are no more values in the frontier return false
        return false;
    }

    public String toString() {
        return maze.toString();
    }

    public void setAnimate(boolean b) {
        animate = b;
    }

    public void clearTerminal() {
        // erase terminal, go to top left of screen.

        System.out.println("\033[2J\033[1;1H");
    }

    public static void main(String[] args) {
        MazeSolver n = new MazeSolver("maze.txt");
        n.solve(0);
    }
}