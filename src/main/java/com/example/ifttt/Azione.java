package com.example.ifttt;

import org.json.JSONObject;


public interface Azione {

        void eseguiAzione();

        JSONObject toJSONObject();

        @Override
        String toString();

    }
