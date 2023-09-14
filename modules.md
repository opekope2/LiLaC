# Module LiLaC API

Documentation of the public LiLaC API.

## Adding dependency

LiLaC doesn't have a maven repository. You can install it from [Modrinth Maven](https://docs.modrinth.com/maven)
or [Curse Maven](https://www.cursemaven.com/).

LiLaC API is shipped as a separate JAR file from the LiLaC implementation, which can be embedded inside other JARs. Use
one of `modCompileOnly`, `modCompileOnlyApi`, `modImplementation` configurations in gradle to
add a dependency on LiLaC API JAR. The API JAR is not the main file.

To load the LiLaC implementation at runtime, add the main LiLaC JAR to the `modLocalRuntime` or `modRuntimeOnly`
configuration. I have no idea what the difference is, maybe ask it in the Fabric discord. I use `modLocalRuntime`.

### Which configuration

> Disclaimer: I'm not a Gradle expert.

| Configuration       | When to use                                                                                                       | LiLaC API available at   |
|---------------------|-------------------------------------------------------------------------------------------------------------------|--------------------------|
| `modCompileOnly`    | When you only use those parts of LiLaC API, which are only called by LiLaC (for example, the resource loader API) | Compile time             |
| `modCompileOnlyApi` | Same as `modCompileOnly`, but use this, if your code exposes part of LiLaC API                                    | Compile time             |
| `modImplementation` | When you directly use LiLaC API annotated with `@RequiresImplementation`                                          | Compile time and runtime |

## `fabric.mod.json`

If you use LiLaC API, you should add it to one of the dependencies blocks. LiLaC API provides `lilac-api`, and LiLaC
(implementation) provides `lilac` and `lilac-api`.

Some guidelines what to put in which dependency block

| Scenario                                                              | What to put in which dependency block         | What to embed |
|-----------------------------------------------------------------------|-----------------------------------------------|---------------|
| You use LiLaC API unconditionally (required dependency)               | Put `lilac` in `depends`                      | Nothing       |
| You use LiLaC API conditionally (optional dependency)                 | Put `lilac-api` in `depends`                  | LiLaC API JAR |
| You only use those parts of LiLaC API, which are only called by LiLaC | Put `lilac-api` in `recommends` or `suggests` | Nothing       |

If you want LiLaC to call your API, then go to you probably want LiLaC as a required dependency.

> It is recommended to add a dependency on the minor version of LiLaC (API) to avoid crashes caused by breaking changes
> in LiLaC. This way, Fabric will refuse to load likely incompatible mods.
>
> For example, `"lilac": "~1.0.0-beta.2"` means `>=1.0.0-beta.2 <1.1.0`

## Embedding LiLaC

You may embed LiLaC API to prevent crashes caused by missing classes.

You can find some guidelines in the previous table.

> Avoid embedding LiLaC (implementation).

# Package opekope2.lilac.annotation

Contains every LiLaC annotation available for public consumption.

These have multiple purposes, including

* Adding metadata to classes or members, which are used to interact with LiLaC API
* Marking LiLaC API classes or members, which require the LiLaC implementation JAR to be installed (not just the API
  JAR)

# Package opekope2.lilac.api

Contains the core LiLaC API class.

# Package opekope2.lilac.api.fabric

Contains a DFU codec for
[VersionPredicate](https://maven.fabricmc.net/docs/fabric-loader-0.14.22/net/fabricmc/loader/api/metadata/version/VersionPredicate.html).

# Package opekope2.lilac.api.fabric.mod_json

Contains utilities related to deserializing custom metadata from `fabric.mod.json`.

# Package opekope2.lilac.api.registry

Wrapper code around Minecraft registry providing lookup functionality across multiple Minecraft versions.

# Package opekope2.lilac.api.resource

Wrapper interfaces around Minecraft resources and resource packs.

# Package opekope2.lilac.api.resource.loading

LiLaC resource loading API.

LiLaC resource loading API implements the
[asynchronous resource loader](https://fabricmc.net/wiki/tutorial:resource) in Minecraft.

# Package opekope2.lilac.api.tick

Externally trigger-able game tick handling event.

# Package opekope2.lilac.exception

Exceptions thrown by LiLaC.

You may throw exceptions from this package.

# Package opekope2.lilac.util

Various utility functions, which are not necessarily related to Fabric or Minecraft.

These utilities are available using the API JAR, and don't require LiLaC implementation.
