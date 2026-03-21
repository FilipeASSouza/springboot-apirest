# Dockerfile otimizado para Render + Spring Boot

# 1️⃣ Imagem base com Maven e Java
FROM maven:3.9.9-eclipse-temurin-17

# 2️⃣ Diretório de trabalho dentro do container
WORKDIR /app

# 3️⃣ Copiar todo o projeto para o container
COPY . .

# 4️⃣ Garantir permissão de execução caso queira usar mvnw (opcional)
RUN chmod +x ./mvnw || true

# 5️⃣ Build do projeto sem executar testes
#    Usamos Maven direto para evitar problemas de mvnw/Windows
RUN mvn clean package -DskipTests

# 6️⃣ Comando para rodar a aplicação
CMD ["java", "-jar", "target/api-0.0.2-SNAPSHOT.jar"]