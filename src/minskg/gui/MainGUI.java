/** 
 * @author teamG
 * MINSK
 * SOEN 6611 - G
 * The MIT License (MIT)
 * Copyright (c) 2015 Nuttakit Phichitsakuldes, Ronak Ramanlal Prajapati, Pratyusha Prathikantham
   Syed Ashfaque Uddin Priom, Golnoush Rahimzadeh, Dhanvin Raval, Kumaran Ayyappan Ravi

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
*/

package minskg.gui;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import minskg.Cubic;
import minskg.Prime;
import minskg.Quadratic;

public class MainGUI {
	
	static final int WIDTH = 600, MAIN_HEIGHT = 160, HEIGHT_1 = 240, HEIGHT_2 = 375, HEIGHT_3 = 460;
	JFrame jf;
	JButton jbGo, jbSolve, jbBack;
	JComboBox<String> jcbMode;
	JTextField jtf1, jtf2, jtf3, jtf4;
	JLabel jl1, jl2, jl3, jltf1, jltf2, jltf3, jltf4, jlInfo1;
	double a,b,c,d;
	long ee;
	
	/**
	 * set some components' actions
	 */
	public void setListener() {
		jbSolve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (jcbMode.getSelectedIndex() == 0) {
						String text = jtf1.getText();
						if(text.length()>19){
							UIManager.put("OptionPane.foreground", Color.red);
							UIManager.put("OptionPane.messageForeground", Color.red);
							UIManager.put("OptionPane.border", new EmptyBorder(60, 60, 60, 60) );
							JOptionPane.showMessageDialog(null, "Please put a number less than 19 digits","Error Message",JOptionPane.ERROR_MESSAGE);
						}else{
							ee = Long.parseLong(text);
							jl1.setText(ee+" is "+(Prime.isPrime(ee)?"":"not")+" prime number");
						}
					} else if (jcbMode.getSelectedIndex() == 1) {
						a = Double.parseDouble(jtf1.getText());
						b = Double.parseDouble(jtf2.getText());
						c = Double.parseDouble(jtf3.getText());
						Quadratic quad = new Quadratic();
						quad.setCoefficient(a, b, c);
						jl1.setText("X1 :"+quad.getX1());
						jl2.setText("X2 :"+quad.getX2());
					} else if (jcbMode.getSelectedIndex() == 2) {
						a = Double.parseDouble(jtf1.getText());
						b = Double.parseDouble(jtf2.getText());
						c = Double.parseDouble(jtf3.getText());
						d = Double.parseDouble(jtf4.getText());
						Cubic cubic = new Cubic();
						cubic.setCoefficient(a, b, c, d);
						jl1.setText("X1 :"+cubic.getX1());
						jl2.setText("X2 :"+cubic.getX2());
						jl3.setText("X3 :"+cubic.getX3());
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please put a number into the textbox");
					jl1.setText("ANSWER 1");
				}
			}
		});

		jbBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jf.setSize(WIDTH, MAIN_HEIGHT);
				jlInfo1.setText("Welcome to MINSK. Select a functional then click Go.");
				jlInfo1.setBounds(150, 30, 300, 15);
				setVisible();
				
				
				//jlInfo2.setVisible(false);
			}
		});

		jbGo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				jbGo.setVisible(false);
				jcbMode.setVisible(false);
				jbBack.setVisible(true);
				jbSolve.setVisible(true);
				if (jcbMode.getSelectedIndex() == 0) {
					jf.setSize(WIDTH, HEIGHT_1);
					jlInfo1.setBounds(185, 30, 300, 15);
					jlInfo1.setText("Insert a number to check primality");
					jbSolve.setText("Check Primility");
					jbBack.setBounds(310, 160, 90, 30);
					jbSolve.setBounds(170, 160, 120, 30);
					
					jl1.setBounds(165, 120, 250, 20);
					jl1.setOpaque(true);
					jl1.setText("ANSWER");
					jltf1.setText("Number :");
					jltf1.setBounds(180, 65, 60, 30);
					
					jtf1.setText("");
					jtf1.setBounds(250, 65, 140, 30);
					jl1.setVisible(true);
					jl2.setVisible(false);
					jl3.setVisible(false);
					jtf1.setVisible(true);
					jtf2.setVisible(false);
					jtf3.setVisible(false);
					jtf4.setVisible(false);
					jltf1.setVisible(true);
					jltf2.setVisible(false);
					jltf3.setVisible(false);
					jltf4.setVisible(false);
					
				} else if (jcbMode.getSelectedIndex() == 1) {
					jf.setSize(WIDTH, HEIGHT_2);
					jlInfo1.setText("Insert values for coefficients a, b, and c for Quadratic equation:");
					jlInfo1.setBounds(120, 30, 500, 15);
					jbSolve.setText("Calculate roots");
					jbBack.setBounds(310, 295, 90, 30);
					jbSolve.setBounds(170, 295, 120, 30);

					jtf1.setText("");
					jtf2.setText("");
					jtf3.setText("");
					jltf1.setText("A :");
					jltf2.setText("B :");
					jltf3.setText("C :");
					
					
					jltf1.setBounds(80, 65, 30, 30);
					jltf2.setBounds(80, 115, 30, 30);
					jltf3.setBounds(80, 165, 30, 30);
					jl1.setBounds(125, 215, 340, 20);
					jl2.setBounds(125, 255, 340, 20);
					jl3.setBounds(125, 295, 340, 20);
					
					jtf1.setBounds(115, 65, 380, 30);
					jtf2.setBounds(115, 115, 380, 30);
					jtf3.setBounds(115, 165, 380, 30);
					jl1.setVisible(true);
					jl2.setVisible(true);
					jl3.setVisible(false);
					jtf1.setVisible(true);
					jtf2.setVisible(true);
					jtf3.setVisible(true);
					jtf4.setVisible(false);
					jltf1.setVisible(true);
					jltf2.setVisible(true);
					jltf3.setVisible(true);
					jltf4.setVisible(false);
					jl1.setText("X1:\t\tANSWER 1");
					jl2.setText("X2:\t\tANSWER 2");
					
				} else {
					jf.setSize(WIDTH, HEIGHT_3);
					jbSolve.setText("Calculate roots");
					jlInfo1.setText("Insert values for coefficients a, b, c, and d for Cubic equation");
					
					jlInfo1.setBounds(120, 30, 500, 15);
					jbBack.setBounds(310, 380, 90, 30);
					jbSolve.setBounds(170, 380, 120, 30);

					jtf1.setText("");
					jtf2.setText("");
					jtf3.setText("");
					jtf4.setText("");
					jtf1.setVisible(true);
					jtf2.setVisible(true);
					jtf3.setVisible(true);
					jtf4.setVisible(true);
					jltf1.setVisible(true);
					jltf2.setVisible(true);
					jltf3.setVisible(true);
					jltf4.setVisible(true);
					jl1.setVisible(true);
					jl2.setVisible(true);
					jl3.setVisible(true);
					jltf1.setText("A :");
					jltf2.setText("B :");
					jltf3.setText("C :");
					jltf4.setText("D :");
					jl1.setText("X1 :ANSWER 1");
					jl2.setText("X2 :ANSWER 2");
					jl3.setText("X3 :ANSWER 3");
					jl1.setLocation(125, 265);
					jl2.setLocation(125, 305);
					jl3.setLocation(125, 345);
					
					
					jltf1.setBounds(80, 65, 30, 30);
					jltf2.setBounds(80, 115, 30, 30);
					jltf3.setBounds(80, 165, 30, 30);
					jltf4.setBounds(80, 215, 30, 30);
					jtf1.setBounds(115, 65, 380, 30);
					jtf2.setBounds(115, 115, 380, 30);
					jtf3.setBounds(115, 165, 380, 30);
					jtf4.setBounds(115, 215, 380, 30);
					
					
				}
				jlInfo1.setVisible(true);
			}
		});

		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * set components visibility
	 */
	public void setVisible() {
		jlInfo1.setVisible(true);
		jbBack.setVisible(false);
		jbSolve.setVisible(false);
		jbGo.setVisible(true);
		jcbMode.setVisible(true);
		jf.setVisible(true);
		jl1.setVisible(false);
		jl2.setVisible(false);
		jl3.setVisible(false);

		jltf1.setVisible(false);
		jltf2.setVisible(false);
		jltf3.setVisible(false);
		jltf4.setVisible(false);
		jtf1.setVisible(false);
		jtf2.setVisible(false);
		jtf3.setVisible(false);
		jtf4.setVisible(false);
		
	}
	
	/**
	 * to initial all variable and set them in the frame
	 */
	public void init() {
		jf = new JFrame("MINSK");
		jbGo = new JButton("Go");
		jbBack = new JButton("Back");
		jbSolve = new JButton("Solve");
		jcbMode = new JComboBox<String>(new String[] { "Prime", "Quadratic Equation", "Cubic Equation" });
		jlInfo1 = new JLabel("Welcome to MINSK. Select a functional then click Go.");
		jl1 = new JLabel();
		jl2 = new JLabel();
		jl3 = new JLabel();
		jltf1= new JLabel("A :");
		jltf2= new JLabel("B :");
		jltf3= new JLabel("C :");
		jltf4= new JLabel("D :");
		jtf1 = new JTextField();
		jtf2 = new JTextField();
		jtf3 = new JTextField();
		jtf4 = new JTextField();
		
		jf.setIconImage((new ImageIcon(getClass().getResource("my_logo.gif"))).getImage());
		jf.setLayout(null);
		jf.setResizable(false);

		jf.add(jbGo);
		jf.add(jbBack);
		jf.add(jbSolve);
		jf.add(jcbMode);
		jf.add(jl1);
		jf.add(jl2);
		jf.add(jl3);
		jl1.setOpaque(true);
		jl2.setOpaque(true);
		jl3.setOpaque(true);
		jl1.setBackground(Color.WHITE);
		jl2.setBackground(Color.WHITE);
		jl3.setBackground(Color.WHITE);
		jf.add(jlInfo1);
		jf.add(jltf1);
		jf.add(jltf2);
		jf.add(jltf3);
		jf.add(jltf4);
		jf.add(jtf1);
		jf.add(jtf2);
		jf.add(jtf3);
		jf.add(jtf4);
		jl1.setSize(400, 20);
		jl2.setSize(400, 20);
		jl3.setSize(400, 20);
		jf.setBounds(50,50,WIDTH, MAIN_HEIGHT);
		jcbMode.setBounds(185, 80, 150, 30);
		jbGo.setBounds(340, 80, 55, 30);
		jlInfo1.setBounds(150, 30, 300, 15);
		
		setVisible();
		setListener();
	}
	
	/**
	 * the main function
	 * @param args unused
	 */
	public static void main(String[] args) {
		MainGUI g = new MainGUI();
		g.init();
	}
}
