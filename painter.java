/********************************************
 * MR ROBOTO ********************************
 * 11/19/16  ********************************
 *******************************************/




import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class painter {
	   
    
    public static void main(String[] args) throws IOException {
        if(args.length == 0)
        {
        	System.out.println("Please enter a filename and try again");
        	System.exit(0);
        }
        String filename = args[0];
        BufferedImage img = (BufferedImage) getImg(filename);
        
        try{
    		File outputFile = new File("testinput.jpg");
    		ImageIO.write(img, "jpg", outputFile);
    	}
    	catch(IOException e)
    	{
    		
    	}
        
        Color[][] rgbArray = readImageData(img);
        
        Color[][] image = generateImage(rgbArray);
    	BufferedImage canvas = paintCanvas(image);
    	
    	
    	/*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        });*/
    }

    private static BufferedImage paintCanvas(Color[][] image) {
		// TODO Auto-generated method stub
    	int width = image[0].length;
    	int height = image.length;
    	
    	BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	
    	for(int y = 0; y < height; y++)
    	{
    		for(int x = 0; x< width; x++)
    		{
    			canvas.setRGB(x, y, image[y][x].getRGB());
    		}
    	}
    	try{
    		File outputFile = new File("canvas.jpg");
    		ImageIO.write(canvas, "jpg", outputFile);
    	}
    	catch(IOException e)
    	{
    		
    	}
    	
		return null;
	}

	private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Le Artist");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    } 
    
    private static Color[][] readImageData(BufferedImage image){
    	int width = image.getWidth();
    	int height = image.getHeight();
    	Color[][] result = new Color[height][width];
    	for(int row = 0; row < height; row++){
    		for(int col = 0; col < width; col++){
    			result[row][col] = new Color(image.getRGB(col, row));
    			//System.out.print(result[row][col] + ", ");
    		}
    		//System.out.println(" ");
    	}
    	/*
    	Color[][] res = new Color[width][height];
    	for(int row = 0; row < height; row++)
    	{
    		for(int col = 0; col < width; col++){
    			res[row][col] = new Color(image.getRGB(row, col));
    			Color c = res[row][col];
    			System.out.println("R: " + c.getRed() + " G: " + c.getGreen() + " B: " + c.getBlue() + " A: " + c.getAlpha() + "");
    		}
    	}*/
    	
    	return result;
    }
    
    private static BufferedImage getImg(String filename) throws IOException{
    	BufferedImage img = null;
    	try{
    		img = ImageIO.read(new File(filename));
    	}
    	catch(IOException e)
    	{
    		System.out.println(e);
    	}
    	return img;
    }
    
    
/*
    private static Color[][] generateImage(Color[][] array){
    	
    	
    	int height = array.length;
    	int width = array[0].length;
    	Color[][] image = new Color[height][width];
    	boolean[][] ticked = new boolean[height][width];

    	
    	for(int i = 0; i < height; i ++)
    	{
    		for(int j = 0; j < width; j++)
    		{
    			image[i][j] = new Color(1.0f,1.0f,1.0f,0.0f);
    		}
    	}
    		for(int i = 0; i < 100; i++)
    		{
    			triPoint t1 = new triPoint((int)Math.random()%width,(int)Math.random()%height);
    			triPoint t2 = new triPoint((int)(t1.x+Math.random()%20),(int)(t1.y+Math.random()%20));
    			triPoint t3 = new triPoint((int)(t1.x+Math.random()%30),(int)(t1.y+Math.random()%30));

    			Color avgValue = Assistant.calcAvg(array, t1, t2, t3);
        		//System.out.println(avgValue);
        		
        		for(int y = 0; y < height; y ++)
        		{
        			for(int x =0; x < width; x++)
        			{
        				triPoint t = new triPoint(x, y);
        				if(Assistant.isInFunction(t, t1,t2,t3))
        				{
        					image[y][x] = Assistant.addColors(image[y][x], avgValue);
        					ticked[y][x] = true;
        				}    
        			}

        		}
    		}
    		
    		for(int i = 0; i < height; i ++)
    		{
    			for(int j = 0; j < width; j++)
    			{
    				if(!ticked[i][j])
    				{
    					triPoint t1 = new triPoint(j, i);
    	    			triPoint t2 = new triPoint((int)Math.random()%width,(int)Math.random()%height);
    	    			triPoint t3 = new triPoint((int)Math.random()%width,(int)Math.random()%height);

    	    			Color avgValue = Assistant.calcAvg(array, t1, t2, t3);
    	        		//System.out.println(avgValue);
    	        		
    	        		for(int y = 0; y < height; y ++)
    	        		{
    	        			for(int x =0; x < width; x++)
    	        			{
    	        				triPoint t = new triPoint(x, y);
    	        				if(Assistant.isInFunction(t, t1,t2,t3))
    	        				{
    	        					image[y][x] = Assistant.addColors(image[y][x], avgValue);
    	        					ticked[y][x] = true;
    	        				}    
    	        			}

    	        		}
    				}
    				
    			}
    		}
    		
    		
    
    		
    		//t3 = new triPoint(0, width-1);
    		//image = imageRecurse(array, image, t1, t2, t3);
    		
        	
    		return image;

    	}
    	*/
    	
    
    
    
    
    
    private static Color[][] generateImage(Color[][] array){
    	
    	int height = array.length;
    	int width = array[0].length;
    	
    	Color[][] image = new Color[height][width];
    	boolean[][] ticked = new boolean[height][width];

    	for(int i = 0; i < height; i ++)
    	{
    		for(int j = 0; j < width; j++)
    		{
    			image[i][j] = new Color(1.0f,1.0f,1.0f,0.0f);
    			ticked[i][j] = false;
    		}
    	}
    	
		triPoint t = new triPoint(0,0);
    	
    	for(int i = 0; i < 100; i ++)
    	{
    		
        	t = new triPoint((int)((Math.random()*100000)%width),(int)((Math.random()*100000)%height));
        	
        	int size = 1;
        	
    		Color avgValue = Assistant.calcAvgSquare(array, t, size);
    		
    		for(int y = t.y; y < (t.y+size) && y < height; y++)
    		{
    			for(int x = t.x; x < (t.x+size) && x < width; x++)
    			{
    				image[y][x] = avgValue;
    				ticked[y][x] = true;
    			}
    		}

    	}
    	
    	for(int i = 0; i < height; i++)
    	{
    		for(int j = 0; j < width; j++)
    		{
    			if(!ticked[i][j])
    			{
    				t = new triPoint(j, i);
    				int size = (int)(Math.random()*100000)%20;
    	        	
    	    		Color avgValue = Assistant.calcAvgSquare(array, t, size);
    	    		
    	    		for(int y = i; y < i+size && y < height; y++)
    	    		{
    	    			for(int x = j; x < j+size && x < width; x++)
    	    			{
    	    				image[y][x] = avgValue;
    	    				ticked[y][x] = true;
    	    			}
    	    		}
    			}
    		}
    	}
    	return image;

    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
    	
    	
    /*	
    		triPoint t1 = new triPoint(0,0);
    		triPoint t2 = new triPoint(0, width-1);
    		triPoint t3 = new triPoint(height-1, 0);
    		triPoint t4 = new triPoint(height-1, width-1);
    		
    		ArrayList<triPoint> points = new ArrayList<triPoint>();
    		points.add(t1);
    		points.add(t2);
    		points.add(t3);
    		points.add(t4);
    		
    		/*for(int i = 0; i < 100; i++)
    		{
    			triPoint t = new triPoint((int)((Math.random()*100000)%width),(int)((Math.random()*100000)%height));
    			points.add(t);
    		}
    		
    		Collections.sort(points);
    	
    		for(triPoint point: points)
    		{
    			System.out.println(point.x + ", " + point.y);
    		}
    		
        	image = imageRecurse(array, image, t1, t3, t4);
    		image = imageRecurse(array, image, t1, t2, t4);
    		
    		//t3 = new triPoint(0, width-1);
    		//image = imageRecurse(array, image, t1, t2, t3);
    		
        	
    		return image;

    	}
    	
    	
    	
    
    
    private static Color[][] imageRecurse(Color[][] array, Color[][] image, triPoint t1, triPoint t2, triPoint t3)
    {

    	int height = array.length;
    	int width = array[0].length;

    	double dist = Math.sqrt(Math.pow(t2.x-t1.x, 2)+Math.pow(t2.y-t1.y, 2));
    	double area = .5*Math.pow(dist, 2);
    	
    	if(area > .05*width*height)
    	{
    		return image;
    	}
    	
    	int midpointX = (t1.x+t2.x)/2;
    	int midpointY = (t1.y+t2.y)/2;
    	
    	triPoint mid = new triPoint(midpointX, midpointY);

		/*********************************************
		 * System.out.println(x1 + ", " + y1);
		 * System.out.println(x2 + ", " + y2);
		 * System.out.println(x3 + ", " + y3);
		 *********************************************
		Color[][] prev = image;

		Color avgValue = Assistant.calcAvg(array, t1, t2, t3);
		//System.out.println(avgValue);
		
		for(int y = 0; y < height; y ++)
		{
			for(int x =0; x < width; x++)
			{
				triPoint t = new triPoint(x, y);
				if(Assistant.isInFunction(t, t1,t2,t3))
				{
					image[y][x] = Assistant.addColors(image[y][x], avgValue);
				}    
			}

		}
    	
    	image = imageRecurse(array, image, t1, t2, mid);
		image = imageRecurse(array, image, t2, t3, mid);
		
		
		/*if(Math.abs(Assistant.calcError(array, prev)) > Math.abs(Assistant.calcError(array, image)))
		{
			image = imageRecurse(array, image);
		}
		else
		{
			image = imageRecurse(array, prev);
		}
		return image;

	}
    
    private static void Delaunay()
    {}
    
    */
}
	
	
    	
    

