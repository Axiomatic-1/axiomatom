package hibe.teststuff;

import hibe.annotations.Existence;
import hibe.annotations.processor.AnnotationProcessor;

@Existence
public class Kurama {
    private String id;
    private String name;

    public static void main(String[] args) {
        AnnotationProcessor a = new AnnotationProcessor("hello");
    }
}
