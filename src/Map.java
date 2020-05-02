

public class Map  {
	int[][] map;
	final int BLACK=1,WHITE=-1;
	int size;
	int cnt =0;
	
	public Map(int size) {
		this.size = size;
		this.map = new int[size+2][size+2];
		for(int i = 0; i<size+2 ;i++) {
			for(int j = 0; j<size+2;j++) {
				if(i==0||i==size+1||j==0||j==size+1) {map[i][j]=9;}
				else {map[i][j] = 0;}
			}
		}
	} 
	
	public int getSize() {return size;}
	public int getColor(int x, int y) { return map[x][y]; }
	public int getBlack(){return BLACK;}
	public int getWhite(){return WHITE;}
	public int[][] getMap(){return map;}
	public void clearMap() {
		for(int i=1;i<=size;i++) {
			for(int j=1;j<=size;j++) {
				map[i][j]=0;
			}
		}
	}
	public void setMap(int[][] input) {
		for(int i=1;i<=size;i++) {
			for(int j=1;j<=size;j++) {
				map[i][j]=input[i][j];
			}
		}
		
	}
	
	
	public boolean setColor(int x, int y, int color) {
		if(map[x][y] == 0) 
		{
			map[x][y]= color;
			if(!samsam(x,y)) {
				map[x][y] = 0;
				return false;
			}
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public boolean setColorForce(int x, int y, int color) {
		//don't check other condition, just set color directly
		map[x][y] =color;
		
		return true;
	}
		
	public boolean samsam(int x, int y) {
	// check whether x,y is double three.
		int[] sam = new int[4];
		if(samHor(x,y).length>0) {	//1. horizontal first, others next.
			sam = samHor(x,y);
			if(samVer(x,y).length > 0) {return false;}
			else if(samVer(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samVer(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
			else if(samDia1(x,y).length > 0) {return false;}
			else if(samDia1(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samDia1(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
			else if(samDia2(x,y).length > 0) {return false;}
			else if(samDia2(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samDia2(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
		}else if (samVer(x,y).length>0) { //2. vertical first, others next.
			sam = samVer(x,y);
			if(samHor(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samHor(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
			else if(samDia1(x,y).length > 0) {return false;}
			else if(samDia1(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samDia1(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
			else if(samDia2(x,y).length > 0) {return false;}
			else if(samDia2(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samDia2(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
		}else if (samDia1(x,y).length>0) {//3. diagonal 1(left top) first, others next.
			sam = samDia1(x,y);
			if(samHor(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samHor(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
			else if(samVer(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samVer(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
			else if(samDia2(x,y).length > 0) {return false;}
			else if(samDia2(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samDia2(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
		}else if (samDia2(x,y).length>0) {//4. diagonal 2 (right top) first, others next.
			sam = samDia2(x,y);
			if(samHor(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samHor(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
			else if(samVer(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samVer(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
			else if(samDia1(sam[0],sam[1]).length > 0 && getColor(sam[0],sam[1])!=0) {return false;}
			else if(samDia1(sam[2],sam[3]).length > 0 && getColor(sam[2],sam[3])!=0) {return false;}
		}
		return true;
	}

	public int[] samVer(int x, int y) {
		int[] sam = new int[12];
		int[] blank = new int[0];
		cnt = 1;
		for(int i=1; i<4; i++) {
			if(map[x][y] != map[x][y+i]) {
				if(map[x][y] == -map[x][y+i] || map[x][y+i] == 9) {  //closed 3,3
					return blank;
				}
				break;
			}
			else {
				sam[cnt*2-2]=x;
				sam[cnt*2-1]=y+i;
				cnt++;
			}
		}
		for(int i=-1; i>-4; i--) {
			if(map[x][y] != map[x][y+i])  {
				if(map[x][y] == -map[x][y+i]|| map[x][y+i] == 9) {  //closed 3,3
					return blank;
				}
				break;
			}
			else {
				sam[cnt*2-2]=x;
				sam[cnt*2-1]=y+i;
				cnt++;
			}		
		}
		if(cnt == 3) { return sam;}
		else {	return blank; }
		
	}
	public int[] samHor(int x, int y) {
		int[] sam = new int[12];
		int[] blank = new int[0];
		cnt = 1;
		for(int i=1; i<4; i++) {
			if(map[x][y] != map[x+i][y])  {
				if(map[x][y] == -map[x+i][y]|| map[x+i][y] == 9) {  //closed 3,3
					return blank;
				}
				break;
			}
			else {
				sam[cnt*2-2]=x+i;
				sam[cnt*2-1]=y;
				cnt++;
			}
		}
		for(int i=-1; i>-4; i--) {
			if(map[x][y] != map[x+i][y]) {
				if(map[x][y] == -map[x+i][y]|| map[x+i][y] == 9) {  //closed 3,3
					return blank;
				}
				break;
			}
			else {
				sam[cnt*2-2]=x+i;
				sam[cnt*2-1]=y;
				cnt++;
			}			
		}
		if(cnt == 3) { return sam;}
		else {	return blank; }
		
	}
	public int[] samDia1(int x, int y) {
		int[] sam = new int[12];
		int[] blank = new int[0];
		cnt = 1;
		for(int i=1; i<4; i++) {
			if(map[x][y] != map[x+i][y+i]) {
				if(map[x][y] == -map[x+i][y+i]|| map[x+i][y+i] == 9) {  //closed 3,3
					return blank;
				}
				break;
			}
			else {
				sam[cnt*2-2]=x+i;
				sam[cnt*2-1]=y+i;
				cnt++;
			}
		}
		for(int i=-1; i>-4; i--) {
			if(map[x][y] != map[x+i][y+i]) {
				if(map[x][y] == -map[x+i][y+i]|| map[x+i][y+i] == 9) {  //closed 3,3
					return blank;
				}
				break;
			}
			else {
				sam[cnt*2-2]=x+i;
				sam[cnt*2-1]=y+i;
				cnt++;
			}			
		}
		if(cnt == 3) { return sam;}
		else {	return blank; }
		
	}
	public int[] samDia2(int x, int y) {
		int[] sam = new int[12];
		int[] blank = new int[0];
		cnt = 1;
		for(int i=1; i<4; i++) {
			if(map[x][y] != map[x+i][y-i]) {
				if(map[x][y] == -map[x+i][y-i]|| map[x+i][y-i] == 9) {  //closed 3,3
					return blank;
				}
				break;
			}
			else {
				sam[cnt*2-2]=x+i;
				sam[cnt*2-1]=y-i;
				cnt++;
			}
		}
		for(int i=-1; i>-4; i--) {
			if(map[x][y] != map[x+i][y-i]){
				if(map[x][y] == -map[x+i][y-i]|| map[x+i][y-i] == 9) {  //closed 3,3
					return blank;
				}
				break;
			}
			else {
				sam[cnt*2-2]=x+i;
				sam[cnt*2-1]=y-i;
				cnt++;
			}			
		}
		if(cnt == 3) { return sam;}
		else {	return blank; }
		
	}
	
	
	
	//win check for map x , y
	public boolean win(int x, int y) {
		if(winHor(x,y)||winVer(x,y)||winDia1(x,y)||winDia2(x,y)) {	return true;}
		else {return false;}
	}
	
	//vertical win check
	public boolean winVer(int x, int y) {
		cnt = 1;
		for(int i=1; i<19; i++) {
			if(map[x][y] != map[x][y+i]) {break;}
			else {cnt++;}
		}
		for(int i=-1; i>-19; i--) {
			if(map[x][y] != map[x][y+i]) {break;}
			else {cnt++;}			
		}
		if(cnt == 5) { return true;}
		else {	return false; }
	}
	
	//horizontal win check
	public boolean winHor(int x, int y) {
		cnt = 1;
		for(int i=1; i<19; i++) {
			if(map[x][y] != map[x+i][y]) {break;}
			else {cnt++;}
		}
		for(int i=-1; i>-19; i--) {
			if(map[x][y] != map[x+i][y]) {break;}
			else {cnt++;}
		}
		if(cnt == 5) { return true;}
		else {	return false; }
		
	}
	
	//diagonal win check (from right top)
	public boolean winDia1(int x, int y) {
		cnt = 1;
		for(int i=1; i<19; i++) {
			if(map[x][y] != map[x+i][y+i]) {break;}
			else {cnt++;}
		}
		for(int i=-1; i>-19; i--) {
			if(map[x][y] != map[x+i][y+i]) {break;}
			else {cnt++;}			
		}
		if(cnt == 5) { return true;}
		else {	return false; }
	}
	//diagonal win check (from left top)
		public boolean winDia2(int x, int y) {
			cnt = 1;
			for(int i=1; i<19; i++) {
				if(map[x][y] != map[x+i][y-i]) {break;}
				else {cnt++;}
			}
			for(int i=-1; i>-19; i--) {
				if(map[x][y] != map[x+i][y-i]) {break;}
				else {cnt++;}			
			}
			if(cnt == 5) { return true;}
			else {	return false; }
		}

		
	//score current map calculation
	public int scoreCur(int aiColor) {
		int score=0;
		int tempScore = 0;
		int tempColor;
		boolean winflag = false;
		for(int i=1;i<=19;i++) {
			for(int j =1; j<=19; j++) {
				tempScore=0;
				tempColor = map[i][j];
				if(tempColor!=0) {
					tempScore = cellScore(i,j,aiColor);
				}
				score +=tempScore;
			}
			if(winflag) {break;	}
		}
		return score;
	}
	
	private int cellScore(int x, int y, int aiColor) {
		//black benefit + , white benefit -
		int score =0;
		int color = map[x][y];
		//find 2
		
		score = horS(x,y,aiColor) + verS(x,y,aiColor) + diaS1(x,y,aiColor) + diaS2(x,y,aiColor);
		
		
		if(color<0) {score = -score;}
		return score;
	}
	
	private int horS(int x, int y, int aiColor) {
		int score = 0;
		int cnt =1;
		int blk =0;
		for(int i =1;i<5;i++) {
			if(map[x+i][y] == 0) {break;}
			else if (map[x][y] == -map[x+i][y]|| map[x+i][y] == 9) 
			{
				blk++;
				break;
			}
			else {cnt++;}
		}
		for(int i = -1;i>-5;i--) {
			if(map[x+i][y] == 0) {break;}
			else if (map[x][y] == -map[x+i][y]|| map[x+i][y] == 9) 
			{
				blk++;
				break;
			}
			else {cnt++;}
		}
		if(blk ==0) {
			if(map[x][y]==aiColor) {	//attackpoint
				switch(cnt) {
					case 0: score = 20; break;
					case 1: score = 100; break;
					case 2: score = 1000; break;
					case 3: score = 4000; break;
					case 4: score =99991;break;
					default: score = 0;
				}
			}else {
				switch(cnt) {
				case 0: score = 10; break;
				case 1: score = 30; break;
				case 2: score = 500; break;
				case 3: score = 2000; break;
				case 4: score =99990;break;
				default: score = 0;
				}
			}
		}else if (blk==1) {
			if(map[x][y]==aiColor) {	//attackpoint
				switch(cnt) {
					case 0: score = 10; break;
					case 1: score = 30; break;
					case 2: score = 300; break;
					case 3: score = 1500; break;
					case 4: score =99991;break;
					default: score = 0;
				}
			}else {
				switch(cnt) {
				case 0: score = 5; break;
				case 1: score = 20; break;
				case 2: score = 200; break;
				case 3: score = 1000; break;
				case 4: score =99990;break;
				default: score = 0;
				}
			}
		}else {
			score = 0;
		}
		return score;
	}
	
	private int verS(int x, int y, int aiColor) {
		int score = 0;
		int cnt =1;
		int blk =0;
		for(int i =1;i<5;i++) {
			if(map[x][y+i] == 0) {break;}
			else if (map[x][y] == -map[x][y+i]|| map[x][y+i] == 9) 
			{
				blk++;
				break;
			}
			else {cnt++;}
		}
		for(int i = -1;i>-5;i--) {
			if(map[x][y+i] == 0) {break;}
			else if (map[x][y] == -map[x][y+i]|| map[x][y+i] == 9) 
			{
				blk++;
				break;
			}
			else {cnt++;}
		}
		if(blk ==0) {
			if(map[x][y]==aiColor) {	//attackpoint
				switch(cnt) {
					case 0: score = 20; break;
					case 1: score = 100; break;
					case 2: score = 1000; break;
					case 3: score = 4000; break;
					case 4: score =99991;break;
					default: score = 0;
				}
			}else {
				switch(cnt) {
				case 0: score = 10; break;
				case 1: score = 30; break;
				case 2: score = 500; break;
				case 3: score = 2000; break;
				case 4: score =99990;break;
				default: score = 0;
				}
			}
		}else if (blk==1) {
			if(map[x][y]==aiColor) {	//attackpoint
				switch(cnt) {
					case 0: score = 10; break;
					case 1: score = 30; break;
					case 2: score = 300; break;
					case 3: score = 1500; break;
					case 4: score =99991;break;
					default: score = 0;
				}
			}else {
				switch(cnt) {
				case 0: score = 5; break;
				case 1: score = 20; break;
				case 2: score = 200; break;
				case 3: score = 1000; break;
				case 4: score =99990;break;
				default: score = 0;
				}
			}
		}else {
			score = 0;
		}
		return score;
	}
	private int diaS1(int x, int y, int aiColor) {
		int score = 0;
		int cnt =1;
		int blk =0;
		for(int i =1;i<5;i++) {
			if(map[x+i][y+i] == 0) {break;}
			else if (map[x][y] == -map[x+i][y+i]|| map[x+i][y+i] == 9) 
			{
				blk++;
				break;
			}
			else {cnt++;}
		}
		for(int i = -1;i>-5;i--) {
			if(map[x+i][y+i] == 0) {break;}
			else if (map[x][y] == -map[x+i][y+i]|| map[x+i][y+i] == 9) 
			{
				blk++;
				break;
			}
			else {cnt++;}
		}
		if(blk ==0) {
			if(map[x][y]==aiColor) {	//attackpoint
				switch(cnt) {
					case 0: score = 20; break;
					case 1: score = 100; break;
					case 2: score = 1000; break;
					case 3: score = 4000; break;
					case 4: score =99991;break;
					default: score = 0;
				}
			}else {
				switch(cnt) {
				case 0: score = 10; break;
				case 1: score = 30; break;
				case 2: score = 500; break;
				case 3: score = 2000; break;
				case 4: score =99990;break;
				default: score = 0;
				}
			}
		}else if (blk==1) {
			if(map[x][y]==aiColor) {	//attackpoint
				switch(cnt) {
					case 0: score = 10; break;
					case 1: score = 30; break;
					case 2: score = 300; break;
					case 3: score = 1500; break;
					case 4: score =99991;break;
					default: score = 0;
				}
			}else {
				switch(cnt) {
				case 0: score = 5; break;
				case 1: score = 20; break;
				case 2: score = 200; break;
				case 3: score = 1000; break;
				case 4: score =99990;break;
				default: score = 0;
				}
			}
		}else {
			score = 0;
		}
		return score;
	}
	private int diaS2(int x, int y, int aiColor) {
		int score = 0;
		int cnt =1;
		int blk =0;
		for(int i =1;i<5;i++) {
			if(map[x+i][y-i] == 0) {break;}
			else if (map[x][y] == -map[x+i][y-i]|| map[x+i][y-i] == 9) 
			{
				blk++;
				break;
			}
			else {cnt++;}
		}
		for(int i = -1;i>-5;i--) {
			if(map[x+i][y-i] == 0) {break;}
			else if (map[x][y] == -map[x+i][y-i]|| map[x+i][y-i] == 9) 
			{
				blk++;
				break;
			}
			else {cnt++;}
		}
		if(blk ==0) {
			if(map[x][y]==aiColor) {	//attackpoint
				switch(cnt) {
					case 0: score = 20; break;
					case 1: score = 100; break;
					case 2: score = 1000; break;
					case 3: score = 4000; break;
					case 4: score =99991;break;
					default: score = 0;
				}
			}else {
				switch(cnt) {
				case 0: score = 10; break;
				case 1: score = 30; break;
				case 2: score = 500; break;
				case 3: score = 2000; break;
				case 4: score =99990;break;
				default: score = 0;
				}
			}
		}else if (blk==1) {
			if(map[x][y]==aiColor) {	//attackpoint
				switch(cnt) {
					case 0: score = 10; break;
					case 1: score = 30; break;
					case 2: score = 300; break;
					case 3: score = 1500; break;
					case 4: score =99991;break;
					default: score = 0;
				}
			}else {
				switch(cnt) {
				case 0: score = 5; break;
				case 1: score = 20; break;
				case 2: score = 200; break;
				case 3: score = 1000; break;
				case 4: score =99990;break;
				default: score = 0;
				}
			}
		}else {
			score = 0;
		}
		return score;
	}
	
	
	
	
	
}
