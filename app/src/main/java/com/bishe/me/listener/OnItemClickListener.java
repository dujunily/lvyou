package com.bishe.me.listener;

import android.view.View;

/**
 * @author luoxin
 * @since 2021-04-09 00:09
 */

public interface OnItemClickListener {
    void onItemClick(View view, int position);
    void onItemLongClick(View view , int position);
}