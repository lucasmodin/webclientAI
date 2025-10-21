# WoW Mythic+ AI Enhancer

A full stack Spring Boot webclient project integrating with Blizzard’s OAuth API and OpenAI for analysis of World of Warcraft character data.  
The application retrieves the user’s characters, gear and Mythic+ scores, and provides an AI-powered chat interface that offers personalized feedback and improvement suggestions.

## Features

- **OAuth Integration:** Secure login via Blizzard OAuth 2.0.  
- **Character Data Retrieval:** Fetches character information, equipped gear, and Mythic+ performance from the Blizzard API.  
- **AI Analysis:** Uses OpenAI integration to evaluate player data and generate insights and suggestions.  
- **Chat Interface:** Enables interactive communication with the AI to discuss gear, scores, and optimization tips.  
- **Full Stack Design:** Built with Spring Boot backend and webclient for API communication.

## Tech Stack

- **Backend:** Java, Spring Boot, WebClient, REST API  
- **AI Integration:** OpenAI API  
- **Authentication:** Blizzard OAuth 2.0  
- **Build & Tools:** Maven, Git, IntelliJ IDEA  
- **Database (optional):** H2 / MySQL  

## Setup
Before you start, you need to create a 'client' on the battle.net developer portal to access the Blizzard OAuth API and get your client id:
[https://develop.battle.net/access/](https://develop.battle.net/access/)


1. Clone the repository:
   ```bash
   git clone https://github.com/lucasmodin/webclientAI.git
   cd webclientAI
   ```
2. Add your credentials in application.properties:
   ```properties
   blizzard.client.id=YOUR_CLIENT_ID
   blizzard.client.secret=YOUR_CLIENT_SECRET
   openai.api.key=YOUR_OPENAI_KEY
   ```
3. Build and run the project:
   ```bash
   mvn spring-boot:run
   ```
4. Access the application in your browser:
   ```arduino
   http://localhost:8080
   ```
## Usage
1. Log in with your Blizzard account to authorize the application.
2. Select your World of Warcraft character.
3. Interact with the AI assistant to get insights into your gear, Mythic+ performance, and potential improvements.
