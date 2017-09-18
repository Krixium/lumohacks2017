package ca.bcit.cst.seta2016.invoker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

/**
 * MainActivity.java
 *
 * The entry point of our program.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private EventAdaptor adapter;
    private EventDatabase db;
    private ItemTouchHelper.SimpleCallback recyclerViewSwipeCallback = new ItemTouchHelper.SimpleCallback(
            0,
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
            final EventCard removedEventCard = eventCardList.get(position);

            eventCardList.remove(position);
            db.deleteEventCard(removedEventCard);
            adapter.notifyItemRemoved(position);

            Snackbar.make(recyclerView, removedEventCard.getEvent() + " was removed.", Snackbar.LENGTH_LONG)
                    .setAction("Undo", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Re-add the eventCard
                            addEvent(removedEventCard);
                        }
                    }).show();
        }
    };

    private List<EventCard> eventCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDataList();
        initView();
    }

    /**
     * Initialize all data related objects.
     */
    private void initDataList() {
        db = new EventDatabase(this);
        eventCardList = db.getAllEventCards();
    }

    /**
     * Initialize all view related objects.
     */
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // Make a grid for the cards
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Create the adapter for the data
        adapter = new EventAdaptor(eventCardList);
        recyclerView.setAdapter(adapter);

        // Create the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the floating action button
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Set card swipe behaviour
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(recyclerViewSwipeCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    /**
     * Add a card to the database.
     *
     * @param eventCard The element to add to the list.
     */
    public void addEvent(EventCard eventCard) {
        db.addEventCard(eventCard);
        eventCardList = db.getAllEventCards();
        adapter.notifyDataSetChanged();
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

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
            addEvent(new EventCard(0, child, date, event, desc, rank));
        }
    }
}
