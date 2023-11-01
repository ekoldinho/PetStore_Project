package com.pojo;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetForUpdate {

    private long id1;
    private int id2;
    private String name1;
    private String name2;
    private String photoUrls;
    private int id3;
    private String name3;
    private String status;


    public String toString() {
        return "   {" +
                "\"id\": " + id1 + "," +
                "\"category\": {"+
                "\"id\": " + id2 + "," +
                "\"name\": \"" + name1 +
                "\" }," +
                "\"name\": \"" + name2 + "\"," +
                "\"photoUrls\": [\""+
                photoUrls +
                "\"]," +
                "\"tags\": [" +
                "{" +
                "\"id\": " + id3 + "," +
                "\"name\": \""+ name3 +
                "\"}" +
                "]," +
                "\"status\": \"" + status +
                "\"}";
    }

}
