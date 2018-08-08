package com.structure.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 二叉树的Java实现
 * Author：wedo
 * Time：2018-08-02 16:31:39
 */
public class Tree {
    private Node root;
    private List<Node> list = new ArrayList<>();

    public Tree() {
        init();
    }

    public void init() {
        Node x = new Node("X", null, null);
        Node y = new Node("Y", null, null);
        Node d = new Node("D", x, y);
        Node e = new Node("E", null, null);
        Node f = new Node("F", null, null);
        Node c = new Node("C", e, f);
        Node b = new Node("B", d, null);
        Node a = new Node("A", b, c);
        root = a;
    }

    private class Node {
        private String data;
        //定义指向左子树的指针
        private Node lchild;
        //定义指向右子树的指针
        private Node rchild;

        public Node(String data, Node lchild, Node rchild) {
            this.data = data;
            this.lchild = lchild;
            this.rchild = rchild;
        }
    }

    /**
     * 对该二叉树进行前序遍历
     * 结果存储到list中
     *
     * @param node
     */
    public void preOrder(Node node) {
        //先将根节点存入list
        list.add(node);
        //如果左子树不为空继续往左找，在递归调用方法的时候一直会将子树的根存
        // 入list，这就做到了先遍历根节点
        if (node.lchild != null) {
            preOrder(node.lchild);
        }
        //无论走到哪一层，只要当前节点左子树为空，那么就可以在右子树上遍历，
        //保证了桶左右的遍历顺序
        if (node.rchild != null) {
            preOrder(node.rchild);
        }
    }

    /**
     * 对该二叉树进行中序遍历，
     * 结果存储到list中
     *
     * @param node
     */
    public void inOrder(Node node) {
        if (node.lchild != null) {
            inOrder(node.lchild);
        }
        list.add(node);
        if (node.rchild != null) {
            inOrder((node.rchild));
        }
    }

    /**
     * 对该二叉树进行后序遍历
     * 结果存储到list中
     *
     * @param node
     */
    public void postOrder(Node node) {
        if (node.lchild != null) {
            postOrder(node.lchild);
        }
        if (node.rchild != null) {
            postOrder(node.rchild);
        }
        list.add(node);
    }

    /**
     * 返回当前数的深度
     * 说明：
     * 1、如果一棵树只有一个结点，它的深度为1
     * 2、如果根结果只有左子树而没有右子树，那么树的尝试是其左子树的深度加1；
     * 3、如果根结点只有右子树而没有左子树，那么树的尝试应该是其右子树的深度加1；
     * 4、如果既有右子树又有左子树，那么该树的尝试就是其左右子树深度的较大值加1；
     * @param node
     * @return
     */
    public int getTreeDepth(Node node){
        if (node.lchild == null && node.rchild == null) {
            return 1;
        }
        int left = 0, right = 0;
        if (node.lchild != null) {
            left = getTreeDepth(node.lchild);
        }
        if (node.rchild != null) {
            right = getTreeDepth(node.rchild);
        }
        return left > right ? left + 1 : right + 1;
    }

    //得到遍历结果
    public List<Node> getResult() {
        return list;
    }

    public static void main(String[] args) {
        List list = new LinkedList();
        Map map = new ConcurrentHashMap();
        List arr = new ArrayList();
        Tree tree = new Tree();
        System.out.println("根节点是：" + tree.root);
        // tree.preOrder(tree.root);
        tree.postOrder(tree.root);
        for (Node node : tree.getResult()) {
            System.out.println(node.data);
        }
        System.out.println("树的深度是： " + tree.getTreeDepth(tree.root));
    }
}
