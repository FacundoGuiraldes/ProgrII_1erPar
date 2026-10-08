# Contexto
Parcial 1 de Programación II (UCES, prof. Mario Detke). Proyecto: Sistema de Turnos Médicos en Java, paquetes com.guiraldes.main y com.guiraldes.modelo. El proyecto lo desarrolla Facundo (estudiante de primer año de la Tecnicatura) y debe entregarse comprimido (.zip) junto con un PDF. La codificación vale 5 de 10 puntos y se evalúan buenas prácticas de programación.

# Cómo compilar y ejecutar
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -Dfile.encoding=UTF-8 -cp out com.guiraldes.main.Principal

# Reglas de trabajo
- Trabajar de a un cambio por vez y esperar confirmación antes de pasar al siguiente.
- No tocar código que no esté en el plan. Entregar archivos completos, no fragmentos.
- Nivel de primer año: usar solo lo visto en la materia (clases, herencia, abstract, interfaces, enum, ArrayList, recursividad, ficheros de texto y XML con DOM, try-with-resources). Evitar streams, records, lambdas y librerías externas.
- No agregar comentarios al código. Nombres en español, camelCase.
- El proyecto tiene que compilar sin errores ni warnings en cualquier momento.
- Después de correr Principal, restaurar con git los archivos de datos (.txt y .xml) si cambiaron sin querer.

# Plan de cambios (se implementan después, de a uno, cuando yo lo pida)
1. Usuario pasa a abstract; validarPassword seguro ante null.
2. Secretaria: atributo turno se renombra a turnoLaboral.
3. Nuevo enum EstadoTurno (PENDIENTE, CONFIRMADO, CANCELADO, ATENDIDO) y atributo estado en Turno (por defecto PENDIENTE).
4. Paciente.solicitarTurno, Secretaria.asignarTurno y Doctor.atenderTurno operan sobre la Agenda (crear, confirmar, marcar como atendido).
5. Agenda: devuelve lista de solo lectura, valida ID repetido y conflicto doctor/fecha/hora, genera IDs, cancelarTurno.
6. Persistencia: mover a paquete com.guiraldes.persistencia, try-with-resources, sin código repetido, validar cantidad de campos por línea, y que el XML guarde y cargue los datos completos (email, obra social, número de afiliado, especialidad, estado), sin guardar la contraseña.
7. Consigna 2: reemplazar la búsqueda recursiva lineal por búsqueda binaria recursiva (divide y vencerás) sobre turnos ordenados por ID; mantener la búsqueda iterativa.
8. Actualizar Principal para demostrar todo lo anterior.
