package com.sjacobpowell.dallascountyfrogcalls;

import android.widget.TextView;

/**
 * Created by S Jacob Powell on 5/2/2016.
 */
public class ViewHolder {
    TextView text;

    public ViewHolder(TextView text) {
        this.text = text;
    }

    public TextView getText() {
        return text;
    }

    public void setText(TextView text) {
        this.text = text;
    }
}
