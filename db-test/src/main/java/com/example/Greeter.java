package com.example;

public class Greeter {

    public static void main(String[] args) {
        Greeter gr = new Greeter();
        gr.method1("Hello World!");
    }

    public String method1(String str) {
        System.out.println(str);
        return str;
    }

}
