package rendering;

import java.util.ArrayList;

public class Mesh {
	private ArrayList<Shape> listShapes = new ArrayList<Shape>();
	private Shape base;
	public Mesh (Shape b)
	{
		listShapes.add(b);
		base = b;
	}
	public void addShape(Shape a, double x, double y)
	{
		a.translate(x, y);
		listShapes.add(a);
	}
	public void rotateX(double d)
	{
		double baseX = base.getTX();
		double baseY = base.getTY();
		for (Shape a : listShapes)
		{
			a.translate(-baseX, -baseY);
			a.rotateXO(d);
			a.translate(baseX, baseY);
		}
	}
	public void rotateY(double d)
	{
		double baseX = base.getTX();
		double baseY = base.getTY();
		for (Shape a : listShapes)
		{
			a.translate(-baseX, -baseY);
			a.rotateYO(d);
			a.translate(baseX, baseY);
		}
	}
	public void scale(double d)
	{
		for (Shape a : listShapes)
		{
			a.scale(d);
		}
	}
	public void translate(double x, double y)
	{
		for (Shape a : listShapes)
		{
			a.translate(x,y);
		}
	}
	public void setColor(int color)
	{
		for (Shape a : listShapes)
		{
			a.setColor(color);
		}
	}
}
