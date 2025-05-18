# 🧮 Spring Métodos Numéricos API

Este proyecto es una API REST construida con Spring Boot que implementa diversos métodos numéricos para aplicaciones académicas o científicas. Incluye su despliegue mediante Docker y Docker Compose.

---

## 📦 Requisitos previos

- [Docker](https://www.docker.com/products/docker-desktop) instalado y en ejecución
- [Docker Compose](https://docs.docker.com/compose/) instalado (incluido en Docker Desktop)

---

## 🚀 Instalación

### 1. Clona el repositorio

```bash
git clone https://github.com/tuusuario/Calculadora_Metodos.git
cd Calculadora_Metodos
```

### 2. Construye y levanta la aplicación

```bash
docker-compose up --build
```

Esto hará:
- Construir la imagen a partir del `Dockerfile`
- Levantar un contenedor y exponer el servicio en `http://localhost:8080`

---

## ⚙️ Endpoints disponibles


> ⚠️ **ESTA EN DESARROLLO **

---

## 📁 Estructura del proyecto

```
.
├── src/                    # Código fuente Java (controladores, servicios, etc.)
├── target/                 # JAR generado por Maven (no se debe versionar)
├── Dockerfile              # Instrucciones para construir la imagen
├── docker-compose.yml      # Orquestador de contenedores
├── pom.xml                 # Configuración de Maven
└── README.md               # Este archivo
```

---

## 🛠 Tecnologías utilizadas

- Java 17
- Spring Boot
- Maven
- Docker

---

## 🧪 Construcción manual (sin Docker)

Si deseas ejecutar la aplicación de forma local sin Docker:

```bash
./mvnw clean install
java -jar target/Metodos-0.0.1-SNAPSHOT.jar
```

---

## 📄 Licencia

Este proyecto está bajo la licencia [MIT](LICENSE).

---

## 🙋‍♂️ Autor

- **Mateo Sánchez**
- GitHub: [@mateosanchezh](https://github.com/mateosanchezh)
