class Node
{
    private int value;
    private int height;
    private Node right;
    private Node left;


    public Node()
    {

    }



    public Node(int inValue)
    {
        value = inValue;
        height = -1;
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
    private String levelOrder = "";



    public AVLTree()
    {
        root = null;
    }



    public String getLevelOrderString()
    {
        return levelOrder;
    }



    public void insert(int key)
    {
        root = insert(root, key);
    }



    public Node insert(Node inNode, int inValue)
    {
        if (inNode == null)
        {
            return (new Node(inValue));
        }

        if (inValue < inNode.getValue())
        {
            System.out.println("Inserted " + inValue + " left");
            inNode.setLeft(insert(inNode.getLeft(), inValue));
        }

        else
        {
            System.out.println("Inserted " + inValue + " right");
            inNode.setRight(insert(inNode.getRight(), inValue));
        }

        inNode = rebalance(inNode);

        return inNode;
    }



    public Node rebalance(Node inNode)
    {
        updateHeight(inNode);

        int balance = getBalance(inNode);
        System.out.println("Balance: " + balance);


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
        int leftHeight = -1;
        int rightHeight = -1;

        if (inNode == null)
        {
            return 0;
        }

        if (inNode.getLeft() != null)
        {
            leftHeight = inNode.getLeft().getHeight();
        }

        if (inNode.getRight() != null)
        {
            leftHeight = inNode.getRight().getHeight();
        }

        return leftHeight - rightHeight;
    }



    public Node rotateLeft(Node inNode)
    {
        Node newRoot = inNode.getRight();
        Node inRightsLeft = newRoot.getLeft();

        newRoot.setLeft(inNode);
        inNode.setRight(inRightsLeft);

        updateHeight(inNode);
        updateHeight(newRoot);

        return newRoot;
    }



    public Node rotateRight(Node inNode)
    {
        Node newRoot = inNode.getLeft();
        Node inLeftsRight = newRoot.getRight();

        inNode.setLeft(newRoot);
        newRoot.setLeft(inLeftsRight);

        updateHeight(inNode);
        updateHeight(newRoot);

        return newRoot;
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



    public void getLevelOrder()
    {
        if (root != null)
        {
            int height = root.getHeight();

            System.out.println("height: " + height);

            for (int level = 0; level <= height + 1; level++)
            {
                getLevelOrderRecursive(root, level);
            }
        }
    }



    public void getLevelOrderRecursive(Node inNode, int level)
    {
        if (inNode == null)
        {
            return;
        }

        if (level == 0)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(levelOrder).append(inNode.getValue() + "\n");
            levelOrder = sb.toString();
        }

        else
        {
            getLevelOrderRecursive(inNode.getLeft(), level - 1);
            getLevelOrderRecursive(inNode.getRight(), level - 1);
        }
    }
}






public class Main
{

    public static void main(String[] args)
    {
        AVLTree tree = new AVLTree();

        /*
        tree.insert(500);
        tree.insert(400);
        tree.insert(600);
        tree.insert(300);
        tree.insert(700);
        tree.insert(200);
        tree.insert(800);
         */

        tree.insert(86);
        tree.insert(75);
        tree.insert(30);


        tree.getLevelOrder();
        System.out.println(tree.getLevelOrderString());
    }
}
