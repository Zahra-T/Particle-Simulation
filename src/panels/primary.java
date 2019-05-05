package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import simulation.Polygon;
import simulation.circle;
import panels.myMouseListener;
import fileManager.myPanels;
import simulation.Line;
public class primary extends JPanel{
	public Polygon polygon;
	ArrayList <circle>circles = new ArrayList();
	ArrayList <Line>lines = new ArrayList();
	myPanels myPanels;
	public primary(myPanels myPanels)
	{
		this.myPanels = myPanels;
		primarySetting();
	}
	public void primarySetting()
	{
		this.setLayout(null);
		this.setBounds(3,3,466,458);    
		this.setBackground(Color.white);
		this.setVisible(true);
		this.setEnabled(true);
		this.addMouseListener(new myMouseListener(myPanels)); //problem?
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(polygon != null)
		{
			this.setBackground(Color.black);
			polygon.draw(g);
		}
		if(circles != null) {
			for(circle c : this.circles)
			{
				g.drawOval((int)(c.x - c.r), (int) (c.y - c.r), (int)(2*c.r),(int)(2* c.r));
				g.fillOval((int)(c.x - c.r), (int) (c.y - c.r), (int)(2*c.r),(int)(2* c.r));
				g.setColor(c.clr);
			}
		}
		if(lines != null)
		{
			for(Line l : this.lines)
			{
				g.drawLine(l.start.x, l.start.y, l.end.x, l.end.y);
				g.setColor(Color.black);
			}
		}
	}
	
	public void addLine(Line l)
	{
		lines.add(l);
		this.repaint();
	}
	public void addCircle(circle c)
	{
		circles.add(c);
		this.repaint();
	}

}
