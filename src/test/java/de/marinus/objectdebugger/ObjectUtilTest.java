package de.marinus.objectdebugger;

import de.marinus.objectdebugger.util.ObjectUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectUtilTest {

    @Test
    public void isPrimitive_returnsTrueForPrimitiveTypes() {
        assertTrue(ObjectUtil.isPrimitive("string"));
        assertTrue(ObjectUtil.isPrimitive(123));
        assertTrue(ObjectUtil.isPrimitive(true));
    }

    @Test
    public void isPrimitive_returnsFalseForNonPrimitiveTypes() {
        assertFalse(ObjectUtil.isPrimitive(new Object()));
    }

    @Test
    public void isPrimitiveArray_returnsTrueForPrimitiveArrayTypes() {
        assertTrue(ObjectUtil.isPrimitiveArray(new String[]{}));
        assertTrue(ObjectUtil.isPrimitiveArray(new Number[]{}));
        assertTrue(ObjectUtil.isPrimitiveArray(new Boolean[]{}));
    }

    @Test
    public void isPrimitiveArray_returnsFalseForNonPrimitiveArrayTypes() {
        assertFalse(ObjectUtil.isPrimitiveArray(new Object[]{}));
    }

    @Test
    public void getArray_returnsCorrectArrayForPrimitiveArrayTypes() {
        assertArrayEquals(new String[]{}, ObjectUtil.getArray(new String[]{}));
        assertArrayEquals(new Number[]{}, ObjectUtil.getArray(new Number[]{}));
        assertArrayEquals(new Boolean[]{}, ObjectUtil.getArray(new Boolean[]{}));
    }

    @Test
    public void getArray_returnsNullForNonArrayTypes() {
        assertNull(ObjectUtil.getArray(new Object()));
    }

    @Test
    public void getChildren_returnsCorrectChildrenForObjects() {
        Object testObject = new Object() {
            String child1 = "child1";
            String child2 = "child2";
        };
        assertArrayEquals(new Object[]{"child1", "child2"}, ObjectUtil.getChildren(testObject));
    }

    @Test
    public void isArray_returnsTrueForArrayTypes() {
        assertTrue(ObjectUtil.isArray(new String[]{}));
        assertTrue(ObjectUtil.isArray(new Number[]{}));
        assertTrue(ObjectUtil.isArray(new Boolean[]{}));
    }

    @Test
    public void isArray_returnsFalseForNonArrayTypes() {
        assertFalse(ObjectUtil.isArray(new Object()));
    }
}
