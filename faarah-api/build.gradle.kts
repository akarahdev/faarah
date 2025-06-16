plugins {
    id("java")
}

group = "dev.akarah"
version = "unspecified"

java {
    modularity.inferModulePath.set(true)
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.addAll(listOf("--module-path", classpath.asPath))
    classpath = files()
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://libraries.minecraft.net")
    }
}

dependencies {
    compileOnly("com.mojang:datafixerupper:8.0.16")
}
