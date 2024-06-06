plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.5"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"

}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17

}

repositories {
    mavenCentral()

}

dependencies {
    // https://mvnrepository.com/artifact/jakarta.validation/beanvalidation-standalone-container-adapter
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.0")
    implementation("org.webjars:bootstrap:5.1.3")
    implementation("org.webjars:webjars-locator-core")
    implementation("org.webjars.npm:bootstrap-icons:1.7.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.3")
    testImplementation("io.mockk:mockk:1.13.9")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // DB
    implementation("org.flywaydb:flyway-mysql")
    implementation("mysql:mysql-connector-java:8.0.28")
    implementation("org.mariadb.jdbc:mariadb-java-client:2.4.4")
    implementation("org.mybatis:mybatis:3.5.16")
    runtimeOnly("com.mysql:mysql-connector-j")

    implementation("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")

}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }

}

tasks.withType<Test> {
    useJUnitPlatform()
}
