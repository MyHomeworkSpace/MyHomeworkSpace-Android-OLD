package me.planhub.android.planhub;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class APIClient extends AsyncTask<String, Void, String> {

    public APIClient() {

    }
    protected String doInBackground(String... params) {
        HttpsURLConnection conn = null;
        try {
            URL url = new URL("https://planhub.me/api/v1/");
            conn = (HttpsURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(conn.getInputStream());
            Scanner s = new Scanner(in).useDelimiter("\\A");
            if (!s.hasNext()) {
                Log.i("PlanHub", "no response to API call!");
                return "error";
            }
            return s.next();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return "error";
    }

    protected void onPostExecute(String resp) {
        Log.i("PlanHub", resp);
    }

    public static String getNonce() {
        return "yay";
    }
}
