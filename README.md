# keylight-cli

An experimental CLI to control the Elgato KeyLight Air that #WorksOnMyMachine

Inspired by https://gitlab.com/obviate.io/pyleglight/

## CLI usage (for native image & native package)

```text
Usage: keylight [COMMAND]
Commands:
  on
  off
  brightness
  color
```

```text
Usage: keylight brightness <brightness>
      <brightness>   the brightness to setup (value must be between 0 and 100)
```

```text
Usage: keylight color <color>
      <color>   the color temperature to set (value must be between 2900 and 7000)
```

## building & running

### a standalone fat-jar

```shell
mvn package
```

then run it with

```shell
java -jar target/keylight-cli-1.0.0-jar-with-dependencies.jar <command>
```

### a native image

(requires GraalVM & native-image installed)

```shell
mvn package -P native-image
```

then run it with

```shell
./target/keyligh <command>
```

then put the native image in your `$PATH`

### building a native package (.deb)

(requires at least a JDK 14)

```shell
mvn package

mkdir target/dist

cp target/keylight-cli-1.0.0-jar-with-dependencies.jar target/dist

jpackage  \
  --input target/dist \
  --dest target/dist \
  --name keylight-cli \
  --app-version 1.0.0 \
  --main-jar keylight-cli-1.0.0-jar-with-dependencies.jar \
  --type deb
```

then install it

```shell
sudo dpkg -i target/dist/keylight-cli_1.0.0-1_amd64.deb
```

the CLI will be installed in /opt/keylight-cli

then run it with 

```shell
/opt/keylight-cli/bin/keylight-cli <command>
```

## dependencies

* picocli : to implement CLI features
* jmdns : to implement service discovery of the keylight

## disclaimer

It may not work with other devices.

When having multiple devices, the first found by the service discovery will be used.