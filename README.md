## Prérequis

- Java 11 ou supérieur
- Maven 3.6 ou supérieur
- yt-dlp installé et accessible dans le PATH

## Installation

1. Clonez le dépôt :
   ```bash
   git clone https://github.com/InacioMacosso/youtube-video-downloader.git
   cd youtube-video-downloader
   
2. Compilez le projet avec Maven :
   ```bash
   mvn clean install
   
## Utilisation

1. Démarrez l'application Spring Boot :
   ```bash
   mvn spring-boot:run
   
2. Envoyez une requête POST à l'URL `http://localhost:8080/api/download` avec le paramètre `url` de la 
vidéo YouTube que vous souhaitez télécharger :
   ```bash
    curl -X POST http://localhost:8080/api/download?url=https://www.youtube.com/watch?v=VIDEO_ID
    ```