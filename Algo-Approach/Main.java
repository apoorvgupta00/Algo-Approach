import java.util.*;
public class Main {
	//Member Variables
	private int[] definePuzzleSize; 
	private int[] spotOfA; 
	private int[] spotOfB; 
	private int[] spotOfC; 
	private int[] spotOfAgent;
	private int[][] obstructionCreated;
	/**
	 *
	 * @param definePuzzleSize		defines the size of puzzle
	 * @param spotOfA				defines the positon of A
	 * @param spotOfB				defines the position of B
	 * @param spotOfC				defines the position of C
	 * @param spotOfAgent			defines the position of Agent
	 * @param obstructionCreated 	defines the position of Obstruction
	 */
	public Main(int[] definePuzzleSize, int[] spotOfAgent, int[] spotOfA, int[] spotOfB, int[] spotOfC, int[]... obstructionCreated) {
		this.definePuzzleSize = definePuzzleSize;
		this.spotOfAgent = spotOfAgent;
		this.spotOfA = spotOfA;
		this.spotOfB = spotOfB;
		this.spotOfC = spotOfC;
		this.obstructionCreated = obstructionCreated;
	}

	public Main(){

	}

	/**
	 * Function to return the size of puzzle
	 */
	public int[] notifyPuzzleDimensions() {
		return definePuzzleSize;
	}

	/**
	 * function to return the position of Agent
	 */

	public int[] notifySpotAgent() {
		return spotOfAgent;
	}

	/**
	 * function to return the position of A
	 */

	public int[] notifySpotA() {
		return spotOfA;
	}

	/**
	 * function to return the position of B
	 */

	public int[] notifySpotB() {
		return spotOfB;
	}

	/**
	 * function to return the position of C
	 */

	public int[] notifySpotC() {
		return spotOfC;
	}


	/**
	 * function to return the position of Obstruction
	 */
	public int[][] notfyObstruction() {
		return obstructionCreated;
	}

	/**
	 * function to check if the we have reached the solution
	 */
	public boolean confirms(Main currentPuzzle, Main destinedPuzzle) {
		if(Arrays.equals(currentPuzzle.notifySpotA(), destinedPuzzle.notifySpotA()) 
			&& Arrays.equals(currentPuzzle.notifySpotB(), destinedPuzzle.notifySpotB()) 
			&& Arrays.equals(currentPuzzle.notifySpotC(), destinedPuzzle.notifySpotC())) 
		{
			return true;
		} else 
		{
			return false;
		}
	}


	public ArrayList<Node> childrenN(Node present)
	{
		ArrayList<Node> nChildren = new ArrayList<>();

		nChildren.add(trackingU(present));
		nChildren.add(trackingD(present));
		nChildren.add(trackingL(present));
		nChildren.add(trackingR(present));
		return nChildren;
	}

	/**
	 * function for making moves
	 */
	private Node tracking(int[] latest,Node y) {
		Node a = null;
		if(Arrays.equals(y.notifyCurrentSpot().notifySpotA(), latest)) {
			Main fpz = new Main(y.notifyCurrentSpot().notifyPuzzleDimensions(),
					latest,
					y.notifyCurrentSpot().notifySpotAgent(),
					y.notifyCurrentSpot().notifySpotB(),
					y.notifyCurrentSpot().notifySpotC(),
					y.notifyCurrentSpot().notfyObstruction());
			a = new Node(fpz,
					y,
					y.notifyCurrentHeight() + 1);
		} else if(Arrays.equals(y.notifyCurrentSpot().notifySpotB(), latest)) {
			Main fpz = new Main(y.notifyCurrentSpot().notifyPuzzleDimensions(),
					latest,
					y.notifyCurrentSpot().notifySpotA(),
					y.notifyCurrentSpot().notifySpotAgent(),
					y.notifyCurrentSpot().notifySpotC(),
					y.notifyCurrentSpot().notfyObstruction());
			a = new Node(fpz, 
					y, 
					y.notifyCurrentHeight() + 1);
		} else if(Arrays.equals(y.notifyCurrentSpot().notifySpotC(), latest)) {
			Main fpz = new Main(y.notifyCurrentSpot().notifyPuzzleDimensions(),
					latest,
					y.notifyCurrentSpot().notifySpotA(),
					y.notifyCurrentSpot().notifySpotB(),
					y.notifyCurrentSpot().notifySpotAgent(),
					y.notifyCurrentSpot().notfyObstruction());
			a = new Node(fpz,
					y,
					y.notifyCurrentHeight() + 1);
		} else {
			Main fpz = new Main(y.notifyCurrentSpot().notifyPuzzleDimensions(),
					latest,
					y.notifyCurrentSpot().notifySpotA(),
					y.notifyCurrentSpot().notifySpotB(),
					y.notifyCurrentSpot().notifySpotC(),
					y.notifyCurrentSpot().notfyObstruction());
			a = new Node(fpz,
					y,
					y.notifyCurrentHeight() + 1);
		}
		return a;
	}

