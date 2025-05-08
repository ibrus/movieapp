plugins {
    id("java-library")
    id("io.spring.dependency-management") version "1.1.7"
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.4.4")
    }
}

group = "com.pet"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security:3.4.4")
    implementation("org.springframework.boot:spring-boot-starter:3.4.4")
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.4")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("software.amazon.awssdk:s3:2.31.21")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")

    compileOnly("jakarta.servlet:jakarta.servlet-api:6.0.0")
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.4")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
}

tasks.named<Jar>("jar") {
    enabled = true
}
