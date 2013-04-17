package edu.ccsf.proj.obit;

import javax.swing.JFrame;

public class ObitFrame extends JFrame
{
	public ObitFrame() 
	{
		setTitle("Obit Frame");
		//note: size of the screen should be based off user's 
		//      monitor/screen size
		setSize(350, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
