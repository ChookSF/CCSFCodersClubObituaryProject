package edu.ccsf.proj.obit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import edu.ccsf.proj.obit.tabs.WordListTab;

public class ObitFrame extends JFrame
{
	public ObitFrame() 
	{
		setTitle("Obit Frame");
		//note: size of the screen should be based off user's 
		//      monitor/screen size
		setSize(350, 350);
		
		//tabs
		JTabbedPane tp = new JTabbedPane();
		tp.addTab("Word List", new WordListTab());
		
		getContentPane().add(tp);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
