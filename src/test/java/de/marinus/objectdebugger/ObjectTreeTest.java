package de.marinus.objectdebugger;

import de.marinus.objectdebugger.objects.SimpleTestObject;
import de.marinus.objectdebugger.tree.ObjectTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectTreeTest {

    @Test
    public void simpleStringTest() {
        String string = "Hello World!";
        ObjectTree objectTree = new ObjectTree(string);
        assertEquals(1, objectTree.getSize());
    }

    @Test
    public void simpleObjectTest() {
        Object object = new Object();
        ObjectTree objectTree = new ObjectTree(object);
        assertEquals(1, objectTree.getSize());
    }

    @Test
    public void simpleTestObjectTest() {
        SimpleTestObject simpleTestObject = new SimpleTestObject();
        ObjectTree objectTree = new ObjectTree(simpleTestObject);
        assertEquals(5, objectTree.getSize());
    }

    @Test
    public void simpleTestObjectWithChildTest() {
        SimpleTestObject simpleTestObject = new SimpleTestObject("Test", 18, true, new SimpleTestObject());
        ObjectTree objectTree = new ObjectTree(simpleTestObject);
        assertEquals(9, objectTree.getSize());
    }

    @Test
    public void simpleTestObjectWithChildAndChildTest() {
        SimpleTestObject simpleTestObject = new SimpleTestObject("Test", 18, true,
                new SimpleTestObject("Child", 10, false, new SimpleTestObject()));
        ObjectTree objectTree = new ObjectTree(simpleTestObject);
        assertEquals(13, objectTree.getSize());
    }


    @Test
    public void emptyTree_hasSizeOne() {
        ObjectTree tree = new ObjectTree(new ObjectTree.Node(null, null));
        assertEquals(1, tree.getSize());
    }

    @Test
    public void singleNodeTree_hasSizeOne() {
        ObjectTree tree = new ObjectTree(new ObjectTree.Node("single node", null));
        assertEquals(1, tree.getSize());
    }

    @Test
    public void treeWithTwoNodes_hasSizeTwo() {
        ObjectTree.Node rootNode = new ObjectTree.Node("root", null);
        rootNode.add(new ObjectTree.Node("child", rootNode));
        ObjectTree tree = new ObjectTree(rootNode);
        assertEquals(2, tree.getSize());
    }

    @Test
    public void treeWithThreeNodes_hasSizeThree() {
        ObjectTree.Node rootNode = new ObjectTree.Node("root", null);
        ObjectTree.Node childNode = new ObjectTree.Node("child", rootNode);
        rootNode.add(childNode);
        childNode.add(new ObjectTree.Node("grandchild", childNode));
        ObjectTree tree = new ObjectTree(rootNode);
        assertEquals(3, tree.getSize());
    }

    @Test
    public void treeWithMultipleLevels_hasCorrectSize() {
        ObjectTree.Node rootNode = new ObjectTree.Node("root", null);
        ObjectTree.Node childNode1 = new ObjectTree.Node("child1", rootNode);
        ObjectTree.Node childNode2 = new ObjectTree.Node("child2", rootNode);
        rootNode.add(childNode1);
        rootNode.add(childNode2);
        childNode1.add(new ObjectTree.Node("grandchild1", childNode1));
        childNode2.add(new ObjectTree.Node("grandchild2", childNode2));
        ObjectTree tree = new ObjectTree(rootNode);
        assertEquals(5, tree.getSize());
    }
}