package swing;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

public class Jtable extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jtable frame = new Jtable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Jtable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		String data[][] = { { "001", "vinod", "Bihar", "India", "Biology", "65", "First" },
				{ "002", "Raju", "ABC", "Kanada", "Geography", "58", "second" },
				{ "003", "Aman", "Delhi", "India", "computer", "98", "Dictontion" },
				{ "004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion" },
				{ "001", "vinod", "Bihar", "India", "Biology", "65", "First" },
				{ "002", "Raju", "ABC", "Kanada", "Geography", "58", "second" },
				{ "003", "Aman", "Delhi", "India", "computer", "98", "Dictontion" },
				{ "004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion" },
				{ "001", "vinod", "Bihar", "India", "Biology", "65", "First" },
				{ "002", "Raju", "ABC", "Kanada", "Geography", "58", "second" },
				{ "003", "Aman", "Delhi", "India", "computer", "98", "Dictontion" },
				{ "004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion" },
				{ "001", "vinod", "Bihar", "India", "Biology", "65", "First" },
				{ "002", "Raju", "ABC", "Kanada", "Geography", "58", "second" },
				{ "003", "Aman", "Delhi", "India", "computer", "98", "Dictontion" },
				{ "004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion" },
				{ "001", "vinod", "Bihar", "India", "Biology", "65", "First" },
				{ "002", "Raju", "ABC", "Kanada", "Geography", "58", "second" },
				{ "003", "Aman", "Delhi", "India", "computer", "98", "Dictontion" },
				{ "004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion" },
				{ "001", "vinod", "Bihar", "India", "Biology", "65", "First" },
				{ "002", "Raju", "ABC", "Kanada", "Geography", "58", "second" },
				{ "003", "Aman", "Delhi", "India", "computer", "98", "Dictontion" },
				{ "004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion" },
				{ "001", "vinod", "Bihar", "India", "Biology", "65", "First" },
				{ "002", "Raju", "ABC", "Kanada", "Geography", "58", "second" },
				{ "003", "Aman", "Delhi", "India", "computer", "98", "Dictontion" },
				{ "004", "Ranjan", "Bangloor", "India", "chemestry", "90", "Dictontion" },
				{ "001", "vinod", "Bihar", "India", "Biology", "65", "First" },
				{ "002", "Raju", "ABC", "Kanada", "Geography", "58", "second" },
				{ "003", "Aman", "Delhi", "India", "computer", "98", "Dictontion" },
				{ "004", "Ranjan11111111111111111111111111111", "Bangloor", "India", "chemestry" } };
		
		String col[] = { "File name", "Created", "Last modified", "Size" };
		table = new JTable(data,col);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		//table.setBounds(25, 50, 399, 188);
		JScrollPane pane = new JScrollPane(table);
		
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		contentPane.add(pane);
		setVisible(true);
		setContentPane(contentPane);
	}
}
