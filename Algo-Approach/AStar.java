import java.util.*;
public class AStar{
	//Member Variables
	Main originalPuzzle; 
	Main destinedPuzzle;
	Main q = new Main();
	//Constructor
	public AStar(Main originalPuzzle, Main destinedPuzzle){
		this.originalPuzzle = originalPuzzle;
		this.destinedPuzzle= destinedPuzzle;
		astarSearch();
	}
	public void astarSearch() 
	{
		//number of nodes
		int nNodes = 0;
		//Prioriy Data Structure
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		Node baseN = new Node(originalPuzzle);
		System.out.println("Performing the A Star Search."+"\n" + baseN.notifyCurrentSpot());
		pq.add(baseN);
		while(pq.isEmpty()==false) 
		{
			ArrayList<Node> followers = new ArrayList<>();
			Node latest = pq.poll();
			//confims when destined puzzle matches current puzzle
			if(q.confirms(latest.notifyCurrentSpot(), destinedPuzzle)) 
			{

				ArrayList<Node> presentPuzzle = latest.arrangement(latest);
				for(Node pz : presentPuzzle) {
					System.out.println(pz.notifyCurrentSpot());
				}
				System.out.println("Succesfully completed the A* Search and found a solution. Depth of the solution is " + latest.notifyCurrentValue() + ". The number of nodes expanded to reach here " + nNodes);
				
				break;
			}
			//nodes are expanded
			nNodes++;
			followers = q.childrenN(latest);
			for(Node a : followers)
			{
				if(a != null) 
				{
					a.aStarManhattanDistance(destinedPuzzle);
					pq.add(a);
				}

			}

		}
	}
}