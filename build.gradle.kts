plugins {
    id("org.springframework.boot") version "3.2.2" apply false
    id("io.spring.dependency-management") version "1.1.4"
    java
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    }


    repositories {
        mavenCentral()
    }
}
