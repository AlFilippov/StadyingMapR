package com.alphilippov.studyingmap.ui;

import android.view.View;

public interface RecyclerViewClickListener {
    void onClick(View view , int position);
    void onLongClick(View view , int position);
}
