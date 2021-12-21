/**
 * Create Rectangle class per instructions in lab.
 *
 * @author Shivam Maji
 * @version 9/28/21
 * @author Period: 7
 * @author Assignment: JMCh09Ex10_Rectangle
 */

class Rectangle {

    int width;
    int height;

    public Rectangle(int w, int h){
        if(w>0 && h>0)
        {
            width = w;
            height = h;
        }
        else
        {
            throw new IllegalArgumentException("Cannot have rectangle with 0 height and/or width");
        }
    }

    public Rectangle(int l){
        if(l>0 && l>0)
        {
            width = l;
            height = l;
        }
        else
        {
            throw new IllegalArgumentException("Cannot have square with 0 height and/or width");
        }
        
    }

    public Rectangle(){
        width = 1;
        height = 1;
    }

    public boolean isSquare(){
        if(width == height)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public void quadratize(){
        int area = width * height;
        int sideLength = (int) Math.round(Math.sqrt(area));
        width = sideLength;
        height = sideLength;
    }

    public String toString()
    {
        return width + " x " + height; 
    }
}