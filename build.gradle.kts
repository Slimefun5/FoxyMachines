plugins {
    java
    id("com.gradleup.shadow") version "9.3.2"
    id("io.github.intisy.github-gradle") version "1.8.3"
}

group = "me.gallowsdove"
description = "FoxyMachines is a Slimefun addon that adds tools, machines, items, armor, weapons, bosses and more!"

apply(from = "https://raw.githubusercontent.com/Slimefun5/gradle/stable/slimefun-addon.gradle")

dependencies {
    githubImplementation("Slimefun5:InfinityLib:v1.3.13")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    compileOnly("com.google.code.gson:gson:2.10.1")
    // AuraSkills is a softdepend; published to Maven Central (no extra repo needed).
    compileOnly("dev.aurelium:auraskills-api-bukkit:2.3.12")
}

tasks {
    shadowJar {
        relocate("io.github.mooy1.infinitylib", "me.gallowsdove.foxymachines.infinitylib")
        minimize()
        exclude("io/github/thebusybiscuit/slimefun5/**")
        exclude("me/mrCookieSlime/**")
    }
}
