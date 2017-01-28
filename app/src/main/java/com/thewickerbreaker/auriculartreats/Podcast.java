package com.thewickerbreaker.auriculartreats;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Podcast extends AppCompatActivity {

    private LinearLayout homeLayout;
    private LinearLayout selectedLayout;
    private LinearLayout myShowsLayout;
    private LinearLayout findShowsLayout;
    private LinearLayout searchResultsLayout;
    private TextView podcastBack;
    private TextView homeTab;
    private TextView myShowsTab;
    private TextView findShowsTab;
    private TextView subscribeButton;
    private EditText podcastSearch;
    private boolean isPortrait;
    private Boolean isPlayButton = true;

    /**
     * Since the information pages would be populated by external information this ID system is only
     * in use for example purposes.
     *
     * Unassigned = 0
     * Home = 1
     * My Shows = 2
     * Find Shows = 3
     *
     */
    private int exampleID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);

        /**
         * Hides Keyboard when enter button is pushed.
         */
        View v = this.getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

        final ImageView continuePlaying = (ImageView) findViewById(R.id.podcast_last_listened_to_image);

        homeLayout = (LinearLayout) findViewById(R.id.podcast_home_container);
        myShowsLayout = (LinearLayout) findViewById(R.id.podcast_my_shows_container);
        selectedLayout = (LinearLayout) findViewById(R.id.podcast_information_container);
        findShowsLayout = (LinearLayout) findViewById(R.id.podcast_find_shows_container);
        searchResultsLayout = (LinearLayout) findViewById(R.id.podcast_search_results_container);

        podcastBack = (TextView) findViewById(R.id.back_text_button);
        homeTab = (TextView) findViewById(R.id.home_tab);
        myShowsTab = (TextView) findViewById(R.id.my_shows_tab);
        findShowsTab = (TextView) findViewById(R.id.find_shows_tab);
        subscribeButton = (TextView) findViewById(R.id.podcast_subscribe);
        TextView podcastGoButton = (TextView) findViewById(R.id.podcast_search_go_button);
        TextView donateButton = (TextView) findViewById(R.id.donate_button);

        podcastSearch = (EditText) findViewById(R.id.search_input);

        ImageView podRewind = (ImageView) findViewById(R.id.pod_previous_button);
        ImageView podStop = (ImageView) findViewById(R.id.pod_stop_button);
        final ImageView podPlayPause = (ImageView) findViewById(R.id.pod_play_pause_button);
        ImageView podForward = (ImageView) findViewById(R.id.pod_next_button);

        SeekBar podSeekBar = (SeekBar) findViewById(R.id.pod_seek);

        ImageView podLogoButton = (ImageView) findViewById(R.id.podcast_logo);

        /**
         * Allows users to click the main logo to return to the main selection/login page.
         */
        assert podLogoButton != null;
        podLogoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Podcast.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        /**
         * Brings up the Podcast Homepage when clicked and hides unrelated content.
         */
        homeTab.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {
                homeLayout.setVisibility(View.VISIBLE);
                myShowsLayout.setVisibility(View.GONE);
                homeTab.setTextColor(getColor(R.color.podcastDark));
                myShowsTab.setTextColor(getColor(R.color.podcastLight));
                findShowsTab.setTextColor(getColor(R.color.podcastLight));
                findShowsLayout.setVisibility(View.GONE);
                searchResultsLayout.setVisibility(View.GONE);
                selectedLayout.setVisibility(View.GONE);
                podcastBack.setVisibility(View.GONE);
            }
        });

        /**
         * Brings up the Podcast My Shows page when clicked and hides unrelated content.
         */
        myShowsTab.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                homeLayout.setVisibility(View.GONE);
                myShowsLayout.setVisibility(View.VISIBLE);
                homeTab.setTextColor(getColor(R.color.podcastLight));
                myShowsTab.setTextColor(getColor(R.color.podcastDark));
                findShowsTab.setTextColor(getColor(R.color.podcastLight));
                findShowsLayout.setVisibility(View.GONE);
                searchResultsLayout.setVisibility(View.GONE);
                selectedLayout.setVisibility(View.GONE);
                podcastBack.setVisibility(View.GONE);
            }
        });

        /**
         * Brings up the Podcast Find Shows page when clicked and hides unrelated content.
         */
        findShowsTab.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                homeLayout.setVisibility(View.GONE);
                myShowsLayout.setVisibility(View.GONE);
                homeTab.setTextColor(getColor(R.color.podcastLight));
                myShowsTab.setTextColor(getColor(R.color.podcastLight));
                findShowsTab.setTextColor(getColor(R.color.podcastDark));
                findShowsLayout.setVisibility(View.VISIBLE);
                searchResultsLayout.setVisibility(View.GONE);
                selectedLayout.setVisibility(View.GONE);
                podcastBack.setVisibility(View.GONE);
                podcastSearch.setText("");
            }
        });

        /**
         * In the finished product this would bring up the search results but for now it brings up a
         * Toast explaining my future intentions.
         */
        assert podcastGoButton != null;
        podcastGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert podcastSearch != null;
                String searchText = podcastSearch.getEditableText().toString();
                toastLayout(getApplicationContext(), getString(R.string.pod_search_toast, searchText), Toast.LENGTH_LONG);
                searchResultsLayout.setVisibility(View.VISIBLE);
            }
        });

        /**
         * Subscribes or unsubscribes users depending on their current status and changes the button
         * color and content to let the user know their current statur.
         */
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (subscribeButton.getText() == getString(R.string.subscribe)){
                    subscribeButton.setBackgroundResource(R.drawable.green_containers_with_dark_border);
                    subscribeButton.setTextColor(getColor(R.color.darkGreen));
                    subscribeButton.setText(R.string.unsubscribe);
                    subscribeButton.setPadding(16, 16, 16, 16);
                } else {
                    subscribeButton.setBackgroundResource(R.drawable.option_page_rounded_corners);
                    subscribeButton.setTextColor(getColor(R.color.podcastDark));
                    subscribeButton.setText(R.string.subscribe);
                    subscribeButton.setPadding(16, 16, 16, 16);
                }
            }
        });

        /**
         * Brings user back to the page they were on when they clicked on the podcast to get more
         * information.
         */
        podcastBack.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (exampleID == 1) {
                    homeLayout.setVisibility(View.VISIBLE);
                    selectedLayout.setVisibility(View.GONE);
                    podcastBack.setVisibility(View.GONE);
                    homeTab.setTextColor(getColor(R.color.podcastDark));
                    exampleID = 0;
                }else if (exampleID == 2){
                    myShowsLayout.setVisibility(View.VISIBLE);
                    selectedLayout.setVisibility(View.GONE);
                    podcastBack.setVisibility(View.GONE);
                    myShowsTab.setTextColor(getColor(R.color.podcastDark));
                    exampleID = 0;
                }else if (exampleID == 3){
                    findShowsLayout.setVisibility(View.VISIBLE);
                    selectedLayout.setVisibility(View.GONE);
                    podcastBack.setVisibility(View.GONE);
                    findShowsTab.setTextColor(getColor(R.color.podcastDark));
                    exampleID = 0;
                }
            }
        });

        /**
         * Allows users to pick up where they left off the last time they were logged in and listening.
         */
        assert continuePlaying != null;
        continuePlaying.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastLayout(getApplicationContext(), getString(R.string.continue_playing_toast), Toast.LENGTH_SHORT);
            }
        });

        /**
         *In the fully functioning app this will allow users to scrub back and fourth through their
         *music until then this Toasts the user what the seekbar is supposed to be doing.
         */
        assert podSeekBar != null;
        podSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                toastLayout(getApplicationContext(), getString(R.string.seekbar_toast), Toast.LENGTH_LONG);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        /**
         * This would allow the user to rewind the show in the finished product but for now it Toasts users the
         * future function of this button.
         */
        assert podRewind != null;
        podRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastLayout(getApplicationContext(), getString(R.string.pod_rewind_toast), Toast.LENGTH_SHORT);
            }
        });

        /**
         * This would allow the user to stop the show in the finished product but for now it Toasts users the
         * future function of this button.
         */
        assert podStop != null;
        podStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastLayout(getApplicationContext(), getString(R.string.pod_stop_toast), Toast.LENGTH_SHORT);
            }
        });

        /**
         * This would allow the user to pause or play the show depending on it's current status in
         * the finished product but for now it Toasts users the future function of this button.
         */
        assert podPlayPause != null;
        podPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlayButton) {
                    toastLayout(getApplicationContext(), getString(R.string.pod_play_toast), Toast.LENGTH_SHORT);
                    isPlayButton = false;
                    podPlayPause.setImageResource(R.drawable.pod_pause_button);
                } else {
                    isPlayButton = true;
                    toastLayout(getApplicationContext(), getString(R.string.pod_pause_toast), Toast.LENGTH_SHORT);
                    podPlayPause.setImageResource(R.drawable.pod_play_button);
                }
            }
        });

        /**
         * This would allow the user to fast forward the show in the finished product but for now it Toasts users the
         * future function of this button.
         */
        assert podForward != null;
        podForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastLayout(getApplicationContext(), getString(R.string.pod_forward_toast), Toast.LENGTH_SHORT);
            }
        });

        /**
         * Directs users to the website of the current playing podcast to allow them to donate if they
         * would like to.
         */
        assert donateButton != null;
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastLayout(getApplicationContext(), getString(R.string.pod_donate_toast), Toast.LENGTH_SHORT);
            }
        });

    }

    /**
     * Sends user to the example page now but in the future this would send users to an information
     * page about the podcast they've selected.
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void podcastHomeInfoExample(View view) {
        homeLayout.setVisibility(View.GONE);
        selectedLayout.setVisibility(View.VISIBLE);
        podcastBack.setVisibility(View.VISIBLE);
        homeTab.setTextColor(getColor(R.color.podcastLight));
        exampleID = 1;
    }

    /**
     * Sends user to the example page now but in the future this would send users to an information
     * page about the podcast they've selected.
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void podcastMyShowsInfoExample(View view) {
        myShowsLayout.setVisibility(View.GONE);
        selectedLayout.setVisibility(View.VISIBLE);
        podcastBack.setVisibility(View.VISIBLE);
        myShowsTab.setTextColor(getColor(R.color.podcastLight));
        exampleID = 2;
    }

    /**
     * Sends user to the example page now but in the future this would send users to an information
     * page about the podcast they've selected.
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void podcastFindShowsInfoExample(View view) {
        findShowsLayout.setVisibility(View.GONE);
        selectedLayout.setVisibility(View.VISIBLE);
        podcastBack.setVisibility(View.VISIBLE);
        findShowsTab.setTextColor(getColor(R.color.podcastLight));
        exampleID = 3;
    }

    /**
     * In the real app this would start the download process but for now it Toasts the user with my
     * intentions for the future functionality of this app.
     */
    public void downloadClicked(View view) {

        toastLayout(getApplicationContext(), getString(R.string.downloadingToast), Toast.LENGTH_SHORT);
    }

    /**
     * This Toast the description of the show that is cut off due to container size restraints.
     */
    public void episodeDetailsClicked(View view) {
        toastLayout(getApplicationContext(), getString(R.string.episodeDetailsToast), Toast.LENGTH_SHORT);
    }

    /**
     * Toast style and positioning.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void toastLayout(Context context, String msg, int duration) {
        Toast toast = Toast.makeText(context, msg, duration);
        getOrientation();
        if (isPortrait){
            toast.setMargin(0, .25f);
        } else {
            toast.setMargin(0, .25f);
        }
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.podcast_containers_with_dark_border);
        view.setAlpha(.95f);
        TextView toastMessage = (TextView) view.findViewById(android.R.id.message);
        toastMessage.setTextColor(getColor(R.color.podcastWhite));
        toastMessage.setPadding(24, 24, 24, 24);
        toast.show();
    }

    /**
     * determines if the phone is portrait or landscape.
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
}
