package com.thewickerbreaker.auriculartreats;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Boolean isPortrait = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView jukeboxSelector = (TextView) findViewById(R.id.jukebox_select_button);
        TextView radioSelector = (TextView) findViewById(R.id.radio_select_button);
        TextView podcastSelector = (TextView) findViewById(R.id.podcast_select_button);

        TextView aboutJukebox = (TextView) findViewById(R.id.about_jukebox);
        TextView aboutRadio = (TextView) findViewById(R.id.about_radio);
        TextView aboutPodcast = (TextView) findViewById(R.id.about_podcast);

        TextView signUp = (TextView) findViewById(R.id.sign_up_clickable_text);
        Button logIn = (Button) findViewById(R.id.log_in_button);

        /**
         * Sends user to the Jukebox feature.
         */
        assert jukeboxSelector != null;
        jukeboxSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jukeboxIntent = new Intent(MainActivity.this, Jukebox.class);
                startActivity(jukeboxIntent);
            }
        });

        /**
         *Triggers Toasts information about my goals on how I see the Jukebox feature working once
         *I learn how to make it fully functional.
         */
        assert aboutJukebox != null;
        aboutJukebox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               aboutJukeboxToast(MainActivity.this, getString(R.string.about_jukebox_toast), Toast.LENGTH_LONG);
            }
        });

        /**
         * Sends user to the Radio feature.
         */
        assert radioSelector != null;
        radioSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent radioIntent = new Intent(MainActivity.this, Radio.class);
                startActivity(radioIntent);
            }
        });

        /**
        *Triggers Toasts information about my goals on how I see the Radio feature working once
        *I learn how to make it fully functional.
        */
        assert aboutRadio != null;
        aboutRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutRadioToast(MainActivity.this, getString(R.string.about_radio_toast), Toast.LENGTH_LONG);
            }
        });

        /**
         * Sends user to the Podcast feature.
         */
        assert podcastSelector != null;
        podcastSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent podcastIntent = new Intent(MainActivity.this, Podcast.class);
                startActivity(podcastIntent);
            }
        });

        /**
         *Triggers Toasts information about my goals on how I see the Podcast feature working once
         *I learn how to make it fully functional.
         */
        assert aboutPodcast != null;
        aboutPodcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutPodcastToast(MainActivity.this, getString(R.string.about_podcast_toast), Toast.LENGTH_LONG);
            }
        });

        /**
         * In the full version this would trigger a sign-up pop-up but until then this creates a Toast
         * stating my future intentions.
         */
        assert signUp != null;
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpToast(MainActivity.this, getString(R.string.sign_up_toast), Toast.LENGTH_LONG);
            }
        });

        /**
         * In the full version this would trigger a login pop-up but until then this creates a Toast
         * stating my future intentions.
         */
        assert logIn != null;
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginToast(MainActivity.this, getString(R.string.log_in_toast), Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * Gets the phone orientation in order to adjust the toast position based on the results.
     */
    private void getOrientation() {
        int screenOrientation = getResources().getConfiguration().orientation;
        switch (screenOrientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                isPortrait = false;
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                isPortrait = true;
                break;
        }
    }

    /**
     *Layout settings for a Toasts  with information about my goals on how I see the Jukebox feature
     *working once I learn how to make it fully functional.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void aboutJukeboxToast(Context context, String msg, int duration){
        Toast toast = Toast.makeText(context,msg,duration);
        getOrientation();
        if (isPortrait){
            toast.setMargin(-.06f, .46f);
        } else {
            toast.setMargin(0,.6f);
        }
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.black_with_light_border_rounded_corners);
        view.setAlpha(.9f);
        TextView toastMessage = (TextView) view.findViewById(android.R.id.message);
        toastMessage.setTextColor(getColor(R.color.optionPageButtons));
        toastMessage.setPadding(24, 24, 24, 24);
        toast.show();
    }

    /**
     *Layout settings for a Toasts  with information about my goals on how I see the radio feature
     *working once I learn how to make it fully functional.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void aboutRadioToast(Context context, String msg, int duration){
        Toast toast = Toast.makeText(context,msg,duration);
        getOrientation();
        if (isPortrait){
            toast.setMargin(-.06f, .32f);
        } else {
            toast.setMargin(0,.325f);
        }
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.black_with_light_border_rounded_corners);
        view.setAlpha(.9f);
        TextView toastMessage = (TextView) view.findViewById(android.R.id.message);
        toastMessage.setTextColor(getColor(R.color.optionPageButtons));
        toastMessage.setPadding(24, 24, 24, 24);
        toast.show();
    }

    /**
     *Layout settings for a Toasts  with information about my goals on how I see the Podcast feature
     *working once I learn how to make it fully functional.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void aboutPodcastToast(Context context, String msg, int duration){
        Toast toast = Toast.makeText(context,msg,duration);
        getOrientation();
        if (isPortrait){
            toast.setMargin(-.06f, .17f);
        } else {
            toast.setMargin(0,.055f);
        }
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.black_with_light_border_rounded_corners);
        view.setAlpha(.9f);
        TextView toastMessage = (TextView) view.findViewById(android.R.id.message);
        toastMessage.setTextColor(getColor(R.color.optionPageButtons));
        toastMessage.setPadding(24, 24, 24, 24);
        toast.show();
    }

    /**
     *Layout settings for a Toast with information about my plans to add an actual login option once
     *I learn how.
     */

    @TargetApi(Build.VERSION_CODES.M)
    private void loginToast(Context context, String msg, int duration){
        Toast toast = Toast.makeText(context,msg,duration);
        getOrientation();
        if (isPortrait){
            toast.setMargin(0, .1f);
        } else {
            toast.setMargin(-.05f,.025f);
        }
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.black_with_light_border_rounded_corners);
        view.setAlpha(.9f);
        TextView toastMessage = (TextView) view.findViewById(android.R.id.message);
        toastMessage.setTextColor(getColor(R.color.optionPageButtons));
        toastMessage.setPadding(24, 24, 24, 24);
        toast.show();
    }

    /**
     *Layout settings for a Toast with information about plans to add an actual an actual sign up
     *form as soon as I learn how.
     */

    @TargetApi(Build.VERSION_CODES.M)
    private void signUpToast(Context context, String msg, int duration){
        Toast toast = Toast.makeText(context,msg,duration);
        getOrientation();
        if (isPortrait){
            toast.setMargin(0, .1f);
        } else {
            toast.setMargin(.2f,.025f);
        }
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.black_with_light_border_rounded_corners);
        view.setAlpha(.9f);
        TextView toastMessage = (TextView) view.findViewById(android.R.id.message);
        toastMessage.setTextColor(getColor(R.color.optionPageButtons));
        toastMessage.setPadding(24, 24, 24, 24);
        toast.show();
    }
}
