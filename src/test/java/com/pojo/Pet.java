package com.pojo;


import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(value = "id", allowSetters = true, ignoreUnknown = true)
public class Pet {

private int id2;
private String name1;
private String name2;
private String photoUrls;
private int id3;
private String name3;
private String status;


    public String toString() {
     return "   {" +
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

