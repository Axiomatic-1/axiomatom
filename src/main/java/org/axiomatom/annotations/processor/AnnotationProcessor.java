package org.axiomatom.annotations.processor;

import org.axiomatom.annotations.scanner.AnnotationScanner;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("java:S1118")
public class AnnotationProcessor {

    static {
        Logger logger = Logger.getLogger(AnnotationProcessor.class.getName());
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String arr = Arrays.toString(new StackTraceElement[]{stackTrace[stackTrace.length - 1]});
        String[] split = arr.split("\\.");
        String root = split[0].substring(1) + "." + split[1];
        AnnotationScanner p = new AnnotationScanner();
        logger.log(Level.INFO, p.getAnnotatedClasses(root).toString());
    }
}

