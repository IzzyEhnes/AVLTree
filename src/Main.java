class Node
{
    private int value;
    private int height;
    private Node right;
    private Node left;



    public Node(int inValue)
    {
        this.value = inValue;
        left = null;
        right = null;
    }



    public int getValue()
    {
        return this.value;
    }



    public void setLeft(Node inNode)
    {
        left = inNode;
    }



    public Node getLeft()
    {
        return left;
    }



    public void setRight(Node inNode)
    {
        right = inNode;
    }



    public Node getRight()
    {
        return right;
    }



    public int getHeight()
    {
        return this.height;
    }



    public void setHeight(int inHeight)
    {
        this.height = inHeight;
    }
}





public class Main
{

    public static void main(String[] args)
    {
	// write your code here
    }
}
