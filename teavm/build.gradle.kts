plugins {
    id("java")
    id("org.gretty") version("3.1.0")
}

gretty {
    contextPath = "/"
    extraResourceBase("build/dist/webapp")
}

dependencies {
    implementation(project(":core"))
    val gdxVersion = project.property("gdxVersion") as String
    val gdxTeaVMVersion = project.property("gdxTeaVMVersion") as String

    implementation("com.badlogicgames.gdx:gdx:$gdxVersion")
    implementation("com.github.xpenatan.gdx-teavm:backend-teavm:$gdxTeaVMVersion")
    implementation("io.github.monstroussoftware.gdx-webgpu:gdx-teavm-webgpu:-SNAPSHOT")

    implementation( project(":core"))
    //implementation(project(":tests:gdx-tests-webgpu2")) // module deleted
    //implementation(project(":tests:gdx-webgpu-tests"))
//    implementation(project(":backends:gdx-teavm-webgpu"))     // broken: could not resolve

    // Have Build errors
}

val mainClassName = "com.monstrous.test.teavm.BuildTeaVM"

tasks.register<JavaExec>("core-build") {
    group = "example-teavm"
    description = "Build teavm test example"
    mainClass.set(mainClassName)
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.register("core-run-teavm") {
    group = "example-teavm"
    description = "Run Test Demo example"
    val list = listOf("core-build", "jettyRun")
    dependsOn(list)

    tasks.findByName("jettyRun")?.mustRunAfter("core-build")
}
