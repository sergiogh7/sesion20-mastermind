package com.jarroba.mastermind.mastermind;

import java.awt.EventQueue;

import Clases.MasterMindGUI;

public class MasterMindApp 
{
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterMindGUI frame = new MasterMindGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});			
	}
}