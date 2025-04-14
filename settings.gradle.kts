pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "movieapp"

include(":user-service")
project(":user-service").projectDir = file("backend/user-service")

include(":movie-service")
project(":movie-service").projectDir = file("backend/movie-service")

include(":recommendation-service")
project(":recommendation-service").projectDir = file("backend/recommendation-service")

include(":notification-service")
project(":notification-service").projectDir = file("backend/notification-service")

include(":event-streaming")
project(":event-streaming").projectDir = file("backend/event-streaming")

include(":common")
project(":common").projectDir = file("backend/common")
