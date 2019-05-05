package panels;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileManager.Information;
import fileManager.myPanels;
import simulation.Point;
import simulation.Polygon;
import simulation.lineEquation;



public class pnlSetting extends JPanel{
	int vern = 0;
	ArrayList <JTextField>vertex = new ArrayList();
	JTextField innerPoint;
	JLabel sn;
	Information information;
	myPanels myPanels;
	//		ArrayList<lineEquation> lines = new ArrayList();
	public pnlSetting(myPanels myPanels, Information information)
	{
		this.myPanels = myPanels;
		this.information = information;
		primarySetting();
		buttons();

		for(int i = 1; i<=4; i++)
		{
			JTextField field = new JTextField();
			field.setBounds(105, 70+ i*22 , 60, 20);
			field.setHorizontalAlignment(JTextField.CENTER);
			this.addTextField(field);
		}
	}

	public void primarySetting()
	{
		this.setBackground(Color.cyan);
		this.setBounds(475, 40, 265, 330);
		this.setVisible(true);
		this.setLayout(null);
		JLabel coordinate = new JLabel("Please enter coordinates");
		coordinate.setBounds(55, 35, 150 , 20);
		this.add(coordinate);

		JLabel side = new JLabel("Number of sides:");
		side.setBounds(55,10, 100, 20);
		this.add(side);
		//	

		sn = new JLabel("4"); // chetori nazaram tu in chizi neveshte she?
		sn.setBounds(150, 10, 30, 20);
		sn.setHorizontalAlignment(JTextField.CENTER);
		this.add(sn);

		JLabel innerDotLabel = new JLabel("Inner:");
		innerDotLabel.setBounds(55, 60, 60, 20);
		this.add(innerDotLabel);

		innerPoint = new JTextField();
		innerPoint.setBounds(105, 62, 60, 20);
		innerPoint.setHorizontalAlignment(JTextField.CENTER);
		this.add(innerPoint);
	}

	public void buttons()
	{
		saveButton();

		removeButton();
		
		arrowUpButton();

		arrowDownButton();

	}

	public void saveButton()
	{
		JButton savePS = new JButton("Save");
		savePS.setBounds(10,300, 100,20);
		savePS.addActionListener((e) -> {
			try {
				savePS();
			}catch(Exception g)  // bara in try catch amal bezaram.
			{}
		});

		this.add(savePS);
	}
	
	public void removeButton()
	{
		JButton remove = new JButton("Remove all");
		remove.setBounds(115, 300, 100, 20);
		remove.addActionListener((e) ->{
			try {
				myPanels.primary.polygon = null;
				myPanels.primary.setBackground(Color.white);
				myPanels.primary.repaint();
				clear();
			}catch(Exception g)
			{}
		});
		
		this.add(remove);
	}
	
	public void clear()
	{
		for(int i = 0; i<vertex.size(); i++)
		{
			JTextField f = vertex.get(i);
			f.setText("");
		}
		
		innerPoint.setText("");
		
	}

	public void arrowUpButton()
	{
		JButton arrowUp = new JButton();
		arrowUp.setBounds(180, 11, 20, 8);
		arrowUp.addActionListener((e) -> {  //azla mahdoodiyat daran?
			int num = Integer.parseInt(sn.getText());
			if(num < 9) {  // badan tedaad raasa ro bishtar konm.
				num++;
				sn.setText(num+"");
				JTextField field = new JTextField();
				field.setBounds(105, 70 + num*22 , 60, 20);
				field.setHorizontalAlignment(JTextField.CENTER);
				this.addTextField(field);
			    information.siden++;
				this.repaint();//chera dakhel action listener repaint mikhad vali biroonesh nemikhad?
			}
			
		});
		this.add(arrowUp);
	}

	public void arrowDownButton()
	{
		JButton arrowDown = new JButton();
		arrowDown.setBounds(180, 22, 20, 8);
		arrowDown.addActionListener((e) -> {
			int num = Integer.parseInt(sn.getText());
			if(num > 3)
			{
				num--;
				sn.setText(num+"");
				JTextField f = vertex.get(vertex.size()-1);
				this.remove(f);
				vertex.remove(f);
				information.siden--;
				this.repaint();
			}

		});
		this.add(arrowDown);
	}

	public ArrayList<lineEquation> getLineEquations()
	{
		ArrayList<lineEquation> lines = new ArrayList();
		
		ArrayList<Point> vertices = information.containerShape.vertices;
		for(int i = 1; i<vertices.size(); i++)
		{
			Point p = vertices.get(i-1);
			Point o = vertices.get(i);
			
			
			double a = o.y - p.y;
			double b = p.x - o.x;
			double c = p.x*(p.y-o.y) - p.y*(p.x-o.x);
			
			lines.add(new lineEquation(a, b, c, p, o));
		}
		Point p = vertices.get(0);
		Point o = vertices.get(vertices.size()-1);

		double a = o.y - p.y;
		double b = p.x - o.x;
		double c = p.x*(p.y-o.y) - p.y*(p.x-o.x);

		lines.add(new lineEquation(a, b, c, p, o));
		
		return lines;
	}

