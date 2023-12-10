package com.example.ifttt;

import org.json.JSONObject;


public interface Trigger {

    boolean verificaCondizione();

    JSONObject toJSONObject();

    @Override
    String toString();

}