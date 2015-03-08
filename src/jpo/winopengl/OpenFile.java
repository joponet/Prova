package jpo.winopengl;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFile {
	private File file;
	public boolean validFile=false;
	public ArrayList <Face> faces = new ArrayList<Face>();
	public ArrayList <Material> materials = new ArrayList<Material>();
	
	public OpenFile () {
		// default - figura per defecte
		double vertex[][];
		int ifaces[][];
	    vertex = new double[][] {
		    	{1.0,-1.0,-1.0},
			    {1.0,-1.0,1.0},
			    {-1.0,-1.0,1.0},
			    {-1.0,-1.0,-1.0},
			    {1.0,0.0,-1.0},
			    {1.0,0.0,1.0},
			    {-1.0,0.0,1.0},
			    {-1.0,0.0,-1.0},
			    {1.0,0.0,0.0},
			    {0.0,0.0,-1.0},
			    {-0.0,0.0,1.0},
			    {-1.0,0.0,-0.0},
			    {-0.0,0.8,0.0}
		    };
		    ifaces = new int[][] {
		    		{1,2,4},
		    		{13,12,11},
		    		{2,9,6},
		    		{3,11,7},
		    		{3,7,12},
		    		{5,1,10},
		    		{9,13,11},
		    		{5,10,9},
		    		{10,8,12},
		    		{12,7,11},
		    		{6,9,11},
		    		{10,13,9},
		    		{13,10,12},
		    		{4,3,12},
		    		{2,3,4},
		    		{8,4,12},
		    		{2,6,11},
		    		{3,2,11},
		    		{1,5,9},
		    		{2,1,9},
		    		{1,4,10},
		    		{4,8,10},
		    };
		    Material material=new Material("Default");
		    materials.add(material);
		    for (int i=0; i<ifaces.length; i++) {
		    	Face face = new Face(material,3,ifaces[i],vertex);
		    	faces.add(face);
		    }
	}
	
	public void open() {
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.obj","obj");
		fc.setFileFilter(filter);
		fc.setCurrentDirectory(new File("D:\\5-personal\\00-documents\\Jordi\\Fitxers\\blender"));
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			validFile=true;
		}
	}
	
	public void read() {
		if (!validFile) return;
		faces.clear();
		materials.clear();
		// fitxer material
		try {
			File filem;
			FileInputStream fileStream=null;
			BufferedReader fileReader;
			String values[];
			String line="";

			String fileName=file.getPath();
			fileName=fileName.replaceAll(".obj", ".mtl");
			filem=new File(fileName);
			System.out.println(filem.getPath());
			fileStream = new FileInputStream(filem);
			fileReader = new BufferedReader(new InputStreamReader(fileStream));
			while (line != null) {
				if (line.startsWith("newmtl")) {
					values=line.split(" ");
					Material material = new Material(values[1]);
					while (!line.startsWith("Kd")) line=fileReader.readLine();
					values=line.split(" ");
					material.setColor(Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]));
					materials.add(material);
					System.out.println(material.name+": "+material.color[0]+" "+material.color[1]+" "+material.color[2]);
				}
				line=fileReader.readLine();
			}
			fileReader.close();
			fileStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// fitxer vertex
		try {
			FileInputStream fileStream=null;
			BufferedReader fileReader;
			ArrayList <String> lines = new ArrayList<String>();
			double vertex[][];
			String values[];
			String line="";
			// vertex
			fileStream = new FileInputStream(file);
			fileReader = new BufferedReader(new InputStreamReader(fileStream));
			while ((line != null) && (!line.startsWith("usemtl"))) {
				if (line.startsWith("v")) {
					lines.add(line);
				}
				line=fileReader.readLine();
			}
			vertex = new double[lines.size()][3];
			for (int i=0; i<lines.size(); i++) {
				values=lines.get(i).split(" ");
				for (int j=0; j<3; j++) vertex[i][j]=Double.parseDouble(values[j+1]);
			}
			System.out.println("Vertex: "+lines.size());
			lines.clear();
			// fitxer cares
			Material material=null;
			int tipus;
			while (line != null) {
				// material utilitzat
				if (line.startsWith("usemtl")) {
					values=line.split(" ");
					System.out.println(values[1]);
					for (int i=0; i<materials.size(); i++) {
						if (materials.get(i).name.endsWith(values[1])) {
							material=materials.get(i);
							System.out.println("* "+values[1]);
						}
					}
				}
				// cara
				if (line.startsWith("f")) {
					values=line.split(" ");
					tipus=values.length-1;
					if (tipus==3 || tipus==4) {
						int ivertex[]= new int[tipus];
						for (int i=0; i<tipus; i++) ivertex[i]=Integer.parseInt(values[i+1]);
						Face face = new Face(material,tipus,ivertex,vertex);
						faces.add(face);
					}
				}
				line=fileReader.readLine();
			}
			System.out.println("Cares: "+faces.size());
			fileReader.close();
			fileStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
