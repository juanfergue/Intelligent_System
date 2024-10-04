package maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Astar extends Maze {

    private Set<Node> openSet;
    private Set<Node> closedSet;
    private Map<Node,Node> parent;
    boolean solved;

    public Astar() {
    	super();
        openSet = new HashSet<>();   // The set of tentative nodes to be evaluated
        closedSet = new HashSet<>(); // The set of nodes already evaluated
        parent= new HashMap<>();// The map of navigated nodes
        
    }
    
    //Calculate H, the distance between 2 Nodes
    private int calculateHeuristic(Node a, Node b) {
        return Math.abs(a.getROW() - b.getROW()) + Math.abs(a.getCOL() - b.getCOL());
    }
    
    //Get the Node with the lowest F from the openSet
    private Node getNodeWithLowestF() {
        Node result = null;
        int minF = Integer.MAX_VALUE;
        for (Node n : openSet) {
            if (n.getF() < minF) {
                minF = n.getF();
                result = n;
            }
        }
        return result;
    }

    //It stores in an ArrayList all of the valid neighbours of the current node
    private ArrayList<Node> getNeighbours(Node current) {
        int row = current.getROW();
        int col = current.getCOL();
        ArrayList<Node> neighbours = new ArrayList<>();

        // Check north neighbour
        if (row > 0 && maze[row - 1][col] != '#')
            neighbours.add(new Node(row - 1, col));

        // Check south neighbour
        if (row < ROWS - 1 && maze[row + 1][col] != '#') 
            neighbours.add(new Node(row + 1, col));

        // Check west neighbour
        if (col > 0 && maze[row][col - 1] != '#') 
            neighbours.add(new Node(row, col - 1));
   
        // Check east neighbour
        if (col < COLS - 1 && maze[row][col + 1] != '#')
            neighbours.add(new Node(row, col + 1));
        
        return neighbours;
    }
    
    //It checks if a Node e is in the same position as the goal state
    private boolean isGoal(Node e) {
    	return (e.getROW()== getRowG() && e.getCOL()==getColG());
    }
    
    //It checks if a set contains a Node with the same row and col
    private boolean containsNode(Set<Node> set,int x, int y) {
        for (Node e : set) 
            if (e.getROW() == x && e.getCOL() == y)
                return true;
        return false; 
    }
    
    //To print the path if it exists
    public void printPath() {
    	ArrayList<Node> path =findPath();
    	if(path == null) {
    		printMaze();
    	} else {
    		solved = true;
    		for(Node n: path) {
    			if(maze[n.getROW()][n.getCOL()]!= 'I' && maze[n.getROW()][n.getCOL()]!= 'G') 
    				maze[n.getROW()][n.getCOL()]= '+'; // It puts '+' in the positions of the path except the initial and goal state
    		}
    		printMaze(); //To print the new maze with the path
    	}
    }
    
    //It implements the A* algorithm
    private ArrayList<Node> findPath() {
        Node start = new Node(getRowI(), getColI(), 0);
        Node goal = new Node(getRowG(), getColG(), 0);
        openSet.add(start);
        parent.put(start, null);
        while (!openSet.isEmpty()) {
            Node current = getNodeWithLowestF();

            if (isGoal(current)) {
                ArrayList<Node> path = new ArrayList<>();
                Node n = current;
                while (n != null) {
                    path.add(n);
                    n = parent.get(n);
                }
                return path;
            }
            openSet.remove(current);
            closedSet.add(current);

            ArrayList<Node> neighbours = getNeighbours(current);
            for (Node neighbour : neighbours) {
                if (containsNode(closedSet,neighbour.getROW(),neighbour.getCOL())) 
                    continue;
                int tentative_g = current.getG() + 1;
      
                if (!containsNode(openSet,neighbour.getROW(),neighbour.getCOL()) || tentative_g < neighbour.getG()) {
                		parent.put(neighbour, current);
	                	neighbour.setG(tentative_g);
	                	neighbour.setH(calculateHeuristic(neighbour,goal));
	                	if(!containsNode(openSet,neighbour.getROW(),neighbour.getCOL())) 
	                		openSet.add(neighbour);
                }
            }
        }
       return null;
    }
    
    public boolean isSolved() {
		return solved;
	}
    
}