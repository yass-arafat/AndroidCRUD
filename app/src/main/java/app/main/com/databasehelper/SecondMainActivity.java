package app.main.com.databasehelper;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.AsyncTask;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;


public class SecondMainActivity extends AppCompatActivity {

        // URL Address
//        String url = "http://www.androidbegin.com";
        String url = "http://www.imdb.com/year/2016/";
//        String url = "http://www.imdb.com/title/tt4972582/";
        ProgressDialog mProgressDialog;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second_main);

            // Locate the Buttons in activity_main.xml
            Button titlebutton = (Button) findViewById(R.id.titlebutton);
            Button yearbutton = (Button) findViewById(R.id.yearbutton);
            Button descbutton = (Button) findViewById(R.id.descbutton);
            Button logobutton = (Button) findViewById(R.id.logobutton);

            // Capture button click
            titlebutton.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    // Execute Title AsyncTask
                    new Title().execute();
                }
            });
            // Capture button click
            yearbutton.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    // Execute Title AsyncTask
                    new Year().execute();
                }
            });

            // Capture button click
            descbutton.setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    // Execute Description AsyncTask
                    new Description().execute();
                }
            });

            // Capture button click
            logobutton.setOnClickListener(new OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                public void onClick(View arg0) {
                    // Execute Logo AsyncTask
                    new Logo().execute();
                }
            });

        }

        // Title AsyncTask
        private class Title extends AsyncTask<Void, Void, Void> {
            String title = "";

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProgressDialog = new ProgressDialog(SecondMainActivity.this);
                mProgressDialog.setTitle("Android Basic JSoup Tutorial");
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    // Connect to the web site
                    Document document = Jsoup.connect(url).get();

                    // Get the html document title
//                    title = document.title();
//                    title = "haaaaa";
                    ArrayList<String> titleOfMovie = new ArrayList<>();
                    Element table = document
                            .select("table[class=results] tbody tr[class=even detailed] td[class=title] a").get(0);
//                    Log.d("Testing",elements.toString());
//                    for (Element e : elements){
                        title += table.text();
//                        System.out.println(""+title);
//                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                // Set title into TextView
                TextView txttitle = (TextView) findViewById(R.id.titletxt);
                txttitle.setText(title);
                mProgressDialog.dismiss();
            }
        }
    // Year AsyncTask
    private class Year extends AsyncTask<Void, Void, Void> {
        String year = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(SecondMainActivity.this);
            mProgressDialog.setTitle("Android Basic JSoup Tutorial");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document document = Jsoup.connect(url).get();

                // Get the html document title
//                    title = document.title();
//                    title = "haaaaa";
                ArrayList<String> titleOfMovie = new ArrayList<>();
                Element yearOfTheMovieInTable = document
                        .select("table[class=results] tbody tr[class=even detailed] td[class=title] span[class=year_type]").get(0);
//                    Log.d("Testing",elements.toString());
//                    for (Element e : elements){
                year += yearOfTheMovieInTable.text();
//                        System.out.println(""+title);
//                    }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set title into TextView
            TextView txttitle = (TextView) findViewById(R.id.yeartxt);
            txttitle.setText(year);
            mProgressDialog.dismiss();
        }
    }

        // Description AsyncTask
        private class Description extends AsyncTask<Void, Void, Void> {
            String descriptionOfMovie = "";

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProgressDialog = new ProgressDialog(SecondMainActivity.this);
                mProgressDialog.setTitle("Android Basic JSoup Tutorial");
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    // Connect to the web site
                    Document document = Jsoup.connect(url).get();
                    // Using Elements to get the Meta data
                    Element descriptionOfMovieInTable = document
                            .select("table[class=results] tbody tr[class=even detailed] td[class=title] span[class=outline]").get(0);
                    // Locate the content attribute
                    descriptionOfMovie  = descriptionOfMovieInTable.text();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                // Set description into TextView
                TextView txtdesc = (TextView) findViewById(R.id.desctxt);
                txtdesc.setText(descriptionOfMovie);
                mProgressDialog.dismiss();
            }
        }

        // Logo AsyncTask
        private class Logo extends AsyncTask<Void, Void, Void> {
            Bitmap bitmap;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mProgressDialog = new ProgressDialog(SecondMainActivity.this);
                mProgressDialog.setTitle("Android Basic JSoup Tutorial");
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(false);
                mProgressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {

                try {
                    // Connect to the web site
                    Document document = Jsoup.connect(url).get();
                    // Using Elements to get the class data
                    Element img = document.select("table[class=results] tbody tr[class=even detailed] td[class=image] a[title=Split (2016)] img[src]").get(0);
                    // Locate the src attribute
                    String imgSrc = img.attr("src");
                    // Download image from URL
                    InputStream input = new java.net.URL(imgSrc).openStream();
                    // Decode Bitmap
                    bitmap = BitmapFactory.decodeStream(input);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                // Set downloaded image into ImageView
                ImageView logoimg = (ImageView) findViewById(R.id.logo);
                logoimg.setImageBitmap(bitmap);
                mProgressDialog.dismiss();
            }
        }

}
