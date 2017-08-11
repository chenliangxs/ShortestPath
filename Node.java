import java.util.HashMap;
import java.util.ArrayList;

public class Node{
	private String currentPos;
	public HashMap<Node, Integer> nextPos;
	public Node prePos;
	public int totalDis;
	public int travelDis;

	public Node(){
		this.currentPos = "";
		nextPos = new HashMap<Node, Integer>();
		prePos = null;
		totalDis = 0;
		travelDis = 0;
	}
	public Node(String currentPos){
		this.currentPos = currentPos;
		nextPos = new HashMap<Node, Integer>();
		prePos = null;
		totalDis = 0;
		travelDis = 0;
	}
	public String getPos(){
		return currentPos;
	}
	public HashMap<Node, Integer> getNext(){
		return nextPos;
	}
	public Node getPreNode(){
		return prePos;
	}
	public ArrayList<String> getPath(){
		ArrayList<String> path = new ArrayList<String>();
		path.add(currentPos+" "+travelDis);
		Node pointer = prePos;
		while(pointer!=null){
			path.add(0, pointer.getPos()+" "+pointer.travelDis);
			pointer = pointer.prePos;
		}
		return path;
	}
	public int getTotalDis(){
		return totalDis;
	}
	public int getTravelDis(){
		return travelDis;
	}
	
	/**public int getDistance(Node next){
		if(nextPos.containsKey(next)){
			return nextPos.get(next);
		}
		return 0;
	}*/
	public String toString(){
		return currentPos;
	}
	
}