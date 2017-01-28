package com.thewickerbreaker.auriculartreats;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Radio extends AppCompatActivity {

    private Boolean isPortrait = false;
    private int fmStation = 1;
    private int amStation = 6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        final View fmStationLine1 = findViewById(R.id.large_station_line_1);
        final View fmStationLine2 = findViewById(R.id.large_station_line_2);
        final View fmStationLine3 = findViewById(R.id.large_station_line_3);
        final View fmStationLine4 = findViewById(R.id.large_station_line_4);
        final View fmStationLine5 = findViewById(R.id.large_station_line_5);
        final View fmStationLine6 = findViewById(R.id.large_station_line_6);
        final View fmStationLine7 = findViewById(R.id.large_station_line_7);

        final View amStationLine1 = findViewById(R.id.small_station_line_1);
        final View amStationLine2 = findViewById(R.id.small_station_line_2);
        final View amStationLine3 = findViewById(R.id.small_station_line_3);
        final View amStationLine4 = findViewById(R.id.small_station_line_4);
        final View amStationLine5 = findViewById(R.id.small_station_line_5);
        final View amStationLine6 = findViewById(R.id.small_station_line_6);

        final ImageView tuningUp = (ImageView) findViewById(R.id.stationUp);
        ImageView tuningDown = (ImageView) findViewById(R.id.stationDown);

        final Switch amfmSwtich = (Switch) findViewById(R.id.am_fm_switch);

        ImageView volumeUp = (ImageView) findViewById(R.id.volumeUp);
        ImageView volumeDown = (ImageView) findViewById(R.id.volumeDown);

        TextView songInfo = (TextView) findViewById(R.id.radio_song_information);
        assert songInfo != null;
        songInfo.setSelected(true);

        ImageButton radioLike = (ImageButton) findViewById(R.id.like_button);
        ImageButton radioDislike = (ImageButton) findViewById(R.id.dislike_button);

        TextView radioBuyButton = (TextView) findViewById(R.id.buy_button);

        ImageView radioLogoButton = (ImageView) findViewById(R.id.radio_logo_image);

        /**
         * Allows users to click the main logo to return to the main selection/login page.
         */
        assert radioLogoButton != null;
        radioLogoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Radio.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });

        /**
         * Turns up the volume when the volume up button it clicked.
         */
        assert volumeUp != null;
        volumeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
            }
        });

        /**
         * Turns down the volume when the volume up button it clicked.
         */
        assert volumeDown != null;
        volumeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audio.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
            }
        });

        /**
         * Creates default display for the radio page.
         */
        assert amfmSwtich != null;
        amfmSwtich.setChecked(false);
        fmStation = 1;
        assert fmStationLine1 != null;
        fmStationLine1.setBackgroundResource(R.color.radioDialGreen);
        getOrientation();
        if (isPortrait) {
            fmStationLine1.setScaleX(3);
        } else {
            fmStationLine1.setScaleY(3);
        }

        /**
         * Changes radio default setting for when the radio is switched between AM and FM.
         */
        amfmSwtich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (amfmSwtich.isChecked()) {
                    amStation = 6;
                    assert amStationLine6 != null;
                    amStationLine6.setBackgroundResource(R.color.radioDialGreen);
                    getOrientation();
                    if (isPortrait) {
                        amStationLine6.setScaleX(3);
                    } else {
                        amStationLine6.setScaleY(3);
                    }
                    fmStationLine1.setBackgroundResource(R.color.optionPageButtons);
                    assert fmStationLine2 != null;
                    fmStationLine2.setBackgroundResource(R.color.optionPageButtons);
                    assert fmStationLine3 != null;
                    fmStationLine3.setBackgroundResource(R.color.optionPageButtons);
                    assert fmStationLine4 != null;
                    fmStationLine4.setBackgroundResource(R.color.optionPageButtons);
                    assert fmStationLine5 != null;
                    fmStationLine5.setBackgroundResource(R.color.optionPageButtons);
                    assert fmStationLine6 != null;
                    fmStationLine6.setBackgroundResource(R.color.optionPageButtons);
                    assert fmStationLine7 != null;
                    fmStationLine7.setBackgroundResource(R.color.optionPageButtons);
                    fmStationLine1.setScaleX(1);
                    fmStationLine2.setScaleX(1);
                    fmStationLine3.setScaleX(1);
                    fmStationLine4.setScaleX(1);
                    fmStationLine5.setScaleX(1);
                    fmStationLine6.setScaleX(1);
                    fmStationLine7.setScaleX(1);
                    fmStationLine1.setScaleY(1);
                    fmStationLine2.setScaleY(1);
                    fmStationLine3.setScaleY(1);
                    fmStationLine4.setScaleY(1);
                    fmStationLine5.setScaleY(1);
                    fmStationLine6.setScaleY(1);
                    fmStationLine7.setScaleY(1);
                } else {
                    fmStation = 1;
                    fmStationLine1.setBackgroundResource(R.color.radioDialGreen);
                    getOrientation();
                    if (isPortrait) {
                        fmStationLine1.setScaleX(3);
                    } else {
                        fmStationLine1.setScaleY(3);
                    }
                    assert amStationLine1 != null;
                    amStationLine1.setBackgroundResource(R.color.optionPageButtons);
                    assert amStationLine2 != null;
                    amStationLine2.setBackgroundResource(R.color.optionPageButtons);
                    assert amStationLine3 != null;
                    amStationLine3.setBackgroundResource(R.color.optionPageButtons);
                    assert amStationLine4 != null;
                    amStationLine4.setBackgroundResource(R.color.optionPageButtons);
                    assert amStationLine5 != null;
                    amStationLine5.setBackgroundResource(R.color.optionPageButtons);
                    assert amStationLine6 != null;
                    amStationLine6.setBackgroundResource(R.color.optionPageButtons);
                    amStationLine1.setScaleX(1);
                    amStationLine2.setScaleX(1);
                    amStationLine3.setScaleX(1);
                    amStationLine4.setScaleX(1);
                    amStationLine5.setScaleX(1);
                    amStationLine6.setScaleX(1);
                    amStationLine1.setScaleY(1);
                    amStationLine2.setScaleY(1);
                    amStationLine3.setScaleY(1);
                    amStationLine4.setScaleY(1);
                    amStationLine5.setScaleY(1);
                    amStationLine6.setScaleY(1);
                }
            }
        });

        /**
         * Scrolls up through stations when the tuning up button is clicked. These stations and genres
         * will be customizable in the fully functioning app.
         */
        assert tuningUp != null;
        tuningUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amfmSwtich.isChecked()) {
                    if (amStation <= 1) {
                        amStation = 1;
                        radioStationToast(getApplicationContext(), getString(R.string.golden_oldies), Toast.LENGTH_SHORT);
                        assert amStationLine1 != null;
                        amStationLine1.setBackgroundResource(R.color.radioDialGreen);
                        if (isPortrait) {
                            amStationLine1.setScaleX(3);
                        } else {
                            amStationLine1.setScaleY(3);
                        }
                    } else if (amStation == 2) {
                        amStation = amStation - 1;
                        radioStationToast(getApplicationContext(), getString(R.string.golden_oldies), Toast.LENGTH_SHORT);
                        assert amStationLine1 != null;
                        amStationLine1.setBackgroundResource(R.color.radioDialGreen);
                        assert amStationLine2 != null;
                        amStationLine2.setBackgroundResource(R.color.optionPageButtons);
                        getOrientation();
                        if (isPortrait) {
                            amStationLine1.setScaleX(3);
                            amStationLine2.setScaleX(1);
                        } else {
                            amStationLine1.setScaleY(3);
                            amStationLine2.setScaleY(1);
                        }
                    } else if (amStation == 3) {
                        amStation = amStation - 1;
                        radioStationToast(getApplicationContext(), getString(R.string.classical), Toast.LENGTH_SHORT);
                        assert amStationLine2 != null;
                        amStationLine2.setBackgroundResource(R.color.radioDialGreen);
                        assert amStationLine3 != null;
                        amStationLine3.setBackgroundResource(R.color.optionPageButtons);
                        getOrientation();
                        if (isPortrait) {
                            amStationLine2.setScaleX(3);
                            amStationLine3.setScaleX(1);
                        } else {
                            amStationLine2.setScaleY(3);
                            amStationLine3.setScaleY(1);
                        }
                    } else if (amStation == 4) {
                        amStation = amStation - 1;
                        radioStationToast(getApplicationContext(), getString(R.string.sports_string), Toast.LENGTH_SHORT);
                        assert amStationLine3 != null;
                        amStationLine3.setBackgroundResource(R.color.radioDialGreen);
                        assert amStationLine4 != null;
                        amStationLine4.setBackgroundResource(R.color.optionPageButtons);
                        getOrientation();
                        if (isPortrait) {
                            amStationLine3.setScaleX(3);
                            amStationLine4.setScaleX(1);
                        } else {
                            amStationLine3.setScaleY(3);
                            amStationLine4.setScaleY(1);
                        }
                    } else if (amStation == 5) {
                        amStation = amStation - 1;
                        radioStationToast(getApplicationContext(), getString(R.string.talk_radio), Toast.LENGTH_SHORT);
                        assert amStationLine4 != null;
                        amStationLine4.setBackgroundResource(R.color.radioDialGreen);
                        assert amStationLine5 != null;
                        amStationLine5.setBackgroundResource(R.color.optionPageButtons);
                        getOrientation();
                        if (isPortrait) {
                            amStationLine4.setScaleX(3);
                            amStationLine5.setScaleX(1);
                        } else {
                            amStationLine4.setScaleY(3);
                            amStationLine5.setScaleY(1);
                        }
                    } else if (amStation == 6) {
                        amStation = amStation - 1;
                        radioStationToast(getApplicationContext(), getString(R.string.opera), Toast.LENGTH_SHORT);
                        assert amStationLine5 != null;
                        amStationLine5.setBackgroundResource(R.color.radioDialGreen);
                        assert amStationLine6 != null;
                        amStationLine6.setBackgroundResource(R.color.optionPageButtons);
                        getOrientation();
                        if (isPortrait) {
                            amStationLine5.setScaleX(3);
                            amStationLine6.setScaleX(1);
                        } else {
                            amStationLine5.setScaleY(3);
                            amStationLine6.setScaleY(1);
                        }
                    }
                } else {
                    if (fmStation == 1) {
                        fmStation = 1;
                        radioStationToast(getApplicationContext(), getString(R.string.rock), Toast.LENGTH_SHORT);
                        fmStationLine1.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine1.setScaleX(3);
                        } else {
                            fmStationLine1.setScaleY(3);
                        }
                    } else if (fmStation == 2) {
                        fmStation = fmStation - 1;
                        radioStationToast(getApplicationContext(), getString(R.string.rock), Toast.LENGTH_SHORT);
                        fmStationLine1.setBackgroundResource(R.color.radioDialGreen);
                        assert fmStationLine2 != null;
                        fmStationLine2.setBackgroundResource(R.color.optionPageButtons);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine1.setScaleX(3);
                            fmStationLine2.setScaleX(1);
                        } else {
                            fmStationLine1.setScaleY(3);
                            fmStationLine2.setScaleY(1);
                        }
                    } else if (fmStation == 3) {
                        fmStation = fmStation - 1;
                        radioStationToast(getApplicationContext(), getString(R.string.alternative_rock), Toast.LENGTH_SHORT);
                        assert fmStationLine2 != null;
                        fmStationLine2.setBackgroundResource(R.color.radioDialGreen);
                        assert fmStationLine3 != null;
                        fmStationLine3.setBackgroundResource(R.color.optionPageButtons);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine2.setScaleX(3);
                            fmStationLine3.setScaleX(1);
                        } else {
                            fmStationLine2.setScaleY(3);
                            fmStationLine3.setScaleY(1);
                        }
                    } else if (fmStation == 4) {
                        fmStation = fmStation - 1;
                        radioStationToast(getApplicationContext(), getString(R.string.the_hits), Toast.LENGTH_SHORT);
                        assert fmStationLine3 != null;
                        fmStationLine3.setBackgroundResource(R.color.radioDialGreen);
                        assert fmStationLine4 != null;
                        fmStationLine4.setBackgroundResource(R.color.optionPageButtons);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine3.setScaleX(3);
                            fmStationLine4.setScaleX(1);
                        } else {
                            fmStationLine3.setScaleY(3);
                            fmStationLine4.setScaleY(1);
                        }
                    } else if (fmStation == 5) {
                        fmStation = fmStation - 1;
                        radioStationToast(getApplicationContext(), getString(R.string.adult_contemporary), Toast.LENGTH_SHORT);
                        assert fmStationLine4 != null;
                        fmStationLine4.setBackgroundResource(R.color.radioDialGreen);
                        assert fmStationLine5 != null;
                        fmStationLine5.setBackgroundResource(R.color.optionPageButtons);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine4.setScaleX(3);
                            fmStationLine5.setScaleX(1);
                        } else {
                            fmStationLine4.setScaleY(3);
                            fmStationLine5.setScaleY(1);
                        }
                    } else if (fmStation == 6) {
                        fmStation = fmStation - 1;
                        radioStationToast(getApplicationContext(), getString(R.string.r_and_b), Toast.LENGTH_SHORT);
                        assert fmStationLine5 != null;
                        fmStationLine5.setBackgroundResource(R.color.radioDialGreen);
                        assert fmStationLine6 != null;
                        fmStationLine6.setBackgroundResource(R.color.optionPageButtons);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine5.setScaleX(3);
                            fmStationLine6.setScaleX(1);
                        } else {
                            fmStationLine5.setScaleY(3);
                            fmStationLine6.setScaleY(1);
                        }
                    } else if (fmStation == 7) {
                        fmStation = fmStation - 1;
                        radioStationToast(getApplicationContext(), getString(R.string.hip_hop), Toast.LENGTH_SHORT);
                        assert fmStationLine6 != null;
                        fmStationLine6.setBackgroundResource(R.color.radioDialGreen);
                        assert fmStationLine7 != null;
                        fmStationLine7.setBackgroundResource(R.color.optionPageButtons);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine6.setScaleX(3);
                            fmStationLine7.setScaleX(1);

                        } else {
                            fmStationLine6.setScaleY(3);
                            fmStationLine7.setScaleY(1);
                        }
                    }
                }
            }
        });

        /**
         * Scrolls down through stations when the tuning up button is clicked. These stations and genres
         * will be customizable in the fully functioning app.
         */
        assert tuningDown != null;
        tuningDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amfmSwtich.isChecked()) {
                    if (amStation == 1) {
                        amStation = amStation + 1;
                        radioStationToast(getApplicationContext(), getString(R.string.classical), Toast.LENGTH_SHORT);
                        assert amStationLine1 != null;
                        amStationLine1.setBackgroundResource(R.color.optionPageButtons);
                        assert amStationLine2 != null;
                        amStationLine2.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            amStationLine1.setScaleX(1);
                            amStationLine2.setScaleX(3);
                        } else {
                            amStationLine1.setScaleY(1);
                            amStationLine2.setScaleY(3);
                        }
                    } else if (amStation == 2) {
                        amStation = amStation + 1;
                        radioStationToast(getApplicationContext(), getString(R.string.sports_string), Toast.LENGTH_SHORT);
                        assert amStationLine2 != null;
                        amStationLine2.setBackgroundResource(R.color.optionPageButtons);
                        assert amStationLine3 != null;
                        amStationLine3.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            amStationLine2.setScaleX(1);
                            amStationLine3.setScaleX(3);
                        } else {
                            amStationLine2.setScaleY(1);
                            amStationLine3.setScaleY(3);
                        }
                    } else if (amStation == 3) {
                        amStation = amStation + 1;
                        radioStationToast(getApplicationContext(), getString(R.string.talk_radio), Toast.LENGTH_SHORT);
                        assert amStationLine3 != null;
                        amStationLine3.setBackgroundResource(R.color.optionPageButtons);
                        assert amStationLine4 != null;
                        amStationLine4.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            amStationLine3.setScaleX(1);
                            amStationLine4.setScaleX(3);
                        } else {
                            amStationLine3.setScaleY(1);
                            amStationLine4.setScaleY(3);
                        }
                    } else if (amStation == 4) {
                        amStation = amStation + 1;
                        radioStationToast(getApplicationContext(), getString(R.string.opera), Toast.LENGTH_SHORT);
                        assert amStationLine4 != null;
                        amStationLine4.setBackgroundResource(R.color.optionPageButtons);
                        assert amStationLine5 != null;
                        amStationLine5.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            amStationLine4.setScaleX(1);
                            amStationLine5.setScaleX(3);
                        } else {
                            amStationLine4.setScaleY(1);
                            amStationLine5.setScaleY(3);
                        }
                    } else if (amStation == 5) {
                        amStation = amStation + 1;
                        radioStationToast(getApplicationContext(), getString(R.string.classic_radio_plays), Toast.LENGTH_SHORT);
                        assert amStationLine5 != null;
                        amStationLine5.setBackgroundResource(R.color.optionPageButtons);
                        assert amStationLine6 != null;
                        amStationLine6.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            amStationLine5.setScaleX(1);
                            amStationLine6.setScaleX(3);
                        } else {
                            amStationLine5.setScaleY(1);
                            amStationLine6.setScaleY(3);
                        }
                    } else if (amStation == 6) {
                        amStation = 6;
                        radioStationToast(getApplicationContext(), getString(R.string.classic_radio_plays), Toast.LENGTH_SHORT);
                        assert amStationLine6 != null;
                        amStationLine6.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            amStationLine6.setScaleX(3);
                        } else {
                            amStationLine6.setScaleY(3);
                        }
                    }
                } else {
                    if (fmStation == 1) {
                        fmStation = fmStation + 1;
                        radioStationToast(getApplicationContext(), getString(R.string.alternative_rock), Toast.LENGTH_SHORT);
                        fmStationLine1.setBackgroundResource(R.color.optionPageButtons);
                        assert fmStationLine2 != null;
                        fmStationLine2.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine1.setScaleX(1);
                            fmStationLine2.setScaleX(3);
                        } else {
                            fmStationLine1.setScaleY(1);
                            fmStationLine2.setScaleY(3);
                        }
                    } else if (fmStation == 2) {
                        fmStation = fmStation + 1;
                        radioStationToast(getApplicationContext(), getString(R.string.the_hits), Toast.LENGTH_SHORT);
                        assert fmStationLine2 != null;
                        fmStationLine2.setBackgroundResource(R.color.optionPageButtons);
                        assert fmStationLine3 != null;
                        fmStationLine3.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine2.setScaleX(1);
                            fmStationLine3.setScaleX(3);
                        } else {
                            fmStationLine2.setScaleY(1);
                            fmStationLine3.setScaleY(3);
                        }
                    } else if (fmStation == 3) {
                        fmStation = fmStation + 1;
                        radioStationToast(getApplicationContext(), getString(R.string.adult_contemporary), Toast.LENGTH_SHORT);
                        assert fmStationLine3 != null;
                        fmStationLine3.setBackgroundResource(R.color.optionPageButtons);
                        assert fmStationLine4 != null;
                        fmStationLine4.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine3.setScaleX(1);
                            fmStationLine4.setScaleX(3);
                        } else {
                            fmStationLine3.setScaleY(1);
                            fmStationLine4.setScaleY(3);
                        }
                    } else if (fmStation == 4) {
                        fmStation = fmStation + 1;
                        radioStationToast(getApplicationContext(), getString(R.string.r_and_b), Toast.LENGTH_SHORT);
                        assert fmStationLine4 != null;
                        fmStationLine4.setBackgroundResource(R.color.optionPageButtons);
                        assert fmStationLine5 != null;
                        fmStationLine5.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine4.setScaleX(1);
                            fmStationLine5.setScaleX(3);
                        } else {
                            fmStationLine4.setScaleY(1);
                            fmStationLine5.setScaleY(3);
                        }
                    } else if (fmStation == 5) {
                        fmStation = fmStation + 1;
                        radioStationToast(getApplicationContext(), getString(R.string.hip_hop), Toast.LENGTH_SHORT);
                        assert fmStationLine5 != null;
                        fmStationLine5.setBackgroundResource(R.color.optionPageButtons);
                        assert fmStationLine6 != null;
                        fmStationLine6.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine5.setScaleX(1);
                            fmStationLine6.setScaleX(3);
                        } else {
                            fmStationLine5.setScaleY(1);
                            fmStationLine6.setScaleY(3);
                        }
                    } else if (fmStation == 6) {
                        fmStation = fmStation + 1;
                        radioStationToast(getApplicationContext(), getString(R.string.latino), Toast.LENGTH_SHORT);
                        assert fmStationLine6 != null;
                        fmStationLine6.setBackgroundResource(R.color.optionPageButtons);
                        assert fmStationLine7 != null;
                        fmStationLine7.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine6.setScaleX(1);
                            fmStationLine7.setScaleX(3);
                        } else {
                            fmStationLine6.setScaleY(1);
                            fmStationLine7.setScaleY(3);
                        }
                    } else if (fmStation == 7) {
                        fmStation = 7;
                        radioStationToast(getApplicationContext(), getString(R.string.latino), Toast.LENGTH_SHORT);
                        assert fmStationLine7 != null;
                        fmStationLine7.setBackgroundResource(R.color.radioDialGreen);
                        getOrientation();
                        if (isPortrait) {
                            fmStationLine7.setScaleX(3);
                        } else {
                            fmStationLine7.setScaleY(3);
                        }
                    }
                }
            }
        });

        /**
         * This will note that the user likes the current song to increase the odds of it playing again in the stations rotation
         */
        assert radioLike != null;
        radioLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioLikeDislikeBuyToast(getApplicationContext(), getString(R.string.like_toast), Toast.LENGTH_LONG);
            }
        });

        /**
         * This will note that the user dislikes the current song to decrease the odds of it playing again in the stations rotation
         */
        assert radioDislike != null;
        radioDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioLikeDislikeBuyToast(getApplicationContext(), getString(R.string.dislike_toast), Toast.LENGTH_LONG);
            }
        });

        /**
         * This will send users to a site where they can buy the song, album or other works from the artist to then ad to the Jukebox feature of this app.
         */
        assert radioBuyButton != null;
        radioBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioLikeDislikeBuyToast(getApplicationContext(), getString(R.string.buy_toast), Toast.LENGTH_LONG);
            }
        });
    }


    /**
     *Layout for radio station Toasts.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void radioStationToast(Context context, String msg, int duration) {
        Toast toast = Toast.makeText(context, msg, duration);
        getOrientation();
        if (isPortrait){
            toast.setMargin(-.15f, .6f);
        } else {
            toast.setMargin(.075f, .4f);
        }
        View view = toast.getView();
        view.setBackgroundResource(R.drawable.radio_red_with_logo_red_trim);
        view.setAlpha(.9f);
        TextView toastMessage = (TextView) view.findViewById(android.R.id.message);
        toastMessage.setTextColor(getColor(R.color.optionPageButtons));
        toastMessage.setPadding(24, 24, 24, 24);
        toast.show();
    }

    /**
     *Sets layout for the Like/Dislike/Buy buttons.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void radioLikeDislikeBuyToast(Context context, String msg, int duration) {
        Toast toast = Toast.makeText(context, msg, duration);
        getOrientation();
        if (isPortrait){
            toast.setMargin(0, .18f);
        } else {
            toast.setMargin(.075f, .3f);
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
}
