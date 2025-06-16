plugins {
    id("java")
}

group = "dev.akarah"
version = "unspecified"

dependencies {
    compileOnly(project(":faarah-api"))
}