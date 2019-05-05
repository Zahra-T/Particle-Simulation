package fileManager;

import java.util.ArrayList;

import simulation.circle;
import simulation.Polygon;

public class Information {
	public int siden = 4;
	public int dotn = 0; //tedad zarat.
	public ArrayList<circle> circlesInfo;  // vijeggihaye zarat.
	public Polygon containerShape; //shekl e mahfaze.
	public long timen; //modat zaman e shabihSazi.
	public String saveAddress; // mahal e zakhireye etelaat
	public String loadAddress; // mahal e load e ettelaat.
	

	public void setSiden(int siden)
	{
		this.siden = siden;
	}
	public void setDotn(int dotn)
	{
		this.dotn = dotn;
	}
	public void setCirclesInfo(ArrayList<circle> circlesInfo)
	{
		this.circlesInfo = circlesInfo;
	}
	public void setContainerShape(Polygon containerShape)
	{
		this.containerShape = containerShape;
	}
	public void setTimen(long timen)
	{
		this.timen = timen;
	}
	public void setSaveAddress(String saveAddress)
	{
		this.saveAddress = saveAddress;
	}
	public void setLoadAddress(String loadAddress)
	{
		this.loadAddress = loadAddress;
	}

	public int getSiden()
	{
		return this.siden;
	}

	public int getDotn()
	{
		return this.dotn;
	}
	public ArrayList<circle> getCirclesInfo()
	{
		return this.circlesInfo;
	}
	public Polygon getContainerShape()
	{
		return this.containerShape;
	}
	public long getTimen()
	{
		return this.timen;
	}
	public String getSaveAddress()
	{
		return this.saveAddress;
	}
	public String getLoadAddress()
	{
		return this.loadAddress;
	}
}
