package de.marinus.objectdebugger.tree;


import de.marinus.objectdebugger.util.ObjectUtil;

import java.util.Objects;

public class ObjectTree {

    private Node root;

    public ObjectTree(Object rootValue) {
        this(new Node(rootValue, null));
    }

    public ObjectTree(Node root) {
        this.root = root;
        this.initializeFromRootNode(this.root);
    }


    private void initializeFromRootNode(Node node) {
        if (ObjectUtil.isPrimitive(node.value) || ObjectUtil.isPrimitiveArray(node.value)) {
            return;
        }
        Object[] children = ObjectUtil.getChildren(node.value);
        for (Object child : children) {

            Node childNode = new Node(child, node);
            node.add(childNode);
            if (!ObjectUtil.isPrimitive(child) && !ObjectUtil.isPrimitiveArray(child) && !Objects.isNull(child)) {
                initializeFromRootNode(childNode);
            }
        }
    }

    public int getSize() {
        return getNodeSize(root);
    }

    public int getChildSize() {
        return this.getSize() - 1;
    }


    private int getNodeSize(Node node) {
        int size = 1;
        for (Node child : node.children) {
            size += getNodeSize(child);
        }
        return size;
    }

    public Node getRoot() {
        return root;
    }

    public Node[] getChildren() {
        return root.children;
    }

    public Node[] getChild(int depth) {
        int currentDepth = 0;
        Node[] nodes = new Node[0];
        for (Node child : root.children) {
            nodes = getChild(child, depth, currentDepth, nodes);
        }
        return nodes;
    }

    private Node[] getChild(Node node, int depth, int currentDepth, Node[] nodes) {
        if (currentDepth == depth) {
            Node[] newNodes = new Node[nodes.length + 1];
            System.arraycopy(nodes, 0, newNodes, 0, nodes.length);
            newNodes[nodes.length] = node;
            return newNodes;
        }
        for (Node child : node.children) {
            nodes = getChild(child, depth, currentDepth + 1, nodes);
        }
        return nodes;
    }


    public String print() {
        return printNode(root, 0);
    }

    public String print(int depth) {
        return printNode(root, depth);
    }

    public String printNode(Node node, int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("  ");
        }
        builder.append(node.value);
        builder.append("\n");
        for (Node child : node.children) {
            builder.append(printNode(child, depth + 1));
        }
        return builder.toString();
    }

    public String printNode(Node node, int depth, boolean printPrimitives) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("  ");
        }
        builder.append(node.value);
        builder.append("\n");
        for (Node child : node.children) {
            builder.append(printNode(child, depth + 1, printPrimitives));
        }
        return builder.toString();
    }

    public static class Node {
        private Object value;
        private Object parent;
        private Node[] children;

        public Node(Object value, Object parent) {
            this.value = value;
            this.parent = parent;
            this.children = new Node[0];
        }

        public void add(Node node) {
            Node[] newChildren = new Node[children.length + 1];
            System.arraycopy(children, 0, newChildren, 0, children.length);
            newChildren[children.length] = node;
            children = newChildren;
        }

    }
}
