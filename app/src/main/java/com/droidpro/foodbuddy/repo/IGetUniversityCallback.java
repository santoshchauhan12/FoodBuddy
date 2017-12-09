package com.droidpro.foodbuddy.repo;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by yml on 22/4/17.
 */

public interface IGetUniversityCallback {

    void onSuccess(RealmResults universityList);
}
