package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileManager.Information;
import fileManager.myPanels;
import simulation.circle;


public class crclSetting extends JPanel{
	JLabel sn;
	int dotn = 0;
	ArrayList <JTextField[]> circleFields = new ArrayList();
	ArrayList <JButton> rndButtons = new ArrayList();
	Information information;
	myPanels myPanels;
	boolean isDrawingCircle = true;
	public crclSetting(myPanels myPanels,Information information)
	{
		this.information = information;
		this.myPanels = myPanels;
		primarySetting();
		setButtons();
	}



	public void primarySetting()
	{
		this.setBackground(Color.blue);
		this.setBounds(475, 40, 265, 330);
		this.setVisible(false);
		this.setLayout(null);
		Font smallFont = new Font("Serif", Font.BOLD, 15);

		JLabel property = new JLabel("Please enter circles property:");
		property.setFont(smallFont);
		property.setForeground(Color.white);
		property.setBounds(35, 35, 250 , 20);
		this.add(property);

		JLabel num = new JLabel("Number of circles:");
		num.setFont(smallFont);
		num.setForeground(Color.white);
		num.setBounds(35,10, 150, 20);
		this.add(num);

		sn = new JLabel("0"); // chetori nazaram tu in chizi neveshte she?
		sn.setForeground(Color.white);
		sn.setBounds(150, 10, 30, 20);
		sn.setHorizontalAlignment(JTextField.CENTER);
		this.add(sn);
	}

	public void addCircle(circle c)
	{
		int where = emptyField();
		if(where != -1)
		{
			for(int i = 0; i<3; i++)
			{
				double r = c.r;
				double x = c.x;
				double y = c.y;

				circleFields.get(where)[0].setText(r+"");
				circleFields.get(where)[1].setText(x+"");
				circleFields.get(where)[2].setText(y+"");
			}
		}
	}
	public int emptyField()
	{
		for(int i = 0; i<circleFields.size(); i++)
		{
			JTextField [] f = circleFields.get(i);
			if(f[0].getText().equals("")&&f[1].getText().equals("")&&f[2].getText().equals(""))
			{
				return i;
			}

		}
		return -1;
	}

	public void setButtons()
	{
		saveButton();
		removeButton();
		drawCircle();
		drawVector();
		arrowUpButton();
		arrowDownButton();
	}
	////////
	public void saveButton()
	{
		JButton saveCS = new JButton("Save");
		saveCS.setBounds(10,300, 65,20);
		saveCS.addActionListener((e) -> {
			try {
				saveCS();
			}catch(Exception g)  // bara in try catch amal bezaram.
			{}
		});

		this.add(saveCS);
	}
	public void removeButton()
	{
		JButton remove = new JButton("Clear");
		remove.setBounds(80,300, 65,20);
		remove.addActionListener((e)->
		{
			myPanels.primary.circles.clear();
			myPanels.primary.lines.clear();
			myPanels.primary.repaint();
			
			for(int i = 0; i<circleFields.size(); i++)
			{
				for(int j = 0; j<6; j++)
				{
					JTextField f = circleFields.get(i)[j];
					f.setText("");
				}
			}
			this.repaint();
		});
		
		this.add(remove);
	}
	
