package rendering;

import javax.imageio.ImageIO;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class Render extends Canvas implements Runnable {  
	
	private BufferStrategy strategy;
	private ArrayList<Shape> listShapes= new ArrayList<Shape>();
	public JFrame fr;
	private Shape smain;
	private boolean disp;
	private int mode;
	private Random rand;
	//input booleans
	private boolean[] inp; //uldr wasd qe
	
	//tetris galaxy fields
	public int[][] board = new int[49][49];
	public int[] activeSet = new int[9];
	public int score = 0;
	public int next = 0;
	private int lines = 0;
	
	private double dispRot = 0;
	private double dispRotx = 0;
	private double nextRot = 0;
	private double nextRotx = 0;
	public Render() {  
		this.setFont(new Font("Arial", Font.PLAIN, (int)(20))); 
		mode = 1;
		disp = false;
		rand = new Random();
		inp = new boolean[16];
		JFrame frame =new JFrame();
		this.setSize(500,500);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);//making the frame visible  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIgnoreRepaint(true);
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		fr = frame;
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				 if (e.getKeyCode() == KeyEvent.VK_F1) {
					mode = 1;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_F2) {
					mode = 2;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_1) {
					smain = generateSquare();
				 }
				 if (e.getKeyCode() == KeyEvent.VK_2) {
					smain = generatePyramid();
				 }
				 if (e.getKeyCode() == KeyEvent.VK_3) {
					smain = generateArwing();
				 }
				 if (e.getKeyCode() == KeyEvent.VK_4) {
					smain = generateSpace();
				 }
				 if (e.getKeyCode() == KeyEvent.VK_W) {
					 inp[4] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_A) {
					 inp[5] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_S) {
					 inp[6] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_D) {
					 inp[7] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_E) {
					 inp[9] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_Q) {
					 inp[8] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_I) {
					 inp[10] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_J) {
					 inp[11] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_K) {
					 inp[12] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_L) {
					 inp[13] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_U) {
					 inp[14] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_O) {
					 inp[15] = true;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					 System.exit(0);
				 }
				 if (e.getKeyCode() == KeyEvent.VK_F11) {
					 if (disp)
					 {
						 disp = false;
					 }
					 else
					 {
						 disp = true;
					 }
				 }
				 if (e.getKeyCode() == KeyEvent.VK_OPEN_BRACKET) {
					 smain.scale(1.1);
				 }
				 if (e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET) {
					 smain.scale(0.9);
				 }
				 if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
					 if (listShapes.indexOf(smain) + 1 == listShapes.size())
					 {
						 smain = listShapes.get(0);
					 }
					 else if  (listShapes.indexOf(smain) != -1)
					 {
						 smain = listShapes.get(listShapes.indexOf(smain) + 1);
					 }
				 }
				 if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					 	if (!listShapes.isEmpty())
					 		listShapes.remove(listShapes.indexOf(smain));
						if (!listShapes.isEmpty())
							smain = listShapes.get(0);
				 }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				 if (e.getKeyCode() == KeyEvent.VK_W) {
					 inp[4] = false;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_A) {
					 inp[5] = false;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_S) {
					 inp[6] = false;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_D) {
					 inp[7] = false;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_E) {
					 inp[9] = false;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_Q) {
					 inp[8] = false;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_I) {
					 inp[10] = false;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_J) {
					 inp[11] = false;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_K) {
					 inp[12] = false;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_L) {
					 inp[13] = false;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_U) {
					 inp[14] = false;
				 }
				 if (e.getKeyCode() == KeyEvent.VK_O) {
					 inp[15] = false;
				 }
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}  
	
	public void passIn(int[][] in, int[] active, int[] score, Bag bag) {
		board = in;
		activeSet = active;
		this.score = score[0];
		lines = score[1];
		this.next = bag.peak();
	}
	
	public void loop()
	{
		while (true)
		{
			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			//generateSpace().rotateZ(rand.nextInt(360));
			input();
			
			
			draw(g);
			g.dispose();
			strategy.show();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void input()
	{
		if (inp[0])
			smain.translate(0, 1);
		if (inp[3])
			smain.translate(1, 0);
		if (inp[1])
			smain.translate(-1, 0);
		if (inp[2])
			smain.translate(0, -1);
		if (inp[4])
			dispRotx += 0.05;
		if (inp[5])
			dispRot -= 0.05;
		if (inp[6])
			dispRotx -= 0.05;
		if (inp[7])
			dispRot += 0.05;
		if (inp[8])
			smain.rotateZ(-0.05);
		if (inp[9])
			smain.rotateZ(0.05); //rando thought... change origin to do context sensitive shit????
		if (inp[10]) //i
			smain.rotXT(0.1);
		if (inp[11]) //j
			smain.rotYT(-0.1);
		if (inp[12]) //k
			smain.rotXT(-0.1);
		if (inp[13]) //l
			smain.rotYT(0.1);
		if (inp[14])
			smain.rotZT(-0.1);
		if (inp[15])
			smain.rotZT(0.1);
	}
	public void end()
	{
		
	}
	public void draw(Graphics2D g)
	{
		listShapes.clear();
		for (int i = 0; i < 49; i++)
			for (int j = 0; j < 49; j++) {
				if (board[i][j] != 0) {
					//g.drawImage(getTetro(board[i][j]), 100 + i * 10, 100 + j * 10, this);
					smain = generateSquare();
					smain.scale(0.1);
					smain.translate(-(-i * 10 + 250), -(j * 10 -250));
					smain.setColor(board[i][j]);
					//System.out.println(i + "  " + j);
				}
			}
		for (int i = 0; i < 8; i += 2)
		{
			//g.drawImage(getTetro(activeSet[8]), 100 + activeSet[i] * 10, 100 + activeSet[i + 1] * 10, this);
			smain = generateSquare();
			smain.scale(0.1);
			smain.translate(-(-activeSet[i] * 10 + 250), -(activeSet[i + 1] * 10 - 250));
			smain.setColor(activeSet[8]);
			//System.out.println(-activeSet[i] + "  " + -activeSet[i + 1]);
		}
		//dispRot += 0.005;
		Shape ring = generateRing();
		ring.setColor(9);
		//ring.rotateZ(nextRot);
		
		
		Shape arwing = generateArwing();
		arwing.scale(0.5);
		arwing.translate(-300 + nextRot * 100, -150);
		arwing.setColor(3);
		
		arwing.rotateZ(nextRot * 5);
		arwing.rotateY(-90);
		
		for (Shape i : listShapes)
		{
			i.rotateYO(dispRot);
			i.rotateXO(dispRotx);
		}
		
		
		
		nextRot += 0.005;
		nextRotx += 0.005;
		if (nextRot == 90)
			nextRot = 0;
		Mesh nxt = new Mesh(generateSquare());
		switch (next)
			{
			case 0:
				nxt.addShape(generateSquare(), -20, 0);
				nxt.addShape(generateSquare(), 20, 0);
				nxt.addShape(generateSquare(), 40, 0);
				break;
			case 1:
				nxt.addShape(generateSquare(), 20, 0);
				nxt.addShape(generateSquare(), 40, 0);
				nxt.addShape(generateSquare(), 0, 20);
				break;
			case 2:
				nxt.addShape(generateSquare(), -20, 0);
				nxt.addShape(generateSquare(), -40, 0);
				nxt.addShape(generateSquare(), 0, 20);
				break;
			case 3:
				nxt.addShape(generateSquare(), -20, 0);
				nxt.addShape(generateSquare(), -20, -20);
				nxt.addShape(generateSquare(), 0, -20);
				break;
			case 4:
				nxt.addShape(generateSquare(), -20, 0);
				nxt.addShape(generateSquare(), 20, 20);
				nxt.addShape(generateSquare(), 0, 20);
				break;
			case 5:
				nxt.addShape(generateSquare(), -20, 0);
				nxt.addShape(generateSquare(), 20, 0);
				nxt.addShape(generateSquare(), 0, -20);
				break;
			case 6:
				nxt.addShape(generateSquare(), 20, 0);
				nxt.addShape(generateSquare(), -20, 20);
				nxt.addShape(generateSquare(), 0, 20);
				break;
			}
		nxt.scale(0.2);
		nxt.setColor(next + 2);
		nxt.translate(-180, 200);
		nxt.rotateY(nextRot);
		nxt.rotateX(nextRotx);
	
		//experimental size downerr
		//arwing.scale(-nextRot);
		
		for (int i = 0; i < listShapes.size(); i++)
		{
			int modifX = getWidth() / 2;
			int modifY = getHeight() / 2;
			Shape s = listShapes.get(i);
			g.setColor(Color.RED);
			//smain.applyRot(); experimental 
			for (int p = 0; p < s.getVectors().size(); p++)
			{
				//experimental vector rendering
				//g.drawLine(modifX + s.getTX(), modifY + s.getTY(), modifX + (int)(s.getVectors().get(p).getX()), modifY - (int)(s.getVectors().get(p).getY()));
				//vector rendering
				if (disp)
				g.drawLine((int)(250 + s.getTX()), (int)(250 - s.getTY()), 250 + (int)(s.getVectors().get(p).getX()), 250 - (int)(s.getVectors().get(p).getY()));
			}
			for (int k = 0; k < s.getMatrices().size(); k++)
			{
				ThreeXThree a = s.getMatrices().get(k);
				//draw Lines between matrices
				if (s == smain)
				{
					g.setColor(Color.GREEN);
				}
				//System.out.print(s.color);
				switch (s.color)
				{
					case 1:
						g.setColor(Color.PINK);
						break;
					case 2:
						g.setColor(Color.CYAN);
						break;
					case 3:
						g.setColor(Color.BLUE);
						break;
					case 4:
						g.setColor(Color.ORANGE);
						break;
					case 5:
						g.setColor(Color.YELLOW);
						break;
					case 6:
						g.setColor(Color.GREEN);
						break;
					case 7:
						g.setColor(Color.MAGENTA);
						break;
					case 8:
						g.setColor(Color.RED);
						break;
					case 9:
						g.setColor(Color.WHITE);
				}	
				double sc = getWidth() / 500.0;
				if (getWidth() > getHeight())
				{
					sc = getHeight() / 500.0;
				}
				
				g.drawString("Lines: " + Integer.toString(lines), (int)(getWidth() - getWidth() / 3), 30 + (int)(40 * sc));
				g.drawString("Level: " + Integer.toString(lines / 10), (int)(getWidth() - getWidth() / 3), 30 + (int)(20 * sc));
				g.drawString("Score: " + Integer.toString(score), (int)(getWidth() - getWidth() / 3), 30);
				//modifX = 0;
				//modifY = 0;
				//g.drawLine(modifX + (int)(a.getX1()), modifY - (int)(a.getY1()), (modifX + (int)(a.getX1())) * sc, (modifY - (int)(a.getY1())) * sc);
				//System.out.println(sc);
				g.drawLine((int)(modifX + (a.getX1()) * sc), (int)(modifY - (a.getY1()) * sc), (int)(modifX + (a.getX2()) * sc), (int)(modifY - (a.getY2()) * sc));

				g.drawLine((int)(modifX + (a.getX1()) * sc), (int)(modifY - (a.getY1()) * sc), (int)(modifX + (a.getX3()) * sc), (int)(modifY - (a.getY3()) * sc));
				/**
				int[] xs = {250 + (int)(a.getX1()), 250 + (int)a.getX2(), 250 + (int)a.getX3()};
				int[] ys = {250 - (int)a.getY1(), 250 - (int)a.getY2(), 250 - (int)a.getY3()};
				g.fillPolygon(xs, ys, 3);
				*/
				//ImageObserver t = new ImageObserver();
			}
			smain.deplyRot();
		}
		
	}
		
	//INDIVIDUAL VECTOR MATRICES
	
	
	
	public Shape generateSquare()
	{
		Shape s = new Shape();
		//old vector arrangements
		Vector3D vect = new Vector3D(50, 50, 50);
		Vector3D vect2 = new Vector3D(50, -50, 50);
		Vector3D vect4 = new Vector3D(-50, 50, 50);
		Vector3D vect5 = new Vector3D(50, 50, -50);
		
		Vector3D vect8 = new Vector3D(-50, 50, -50);
		//Vector3D vect4 = new Vector3D(-50, 50, 50);
		//Vector3D vect5 = new Vector3D(50, 50, -50);
		Vector3D vect7 = new Vector3D(-50, -50, -50);
		
		Vector3D vect3 = new Vector3D(-50, -50, 50);
		//Vector3D vect2 = new Vector3D(50, -50, 50);
		//Vector3D vect7 = new Vector3D(-50, -50, -50);
		//Vector3D vect4 = new Vector3D(-50, 50, 50);
		
		Vector3D vect6 = new Vector3D(50, -50, -50);
		//Vector3D vect2 = new Vector3D(50, -50, 50);
		//Vector3D vect7 = new Vector3D(-50, -50, -50);
		//Vector3D vect5 = new Vector3D(50, 50, -50);
		s.addVector(vect);
		s.addVector(vect2);
		s.addVector(vect3);
		s.addVector(vect4);
		s.addVector(vect5);
		s.addVector(vect6);
		s.addVector(vect7);
		s.addVector(vect8);
		
		s.addMatrix(8, 4, 5);
		s.addMatrix(1, 5, 4);
		s.addMatrix(3, 4, 7);
		s.addMatrix(7, 8, 6);
		s.addMatrix(6, 5, 2);
		s.addMatrix(2, 1, 3);
		listShapes.add(s);
		return s;
	}
	public Shape generatePyramid()
	{
		Shape s = new Shape();
		Vector3D vect5 = new Vector3D(0, 50, 0);
		Vector3D vect = new Vector3D(-50, 0, 50);
		Vector3D vect2 = new Vector3D(50, 0, 50);
		
		//Vector3D vect5 = new Vector3D(0, 50, 0);
		Vector3D vect4 = new Vector3D(-50, 0, -50);
		Vector3D vect3 = new Vector3D(50, 0, -50);
		
		//Vector3D vect2 = new Vector3D(50, 0, 50);
		//Vector3D vect = new Vector3D(-50, 0, 50);
		//Vector3D vect3 = new Vector3D(50, 0, -50);
		
		//Vector3D vect4 = new Vector3D(-50, 0, -50);
		//Vector3D vect = new Vector3D(-50, 0, 50);
		//Vector3D vect3 = new Vector3D(50, 0, -50);
		
		s.addVector(vect);
		s.addVector(vect2);
		s.addVector(vect3);
		s.addVector(vect4);
		s.addVector(vect5);
		s.addMatrix(5, 1, 2);
		s.addMatrix(5, 4, 3);
		s.addMatrix(2, 1, 3);
		s.addMatrix(4, 1, 3);
		listShapes.add(s);
		return s;
	}
	public Shape generateRing()
	{
		Shape s = new Shape();
		s.addVector(new Vector3D(0, 200, 25));
		s.addVector(new Vector3D(200, 100, 25));
		s.addVector(new Vector3D(200, -100, 25));
		s.addVector(new Vector3D(0, -200, 25));
		s.addVector(new Vector3D(-200, -100, 25));
		s.addVector(new Vector3D(-200, 100, 25));
		s.addVector(new Vector3D(0, 200, -25)); //back side #7
		s.addVector(new Vector3D(200, 100, -25));
		s.addVector(new Vector3D(200, -100, -25));
		s.addVector(new Vector3D(0, -200, -25));
		s.addVector(new Vector3D(-200, -100, -25));
		s.addVector(new Vector3D(-200, 100, -25));
		s.addMatrix(1, 2, 6);
		s.addMatrix(3, 2, 4);
		s.addMatrix(5, 4, 6);
		s.addMatrix(7, 1, 8);
		s.addMatrix(8, 2, 9);
		s.addMatrix(9, 3, 10);
		s.addMatrix(10, 4, 11);
		s.addMatrix(11, 5, 12);
		s.addMatrix(12, 6, 7);
		listShapes.add(s);
		return s;
	}
	public Shape generateArwing()
	{
		Shape s = new Shape();
		
		//main ship chunk
		Vector3D vect1 = new Vector3D(0,-13,0);
		Vector3D vect2 = new Vector3D(24,0,0);
		Vector3D vect3 = new Vector3D(-24,0,0);
		Vector3D vect4 = new Vector3D(0,0,90);
		Vector3D vect5 = new Vector3D(0,21,0);
		Vector3D vect6 = new Vector3D(0,12,-30);
		s.addVector(vect1);
		s.addVector(vect2);
		s.addVector(vect3);
		s.addVector(vect4);
		s.addVector(vect5);
		s.addVector(vect6);
		
		s.addMatrix(5, 2, 3);
		s.addMatrix(5, 6, 4);
		s.addMatrix(6, 2, 3);
		s.addMatrix(4, 2, 3);
		//s.addMatrix(1, 4, 6);
		s.addMatrix(1, 2, 3);
		s.addMatrix(2, 3, 2);
		s.addMatrix(1, 4, 4);
		
		//left wing
		s.addVector(new Vector3D(-48, 0, 20));
		s.addVector(new Vector3D(-119, 0, -39));
		s.addVector(new Vector3D(-64, 6, -6));
		s.addVector(new Vector3D(-64, -6, -6));
		s.addMatrix(3, 9, 10);
		s.addMatrix(7, 9, 10);
		s.addMatrix(8, 9, 10);
		s.addMatrix(8, 7, 3);
		s.addMatrix(3, 7, 3);
		
		//right wing
		s.addVector(new Vector3D(48, 0, 20));
		s.addVector(new Vector3D(119, 0, -39));
		s.addVector(new Vector3D(64, 6, -6));
		s.addVector(new Vector3D(64, -6, -6));
		s.addMatrix(2, 13, 14);
		s.addMatrix(11, 13, 14);
		s.addMatrix(12, 13, 14);
		s.addMatrix(12, 11, 2);
		s.addMatrix(2, 11, 2);
		
		//left thingy
		s.addVector(new Vector3D(-24,10,0)); //Vector 15
		s.addVector(new Vector3D(-24,0,-10));
		s.addVector(new Vector3D(-27,18,-50));
		s.addVector(new Vector3D(-26,3,-7));
		s.addVector(new Vector3D(-22,3,-7));
		s.addMatrix(3, 15, 16);
		s.addMatrix(17, 15, 16);
		s.addMatrix(18, 3, 17);
		s.addMatrix(19, 3, 17);
		s.addMatrix(18, 15, 16);
		s.addMatrix(19, 15, 16);
		
		//right thingy
		s.addVector(new Vector3D(24,10,0)); //Vector 20
		s.addVector(new Vector3D(24,0,-10));
		s.addVector(new Vector3D(27,18,-50));
		s.addVector(new Vector3D(26,3,-7));
		s.addVector(new Vector3D(22,3,-7));
		s.addMatrix(2, 20, 21);
		s.addMatrix(22, 20, 21);
		s.addMatrix(23, 2, 22);
		s.addMatrix(24, 2, 22);
		s.addMatrix(23, 20, 21);
		s.addMatrix(24, 20, 21);
		
		//left blaster
		s.addVector(new Vector3D(-24, 0, 7)); //Vector 25
		s.addVector(new Vector3D(-24, -6, 7));
		s.addVector(new Vector3D(-24, -6, 17));
		s.addVector(new Vector3D(-27, -2, 7));
		s.addVector(new Vector3D(-23, -2, 7)); //Vector 29
		s.addMatrix(3, 25, 26);
		s.addMatrix(27, 25, 26);
		s.addMatrix(28, 25, 26);
		s.addMatrix(28, 3, 27);
		s.addMatrix(29, 25, 26);
		s.addMatrix(29, 3, 27);
		
		//right blaster
		s.addVector(new Vector3D(24, 0, 7)); //Vector 30
		s.addVector(new Vector3D(24, -6, 7));
		s.addVector(new Vector3D(24, -6, 17));
		s.addVector(new Vector3D(27, -2, 7));
		s.addVector(new Vector3D(23, -2, 7)); //Vector 34
		s.addMatrix(2, 30, 31);
		s.addMatrix(32, 30, 31);
		s.addMatrix(33, 30, 31);
		s.addMatrix(33, 2, 32);
		s.addMatrix(34, 30, 31);
		s.addMatrix(34, 2, 32);
		
		listShapes.add(s);
		return s;
	}
	public Shape generateSpace()
	{
		Shape s = new Shape();
		s.addVector(new Vector3D(0,0,0));
		s.addVector(new Vector3D(0,10,0));
		
		s.addMatrix(1, 2, 1);
		listShapes.add(s);
		return s;
	}
	//finds the diagonal of the parlalthingy for three 3D vectors
	public Vector3D diagonal3D(ThreeXThree mat)
	{
		double x = mat.getX1() + mat.getX2() + mat.getX3();
		double y = mat.getY1() + mat.getY2() + mat.getY3();
		double z = mat.getZ1() + mat.getZ2() + mat.getZ3();
		return new Vector3D(x, y, z);
	}

	@Override
	public void run() {
		loop();
		// TODO Auto-generated method stub
		
	}
}