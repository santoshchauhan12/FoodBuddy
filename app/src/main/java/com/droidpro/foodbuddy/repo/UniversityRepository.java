package com.droidpro.foodbuddy.repo;


import com.droidpro.foodbuddy.FoodBuddyApp;
import com.droidpro.foodbuddy.models.University;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by yml on 22/4/17.
 */

public class UniversityRepository implements IUniversityRepo{

    @Override
    public void getUniversityById(IGetUniversityCallback iGetUniversityCallback) {
        Realm realm = Realm.getInstance(FoodBuddyApp.getInstance());
        RealmQuery query = realm.where(University.class);
        RealmResults results = query.findAll();
        if(iGetUniversityCallback != null){
            iGetUniversityCallback.onSuccess(results);
        }
    }

    @Override
    public void addUniversity(University university, OnAddUniversityCallback onAddUniversityCallback) {
        Realm realm = Realm.getInstance(FoodBuddyApp.getInstance());
        realm.beginTransaction();
        University u = realm.createObject(University.class);
        u.setId(UUID.randomUUID().toString());
        u.setName(university.getName());
        realm.commitTransaction();

        if (onAddUniversityCallback != null)

            onAddUniversityCallback.onSuccess();
    }
}
