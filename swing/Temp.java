package swing;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Temp extends JPanel {
	
	static JFrame frame;
	
	public Temp(){
        DefaultListModel list1items = new DefaultListModel();
        list1items.addElement("-");
        for(int i = 0; i < 10; i++)
            list1items.addElement("Item " + i);
        JList list1 = new JList(list1items);
        list1.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        JScrollPane list1scr = new JScrollPane(list1);
        list1.setVisibleRowCount(8);
        add (list1scr);
        
        JScrollPane scrollPane = new JScrollPane();
        list1scr.setRowHeaderView(scrollPane);
    }


    public static void main(String[] args) {
    	frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Temp());
        frame.pack();
        frame.setVisible(true);
    }
}
