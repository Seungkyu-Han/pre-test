plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "pre-test"
include("domain")
include("persistence")
include("presentation")
include("container")
