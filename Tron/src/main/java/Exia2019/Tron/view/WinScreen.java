package Exia2019.Tron.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import javax.swing.JPanel;

public class WinScreen extends JPanel {
	
	public String popText;
	
	public WinScreen(final String pop) {
		
		this.popText = pop;

    }

    public void paintComponent(Graphics g) {

        Graphics2D g1 = (Graphics2D) g;
        g.fillRect(0, 0, 700, 600);
        g.setColor(Color.YELLOW);
        AttributedString as1 = new AttributedString(popText);
        as1.addAttribute(TextAttribute.SIZE, 40);
        g1.drawString(as1.getIterator(), 100, 185);

    }

}
