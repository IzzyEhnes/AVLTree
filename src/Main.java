import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

class Node
{
    private int value;
    private int height;
    private int balanceFactor;
    private Node right;
    private Node left;



    public Node()
    {

    }



    public Node(int inValue)
    {
        value = inValue;
        height = 0;
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



    public int getBalanceFactor()
    {
        return this.balanceFactor;
    }



    public void setBalanceFactor(int inBalanceFactor)
    {
        this.balanceFactor = inBalanceFactor;
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
        System.out.println("\nInsert " + key);
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
            inNode.setLeft(insert(inNode.getLeft(), inValue));
        }

        else
        {
            inNode.setRight(insert(inNode.getRight(), inValue));
        }

        inNode = rebalance(inNode);

        // sets balance factor so it can be added to output later
        inNode.setBalanceFactor(getBalance(inNode));

        return inNode;
    }



    public Node rebalance(Node inNode)
    {
        updateHeight(inNode);

        int balance = getBalance(inNode);

        // left heavy outside
        if (balance > 1 && getBalance(inNode.getLeft()) >= 0)
        {
            System.out.println("\nLEFT heavy OUTSIDE");
            return rotateRight(inNode);
        }

        // right heavy outside
        if (balance < -1 && getBalance(inNode.getRight()) <= 0)
        {
            System.out.println("\nRIGHT heavy OUTSIDE");
            return rotateLeft(inNode);
        }

        // left heavy inside
        if (balance > 1 && getBalance(inNode.getLeft()) < 0)
        {
            System.out.println("\nLEFT heavy INSIDE");
            inNode.setLeft(rotateLeft(inNode.getLeft()));

            return rotateRight(inNode);
        }

        // right heavy inside
        if (balance < -1 && getBalance(inNode.getRight()) > 0)
        {
            System.out.println("\nRIGHT heavy INSIDE");
            inNode.setRight(rotateRight(inNode.getRight()));

            return rotateLeft(inNode);
        }

        return inNode;
    }



    public int getBalance(Node inNode)
    {
        if (inNode == null)
        {
            return 0;
        }

        return height(inNode.getLeft()) - height(inNode.getRight());
    }



    public Node rotateLeft(Node inNode)
    {
        System.out.println("Left rotation on node " + inNode.getValue());

        Node newRoot = inNode.getRight();
        Node newRootLeft = newRoot.getLeft();

        newRoot.setLeft(inNode);
        inNode.setRight(newRootLeft);

        updateHeight(inNode);
        updateHeight(newRoot);

        return newRoot;
    }



    public Node rotateRight(Node inNode)
    {
        System.out.println("Right rotation on node " + inNode.getValue());

        Node newRoot = inNode.getLeft();
        Node newRootRight = newRoot.getRight();

        newRoot.setRight(inNode);
        inNode.setLeft(newRootRight);

        updateHeight(inNode);
        updateHeight(newRoot);

        return newRoot;
    }



    public void updateHeight(Node inNode)
    {
        inNode.setHeight(Math.max(height(inNode.getLeft()), height(inNode.getRight())) + 1);
    }



    public int height(Node inNode)
    {
        if (inNode == null)
        {
            return -1;
        }

        else
        {
            return  inNode.getHeight();
        }
    }



    public void getLevelOrder()
    {
        if (root != null)
        {
            int height = root.getHeight();

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
            sb.append(levelOrder).append("\nValue: " + inNode.getValue() + "\nHeight: " + inNode.getHeight() +
                    "\nBalance factor: " + inNode.getBalanceFactor() + "\n");

            levelOrder = sb.toString();
        }

        else
        {
            getLevelOrderRecursive(inNode.getLeft(), level - 1);
            getLevelOrderRecursive(inNode.getRight(), level - 1);
        }
    }
}






class Driver
{
    public static AVLTree readFile(String fileName)
    {
        Scanner fileReader = null;

        try
        {
            fileReader = new Scanner(new File(fileName));
        }

        catch (FileNotFoundException fileError)
        {
            System.out.println(String.format
                    ("There was a problem opening file \"%s\": \n\tError = %s", fileName, fileError.getMessage()));

            System.out.println("Exiting program...");

            System.exit(1);
        }

        AVLTree tree = new AVLTree();

        while (fileReader.hasNext())
        {
            int num = fileReader.nextInt();

            tree.insert(num);
        }

        fileReader.close();

        return tree;
    }



    public static void writeFile(AVLTree inTree, String fileName)
    {
        try
        {
            File outputFile = new File(fileName);

            FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            inTree.getLevelOrder();
            bw.write(inTree.getLevelOrderString());

            bw.close();
            fw.close();
        }

        catch (Exception ex)
        {
            System.out.print("\nError encountered when creating file: ");
            System.out.println(ex.getMessage());
        }
    }



    public static void main(String[] args)
    {
        AVLTree tree = new AVLTree();

        tree = readFile("src/input.txt");

        writeFile(tree, "src/output.txt");
    }
}
