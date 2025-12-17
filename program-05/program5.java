class treeNode{
    char val;
    treeNode left;
    treeNode right;

    treeNode(char val){
        this.val = val;
        this.left = null;
        this.right = null;
    }

    treeNode(char val, treeNode left, treeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class program5{
    public static void main(String[] args){
        treeNode root =
                new treeNode('a',
                        new treeNode('b', new treeNode('d'), new treeNode('e')),
                        new treeNode('c', new treeNode('f'), null)
                );
        root = invert(root);
        printTree(root);
    }

    public static treeNode invert(treeNode root){
        if (root == null) return null;

        treeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invert(root.left);
        invert(root.right);

        return root;
    }

    public static void printTree(treeNode root){
        if (root == null) return;
        System.out.print(root.val + "");
        printTree(root.left);
        printTree(root.right);
    }
}