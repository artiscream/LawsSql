package com.example.arti_scream.sqllaws;


public class Laws {
    int _id;
    int _numrozd;
    String _rozd;
    int _numstat;
    String _stat;


    public Laws(){}


    public Laws(int id, int numrozd, String rozd, int numstat, String stat){
        this._id = id;
        this._numrozd = numrozd;
        this._rozd = rozd;
        this._numstat = numstat;
        this._stat = stat;
    }

    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }


}
