package shadow;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

import swing.ScrollableJTable;

public class HomeFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HomePanel homePanel;
	private JTextField txtSearch;
	private JTextArea txtrPuttyDetailsLogin;
	private HomeFrame homeFrame;
	private JList<String> list;
	private User user;
	private DefaultListModel<String> model;
	private JLabel lblUploading;
	
	public HomeFrame(User user) {
		this.user = user;
		homeFrame = this;
		homePanel = new HomePanel();
		homePanel.setBackground(Color.WHITE);
		List<Project> projects = ProjectRest.getProjects(user.getAuthenticationToken(), this);
		
		List<String> projectsName = projects.stream().map(project -> project.getName()).collect(Collectors.toList());
		model = new DefaultListModel<>();
		// Initialize the list with items
		for (String projectName : projectsName) {
			model.addElement(projectName);
		}
		list = new JList<>(model);
		list.setToolTipText("Press delete to delete the project\r\nCtrl+N to add new project");
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setSelectedIndex(0);
		list.setVisibleRowCount(5);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		list.addMouseListener(new MouseListener() {
			
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
				
				if(list.getSelectedIndex() == -1){
					return;
				}
				
				if(SwingUtilities.isRightMouseButton(e)){
					JPopupMenu popup = new JPopupMenu();
					popup.add(new JMenuItem("Remove")).addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							int value = JOptionPane.showConfirmDialog(homeFrame, "Do you want to delete??");
							if(value == JOptionPane.YES_OPTION){
								deleteProject(projects);
							}
							
						}
					});
					
					popup.add(new JMenuItem("Rename")).addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(list.getSelectedIndex() == -1){
								return;
							}
							Project projectToRename = projects.get(list.getSelectedIndex());
							String projectTitle = JOptionPane.showInputDialog(null,"Enter project title",projectToRename.getName());
							if (projectTitle == null || (projectTitle != null && ("".equals(projectTitle)))) {
								return;
							}
							
							boolean success = ProjectRest.updateProjectName(user.getAuthenticationToken(), projectToRename.getId(),projectTitle, homeFrame);
							if(success){
								projectToRename.setName(projectTitle);
								model.set(list.getSelectedIndex(), projectTitle);
							}
						}
					});
					list.setComponentPopupMenu(popup);
				}
			}
		});
		
		list.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == 127 && list.getSelectedIndex() != -1){
					int value = JOptionPane.showConfirmDialog(homeFrame, "Do you want to delete");
					if(value == JOptionPane.YES_OPTION){
						deleteProject(projects);
					}
				}
				
			}
		});
		
		txtrPuttyDetailsLogin = new JTextArea();
		txtrPuttyDetailsLogin.setToolTipText("Ctrl+S to save the changes");
		txtrPuttyDetailsLogin.setBorder(new LineBorder(new Color(0, 0, 0)));

		if (list.getSelectedIndex() != -1) {
			Project project = projects.get(list.getSelectedIndex());
			if (project != null) {
				txtrPuttyDetailsLogin.setText(project.getDescription());
			}
		}

		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(list.getSelectedIndex() == -1){
					txtrPuttyDetailsLogin.setText(null);
					return;
				}
				Project project = projects.get(list.getSelectedIndex());
				txtrPuttyDetailsLogin.setText(project.getDescription());
			}
		});
		txtrPuttyDetailsLogin.setBackground(Color.WHITE);
		txtrPuttyDetailsLogin.setFont(new Font("Monospaced", Font.PLAIN, 13));
		txtrPuttyDetailsLogin.setColumns(20);
		txtrPuttyDetailsLogin.setLineWrap(true);
		txtrPuttyDetailsLogin.setRows(5);
		txtrPuttyDetailsLogin.setWrapStyleWord(true);
		txtrPuttyDetailsLogin.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("key realsed");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
					
					if(list.getSelectedIndex() == -1){
						return;
					}
					Project project = projects.get(list.getSelectedIndex());
					project.setDescription(txtrPuttyDetailsLogin.getText());
					if (project != null) {
						ProjectRest.updateProject(project, user.getAuthenticationToken(), homeFrame);
					}

	            }
			}
			
		});

		JEditorPane editorPane = new JEditorPane();

		JButton btnAddNew = new JButton("Add new");
		btnAddNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String projectTitle = JOptionPane.showInputDialog("Enter project title");
				if (projectTitle == null || (projectTitle != null && ("".equals(projectTitle)))) {
					return;
				}
				Project project = ProjectRest.saveProjectName(projectTitle, user.getAuthenticationToken());
				projects.add(project);
				model.addElement(projectTitle);
				if (list.getSelectedIndex() == -1) {
					// list is empty
					list.setSelectedIndex(0);
				}
			}
		});

		txtSearch = new JTextField();
		txtSearch.setText("Search");
		txtSearch.setColumns(10);

		JTextArea textArea_1 = new JTextArea();

		JLabel lblYourFiles = new JLabel("YOUR FILES");
		lblYourFiles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JScrollPane jScrollPane = new JScrollPane(list);

		JScrollPane scrollForTextArea = new JScrollPane(txtrPuttyDetailsLogin);
		
		JLabel lblViewAttachments = new JLabel("<HTML><U>View attachments</U></HTML>");
		lblViewAttachments.setIcon(new ImageIcon("C:\\Users\\52018588\\Downloads\\attachment.png"));
		lblViewAttachments.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblViewAttachments.setForeground(Color.BLUE);
		lblViewAttachments.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		lblViewAttachments.addMouseListener(new MouseListener() {
			
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
				homeFrame.dispose();
				if (list.getSelectedIndex() != -1) {
					Project project = projects.get(list.getSelectedIndex());
					new ScrollableJTable(user,project.getId());
				}
				
				
			}
		});
		
		
		lblUploading = new JLabel("");
		lblUploading.setIcon(new ImageIcon("C:\\Users\\52018588\\Downloads\\ajax-loader (3).gif"));
			
		JLabel lblUploadDocument = new JLabel("<HTML><U>Upload document</U></HTML>");
		lblUploadDocument.setIcon(new ImageIcon("C:\\Users\\52018588\\Downloads\\attachment.png"));
		lblUploadDocument.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUploadDocument.setForeground(Color.BLUE);
		lblUploadDocument.addMouseListener(new MouseListener() {
			
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
				
				if (list.getSelectedIndex() != -1) {
					Project project = projects.get(list.getSelectedIndex());
					JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
					  
		            // invoke the showsSaveDialog function to show the save dialog 
		            int r = j.showSaveDialog(null); 
		  
		            // if the user selects a file 
		            if (r == JFileChooser.APPROVE_OPTION) 
		  
		            { 
		                // set the label to the path of the selected file 
		                String path = j.getSelectedFile().getAbsolutePath();
		                boolean fileUploadSuccess = ProjectRest.uploadDocument(user.getAuthenticationToken(), path, project.getId(), homeFrame);
		                if(!fileUploadSuccess){
		                	JOptionPane.showMessageDialog(homeFrame, "Error in upload file", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            } 
		            // if the user cancelled the operation 
		            else
		                System.out.println("the user cancelled the operation"); 
				} else {
					JOptionPane.showMessageDialog(homeFrame, "Please select the project", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		GroupLayout gl_homePanel = new GroupLayout(homePanel);
		gl_homePanel.setHorizontalGroup(
			gl_homePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_homePanel.createSequentialGroup()
					.addGroup(gl_homePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_homePanel.createSequentialGroup()
							.addGap(174)
							.addComponent(lblYourFiles)
							.addGap(97)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(117))
						.addGroup(gl_homePanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnAddNew)
							.addPreferredGap(ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
							.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_homePanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
							.addGroup(gl_homePanel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollForTextArea, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_homePanel.createSequentialGroup()
									.addGroup(gl_homePanel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblViewAttachments, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblUploadDocument, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
									.addGap(18)
									.addComponent(lblUploading)))))
					.addContainerGap())
		);
		gl_homePanel.setVerticalGroup(
			gl_homePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_homePanel.createSequentialGroup()
					.addGroup(gl_homePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_homePanel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_homePanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_homePanel.createSequentialGroup()
									.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddNew))
								.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(27))
						.addGroup(gl_homePanel.createSequentialGroup()
							.addGap(25)
							.addComponent(lblYourFiles)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_homePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollForTextArea, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
						.addComponent(jScrollPane, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblViewAttachments)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_homePanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUploadDocument)
						.addComponent(lblUploading))
					.addGap(213))
		);
		homePanel.setLayout(gl_homePanel);
		setupFrame();
	}

	private void setupFrame() {
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(SystemColor.textHighlight);
		menuBar.setBackground(SystemColor.textHighlight);
		setJMenuBar(menuBar);
		
		JMenu mnPrabjotSingh = new JMenu(user.getFirstName() + " " + user.getLastName());
		mnPrabjotSingh.setForeground(SystemColor.desktop);
		menuBar.add(mnPrabjotSingh);
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mnPrabjotSingh.add(mntmLogout).addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				LogoutRest.logout(user.getAuthenticationToken(), homeFrame);
				homeFrame.dispose();
				new LoginFrame(); //go to login page
			}
		});
		
		this.setContentPane(homePanel);
		this.pack();
		this.setSize(500, 600);
		FrameCommonProperties.centreWindow(this);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setVisible(true);
		

	}
	
	private void deleteProject(List<Project> projects){
		
		if(list.getSelectedIndex() == -1){
			return;
		}
		int indexToRemove = list.getSelectedIndex();
		Project project = projects.get(indexToRemove);	
		boolean success = ProjectRest.deleteProject(user.getAuthenticationToken(), project.getId(), homeFrame);
		if(success){
			projects.remove(indexToRemove);
			model.removeElementAt(indexToRemove);
		}
		
		if(list.getSelectedIndex() != -1){
			project = projects.get(list.getSelectedIndex());
			if (project != null) {
				txtrPuttyDetailsLogin.setText(project.getDescription());
			}
		}
	}
}
