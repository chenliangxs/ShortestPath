import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class MapBuilder{
	private String[] input;
	public ArrayList<Node> allNodes;
	public ArrayList<Pair> Pairs;
	
	public MapBuilder(String[] inputtxt){
		this.input = inputtxt;
		HashSet<String> nodeBuilder = new HashSet<String>();
		allNodes = new ArrayList<Node>();
		Pairs = new ArrayList<Pair>();
		Scanner in = new Scanner(input[0]);
		for(int i=0;i<input.length;i++){
			in = new Scanner(input[i]);
			for(int j=0;j<2;j++){
				String currentNode = in.next();
				if(nodeBuilder.add(currentNode)){
					Node head = new Node(currentNode);
					allNodes.add(head);
				}
			}
		}
		for(int i=0;i<allNodes.size();i++){
			Node currentNode = allNodes.get(i);
			for(int j=0;j<input.length;j++){
				in = new Scanner(input[j]);
				if(in.next().equals(currentNode.getPos())){
					String followPos = in.next();
					int followDis = in.nextInt();
					for(int m=0;m<allNodes.size();m++){
						if(allNodes.get(m).getPos().equals(followPos)){
							currentNode.nextPos.put(allNodes.get(m), followDis);
						}
					}
				}
			}			
		}
		for(int i=0;i<input.length;i++){
			in = new Scanner(input[i]);
			String s = in.next();
			String e = in.next();
			Pair edge = new Pair(s,e);
			Pairs.add(edge);
		}	
		
	    in.close();
	}
}