package pl.edu.icm.meteo.android.meteo.app;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import pl.edu.icm.meteo.android.meteo.app.location.LocationsActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;


public class MainActivity extends ActionBarActivity {

    private ImageView imageView;
    private WebView webView;
    private String latestMeteogramFilename = "latest_meteogram.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();

        if (isNonEmptyMeteogramOnLocalStorage()) {
            displayLocalMeteogram();
            makeImageViewGone();
        } else {
            displayICMLogo();
        }
        new DownloadLatestMeteogramTask().execute();
    }

    private boolean isNonEmptyMeteogramOnLocalStorage() {
        File file =  new File(getFilesDir() + "/" + latestMeteogramFilename);
        return file.exists() && file.length() > 0;
    }

    private void displayLocalMeteogram() {
        if (webView == null)
            webView = new WebView(getBaseContext());
        try {
            URL url = new URL("file://" + getFilesDir() + "/" + latestMeteogramFilename);
            webView.loadUrl(url.toString());
            setContentView(webView);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void makeImageViewGone() {
        if (imageView != null)
            imageView.setVisibility(View.GONE);
    }

    private void displayICMLogo() {
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.icm_logo_view);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.icm_logo_256));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_locations:
                startActivity(new Intent(this, LocationsActivity.class));
            default:
                System.out.println("Default");
        }
        return super.onOptionsItemSelected(item);
    }

    private final class DownloadLatestMeteogramTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            download(getLatestMeteogramFilename());
            return "Done";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            setMeteoFromLocalFile();
        }
    }

    private void setMeteoFromLocalFile() {
        displayLocalMeteogram();
    }

    private void download(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            FileOutputStream fileOutput = openFileOutput(latestMeteogramFilename, Context.MODE_PRIVATE);

            InputStream inputStream = urlConnection.getInputStream();
            byte[] buffer = new byte[1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
            }
            fileOutput.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getLatestMeteogramFilename() {
        int fileNum = 12 + new Random().nextInt(16) * 3;
        return "http://tdl.home.pl/meteoandroid/maps/cropmore/temperatura/0" + fileNum + ".png";
    }
}
