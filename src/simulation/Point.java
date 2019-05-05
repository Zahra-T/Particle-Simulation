package simulation;
import fileManager.myPanels;

public class Point implements Comparable{
	
	public myPanels myPanels;
	public int x;
	public int y;
	public Point inner;
	double m;
	public Point(int x, int y)
	{
//		this.myPanels = myPanels;
		this.x = x;
		this.y = y;
//		System.out.println(inner.x);
//		inner = myPanels.pnlSetting.getInnerPoint();
//		
//		if(this.x != inner.x) {
//		this.m = (y - inner.y)/(x - inner.x);
//		}
//		else
//		{
//			this.m = Double.MAX_VALUE;
//		}
	}

	@Override
	public int compareTo(Object arg0) {
		Point p = (Point)arg0;
		if (this.x>p.x) return 1;
		else if(this.x == p.x) return 0;
		else return -1;
	}
}
