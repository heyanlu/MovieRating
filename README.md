# Movie Rating Platform

![Java](https://img.shields.io/badge/Java-17+-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)

A comprehensive social platform for movie enthusiasts to discover, rate, and discuss films while connecting with other users. 
## Features

### User Management
- Registration with email, username, password, profile picture, and bio
- Secure login/logout functionality
- Profile viewing with follower/following counts
- User discovery/suggestions system

### Social Features
- Follow/unfollow other users
- View followers/following lists
- Get suggestions for users to follow

### Movie Interactions
- Browse complete movie database
- Search by title, genre, or other attributes
- Detailed movie information pages
- Paginated browsing experience


### Rating System
- Rate movies on a 10-point scale
- Post detailed reviews and comments
- View community ratings
- Edit existing ratings

### Collections
- Create "Want to Watch" lists 
- Track watched movies 
- Manage personal collections 
- Check movie collection status



### Technical Stack
- **Backend**: Java 17+, Spring Boot 3.0
- **Database**: MySQL 8.0
- **ORM**: MyBatis
- **Security**: Session-based authentication
- **Frontend**: Thymeleaf templates (implied by controller returns)

## Installation

### Prerequisites
- Java JDK 17+
- MySQL 8.0+
- Maven 3.8+

### Setup Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/heyanlu/MovieRating.git
   ```

2. Database setup:
   ```bash
   CREATE DATABASE MoviePlatform;
   ```
   Refer to Group7-Final.zip for details of the tables


3. Configure application:
   ```bash
   # In application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/MoviePlatform
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```


4. Build and launch:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

Access the platform at: http://localhost:8082

