package ca.bcit.cst.seta2016.invoker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // DON'T TOUCH
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private EventAdaptor adapter;
    private ItemTouchHelper itemTouchHelper;
    private EventDatabase db;

    // List of data for CardViews
    private List<Event> event_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new EventDatabase(this);

        // Creating the list of events
        event_list = db.getAllEvents();

        // DON'T TOUCH
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new EventAdaptor(this, event_list);
        recyclerView.setAdapter(adapter);
        // End of DON'T TOUCH

        itemTouchHelper = new ItemTouchHelper(recyclerViewSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // Reference tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Reference floating button and assign on click listener
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });

        // Makes the Navigation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Set Layout type
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    ItemTouchHelper.SimpleCallback recyclerViewSwipeCallback = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            // not used
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            //Remove swiped item from list and notify the RecyclerView
            final int position = viewHolder.getAdapterPosition();
            final Event event = event_list.get(position);

            event_list.remove(position);
            db.deleteShop(event);
            adapter.notifyItemRemoved(position);

            Snackbar.make(recyclerView, event.getChild() + " was removed.", Snackbar.LENGTH_LONG)
                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Re-add the event
                            event_list.add(event);
                            Toast.makeText(MainActivity.this,
                                    "child=" + event.getChild() + ", event=" + event.getEvent(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }).show();
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_today) {
            // Handle the today action
        } else if (id == R.id.nav_calendar) {
            intent = new Intent(this, CalendarActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_topic) {
            intent = new Intent(this, TopicsMenu.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {
            intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_feedback) {
            intent = new Intent(this, FeedbackActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

        if (intent.hasExtra("child") && intent.hasExtra("date") && intent.hasExtra("event") && intent.hasExtra("desc") && intent.hasExtra("rank")) {
            String child = intent.getStringExtra("child");
            String date = intent.getStringExtra("date");
            String event = intent.getStringExtra("event");
            String desc = intent.getStringExtra("desc");
            String rank = intent.getStringExtra("rank");
            addEvent(new Event(0, child, date, event, desc, rank));
        }
    }

    public void addEvent(Event event) {
        db.addEvent(event);
        event_list = db.getAllEvents();
        adapter.notifyDataSetChanged();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }
}
