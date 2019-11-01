package rendering;
import java.util.ArrayList;

public class Shape {
	private ArrayList<ThreeXThree> list3D;
	private ArrayList<Vector3D> listVect;
	private double transX;
	private double transY;
	private double transZ;
	private double rotZ;
	private double rotX;
	private double rotY;
	public int color;
	public Shape()
	{
		list3D = new ArrayList<ThreeXThree>();
		listVect = new ArrayList<Vector3D>();
		transX = 0;
		transY = 0;
		transZ = 0;
		rotZ = 0;
		rotX = 0;
		rotY = 0;
		color = 0;
	}
	public void setColor(int i)
	{
		color = i;
	}
	public void addVector(Vector3D a)
	{
		listVect.add(a);
	}
	public void addMatrix(int a, int b, int c)
	{
		list3D.add(new ThreeXThree(listVect.get(a - 1), listVect.get(b - 1), listVect.get(c - 1)));
	}
	public ArrayList<ThreeXThree> getMatrices()
	{
		return list3D;
	}
	public ArrayList<Vector3D> getVectors()
	{
		return listVect;
	}
	
	
	
	//to be called outside of the class
	public void translate(double x, double y)
	{
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() + x, i.getY() + y, i.getZ());
		}
		transX += x;
		transY += y;
		//System.out.println(transY);
	}
	public void translate(double x, double y, double z)
	{
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() + x, i.getY() + y, i.getZ() + z);
		}
		transX += x;
		transY += y;
		transZ += z;
		
	}
	public void scale(double d)
	{
		//reset origin to 0
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() - transX, i.getY() - transY, i.getZ());
		}
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() * d, i.getY() * d, i.getZ() *d);
		}
		//restore
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() + transX, i.getY() + transY, i.getZ());
		}
	}

	public void rotateX(double d)
	{
		//reset origin to 0
				for (Vector3D i : getVectors())
				{
					i.update(i.getX() - transX, i.getY() - transY, i.getZ());
				}
		for (Vector3D i : getVectors())
		{
			rotateX(i, d);
		}
		//restore
				for (Vector3D i : getVectors())
				{
					i.update(i.getX() + transX, i.getY() + transY, i.getZ());
				}
	}
	public void rotateY(double d)
	{
		//reset origin to 0
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() - transX, i.getY() - transY, i.getZ());
		}
		//rotate
		for (Vector3D i : getVectors())
		{
			rotateY(i, d);
		}
		//restore
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() + transX, i.getY() + transY, i.getZ());
		}
	}

	public void rotateZ(double d)
	{
		//reset origin to 0
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() - transX, i.getY() - transY, i.getZ());
		}
		for (Vector3D i : getVectors())
		{
			rotateZ(i, d);
		}
		//restore
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() + transX, i.getY() + transY, i.getZ());
		}
	}   
	
	public void rotateXT(double d)
	{
		
		//reset origin to 0
				for (Vector3D i : getVectors())
				{
					i.update(i.getX() - transX, i.getY() - transY, i.getZ());
				}
				rotateXO(-rotX);
				rotX += d;
				if (rotX > 360)
					rotX -= 360;
				rotateZO(-rotZ);
				rotateYO(-rotY);
		for (Vector3D i : getVectors())
		{
			rotateX(i, d);
		}
		rotateYO(rotY);
		rotateZO(rotZ);
		rotateXO(rotX);
		//restore
				for (Vector3D i : getVectors())
				{
					i.update(i.getX() + transX, i.getY() + transY, i.getZ());
				}
	}
	public void rotateYT(double d)
	{
		rotY += d;
		if (rotY > 360)
			rotY -= 360;
		//reset origin to 0
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() - transX, i.getY() - transY, i.getZ());
		}
		rotateXO(-rotX);
		rotateZO(-rotZ);
		//rotate
		for (Vector3D i : getVectors())
		{
			rotateY(i, d);
		}
		rotateZO(rotZ);
		rotateXO(rotX);
		
		//restore
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() + transX, i.getY() + transY, i.getZ());
		}
	}
	public void rotateZT(double d)
	{
    	rotZ += d;
		if (rotZ > 360)
			rotZ -= 360;
		//reset origin to 0
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() - transX, i.getY() - transY, i.getZ());
		}
		rotateXO(-rotX);
		rotateYO(-rotY);
		for (Vector3D i : getVectors())
		{
			rotateZ(i, d);
		}
		rotateYO(rotY);
		rotateXO(rotX);
		
		//restore
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() + transX, i.getY() + transY, i.getZ());
		}
	}  
	public void rotateXO(double d)
	{
		for (Vector3D i : getVectors())
		{
			rotateX(i, d);
		}
	}
	public void rotateYO(double d)
	{
		for (Vector3D i : getVectors())
		{
			rotateY(i, d);
		}
	}
	public void rotateZO(double d)
	{
		for (Vector3D i : getVectors())
		{
			rotateZ(i, d);
		}
	}

	public void applyRot()
	{
		origin();
		rotateXO(rotX);
		rotateYO(rotY);
		rotateZO(rotZ);
		back();
	}
	public void deplyRot()
	{
		origin();
		rotateZO(-rotZ);
		rotateYO(-rotY);
		rotateXO(-rotX);
		back();
	}
	public void origin()
	{
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() - transX, i.getY() - transY, i.getZ());
		}
	}
	public void back()
	{
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() + transX, i.getY() + transY, i.getZ());
		}
	}
	public void transDir(double x, double y)
	{
		double trX = listVect.get(0).getX();
		double trY = listVect.get(0).getY();
		double trZ = listVect.get(0).getZ();
		translateI(-transX, -transY);
		rotateZO(-rotZ);
		rotateXO(-rotX);
		//rotateYO(-rotY);
		//translateI(x, y);
		//rotateYO(rotY);
		//rotateXO(rotX);
		//rotateZO(rotZ);
		//translateI(transX, transY);
		//transX += listVect.get(0).getX() - trX; 
		//transY += listVect.get(0).getY() - trY;
		//transZ += listVect.get(0).getZ() - trZ;;
	}
	
	
	public void rotXT(double d)
	{
		rotX += d;
	}
	public void rotYT(double d)
	{
		rotY += d;
	}
	public void rotZT(double d)
	{
		rotZ += d;
	}
	//for use of the class
	public static void rotateX(Vector3D v, double d)
	{
		double y = (v.getY() * Math.cos(-d) + v.getZ() * -Math.sin(-d));
		double z = (v.getY() * Math.sin(-d) + v.getZ() * Math.cos(-d));
		v.update(v.getX(), y, z);
	}
	public static void rotateY(Vector3D v, double d)
	{
		double x = v.getX() * Math.cos(-d) + v.getZ() * Math.sin(-d);
		double z = v.getX() * -Math.sin(-d) + v.getZ() * Math.cos(-d);
		v.update(x, v.getY(), z);
	}
	public static void rotateZ(Vector3D v, double d)
	{
		double x = v.getX() * Math.cos(-d) + v.getY() * -Math.sin(-d);
		double y = v.getX() * Math.sin(-d) + v.getY() * Math.cos(-d);
		v.update(x, y, v.getZ());
	}
	public void translateI(double x, double y)
	{
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() + x, i.getY() + y, i.getZ());
		}
	}
	public void translateI(double x, double y, double z)
	{
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() + x, i.getY() + y, i.getZ() + z);
		}
	}
	public void rotateZI(double d)
	{
		//reset origin to 0
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() - transX, i.getY() + transY, i.getZ());
		}
		for (Vector3D i : getVectors())
		{
			rotateZ(i, d);
		}
		//restore
		for (Vector3D i : getVectors())
		{
			i.update(i.getX() + transX, i.getY() - transY, i.getZ());
		}
	}
	public double getTX()
	{
		return transX;
	}
	public double getTY()
	{
		return transY;
	}
	public double getZ()
	{
		return getVectors().get(0).getZ();
	}
}
