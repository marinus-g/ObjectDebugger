package de.marinus.objectdebugger;

import de.marinus.objectdebugger.objects.SimpleTestObject;
import de.marinus.objectdebugger.tree.ObjectTree;
import de.marinus.objectdebugger.tree.ObjectTree.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectTreePrintTest {

    @Test
    public void print_emptyTree_returnsCorrectString() {
        ObjectTree tree = new ObjectTree(new Node(null, null));
        assertEquals("null\n", tree.print());
    }

    @Test
    public void print_singleNodeTree_returnsCorrectString() {
        ObjectTree tree = new ObjectTree(new Node("single node", null));
        assertEquals("single node\n", tree.print());
    }

    @Test
    public void print_treeWithTwoNodes_returnsCorrectString() {
        Node rootNode = new Node("root", null);
        rootNode.add(new Node("child", rootNode));
        ObjectTree tree = new ObjectTree(rootNode);
        assertEquals("root\n  child\n", tree.print());
    }

    @Test
    public void print_treeWithThreeNodes_returnsCorrectString() {
        Node rootNode = new Node("root", null);
        Node childNode = new Node("child", rootNode);
        rootNode.add(childNode);
        childNode.add(new Node("grandchild", childNode));
        ObjectTree tree = new ObjectTree(rootNode);
        assertEquals("root\n  child\n    grandchild\n", tree.print());
    }

    @Test
    public void print_treeWithMultipleLevels_returnsCorrectString() {
        Node rootNode = new Node("root", null);
        Node childNode1 = new Node("child1", rootNode);
        Node childNode2 = new Node("child2", rootNode);
        rootNode.add(childNode1);
        rootNode.add(childNode2);
        childNode1.add(new Node("grandchild1", childNode1));
        childNode2.add(new Node("grandchild2", childNode2));
        ObjectTree tree = new ObjectTree(rootNode);
        assertEquals("root\n  child1\n    grandchild1\n  child2\n    grandchild2\n", tree.print());
    }

    @Test
    public void print_treeWithSimpleTestObject_returnsCorrectString() {
        SimpleTestObject testObject = new SimpleTestObject("test", 123, true, null);
        ObjectTree tree = new ObjectTree(new Node(testObject, null));
        assertEquals("SimpleTestObject{name='test', value=123, flag=true}\n", tree.print());

    }
}