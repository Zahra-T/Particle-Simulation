package panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileManager.Information;
import simulation.Point;
import simulation.Polygon;
import simulation.circle;


public class saveInfo extends JPanel{
	JTextField address;
	Information information;
	public saveInfo(Information information)
	{
		this.information = information;
		primarySetting();
		buttons();
		addressBox();
	}

	public void primarySetting()
	{
		this.setBackground(Color.pink);
		this.setBounds(475,395 , 170, 65);
		this.setVisible(true);
		this.setLayout(null);
	}

	public void buttons()
	{
		addressBox();
		saveButton();
		browseButton();
	}

	public void saveButton()
	{
		JButton done = new JButton("Done");
		done.setBounds(5, 38, 78, 20);
		done.addActionListener((e)->{
			try {
				information.setSaveAddress(address.getText());
				save(information.getSaveAddress());
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
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
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

	public void addressBox()
	{
		JTextField address = new JTextField("Save address");
		//			address.requestFocus();
		//			address.selectAll();

		address.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				if(address.getText().equals("Save address")) {
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
	
	public void save(String address) throws FileNotFoundException
	{

		File p = new File(address + "\\" + "polygonPoints.txt");
		File c = new File(address + "\\" + "circlesInfo.txt");
		PrintWriter PolyWriter = new PrintWriter(p);
		PrintWriter CirInfo = new PrintWriter(c);
		ArrayList<Point>vertices = information.getContainerShape().vertices;
		for(int i = 0; i<vertices.size(); i++)
		{
			PolyWriter.println(vertices.get(i).x + " " + vertices.get(i).y);
		}
		for(int i = 0; i<information.circlesInfo.size(); i++)
		{
			circle cir = information.circlesInfo.get(i);
			CirInfo.println(cir.r + " " + cir.x + " "+ cir.y + " " + cir.vx + " " + cir.vy + " "+cir.m + " " + cir.color);
		}
//		polygonPointsFile = p;
//		circlesInfoFile = c;

		File I = new File(address + "\\" + "ProgInfo.init");
		PrintWriter ProgInfo = new PrintWriter(I);
		ProgInfo.println(information.getSiden());
		ProgInfo.println(information.getDotn());
		ProgInfo.println(information.getTimen());
//		ProgInfo.println(information.getSaveAddress());
		
		Polygon container = information.getContainerShape();
		for(int i = 0; i<container.vertices.size(); i++)
		{
			ProgInfo.println(container.vertices.get(i).x + " " + container.vertices.get(i).y);
		}
		for(int i = 0; i<information.getCirclesInfo().size(); i++)
		{
			circle cir = information.getCirclesInfo().get(i);
			ProgInfo.println(cir.r + " " + cir.x + " "+ cir.y + " " + cir.vx + " " + cir.vy + " " +cir.m +" "+ cir.color);
		}
		ProgInfo.close();

	}
}
