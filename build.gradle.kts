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
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
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

    embed(files("../../libs/polyui-nanovg-impl-0.20.0.jar"))
    embed(files("../../libs/polyui-0.20.0.jar"))


//    // Add LWJGL modules' native bindings to the test runtime
//    val nativePlatforms = listOf("windows", "linux", "macos", "macos-arm64")
//    val modules = listOf(/*"lwjgl",*/ "lwjgl-nanovg", "lwjgl-opengl", "lwjgl-glfw", "lwjgl-stb")
//    modules.forEach { module ->
//        nativePlatforms.forEach { platform ->
//            embed("org.lwjgl:$module:3.3.2:natives-$platform")
//            compileOnly("org.lwjgl:$module:3.3.2")
//        }
//    }
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