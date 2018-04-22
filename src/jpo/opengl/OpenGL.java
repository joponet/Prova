package jpo.opengl;

import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.*;

import com.jogamp.opengl.util.Animator;
import static javax.media.opengl.GL.*;  // GL constants
import static javax.media.opengl.GL2ES1.GL_LIGHT_MODEL_AMBIENT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT_AND_DIFFUSE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_EMISSION;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHT0;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_POSITION;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SHININESS;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SPECULAR;


public class OpenGL implements GLEventListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {
	private double rotateTempX;
	private double rotateTempY;
//	private double rotateTempZ;
//	private double rotateStatX;
//	private double rotateStatY;
//	private double rotateStatZ;
//	private double rotateDragXX;
//	private double rotateDragXY;
//	private double rotateDragYX;
//	private double rotateDragYY;
//	private double rotateDragZX;
//	private double rotateDragZY;
	
	private float x1=0;
	private float x2=1;
	private float x3=0;
	
	private float y1=1;
	private float y2=0;
	private float y3=0;

	float modelMatrix[]= new float[16];
	
	private double translateTx = 0.0f;
	private double translateTy = 0.0f;
	private double translateSx = 0;
	private double translateSy = 0;
	private int mousedragx;
	private int mousedragy;
	private int mouseKey;
	
	private Animator animator;
	private double escala = 0.6f;
	private double escalaInc = -0.05f;
	
	private boolean resetPos=false;
	GLCanvas canvas;
	
	OpenFile terra;
	
	Info info;
	
    // Status
	int modelDepth[]= new int[1];
	float depthRange[]= new float[2];
	String glMode;
	
	public OpenGL() {
		GLProfile glp = GLProfile.getDefault();
		glMode=glp.getImplName();
        GLCapabilities caps = new GLCapabilities(glp);
        canvas = new GLCanvas(caps);
        
        Frame frame = new Frame("AWT Window Test");
        frame.setSize(500, 500);
        frame.setLocation(500, 200);
        frame.add(canvas);
        frame.setVisible(true);
        
        // by default, an AWT Frame doesn't do anything when you click
        // the close button; this bit of code will terminate the program when
        // the window is asked to close
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        canvas.addGLEventListener(this);
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseWheelListener(this);
        canvas.addKeyListener(this);
        
        terra = new OpenFile();
        
        info = new Info();
        
        canvas.requestFocus();
	}
	
	public void start() {
		animator = new Animator(canvas);
//		animator.start();
	}
	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
	    
	    gl.setSwapInterval(1);

	    gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
	    gl.glEnable(GL.GL_DEPTH_TEST); // enables depth testing
	    gl.glEnable(GL_LIGHT0);	    
	    gl.glEnable(GL_LIGHTING);
		gl.glShadeModel(GL2.GL_SMOOTH); // blends colors nicely, and smoothes out lighting
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	    gl.glClearColor(0.5f, 0.5f, 1, 0);
	    gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	    gl.glPushMatrix();

	    // Status
		gl.glGetIntegerv(GL2.GL_MODELVIEW_STACK_DEPTH, modelDepth, 0);
		gl.glGetFloatv(GL2.GL_DEPTH_RANGE, depthRange, 0);
		
		info.setGL(drawable);
		info.display();
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		update();
	    render(drawable);		
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {

	}
	
	private void update() {
	}
	
	private void render(GLAutoDrawable drawable) {
	    GL2 gl = drawable.getGL().getGL2();

	    gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

	    // escalat
	    gl.glMatrixMode(GL2.GL_PROJECTION);
	    gl.glLoadIdentity();
//		gl.glPushMatrix();
		gl.glOrtho(translateTx-(1/escala),translateTx+1/escala,translateTy-1/escala,translateTy+1/escala, 300, -200);
//		gl.glOrtho(-2, 2, -2, 2, 3, -2);
//	    gl.glScaled(escala, escala, escala);
//		gl.glPopMatrix();
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		if (resetPos) {
			gl.glPopMatrix();
			gl.glPushMatrix();
			resetPos=false;
		}
//		gl.glPushMatrix();
	    // light
	    float ambientLight[] = {0.8f, 0.8f, 0.8f, 1.0f};
	    float lightColor[] = {0.1f, 0.1f, 0.1f, 1.0f};
	    float lightPosition[] = { 20.0f, 30.0f, 20.0f, 0.0f };
	    gl.glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambientLight,0);
	    gl.glLightfv(GL_LIGHT0, GL_DIFFUSE, lightColor,0);
	    gl.glLightfv(GL_LIGHT0, GL_SPECULAR, lightColor,0);
	    gl.glLightfv(GL_LIGHT0, GL_POSITION, lightPosition,0);
	    
