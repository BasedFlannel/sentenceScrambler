package sentenceScrambler;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Program {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				createAndShowGUI();
			}
		});

	}
	
	public static void createAndShowGUI(){
		
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		JFrame frame = new JFrame("Scramblr v1.36");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ScramblerPanel scramblePanel = new ScramblerPanel();
		frame.add(scramblePanel);
		Border border = scramblePanel.getBorder();
		Border margin = new EmptyBorder(5,5,5,5);
		scramblePanel.setBorder(new CompoundBorder(border, margin));
		
		frame.getRootPane().setDefaultButton(scramblePanel.getMainButton());
		
		frame.setPreferredSize(new Dimension(900,600));
		frame.pack();
		frame.setVisible(true);
		
	}
}
