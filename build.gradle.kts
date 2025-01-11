plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.lmh"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // mysql
    runtimeOnly("com.mysql:mysql-connector-j")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("io.rest-assured:rest-assured:5.5.0")
    runtimeOnly("com.h2database:h2")
}

tasks.test {
    useJUnitPlatform()
}

/**
 * QueryDsl Build Option
 */

// QueryDsl Q 객체 저장 장소
val queryDslDir = "${layout.projectDirectory}/build/generated/querydsl"

sourceSets{
    getByName("main").java.srcDirs(queryDslDir)
}

// JavaCompile Task 실행 시, 어디에 파일을 넣어 줄지
tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory = file(queryDslDir)
}

// clean 실행 시, queryDsl 파일도 삭제
tasks.named("clean") {
    doLast {
        file(queryDslDir).deleteRecursively()
    }
}