

public class AI {
	int timeLimit;
	Map map;
	int color;		//color of AI.
	int resultX;
	int resultY;
	
	public AI(Map map,int color,int timeLimit) {
		this.map = map;
		this.color = color;
		this.timeLimit = timeLimit;
	}
	public int getX() {return resultX;}
	public int getY() {return resultY;}
	


	
	
	public int[] IDDFS(Node node, int iter) {
		//return best x,y ([0], [1])
		int[] coord = new int[2];
		long timeStart = System.currentTimeMillis();		//get system time at starting point
		Search s; 
		boolean max =false;			
		if(color <0) {max =true;}
		
		for(int i=2; i<=iter;i+=2) {
			s = new Search(node, i, timeStart, timeLimit, color);
			int score = s.search(node,i,max,-99999,99999,color);			//very large/small value for alpha & beta
			System.out.println(score);
			if(System.currentTimeMillis()- timeStart > timeLimit) {		//check if it overs time
				System.out.println("timeout");
				break;
				}
			node.clear();		//clear node for memory
			coord=s.getXY();
			System.out.println("Level  " + i + " finish. "+coord[0] +" "+coord[1] );		//print level i's result to console.
			s =null;
		}
		resultX = coord[0];
		resultY = coord[1];
		
		return coord;
	}

	
	
	
}