	public void drawCircle()
	{
		JButton drawCircle = new JButton("c");
		drawCircle.setBounds(220, 275, 41, 20);
		drawCircle.addActionListener((e)->
		{
			this.isDrawingCircle = true;
		});
		this.add(drawCircle);
	}
	public void drawVector()
	{
		JButton drawVector = new JButton("v");
		drawVector.setBounds(220, 300, 41, 20);
		drawVector.addActionListener((e)->
		{
			this.isDrawingCircle = false;
		});
		this.add(drawVector);
	}

	

	
	public void arrowUpButton()
	{

		JButton arrowUp = new JButton();
		arrowUp.setBounds(180, 11, 20, 8);
		arrowUp.addActionListener((e) -> {  //azla mahdoodiyat daran?
			int num = Integer.parseInt(sn.getText());
			if(num < 10) {  // badan tedaad raasa ro bishtar konm.
				num++;
				sn.setText(num+"");
				JTextField [] fields = new JTextField[6];
				for(int i = 0; i<6; i++) {
					JTextField field = new JTextField();
					field.setBounds(20 + i*33 , 50 + num*22, 30, 15);
					field.setHorizontalAlignment(JTextField.CENTER);
					fields[i] = field;
				}

				this.addTextFields(fields);
				setRndButton();
				this.repaint();//chera dakhel action listener repaint mikhad vali biroonesh nemikhad?
				information.dotn++;
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
			if(num > 0)
			{

				num--;
				sn.setText(num+"");
				JTextField[] f = circleFields.get(circleFields.size()-1);
				for(int i = 0; i<f.length; i++)
				{
					JTextField m = f[i];
					this.remove(m);
				}
				String xs = f[1].getText(), ys = f[2].getText(), rs = f[0].getText();

				if(!xs.equals("") && !ys.equals("") && !rs.equals("")) {
					double x = Double.parseDouble(xs);
					double y = Double.parseDouble(ys);
					double r = Double.parseDouble(rs);
//					circle c = new circle();
//					c.setX(x);
//					c.setY(y);
//					c.setR(r);
//					c.clr = Color.white;
//					primary.addCircle(c);
//					primary.addCircle(c);
//					primary.repaint();
					
					circle l = myPanels.primary.circles.get(myPanels.primary.circles.size()-1);
					if(l.x == x && l.y == y && l.r == r)
					{
						myPanels.primary.circles.remove(myPanels.primary.circles.size() - 1);
						myPanels.primary.repaint();
					}
				}
				//					primary.addCircle(c);
				circleFields.remove(f);

				this.remove(rndButtons.get(rndButtons.size()-1));
				rndButtons.remove(rndButtons.get(rndButtons.size()-1));
				information.dotn--;
				this.repaint();
				
			}
		});
		this.add(arrowDown);
	}

	public void setRndButton()
	{
		JButton rnd = new JButton("r");
		int n = circleFields.size();
		rnd.setBounds(20 + 6*33 , 50 + n*22, 39, 15);
		rnd.addActionListener((e)->{
			Random random = new Random();
			for(int i = 0; i<circleFields.size(); i++)
			{
				JTextField f = circleFields.get(i)[0];
				if(f.getY() == rnd.getY())
				{
					circleFields.get(i)[3].setText(random.nextInt(50) + "");
					circleFields.get(i)[4].setText(random.nextInt(50) + "");
				}
			}
					
		});
		rndButtons.add(rnd);
		this.add(rnd);
//		JColorChooser choose = new JColorChooser();
//		int n = circleFields.size();
//
//		choose.setBounds(20 + 6*33 , 50 + n*22, 30, 15);
//		choose.setVisible(true);
//		colorChooser.add(choose);
//		this.add(choose);
	}

	public void addTextFields(JTextField [] fields) // ba mokhtasate dakhelesh add kon.
	{
		for(int i = 0; i<fields.length; i++)
		{
			this.add(fields[i]);
		}
		circleFields.add(fields);
		repaint();
	}

	public void saveCS()
	{
		ArrayList<circle> circles = getCirclesList();
		information.setCirclesInfo(circles);
		myPanels.panel.circles = circles;
		myPanels.panel.repaint();
	}

	public ArrayList<circle> getCirclesList()
	{
		ArrayList <circle> c = new ArrayList();

		for(int i = 0; i<circleFields.size(); i++)
		{
			JTextField [] f = circleFields.get(i);
			double r = Double.parseDouble(f[0].getText());
			double x = Double.parseDouble(f[1].getText());
			double y = Double.parseDouble(f[2].getText());
			double vx = Double.parseDouble(f[3].getText());
			double vy = Double.parseDouble(f[4].getText());
			double m = Double.parseDouble(f[5].getText());



			circle e = new circle(r, x, y, vx, vy, m, -1); // set -1 to get random color.
			c.add(e);
		}

		return c;
	}

	///////////


	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}

	//		public void addTextFields(JTextField [] fields)//chikar konm be jash?
	//		{
	//			for(int i = 0; i<5; i++)
	//			{
	//				int n = circleFields.length;
	//				fields[i].setBounds(5 + i*15 , 30 + n*25, 10, 20);
	//				this.add(fields[i]);
	//			}
	//
	//			JTextField [][] f = new JTextField[++dotn][5];
	//
	//			for(int i = 0; i<dotn-1; i++)
	//			{
	//				for(int j = 0; j<5; j++)
	//				{
	//					f[i][j] = circleFields[i][j];
	//
	//				}
	//			}
	//			for(int i = 0; i<5; i++)
	//			{
	//				f[dotn-1][i] = fields[i];
	//			}
	//			circleFields = f;
	//			
	//			
	//			
	//			repaint();
	//		}

}
