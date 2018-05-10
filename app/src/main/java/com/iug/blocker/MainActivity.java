package com.iug.blocker;

import android.content.DialogInterface;
import android.net.MailTo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iug.blocker.model.Profile;
import com.iug.blocker.model.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;
    ProfilesRecyclerViewAdapter adapter;
    private List<Profile> list;

    Realm realm ;
    EditText name ;
    CheckBox sat ;
    CheckBox sun ;
    CheckBox mon ;
    CheckBox tue ;
    CheckBox wed ;
    CheckBox thu;
    CheckBox fri ;
    Button addProfile ;
    EditText from;
    EditText to;
    AlertDialog timeAlertDialog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
          Button button = (Button)findViewById(R.id.button_noti);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        realm = Realm.getDefaultInstance() ;
          button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                                  Snackbar.make(view, "Show Notifications list ", Snackbar.LENGTH_LONG)
                        .setAction("Notification", null).show();
              }
          });
          /**recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
              @Override
              public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) { return false; }
              @Override
              public void onTouchEvent(RecyclerView rv, MotionEvent e) {

              }
              @Override
              public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) { }
                                           });

               /**  @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               }

                @Override public void onNothingSelected(AdapterView<?> parent) {

                }
                });*/


               RealmHelper helper = new RealmHelper(realm);
        list =helper.restore();
        adapter = new ProfilesRecyclerViewAdapter(list);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setAdapter(adapter);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        recyclerView.setItemAnimator(new DefaultItemAnimator());


        //test


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAdd();}
        });
    }

    @Override
    public void onClick(View view) {

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    AlertDialog alertDialog;
    public static EditText date ;

    public void showAdd() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_profile_dialog, null);
        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
         name = (EditText)dialogView.findViewById(R.id.ed_name) ;
        date = (EditText) dialogView.findViewById(R.id.ed_date);

        sat = (CheckBox)dialogView.findViewById(R.id.sat);
        sun = (CheckBox)dialogView.findViewById(R.id.sun);
        mon = (CheckBox)dialogView.findViewById(R.id.mon);
        tue = (CheckBox)dialogView.findViewById(R.id.tue);
        wed = (CheckBox)dialogView.findViewById(R.id.wed);
        thu = (CheckBox)dialogView.findViewById(R.id.thu);
        fri = (CheckBox)dialogView.findViewById(R.id.fri);
        addProfile = (Button)dialogView.findViewById(R.id.btn);
        from = (EditText)dialogView.findViewById(R.id.from );
        to = (EditText)dialogView.findViewById(R.id.to );

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(dialogView, "It will show Apps list", Snackbar.LENGTH_LONG)
                        .setAction("Apps List", null).show();}
        });
        Button saving = (Button) dialogView.findViewById(R.id.btn);

        saving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             try {

                  realm.executeTransaction(new Realm.Transaction() {
                      @Override
                      public void execute(Realm realm) {
                          Profile profile = new Profile();
                          profile.setName(name.getText().toString().trim());
                          profile.setSat(sat.isChecked());
                          profile.setSun(sun.isChecked());
                          profile.setMon(mon.isChecked());
                          profile.setTue(tue.isChecked());
                          profile.setWed(wed.isChecked());
                          profile.setTur(thu.isChecked());
                          profile.setFri(fri.isChecked());
                          from.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                          showTimePicker();
                          }
                          });
                           to.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                          showTimePicker();
                          }
                          });
                           realm.copyToRealmOrUpdate(profile);
                          // list.add(profile);
                          adapter.notifyDataSetChanged();
                          realm.commitTransaction();
                          alertDialog.dismiss();
                          Toast.makeText(MainActivity.this,"Done"+list.size()+" "+profile.getId(),Toast.LENGTH_LONG).show();
                      }
                  });

               /* realm.executeTransactionAsync(new Realm.Transaction() {
                      @Override
                      public void execute(Realm bgRealm) {
                          //  Profile user = bgRealm.createObject(Profile.class);
                          Profile profile = new Profile();
                          profile.setName(name.getText().toString().trim());
                          profile.setSat(sat.isChecked());
                          profile.setSun(sun.isChecked());
                          profile.setMon(mon.isChecked());
                          profile.setTue(tue.isChecked());
                          profile.setWed(wed.isChecked());
                          profile.setTur(thu.isChecked());
                          profile.setFri(fri.isChecked());
                          from.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View view) {
                                  showTimePicker();
                              }
                          });
                          to.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View view) {
                                  showTimePicker();
                              }
                          });

                          list.add(profile);
                          adapter.notifyDataSetChanged();
                          realm.copyToRealmOrUpdate(profile);


                          bgRealm.copyToRealmOrUpdate(profile);
                          bgRealm.commitTransaction();
                      }
                  }, new Realm.Transaction.OnSuccess() {
                      @Override
                      public void onSuccess() {
                          Log.v("Database","Data inserted");

                      }
                  }, new Realm.Transaction.OnError() {
                      @Override
                      public void onError(Throwable error) {
                          Log.e("Database",error.getMessage());
                      }
                  });*/


              }catch (Exception e){
                  Log.d("ex" , "input error") ;
              }

            }
        });

            }



    public void showTimePicker() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.time, null);
        dialogBuilder.setView(dialogView);
        timeAlertDialog = dialogBuilder.create();



          dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                  timeAlertDialog.dismiss();
              }
          });
          dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
             timeAlertDialog.dismiss();
              }
          });
        timeAlertDialog = dialogBuilder.create();
        timeAlertDialog.show();
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
