# ChronosSim - Simulador de Planificación de CPU

**Descripción:**
ChronosSim es un simulador web de planificación de CPU que permite analizar distintos algoritmos clásicos: **FCFS**, **SJF**, **Round Robin (RR)** y **Prioridades (PRIORITY)**.  
El sistema calcula métricas clave como tiempo de espera promedio, tiempo de retorno, uso de CPU, y cantidad de cambios de contexto.

---

## Tecnologías

- **Java 21**
- **Spring Boot 4.0.0-SNAPSHOT**
- **Lombok**
- **Maven**
- **No se utiliza base de datos** (simulación en memoria)
- **Swagger/OpenAPI** para documentación de la API

---

## Endpoints

### POST `/api/v1/simulations`

Ejecuta una simulación de CPU con un conjunto de procesos y un algoritmo específico.

**Request Body (JSON):**
```json
{
  "algorithm": "RR",
  "quantum": 3,
  "processSimulationDto": [
    { "id": "P1", "arrivalTime": 0, "burstTime": 5, "priority": 1 },
    { "id": "P2", "arrivalTime": 1, "burstTime": 3, "priority": 2 }
  ]
}
```

**Response Body (JSON):***
```json
{
  "algorithm": "RR",
  "metrics": {
    "avgResponseTime": 3.0,
    "avgTurnaroundTime": 14.0,
    "avgWaitingTime": 8.5,
    "contextSwitches": 7,
    "cpuUtilization": 100.0,
    "throughput": 0.18
  },
  "timeline": [
    { "start": 0, "end": 3, "processId": "P1" },
    { "start": 3, "end": 6, "processId": "P2" }
  ]
}
```

## Ejecución del proyecto

1. **Clonar el repositorio:**
```bash
git clone <repo-url>
```
2. **Construir el proyecto:**
```bash
mvn clean install
```
3. **Ejecutar el Proyecto:**
```bash
mvn spring-boot:run
```
4. **Acceder a la documentación de la API:**
   - URL: `http://localhost:8080/swagger-ui/index.html`
 
5. **Probar los endpoints** usando Swagger UI o herramientas como Postman.
    - ENDPOINTS
    - POST `/api/v1/simulations`
    
**Ejemplo de Request Body:**
```json
{
  "algorithm": "RR",
  "quantum": 3,
  "processSimulationDto": [
    { "id": "P1", "arrivalTime": 0, "burstTime": 5, "priority": 1 },
    { "id": "P2", "arrivalTime": 1, "burstTime": 3, "priority": 2 }
  ]
}
```

**Ejemplo de Response Body FCFS:**
```json
{
  "algorithm": "FCFS",
  "metrics": {
    "avgResponseTime": 2.33,
    "avgTurnaroundTime": 5.33,
    "avgWaitingTime": 2.33,
    "contextSwitches": 2,
    "cpuUtilization": 100.0,
    "throughput": 0.33
  },
  "timeline": [
    { "start": 0, "end": 5, "processId": "P1" },
    { "start": 5, "end": 8, "processId": "P2" },
    { "start": 8, "end": 9, "processId": "P3" }
  ]
}
```

**Ejemplo de Response Body SJF:**
```json
{
  "algorithm": "FCFS",
  "metrics": {
    "avgResponseTime": 2.33,
    "avgTurnaroundTime": 5.33,
    "avgWaitingTime": 2.33,
    "contextSwitches": 2,
    "cpuUtilization": 100.0,
    "throughput": 0.33
  },
  "timeline": [
    { "start": 0, "end": 5, "processId": "P1" },
    { "start": 5, "end": 8, "processId": "P2" },
    { "start": 8, "end": 9, "processId": "P3" }
  ]
}
```

**Ejemplo de Response Body PRIORITY:**
```json
{
  "algorithm": "PRIORITY",
  "metrics": {
    "avgResponseTime": 4.33,
    "avgTurnaroundTime": 9.33,
    "avgWaitingTime": 4.33,
    "contextSwitches": 2,
    "cpuUtilization": 100.0,
    "throughput": 0.2
  },
  "timeline": [
    { "start": 0, "end": 6, "processId": "P1" },
    { "start": 6, "end": 10, "processId": "P2" },
    { "start": 10, "end": 15, "processId": "P3" }
  ]
}
```

**Ejemplo de Response Body RR:**
```json
{
  "algorithm": "RR",
  "metrics": {
    "avgResponseTime": 3.0,
    "avgTurnaroundTime": 14.0,
    "avgWaitingTime": 8.5,
    "contextSwitches": 7,
    "cpuUtilization": 100.0,
    "throughput": 0.18
  },
  "timeline": [
    { "start": 0, "end": 3, "processId": "P1" },
    { "start": 3, "end": 6, "processId": "P2" },
    { "start": 6, "end": 9, "processId": "P3" },
    { "start": 9, "end": 12, "processId": "P4" }
  ]
}
```

## NOTAS IMPORTANTES
- RR requiere definir un quantum; otros algoritmos ignoran este valor.
- La respuesta contiene un timeline con intervalos de ejecución de cada proceso.
- No se utiliza base de datos; toda la simulación ocurre en memoria.
- Las métricas permiten comparar la eficiencia de cada algoritmo en distintos escenarios.

## Autor
- Marcos Enrique Guzmán Nazareno