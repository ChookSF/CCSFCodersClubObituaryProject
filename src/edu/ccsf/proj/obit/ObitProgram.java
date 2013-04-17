package edu.ccsf.proj.obit;

import javax.swing.SwingUtilities;

//Use this to load the gui
public class ObitProgram 
{
	public static void main(String[] args)
	{
		try
		{	//read up on invokeLater!
			SwingUtilities.invokeLater(new Runnable() //create thread for Swing
			{
				public void run() //implement run
				{
					new ObitProjFrame();
				}
			});
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
