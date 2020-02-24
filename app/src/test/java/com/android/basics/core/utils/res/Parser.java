package com.android.basics.core.utils.res;

import java.util.Map;

public interface Parser<K, V> {

    interface OnFileParsedListener<K, V> {
        void onFileParsedSuccessfully(Map<? extends K, ? extends V> m);
    }

    void parse(OnFileParsedListener<K, V> onFileParsedListener);
}
