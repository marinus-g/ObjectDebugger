package de.marinus.objectdebugger.util;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.Arrays;

@UtilityClass
public class ObjectUtil {

    public boolean isPrimitive(Object object) {
        return object instanceof String || object instanceof Number || object instanceof Boolean;
    }

    public boolean isPrimitiveArray(Object object) {
        return object instanceof String[] || object instanceof Number[] || object instanceof Boolean[];
    }

    public Object[] getArray(Object object) {
        if (object instanceof String[]) {
            return (String[]) object;
        } else if (object instanceof Number[]) {
            return (Number[]) object;
        } else if (object instanceof Boolean[]) {
            return (Boolean[]) object;
        }
        return null;
    }

    public Object[] getChildren(Object object) {
        if (object == null) {
            return new Object[0];
        }
        Field[] fields = Arrays.stream(object.getClass().getDeclaredFields())
                .filter(field -> !field.isSynthetic())
                .toArray(Field[]::new);
        final Object[] children = new Object[fields.length];
        for (int i = 0; i < children.length; i++) {
            try {
                final Field field = fields[i];
                field.setAccessible(true);
                children[i] = field.get(object);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return children;
    }

    public boolean isArray(Object object) {
        return object.getClass().isArray();
    }
}