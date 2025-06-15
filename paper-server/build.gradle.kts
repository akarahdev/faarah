plugins {
    `java-library`
    id("java")
    id("io.papermc.paperweight.core") version "2.0.0-beta.17"
}

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
}

group = "dev.akarah"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    mache("io.papermc:mache:1.21.5+build.3")
    paperclip("io.papermc:paperclip:3.0.3")
}

tasks.test {
    useJUnitPlatform()
}

paperweight {
    minecraftVersion = "1.21.5"
    gitFilePatches = false

    reobfPackagesToFix.addAll(
        "net.minecraft"
    )

    spigot {
        buildDataRef = "702e1a0a5072b2c4082371d5228cb30525687efc"
        packageVersion = "v1_21_R4" // also needs to be updated in MappingEnvironment
    }
}

// borrowed and modified from PaperMC/Paper github
fun TaskContainer.registerRunTask(
    name: String,
    block: JavaExec.() -> Unit
): TaskProvider<JavaExec> = register<JavaExec>(name) {
    group = "runs"
    mainClass.set("net.minecraft.server.Main")
    standardInput = System.`in`
    workingDir = rootProject.layout.projectDirectory.dir("run").asFile
    javaLauncher.set(project.javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(24))
    })

    args("--nogui")
    minHeapSize = "2G"
    maxHeapSize = "2G"

    doFirst {
        workingDir.mkdirs()
    }

    block(this)
}

tasks.registerRunTask("runServer") {
    description = "Spin up a test server from the Mojang mapped server jar"
    classpath(tasks.includeMappings.flatMap { it.outputJar })
    classpath(configurations.runtimeClasspath)
}
