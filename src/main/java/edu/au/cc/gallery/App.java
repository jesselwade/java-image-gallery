/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.au.cc.gallery;

import java.sql.SQLException;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) throws Exception {
       // System.out.println(new App().getGreeting());
       DB.demo();

    }
}