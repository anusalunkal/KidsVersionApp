package com.nextgen.storytellingapp.ModelClass;

public class StoryModelClass {

    String id;
    String title;
    String desc;
    String image;


    public StoryModelClass(String id,String title,String desc,String image) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.image = image;

    }

    public String getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getDesc(){
        return desc;
    }

    public String getImage(){
        return image;
    }
}
