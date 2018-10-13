package swing;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JApplet;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;

public class inv extends JApplet {


	JList listbox;

    public void init()
    {


   String  listData[] = { "Item 1","Item 2","Item 3","Item 4" };
listbox = new JList( listData );

    listbox.addMouseListener( new MouseAdapter()
    {
    	public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                JPopupMenu menu = new JPopupMenu();
                JMenuItem item = new JMenuItem("Say hello");
                item.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(inv.this, "Hello "
                                + listbox.getSelectedValue());
                    }
                });
                menu.add(item);
                menu.show(inv.this, 5, listbox.getCellBounds(
                        listbox.getSelectedIndex() + 1,
                        listbox.getSelectedIndex() + 1).y);
            }
        }
    });

   listbox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       add(listbox);
                   // unnecessary
                   //listbox.setVisible(true);

           listbox.setFocusable(false);
    }



    private int getRow(Point point)
    {
       return listbox.locationToIndex(point);
}

}
