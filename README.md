# 🎬 MovieApp - Microservices Architecture

MovieApp is a scalable and robust web application designed for managing movies, user profiles, and personalized recommendations. It uses a microservices architecture implemented in Java (Spring Boot) with Angular on the frontend. Communication between microservices is managed through REST APIs and Apache Kafka for event-driven interactions.

## 🚀 Features

- User registration and authentication (JWT)
- Browse popular movies and detailed movie pages
- User profiles with personalized movie lists (Watched, Liked, Want to Watch)
- Premium subscription management
- Intelligent movie recommendations powered by AI
- Real-time notifications and analytics

## 📐 Architecture Overview

```
Frontend (Angular)
├── Login
├── Register
├── Browse Movies
├── User Profiles
└── Premium Subscription
       │ HTTP
Backend (Spring Boot)
├── User Service (Auth + User management)
├── Movie Service
├── Recommendation Service
├── Notification Service
└── Event-Streaming (Analytics)
       │ Kafka (Event-driven)
```

## 📡 API Endpoints (REST)

| Endpoint                          | Method | Service                  | Description                             |
|-----------------------------------|--------|--------------------------|-----------------------------------------|
| `/api/auth/login`                | POST   | `user-service`           | User authentication and JWT retrieval   |
| `/api/auth/register`             | POST   | `user-service`           | User registration                       |
| `/api/auth/secure-hello`         | GET    | `user-service`           | Protected test endpoint                 |
| `/movies`                         | GET    | `movie-service`          | Retrieve popular movies                 |
| `/movies/{id}`                    | GET    | `movie-service`          | Retrieve movie details                  |
| `/profile/{userid}`               | GET    | `user-service`           | Retrieve user profile and movie lists   |
| `/recommendations/{userid}`       | GET    | `recommendation-service` | Personalized movie recommendations      |
| `/subscribe`                      | POST   | `user-service`           | Activate premium subscription           |
| `/admin/users`                    | GET    | `user-service`           | Admin-only: retrieve all user details   |

## 🔄 Kafka Topics and Message Flow

| Topic                     | Producer                 | Consumer               | Purpose                                 |
|---------------------------|--------------------------|------------------------|-----------------------------------------|
| `user-registered-topic`   | `user-service`           | `notification-service` | Send welcome notifications              |
| `user-activity-topic`     | `movie-service`, `user-service` | `recommendation-service` | Update recommendations based on activity|
| `notification-topic`      | `recommendation-service`, `movie-service` | `notification-service` | User notifications (new movies, etc.)   |
| `premium-subscription-topic` | `user-service`       | `notification-service` | Notify users about subscription changes |
| `logging-topic`           | All services             | `event-streaming`      | Centralized logging and analytics       |

## 🛠 Tech Stack

### Backend:
- Java 17
- Spring Boot 3
- Spring Security
- Spring Data
- Hibernate

### Databases:
- PostgreSQL (primary)
- MongoDB (historical data)
- Redis (caching)

### Frontend:
- Angular

### APIs:
- TMDb / OMDb APIs (movie data)

### Messaging:
- Apache Kafka

### AI & ML Recommendations:
- TensorFlow / scikit-learn

### CI/CD & Logging:
- GitHub Actions
- Docker
- AWS (Free Tier)

### Development Tools:
- Gradle (build tool)
- JUnit, Mockito (testing)
- Git (version control)

## 🧑‍💻 Getting Started

Clone the repository:
```sh
git clone https://github.com/ibrus/movieapp.git
cd movieapp
```

Start services using Docker Compose:
```sh
docker-compose up --build
```

Access frontend on `http://localhost:4200`

## 🧪 Testing

Tests can be executed using Gradle:
```sh
./gradlew test
```

## 📦 Deployment

Deployment pipelines are configured via GitHub Actions for automated build and deployment to AWS.

---

Feel free to open issues and contribute to enhance the MovieApp!
