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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.hitesh.library.JokeShowActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class DummyFrag extends Fragment {

    Button tellJoke;
    private static final String INTENT_JOKE = "joke";
    Context context;
    ProgressBar progressbar;
    private InterstitialAd interstitialAd;

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

                        JokeFetchTask task = new JokeFetchTask(progressbar,new JokeRetrievalHandler());
                        task.execute();

                        final String result = task.get();
                        interstitialAd = new InterstitialAd(context);
                        interstitialAd.setAdUnitId(context.getString(R.string.interstitial_ad_unit_id));
                        interstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdClosed() {
                                super.onAdClosed();
                                if (result != null && !result.isEmpty())
                                    startJokeDisplayActivity(result);
                            }

                            @Override
                            public void onAdFailedToLoad(int errorCode) {
                                super.onAdFailedToLoad(errorCode);
                                if (progressbar != null)
                                    progressbar.setVisibility(View.GONE);
                                startJokeDisplayActivity(result);
                            }

                            @Override
                            public void onAdOpened() {
                                super.onAdOpened();
                            }

                            @Override
                            public void onAdLoaded() {

                                super.onAdLoaded();
                                if (progressbar != null)
                                    progressbar.setVisibility(View.GONE);
                                interstitialAd.show();
                            }

                            @Override
                            public void onAdLeftApplication() {
                                super.onAdLeftApplication();
                            }
                        });

                        AdRequest ar = new AdRequest
                                .Builder()
                                .build();
                        interstitialAd.loadAd(ar);

                        Log.d("Project Result", result);


                    } catch (Exception e) {
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

    private void startJokeDisplayActivity(String result) {


        Log.d("Project Result", result);
        Intent i = new Intent(context, JokeShowActivity.class);
        i.putExtra(INTENT_JOKE, result);
        context.startActivity(i);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (progressbar != null && progressbar.getVisibility() == View.VISIBLE) {
            progressbar.setVisibility(View.GONE);
        }
    }

    private class JokeRetrievalHandler implements JokeFetchTask.OnJokeRetrievedListener {

        @Override
        public void onJokeRetrieved(String joke) {

        }
    }

}
