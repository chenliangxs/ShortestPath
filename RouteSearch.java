import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.HashMap;

public class RouteSearch{
	private ArrayList<Node> mapData;
	private Node start;
	private Node goal;

	public RouteSearch(String begin, String end, ArrayList<Node> map){
		mapData = map;
		for(int i=0;i<mapData.size();i++){
			if(mapData.get(i).getPos().equals(begin)){
				start = mapData.get(i);
			}
			if(mapData.get(i).getPos().equals(end)){
				goal = mapData.get(i);
			}
		}
	}
	public Node BFSsearch(ArrayList<Pair> pairs){
		LinkedList<Node> path = new LinkedList<Node>();
		HashSet<String> visited = new HashSet<String>();
		path.addLast(start);
		visited.add(start.getPos());
		while(path.size()>0){
			Node current = path.poll();
			String currentPos = current.getPos();
			for(Pair p:pairs){
				String s = p.getStart();
				String e = p.getEnd();
				if(s.equals(currentPos)){
					for(Node n:mapData){
						if(n.getPos().equals(e)){
							if(visited.add(e)){
								n.travelDis = current.travelDis+1;
								n.prePos = current;
								path.add(n);
							}
						}
					}
				}
			}
			if(current==goal){
				return current;
			}
		}
		return null;
	}
	public Node DFSsearch(ArrayList<Pair> pairs){
		Stack<Node> path = new Stack<Node>();
		HashSet<String> visited = new HashSet<String>();
		path.push(start);
		while(!path.empty()){
			Node currentNode = path.peek();
			if(currentNode==goal){
				return currentNode;
			}
			if(visited.contains(currentNode.getPos())){
				path.pop();
			}
			visited.add(currentNode.getPos());
			ArrayList<Node> enqueue = new ArrayList<Node>();
			for(Pair p:pairs){
				String s = p.getStart();
				String e = p.getEnd();
				if(currentNode.getPos().equals(s)){
					for(Node n:mapData){
						if(n.getPos().equals(e)){
							if(visited.add(e) && path.search(n)==-1){
								enqueue.add(n);
							}
						}
					}
				}
			}
			for(int i=0;i<enqueue.size();i++){
				Node x = enqueue.get(enqueue.size()-i-1);
				path.push(x);
				x.travelDis = currentNode.travelDis+1;
				x.prePos = currentNode;
			}
		}
		return null;
	}
	public Node UCSsearch(){
		NodeComparator nodeCompare = new NodeComparator();
		PriorityQueue<Node> pathQ = new PriorityQueue<Node>(mapData.size(),nodeCompare);
		HashSet<String> visited = new HashSet<String>(mapData.size());
		pathQ.add(start);
		while(pathQ.size()>0){
			Node currentNode = pathQ.poll();
			if(currentNode==goal){
				return currentNode;
			}
			visited.add(currentNode.getPos());
			for(Node n: mapData){
				if(currentNode.nextPos.containsKey(n)){
					int newDis = currentNode.getTravelDis()+currentNode.nextPos.get(n);
					if((!visited.contains(n.getPos())) && (!pathQ.contains(n))){
						n.travelDis = newDis;
						n.prePos = currentNode;
						pathQ.add(n);
					}
					else if(pathQ.contains(n)){
						if(n.travelDis>newDis){
							pathQ.remove(n);
							n.travelDis = newDis;
							n.prePos = currentNode;
							pathQ.add(n);
						}
					}
				}
			}
		}
		return null;
	}
	public Node aStarSearch(HashMap<String, Integer> toGoal){	
		NodeComparator nodeCompare = new NodeComparator();
		PriorityQueue<Node> pathQ = new PriorityQueue<Node>(mapData.size(),nodeCompare);
		HashSet<String> visited = new HashSet<String>(mapData.size());
		start.totalDis = toGoal.get(start.getPos());
		pathQ.add(start);
		while(pathQ.size()>0){
			Node currentNode = pathQ.poll();
			if(currentNode==goal){
				return currentNode;
			}
			visited.add(currentNode.getPos());
			for(Node n:mapData){
				if(currentNode.nextPos.containsKey(n)){
					int newTravelDis = currentNode.travelDis+currentNode.nextPos.get(n);
					int expectDis = newTravelDis + toGoal.get(n.getPos());
					if(!visited.contains(n.getPos()) && !pathQ.contains(n)){
						n.totalDis = expectDis;
						n.travelDis = newTravelDis;
						n.prePos = currentNode;
						pathQ.add(n);
					}
					else if(pathQ.contains(n)){
						if(expectDis<n.totalDis){
							n.totalDis = expectDis;
							n.travelDis = newTravelDis;
							n.prePos = currentNode;
							pathQ.add(n);
						}
					}
				}
			}
		}		
		return null;
	}
	
	
	
}