import java.util.*;
public class DFS{
	//Member Variables
	Main originalPuzzle; 
	Main destinedPuzzle;
	Main q = new Main();
	//Constructor
	public DFS(Main originalPuzzle, Main destinedPuzzle){
		this.originalPuzzle = originalPuzzle;
		this.destinedPuzzle= destinedPuzzle;
		depthFirstSearch();
	}
	public void depthFirstSearch() 
	{
		//number of Nodes
		int nNodes = 0;
		Node baseN = new Node(originalPuzzle);
		// Stack Data Strucutre
		Stack<Node> s = new Stack<>();
		System.out.println("Performing the DFS (Depth First Search)."+"\n" + baseN.notifyCurrentSpot());
		s.add(baseN);
		while(s.isEmpty()==false) 
		{
			ArrayList<Node> followers = new ArrayList<>();
			Node latest = s.pop();
			//confims when destined puzzle matches current puzzle
			if(q.confirms(latest.notifyCurrentSpot(), destinedPuzzle)) 
			{
				ArrayList<Node> presentPuzzle = latest.arrangement(latest);
				for(Node pz : presentPuzzle) {
					System.out.println(pz.notifyCurrentSpot());
				}

				System.out.println("Succesfully completed the Depth First Search and found a solution. Depth of the solution is " + latest.notifyCurrentValue() + ". The number of nodes expanded to reach here " + nNodes);
				
				break;
			}
			//expanding the nodes
			nNodes++;
			followers = q.childrenN(latest);
			Collections.shuffle(followers);
			for(Node a : followers) {
				if(a != null) {
					s.add(a);
				}				
			}
			
		}
	}
	
}