	private boolean obstructionInquiry(int a, int b, int[][] fail) {
		boolean c= false;
		if(fail != null) {
			for(int[] d : fail) {
				if(a == d[0] && b == d[1]) {
					c = true;
					break;
				}
			}
		}
		return c;
	}

	/**
	 * function for upwards movement
	 */

	private Node trackingU(Node x) {
		if(x.notifyCurrentSpot().notifySpotAgent()[1] - 1 >= 1 
			&& !obstructionInquiry(x.notifyCurrentSpot().notifySpotAgent()[0], 
				x.notifyCurrentSpot().notifySpotAgent()[1] - 1,
			 	x.notifyCurrentSpot().notfyObstruction())) {
			return tracking(new int[] {x.notifyCurrentSpot().notifySpotAgent()[0], x.notifyCurrentSpot().notifySpotAgent()[1] - 1},x);
		} else {
			return null;
		}
	}

	/**
	 * function to make ddownwards movement
	 */
	private Node trackingD(Node x) {
		if(x.notifyCurrentSpot().notifySpotAgent()[1] + 1 <= x.notifyCurrentSpot().notifyPuzzleDimensions()[1] 
			&& !obstructionInquiry(x.notifyCurrentSpot().notifySpotAgent()[0],
				x.notifyCurrentSpot().notifySpotAgent()[1] + 1,
				x.notifyCurrentSpot().notfyObstruction())) {
			return tracking(new int[] {x.notifyCurrentSpot().notifySpotAgent()[0],
					x.notifyCurrentSpot().notifySpotAgent()[1] + 1},x);
		} else {
			return null;
		}
	}

	/**
	 * function to make right movement
	 */
	private Node trackingR(Node x) {
		if(x.notifyCurrentSpot().notifySpotAgent()[0] + 1 <= x.notifyCurrentSpot().notifyPuzzleDimensions()[0] 
			&& !obstructionInquiry(x.notifyCurrentSpot().notifySpotAgent()[0] + 1, 
				x.notifyCurrentSpot().notifySpotAgent()[1], 
				x.notifyCurrentSpot().notfyObstruction())) {
			return tracking(new int[] {x.notifyCurrentSpot().notifySpotAgent()[0] + 1, x.notifyCurrentSpot().notifySpotAgent()[1]},x);
		} else {
			return null;
		}
	}

	/**
	 * function to make left movement
	 */

	private Node trackingL(Node x) {
		if(x.notifyCurrentSpot().notifySpotAgent()[0] - 1 >= 1 
			&& !obstructionInquiry(x.notifyCurrentSpot().notifySpotAgent()[0] - 1,
				x.notifyCurrentSpot().notifySpotAgent()[1],
				x.notifyCurrentSpot().notfyObstruction())) {
			return tracking(new int[] {x.notifyCurrentSpot().notifySpotAgent()[0] - 1,
					x.notifyCurrentSpot().notifySpotAgent()[1]},x);
		} else {
			return null;
		}
	}

	/**
	 * function to output the puzzle
	 */

