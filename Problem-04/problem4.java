class treeNode{
        int val;
        treeNode left;
        treeNode right;

        treeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }

    treeNode(int val, treeNode left, treeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class problem4{
    public static void main(String[] args){
        treeNode tree = new treeNode(3, new treeNode(4, new treeNode(1), new treeNode(2)), new treeNode(5));

        treeNode Subtree = new treeNode(4, new treeNode(1), new treeNode(2));

        System.out.println(isSubtree(tree, Subtree));
    }

    public static boolean isSubtree(treeNode root, treeNode subRoot) {
        if( root == null ) return false;
        if(isSameTree(root, subRoot)) return true;

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private static boolean isSameTree(treeNode a, treeNode b) {
        if(a == null && b == null) return true;
        if(a == null || b == null) return false;

        if(a.val != b.val) return false;

        return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }
}