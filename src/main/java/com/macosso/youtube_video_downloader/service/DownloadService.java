package com.macosso.youtube_video_downloader.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
@Service
public class DownloadService {
    @Value("${video.download.path}")
    private String outputPath;
    public String downloadAndConvertToMp4(String videoUrl, String videoName) {
        try {
            String outputPath = this.outputPath + videoName; // Caminho base do arquivo
            // Verifica se o diretório de saída existe, se não, cria-o
            File outputDir = new File(outputPath).getParentFile();
            if (!outputDir.exists()) {
                boolean dirsCreated = outputDir.mkdirs();
                if (!dirsCreated) {
                    return "Erro ao criar diretório de saída: " + outputDir.getAbsolutePath();
                }
            }

            // Baixar o vídeo diretamente no formato mp4 com vídeo e áudio usando yt-dlp
            String downloadPath = outputPath + ".mp4"; // Arquivo final
            String downloadCommand = String.format("yt-dlp -f \"bestvideo[ext=mp4]+bestaudio[ext=m4a]/mp4\" -o \"%s\" %s", downloadPath, videoUrl);
            executeCommand(downloadCommand);

            // Verifica se o arquivo foi realmente criado
            File downloadedFile = new File(downloadPath);
            if (!downloadedFile.exists()) {
                return "Erro: o arquivo não foi encontrado após o download: " + downloadPath;
            }

            System.out.println("Download concluído!");
            return "Download concluído! Arquivo salvo em: " + downloadPath;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro durante o download: " + e.getMessage();
        }
    }

    private void executeCommand(String command) throws Exception {
        Process process = Runtime.getRuntime().exec(command);

        // Captura saída do comando para debug
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // Captura saída de erro do comando para debug
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }

        // Espera o processo terminar
        process.waitFor();
    }
}
