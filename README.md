# AetherClient
.gradle/
build/
out/
.idea/
*.iml
run/

plugins {
    id 'net.fabricmc.fabric-loom-remap' version "${loom_version}"
    id 'maven-publish'
}

version = project.mod_version
group = project.maven_group
base { archivesName = project.archives_base_name }

repositories { }

loom { splitEnvironmentSourceSets() }

dependencies {
    minecraft "com.mojang:minecraft:${project.minecraft_version}"
    mappings "net.fabricmc:yarn:${project.yarn_mappings}:v2"
    modImplementation "net.fabricmc:fabric-loader:${project.loader_version}"
    modImplementation "net.fabricmc.fabric-api:fabric-api:${project.fabric_version}"
}

processResources {
    inputs.property 'version', project.version
    filesMatching('fabric.mod.json') { expand 'version': project.version }
}

tasks.withType(JavaCompile).configureEach { it.options.release = 21 }
java { withSourcesJar(); sourceCompatibility = JavaVersion.VERSION_21; targetCompatibility = JavaVersion.VERSION_21 }

jar { from('LICENSE') { rename { "${it}_${project.archives_base_name}" } } }

org.gradle.jvmargs=-Xmx3G
org.gradle.parallel=true
minecraft_version=1.21.11
loader_version=0.18.4
loom_version=1.14-SNAPSHOT
yarn_mappings=1.21.11+build.4
fabric_version=0.141.3+1.21.11
mod_version=1.0.0
maven_group=com.aetherclient
archives_base_name=aether-client

MIT License

Copyright (c) 2026 Aether Client

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.

# Aether Client

A client-side Fabric 1.21.11 project designed for Modrinth/Fabric installations.

## Features
- Polished dark ClickGUI, opened with **Right Shift**.
- Storage Finder: highlights chests, trapped chests, barrels, shulker boxes, hoppers, droppers and dispensers.
- Spawner Finder: highlights mob spawners.
- Per-module settings for enabled state, render mode, range, outline opacity, fill opacity, RGB color and line width.
- JSON config saved to `.minecraft/config/aetherclient.json`.
- No server-side installation required.

## Build
1. Install JDK 21.
2. Open this folder in IntelliJ IDEA or VS Code with the Gradle extension.
3. Run `./gradlew build`.
4. Put `build/libs/aether-client-1.0.0.jar` into your Fabric `mods` folder alongside Fabric API.

Fabric's 1.21.11 documentation recommends JDK 21; Fabric API 0.141.x has a 1.21.11 release. The project uses the remapping Loom plugin appropriate for 1.21.11.

pluginManagement { repositories { maven { name = 'Fabric'; url = 'https://maven.fabricmc.net/' }; gradlePluginPortal(); mavenCentral() } }
dependencyResolutionManagement { repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS); repositories { maven { name = 'Fabric'; url = 'https://maven.fabricmc.net/' }; mavenCentral() } }
rootProject.name='AetherClient'
