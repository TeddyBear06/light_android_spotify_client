package com.lfconsult.bfsc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

public class MainActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "f6224085ede04a29b014064177cbc2ed";
    private static final String REDIRECT_URI = "bfsc://callback";
    private SpotifyAppRemote mSpotifyAppRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Set the connection parameters
        ConnectionParams connectionParams =
            new ConnectionParams.Builder(CLIENT_ID)
                .setRedirectUri(REDIRECT_URI)
                .showAuthView(true)
                .build();

        SpotifyAppRemote.connect(this, connectionParams,
            new Connector.ConnectionListener() {

            @Override
            public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                mSpotifyAppRemote = spotifyAppRemote;
                Log.d("MainActivity", "Connected! Yay!");

                // Now you can start interacting with App Remote
                connected();
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.e("MainActivity", throwable.getMessage(), throwable);

                // Something went wrong when attempting to connect! Handle errors here
            }
        });
    }

    private void connected() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        // Aaand we will finish off here.
    }

    public void playPlaylist(String playlistID) {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:"+playlistID);
    }

    public void playMusicLand(View view) {
        this.playPlaylist("1vVdysRBzIhdw0bLx5c5LW");
    }

    public void playBeastMode(View view) {
        this.playPlaylist("37i9dQZF1DX76Wlfdnj7AP");
    }

    public void playMetrik(View view) {
        this.playPlaylist("37i9dQZF1E4AIS6Tcn6dvZ");
    }

    public void playBassArcade(View view) {
        this.playPlaylist("37i9dQZF1DX0hvSv9Rf41p");
    }

}
