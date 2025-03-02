plugins {
    id("org.springframework.boot")
    java
}

dependencies {
    implementation(project(":adapters:web"))
    implementation(project(":adapters:persistence"))
    implementation(project(":adapters:messaging"))
    implementation("org.springframework.boot:spring-boot-starter")
}

springBoot {
    mainClass.set("com.example.ExampleApp")
}