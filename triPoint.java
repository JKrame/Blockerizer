
public class triPoint implements Comparable<triPoint>{
	int x;
	int y;
	
	public triPoint(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(triPoint arg0) {
		// TODO Auto-generated method stub
		if(this.x < arg0.x){
			return -1;
		}
		else if(this.x == arg0.x){
			return 0;
		}
		else{
			return 1;
		}
	}
}
