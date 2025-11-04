package br.com.guiahc.services;

import br.com.guiahc.api.VerificaSenha;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class VerificaSenhaService {

    public VerificaSenha getVerificarSenha(String senha) throws IOException, IOException {
        VerificaSenha verificaSenha = null;

        String url = "https://verificador-senha-api.onrender.com/senha/" + senha;

        // Request
        HttpGet request = new HttpGet(url);

        // Client
        CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();

        // Response
        CloseableHttpResponse response = httpClient.execute(request);

        // Entity
        HttpEntity entity = response.getEntity();

        if (entity != null) {

            String json = EntityUtils.toString(entity);

            Gson gson = new Gson();

            verificaSenha = gson.fromJson(json, VerificaSenha.class);

        }

        return verificaSenha;
    }
}
