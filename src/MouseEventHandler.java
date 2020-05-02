import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class MouseEventHandler extends MouseAdapter {
	//mouse event handling
	Map map;
	DrawBoard draw;
	int color;	//user color
	int time;		//time limit
	
	public MouseEventHandler(Map map, DrawBoard draw, int color, int time) {
		this.map = map;
		this.draw = draw;
		this.color = color;
		this.time = time;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();		//get mouse coordinate
		AI ai = new AI(map,-color,time*1000);
		
		x = (int)Math.round(((double)x/30)-1);		//translate mouse coordinates in to map coordinates
		y = (int)Math.round(((double)y/30)-2);
		
		if(x<1||y<1||x>19||y>19) {return;}		//out of bound
		if(map.getColor(x, y) == map.getBlack() || map.getColor(x, y) == map.getWhite()) {return;}		//already possessed
		
		if(!map.setColor(x, y, color))		//if user cannot put there
		{
			JOptionPane.showMessageDialog(null, "You cannot do that.");
			return;
		}
		
		
		draw.repaint();
		if(map.win(x, y)) {		//win decision for user
			if(color== 1) {JOptionPane.showMessageDialog(null, "Black win!");}
			else {JOptionPane.showMessageDialog(null, "White win!");}
			System.exit(0);
		}
		if(color==1) {color=-1;}		//change color for ai
		else {color =1;}
		
		//AI Turn
		JOptionPane.showMessageDialog(null,"AI Turn.\n Please Wait for max " + time +" seconds..");  //break for ai
		
		int[][] tempMap = new int[21][21];		//save current map
		tempMap = map.getMap();
		Node now = new Node(null,map,color);		// create root node

			
		int[] xy = ai.IDDFS(now, 10);		//run Searching algorithm
		map.setMap(tempMap);				//call current map again, because map could be dirty in algorithm

		x=xy[0];
		y=xy[1];
		map.setColor(x, y, color);			//set algorithm result to map
		
		System.out.println(x + " " + y);
		draw.repaint();
		if(map.win(x, y)) {					//win decision for ai
			if(color== 1) {JOptionPane.showMessageDialog(null, "Black win!");}
			else {JOptionPane.showMessageDialog(null, "White win!");}
			System.exit(0);
		}
		if(color==1) {color=-1;}	//color change for user
		else {color =1;}		
		return;
	}
}
