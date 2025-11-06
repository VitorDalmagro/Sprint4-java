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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class VerificaSenhaService {

    public VerificaSenha getVerificarSenha(String senha) throws IOException, IOException {
        VerificaSenha verificaSenha = null;

        // transforma caracteres especiais em formatos seguros para URL
        String senhaEncoded = URLEncoder.encode(senha, StandardCharsets.UTF_8);
        String url = "https://verificador-senha-api.onrender.com/senha/" + senhaEncoded;

        // Request
        HttpGet request = new HttpGet(url);

        // Client
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build()){

        // Response
        CloseableHttpResponse response = httpClient.execute(request);

        // Entity
        HttpEntity entity = response.getEntity();

            if (entity != null) {

                String json = EntityUtils.toString(entity);
                System.out.println("JSON retornado da API: " + json);
                Gson gson = new Gson();

                verificaSenha = gson.fromJson(json, VerificaSenha.class);

                // Garantir que verificaSenha não seja nulo
                if (verificaSenha == null) {
                    verificaSenha = new VerificaSenha();
                }

                // Setando a senha para retornar bonitinho ao inves da senha encoded :)
                verificaSenha.setSenha(senha);
            }
            response.close();
        }

        return verificaSenha;
    }
}
