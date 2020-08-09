import java.util.*;
public class BFS{
	//Member Variables
	Main originalPuzzle; 
	Main destinedPuzzle;
	Main q = new Main();
	//Constructor
	public BFS(Main originalPuzzle, Main destinedPuzzle){
		this.originalPuzzle = originalPuzzle;
		this.destinedPuzzle= destinedPuzzle;
		breadthFirstSearch();
	}
	public void breadthFirstSearch() {
		//number of Nodes
		int nNodes = 0;
		Node baseN = new Node(originalPuzzle);
		// Queue Data Strucutre
		Queue<Node> qu = new LinkedList<Node>();
		System.out.println("Performing the BFS (Breadth First Search)."+"\n" + baseN.notifyCurrentSpot());
		qu.add(baseN);
		while(qu.isEmpty()==false) {
			Node latest = qu.remove();
			ArrayList<Node> followers = new ArrayList<>();
			//confims when destined puzzle matches current puzzle
			if(q.confirms(latest.notifyCurrentSpot(), destinedPuzzle)) {
				ArrayList<Node> presentPuzzle = latest.arrangement(latest);
				for(Node pz : presentPuzzle) {
					System.out.println(pz.notifyCurrentSpot());
				}
				System.out.println("Succesfully completed the Breadth First Search and found a solution. Depth of the solution is " + latest.notifyCurrentValue() + ". The number of nodes expanded to reach here " + nNodes );


				break;
			}
			//expanding the nodes
			nNodes++;			
			followers = q.childrenN(latest);
			for(Node a : followers) 
			{
				if(a != null) {
					qu.add(a);
				}				
			}
		}
	}
}