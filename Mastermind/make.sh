#!/bin/bash
echo "Comença la compilació";
javac --release 11 -d ../EXE/ src/domini/*.java src/presentacion/*.java src/persistencia/*.java
jar cvfm Program.jar src/MANIFEST.MF -C ../EXE/ . 
echo "Compilacio finalitzada";



