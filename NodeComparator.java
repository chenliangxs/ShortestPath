import java.util.Comparator;

public class NodeComparator implements Comparator<Node>{
	public int compare(Node n1, Node n2){
		int dis1 = n1.getTotalDis();
		int dis2 = n2.getTotalDis();
		return dis1-dis2;
	}
}