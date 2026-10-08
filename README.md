# ProgrII_1erPar

# Sistema de Turnos Médicos (Programación II)

## Compilar y ejecutar (desde la raíz del proyecto, en la terminal)

    mkdir -p out
    javac -encoding UTF-8 -d out $(find src -name "*.java")
    java -cp out com.guiraldes.main.Principal

La clase principal es `com.guiraldes.main.Principal`.
Los archivos `pacientes.txt`, `doctores.txt` y `turnos.xml` se generan/leen en la raíz del proyecto.
