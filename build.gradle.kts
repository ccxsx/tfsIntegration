plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.13.2"
}


group = "org.jetbrains"
version = "TFS"

sourceSets {
    main {
        java.srcDirs ("src")
        resources.srcDir ("resources")
    }
    test {
        java.srcDir ("tests")
        resources.srcDir ("testData")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(fileTree("lib"))
    testImplementation("junit:junit:4.12")
    testImplementation( fileTree("test-lib"))
}

intellij {
    version.set("2022.1.4")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))

//    prepareSandbox() {
//        from('lib/native') {
//            into ("${intellij.pluginName}/lib/native")
//        }
//    }
}


tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("221")
        untilBuild.set("231.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
