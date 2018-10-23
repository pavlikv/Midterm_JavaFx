package controller;

import model.DBData;

public class Controller
{
    private DBData model;

    public Controller(){
        model = new DBData();
    }


    private boolean isEmpty(String value)
    {
        return value == null || value.equals("");
    }

}