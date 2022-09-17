package org.axiomatom.annotations.scanner;

import org.axiomatom.annotations.Existence;
import org.axiomatom.annotations.processor.AnnotationProcessor;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnnotationScanner {
    private final Logger logger = Logger.getLogger(AnnotationScanner.class.getName());

    public static void main(String[] args) {
        AnnotationProcessor p = new AnnotationProcessor();
    }

    /**
     * Iterate throw all classes and filter classes with required annotations
     *
     * @param packageName The base package
     * @return The annotated classes
     */
    public Map<String, Class<?>> getAnnotatedClasses(String packageName) {
        Map<String, Class<?>> classes = new HashMap<>();
        for (Class<?> clazz :
                getClasses(packageName)) {
            if (clazz.isAnnotationPresent(Existence.class)) {
                classes.put(clazz.getName(), clazz);
            }
        }
        return classes;
    }

    /**
     * Scans all classes accessible from the context class loader which belong
     * to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     */
    private List<Class<?>> getClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);
            Set<File> dirs = new HashSet<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                URI uri = new URI(resource.toString());
                dirs.add(new File(uri.getPath()));
            }

            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return classes;
    }

    /**
     * Recursive method used to find all classes in a given directory and
     * subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     */
    private List<Class<?>> findClasses(File directory, String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files != null) {

            for (File file : files) {
                if (file.isDirectory()) {
                    classes.addAll(findClasses(file, packageName + '.' + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    try {
                        classes.add(Class.forName(
                                packageName + '.' + file.getName().substring(0, file.getName().length() - 6))
                        );
                    } catch (ClassNotFoundException e) {
                        logger.log(Level.WARNING, "Looks like there is no Any classes : {}", e.getMessage());
                    }
                }
            }

        }
        return classes;
    }
}

















