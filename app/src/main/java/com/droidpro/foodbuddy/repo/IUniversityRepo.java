package com.droidpro.foodbuddy.repo;


import com.droidpro.foodbuddy.models.University;

/**
 * Created by yml on 22/4/17.
 */

public interface IUniversityRepo  {

    void addUniversity(University university, OnAddUniversityCallback onAddUniversityCallback);

    void getUniversityById(IGetUniversityCallback iGetUniversityCallback);
}
