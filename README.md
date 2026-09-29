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
