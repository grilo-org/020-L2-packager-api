# 📦 API de Embalagem para Loja de Jogos Online

## 🌟 Visão Geral
API para cálculo automático de embalagens de produtos em caixas de papelão, otimizando o espaço utilizado e sugerindo a melhor combinação de caixas para cada pedido.

## 🛠️ Tecnologias Utilizadas

### Backend
| Tecnologia | Versão | Finalidade |
|------------|--------|------------|
| ![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk) | 21 | Linguagem principal |
| ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-6DB33F?logo=spring) | 3.2.0 | Framework backend |
| ![Spring Security](https://img.shields.io/badge/Spring_Security-6.1.0-6DB33F?logo=spring) | 6.1.0 | Autenticação e autorização |
| ![SpringDoc OpenAPI](https://img.shields.io/badge/SpringDoc-2.3.0-6DB33F?logo=swagger) | 2.3.0 | Documentação da API (Swagger) |
| ![JUnit](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5) | 5 | Testes unitários |

### Infraestrutura
| Tecnologia | Versão | Finalidade |
|------------|--------|------------|
| ![Docker](https://img.shields.io/badge/Docker-24.0.7-2496ED?logo=docker) | 24.0.7 | Containerização |
| ![Maven](https://img.shields.io/badge/Maven-3.9.6-C71A36?logo=apachemaven) | 3.9.6 | Gerenciamento de dependências |

## 📋 Requisitos do Sistema
- JDK 21+
- Maven 3.9+
- Docker 24.0+ (opcional)

## 🚀 Como Executar

### 1. Configuração Inicial
```bash
mvn clean install
```

### 2. Execução com Docker
```bash
docker-compose up --build
```

### 3. Execução Local
```bash
mvn spring-boot:run
```

## 🔍 Endpoints da API

Acesse a documentação interativa:
```
http://localhost:8080/swagger-ui.html
```

Endpoint:
- `POST /api/embalagem/calcular` - Calcula a melhor combinação de caixas

## 🧪 Testes
```bash
# Executar todos os testes
mvn test

# Executar testes específicos
mvn test -Dtest="EmbalagemServiceTest"
```

## 🌐 Modelo de Dados

### Caixas Disponíveis
| Caixa | Dimensões (AxLxC) |
|-------|------------------|
| Caixa 1 | 30x40x80 cm |
| Caixa 2 | 80x50x40 cm |
| Caixa 3 | 50x80x60 cm |

### JSON de Exemplo
```json
{
  "pedidos": [
    {
      "pedido_id": 1,
      "produtos": [
        {
          "produto_id": "PS5",
          "dimensoes": {
            "altura": 40,
            "largura": 10,
            "comprimento": 25
          }
        }
      ]
    }
  ]
}
```

## 🔒 Segurança
- Autenticação Basic Auth padrão:
  - Usuário: `admin`
  - Senha: `admin`

## 📦 Estrutura do Projeto
```
src/
├── main/
│   ├── java/br/com/allidev/packager/
│   │   ├── config/       # Configurações do Spring
│   │   ├── controller/   # Controladores REST
│   │   ├── dto/          # Objetos de transferência
│   │   ├── model/        # Entidades do domínio
│   │   ├── service/      # Lógica de negócio
│   │   └── Application.java
│   └── resources/        # Arquivos de configuração
├── test/                 # Testes unitários
docker-compose.yml        # Configuração Docker
Dockerfile                # Build da imagem
```
