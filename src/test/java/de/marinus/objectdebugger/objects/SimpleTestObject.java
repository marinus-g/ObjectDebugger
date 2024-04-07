package de.marinus.objectdebugger.objects;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SimpleTestObject {

    private String name;
    private int age;
    private boolean isAdult;
    private SimpleTestObject child;

}