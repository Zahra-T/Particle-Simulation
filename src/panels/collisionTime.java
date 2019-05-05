package panels;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileManager.Information;

public class collisionTime extends JPanel{
	JTextField field;
	Information information;
	public collisionTime(Information information)
	{
		this.information = information;
		primarySetting();
		setTextField();
		setLabel();
		setSaveButton();
	}
	public void primarySetting()
	{
		this.setLayout(null);
		this.setBounds(475, 373, 265, 19);
		this.setBackground(Color.gray); 
	}
	public void setTextField()
	{
		JTextField timeField = new JTextField();
		timeField.setBounds(175, 2 , 67, 15);
		timeField.setHorizontalAlignment(JTextField.CENTER);
		field = timeField;
		this.add(timeField);
	}
	public void setLabel()
	{
		JLabel timeLabel = new JLabel("Duration of simulation:(ms)");
		timeLabel.setForeground(Color.white);
		timeLabel.setBounds(15,1, 200,15);
		this.add(timeLabel);
	}
	public void setSaveButton()
	{
		JButton save = new JButton();
		save.setBounds(245, 2, 18, 15);
		save.addActionListener((e)->
		{
			try {
				long timen = Long.parseLong(field.getText());
				information.setTimen(timen);
			}catch(Exception g) {};
		});
		this.add(save);

	}
}
