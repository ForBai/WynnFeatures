import gg.essential.gradle.util.noServerRunConfigs

plugins {
    kotlin("jvm")
    id("gg.essential.multi-version")
    id("gg.essential.defaults")
}

val modGroup: String by project
val modBaseName: String by project
group = modGroup
base.archivesName.set("$modBaseName-${platform.mcVersionStr}")

kotlin {
    //set kotlin compile target version to 1.7
    sourceSets.all {
        languageSettings.apply {
            languageVersion = "2.0"
            apiVersion = "2.0"
        }
    }
}

loom {
    noServerRunConfigs()
    mixin {
        defaultRefmapName.set("wynnfeatures.mixins.refmap.json")
    }
    launchConfigs {
        getByName("client") {
            property("fml.coreMods.load", "me.anemoi.wynnfeatures.core.CoreMod")
            property("patcher.debugBytecode", "true")
            property("mixin.debug.verbose", "true")
            property("mixin.debug.export", "true")
            property("mixin.dumpTargetOnFailure", "true")
            arg("--tweakClass", "gg.essential.loader.stage0.EssentialSetupTweaker")
            arg("--mixin", "wynnfeatures.mixins.json")
        }
    }
}

repositories {
    mavenCentral()
    maven("https://repo.essential.gg/repository/maven-public/")
    maven("https://repo.spongepowered.org/repository/maven-public/")
//    maven("https://raw.githubusercontent.com/kotlin-graphics/mary/master")
    maven("https://jitpack.io")
    maven("https://repo.polyfrost.cc/releases/")
}

val embed by configurations.creating
configurations.implementation.get().extendsFrom(embed)

dependencies {
    compileOnly("gg.essential:essential-${platform}:4246+g8be73312c")
    embed("gg.essential:loader-launchwrapper:1.1.3")

    compileOnly("org.spongepowered:mixin:0.8.5-SNAPSHOT")
    //okhttp
    embed("com.squareup.okhttp3:okhttp:4.9.0")
    //slf4j
    embed("org.slf4j:slf4j-api:1.6.1")


}

tasks.compileKotlin {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xno-param-assertions",
            "-Xjvm-default=all-compatibility"
        )
    }
}

tasks.processResources {
    rename("(.+_at.cfg)", "META-INF/$1")
}

tasks.jar {
    from(embed.files.map { zipTree(it) })

    manifest.attributes(
        mapOf(
            "FMLCorePlugin" to "me.anemoi.wynnfeatures.core.CoreMod",
            "ModSide" to "CLIENT",
            "FMLCorePluginContainsFMLMod" to "Yes, yes it does",
            "TweakClass" to "gg.essential.loader.stage0.EssentialSetupTweaker",
            "TweakOrder" to "0",
            "MixinConfigs" to "wynnfeatures.mixins.json"
        )
    )
}



/*
//    embed(files("../../libs/polyui-nanovg-impl-0.23.0.jar"))
//    embed(files("../../libs/polyui-0.23.0.jar"))

    embed("io.github.spair:imgui-java-app:1.86.10") /*{
        exclude("org.lwjgl", "lwjgl")
    }*/
//    embed("io.github.spair:imgui-java-lwjgl3:1.86.10")
    embed("io.github.spair:imgui-java-binding:1.86.10")
    arrayOf("windows", "linux", "macos").forEach {
        embed("io.github.spair:imgui-java-natives-$it:1.86.10")
//        runtimeOnly("org.lwjgl:lwjgl:3.2.3:natives-$it")
//        embed(files("../../libs/lwjgl-natives-$it.jar"))
    }
    arrayOf("", "-opengl", "-glfw").forEach {
        runtimeOnly("org.lwjgl:lwjgl$it:3.3.1")
//        embed("org.lwjgl:lwjgl$it::natives-linux")
    }
 */