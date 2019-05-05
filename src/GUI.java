import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import panels.pnlSetting;
import panels.crclSetting;
import panels.collisionTime;
import panels.panel;
import panels.primary;
import panels.saveInfo;
import panels.loadInfo;
import simulation.circle;
import simulation.lineEquation;
import simulation.Action;
import simulation.Calculate;
import simulation.Point;
import simulation.Polygon;
import fileManager.Information;
import fileManager.myPanels;

public class GUI {
	static myPanels myPanels;
	static File polygonPointsFile;
	static File circlesInfoFile;
	//	static ArrayList<Point>Polygon;
	static panels.pnlSetting pnlSetting;
	static crclSetting crclSetting;
	static collisionTime collisionTime;
	static panel panel;

	static Information information;
	static primary primary;
	public static void main(String[] args) {
		myPanels = new myPanels();
		information = new Information();
		//		
		//		information.setLoadAddress("C:\\Users\\Markazi.co\\eclipse-workspaceTwo\\Zarei\\src\\Info");
		//		load("C:\\Users\\Markazi.co\\eclipse-workspaceTwo\\Zarei\\src\\Info");

		Random rnd = new Random();
		long l = System.currentTimeMillis();
		//		panel = new panel();
		//		panel.setBounds(3,3,466,458);    
		//		panel.setBackground(Color.white);
		//		panel.addMouseListener(new myMouseListener());
		//		JPanel
		pnlSetting = new pnlSetting(myPanels, information);
		myPanels.pnlSetting = pnlSetting;
		//		JTextField vnum = new JTextField("3");
		//		vnum.setBounds(50, 100, 50, 20);
		//		pnlSetting.add(vnum);
		crclSetting = new crclSetting(myPanels, information);
		myPanels.crclSetting = crclSetting;
		collisionTime = new collisionTime(information);
		primary = new primary(myPanels);
		myPanels.primary = primary;
		saveInfo saveInfo = new saveInfo(information);
		myPanels.saveInfo = saveInfo;
		loadInfo loadInfo = new loadInfo(information);
		myPanels.loadInfo = loadInfo;
		//		JPanel back = new JPanel();
		//		back.setBackground(Color.black);

		JFrame frame = new JFrame();
		frame.setSize(750,500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		JLayeredPane layered = new JLayeredPane();
		frame.setContentPane(layered);
		frame.setBackground(Color.black);
		frame.setLayout(null);//new BorderLayout());




		//		layered.add(panel); //, BorderLayout.CENTER);
		layered.add(pnlSetting);//, BorderLayout.WEST);
		layered.add(crclSetting);//, BorderLayout.EAST);
		layered.add(saveInfo);
		layered.add(loadInfo);
		layered.add(collisionTime);
		layered.add(primary);
		//        frame.setLayout(new BorderLayout());

		JButton pnl = new JButton("Panel setting");
		pnl.addActionListener((e) -> {
			layered.moveToFront(pnlSetting);
			panel.isPanelSettingFront = true;
			pnlSetting.setVisible(true);
			crclSetting.setVisible(false);
			
		});
		pnl.setBounds(475, 5, 130, 30);
		frame.add(pnl);

		JButton crcl = new JButton("Add circle");
		crcl.addActionListener((e) -> {
			layered.moveToFront(crclSetting);
			panel.isPanelSettingFront = false;
			crclSetting.setVisible(true);
			pnlSetting.setVisible(false);
		});
		crcl.setBounds(610, 5, 130, 30);
		frame.add(crcl);

		JButton save = new JButton("save info");
		save.addActionListener((e)->{
			layered.moveToFront(saveInfo);
			saveInfo.setVisible(true);
			loadInfo.setVisible(false);
		});
		save.setBounds(650, 395, 90, 20);
		frame.add(save);

		JButton load = new JButton("load info");
		load.addActionListener((e)->{
			layered.moveToFront(loadInfo);
			loadInfo.setVisible(true);
			saveInfo.setVisible(false);
		});
		load.setBounds(650, 418, 90, 20);
		frame.add(load);

		panel = new panel(pnlSetting);
		myPanels.panel = panel;

		layered.add(panel);


		//here
//		Calculate clu = new Calculate(information, myPanels);
//		clu.change();



		//here

		JButton run = new JButton("Run");
		run.setBounds(650,440,90,20);
		run.setVisible(true);
		layered.add(run);

		run.addActionListener((e)->{


			Thread t = new Thread(new Runnable(){

				@Override
				public void run() {
					//here
										try {
											GUI.primary.setVisible(false);
											GUI.panel.setVisible(true);
											GUI.panel.setPolygon(GUI.information.getContainerShape());
											GUI.panel.setCircles(GUI.information.getCirclesInfo());
											GUI.panel.start(GUI.information.getTimen());
										} catch (Exception e1) {//what?
											e1.printStackTrace();
										} 

					//here
//					Calculate clu = new Calculate(information, myPanels);
//					clu.change();
//					GUI.doActions(clu.Actions);
				}

			});
			t.start(); 
		});

		
	
		//		JButton run = new JButton("Run");
		//		run.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e)
		//			{
		//				try {
		//					panel.start(timen);
		//				} catch (InterruptedException e1) {
		//					// TODO Auto-generated catch block
		//					e1.printStackTrace();
		//				}
		//			}
		//		});
		//		Thread.currentThread().wait(20 * 3600 * 1000);
		//		run.setBounds(650, 441, 90, 20);
		//		frame.add(run);S

		//		panel.addMouseListener(new MouseAdapter()
		//				{
		//			@Override
		//			public void mouseClicked(MouseEvent e)
		//			{
		//				writer.println(e.getX()+" "+e.getY());
		//			}
		//				}
		//				);
		frame.toFront();
		//		try {
		//			panel.start(timen);
		//		} catch (Exception e1) {//what?
		//			e1.printStackTrace();
		//		}  
		//		frame.setVisible(true);
		//		panel.start(timen);
		//event

	}

	public static void doActions(ArrayList<Action> actions)
	{
		for(int i = 0; i<actions.size(); i++)
		{
			Action a = actions.get(i);
			int n = a.n;
			myPanels.panel.setCircles(a.circles);
			for(int j = 0; j<n; j++)
			{
				moveCircles(a);
			}
			myPanels.panel.repaint();
		}
	}

	public static void moveCircles(Action a)
	{
		for(int i = 0; i<a.circles.size(); i++)
		{
			circle c = a.circles.get(i);
			c.x += c.vx;
			c.y += c.vy;
		}
	}






	//	public static Point getPoint(String s)
	//	{
	//
	//		s = removeSpace(s);
	//		int x = Integer.parseInt(s.substring(0, s.indexOf("-")));
	//		int y = Integer.parseInt(s.substring(s.indexOf("-")+1));
	//		Point p =new Point(x, y);
	//		return p;
	//	}
	//
	//	public static String removeSpace(String str)
	//	{
	//		for(int i = 0; i<str.length(); i++)
	//		{
	//			if(str.charAt(i) == ' ')
	//			{
	//				str = str.substring(0, i) + str.substring(i+1);
	//			}
	//		}
	//		return str;
	//	}

	//	public static void load(String address) throws FileNotFoundException
	//	{
	//		Scanner sc = new Scanner(new File(address));
	//		System.out.println(address);
	//		information.setSiden(sc.nextInt());
	//		information.setDotn(sc.nextInt());
	//		information.setTimen(sc.nextLong());
	//		//		Address = sc.next();
	//		loadPolygon(sc);
	//		loadCircles(sc);
	//
	//	}
	//	
	//	public static void loadPolygon(Scanner sc)
	//	{
	//		ArrayList<Point> points = new ArrayList();
	//		for(int i = 0; i<information.getSiden(); i++)
	//		{
	//			double x = sc.nextDouble(), y = sc.nextDouble();
	//			points.add(new Point((int)x, (int)y));
	//		}
	//	
	//		if(points.size() != 0)
	//		{
	//			information.setContainerShape(new Polygon(points));
	//		}
	//	}

	//	public static void loadCircles(Scanner sc)
	//	{
	//		ArrayList<circle> circles = new ArrayList();
	//		for(int i = 0; i<information.getDotn(); i++)
	//		{
	//			double r, x, y, vx, vy, m;
	//			int color;
	//
	//			r = sc.nextDouble();
	//			x = sc.nextDouble();
	//			y = sc.nextDouble();
	//			vx = sc.nextDouble();
	//			vy = sc.nextDouble();
	//			m = sc.nextDouble();
	//			color = sc.nextInt();
	//
	//			circle cir = new circle(r, x, y, vx, vy, m, color);
	//			circles.add(cir);
	//		}
	//		information.circlesInfo = circles;
	//
	//	}

	//	public static void save(String address) throws FileNotFoundException
	//	{
	//
	//		File p = new File(address + "\\" + "polygonPoints.txt");
	//		File c = new File(address + "\\" + "circlesInfo.txt");
	//		PrintWriter PolyWriter = new PrintWriter(p);
	//		PrintWriter CirInfo = new PrintWriter(c);
	//		ArrayList<Point>vertices = information.getContainerShape().vertices;
	//		for(int i = 0; i<vertices.size(); i++)
	//		{
	//			PolyWriter.println(vertices.get(i).x + " " + vertices.get(i).y);
	//		}
	//		for(int i = 0; i<information.circlesInfo.size(); i++)
	//		{
	//			circle cir = information.circlesInfo.get(i);
	//			CirInfo.println(cir.r + " " + cir.x + " "+ cir.y + " " + cir.vx + " " + cir.vy + " "+cir.m + " " + cir.color);
	//		}
	//		polygonPointsFile = p;
	//		circlesInfoFile = c;
	//
	//		File I = new File(address + "\\" + "ProgInfo.init");
	//		PrintWriter ProgInfo = new PrintWriter(I);
	//		ProgInfo.println(information.getSiden());
	//		ProgInfo.println(information.getDotn());
	//		ProgInfo.println(information.getTimen());
	////		ProgInfo.println(information.getSaveAddress());
	//		
	//		Polygon container = information.getContainerShape();
	//		for(int i = 0; i<container.vertices.size(); i++)
	//		{
	//			ProgInfo.println(container.vertices.get(i).x + " " + container.vertices.get(i).y);
	//		}
	//		for(int i = 0; i<information.getCirclesInfo().size(); i++)
	//		{
	//			circle cir = information.getCirclesInfo().get(i);
	//			ProgInfo.println(cir.r + " " + cir.x + " "+ cir.y + " " + cir.vx + " " + cir.vy + " " +cir.m +" "+ cir.color);
	//		}
	//		ProgInfo.close();
	//
	//	}

}