	public String toString() {		
		String view = "";
		
        for (int q = 1; q <= definePuzzleSize[0]; q++) {
            for (int t = 1; t <= definePuzzleSize[1]; t++) {
                if(t ==spotOfAgent[0] && q ==spotOfAgent[1]) {
                	view += "O ";
                } else if(t == spotOfC[0] && q == spotOfC[1]) {
                	view += "C ";
                } else if(t == spotOfB[0] && q == spotOfB[1]) {
                	view += "B ";
                } else if(t == spotOfA[0] && q == spotOfA[1]) {
                	view += "A ";
                } else {
                	boolean a = false;
                	if(obstructionCreated != null) {
                		for(int[] w : obstructionCreated) {
                    		if(w[0] == t && w[1] == q) {
                    			view += "- ";
                    			a = true;
                    			break;
                    		}
                    	}
                	}
                	if(!a) {
                		view += "# ";
                	}                	
                }            	
            }
            view += "\n";
        } 
        return view;
	}
	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);
		// take the input for dimensions of the puzzle
		System.out.println("Enter the size of grid ");
		int bl1 = scann.nextInt();
		int bl2 = scann.nextInt();
		//take the input from user for Block Agent
		System.out.println("Enter the position of Agent");
		int ag1 = scann.nextInt();
		int ag2 = scann.nextInt();
		//take the input from user for Block A
		System.out.println("Enter the position of A");
		int a1 = scann.nextInt();
		int a2 = scann.nextInt();
		//take the input from user for Block B
		System.out.println("Enter the position of B");
		int b1 = scann.nextInt();
		int b2 = scann.nextInt();
		//take the input from user for Block C
		System.out.println("Enter the position of C");
		int c1 = scann.nextInt();
		int c2 = scann.nextInt();
		//take the input from user for Obstacle Block
		System.out.println("Enter the position of obstacle");
		int o1 = scann.nextInt();
		int o2 = scann.nextInt();
		Main originalPuzzle = new Main(new int[] {bl1,bl2}, new int[] {ag1, ag2},new int[] {a1,a2}, new int[] {b1,b2}, new int[] {c1,c2}, new int[] {o1,o2});
		Main destinedPuzzle = new Main(null, null,new int[] {2,2}, new int[] {2,3}, new int[] {2, 4}, new int[] {o1,o2});

		System.out.println("Please enter the method you want to perform: 1)BFS 2)DFS 3)IDS 4)A* Search");
		int method = scann.nextInt();
		switch(method){
			case 1:
				BFS bfs = new BFS(originalPuzzle, destinedPuzzle);
				break;
			case 2:
				DFS dfs = new DFS(originalPuzzle, destinedPuzzle);
				break;
			case 3:
				IDS ids = new IDS(originalPuzzle, destinedPuzzle);
				break;
			case 4:
				AStar astar = new AStar(originalPuzzle, destinedPuzzle);
				break;
			default:
				System.out.println("Wrong Option Selected!");	

		}
		
	}
}

/**
 * Node class
 */
class Node implements Comparable<Node> {
	//Member Variables
	private Node parentNode = null;
	private Main m;
	private int height;
	private int value;
	//Constructor
	public Node(Main m) {
		this.value = 0;
		this.m = m;
		this.height = 0;
		this.parentNode = null;
	}
	//Constructor
	public Node(Main m, Node parentNode, int x) {
		this.value = x;
		this.m = m;   
		this.parentNode = parentNode; 
		this.height = x; 
		 
	}

	/**
	 * function that returns the current height
	 */
	public int notifyCurrentHeight() {
		return height;
	}

	/**
	 * function that returns the value of node
	 */
 
	public int notifyCurrentValue() {
		return value;
	}

	/**
	 * function that returns the formation of puzzle
	 */

	public Main notifyCurrentSpot() {
		return m;
	}

	/**
	 * function that takes the node and returns the direction
	 */
	public ArrayList<Node> arrangement(Node x) {
		ArrayList<Node> arng = new ArrayList<>();
		while (x.parentNode != null) {
			arng.add(x);
			x = x.parentNode;
		}
		Collections.reverse(arng);
		return arng;
	}

	/**
	 * function to compare the nodes
	 * @param y
	 * @return
	 */
	@Override
	public int compareTo(Node y) {
		int x = this.value - y.notifyCurrentValue();
		if(x == 0) {
			if(this.notifyCurrentHeight() > y.notifyCurrentHeight()) {
				return -1;
			} else if (this.notifyCurrentHeight() == y.notifyCurrentHeight()){
				return 0;
			} else {
				return 1;
			}
		} else {
			return x;
		} 				
	}

	/**
	 * Heuristic fundtion i.e. Manhattan Distance for A*
	 */
	public void aStarManhattanDistance(Main m1) {
		int cLength = Math.abs(m.notifySpotC()[1] - m1.notifySpotC()[1])+Math.abs(m.notifySpotC()[0] - m1.notifySpotC()[0]);
		int bLength = Math.abs(m.notifySpotB()[1] - m1.notifySpotB()[1])+Math.abs(m.notifySpotB()[0] - m1.notifySpotB()[0]);
		int aLength = Math.abs(m.notifySpotA()[1] - m1.notifySpotA()[1])+Math.abs(m.notifySpotA()[0] - m1.notifySpotA()[0]);
		value = value + cLength + bLength + aLength;
	}
}
