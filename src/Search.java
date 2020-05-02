
public class Search {
	//max: true=find max , false = find min
	//top = set 1 to get top x,y
	int childNum;
	int childVal;
	int[] xy = {10,10};
	Node node;
	int score;
	int depth;
	long time;
	long timeLimit;
	int color;
	
	
	public Search(Node node,int depth,long timeStart, long timeLimit,int color) {
		this.node = node;
		this.depth = depth;
		this.time = timeStart; 
		this.timeLimit = timeLimit;
		this.color= color;
		this.score = node.getScore();
	}
	public int[] getXY() {return xy;}
	

	public int search(Node node, int depthNow, boolean max, int alpha, int beta,int colorNow)  {
		//minimax search & alpha -beta pruning from the node
		if(depthNow <=0) {		//leaf node
			node.visit();
			return node.getScore();
		}
		colorNow = -colorNow;			//children color is different from now
		for(int i=1;i<=19;i++) {
			for(int j=1; j<=19;j++) {
				node.addChild(i, j, colorNow);		//add children to the node, if possible(checked in the funct.)
			}
		}

		childNum = node.getChildNum();		//get number of children
		for(int i=0; i<childNum;i++) {		//search children for number of children times
			Node child = node.getChild(i);		//call child
			if(child == null) {break;}			//error prevention part for null child
			if(child.getMap().win(child.getX(), child.getY()) == true) {	//if you play there, you can win. then mus pick there.
				xy[0] = child.getX();
				xy[1] = child.getY();
				break;
			}
			childVal = search(child,depthNow-1,!max,alpha,beta,colorNow);	//going down. change min/max
			if(System.currentTimeMillis() - time > timeLimit) {break;}		//time out
			if(i==0) {		//initial pick
				if(max) {
					alpha = childVal;
					if(depthNow == depth) {		//get x,y if picked. 
						xy[0] = child.getX();
						xy[1] = child.getY();
					}	
				}else {
					beta = childVal;
					if(depthNow == depth) {		//get x,y if picked. 
						xy[0] = child.getX();
						xy[1] = child.getY();
					}	
				}
			}
			else if(max) {	//max level
				if(alpha<childVal) {
					alpha = childVal;
					if(depthNow == depth) {		//get x,y if picked.
						xy[0] = child.getX();
						xy[1] = child.getY();
					}	
				}
			}else {	//min level
				if(beta>childVal) {
					beta = childVal;
					if(depthNow == depth) {		//get x,y if picked. 
						xy[0] = child.getX();
						xy[1] = child.getY();
					}	
				}
			}
			if(max) {		//pruning part
				if(childVal>=beta) {
					break;
				}
			}else {
				if(childVal<=alpha) {
					break;
				}
			}
		}
		node.clear();		//after iteration, clear node for memory
		if(max) {
			return alpha;
		}else {
			return beta;
		}
	}
}
