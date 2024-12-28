import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("maven-publish")
}

group = "dev.kyriji"
version = "0.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("net.minestom:minestom-snapshots:9803f2bfe3")
    compileOnly("dev.hollowcube:schem:1.3.1")
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "dev.kyriji.tritonstom.TritonStom"
        )
    }
}

tasks.named<ShadowJar>("shadowJar") {
    archiveClassifier.set("")
}

publishing {
    publications {
        create<MavenPublication>("shadow") {
            project.shadow.component(this)

            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
        }
    }
}