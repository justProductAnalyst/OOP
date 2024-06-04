plugins {
    jacoco
    groovy
    application
}

jacoco {
    toolVersion = "0.8.9"
    reportsDirectory.set(layout.buildDirectory.dir("reports/jacoco"))
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.28")
    implementation("org.slf4j:slf4j-simple:2.0.13")
    annotationProcessor("org.projectlombok:lombok:1.18.28")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.guava:guava:32.1.1-jre")
    implementation("org.eclipse.jgit:org.eclipse.jgit:6.9.0.202403050737-r")
    implementation("org.gradle:gradle-tooling-api:7.3-20210825160000+0000")
    implementation("org.slf4j:slf4j-api:2.0.13")
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("org.codehaus.groovy:groovy-all:3.0.21")
    implementation("org.jsoup:jsoup:1.17.2")
    implementation("org.thymeleaf:thymeleaf:3.1.2.RELEASE")
    implementation("com.puppycrawl.tools:checkstyle:10.15.0")
    implementation("com.github.stefanbirkner:system-lambda:1.2.1")
}

sourceSets {
    main {
        groovy {
            srcDirs(listOf("src/main/groovy", "src/main/java"))
        }
        java {
            srcDirs() // don't compile Java code twice
        }
    }
    test {
        groovy {
            srcDirs(listOf("src/test/groovy", "src/test/java"))
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("ru.nsu.kuklin.dsl.Application")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true) // Add HTML report for easier debugging
    }
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.withType<JavaExec> {
    standardInput = System.`in`
}

