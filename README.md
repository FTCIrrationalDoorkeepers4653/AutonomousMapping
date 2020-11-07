# AutonomousMapping

Autonomous Mapping Application for FTC teams

## Installation:

You can either download and use this repo or use an installation Software (i.e <i>Maven</i> or <i>Gradle</i>).

Maven:
```XML
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
  
<dependencies>
  <dependency>
    <groupId>com.github.FTCIrrationalDoorkeepers4653</groupId>
    <artifactId>AutonomousMapping</artifactId>
    <version>1.0</version>
  </dependency>
</dependencies>  
```

Gradle:
```Java
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
  
dependencies {
  implementation 'com.github.FTCIrrationalDoorkeepers4653:AutonomousMapping:Tag'
}
```

## Usage:

To draw your path on the canvas, just click once on the start position, and then on the end position. A purple line will appear on the screen, indicating your path. To move the screen around, just click and drag anywhere on your computer screen. The calculated information will appear on another white-colored screen that can also be dragged. The information will update as you change your path. Lastly, to close the application just press "e" when either screen is open.