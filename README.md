# Faarah
Faarah is an experimental new API for creating Minecraft servers.

# Biggest Differences
While similar to Spigot and Paper's API, there are a few fundamental differences.

## Breakages
Faarah handles breakages a bit differently. Instead of focusing on complete stability, APIs are assigned a Lifecycle.

Lifecycles generally have five tiers:
- Stable: This API will generally be consistent across versions, unless a major rework happens.
- Unstable: While somewhat stable, this API is broken frequently.
- Experimental: This API is due to be changed at any time for any reason.
- Deprecated: This API will be removed in a future update. Try to avoid usages of these APIs where possible.
- Internal: This API is not to be relied on by plugins. You can if you want, but you open yourself up to breakages.

No Lifecycle guarantees stability, because ever since 1.20 or so, Minecraft has been undergoing constant technological changes.
Faarah aims to let developers use new things Minecraft adds as soon as possible, rather than being stuck on legacy APIs.

## Components
While in PaperMC, only items are representable through components, in Faarah, almost *everything* is made out of components. This includes:
- Blocks
- Items
- Entities
- Probably more!

These are generally represented by a `DataComponentMap` interface. It's equivalent to a `Map<DataComponentType<?>, ?>` but with extra type-safety
checks and compile-time casts.

## Java 24
Unlike vanilla Minecraft, Faarah will always try to run on the latest Java version physically possible without breaking 
everything. And, since we have a Lifecycle system allowing breakages, we can use more modern Java alternatives:
- Schedulers and tasks are represented using a structured concurrency system.
- [Data](https://inside.java/2024/05/23/dop-v1-1-introduction/) is generally represented as a mix of records & sealed interfaces where applicable.
- Faarah's APIs will generally lean more towards functional programming & immutability than previous APIs.
- Faarah's plugins must use [Java's Module System](https://dev.java/learn/modules/) introduced in Java 9. 