import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI extends JFrame{
	
	Container c;	
	JLabel strSelect = new JLabel("Choose your Color");
	JButton btnBlk = new JButton("Black! First!");
	JButton btnWht = new JButton("White! Second!");
	

	
	public GUI(String title) {
		buttonGui();
	}
	

	
	public void buttonGui() {
		c=getContentPane();
		setBounds(50,50,200,300);
		c.setLayout(new GridLayout(3,1));		//set layout
		
		strSelect.setHorizontalAlignment(JLabel.CENTER);
		btnBlk.addActionListener(new ActionListener() {			//black button click
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btnBlk)) {
					System.out.println("Black");
					setVisible(false);
					timeGui(1);		//user color = 1(black), ai color = -1(white)   // next->time GUI
					c.remove(btnBlk);
					c.remove(btnWht);
					c.remove(strSelect);
				}
			}
		});
		btnWht.addActionListener(new ActionListener() {			//white button click
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(btnWht)) {
					System.out.println("White");
					setVisible(false);
					timeGui(-1);					// white == -1
					c.remove(btnBlk);
					c.remove(btnWht);
					c.remove(strSelect);
				}
			}
		});
		
		c.add(strSelect);
		c.add(btnBlk);
		c.add(btnWht);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void timeGui(int color) {
		//Gui for setting time
		JLabel strTime = new JLabel("Set Time (seconds)");
		JTextField timeField = new JTextField(20);
		JButton enter = new JButton("Play");
		
		timeField.setText("10");			//default time = 10s
		
		c= getContentPane();
		setBounds(50, 50, 200,150);
		c.setLayout(null);
		c.add(strTime);
		strTime.setBounds(0, 0, 200, 30);
		c.add(timeField);
		timeField.setBounds(0,30,200,20);
		c.add(enter);
		enter.setBounds(50, 50, 100, 30);			//setting layouts
		enter.addActionListener(new ActionListener() {			//button clicked
			public void actionPerformed(ActionEvent e) {
				Object obj = e.getSource();
				if(obj.equals(enter)) {
					String timeStr = timeField.getText();
					int timen;
					try {
						timen = Integer.parseInt(timeStr);
					}catch(Exception ep) {			//parse error, return
						JOptionPane.showMessageDialog(null, "Please write positive integer");
						return;
					}
					if(timen<=0) {			//time must be positive
						JOptionPane.showMessageDialog(null, "Please write positive integer");
						return;
					}
					setVisible(false);		//no error, disable this GUI
					boardGui(color,timen);	//next-> board GUI
				}
			}
		});
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//GUI for board
	public void boardGui(int color, int time) {
		c= getContentPane();
		setBounds (100,100,680,750);		//Layout
		c.setLayout(null);
		Map map = new Map(19);
		if(color == -1) {		//if ai black, first is center.
			map.setColor(10, 10, 1);
		}
		DrawBoard draw = new DrawBoard(map);
		setContentPane(draw);
		addMouseListener(new MouseEventHandler(map,draw,color,time));		//if mouse clicked.
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
