package com.hitesh.builditbigger;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.hitesh.builditbigger.R;
import com.hitesh.library.JokeShowActivity;

import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class DummyFrag extends Fragment {

    Button tellJoke;
    private static final String INTENT_JOKE = "joke";
    Context context;
    ProgressBar progressbar;

    public DummyFrag() {
        // Required empty public constructor
    }

    public DummyFrag(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dummy, container, false);
        getAdd(view);
        progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
        tellJoke = (Button) view.findViewById(R.id.btn_tellJoke);
        tellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Util.isOnline(getActivity())) {
                    try {

                        JokeFetchTask task = new JokeFetchTask((Activity)context,progressbar);
                        task.execute();

                        String result = task.get();

                        Log.d("Project Result", result);
                        Intent i = new Intent(getActivity(), JokeShowActivity.class);
                        i.putExtra(INTENT_JOKE, result);
                        startActivity(i);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, context.getResources().getString(R.string.nonet), Toast.LENGTH_SHORT).show();
                }
            }

        });
        return view;
    }

    public void getAdd(View view) {
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }
}
