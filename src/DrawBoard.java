import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class DrawBoard extends JPanel{
	Map map;
	
	public DrawBoard(Map map) {
		this.map = map;
		setBackground(new Color(232,145,55));		//set background color of boarrd
		setLayout(null);
	}
	@Override
	public void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		drawMap(arg0);
		drawStone(arg0);
	}
	public void drawMap(Graphics arg0) {
		for(int i=1; i<=map.getSize();i++) {
			arg0.drawLine(30*2, (i+1)*30, 30*(map.getSize()+1), (i+1)*30);		//draw lines for board
			arg0.drawLine((i+1)*30, 30*2, (i+1)*30, 30*(map.getSize()+1));		// 1 cell is 30*30
		}
	}
	public void drawStone(Graphics arg0) {
	//draw stone of map 
		for(int x=1; x<=map.getSize(); x++) {				
			for(int y=1; y<=map.getSize(); y++) {
				if(map.getColor(x, y) == map.getBlack()) {
					drawBlack(x,y,arg0);
				}else if(map.getColor(x, y) == map.getWhite()) {
					drawWhite(x,y,arg0);
				}
			}
		}
	}
	
	public void drawBlack(int x, int y, Graphics arg0) {
		arg0.setColor(Color.black);
		arg0.fillOval(x*30 + 15, y*30 + 15, 25, 25);		//stone size is 25*25, circle.
	}
	public void drawWhite(int x, int y, Graphics arg0) {
		arg0.setColor(Color.white);
		arg0.fillOval(x*30 + 15, y*30 + 15, 25, 25);
	}
	

	
	
}
