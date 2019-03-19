package com.example.firebaseviewpager.Interface;

import com.example.firebaseviewpager.Model.Wall;

import java.util.List;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<Wall> walllist);
    void onFirebaseLoasFailed(String message);
}
