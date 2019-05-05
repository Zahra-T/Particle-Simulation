package panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileManager.Information;
import simulation.Point;
import simulation.Polygon;
import simulation.circle;

public class loadInfo extends JPanel{
	static JTextField address;
	static Information information;
	public loadInfo(Information information)
	{
		this.information = information;
		primarySetting();
		buttons();
	}
	public void primarySetting()
	{
		this.setBackground(Color.red);
		this.setBounds(475,395 , 170, 65);
		this.setVisible(true);
		this.setLayout(null);
	}
	public void buttons()
	{
		addressBox();
		loadButton();
		browseButton();
	}

	public void addressBox()
	{
		JTextField address = new JTextField("Load address");
		//			address.requestFocus();
		//			address.selectAll();

		address.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if(address.getText().equals("Load address")) {
					address.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				// nothing
			}
		});
		address.setBounds(18, 10, 130, 20);
		this.add(address);
		this.address = address;
	}

	public void loadButton()
	{
		JButton done = new JButton("Done");
		done.setBounds(5, 38, 78, 20);
		done.addActionListener((e)->{
			try {
				information.setLoadAddress(address.getText());
				load(information.getLoadAddress());
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});
		this.add(done);
	}

	public void browseButton()
	{
		JButton browse = new JButton("Browse");
		browse.setBounds(85, 38, 78, 20);
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Browse the folder to process");
				//					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				//					chooser.setAcceptAllFileFilterUsed(false);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					address.setText(""+chooser.getSelectedFile());
					System.out.println("getCurrentDirectory(): "+ chooser.getCurrentDirectory());
					System.out.println("getSelectedFile() : "+ chooser.getSelectedFile());
				} else {
					System.out.println("No Selection ");
				}
			}
		});
		this.add(browse);
	}
	
	public static void load(String address) throws FileNotFoundException
	{
		Scanner sc = new Scanner(new File(address));
		System.out.println(address);
		information.setSiden(sc.nextInt());
		information.setDotn(sc.nextInt());
		information.setTimen(sc.nextLong());
		//		Address = sc.next();
		loadPolygon(sc);
		loadCircles(sc);

	}
	
	public static void loadPolygon(Scanner sc)
	{
		ArrayList<Point> points = new ArrayList();
		for(int i = 0; i<information.getSiden(); i++)
		{
			double x = sc.nextDouble(), y = sc.nextDouble();
			points.add(new Point((int)x, (int)y));
		}
	
		if(points.size() != 0)
		{
			information.setContainerShape(new Polygon(points));
		}
	}
	
	public static void loadCircles(Scanner sc)
	{
		ArrayList<circle> circles = new ArrayList();
		for(int i = 0; i<information.getDotn(); i++)
		{
			double r, x, y, vx, vy, m;
			int color;

			r = sc.nextDouble();
			x = sc.nextDouble();
			y = sc.nextDouble();
			vx = sc.nextDouble();
			vy = sc.nextDouble();
			m = sc.nextDouble();
			color = sc.nextInt();

			circle cir = new circle(r, x, y, vx, vy, m, color);
			circles.add(cir);
		}
		information.circlesInfo = circles;

	}


}
