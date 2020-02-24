package com.android.basics.core;

public interface Inverter<F, T> {
    T invert(F fromObj);
}
