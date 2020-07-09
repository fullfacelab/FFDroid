package br.com.fullface.ffdroid.json;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;

/**
 * Created by Wender on 12/08/2016.
 */
public class JSONParser {
    private final URI uri;
    private final String json;

    JSONParser(URI uri, String json) {
        this.uri = uri;
        this.json = json;
    }


    /**
     * Executa a transmissão dos arquivos para o servidor remoto
     *
     * @return uma resposta vinculada à resposta do servidor remoto
     * @throws IOException se problemas na transmissão ou na manipulação dos arquivos
     */
    public Response upload() throws IOException {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost =  new HttpPost(uri);
        httpPost.setHeader("Content-Type", "application/json");

        try {
            HttpEntity httpEntity = new StringEntity(json);
            httpPost.setEntity(httpEntity);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse httpResponse = httpclient.execute(httpPost);

        InputStream inputStream = httpResponse.getEntity().getContent();

        Response response = Response.Builder
                .create(httpResponse.getStatusLine().getStatusCode())
                .content(StringRetorno.InputStreamToStringExample.getStringFromInputStream(inputStream))
                .build();

        return response;

    }

    public final static class Builder{

        private final URI uri;
        private String json;

        private Builder(final URI uri){
            this.uri = uri;
        }

        public static final Builder forURI(URI uri) {
            return new Builder(uri);
        }



        public static final Builder forURI(String uri) {
            return new Builder(URI.create(uri));
        }

        public final Builder withJson(String json){

            this.json = json;

            return this;
        }


        public JSONParser build(){
            return new JSONParser(uri,json);
        }

        private boolean uriIsNullOrEmpty() {
            return false;
        }


    }

}
