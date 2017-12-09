package com.droidpro.foodbuddy.models;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by yml on 22/4/17.
 */

public class University extends RealmObject {

    @PrimaryKey
    private  String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public RealmList getStudents() {
        return students;
    }

    public void setStudents(RealmList students) {
        this.students = students;
    }
*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Required
    private String name;
    //private RealmList students;

}
