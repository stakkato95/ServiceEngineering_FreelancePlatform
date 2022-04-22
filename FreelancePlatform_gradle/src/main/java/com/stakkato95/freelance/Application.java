package com.stakkato95.freelance;

import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
//    5 platform-port instead of "8080"
//    6 db url in app injecten. DbConfig
}
