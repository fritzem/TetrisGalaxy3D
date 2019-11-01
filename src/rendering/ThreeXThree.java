package rendering;

public class ThreeXThree {
	private Vector3D f;
	private Vector3D s;
	private Vector3D t;
	public ThreeXThree(Vector3D f, Vector3D s, Vector3D t)
	{
		this.f = f;
		this.s = s;
		this.t = t;
	}
	public Vector3D getF()
	{
		return f;
	}
	public Vector3D getS()
	{
		return s;
	}
	public Vector3D getT()
	{
		return t;
	}
	public double getX1()
	{
		return f.getX();
	}
	public double getY1()
	{
		return f.getY();
	}
	public double getZ1()
	{
		return f.getZ();
	}
	public double getX2()
	{
		return s.getX();
	}
	public double getY2()
	{
		return s.getY();
	}
	public double getZ2()
	{
		return s.getZ();
	}
	public double getX3()
	{
		return t.getX();
	}
	public double getY3()
	{
		return t.getY();
	}
	public double getZ3()
	{
		return t.getZ();
	}
}
