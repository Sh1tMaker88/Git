package com;

import com.annotations.ClassToInjectProperty;
import com.menu.MenuController;
import com.propertyInjector.ClassScanner;
import com.service.BookService;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import com.serialization.*;

import java.util.Set;

public class Starter {
    public static void main(String[] args) {
        ClassScanner classScanner = ClassScanner.getInstance();
        System.out.println(classScanner.scanForClasses());

        new Initializer();
        new Deserializator();
        MenuController.getInstance().run();
        new Serializator();
    }
}