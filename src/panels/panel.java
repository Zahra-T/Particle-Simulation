package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JPanel;

import simulation.Point;
import simulation.Polygon;
import simulation.circle;
import simulation.lineEquation;


public class panel extends JPanel{
	Polygon polygon;
	ArrayList<circle>circles;
	public boolean isPanelSettingFront = true;
	public pnlSetting pnlSetting;

	public panel(pnlSetting pnlSetting)
	{
		this.pnlSetting = pnlSetting;
		primarySetting();
		// needed?
		//			mouseClick();

	}

	public void primarySetting()
	{
		this.setLayout(null);
		this.setBounds(3,3,466,458);    
		this.setBackground(Color.white);
		this.setVisible(false);
		this.setEnabled(true);
	}

	public void setPolygon(Polygon p)
	{
		this.polygon = p;
	}
	public void setCircles(ArrayList<circle>circles)
	{
		this.circles = circles;
	}


	//		public void mouseClick()
	//		{
	//			this.addMouseListener((e)->{
	//				
	//			});
	//		}
	@Override
	public void paint(Graphics g)
	{

		super.paint(g);
		if(polygon != null)
		{
			this.setBackground(Color.black);
			polygon.draw(g);
			
		}
		if(circles != null )
		{
			for (circle a : circles) {
				g.drawOval((int) (a.x - a.r), (int) (a.y - a.r), (int) Math.round(a.r * 2), (int) Math.round(a.r * 2));//why?
				g.setColor(a.getColor());
				g.fillOval((int) (a.x - a.r), (int) (a.y - a.r), (int) Math.round(a.r * 2), (int) Math.round(a.r * 2));
			}
		}


	}


	public void start(long duration) throws InterruptedException
	{

					Date d = new Date();
					long startTime = d.getTime();
					long currentTime = d.getTime();
//		long startTime = System.currentTimeMillis();
//		long currentTime = System.currentTimeMillis();

		while(!(currentTime - startTime >= duration))
		{
			for(circle s : circles)
			{
				move(s);
				//					this.repaint();
			}
							this.repaint();
							Date a = new Date();
							currentTime = a.getTime();
//			currentTime = System.currentTimeMillis();
			Thread.sleep(40);
		}
	}

	public void move(circle a) {//gam zamani az biroon taeen mishe?
		a.x += (double) a.vx /25;
		a.y += (double) a.vy /25;

		hitTheContainer(a);

		for(circle b: circles)
		{
			if(!b.equals(a))
			{
				double d = Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
				if(d <= a.r+b.r )//&& a.hit == false && b.hit == false)
				{
					a.x -= (double)a.vx / 25;
					a.y -= (double)a.vy / 25;
					b.x -= (double)b.vx / 25;
					b.y -= (double)b.vy / 25;
					
					double cosc = (a.vx* b.vx + a.vy * b.vy)/(a.v*b.v);
					double sinc = Math.sqrt(1-cosc*cosc);
					double sina = a.getSinx()*cosc - a.getCosx()*sinc;
					double cosa = a.getCosx()*cosc + a.getSinx()*sinc;
					double sinb = b.getSinx()*cosc - b.getCosx()*sinc;
					double cosb = b.getCosx()*cosc + b.getSinx()*sinc;
					a.vx = (a.v * cosa*(a.m - b.m) + 2*b.m*b.v*cosb)*cosc / (a.m+b.m) + a.v*sina*sinc;
					a.vy = (a.v * cosa*(a.m - b.m) + 2*b.m*b.v*cosb)*sinc / (a.m+b.m) + a.v*sina*sinc;
					a.v = Math.sqrt(a.vx*a.vx+a.vy*a.vy);
					b.vx = (b.v * cosb*(b.m - a.m) + 2*a.m*a.v*cosa)*cosc / (a.m+b.m) + b.v*sinb*sinc;
					b.vy = (b.v * cosb*(b.m - a.m) + 2*a.m*a.v*cosa)*sinc / (a.m+b.m) + b.v*sinb*sinc;
					b.v = Math.sqrt(b.vx*b.vx + b.vy*b.vy);
					
//					a.hit = true;
//					b.hit = true;
//					a.x += (double)a.vx / 25;
//					a.y += (double)a.vy / 25;
//					b.x += (double)b.vx / 25;
//					b.y += (double)b.vy / 25;
				}
//				else if(d > a.r+b.r && a.hit && b.hit)
//				{
//					a.hit = false;
//					b.hit = false;
//				}
			}
		}
		
		revalidate();
		repaint();

	}

	public void hitTheContainer(circle c)
	{
					ArrayList <lineEquation> lines = pnlSetting.getLineEquations();
					hitTheWall(lines, c);
					lineEquation l = lines.get(0);
					lineEquation l2 = lines.get(1);


		if(c.x + c.r > getWidth())
		{
			c.x = getWidth() - c.r;
			c.vx *= -1;
		}
		
		else if(c.x - c.r < 0)
		{
			c.x = c.r;
			c.vx *= -1;
		}
		
		if(c.y+(c.r)>getHeight())
		{
			c.y = getHeight() - c.r;
			c.vy *= -1;
		}
		else if(c.y - c.r < 0)
		{
			c.y = c.r;
			c.vy *= -1;
		}
	}

	public void hitTheWall(ArrayList <lineEquation> lines, circle e)
	{
		for(int i = 0; i<lines.size(); i++)
		{
			lineEquation lineEq = lines.get(i);
			double a = lineEq.a;
			double b = lineEq.b;
			double c = lineEq.c;
			Point p = lineEq.start;
			Point o = lineEq.end;
			int x1 = Math.min(p.x , o.x);
			int x2 = Math.max(p.x, o.x);
			int y1 = Math.min(p.y, o.y);
			int y2 = Math.max(p.y,  o.y);
			double d = Math.abs((e.x*a+e.y*b+c))/Math.sqrt((a*a+b*b));
			
			if(d <= e.r && (((e.x < x2) && (e.x > x1)) || ((e.y<y2)&&(e.y>y1)))) {
			double vpx = (((e.vx)*a + (e.vy)*b)/(a*a+b*b))*a;
			double vpy = (((e.vx)*a + (e.vy)*b)/(a*a+b*b))*b;
			double vppx = 2*vpx - e.vx;
			double vppy = 2*vpy - e.vy;
			e.vx = -vppx;
			e.vy = -vppy;
			if(e.vx >= 50 && e.vy <50) {
			e.x += e.vx / (25*10);
			e.y += e.vy/25 ;
			}
			else if(e.vx >= 50 && e.vy >= 50)
			{
				e.x += e.vx / (25*10);
				e.y += e.vy / (25*10);
			}
			else if(e.vx <50 && e.vy >=50)
			{
				e.x += e.vx/25;
				e.y += e.vy / (25*10);
			}
			else if(e.vx < 50 && e.vy <50)
			{
				e.x += e.vx/25;
				e.y += e.vy/25;
			}
			
//			double m = e.r - (Math.abs(a*e.x + b*e.y + c) / Math.sqrt(a*a+b*b));
//			double k = a*m / Math.sqrt(a*a+b*b);
//			double l = b*m / Math.sqrt(a*a+b*b);
			
//			e.x += k;
//			e.y += l;
			}

		}

	}

}
