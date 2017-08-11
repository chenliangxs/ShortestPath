import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.HashMap;

public class homework{
	public static void main(String[] args) throws FileNotFoundException{
		File inputfile = new File("input.txt");
		Scanner in = new Scanner(inputfile);
		PrintWriter out = new PrintWriter("output.txt");
		
		String alg = in.next();
		in.nextLine();
		String start = in.next();
		in.nextLine();
		String end = in.next();
		in.nextLine();
		int inputSize = in.nextInt();
		in.nextLine();
		String[] input = new String[inputSize];
		for(int i=0;i<inputSize;i++){
			input[i] = in.nextLine();
		}
		int aStarSize = in.nextInt();
		in.nextLine();
		String[] aStar = new String[aStarSize];
		for(int i=0;i<aStarSize;i++){
			aStar[i] = in.nextLine();
		}
		MapBuilder routeMap = new MapBuilder(input);
		RouteSearch searchX = new RouteSearch(start, end, routeMap.allNodes);
		Node goal = new Node();
		if(alg.equals("BFS")){
			goal = searchX.BFSsearch(routeMap.Pairs);
			if(goal==null){
				System.out.println("No path");
				out.println("No path found");
			}
			else{
				ArrayList<String> path = goal.getPath();
				System.out.println(goal.getPos());
				for(int i=0;i<path.size();i++){
					out.println(path.get(i));
				}
			}
		}
		else if(alg.equals("DFS")){
			goal = searchX.DFSsearch(routeMap.Pairs);
			if(goal==null){
				System.out.println("No path");
				out.println("No path found");
			}
			else{
				ArrayList<String> path = goal.getPath();
				System.out.println(goal.getPos());
				for(int i=0;i<path.size();i++){
			    	out.println(path.get(i));
				}
			}
		}
		else if(alg.equals("UCS")){
			goal = searchX.UCSsearch();
			if(goal==null){
				System.out.println("No path");
				out.println("No path found");
			}
			else{
				ArrayList<String> path = goal.getPath();
				System.out.println(goal.getPos());
				for(int i=0;i<path.size();i++){
			    	out.println(path.get(i));
			    }
			}
		}
		else{
			HashMap<String, Integer> toGoal = new HashMap<String, Integer>();
			for(String s:aStar){
				in = new Scanner(s);
				String key = in.next();
				int value = in.nextInt();
				toGoal.put(key, value);
			}	
			goal = searchX.aStarSearch(toGoal);
			if(goal==null){
				System.out.println("no Path");
				out.println("No path found");
			}
			else{
				ArrayList<String> path = goal.getPath();
				System.out.println(goal.getPos());
				for(int i=0;i<path.size();i++){
					out.println(path.get(i));
				}
			}
		}
		
		in.close();
		out.close();
	}
}