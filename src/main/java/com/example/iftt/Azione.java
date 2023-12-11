package com.example.ifttt;

import org.json.JSONObject;

public interface Azione {

        void eseguiAzione();
        @Override
        String toString();

        JSONObject toJSONObject();

    }
