import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.openapi.generator") version "7.12.0"
}

tasks.bootJar { enabled = false }
tasks.jar { enabled = true }

dependencies {
    implementation(project(":app"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.28")

    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    implementation("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.hibernate.validator:hibernate-validator:6.2.0.Final")
    implementation("org.openapitools:jackson-databind-nullable:0.2.0")

    implementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("org.instancio:instancio-junit:5.4.0")

}

buildscript {
    dependencies {
        classpath("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml") {
            version { strictly("2.14.2") }
        }
    }
}

tasks.register<GenerateTask>("generateMainApi") {
    generatorName.set("spring")
    inputSpec.set("${layout.projectDirectory}/src/main/resources/api-rest/openapi-rest.yml")
    outputDir.set("${layout.buildDirectory.get()}/generated/api-rest")
    apiPackage.set("com.example.adapter.web.api_rest.api")
    invokerPackage.set("com.example.adapter.web.api_rest.invoker")
    modelPackage.set("com.example.adapter.web.api_rest.model")
    modelNameSuffix.set("DTO")
    configOptions.set(
        mapOf(
            "dateLibrary" to "java8",
            "interfaceOnly" to "true",
            "useSpringBoot3" to "true"
        )
    )
}

val openApiClientSpecs = mapOf(
    "users" to "users-openapi-rest.yml"
)

openApiClientSpecs.forEach { (name, file) ->
    tasks.register<GenerateTask>("generate${name.replaceFirstChar { it.uppercaseChar() }}Api") {
        generatorName.set("java")
        inputSpec.set("${layout.projectDirectory}/src/main/resources/api-rest-clients/$file")
        outputDir.set("${layout.buildDirectory.get()}/generated/$name-api")
        apiPackage.set("com.example.adapter.web.api_rest_clients.$name.api")
        invokerPackage.set("com.example.adapter.web.api_rest_clients.$name.invoker")
        modelPackage.set("com.example.adapter.web.api_rest_clients.$name.model")
        modelNameSuffix.set("DTO")
        configOptions.set(
            mapOf(
                "library" to "restclient",
                "dateLibrary" to "java8",
                "useJakartaEe" to "true"
            )
        )
    }
}

tasks.compileJava {
    dependsOn(
        tasks.named("generateMainApi"),
        *openApiClientSpecs.keys.map { tasks.named("generate${it.replaceFirstChar { it.uppercaseChar() }}Api") }.toTypedArray()
    )
}

sourceSets {
    main {
        java {
            srcDirs(
                "src/main/java",
                "${layout.buildDirectory.get()}/generated/api-rest/src/main/java",
                *openApiClientSpecs.keys.map { "${layout.buildDirectory.get()}/generated/$it-api/src/main/java" }.toTypedArray()
            )
        }
    }
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
    }
}