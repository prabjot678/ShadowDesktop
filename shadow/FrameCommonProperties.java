package shadow;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

public final class FrameCommonProperties {
	
	private FrameCommonProperties(){};
	
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}

}
