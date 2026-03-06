package com.hidrolife.beta.service;

import org.springframework.stereotype.Service;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class TelegramService {

    private final String token = "8002238287:AAE8N5I-wbq2VJU-mxcQzmEaHKOxspsd9y0";
    private final String chatId = "6354984166";

    public void enviarAlerta(String mensaje) {
        try {

            String mensajeCodificado =
                    URLEncoder.encode(mensaje, StandardCharsets.UTF_8);

            String urlString =
                    "https://api.telegram.org/bot" + token +
                            "/sendMessage?chat_id=" + chatId +
                            "&text=" + mensajeCodificado;

            URL url = new URL(urlString);

            url.openStream().close(); // IMPORTANTE cerrar stream

            System.out.println("✅ Alerta enviada correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
