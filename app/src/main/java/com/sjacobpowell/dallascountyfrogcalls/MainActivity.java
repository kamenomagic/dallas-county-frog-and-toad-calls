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

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private String[] herpNames;
    private MediaPlayer[] herpSounds;
    private int currentSoundIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        final ListViewItem[] items = new ListViewItem[40];

        for (int i = 0; i < items.length; i++) {
           if (i % 2 == 0) {
                items[i] = new ListViewItem("EVEN " + i, CustomAdapter.TYPE_HERP_ROW);
            } else {
                items[i] = new ListViewItem("ODD " + i, CustomAdapter.TYPE_TITLE);
            }
        }

        CustomAdapter customAdapter = new CustomAdapter(this, R.id.text, items);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), items[i].getText(), Toast.LENGTH_SHORT).show();
            }
        });

//        herpNames = new String[] {
//                //Genus Hylidae (Treefrogs)
//                "Cricket Frog\n(Acris crepitans blanchardi)",
//                "Cope's Gray Treefrog\n(Hyla chrysoscelis)",
//                "Gray Treefrog\n(Hyla versicolor)",
//                "Green Treefrog\n(Hyla cinerea)",
//                "Spotted Chorus Frog\n(Pseudacris clarkii)",
//                "Strecker's Chorus Frog\n(Pseudacris streckeri)",
//                "Upland Chorus Frog\n(Pseudacris triseriata feriarum)",
//
//                //Genus Bufonidae (Toads)
//                "Green Toad\n(Bufo debilis)",
//                "Red Spotted Toad\n(Bufo punctatus)",
//                "Texas Toad\n(Bufo speciosus)",
//                "Gulf Coast Toad\n(Bufo valliceps)",
//                "Woodhouse's Toad\n(Bufo woodhousii)",
//
//                //Genus Pelobatidae (Spadefoot Toads)
//                "Hurter's Spadefoot Toad\n(Scaphiopus hurterii)",
//
//                //Genus Ranidae (True Frogs)
//                "Rio Grande Leopard Frog\n(Rana berlandieri)",
//                "Plains Leopard Frog\n(Rana blairi)",
//                "Bull Frog\n(Rana catesbeiana)",
//                "Southern Leopard Frog\n(Rana sphenocephala)",
//
//                //Genus Microhylidae (Narrowmouth Toads)
//                "Eastern Narrowmouth Toad\n(Gastrophryne carolinensis)",
//                "Great Plains Narromouth Toad\n(Gastrophryne olivacea)"
//        };
//        herpSounds = new MediaPlayer[] {
//                //Genus Hylidae (Treefrogs)
//                MediaPlayer.create(this, R.raw.cricketfrog),
//                MediaPlayer.create(this, R.raw.copesgraytreefrog),
//                MediaPlayer.create(this, R.raw.graytreefrog),
//                MediaPlayer.create(this, R.raw.greentreefrog),
//                MediaPlayer.create(this, R.raw.spottedchorusfrog),
//                MediaPlayer.create(this, R.raw.streckerschorusfrog),
//                MediaPlayer.create(this, R.raw.uplandchorusfrog),
//
//                //Genus Bufonidae (Toads)
//                MediaPlayer.create(this, R.raw.greentoad),
//                MediaPlayer.create(this, R.raw.redspottedtoad),
//                MediaPlayer.create(this, R.raw.texastoad),
//                MediaPlayer.create(this, R.raw.gulfcoasttoad),
//                MediaPlayer.create(this, R.raw.woodhousestoad),
//
//                //Genus Pelobatidae (Spadefoot Toads)
//                MediaPlayer.create(this, R.raw.hurtersspadefoottoad),
//
//                //Genus Ranidae (True Frogs)
//                MediaPlayer.create(this, R.raw.riograndeleopardfrog),
//                MediaPlayer.create(this, R.raw.plainsleopardfrog),
//                MediaPlayer.create(this, R.raw.bullfrog),
//                MediaPlayer.create(this, R.raw.southernleopardfrog),
//
//                //Genus Microhylidae (Narrowmouth Toads)
//                MediaPlayer.create(this, R.raw.easternnarrowmouthttoad),
//                MediaPlayer.create(this, R.raw.greatplainsnarrowmouthtoad),
//        };
//
//
//
//        // Define a new Adapter
//        // First parameter - Context
//        // Second parameter - Layout for the row
//        // Third parameter - ID of the TextView to which the data is written
//        // Forth - the Array of data
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.herp_row, R.id.my_text, herpNames);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int index, long id) {
//                playSound(index);
//                Toast.makeText(getApplicationContext(), (String) listView.getItemAtPosition(index) , Toast.LENGTH_SHORT).show();
//            }
//        });
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
                MediaPlayer currentSound  = herpSounds[currentSoundIndex];
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

    public void playSound(int index) {
        MediaPlayer currentSound;
        try {
            if(currentSoundIndex != -1) {
                currentSound = herpSounds[currentSoundIndex];
                currentSound.stop();
                currentSound.prepare();
            }
            currentSoundIndex = index;
            currentSound = herpSounds[currentSoundIndex];
            currentSound.setLooping(true);
            currentSound.start();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error playing sound" , Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
