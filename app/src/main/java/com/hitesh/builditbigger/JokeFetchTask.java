package com.hitesh.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.hitesh.builditbigger.backend.myApi.MyApi;
import com.hitesh.builditbigger.backend.myApi.model.MyBean;
import com.hitesh.library.JokeShowActivity;

import java.io.IOException;

/**
 * Created by Hitesh Sutar on 07-06-2016.
 */
public class JokeFetchTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi mJokeApi = null;
    private Context mContext;
    private String mResult;
    private static final String INTENT_JOKE = "joke";
    private ProgressBar progressbar;
    private OnJokeRetrievedListener jokeListener;
    public JokeFetchTask(Context context, ProgressBar progressbar) {
        this.mContext = context;
        this.progressbar = progressbar;
        Log.d("Project", "constructor");
    }

    public JokeFetchTask(ProgressBar progressbar,OnJokeRetrievedListener listener) {
        this.progressbar = progressbar;
        Log.d("Project", "constructor");
        jokeListener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("Project", "onPreExecute");
        if (progressbar != null)
            progressbar.setVisibility(View.VISIBLE);

    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (mJokeApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
//                    .setRootUrl("http://10.0.3.2:8080/_ah/api/");
            .setRootUrl("https://build-it-bigger-gce-1333.appspot.com/_ah/api/");
            mJokeApi = builder.build();
            Log.d("Project", "doIn");

        }
        try {
            return mJokeApi.putJoke(new MyBean()).execute().getJoke();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mResult = result;
        try {
            jokeListener.onJokeRetrieved(mResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Project", "post");
//        startJokeDisplayActivity(mResult);


    }

    private void startJokeDisplayActivity(String result) {


        Log.d("Project Result", result);
        Intent i = new Intent(mContext, JokeShowActivity.class);
        i.putExtra(INTENT_JOKE, result);
        mContext.startActivity(i);
    }

    public interface OnJokeRetrievedListener {
        void onJokeRetrieved(String joke) throws InterruptedException;
    }
}
