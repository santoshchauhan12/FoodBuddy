package com.droidpro.foodbuddy.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by deepak on 19/4/17.
 * All application view holder will extend this class
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{

    protected View itemView;
    protected Context context;


    /**
     *
     * @param itemView - Main View
     */
    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.context = itemView.getContext();
    }

    public abstract void bindData(int pos, T dataModel);
}
