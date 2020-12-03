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






class AVLTree
{
    private Node root;



    public AVLTree()
    {
        root = null;
    }



    public Node insert(Node inNode, int inValue)
    {
        if (inNode == null)
        {
            return (new Node(inValue));
        }

        if (inValue < inNode.getValue())
        {
            inNode.setLeft(insert(inNode.getLeft(), inValue));
        }

        else
        {
            inNode.setRight(insert(inNode.getRight(), inValue));
        }

        inNode = rebalance(inNode);

        return inNode;
    }



    public Node rebalance(Node inNode)
    {
        inNode.setHeight(Math.max(inNode.getLeft().getHeight(), inNode.getRight().getHeight()) + 1);

        int balance = getBalance(inNode);

        // If left heavy
        if (balance > 1)
        {
            if (inNode.getRight().getRight().getHeight() > inNode.getRight().getLeft().getHeight())
            {
                inNode = rotateLeft(inNode);
            }

            else
            {
                inNode.setRight(rotateRight(inNode.getRight()));
                inNode = rotateLeft(inNode);
            }
        }

        else if (balance < -1)
        {
            if (inNode.getLeft().getLeft().getHeight() > inNode.getLeft().getRight().getHeight())
            {
                inNode = rotateRight(inNode);
            }

            else
            {
                inNode.setLeft(rotateLeft(inNode.getLeft()));
                inNode = rotateRight(inNode);
            }
        }

        return inNode;
    }



    public int getBalance(Node inNode)
    {
        int leftHeight = inNode.getLeft().getHeight();
        int rightHeight = inNode.getRight().getHeight();

        return leftHeight - rightHeight;
    }



    public Node rotateLeft(Node inNode)
    {
        Node inRight = inNode.getRight();
        Node inRightLeft = inRight.getLeft();

        inRight.setLeft(inNode);
        inNode.setRight(inRightLeft);

        updateHeight(inNode);
        updateHeight(inRight);

        return inRight;
    }



    public Node rotateRight(Node inNode)
    {
        Node inLeft = inNode.getLeft();
        Node inLeftRight = inLeft.getRight();

        inLeft.setRight(inNode);
        inNode.setLeft(inLeftRight);

        updateHeight(inNode);
        updateHeight(inLeft);

        return inLeft;
    }



    public void updateHeight(Node inNode)
    {
        int leftHeight = -1;

        if (inNode.getLeft() != null)
        {
            leftHeight = inNode.getLeft().getHeight();
        }

        int rightHeight = -1;

        if (inNode.getRight() != null)
        {
            rightHeight = inNode.getRight().getHeight();
        }

        inNode.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }
}






public class Main
{

    public static void main(String[] args)
    {
	// write your code here
    }
}
