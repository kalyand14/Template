package com.android.basics.core;

public interface Mapper<F, T> {

    T convert(F fromObj);
}
