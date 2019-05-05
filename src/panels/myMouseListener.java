package panels;

import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

import simulation.Line;
import simulation.Point;
import simulation.circle;
import fileManager.myPanels;

public class myMouseListener extends MouseAdapter{
	boolean isDrawingCircle = false;
	boolean isDrawingLine = false;
	myPanels myPanels;

	public myMouseListener(myPanels myPanels)
	{
		this.myPanels = myPanels;

	}
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(myPanels.panel.isPanelSettingFront)
		{
			JTextField inner = myPanels.pnlSetting.innerPoint;
			ArrayList<JTextField> f = myPanels.pnlSetting.vertex;

			if(inner.getText().equals(""))
			{
				inner.setText(e.getX()+"-"+e.getY());
			}
			else {
				for(JTextField m: f)
				{
					if(m.getText().equals(""))
					{
						m.setText(e.getX()+"-"+e.getY());
						break;
					}
				}
			}
		}
		else
		{
			ArrayList <JTextField[]> f = myPanels.crclSetting.circleFields;
			for(int i = 0; i<f.size(); i++)
			{
				JTextField x = f.get(i)[1];
				JTextField y = f.get(i)[2];
				if(x.getText().equals("") || y.getText().equals(""))
				{
					x.setText(e.getX()+"");
					y.setText(e.getY()+"");
					break;
				}
			}
			//				pnlSetting.innerPoint.setText("hello");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!myPanels.panel.isPanelSettingFront && myPanels.crclSetting.emptyField() != -1 && myPanels.crclSetting.isDrawingCircle)
		{
			circle c = new circle();
			c.getColor();
			myPanels.primary.addCircle(c);

			c.setX(e.getX());
			c.setY(e.getY());
			c.setR(0);

			Thread t = new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						drawingCircle(c);
					} catch (Exception e1) {//what?
						e1.printStackTrace();
					} 
				}
			});
			t.start(); 
		}
		else if(!myPanels.panel.isPanelSettingFront&& isOnCircle(new Point(e.getX(), e.getY()))  && !myPanels.crclSetting.isDrawingCircle)
		{

			Line l = new Line();
			myPanels.primary.addLine(l);
			l.setStart(new Point(e.getX(),e.getY()));
			l.setEnd(new Point(e.getX(), e.getY()));
			Thread t = new Thread(new Runnable()
			{

				@Override
				public void run() {
					try {
						drawingLine(l);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			});
			t.start();
		}



		//			try {
		//				
		//				drawing(c);
		//			} catch (InterruptedException e1) {
		//				// TODO Auto-generated catch block
		//				e1.printStackTrace();
		//			}

		//	         for(int i=0;i<200000000;i++) {
		//	        	 System.out.println("kkk");
		//	        	 int x =MouseInfo.getPointerInfo().getLocation().x;
		//	        	 System.out.println(x);
		//	         }
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		if(myPanels.crclSetting.isDrawingCircle  && !myPanels.panel.isPanelSettingFront)
		{
			isDrawingCircle = false;
		}
		else if(!myPanels.panel.isPanelSettingFront)
		{
			isDrawingLine = false;
		}


	}
	public boolean isOnCircle(Point p)
	{
		ArrayList<circle> circles = myPanels.primary.circles;
		for(int i = 0; i<circles.size(); i++)
		{
			circle c = circles.get(i);
			double d = Math.sqrt((c.x-p.x)*(c.x-p.x) + (c.y-p.y)*(c.y-p.y));
			if(d <= c.r) 
			{
				return true;
			}
		}
		return false;
	}

	public circle getCircle(Point p)
	{

		ArrayList<circle> circles = myPanels.primary.circles;
		for(int i = 0; i<circles.size(); i++)
		{
			circle c = circles.get(i);
			double d = Math.sqrt((c.x-p.x)*(c.x-p.x) + (c.y-p.y)*(c.y-p.y));
			if(d <= c.r) 
			{
				return c;
			}
		}
		return null;
	}
	public void drawingLine(Line l)
	{
		isDrawingLine = true;
		while(isDrawingLine)
		{
			int xp = MouseInfo.getPointerInfo().getLocation().x - myPanels.primary.getLocationOnScreen().x;
			int yp = MouseInfo.getPointerInfo().getLocation().y - myPanels.primary.getLocationOnScreen().y;
			l.end.x = xp;
			l.end.y = yp;
			myPanels.primary.repaint();
			//				Thread.sleep(10);
		}
		
		circle c = getCircle(l.start);
		System.out.println(c.x);
		ArrayList <JTextField[]> f = myPanels.crclSetting.circleFields;
		for(int i = 0; i<f.size(); i++)
		{
			JTextField xf = f.get(i)[1];
			JTextField yf= f.get(i)[2];
			JTextField rf = f.get(i)[0];
			if(xf.getText().equals((int)c.x+"") && yf.getText().equals((int)c.y+"") && rf.getText().equals(c.r+""))
			{
				f.get(i)[3].setText((l.end.x-l.start.x)+"");
				f.get(i)[4].setText((l.end.y-l.start.y)+"");
				break;
			}
		}
		
		}

	public void drawingCircle(circle c) throws InterruptedException
	{
		isDrawingCircle = true;
		int x = (int)c.getX();
		int y = (int)c.getY();
		//			circle c = primary.circles.get(circles.size() - 1);

		while(isDrawingCircle)
		{
			int xp = MouseInfo.getPointerInfo().getLocation().x - myPanels.primary.getLocationOnScreen().x;
			int yp = MouseInfo.getPointerInfo().getLocation().y - myPanels.primary.getLocationOnScreen().y;
			double d = Math.sqrt((xp-x)*(xp-x) + (yp-y)*(yp-y));
			c.r = d;
			myPanels.primary.repaint();
			//				Thread.sleep(10);
		}

		//			crclSetting.addCircle(c);

		ArrayList <JTextField[]> f = myPanels.crclSetting.circleFields;
		for(int i = 0; i<f.size(); i++)
		{
			JTextField xf = f.get(i)[1];
			JTextField yf= f.get(i)[2];
			JTextField rf = f.get(i)[0];
			if(xf.getText().equals("") || yf.getText().equals(""))
			{
				xf.setText(x+"");
				yf.setText(y+"");
				rf.setText(c.r+"");
				break;
			}
		}

	}


}
