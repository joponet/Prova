package jpo.winopengl;

public class Face {
	Material material;
	int tipus;
	int ivertex[];
	double vertex[][];
	float color[];
	double normal[];
	public Face (Material pmaterial, int ptipus, int[] pivertex, double[][] pvertex) {	
		material=pmaterial;
		tipus=ptipus;
		ivertex=pivertex;
		vertex=pvertex;
		normal = new double[3];
		normal=Calculs.normal(vertex[ivertex[0]-1], vertex[ivertex[1]-1], vertex[ivertex[2]-1]);
		color = new float[4];
		color = material.color;
	}
	public double[] normal() {
		double cNormal[];
		cNormal=Calculs.normal(vertex[ivertex[0]-1], vertex[ivertex[1]-1], vertex[ivertex[2]-1]);
		return  cNormal;
	}
	public double[] getVertex(int i) {
		return vertex[ivertex[i]-1];
	}
	public float[] getColor() {
		return color;
	}
	public double[] getNormal() {
		return normal;
	}
}
