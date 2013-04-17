package edu.ccsf.proj.obit;

import javax.swing.SwingUtilities;

public class ObitProjLaunch 
{
	public static void main(String[] args)
	{
		try 
		{
			SwingUtilities.invokeLater(new Runnable() //create thread for Swing
			{
				public void run() //implement run
				{
					new ObitFrame();
				}
			});
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
