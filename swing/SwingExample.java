package swing;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SwingExample {
	
	JLabel l1,l2;  
	JTextArea area;  
	JButton b;
	private  void createTextArea(){
		JFrame f= new JFrame();  
	    l1=new JLabel();  
	    l1.setBounds(50,25,100,30);  
	    l2=new JLabel();  
	    l2.setBounds(160,25,100,30);  
	    area=new JTextArea();  
	    area.setBounds(20,75,250,200);  
	    b=new JButton("Count Words");  
	    b.setBounds(100,300,120,30);  
	    f.add(l1);f.add(l2);f.add(area);f.add(b);  
	    f.setSize(450,450);  
	    f.setLayout(null);  
	    f.setVisible(true);  
		
	}
	
	private static void createComponenet(){
		
		JFrame jFrame = new JFrame();
		JButton jButton = new JButton("Submit");
		jButton.setBounds(250, 250, 100, 50	);
		jButton.addActionListener(new ButtonActionListener());
		jFrame.add(jButton);
		JLabel label = new JLabel("Name");
		label.setBounds(50, 50, 100, 40);
		label.setBackground(Color.blue);
		label.setForeground(Color.BLUE);
		jFrame.add(label);
		
		JTextArea area = new JTextArea();
		area.setBounds(250, 250, 100, 50);
		
		JScrollPane areaScrollPane = new JScrollPane(area);
		areaScrollPane.setVerticalScrollBarPolicy(
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		jFrame.add(areaScrollPane);
		jFrame.setSize(500, 500);
		jFrame.setLayout(null);
		jFrame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//createComponenet();
		new SwingExample().createTextArea();

	}

}
