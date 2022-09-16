package annotations;

import teststuff.TestEntity;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

public class AnnotationProcessor {
    Logger logger = Logger.getLogger(AnnotationProcessor.class.getName());

    public static void main(String[] args) {
        AnnotationProcessor a = new AnnotationProcessor();
        System.out.println(a.getExistence(TestEntity.class));
    }

    private Map<Class<?>, Class<?>> getAllAnnotatedClasses(String basePackage) {
        Map<Class<?>, Class<?>> existence = new HashMap();
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();

            String path = basePackage.replace("/", ".");
            Enumeration<URL> resources = classLoader.getResources(basePackage);

            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File file = new File(resource.toURI());

                for (File classFile : Objects.requireNonNull(file.listFiles())) {
                    String filename = classFile.getName();
                    if (filename.endsWith(".class")) {
                        String className = filename.substring(0, filename.lastIndexOf('.'));
                        Class<?> classObject = Class.forName(path + '.' + className);
                        if (classObject.isAnnotationPresent(Existence.class)) {
                            existence.put(classObject, classObject);
                            logger.info("Existence " + classObject.getName());
                        }
                    }
                }
            }
        } catch (IOException | URISyntaxException | ClassNotFoundException e) {
            logger.info(e.getMessage());
        }
        return existence;
    }

    public Class<?> getExistence(Class<?> cl) {
        return getAllAnnotatedClasses("main/hibe/teststuff").get(cl);
    }


}

















