import java.awt.Color;

public class Assistant {

	public static boolean isInFunction(triPoint pt, triPoint v1, triPoint v2, triPoint v3)
	{
		boolean b1, b2, b3;
		
		b1 = sign(pt, v1, v2) < 0.0f;
		b2 = sign(pt, v2, v3) < 0.0f;
		b3 = sign(pt, v3, v1) < 0.0f;
		
		return ((b1 == b2) && (b2 == b3));
	}
	
	static float sign (triPoint p1, triPoint p2, triPoint p3)
	{
		return ((p1.x-p3.x)*(p2.y-p3.y)-(p2.x-p3.x)*(p1.y-p3.y));
	}
	
	public static Color calcAvg(Color[][] array, triPoint p1, triPoint p2, triPoint p3)
	{
		Color avg = array[(p1.x+p2.x+p3.x)/3][(p1.y+p2.y+p3.y)/3];
		/*
		for(int i = 0; i < array.length; i++)
		{
			for(int j = 0; j < array[0].length; j++)
			{
				triPoint current = new triPoint(j, i);
				
				if(isInFunction(current, p1, p2, p3))
				{
					int red1 = avg.getRed();
					int red2 = array[i][j].getRed();
					
					int blue1 = avg.getBlue();
					int blue2 = array[i][j].getBlue();
					
					int green1 = avg.getGreen();
					int green2 = array[i][j].getGreen();
					
					avg = new Color((red1+red2)/2, (green1+green2)/2, (blue1+blue2)/2, 127);
				}
				
			}
			
			return avg;
		}
		*/
		return avg;
	}

	public static Color addColors(Color c1, Color c2)
	{
		Color cfinal;
		
		
		int red1 = c1.getRed();
		int red2 = c2.getRed();
		//System.out.println((red1+red2)/2);
		
		int green1 = c1.getGreen();
		int green2 = c2.getGreen();
		//System.out.println((green1+green2)/2);

		
		int blue1 = c1.getBlue();
		int blue2 = c2.getBlue();
		//System.out.println((blue1+blue2)/2);

		/*********************************
		float finalRed = ((red1 * c1.getAlpha() + red2 *(255-c2.getAlpha()))%255);
		float finalGreen = ((green1 * c1.getAlpha() + green2 * (255-c2.getAlpha()))%255);
		float finalBlue = ((blue1 * c1.getAlpha() + blue2 * (255-c2.getAlpha()))%255);
		***********************************/
		
		return (cfinal = new Color((red1+red2)/2, (green1+green2)/2, (blue1+blue2)/2));
		
	}
	
	
	public static Color[][] addPoly(Color[][] image, Color avg, triPoint t1, triPoint t2, triPoint t3) {
		// TODO Auto-generated method stub
		
		int height = image.length;
		int width = image[0].length;
		
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				
			}
		}
		return null;
	}
	
	public static int calcError(Color[][] image1, Color[][] image2)
	{
		int error = 0;
		
		for(int i = 0; i < image1.length; i++)
		{
			for(int j = 0; j < image1[0].length; j++)
			{
				error += image1[i][j].getRGB()-image2[i][j].getRGB();
			}
		}
		return error;
	}

	public static Color calcAvgSquare(Color[][] array, triPoint t, int size) {
		// TODO Auto-generated method stub
		Color avg = null;
		
		for(int i = t.y; i < t.y+size && i < array.length; i++)
		{
			for(int j = t.x; j < t.x+size && j < array[0].length; j++)
			{
				if(i== t.y && j == t.x){
					avg = array[i][j];
				}
				
			
				int red1 = avg.getRed();
				int red2 = array[i][j].getRed();
				int avgRed = (red1+red2)/2;
					
				int blue1 = avg.getBlue();
				int blue2 = array[i][j].getBlue();
				int avgBlue = (blue1+blue2)/2;
					
				int green1 = avg.getGreen();
				int green2 = array[i][j].getGreen();
				int avgGreen = (green1+green2)/2;
					
				avg = new Color(avgRed, avgGreen, avgBlue);
				
				
			}
			
		}
		
		return avg;

		
		}
	
}
