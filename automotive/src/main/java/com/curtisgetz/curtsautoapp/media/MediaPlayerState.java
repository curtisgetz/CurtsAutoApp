package com.curtisgetz.curtsautoapp.media;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class MediaPlayerState {


    public static final int IDLE = 0;
    public static final int INITIALIZED = 1;
    public static final int PREPARING = 2;
    public static final int PREPARED = 3;
    public static final int STARTED = 4;
    public static final int STOPPED = 5;
    public static final int PAUSED = 6;
    public static final int PLAYBACKCOMPLETED = 7;
    public static final int END = 8;
    public static final int ERROR = 9;

    public static final int[] IDLE_ALLOWED = {INITIALIZED};
    public static final int[] INITIALIZED_ALLOWED = {PREPARING, PREPARED};
    public static final int[] PREPARING_ALLOWED = {PREPARED};
    public static final int[] PREPARED_ALLOWED = {STOPPED, STARTED, PREPARED};
    public static final int[] STARTED_ALLOWED = {STARTED, STOPPED, PAUSED, PLAYBACKCOMPLETED};
    public static final int[] STOPPED_ALLOWED = {STOPPED, PREPARED, PREPARING};
    public static final int[] PAUSED_ALLOWED = {PAUSED, STARTED, STOPPED};
    public static final int[] PLAYBACKCOMPLETED_ALLOWED = {PLAYBACKCOMPLETED, STARTED, STOPPED};
    public static final int[] END_ALLOWED = {IDLE};
    public static final int[] ERROR_ALLOWED = {IDLE};




    public static boolean isStateAllowed(int currentState, int requestedState){
        switch (currentState){
            case IDLE:
                return checkArray(requestedState, IDLE_ALLOWED);
            case INITIALIZED:
                return checkArray(requestedState, INITIALIZED_ALLOWED);
            case PREPARING:
                return checkArray(requestedState, PREPARING_ALLOWED);
            case PREPARED:
                return checkArray(requestedState, PREPARED_ALLOWED);
            case STARTED:
                return checkArray(requestedState, STARTED_ALLOWED);
            case STOPPED:
                return checkArray(requestedState, STOPPED_ALLOWED);
            case PAUSED:
                return checkArray(requestedState, PAUSED_ALLOWED);
            case PLAYBACKCOMPLETED:
                return checkArray(requestedState, PLAYBACKCOMPLETED_ALLOWED);
            case END:
                return checkArray(requestedState, END_ALLOWED);
            case ERROR:
                return checkArray(requestedState, ERROR_ALLOWED);
        }
        return false;
    }

    private static boolean checkArray(int value, int[] array){
        for (int j : array) {
            if (value == j) return true;
        }
        return false;
    }

    public static String getStatus(int state){
        switch (state){
            case STARTED:
                return "Playing";
            case STOPPED:
                return "Stopped";
            case INITIALIZED:
            case PREPARING:
                return "Loading";
        }
        return "";
    }

    @IntDef({IDLE, INITIALIZED, PREPARING, PREPARED, STARTED, STOPPED, PAUSED, PLAYBACKCOMPLETED, END, ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlayerState{}


    //public MediaPlayerState()



}
