package com.android.basics.data.core;

import android.os.AsyncTask;

public class DaoExecutor {
    private DaoCallback daoCallback;

    public DaoExecutor() {
    }

    public void start(DaoCallback daoCallback) {
        this.daoCallback = daoCallback;
        new DaoProcessAsyncTask().execute();
    }

    private class DaoProcessAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            daoCallback.doAsync();
            return null;
        }
    }
}