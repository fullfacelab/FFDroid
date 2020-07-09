package br.com.fullface.ffdroid.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.IOException;

import br.com.fullface.ffdroid.json.JSONParser;
import br.com.fullface.ffdroid.json.Response;

public class Request extends AsyncTask<String, String, Response> {
    private ProgressDialog dialog;
    private Response mResponse;
    private Activity activity;
    private String url;
    private String json;


//    public Request(Activity activity) {
//        this.activity = activity;
//    }


    public Request(Activity activity, String url, String json) {
        this.activity = activity;
        this.url = url;
        this.json = json;
    }

    @Override
    protected Response doInBackground(String... strings) {
        Response response = null;
        try {
            JSONParser jsonParser = JSONParser.Builder
                    .forURI(url)
                    .withJson(json)
                    .build();

            response = jsonParser.upload();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return response;
    }

        @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
            mResponse = response;
    }
}
