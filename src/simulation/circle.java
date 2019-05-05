package simulation;

import java.awt.Color;
import java.util.Random;

public class circle {
	public int color;
	public double r;
	public double x;
	public double y;
	public double vx;
	public double vy;
	public double m;
	public double v;
	public boolean isChoosed = false;
	public Color clr;
	public boolean hit = false;
	public circle()
	{
		super();
	}

	public circle(double r, double x, double y, double vx, double vy, double m, int color)
	{
		this.r = r;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.m = m;
		this.color = color;
		this.v = Math.sqrt(vx*vx + vy*vy);

	}
	//		public void setR(double r){
	//			this.r = r;		
	//		}
	//		public void setX


	public Color getColor()
	{
		if (color == 1) return Color.blue;
		else if(color == 2) return Color.green;
		else if(color == 3) return Color.yellow;
		else if(color == 4) return Color.orange;
		else if(color == 5) return Color.pink;
		else if(color == 6) return Color.red;
		else if(!isChoosed)
		{
			Random rnd = new Random();
			float r = rnd.nextFloat();
			float g = rnd.nextFloat();
			float b = rnd.nextFloat();
			while(r == 255 || g == 255 || b == 255)
			{
				r = 200;
				g = 200;
				b = 200;
			
			}
			Color rndColor = new Color(r, g, b);
			this.clr = rndColor;
			isChoosed = true;
			return rndColor;

		}
		else
		{
			return this.clr;
		}

	}

	public void setX(double x)
	{
		this.x = x;
	}
	public void setY(double y)
	{
		this.y = y;
	}

	public void setR(double r)
	{
		this.r = r;
	}
	public double getX()
	{
		return this.x;
	}
	public double getY()
	{
		return this.y;
	}
	public double getR()
	{
		return this.r;
	}

	public double getSinx()
	{
		return vy/v;
	}

	public double getCosx()
	{
		return vx/v;
	}

	public double getTanx()
	{
		return vy/vx;
	}

}
