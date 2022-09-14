package main.hibe.annotations;

import main.hibe.testStuff.TestEntity;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

public class AnnotationProcessor {
    public static void main(String[] args) throws IOException {
        TestEntity t = new TestEntity();
        t.toString();
        AnnotationProcessor a = new AnnotationProcessor();
        a.getAllAnnotatedClasses("main/hibe/");
    }

    public void getAllAnnotatedClasses(String basePackage) throws IOException {
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            String path = basePackage.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File file = new File(resource.toURI());

                for (File classFile : file.listFiles()) {
                    String filename = classFile.getName();

                    System.out.println(filename);



                    if (filename.endsWith(".class")){
                        Class classObject = Class.forName(filename);

                    if (classObject.isAnnotationPresent(Existence.class)) {
                        System.out.println("Existence " + filename);
                    }}
                }
            }
        } catch (IOException | URISyntaxException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