	    // material
	    float mat_specular[] = { 0.0f, 0.0f, 1.0f, 1.0f };
	    float mat_shininess[] = { 20.0f };
	    float materialColor[] = {0.8f, 0.2f, 0.0f, 1.0f};
	    float materialEmission[] = {0.2f,0.2f,0.2f, 0.6f};
	    gl.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, materialColor,0);
	    gl.glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, mat_specular,0);
	    gl.glMaterialfv(GL_FRONT_AND_BACK, GL_SHININESS, mat_shininess,0);
	    gl.glMaterialfv(GL_FRONT_AND_BACK, GL_EMISSION, materialEmission,0);

	    // moviment
//	    gl.glRotated(rotateTempX, 1, 0, 0);
//		gl.glRotated(rotateTempY, 0, 1, 0);
//		gl.glRotated(rotateTempZ, 0, 0, 1);
	    gl.glRotatef((float) rotateTempY, y1, y2, y3);
		gl.glRotatef((float) rotateTempX, x1, x2, x3);
//		gl.glRotatef((float) rotateTempZ, 0, 0, 1);
		rotateTempX=0;
		rotateTempY=0;
		
	    // draw
	    gl.glBegin(GL2.GL_TRIANGLES);
	    if (terra.faces.get(0).tipus==4) {
			gl.glEnd();	    
		    gl.glBegin(GL2.GL_QUADS);
	    }
	    for (int nf=0; nf<terra.faces.size(); nf++) {
    		gl.glColor3fv(terra.faces.get(nf).getColor(), 0);
    		gl.glNormal3dv(terra.faces.get(nf).getNormal(), 0);
    		gl.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE, terra.faces.get(nf).color,0);
	    	for (int nfv=0; nfv<terra.faces.get(nf).tipus; nfv++) {
	    		// escriu cara
	    		gl.glVertex3dv(terra.faces.get(nf).getVertex(nfv),0);
	    	}
	    }
		gl.glEnd();
		
		gl.glGetFloatv(GL2.GL_MODELVIEW_MATRIX, modelMatrix, 0);
				
//		gl.glPopMatrix();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		double sens= 3d;
		if (mouseKey == MouseEvent.BUTTON1) {
//			rotateTempX=rotateStatX + ((mousedragx-e.getX()) * rotateDragXX / sens) + ((mousedragy-e.getY()) * rotateDragXY / sens);
//			rotateTempY=rotateStatY + ((mousedragx-e.getX()) * rotateDragYX / sens) + ((mousedragy-e.getY()) * rotateDragYY / sens);
//			rotateTempZ=rotateStatZ + ((mousedragx-e.getX()) * rotateDragZX / sens) + ((mousedragy-e.getY()) * rotateDragZY / sens);
			rotateTempX=rotateTempX + (mousedragx-e.getX()) / sens;
			rotateTempY=rotateTempY + (mousedragy-e.getY()) / sens;
//			rotateTempZ=rotateStatZ + ((mousedragx-e.getX()) * rotateDragZX / sens) + ((mousedragy-e.getY()) * rotateDragZY / sens);
			mousedragx = e.getX();
			mousedragy = e.getY();
		}
		// arrodoneix si prop de 0,90,...
//		double d=90*Math.round(rotateTempX/90);
//		if (Math.abs(rotateTempX-d)<5) rotateTempX=d;
//		d=90*Math.round(rotateTempY/90);
//		if (Math.abs(rotateTempY-d)<5) rotateTempY=d;
		
