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

public class APIClient extends AsyncTask<String, void, String> {

    public APIClient(Context ctx) {

    }
    protected String doInBackground(String... params) throws MalformedURLException, IOException {
        URL url = new URL("https://planhub.me/api/v1");
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(conn.getInputStream());
            Scanner s = new Scanner(in).useDelimiter("\\A");
            if (!s.hasNext()) {
                Log.i("PlanHub", "no response to API call!");
                return "error";
            }
            return s.next();
        } finally {
            conn.disconnect();
        }
        return "error";
    }

    protected void onPostExecute(String resp) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(findActivity);
        dlgAlert.setMessage(resp);
        dlgAlert.setTitle("It worked!");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    public static String getNonce() {
        return "yay";
    }
}
