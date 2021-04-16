# Validieren einer VdS UE Konfiguration

Das Format wird mit der XML Schema Datei ueconfig.xsd validiert

# Erstellen der Java Klassen

Nach einer Änderung des Schemas müssen jedesmal die Klassen neu erstellt werden.

## Mit IntelliJ

Tools -> JAXB -> Generate Java Code from XSD Schema Using JAXB...

src/com/vds/ueconfig

## Mit xjc (Ein Java 8 SDK vorausgesetzt)

`xjc ueconfig.xsd -p com.vds.ueconfig -d src`

# Beispiel Code

In der Klasse com/vds/Main befindet sich der Bespielcode.

## compilieren mit Java 

`javac -cp .\src -d out src\com\vds\Main.java`

Im Directoy out werden die kompilierten Klassen abgelegt

## Zum Validieren einer Konfig mit dem Namen ueconfig.xml

`java -cp out com/vds/Main bsp/ueconfig.xml` Beispiel einer erfolgreichen Validierung

oder

`java -cp out com/vds/Main bsp/ueconfig_invalid.xml` Beispiel einer fehlerhaften Konfiguration

---
## Optional: generieren eines JAR Files

`jar cvfm classes.jar src/MANIFEST.MF  -C out .`

`java -jar classes.jar bsp/ueconfig.xml`

# Weitere Dateien

ueconfig.xsd.xml. Diese Datei wurde mit IntelliJ ab dem Schema erstellt.

Tools -> XML Actions -> Generate XML Document from XSD Schema...

Es wird eine schemakonforme Beispieldatei erstellt. 

---

Author: Martin Pfander
Firma: Sitasys AG
