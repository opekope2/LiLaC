# Lib Lapis Core

[![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/opekope2/LiLaC?include_prereleases&label=Download+from+GitHub&logo=github)](https://github.com/opekope2/OptiGUI/releases)
[![Modrinth Version](https://img.shields.io/modrinth/v/lilac?label=Download+from+Modrinth&logo=modrinth)](https://modrinth.com/mod/lilac/versions)
[![CurseForge Download](https://img.shields.io/badge/Download_from_CurseForge-uhh..._latest_I_guess%3F-E04E14?logo=curseforge)](https://www.curseforge.com/minecraft/mc-mods/lilac/files)
![GitHub top language](https://img.shields.io/github/languages/top/opekope2/LiLaC?color=7F52FF&logo=kotlin)
[![Buy me a coffee](https://img.shields.io/badge/Buy%20me%20a%20coffe-Ko--fi-f16061?logo=ko-fi)](https://ko-fi.com/opekope2)

Welcome to LiLaC documentation. LiLaC is a library mod to wrap parts of Minecraft used by my other mod(s) with a compatibility layer to ensure binary compatibility with multiple versions of the game.

## APIs

LiLaC aims to provide support for its APIs for minecraft versions 1.18 and later (not including snapshots).

The documentation is being written. Until then, here's the overview of the APIs in LiLaC you as a developer can use:

* Resource loader - a wrapper around Minecraft's asynchronous Resource Loader API ([Fabric docs](https://fabricmc.net/wiki/tutorial:resource)). Support will be added for Minecraft versions back until 1.18
* A codec for [VersionPredicate](https://maven.fabricmc.net/docs/fabric-loader-0.14.22/net/fabricmc/loader/api/metadata/version/VersionPredicate.html) in Fabric Loader API
* `fabric.mod.json` custom metadata deserializer (and serializer)
* Some Minecraft Registry lookup API
* A wrapper for game tick

Some planned APIs:

* A wrapper for [DrawContext](https://maven.fabricmc.net/docs/yarn-1.20+build.1/net/minecraft/client/gui/DrawContext.html) (Minecraft 1.20+) and its predecessor, [DrawableHelper](https://maven.fabricmc.net/docs/yarn-1.19.4+build.2/net/minecraft/client/gui/DrawableHelper.html)
* A wrapper for [Toast](https://maven.fabricmc.net/docs/yarn-1.20+build.1/net/minecraft/client/toast/Toast.html) (because of DrawContext)
