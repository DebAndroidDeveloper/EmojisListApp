package assessment.mycompany.com.emojis.activities;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import assessment.mycompany.com.emojis.R;
import assessment.mycompany.com.emojis.fragments.EmojisFragment;

public class EmojisActivity extends BaseFragmentActivity{

    @Override
    public String getTag() {
        return EmojisActivity.class.getCanonicalName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emojis);
        EmojisFragment emojisFragment = new EmojisFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.
                beginTransaction().
                replace(R.id.sample_emojis, emojisFragment, "emojis").
                addToBackStack(null).
                commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_emojis, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
