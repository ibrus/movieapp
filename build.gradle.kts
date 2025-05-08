plugins {
    base
}

allprojects {
    group = "com.pet"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()

        /**
         * Loads environment variables from .env file before test execution.
         * This allows tests to access environment-specific configuration like:
         * - Database connection details
         * - API keys
         * - Service endpoints
         * - Other environment-dependent settings
         * 
         * The .env file should be in the root project directory and contain
         * key-value pairs in the format: KEY=value
         * Lines starting with # are treated as comments and ignored.
         */
        doFirst {
            rootProject.file(".env").takeIf { it.exists() }?.forEachLine { line ->
                val trimmed = line.trim()
                if (trimmed.isNotEmpty() && !trimmed.startsWith("#") && trimmed.contains("=")) {
                    val parts = trimmed.split("=", limit = 2)
                    val key = parts[0].trim()
                    val value = parts.getOrElse(1) { "" }.trim()
                    environment(key, value)
                }
            }
        }
    }
} 
