package swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.JTableHeader;

import shadow.AttachmentResponse;
import shadow.FrameCommonProperties;
import shadow.GetAttachmentResponse;
import shadow.HomeFrame;
import shadow.ProjectRest;
import shadow.User;

public class ScrollableJTable {
	
	private User user;
	
	

	public ScrollableJTable(User user,String projectId) {
		this.user = user;
		JFrame frame = new JFrame("Your files!");
		JPanel panel = new JPanel();
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		GetAttachmentResponse attachmentResponse = ProjectRest.getAttachments(user.getAuthenticationToken(), projectId, frame);
		
		List<AttachmentResponse> attachments = attachmentResponse.getAttachments();
		String data[][] = new String[attachments.size()][4];
		int i = 0;
		for(AttachmentResponse response : attachments){
			data[i][0] = response.getName();
			data[i][1] = response.getCreatedAt();
			data[i][2] = response.getLastModified();
			data[i][3] = response.getSize();
			i++;
		}
		
		
		
		
		String col[] = { "File name", "Created", "Last modified", "Size" };
		JTable table = new JTable(data, col);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		JScrollPane pane = new JScrollPane(table);
		sl_panel.putConstraint(SpringLayout.NORTH, pane, -409, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, pane, 32, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, pane, -37, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, pane, -28, SpringLayout.EAST, panel);
		pane.setAlignmentX(JScrollPane.LEFT_ALIGNMENT);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		panel.add(pane);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("<HTML><U></U></HTML>");
		sl_panel.putConstraint(SpringLayout.NORTH, lblNewLabel, 6, SpringLayout.SOUTH, pane);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, pane);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\52018588\\Downloads\\icons8-go-back-26.png"));
		lblNewLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();
				new HomeFrame(user);
			}
		});
		panel.add(lblNewLabel);
		frame.setSize(500, 500);
		FrameCommonProperties.centreWindow(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	
	public static void main(String args[]){
		new ScrollableJTable(null, null);
	}
}