	public void addTextField(JTextField field) // ba mokhtasate dakhelesh add kon.
	{
		this.add(field);
		vertex.add(field);
		repaint();
	}

	public ArrayList<Point> getSortedVertexT()
	{
		ArrayList<JTextField> c = this.vertex;
		ArrayList<Point> po = new ArrayList();
		for(int i = 0; i<c.size(); i++)
		{
			JTextField f = c.get(i);
			String s = f.getText();
			po.add(getPoint(s));
		}
		po = sortDots(po);
		return po;
	}
	public Point getInnerPoint()
	{
		String s = this.innerPoint.getText();
		return getPoint(s);
	}

	public void savePS()
	{
		Polygon p = new Polygon(getSortedVertexT());
		information.containerShape = p;
		myPanels.panel.polygon = p;
		myPanels.primary.polygon = p;
		myPanels.primary.repaint();
		//			panel.repaint();
	}

	public ArrayList<Point> sortDots(ArrayList<Point> po)
	{
		Point InnerPoint = this.getInnerPoint();
		ArrayList<Point> a = new ArrayList();
//		ArrayList<Point> up = new ArrayList();
//		ArrayList<Point> down = new ArrayList();
		
		ArrayList<Point> upEast = new ArrayList();
		ArrayList<Point> upWest = new ArrayList();
		ArrayList<Point> downEast = new ArrayList();
		ArrayList<Point> downWest = new ArrayList();

//		for(int i = 0; i<po.size(); i++)
//		{
//			Point p = po.get(i);
//			if(p.y <= InnerPoint.y)
//			{
//				up.add(p);
//			}
//			else
//			{
//				down.add(p);
//			}
//		}
//		up.sort(null);
//		down.sort(null);

//		for(int i = up.size()-1; i>=0; i--)
//		{
//			Point p = up.get(i);
//			a.add(p);
//		}
//		a.addAll(up);
//		a.addAll(down);
		for(int i = 0; i<po.size(); i++)
		{
			Point p = po.get(i);
			if(p.x >= InnerPoint.x && p.y >= InnerPoint.y)
			{
				upEast.add(p);
			}
			else if(p.x >= InnerPoint.x && p.y < InnerPoint.y)
			{
				downEast.add(p);
			}
			else if(p.x < InnerPoint.x && p.y < InnerPoint.y)
			{
				downWest.add(p);
			}
			else if(p.x <InnerPoint.x && p.y >= InnerPoint.y)
			{
				upWest.add(p);
			}
			
		}
//		upEast.sort(null);
//		upWest.sort(null);
//		downEast.sort(null);
//		downWest.sort(null);
		sort(upEast);
		sort(upWest);
		sort(downEast);
		sort(downWest);
		
		a.addAll(upEast);
		a.addAll(upWest);
		a.addAll(downWest);
		a.addAll(downEast);

		return a;
	}
	
	public Point getPoint(String s)
	{

		s = removeSpace(s);
		int x = Integer.parseInt(s.substring(0, s.indexOf("-")));
		int y = Integer.parseInt(s.substring(s.indexOf("-")+1));
		Point p =new Point(x, y);
		return p;
	}
	
	public void sort(ArrayList<Point> p)
	{
		Point inner = myPanels.pnlSetting.getInnerPoint();
		
		for(int i = 1; i<p.size(); i++)
		{
			Point p1 = p.get(i);
			
			double m1 = inner.x != p1.x ? (p1.y-inner.y)/(p1.x - inner.x) : Double.MAX_VALUE;
			for(int j = 0; j<i; j++)
			{
				Point p2 = p.get(j);
				double m2 = inner.x != p2.x ? (p2.y-inner.y)/(p2.x - inner.x) : Double.MAX_VALUE;
				
				if(m2 > m1)
				{
					Point p1p = new Point(p1.x, p1.y);
					Point p2p = new Point(p2.x, p2.y);
					
					p.remove(i);
					p.remove(j);
					
					p.add(j, p1p);
					p.add(i, p2p);
					
				}
				
			}
		}
	}
	
	public String removeSpace(String str)
	{
		for(int i = 0; i<str.length(); i++)
		{
			if(str.charAt(i) == ' ')
			{
				str = str.substring(0, i) + str.substring(i+1);
			}
		}
		return str;
	}
}
