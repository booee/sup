package us.broani.sup;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class Main extends Activity {
    private static final String TAG = Main.class.getCanonicalName();

    private final String[] lines = {
            "Do you have a sunburn, or are you always this hot?",
            "Can I buy you a fish sandwich?",
            "Are you an orphanage? Cause I wanna give you kids",
            "Those jeans make your ass look fat. I wanna get you pregnant while you make me dinner",
            "I'm not staring at your boobs. I'm staring at your heart.",
            "What yo booty taste like? ... SKRAWWWWWW-BERRRRRRIES",
            "Hey baby, Wanna make a big mistake?",
            "Is your name Homework? 'Cause I'm not doing you, but I should be",
            "Wanna go halfsies on a bastard child?",
            "Are you the Gulf of Mexico? Becuase I wanna drill you and make a huge mess",
            "Are you sitting on that F5 key? 'Cause that ass is refreshing",
            "Can I get yo screen name?"
    };

    private Queue<Integer> recents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recents = new LinkedList<Integer>();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void refreshLineView(View view) {
        TextView lineView = (TextView) this.findViewById(R.id.lineView);
        lineView.setText(getNewLine());
    }

    private String getNewLine() {
        int randomNum = getRandomIndex();
        updateRecents(randomNum);

        return lines[randomNum];
    }

    private int getRandomIndex() {
        Random rand = new Random();
        int max = lines.length - 1;
        int min = 0;
        int randomNum = rand.nextInt((max - min) + 1) + min;


        while(recents.contains(randomNum)) {
            Log.d(TAG, "Avoiding duplicate: " + lines[randomNum]);
            randomNum = rand.nextInt((max - min) + 1) + min;
        }

        return randomNum;
    }

    private void updateRecents(int selectedIndex) {
        int threshold = recents.size() / 2;
        while(recents.size() > 5) {
            recents.remove();
        }

        recents.add(selectedIndex);
    }
}
