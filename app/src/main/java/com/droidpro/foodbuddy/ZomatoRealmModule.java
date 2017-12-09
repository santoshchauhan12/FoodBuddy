package com.droidpro.foodbuddy;


import com.droidpro.foodbuddy.models.Students;
import com.droidpro.foodbuddy.models.University;

import io.realm.annotations.RealmModule;

/**
 * Created by yml on 22/4/17.
 */


@RealmModule(classes = {University.class, Students.class})
public class ZomatoRealmModule {
}
