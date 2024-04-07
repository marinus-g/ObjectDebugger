package de.marinus.objectdebugger;

import de.marinus.objectdebugger.tree.ObjectTree;

import java.util.logging.Logger;

public class ObjectDebugger {

    private final ObjectTree objectTree;
    private final Logger logger;
    private ObjectDebugger(Object object, Logger logger) {
        this.objectTree = new ObjectTree(object);
        this.logger = logger;
    }

    public void print() {
        logger.info(objectTree.print());
    }

    public void print(int depth) {
        logger.info(objectTree.print(depth));
    }

    public void print(int depth, boolean printPrimitive) {

    }

    public void print(int depth, boolean printPrimitive, boolean printNull) {

    }

    public void print(int depth, boolean printPrimitive, boolean printNull, boolean printArray) {

    }

    public void print(int depth, boolean printPrimitive, boolean printNull, boolean printArray, boolean printArrayContent) {

    }

    public void print(int depth, boolean printPrimitive, boolean printNull, boolean printArray, boolean printArrayContent, boolean printArraySize) {

    }

    public void print(int depth, boolean printPrimitive, boolean printNull, boolean printArray, boolean printArrayContent, boolean printArraySize, boolean printArrayIndex) {

    }

    public static ObjectDebugger debug(Object object) {
        return debug(object, Logger.getGlobal());
    }
    public static ObjectDebugger debug(Object object, Logger logger) {
        return new ObjectDebugger(object, logger);
    }
}
