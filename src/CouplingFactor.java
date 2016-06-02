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

import java.io.*;
import java.nio.file.*;
import java.util.*;


/**
 * 
 * @author teamG
 *
 */

// this one does not work when there is a same class name (just for this
// project)

public class CouplingFactor {
	private Vector<File> java_file;
	private Vector<String> java_medthods;
	private int coupling;
	private int n;

	/**
	 * constructor
	 */
	public CouplingFactor() {
		n = 0;
		coupling = 0;
		java_file = new Vector<File>();
	}

	/**
	 * 
	 * @param file any files
	 * @return true if it is .java
	 */
	public boolean isJavaFile(String file) {
		return file.matches("^.*\\.java$");
	}

	/**
	 * 
	 * @param line line of source code
	 * @return true if it is function --> public (static) return type ( )
	 */
	public boolean isFunction(String line) {
		return line.matches("^.*public (static |)(String|boolean|void|double).*(.*).*$");
	}

	/**
	 * @param classname name of the class
	 * @param line line of source code
	 * @return ture if the line has the class name
	 */
	public boolean isIncludedbyClass(String classname, String line) {
		return line.matches("^.*" + classname + ".*$");
	}

	/**
	 * do all process
	 */
	public void compute() {
		Scanner input = null;
		for (File file : java_file) {
			try {
				System.out.println("Class "+file.getName().replaceFirst("[.][^.]+$", ""));
				java_medthods = new Vector<String>();
				input = new Scanner(file);
				while (input.hasNextLine()) {
					String line = input.nextLine();
					if(line.matches("^.*@Override.*$"))
					{
						//does not count inherited methods
						input.nextLine();
						continue;
					}
					if (isFunction(line)) {
						String[] keywords =line.split(" ");
						for (int k =0; k<keywords.length;k++) {
							String keys = keywords[k].replaceFirst("[\\(][^.]+$", "");
							if(!keys.matches("^.*(public|static|String|boolean|void|double).*$"))
							{
								System.out.println("+ "+keys);
								java_medthods.add(keys);
								break;
							}
						}
					}
				}
				for (File file2 : java_file) {
					if (file.equals(file2)) {
						continue;
					}
					String filename = file.getName().replaceFirst("[.][^.]+$", "");
					input = new Scanner(file2);
					while (input.hasNextLine()) {
						String line = input.nextLine();
						if (isIncludedbyClass(filename, line)) {
							System.out.println(filename + " was called by " + file2.getName()+"(" + filename + " <--- " + file2.getName() + ")");
							++coupling;
							break;
						}
					}
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
		}
		System.out.println("the number of actual couplings (c) :: " + coupling);
		System.out.println("number of classes (n):: " + n);
		double result = coupling / (Math.pow(n, 2) - n);
		System.out.println("Coupling Factor (formula c/[(n^2)-n]) :: " + result);
	}

	/**
	 * Put all java file in java_file vector
	 * @param folder folder of the source code 
	 * @return false if it is not a folder
	 */
	public boolean listFilesForFolder(final String folder) {
		try {
			Files.walk(Paths.get(folder)).forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					if (isJavaFile(filePath.toString())) {
						java_file.add(filePath.toFile());
						++n;
					}
				}
			});
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("it is not a folder");
			return false;
		}
	}

	/**
	 * to run the main program, you need to put the source code folder
	 * @param args unused
	 */
	public static void main(String[] args) {
		CouplingFactor cf = new CouplingFactor();
		Scanner sc = new Scanner(System.in);
		
		boolean isFolder = false;
		while (!isFolder) {
			System.out.print("Put folder name to compute coupling factor (CF) ::");
			String path = sc.next();
			isFolder = cf.listFilesForFolder(path);
		}
		cf.compute();
		
	}
}
