package ny.nyfit;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

/**
 * Created by U820319 on 30.09.2016.
 */

public class ListViewLoader extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter mAdapter;

    static final String[] TESTDATEN = new String[] {"1", "2"};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] fromColumns = {"1"};
        int[] toViews = {};

        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, null, fromColumns, toViews, 0);
        setListAdapter(mAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
