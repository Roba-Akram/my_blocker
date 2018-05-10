package com.iug.blocker.model;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm ;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }
   public void save(final Profile profile){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Profile p = realm.copyToRealm(profile);

            }
        });

    }
    public ArrayList<Profile> restore(){
        ArrayList<Profile> prof=new ArrayList<>();
        RealmResults<Profile> profiles=realm.where(Profile.class).findAllAsync();
        for(Profile p:profiles){
            prof.add(p);
        }
        return prof;
    }
}
