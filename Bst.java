class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class BST {
    Node root;

    // Insert a node
    public Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }

        return root;
    }

    // Search a key in BST
    public boolean search(Node root, int key) {
        if (root == null)
            return false;

        if (root.data == key)
            return true;

        if (key < root.data)
            return search(root.left, key);
        else
            return search(root.right, key);
    }

    // Inorder Traversal (Left -> Root -> Right)
    public void inorder(Node root) {
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // Preorder Traversal (Root -> Left -> Right)
    public void preorder(Node root) {
        if (root == null)
            return;

        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    // Postorder Traversal (Left -> Right -> Root)
    public void postorder(Node root) {
        if (root == null)
            return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {
        BST tree = new BST();

        tree.root = tree.insert(tree.root, 12);
        tree.root = tree.insert(tree.root, 5);
        tree.root = tree.insert(tree.root, 70);
        tree.root = tree.insert(tree.root, 3);
        tree.root = tree.insert(tree.root, 6);
        tree.root = tree.insert(tree.root, 13);

        System.out.print("Inorder: ");
        tree.inorder(tree.root);

        System.out.println();

        System.out.print("Preorder: ");
        tree.preorder(tree.root);

        System.out.println();

        System.out.print("Postorder: ");
        tree.postorder(tree.root);

        System.out.println();

        // Search
        int key = 6;

        if (tree.search(tree.root, key))
            System.out.println("Key " + key + " found in BST.");
        else
            System.out.println("Key " + key + " not found in BST.");
    }
                      } 