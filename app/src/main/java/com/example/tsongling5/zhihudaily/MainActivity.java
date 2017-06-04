package com.example.tsongling5.zhihudaily;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private boolean connectStatue;
    private int ItemId;
    private SwipeRefreshLayout refreshLayout;
    private ListView lv;
    private NewsAdapter adapter;
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        connectStatue=Utility.isConnected(this);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

//        HttpUtility http=new HttpUtility();

        lv=(ListView)findViewById(R.id.storyList);
//        setTitle(getTime());
        adapter = new NewsAdapter(this, R.layout.news_layout);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
        lv.setOnScrollChangeListener(new ListView.OnScrollChangeListener(){

            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//                Toast.makeText(MainActivity.this,"hehe",Toast.LENGTH_SHORT).show();
            }
        });
//        lv.setOnScrollChangeListener(new View.OnScrollChangeListener(){

//            @Override
//            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
//                if(i==i2){
//                    Toast.makeText(MainActivity.this,"hehe",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });




        if (connectStatue) new LoadNewsTask(adapter,Constant.NEWSLIST_LATEST).execute();
        else Utility.NoNetAlert(this);


//        GetHTTP example = new HttpUtility.GetHTTP();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        if(id==R.id.firstPage){
            ItemId=0;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.NEWSLIST_LATEST).execute();
        }
        else if (id == R.id.nav_movieDaily) {
            ItemId=3;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"3").execute();
            // Handle the camera action
        } else if (id == R.id.nav_noboreding) {
            ItemId=11;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"11").execute();

        } else if (id == R.id.nav_dailyPsy) {
            ItemId=13;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"13").execute();

        } else if (id == R.id.nav_userRecomand) {
            ItemId=12;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"12").execute();

        } else if (id == R.id.nav_designDaily) {
            ItemId=4;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"4").execute();

        } else if (id == R.id.nav_companyDaily) {
            ItemId=5;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"5").execute();

        }else if (id == R.id.nav_financeDaily) {
            ItemId=6;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"6").execute();

        }else if (id == R.id.nav_netSafety) {
            ItemId=10;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"10").execute();

        }else if (id == R.id.nav_startGame) {
            ItemId=2;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"2").execute();

        }else if (id == R.id.nav_musicDaily) {
            ItemId=7;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"7").execute();

        }else if (id == R.id.nav_ACGDaily) {
            ItemId=9;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"9").execute();

        }else if (id == R.id.nav_GYMDaily) {
            ItemId=8;
            adapter.clear();
            new LoadNewsTask(adapter,Constant.THEME+"8").execute();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRefresh() {

        if(Utility.isConnected(this)){

            String url =null;
            if(ItemId==0){
                url=Constant.NEWSLIST_LATEST;
            }
            else{
                url=Constant.THEME+Integer.toString(ItemId);
//                switch(ItemId){
//                    case 3:
//
//                        break;
//                    case 11:
//
//                        break;
//                    case 13:
//
//                        break;
//                    case 12:
//
//                        break;
//                    case 4:
//
//                        break;
//                    case 5:
//
//                        break;
//                    case 6:
//
//                        break;
//                    case 2:
//
//                        break;
//                    case 10:
//
//                        break;
//                    case 7:
//
//                        break;
//                    case 9:
//
//                        break;
//                    case 8:
//
//                        break;
//                }
            }
//            int id = item.getItemId();
            new LoadNewsTask(adapter,url ,new LoadNewsTask.onFinishListener() {
                @Override
                public void afterTaskFinish() {
                    refreshLayout.setRefreshing(false);
                }
            }).execute();

        }
        else{
            Utility.NoNetAlert(this);
            refreshLayout.setRefreshing(false);
        }
    }

//    @TargetApi(Build.VERSION_CODES.N)
//    public String getTime() {
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat format = new SimpleDateFormat(getString(R.string.date_format));
//        return format.format(c.getTime());
//
//    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        NewsDetailActivity.startActivity(this, adapter.getItem(position));
    }
}
