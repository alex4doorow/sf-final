package com.sf.types;

public enum ENOperateTypes {
    ADD("Additional"),
    SUBTR("Subtract"),
    TRANSF("Transfer");

    private final String annotation;

    ENOperateTypes(String annotation) {
        this.annotation = annotation;
    }

    public String getAnnotation() {
        return annotation;
    }
}
