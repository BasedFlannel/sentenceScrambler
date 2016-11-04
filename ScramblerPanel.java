package sentenceScrambler;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ScramblerPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 6238863486819354886L;
	private Scrambler strScrambler;
	protected JTextArea inputArea;
	protected JTextArea outputArea;
	protected JButton mainButton;
	
	public ScramblerPanel(){
		super(new GridBagLayout());
		
		Font appFont = new Font("Courier New", Font.BOLD, 26);
		Font appFontSmall = new Font("Courier New", Font.BOLD, 20);
		//setup scrambler
		this.strScrambler = new Scrambler();
		
		//setup input label
		JLabel inputLabel = new JLabel("Input");
		inputLabel.setFont(appFontSmall);
		
		//setup input area
		this.inputArea = new JTextArea();
		this.inputArea.setLineWrap(true);
		this.inputArea.setWrapStyleWord(true);
		this.inputArea.setFont(appFont);
		this.inputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		JScrollPane inputScrollPane = new JScrollPane(inputArea);
		inputScrollPane.setPreferredSize(new Dimension(890,450));
		
		//setup output area
		this.outputArea = new JTextArea();
		this.outputArea.setEditable(false);
		this.outputArea.setLineWrap(true);
		this.outputArea.setWrapStyleWord(true);
		this.outputArea.setFont(appFont);
		this.outputArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		JScrollPane outputScrollPane = new JScrollPane(outputArea);
		outputScrollPane.setPreferredSize(new Dimension(890,20));
		
		//setup main button
		this.mainButton = new JButton("Scramble");
		this.mainButton.addActionListener(this);
		this.mainButton.setFont(appFontSmall);
		
		//add components to frame
		int pad = 5;
		GridBagConstraints c = new GridBagConstraints();
		
		//add input label
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1.0;
		c.weighty = 0.0;
		add(inputLabel);
		
		//add inputArea
		c.insets = new Insets(0,0,pad/2,0);
		c.gridwidth = 2;
		c.gridy++;
		c.weighty = 1.0;
		add(inputScrollPane, c);
		
		//add main button
		c.insets = new Insets(0,0,0,0);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy++;
		c.weighty = 0.0;
		add(mainButton, c);
		
		//add outputArea
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(pad/2,0,0,0);
		c.gridy++;
		c.weighty = 1.0;
		add(outputScrollPane, c);
		
	}
	
	public JButton getMainButton(){
		return this.mainButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String text = strScrambler.shuffleString(inputArea.getText());
		outputArea.setText(text);
		outputArea.selectAll();
		outputArea.copy();
	}
}
