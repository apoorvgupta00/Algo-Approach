import java.util.*;
public class IDS{
	//Member Variables
	Main originalPuzzle; 
	Main destinedPuzzle;
	Main q = new Main();
	//Constructor
	public IDS(Main originalPuzzle, Main destinedPuzzle){
		this.originalPuzzle = originalPuzzle;
		this.destinedPuzzle= destinedPuzzle;
		iterativeDeepeningSearch();
	}
	public void iterativeDeepeningSearch() {
		//maximum depth
		int maximumDepth = 0;
		//number of Nodes
		int nNodes = 0;
		Node baseN = new Node(originalPuzzle);
		//Stack Data Structure
		Stack<Node> s = new Stack<>();
		System.out.println("Performing the IDS (Iterative Deepening Search)."+"\n"+ baseN.notifyCurrentSpot());
		s.add(baseN);		
		while(s.isEmpty()==false) {
			ArrayList<Node> followers = new ArrayList<>();
			Node latest = s.pop();
			//confims when destined puzzle matches current puzzle
			if(q.confirms(latest.notifyCurrentSpot(), destinedPuzzle)) {
				ArrayList<Node> presentPuzzle = latest.arrangement(latest);
				for(Node pz : presentPuzzle) {
					System.out.println(pz.notifyCurrentSpot());
				}
				System.out.println("Succesfully completed the Iterative Deepening Search and found a solution. Depth of the solution is " + latest.notifyCurrentValue() + ". The number of nodes expanded to reach here " + nNodes);
				
				break;
			//expanding the nodes if not reaached the depth
			} else if(latest.notifyCurrentHeight() < maximumDepth)
			{
				nNodes++;
				followers = q.childrenN(latest);
				for(Node a : followers) {
					if(a != null) {
						s.add(a);
					}				
				}
			}
			if(s.size() == 0) 
			{
				s.push(baseN);
				maximumDepth++;
			}
			
		}		
	}
}







