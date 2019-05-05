package simulation;

import java.awt.Color;
import java.util.ArrayList;

import fileManager.Information;
import fileManager.myPanels;

public class Calculate {

	ArrayList<circle> circles;
	ArrayList<lineEquation> lineEqs;
	public ArrayList<Action> Actions = new ArrayList();
	long duration;

	public Calculate(Information information, myPanels myPanels)
	{
		this.circles = information.circlesInfo;
		this.lineEqs = myPanels.pnlSetting.getLineEquations();
		this.duration = information.timen;
	}


	public double getCollisionTime(circle g, circle e)
	{
		double x01 = g.x, y01 = g.y;
		double x02 = e.x, y02 = e.y;

		double a = (g.vx - e.vx)*(g.vx - e.vx) + (g.vy - e.vy)*(g.vy - e.vy);
		double b = 2 * (x01 - x02) * (g.vx - e.vx) + 2 * (y01 - y02) * (g.vy - e.vy);
		double c = (x01 - x02)*(x01 - x02) + (y01 - y02)*(y01 - y02) - (e.r + g.r)*(e.r + g.r);
		double delta = b*b - 4*a*c;
		double t1 = (-b + Math.sqrt(delta))/ (2*a);
		double t2 = (-b - Math.sqrt(delta))/ (2*a);
		double t = -1;
		if(t1 >= 0 && t2<0) 
		{
			t = t1;
		}
		else if(t1 < 0 && t2 >= 0)
		{
			t = t2;
		}
		else if( t1 >= 0 && t2 >= 0 )
		{
			t = Math.min(t1 , t2);
		}

		return t;
	}

	public double getHitTheWallTime(circle g, lineEquation l)
	{
		double x0c = g.x, y0c = g.y;
		double a = (l.a * g.vx + l.b * g.vy) * (l.a * g.vx + l.b * g.vy);
		double b = 2*(l.a * g.vx + l.b * g.vy)*(l.a * x0c + l.b * y0c + l.c);
		double c = (l.a * x0c + l.b * y0c + l.c)*(l.a * x0c + l.b * y0c + l.c) - (g.r)*(g.r)*(l.a*l.a + l.b*l.b);

		double delta = b*b - 4*a*c;
		double t1 = (-b + Math.sqrt(delta))/ (2*a);
		double t2 = (-b - Math.sqrt(delta))/ (2*a);
		double t = -1;
		if(t1 >= 0 && t2<0) 
		{
			t = t1;
		}
		else if(t1 < 0 && t2 >= 0)
		{
			t = t2;
		}
		else if( t1 >= 0 && t2 >= 0 )
		{
			t = Math.min(t1 , t2);
		}

		return t;
	}

