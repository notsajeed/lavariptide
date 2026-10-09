# Lava Riptide

A Fabric mod for Minecraft 26.2 that lets **Riptide tridents work in lava**, not just in water or rain.

On a multiplayer server it only needs to be installed on the server. Players join with an unmodded client.

## What it does

Vanilla only lets you use a Riptide trident while you're in water or rain. This mod also allows it while you're in lava. Hold right-click, release, and you launch with the spin attack as you would in water.

It works by wrapping the `isInWaterOrRain()` check in `TridentItem` (in both `use` and `releaseUsing`) so that `isInLava()` counts too. The mod adds no items, blocks, or packets.

## Requirements

- Minecraft 26.2 (Java Edition)
- Fabric Loader 0.17.0 or newer
- Java 25
- Fabric API is **not** required

## How to use it on your computer

### Step 1: Get the jar

Pick one:

- **Download:** go to the [Releases page](https://github.com/notsajeed/lavariptide/releases) and download `lavariptide-1.0.0.jar`.
- **Build it yourself:** see [Building from source](#building-from-source) below.

### Step 2: Install Fabric (skip if you already have a Fabric 26.2 profile)

1. Download the installer from [fabricmc.net/use](https://fabricmc.net/use/installer/).
2. Run it, choose **Client**, set the game version to **26.2**, and click Install.
3. Open the Minecraft launcher and select the new **fabric-loader-26.2** profile.

Using Prism Launcher or MultiMC instead? Create an instance for 26.2 and choose Fabric as the mod loader.

### Step 3: Put the jar in your `mods` folder

| System          | Folder                                                               |
| --------------- | -------------------------------------------------------------------- |
| Windows         | `%appdata%\.minecraft\mods` (press `Win + R`, paste it, press Enter) |
| macOS           | `~/Library/Application Support/minecraft/mods`                       |
| Linux           | `~/.minecraft/mods`                                                  |
| Prism / MultiMC | right-click the instance, choose **Folder**, then open `mods`        |

If there's no `mods` folder, create one.

### Step 4: Play

Launch the Fabric profile and open any singleplayer world. The mod applies to every world, with nothing to enable. To turn it off, remove the jar from the `mods` folder.

### Using it on a server

Put the same jar in the server's `mods` folder and restart. The server must run Fabric Loader for 26.2 on Java 25. Players don't need to install anything.

## Building from source

You need JDK 25 and Git.

```
git clone https://github.com/notsajeed/lavariptide.git
cd lavariptide
./gradlew build
```

On Windows, use `gradlew.bat build`. The jar is written to `build/libs/lavariptide-1.0.0.jar`. Use that one, not the `-sources` jar.

To test in a dev client without installing anything:

```
./gradlew runClient
```

## Testing

In a creative world with cheats on:

```
/give @s trident[enchantments={riptide:3}]
/effect give @s fire_resistance infinite 0 true
```

Stand in lava, hold right-click, and release. You should launch. Fire resistance keeps you from burning to death while testing. Command syntax may differ slightly on 26.2.

## Known limitations

- **Vanilla clients on a server:** the client runs its own water check, so in lava you may not see the charge-up pose. The server still performs the launch.
- **Lava damage:** you still burn unless you have fire resistance.
- **Lava drag:** movement in lava is heavy, so you travel less far than in water.
- **Anticheat plugins:** they may flag the movement. Whitelist it if you run one.

## Troubleshooting

- **Mod doesn't seem to do anything:** make sure you launched the Fabric profile, not the vanilla one, and that the jar is directly inside `mods`, not in a subfolder.
- **Game crashes on startup with a mixin error:** the mixin uses `defaultRequire: 1`, so if the target method changes in a future version, the game fails loudly instead of silently doing nothing. Check the `isInWaterOrRain()` call sites in the decompiled `TridentItem` and update `TridentItemMixin`, or open an issue with your `logs/latest.log`.
- **Wrong Java version:** Minecraft 26.2 needs Java 25.

## Using the code in your own mod

The mod has no API, so there's nothing to add as a dependency. To get the same behavior in your own Fabric mod, copy `TridentItemMixin.java` into it and list it in your mixin config's `mixins` array.

## Project layout

```
src/main/java/com/example/lavariptide/
  LavaRiptide.java                 mod entrypoint (logs on load)
  mixin/TridentItemMixin.java      the lava check
src/main/resources/
  fabric.mod.json                  mod metadata
  lavariptide.mixins.json          mixin config
```

## License

MIT, as declared in `fabric.mod.json`. Add a `LICENSE` file if you publish the repo.
