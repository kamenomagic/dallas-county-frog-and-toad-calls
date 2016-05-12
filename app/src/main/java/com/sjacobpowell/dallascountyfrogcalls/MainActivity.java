package com.sjacobpowell.dallascountyfrogcalls;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ListViewItem[] herps;
    private int currentSoundIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        List<ListViewItem> herpList = new ArrayList<>();
        herpList.add(new ListViewItem("Genus Hylidae\n(Treefrogs)", null, CustomAdapter.TYPE_TITLE));
            herpList.add(new ListViewItem("Cricket Frog\n(Acris crepitans blanchardi)", MediaPlayer.create(this, R.raw.cricketfrog), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Cope's Gray Treefrog\n(Hyla chrysoscelis)", MediaPlayer.create(this, R.raw.copesgraytreefrog), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Gray Treefrog\n(Hyla versicolor)", MediaPlayer.create(this, R.raw.graytreefrog), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Green Treefrog\n(Hyla cinerea)", MediaPlayer.create(this, R.raw.greentreefrog), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Spotted Chorus Frog\n(Pseudacris clarkii)", MediaPlayer.create(this, R.raw.spottedchorusfrog), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Strecker's Chorus Frog\n(Pseudacris streckeri)", MediaPlayer.create(this, R.raw.streckerschorusfrog), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Upland Chorus Frog\n(Pseudacris triseriata feriarum)", MediaPlayer.create(this, R.raw.uplandchorusfrog), CustomAdapter.TYPE_HERP_ROW));
        herpList.add(new ListViewItem("Genus Bufonidae\n(Toads)", null, CustomAdapter.TYPE_TITLE));
            herpList.add(new ListViewItem("Green Toad\n(Bufo debilis)", MediaPlayer.create(this, R.raw.greentoad), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Red Spotted Toad\n(Bufo punctatus)", MediaPlayer.create(this, R.raw.redspottedtoad), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Texas Toad\n(Bufo speciosus)", MediaPlayer.create(this, R.raw.texastoad), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Gulf Coast Toad\n(Bufo valliceps)", MediaPlayer.create(this, R.raw.gulfcoasttoad), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Woodhouse's Toad\n(Bufo woodhousii)", MediaPlayer.create(this, R.raw.woodhousestoad), CustomAdapter.TYPE_HERP_ROW));
        herpList.add(new ListViewItem("Genus Pelobatidae\n(Spadefoot Toads)", null, CustomAdapter.TYPE_TITLE));
            herpList.add(new ListViewItem("Hurter's Spadefoot Toad\n(Scaphiopus hurterii)", MediaPlayer.create(this, R.raw.hurtersspadefoottoad), CustomAdapter.TYPE_HERP_ROW));
        herpList.add(new ListViewItem("Genus Ranidae\n(True Frogs)", null, CustomAdapter.TYPE_TITLE));
            herpList.add(new ListViewItem("Rio Grande Leopard Frog\n(Rana berlandieri)", MediaPlayer.create(this, R.raw.riograndeleopardfrog), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Plains Leopard Frog\n(Rana blairi)", MediaPlayer.create(this, R.raw.plainsleopardfrog), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Bull Frog\n(Rana catesbeiana)", MediaPlayer.create(this, R.raw.bullfrog), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem("Southern Leopard Frog\n(Rana sphenocephala)", MediaPlayer.create(this, R.raw.southernleopardfrog), CustomAdapter.TYPE_HERP_ROW));
        herpList.add(new ListViewItem("Genus Microhylidae\n(Narrowmouth Toads)", null, CustomAdapter.TYPE_TITLE));
            herpList.add(new ListViewItem("Eastern Narrowmouth Toad\n(Gastrophryne carolinensis)", MediaPlayer.create(this, R.raw.easternnarrowmouthttoad), CustomAdapter.TYPE_HERP_ROW));
            herpList.add(new ListViewItem( "Great Plains Narrowmouth Toad\n(Gastrophryne olivacea)", MediaPlayer.create(this, R.raw.greatplainsnarrowmouthtoad), CustomAdapter.TYPE_HERP_ROW));

        herps = new ListViewItem[herpList.size()];
        for(int i = 0; i < herpList.size(); i++) {
            herps[i] = herpList.get(i);
        }

        CustomAdapter customAdapter = new CustomAdapter(this, R.id.text, herps);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int index, long l) {
                if(herps[index].getSound() == null) return;
                playSound(index);
                Toast.makeText(getBaseContext(),herps[index].getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void playSound(int index) {
        MediaPlayer currentSound;
        try {
            if(currentSoundIndex != -1) {
                currentSound = herps[currentSoundIndex].getSound();
                currentSound.stop();
                currentSound.prepare();
            }
            currentSoundIndex = index;
            currentSound = herps[currentSoundIndex].getSound();
            currentSound.setLooping(true);
            currentSound.start();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error playing sound" , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.stop_playing) {
            try {
                MediaPlayer currentSound  = herps[currentSoundIndex].getSound();
                currentSound.stop();
                currentSound.prepare();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Error stopping sound" , Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
