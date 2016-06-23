package com.hitesh.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hitesh.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.hitesh.builditbigger.backend.myApi.model.MyBean;

import java.io.IOException;

/**
 * Created by Vishal on 07-06-2016.
 */
public class JokeFetchTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi mJokeApi = null;
    private Context mContext;
    private String mResult;
    private static final String INTENT_JOKE = "joke";
    private ProgressBar progressbar;

    public JokeFetchTask(Context context, ProgressBar progressbar) {
        this.mContext = context;
        this.progressbar = progressbar;
        Log.d("Project", "constructor");
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
                    .setRootUrl(mContext.getString(R.string.root_url_api));
            mJokeApi = builder.build();
            Log.d("Project", "doIn");

        }
        try {
            return mJokeApi.putJoke(new MyBean()).execute().getJoke();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Hitesh";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        mResult = result;
        Log.d("Project", "post");
        if (progressbar != null)
            progressbar.setVisibility(View.GONE);


    }

   /* private void startJokeDisplayActivity() {

        Toast.makeText(mContext, mResult+" : joke aya", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(,JokeShow)
    }*/
}
