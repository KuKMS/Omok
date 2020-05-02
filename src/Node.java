

public class Node {
	//node has a map for value.
	Node[] children = new Node[361];		//children max = 19* 19 
	Node parent = null;						//default= no parent
	boolean visited =false;					//not visited first.
	private int childNum = 0;				
	int score = 0;
	Map map = new Map(19);
	public int xin =10, yin =10;			//if it is a child, then what's parents input for this node?
	int aiColor;
											//for the output of ai, this variable exists.
	
	public Node(Node parent, Map map, int color) {		//constructor: get parent node & map for this node
		this.parent = parent;
		this.map = map;
		this.aiColor = color;
	}
	
	public int getChildNum() {return childNum;}			//get number of children
	public Node getChild(int index) {return children[index];}		//get chlid node at index
	public Map getMap() {return map;}						//get Map for this node
	public int getScore() {return score;}					//get score for this node.
	public int getX() {return xin;}							//get last x
	public int getY() {return yin;}							//get last y
	public void setX(int x) {xin =x;}						//set last x
	public void setY(int y) {yin =y;}						//set lasy y
	
	public Node addChild(int x,int y,int color)  {		
		//add child to this node, and play stone in x,y  of color
		Map childmap = new Map(19);
		childmap.setMap(map.getMap());		//copy map from this node
		if(childmap.setColor(x, y, color)) {		//if child map can exist
			Node child = new Node(this,childmap,aiColor);		//create node
			if(childNum<361) {
				child.score = childmap.scoreCur(aiColor);		//get score for map of child
				children[childNum] = child;				//add child to this node
				child.setX(x);
				child.setY(y);
				childNum++;
				return child;
			}else {
				System.out.println("child full");		//num of children is full, null.
				return null;
			}
		}
		return null;
	}
	public void clear() {
		//clear children for the memory.
		for(int i = 0; i<childNum; i++) {
			children[i] = null;
		}
		childNum =0;
		visited = false;
	}
	
	public void visit() {
		//mark this node as visited 
		if(!visited){visited = true;}
	}
	
	public boolean visitEveryChild() {
		//find whether every children is visited
		for(int i =0 ; i<childNum; i++) {
			if(children[i].visited !=false) {
				return false;
			}
		}
		return true;
	}
}
