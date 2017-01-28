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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Jukebox extends AppCompatActivity {

    /**
     * Boolean to tell whether the play button or the pause button is being displayed.
     */
    private Boolean isPlayButton = true;
    private Boolean isPortrait = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jukebox);

        final LinearLayout nowPlayingContainerP = (LinearLayout) findViewById(R.id.now_playing_container_jbp);
        final LinearLayout playlistContainerP = (LinearLayout) findViewById(R.id.playlist_container_jbp);
        final LinearLayout addMusicContainerP = (LinearLayout) findViewById(R.id.add_music_container_jpd);

        Button aboutNowPlayingP = (Button) findViewById(R.id.now_playing_about_button);
        Button aboutPlaylistP = (Button) findViewById(R.id.playlist_about_button);

        final SeekBar seekBarP = (SeekBar) findViewById(R.id.seek_jbp);

        ImageButton previousButtonP = (ImageButton) findViewById(R.id.previous_button_jbp);
        ImageButton stopButtonP = (ImageButton) findViewById(R.id.stop_button_jbp);
        final ImageButton playButtonP = (ImageButton) findViewById(R.id.play_pause_button_jbp);
        ImageButton nextButtonP = (ImageButton) findViewById(R.id.next_button_jbp);

        final TextView nowPlayingP = (TextView) findViewById(R.id.now_playing_tab_jbp);
        final TextView playlistP = (TextView) findViewById(R.id.playlist_tab_jbp);
        final TextView addMusicP = (TextView) findViewById(R.id.add_music_tab_jbp);

        TextView topLeftEdgePrev = (TextView) findViewById(R.id.top_left_edge_prev);
        TextView topLeftEdgeNext = (TextView) findViewById(R.id.top_left_edge_next);
        final TextView artistOneHeader = (TextView) findViewById(R.id.artist_1);
        final ImageView albumArtOne = (ImageView) findViewById(R.id.album_art_1);
        final TextView albumOneFooter = (TextView) findViewById(R.id.album_1);

        TextView bottomLeftEdgePrev = (TextView) findViewById(R.id.bottom_left_edge_prev);
        TextView bottomLeftEdgeNext = (TextView) findViewById(R.id.bottom_left_edge_next);
        final TextView artistTwoHeader = (TextView) findViewById(R.id.artist_2);
        final ImageView albumArtTwo = (ImageView) findViewById(R.id.album_art_2);
        final TextView albumTwoFooter = (TextView) findViewById(R.id.album_2);

        TextView topRightEdgePrev = (TextView) findViewById(R.id.top_right_edge_prev);
        TextView topRightEdgeNext = (TextView) findViewById(R.id.top_right_edge_next);
        final TextView artistThreeHeader = (TextView) findViewById(R.id.artist_3);
        final ImageView albumArtThree = (ImageView) findViewById(R.id.album_art_3);
        final TextView albumThreeFooter = (TextView) findViewById(R.id.album_3);

        TextView bottomRightEdgePrev = (TextView) findViewById(R.id.bottom_right_edge_prev);
        TextView bottomRightEdgeNext = (TextView) findViewById(R.id.bottom_right_edge_next);
        final TextView artistFourHeader = (TextView) findViewById(R.id.artist_4);
        final ImageView albumArtFour = (ImageView) findViewById(R.id.album_art_4);
        final TextView albumFourFooter = (TextView) findViewById(R.id.album_4);

        LinearLayout topLeftEdge = (LinearLayout) findViewById(R.id.topLeftEdgeContainer);
        LinearLayout topRightEdge = (LinearLayout) findViewById(R.id.topRightEdgeContainer);
        LinearLayout bottomLeftEdge = (LinearLayout) findViewById(R.id.bottomLeftEdgeContainer);
        LinearLayout bottomRightEdge = (LinearLayout) findViewById(R.id.bottomRightEdgeContainer);

        final LinearLayout selectedAlbumContent = (LinearLayout) findViewById(R.id.selected_album_container_jpd);
        Button aboutSelectedAlbum = (Button) findViewById(R.id.slected_music_about_button);
        final ImageView selectedAlbumArt = (ImageView) findViewById(R.id.sample_album);

        final EditText searchTextField = (EditText) findViewById(R.id.search_text_field);
        TextView goButton = (TextView) findViewById(R.id.go_text_button);
        TextView jukeboxBack = (TextView) findViewById(R.id.jukebox_back_button);

        TextView aboutAddMusic = (TextView) findViewById(R.id.add_music_about_button);

        /**
         * Hides Keyboard when enter button is pushed.
         */

        View v = this.getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

        ImageView jukeboxLogoButton = (ImageView) findViewById(R.id.jukebox_logo);

        /**
         * Allows users to click the main logo to return to the main selection/login page.
         */
        assert jukeboxLogoButton != null;
        jukeboxLogoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Jukebox.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });
        /**
         *This question mark button and Toast will not be part of the fully functional app but for
         *now it Toasts my intention for how I see the Now Playing feature will work in the future.
         */
        assert aboutNowPlayingP != null;
        aboutNowPlayingP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightPortraitToast(Jukebox.this, getString(R.string.about_now_playing_toast), Toast.LENGTH_LONG);
            }
        });

        /**
         *This question mark button and Toast will not be part of the fully functional app but for
         *now it Toasts my intention for how I see the Playlist feature will work in the future.
         */
        assert aboutPlaylistP != null;
        aboutPlaylistP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightPortraitToast(Jukebox.this, getString(R.string.about_playlist_toast), Toast.LENGTH_LONG);
            }
        });

        assert jukeboxBack != null;
        jukeboxBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert selectedAlbumContent != null;
                selectedAlbumContent.setVisibility(View.GONE);
                assert addMusicContainerP != null;
                addMusicContainerP.setVisibility(View.VISIBLE);
            }
        });

        /**
         *In the fully functioning app this will allow users to scrub back and fourth through their
         *music until then this Toasts the user what the seekbar is supposed to be doing.
         */
        assert seekBarP != null;
        seekBarP.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                lightPortraitControlsToast(Jukebox.this, getString(R.string.seekbar_toast), Toast.LENGTH_LONG);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        /**
         *In the fully functioning app this will allow users to replay the previous song, until then
         *this Toasts the user what the rewind is supposed to be doing.
         */
        assert previousButtonP != null;
        previousButtonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightPortraitControlsToast(Jukebox.this, getString(R.string.previous_toast), Toast.LENGTH_LONG);
            }
        });

        /**
         *In the fully functioning app this will allow users to stop the song that is playing, until
         *then this Toasts the user what the stop is supposed to be doing.
         */
        assert stopButtonP != null;
        stopButtonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightPortraitControlsToast(Jukebox.this, getString(R.string.stop_toast), Toast.LENGTH_LONG);
            }
        });

        /**
         *In the fully functioning app this will allow users to play or pause the song being played,
         *until then this Toasts the user what the play/pause is supposed to be doing.
         */
        assert playButtonP != null;
        playButtonP.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (isPlayButton) {
                    lightPortraitControlsToast(Jukebox.this, getString(R.string.play_toast), Toast.LENGTH_LONG);
                    isPlayButton = false;
                    playButtonP.setImageResource(R.drawable.pause_button);
                } else {
                    isPlayButton = true;
                    lightPortraitControlsToast(Jukebox.this, getString(R.string.pause_toast), Toast.LENGTH_LONG);
                    playButtonP.setImageResource(R.drawable.play_button);
                }
            }
        });

        /**
         *In the fully functioning app this will allow users to skip to the next song, until then
         *this Toasts the user what the fast forward is supposed to be doing.
         */
        assert nextButtonP != null;
        nextButtonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightPortraitControlsToast(Jukebox.this, getString(R.string.nest_toast), Toast.LENGTH_LONG);
            }
        });

        /**
         * Provides information about the song that is currently playing.
         */
        assert nowPlayingP != null;
        nowPlayingP.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                assert nowPlayingContainerP != null;
                nowPlayingContainerP.setVisibility(View.VISIBLE);
                assert playlistContainerP != null;
                playlistContainerP.setVisibility(View.GONE);
                assert addMusicContainerP != null;
                addMusicContainerP.setVisibility(View.GONE);
                assert selectedAlbumContent != null;
                selectedAlbumContent.setVisibility(View.GONE);
                nowPlayingP.setBackgroundResource(R.drawable.gray_rounded_bottom_left_corners);
                nowPlayingP.setTextColor(getColor(R.color.offBlackForText));
                assert playlistP != null;
                playlistP.setBackgroundResource(R.drawable.black_nonrounded_corners);
                playlistP.setTextColor(getColor(R.color.optionPageButtons));
                assert addMusicP != null;
                addMusicP.setBackgroundResource(R.drawable.black_rounded_bottom_right_corners);
                addMusicP.setTextColor(getColor(R.color.optionPageButtons));
            }
        });

        /**
         *Provides information about the last three songs played, the current song, and the next ten
         *songs in the playlist.
         */
        assert playlistP != null;
        playlistP.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                assert nowPlayingContainerP != null;
                nowPlayingContainerP.setVisibility(View.GONE);
                assert playlistContainerP != null;
                playlistContainerP.setVisibility(View.VISIBLE);
                assert addMusicContainerP != null;
                addMusicContainerP.setVisibility(View.GONE);
                assert selectedAlbumContent != null;
                selectedAlbumContent.setVisibility(View.GONE);
                nowPlayingP.setBackgroundResource(R.drawable.black_rounded_bottom_left_corners);
                nowPlayingP.setTextColor(getColor(R.color.optionPageButtons));
                playlistP.setBackgroundResource(R.drawable.gray_nonrounded_corners);
                playlistP.setTextColor(getColor(R.color.offBlackForText));
                assert addMusicP != null;
                addMusicP.setBackgroundResource(R.drawable.black_rounded_bottom_right_corners);
                addMusicP.setTextColor(getColor(R.color.optionPageButtons));
            }
        });

        /**
         *This will eventual collect all the information about the songs loaded on the users for and
         * allow them to flip through albums jukebox style to find new music to add to their playlist.
         */
        assert addMusicP != null;
        addMusicP.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                assert nowPlayingContainerP != null;
                nowPlayingContainerP.setVisibility(View.GONE);
                assert playlistContainerP != null;
                playlistContainerP.setVisibility(View.GONE);
                assert addMusicContainerP != null;
                addMusicContainerP.setVisibility(View.VISIBLE);
                assert selectedAlbumContent != null;
                selectedAlbumContent.setVisibility(View.GONE);
                nowPlayingP.setBackgroundResource(R.drawable.black_rounded_bottom_left_corners);
                nowPlayingP.setTextColor(getColor(R.color.optionPageButtons));
                playlistP.setBackgroundResource(R.drawable.black_nonrounded_corners);
                playlistP.setTextColor(getColor(R.color.optionPageButtons));
                addMusicP.setBackgroundResource(R.drawable.gray_rounded_bottom_right_corners);
                addMusicP.setTextColor(getColor(R.color.offBlackForText));
            }
        });

        /**
         * This will allow users to scroll through the albums located in the top left corner of the
         * Jukebox to find new music to play.
         */
        assert topLeftEdgePrev != null;
        topLeftEdgePrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert artistOneHeader != null;
                if (artistOneHeader.getText() == getString(R.string.armchair_martian)){
                    artistOneHeader.setText(R.string.lagwagon);
                    assert albumArtOne != null;
                    albumArtOne.setImageResource(R.drawable.lagwagon);
                    assert albumOneFooter != null;
                    albumOneFooter.setText(R.string.hoss);
                } else if (artistOneHeader.getText() == getString(R.string.lagwagon)){
                    artistOneHeader.setText(R.string.bad_religion);
                    assert albumArtOne != null;
                    albumArtOne.setImageResource(R.drawable.bad_religion);
                    assert albumOneFooter != null;
                    albumOneFooter.setText(R.string.recipe_for_hate);
                } else if (artistOneHeader.getText() == getString(R.string.bad_religion)){
                    artistOneHeader.setText(R.string.armchair_martian);
                    assert albumArtOne != null;
                    albumArtOne.setImageResource(R.drawable.bad_astronaut_vs_armchair_martian_small);
                    assert albumOneFooter != null;
                    albumOneFooter.setText(R.string.war_of_the_worlds);
                }
            }
        });

        /**
         * This will allow users to scroll through the albums located in the top left corner of the
         * Jukebox to find new music to play. This will be simplified in the fully functioning app
         * as the content will be collected through the media files and not sample albums that have
         * been manually included.
         */
        assert topLeftEdgeNext != null;
        topLeftEdgeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert artistOneHeader != null;
                if (artistOneHeader.getText() == getString(R.string.bad_religion)){
                    artistOneHeader.setText(R.string.lagwagon);
                    assert albumArtOne != null;
                    albumArtOne.setImageResource(R.drawable.lagwagon);
                    assert albumOneFooter != null;
                    albumOneFooter.setText(R.string.hoss);
                } else if (artistOneHeader.getText() == getString(R.string.armchair_martian)){
                    artistOneHeader.setText(R.string.bad_religion);
                    assert albumArtOne != null;
                    albumArtOne.setImageResource(R.drawable.bad_religion);
                    assert albumOneFooter != null;
                    albumOneFooter.setText(R.string.recipe_for_hate);
                } else if (artistOneHeader.getText() == getString(R.string.lagwagon)){
                    artistOneHeader.setText(R.string.armchair_martian);
                    assert albumArtOne != null;
                    albumArtOne.setImageResource(R.drawable.bad_astronaut_vs_armchair_martian_small);
                    assert albumOneFooter != null;
                    albumOneFooter.setText(R.string.war_of_the_worlds);
                }
            }
        });

        /**
         * This will allow users to scroll through the albums located in the bottom left corner of the
         * Jukebox to find new music to play.
         */
        assert bottomLeftEdgePrev != null;
        bottomLeftEdgePrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert artistTwoHeader != null;
                if (artistTwoHeader.getText() == getString(R.string.grenadine)){
                    artistTwoHeader.setText(R.string.weird_al);
                    assert albumArtTwo != null;
                    albumArtTwo.setImageResource(R.drawable.weird_al);
                    assert albumTwoFooter != null;
                    albumTwoFooter.setText(R.string.uhf);
                } else if (artistTwoHeader.getText() == getString(R.string.weird_al)){
                    artistTwoHeader.setText(R.string.willie_nelson);
                    assert albumArtTwo != null;
                    albumArtTwo.setImageResource(R.drawable.willie_nelson);
                    assert albumTwoFooter != null;
                    albumTwoFooter.setText(R.string.greatest_hits);
                } else if (artistTwoHeader.getText() == getString(R.string.willie_nelson)){
                    artistTwoHeader.setText(R.string.grenadine);
                    assert albumArtTwo != null;
                    albumArtTwo.setImageResource(R.drawable.grenadine);
                    assert albumTwoFooter != null;
                    albumTwoFooter.setText(R.string.nopalitos);
                }
            }
        });

        /**
         * This will allow users to scroll through the albums located in the bottom left corner of the
         * Jukebox to find new music to play.
         */
        assert bottomLeftEdgeNext != null;
        bottomLeftEdgeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert artistTwoHeader != null;
                if (artistTwoHeader.getText() == getString(R.string.willie_nelson)){
                    artistTwoHeader.setText(R.string.weird_al);
                    assert albumArtTwo != null;
                    albumArtTwo.setImageResource(R.drawable.weird_al);
                    assert albumTwoFooter != null;
                    albumTwoFooter.setText(R.string.uhf);
                } else if (artistTwoHeader.getText() == getString(R.string.grenadine)){
                    artistTwoHeader.setText(R.string.willie_nelson);
                    assert albumArtTwo != null;
                    albumArtTwo.setImageResource(R.drawable.willie_nelson);
                    assert albumTwoFooter != null;
                    albumTwoFooter.setText(R.string.greatest_hits);
                } else if (artistTwoHeader.getText() == getString(R.string.weird_al)){
                    artistTwoHeader.setText(R.string.grenadine);
                    assert albumArtTwo != null;
                    albumArtTwo.setImageResource(R.drawable.grenadine);
                    assert albumTwoFooter != null;
                    albumTwoFooter.setText(R.string.nopalitos);
                }
            }
        });

        /**
         * This will allow users to scroll through the albums located in the top right corner of the
         * Jukebox to find new music to play.
         */
        assert topRightEdgePrev != null;
        topRightEdgePrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert artistThreeHeader != null;
                if (artistThreeHeader.getText() == getString(R.string.das_efx)){
                    artistThreeHeader.setText(R.string.pharcyde);
                    assert albumArtThree != null;
                    albumArtThree.setImageResource(R.drawable.pharcyde);
                    assert albumThreeFooter != null;
                    albumThreeFooter.setText(R.string.bizarre_ride);
                } else if (artistThreeHeader.getText() == getString(R.string.pharcyde)){
                    artistThreeHeader.setText(R.string.son_of_bazerk);
                    assert albumArtThree != null;
                    albumArtThree.setImageResource(R.drawable.son_of_bazerk);
                    assert albumThreeFooter != null;
                    albumThreeFooter.setText(R.string.bazerk);
                } else if (artistThreeHeader.getText() == getString(R.string.son_of_bazerk)){
                    artistThreeHeader.setText(R.string.das_efx);
                    assert albumArtThree != null;
                    albumArtThree.setImageResource(R.drawable.dasefx);
                    assert albumThreeFooter != null;
                    albumThreeFooter.setText(R.string.dead_serious);
                }
            }
        });

        /**
         * This will allow users to scroll through the albums located in the top right corner of the
         * Jukebox to find new music to play.
         */
        assert topRightEdgeNext != null;
        topRightEdgeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert artistThreeHeader != null;
                if (artistThreeHeader.getText() == getString(R.string.son_of_bazerk)){
                    artistThreeHeader.setText(R.string.pharcyde);
                    assert albumArtThree != null;
                    albumArtThree.setImageResource(R.drawable.pharcyde);
                    assert albumThreeFooter != null;
                    albumThreeFooter.setText(R.string.bizarre_ride);
                } else if (artistThreeHeader.getText() == getString(R.string.das_efx)){
                    artistThreeHeader.setText(R.string.son_of_bazerk);
                    assert albumArtThree != null;
                    albumArtThree.setImageResource(R.drawable.son_of_bazerk);
                    assert albumThreeFooter != null;
                    albumThreeFooter.setText(R.string.bazerk);
                } else if (artistThreeHeader.getText() == getString(R.string.pharcyde)){
                    artistThreeHeader.setText(R.string.das_efx);
                    assert albumArtThree != null;
                    albumArtThree.setImageResource(R.drawable.dasefx);
                    assert albumThreeFooter != null;
                    albumThreeFooter.setText(R.string.dead_serious);
                }
            }
        });

        /**
         * This will allow users to scroll through the albums located in the bottom right corner of the
         * Jukebox to find new music to play.
         */
        assert bottomRightEdgePrev != null;
        bottomRightEdgePrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert artistFourHeader != null;
                if (artistFourHeader.getText() == getString(R.string.even_in_blackouts)){
                    artistFourHeader.setText(R.string.toadies);
                    assert albumArtFour != null;
                    albumArtFour.setImageResource(R.drawable.toadie);
                    assert albumFourFooter != null;
                    albumFourFooter.setText(R.string.rubberneck);
                } else if (artistFourHeader.getText() == getString(R.string.toadies)){
                    artistFourHeader.setText(R.string.swingin_utters);
                    assert albumArtFour != null;
                    albumArtFour.setImageResource(R.drawable.swingin_utters);
                    assert albumFourFooter != null;
                    albumFourFooter.setText(R.string.five_lessons_learned);
                } else if (artistFourHeader.getText() == getString(R.string.swingin_utters)){
                    artistFourHeader.setText(R.string.even_in_blackouts);
                    assert albumArtFour != null;
                    albumArtFour.setImageResource(R.drawable.even_in_blackouts);
                    assert albumFourFooter != null;
                    albumFourFooter.setText(R.string.foreshadows_on_the_wall);
                }
            }
        });

        /**
         * This will allow users to scroll through the albums located in the bottom right corner of the
         * Jukebox to find new music to play.
         */
        assert bottomRightEdgeNext != null;
        bottomRightEdgeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert artistFourHeader != null;
                if (artistFourHeader.getText() == getString(R.string.swingin_utters)){
                    artistFourHeader.setText(R.string.toadies);
                    assert albumArtFour != null;
                    albumArtFour.setImageResource(R.drawable.toadie);
                    assert albumFourFooter != null;
                    albumFourFooter.setText(R.string.rubberneck);
                } else if (artistFourHeader.getText() == getString(R.string.even_in_blackouts)){
                    artistFourHeader.setText(R.string.swingin_utters);
                    assert albumArtFour != null;
                    albumArtFour.setImageResource(R.drawable.swingin_utters);
                    assert albumFourFooter != null;
                    albumFourFooter.setText(R.string.five_lessons_learned);
                } else if (artistFourHeader.getText() == getString(R.string.toadies)){
                    artistFourHeader.setText(R.string.even_in_blackouts);
                    assert albumArtFour != null;
                    albumArtFour.setImageResource(R.drawable.even_in_blackouts);
                    assert albumFourFooter != null;
                    albumFourFooter.setText(R.string.foreshadows_on_the_wall);
                }
            }
        });

        /**
         * Clicking this takes users to see the songs available to select from the album in the
         * top left corner of the Jukebox Add Music Page.
         */
        assert topLeftEdge != null;
        topLeftEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert addMusicContainerP != null;
                addMusicContainerP.setVisibility(View.GONE);
                assert selectedAlbumContent != null;
                selectedAlbumContent.setVisibility(View.VISIBLE);
                assert selectedAlbumArt != null;
                selectedAlbumArt.setImageResource(R.drawable.album_sample_one);
            }
        });

        /**
         * Clicking this takes users to see the songs available to select from the album in the
         * top right corner of the Jukebox Add Music Page.
         */
        assert topRightEdge != null;
        topRightEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert addMusicContainerP != null;
                addMusicContainerP.setVisibility(View.GONE);
                assert selectedAlbumContent != null;
                selectedAlbumContent.setVisibility(View.VISIBLE);
                assert selectedAlbumArt != null;
                selectedAlbumArt.setImageResource(R.drawable.album_sample_two);
            }
        });

        /**
         * Clicking this takes users to see the songs available to select from the album in the
         * bottom left corner of the Jukebox Add Music Page.
         */
        assert bottomLeftEdge != null;
        bottomLeftEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert addMusicContainerP != null;
                addMusicContainerP.setVisibility(View.GONE);
                assert selectedAlbumContent != null;
                selectedAlbumContent.setVisibility(View.VISIBLE);
                assert selectedAlbumArt != null;
                selectedAlbumArt.setImageResource(R.drawable.album_sample_three);
            }
        });

        /**
         * Clicking this takes users to see the songs available to select from the album in the
         * bottom right corner of the Jukebox Add Music Page.
         */
        assert bottomRightEdge != null;
        bottomRightEdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert addMusicContainerP != null;
                addMusicContainerP.setVisibility(View.GONE);
                assert selectedAlbumContent != null;
                selectedAlbumContent.setVisibility(View.VISIBLE);
                assert selectedAlbumArt != null;
                selectedAlbumArt.setImageResource(R.drawable.album_sample_four);
            }
        });

        /**
         * Triggers the text based search method.
         */
        assert goButton != null;
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert searchTextField != null;
                String searchText = String.valueOf(searchTextField.getEditableText());
                lightPortraitToast(Jukebox.this, getString(R.string.go_button_toast, searchText), Toast.LENGTH_LONG);
            }
        });

        /**
         *This question mark button and Toast will not be part of the fully functional app but for
         *now it Toasts my intention for how I see the Add Music feature will work in the future.
         */
        assert aboutAddMusic != null;
        aboutAddMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightPortraitToast(Jukebox.this, getString(R.string.about_add_music_toast), Toast.LENGTH_LONG);
            }
        });

        /**
         *This question mark button and Toast will not be part of the fully functional app but for
         *now it Toasts my intention for how I see the Selected Music feature will work in the future.
         */
        assert aboutSelectedAlbum != null;
        aboutSelectedAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightPortraitToast(Jukebox.this, getString(R.string.about_selected_album_toast), Toast.LENGTH_LONG);
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
     *Layout for the "About" question mark Toasts that will not be part of the fully functional app.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void lightPortraitToast(Context context, String msg, int duration) {
        Toast toast = Toast.makeText(context, msg, duration);
        getOrientation();
        if (isPortrait){
            toast.setMargin(0, .25f);
        } else {
            toast.setMargin(0, .15f);
        }
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.option_page_rounded_corners);
        view.setAlpha(.9f);
        TextView toastMessage = (TextView) view.findViewById(android.R.id.message);
        toastMessage.setTextColor(getColor(R.color.offBlackForText));
        toastMessage.setPadding(24, 24, 24, 24);
        toast.show();
    }

    /**
     *Layout for the Jukebox controls Toasts that will not be part of the fully functional app.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void lightPortraitControlsToast(Context context, String msg, int duration) {
        Toast toast = Toast.makeText(context, msg, duration);
        getOrientation();
        if (isPortrait){
            toast.setMargin(0, .25f);
        } else {
            toast.setMargin(0, .325f);
        }
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.option_page_rounded_corners);
        view.setAlpha(.9f);
        TextView toastMessage = (TextView) view.findViewById(android.R.id.message);
        toastMessage.setTextColor(getColor(R.color.offBlackForText));
        toastMessage.setPadding(24, 24, 24, 24);
        toast.show();
    }
}
