package hibe.annotations.processor;

import java.net.URL;

public class AnnotationProcessor {
    private static String s;
    public AnnotationProcessor(String s) {
        AnnotationProcessor.s = s;
    }

    static {
        URL a = Thread.currentThread().getContextClassLoader().getResource("scanner");;
    }


}