//		System.out.println("D="+Math.round(rotateTempX)+":"+Math.round(rotateTempY)+":"+Math.round(rotateTempZ));
		if (mouseKey == MouseEvent.BUTTON3) {
			translateTx=translateSx+((mousedragx-e.getX())/200f)/escala;
			translateTy=translateSy-((mousedragy-e.getY())/200f)/escala;
		}		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount()==2) {
//			rotateStatX=90d;
//			rotateTempX=rotateStatX;
//			rotateStatY=90d;
//			rotateTempY=rotateStatY;
//			rotateStatZ=0d;
//			rotateTempZ=rotateStatZ;
//			translateSx=0d;
//			translateTx=translateSx;
//			translateSy=0d;
//			translateTy=translateSy;
			resetPos=true;
			canvas.display();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		canvas.setCursor(new Cursor(Cursor.MOVE_CURSOR));
//		double degrad = Math.PI/180;
		mouseKey = e.getButton();
		mousedragx = e.getX();
		mousedragy = e.getY();
		
		/*  X   0  90 180 270
		 *  Y   0   0   0   0
		 * ------------------
		 * XX   0   0   0   0
		 * XY   1   1   1   1
		 * YX   1   0  -1   0
		 * YY   0   0   0   0
		 * ZX   0  -1   0   1
		 * ZY   0   0   0   0
		 */   
		
//		rotateDragXX = 0; // Math.sin(rotateStatX*degrad);
//		rotateDragXY = 1; //Math.cos(rotateStatX*degrad); // 1 si X=0,90,180
//		rotateDragYX = 1; //Math.cos(rotateStatX*degrad); // 1 si X=0 -1 si X=180
//		rotateDragYY = 0; // Math.sin(rotateStatX*degrad);
//		rotateDragZX = 0; //-Math.sin(rotateStatX*degrad); // -1 si X=90 
//		rotateDragZY = 0; // Math.sin(rotateStatX*degrad);
		animator.start();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		rotateStatX=rotateTempX;
//		rotateStatY=rotateTempY;
//		rotateStatZ=rotateTempZ;
		translateSx=translateTx;
		translateSy=translateTy;
		
		y1=modelMatrix[0]; if (Math.abs(y1)<0.6) y1=0; else y1=Math.signum(y1);
		y2=modelMatrix[1]; if (Math.abs(y2)<0.6) y2=0; else y2=Math.signum(y2);
		y3=modelMatrix[2]; if (Math.abs(y3)<0.6) y3=0; else y3=Math.signum(y3);
		x1=modelMatrix[4]; if (Math.abs(x1)<0.6) x1=0; else x1=Math.signum(x1);
		x2=modelMatrix[5]; if (Math.abs(x2)<0.6) x2=0; else x2=Math.signum(x2);
		x3=modelMatrix[6]; if (Math.abs(x3)<0.6) x3=0; else x3=Math.signum(x3);
		
		if (y1==1) x3=-x3; else
		if (y1==-1) y1=-1; else
			
		if (y2==1 && x1==-1) {y2=-1; x1=1;} else
		if (y2==1 && x3==1)  {y2=0; y3=1; x1=1; x3=0;} else
		if (y2==1 && x3==-1)  {y2=0; y3=-1; x1=1; x3=0;} else
		if (y2==-1 && x1==1) {y2=1; x1=-1;} else
		if (y2==-1 && x1==-1) y2=-1; else
		if (y2==-1 && x3==1)  {y2=0; y3=-1; x1=-1; x3=0;} else
		if (y2==-1 && x3==-1)  {y2=0; y3=1; x1=-1; x3=0;} else
			
		if (y3==1 && x1==1) {y2=1; y3=0; x1=0; x3=1;} else
		if (y3==1 && x1==-1) {y2=-1; y3=0; x1=0; x3=-1;} else
		if (y3==1 && x2==1) y3=-1; else
		if (y3==1 && x2==-1) y3=1; else
		if (y3==-1 && x1==1) {y2=1; y3=0; x1=0; x3=-1;} else
		if (y3==-1 && x1==-1) {y2=-1; y3=0; x1=0; x3=1;} else
		if (y3==-1 && x2==1) y3=1; else
		if (y3==-1 && x2==-1) y3=-1;
			
		
		canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		System.out.println("Matriu:");
		System.out.printf("%1$4.2f %2$4.2f %3$4.2f\n",modelMatrix[0],modelMatrix[1],modelMatrix[2]);
		System.out.printf("%1$4.2f %2$4.2f %3$4.2f\n",modelMatrix[4],modelMatrix[5],modelMatrix[6]);
		System.out.printf("%1$4.2f %2$4.2f %3$4.2f\n",y1,y2,y3);
		System.out.printf("%1$4.2f %2$4.2f %3$4.2f\n",x1,x2,x3);
//		System.out.println("D="+Math.round(rotateTempX)+":"+Math.round(rotateTempY)+":"+Math.round(rotateTempZ));
		animator.stop();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		escala = escala * (1 + escalaInc*e.getWheelRotation());
		System.out.println("Escala: "+escala);
		canvas.display();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getKeyChar()) {
		case 'o':
			terra.open();
			if (terra.validFile) {
				terra.read();
				canvas.display();
			}

			break;
		case 'x':
			System.exit(0);
			break;
		case 's':
			info.display();
			
			System.out.println("Matriu:");
			System.out.printf("%1$4.2f %2$4.2f %3$4.2f %4$4.2f\n",modelMatrix[0],modelMatrix[1],modelMatrix[2],modelMatrix[3]);
			System.out.printf("%1$4.2f %2$4.2f %3$4.2f %4$4.2f\n",modelMatrix[4],modelMatrix[5],modelMatrix[6],modelMatrix[7]);
			System.out.printf("%1$4.2f %2$4.2f %3$4.2f %4$4.2f\n",modelMatrix[8],modelMatrix[9],modelMatrix[10],modelMatrix[11]);
			System.out.printf("%1$4.2f %2$4.2f %3$4.2f %4$4.2f\n",modelMatrix[12],modelMatrix[13],modelMatrix[14],modelMatrix[15]);

			break;
		}
	}
}