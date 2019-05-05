package simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Polygon {
	public ArrayList<Point> vertices;
	public Polygon(ArrayList<Point> vertices)
	{
		super();
		this.vertices = vertices;
	}

	public void draw(Graphics g)
	{
		int npoints = vertices.size();
		int [] xpoints = new int[npoints];
		int [] ypoints = new int[npoints];
		for(int i = 0; i<npoints; i++)
		{
			xpoints[i] = vertices.get(i).x;
			ypoints[i] = vertices.get(i).y;
		}
		
		g.drawPolygon(xpoints, ypoints, npoints);
		g.setColor(Color.white);
		g.fillPolygon(xpoints, ypoints, npoints);
		
	}
}