	public void change()
	{
//		ArrayList <ArrayList<Triple>> collisions = new ArrayList();
		while(duration > 0)
		{
			ArrayList<Triple> queue = calculate();
			Triple tr = queue.get(0);
			Action ac = new Action();
			double t = tr.getT();

			if(duration >= t) {
				if (t == Integer.MAX_VALUE) // yani age dige barkhordi nabud
				{
					t = duration;
				}

				int n = (int)(t/0.4);
				ac.n = n;


				for(int i = 0; i<circles.size(); i++)
				{
					circle c = circles.get(i);
					c.x = c.vx * t + c.x;
					c.y = c.vy * t + c.y;
				}

				for(int i = 0; i<queue.size(); i++)
				{
					Triple tp = queue.get(i);
					if(tp.wallCollision)
					{
						lineEquation lineEq = tp.getLineEq();
						circle e = tp.getC1();
						double a = lineEq.a;
						double b = lineEq.b;
						double c = lineEq.c;
						//						double d = Math.abs((e.x*a+e.y*b+c))/Math.sqrt((a*a+b*b));

						//						if(d <= e.r) {
						double vpx = (((e.vx)*a + (e.vy)*b)/(a*a+b*b))*a;
						double vpy = (((e.vx)*a + (e.vy)*b)/(a*a+b*b))*b;
						double vppx = 2*vpx - e.vx;
						double vppy = 2*vpy - e.vy;
						e.x = e.vx*tp.t + e.x;
						e.y = e.vy*tp.t + e.y;
						e.vx = -vppx;
						e.vy = -vppy;

						//						}
					}
					else
					{
						circle a = tp.getC1();
						circle b = tp.getC2();
						double cosc = (a.vx* b.vx + a.vy * b.vy)/(a.v*b.v);
						System.out.println("cosc:"+cosc);
						double sinc = Math.sqrt(1-cosc*cosc);
						System.out.println("sinc:"+sinc);
						double sina = a.getSinx()*cosc - a.getCosx()*sinc;
						System.out.println("sina:"+sina);
						double cosa = a.getCosx()*cosc + a.getSinx()*sinc;
						System.out.println("cosa:"+cosa);
						double sinb = b.getSinx()*cosc - b.getCosx()*sinc;
						System.out.println("sinb:"+sinb);
						double cosb = b.getCosx()*cosc + b.getSinx()*sinc;
						System.out.println("cosb:"+cosb);

						//						a.x = a.vx * tp.t + a.x;
						//						a.y = a.vy * tp.t + a.y;
						a.vx = (a.v * cosa*(a.m - b.m) + 2*b.m*b.v*cosb)*cosc / (a.m+b.m) + a.v*sina*sinc;
						System.out.println("a.vx:"+a.vx);
						a.vy = (a.v * cosa*(a.m - b.m) + 2*b.m*b.v*cosb)*sinc / (a.m+b.m) + a.v*sina*sinc;
						a.v = Math.sqrt(a.vx*a.vx+a.vy*a.vy);

						//						b.x = b.vx * tp.t + b.x;
						//						b.y = b.vy * tp.t + b.y;
						b.vx = (b.v * cosb*(b.m - a.m) + 2*a.m*a.v*cosa)*cosc / (a.m+b.m) + b.v*sinb*sinc;
						b.vy = (b.v * cosb*(b.m - a.m) + 2*a.m*a.v*cosa)*sinc / (a.m+b.m) + b.v*sinb*sinc;
						b.v = Math.sqrt(b.vx*b.vx + b.vy*b.vy);
					}
				}
				for(int i = 0; i<circles.size(); i++)
				{
					circle g = circles.get(i);
					double r = g.getR();
					double x = g.getX();
					double y = g.getY();
					double vx = g.vx;
					double vy = g.vy;
					double m = g.m;
					int color = g.color;
					Color clr = g.clr;
					circle c = new circle(r, x, y, vx, vy, m, color);
					ac.addCircle(g);
				}
				Actions.add(ac);

//				duration -= t;
			}
			else
			{
				int n = (int)(duration/0.4);
				ac.n = n;
				ac.theEndPart = true;
				Actions.add(ac);
			}
			duration -= t;

		}
	}

	public ArrayList<Triple> calculate()
	{
		Triple tr = new Triple();
		tr.setT(Integer.MAX_VALUE);
		ArrayList<Triple> queue = new ArrayList();
		queue.add(tr);

		for(int i = 0; i<circles.size(); i++)
		{
			circle g = circles.get(i);
			for(int j = i+1; j<circles.size(); j++)
			{
				circle e = circles.get(j);
				double t = getCollisionTime(g, e);
				if(t<tr.getT())
				{
					queue.clear();
					tr.setT(t);
					tr.setC1(g);
					tr.setC2(e);
					queue.add(tr);
				}
				else if(t == tr.getT())
				{
					Triple trp = new Triple();
					trp.setT(t);
					trp.setC1(g);
					trp.setC2(e);
					queue.add(trp);
				}
			}
		}

		for(int i = 0; i<circles.size(); i++)
		{
			circle g = circles.get(i);
			for(int j = 0; j<lineEqs.size(); j++)
			{
				lineEquation l = lineEqs.get(j);
				double t = getHitTheWallTime(g, l);
				if(t<tr.getT())
				{
					queue.clear();
					tr.setT(t);
					tr.setC1(g);
					tr.setC2(null);
					tr.setLineEq(l);
					tr.setWallCollision(true);
				}
				else if(t == tr.getT())
				{
					Triple trp = new Triple();
					trp.setT(t);
					trp.setC1(g);
					trp.setLineEq(l);
					trp.setWallCollision(true);
					queue.add(trp);
				}
			}
		}
		return queue;
	}






}



