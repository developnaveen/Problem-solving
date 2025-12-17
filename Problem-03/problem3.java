class treeNode{
    int val;
    treeNode left;
    treeNode right;

    treeNode(int data, treeNode left, treeNode right){
        this.val = data;
        this.left= left;
        this.right=right;
    }
}

public class problem3{
    public static void main(String[] args){
        treeNode root = new treeNode(1, new treeNode(2, null, null),new treeNode(3, null, null));
        System.out.println(path(root,0,3));
    }

    public static boolean path(treeNode root,int sum,int target){
        if(root == null){
            return false;
        }

        sum+=root.val;
        if(root.left == null && root.right == null){
            if(sum==target)
                return true;
        }
        return path(root.left,sum,target) || path(root.right,sum,target);
    }
}