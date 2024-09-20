package com.nextgen.storytellingapp.ModelClass;

public class PlotModelClass {

    String id;
    String plot;


    public PlotModelClass(String id,String plot) {
        this.id = id;
        this.plot = plot;

    }

    public String getId(){
        return id;
    }

    public String getPlot(){
        return plot;
    }


}
