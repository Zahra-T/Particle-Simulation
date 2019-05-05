package simulation;

import java.util.ArrayList;

public class Action
{
	public int n;
	public boolean theEndPart = false;
	public ArrayList<circle> circles = new ArrayList();

	public void addCircle(circle c)
	{
		circles.add(c);
	}
}
