package com.example.ifttt;

import org.json.JSONObject;

public interface Trigger {

        boolean verificaCondizione();
        @Override
        String toString();
        JSONObject toJSONObject();
    }