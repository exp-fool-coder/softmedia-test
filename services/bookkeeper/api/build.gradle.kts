import com.google.protobuf.gradle.*

plugins {
    java
    id("com.google.protobuf")
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "com.expfool.bookkeeper"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.grpc:grpc-protobuf:1.58.0")
    implementation("io.grpc:grpc-stub:1.58.0")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("net.devh:grpc-client-spring-boot-starter:2.15.0.RELEASE")
    compileOnly("jakarta.annotation:jakarta.annotation-api:1.3.5")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.23.4"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.58.0"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc") {}
            }
        }
    }
}