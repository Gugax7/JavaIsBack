package com.ggx.kipper_spring_project.exceptions;

public class PathNotFoundException extends RuntimeException {
    public PathNotFoundException(){super("Path Not Found!");}
    public PathNotFoundException(String message) {
        super(message);
    }
}
