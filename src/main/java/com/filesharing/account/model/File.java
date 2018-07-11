package com.filesharing.account.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "file")
public class File {

    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private int file_owner ;

    @Id
    private String file_path;
    private String file_url;

    private String file_name;

    public File() {
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public File(int file_owner, String file_path, String file_name) {
        this.file_owner = file_owner;
        this.file_path = file_path;
        this.file_name = file_name;
    }

    public int getId() {
        return id;
    }

    public int getFile_owner() {
        return file_owner;
    }

    public void setFile_owner(int file_owner) {
        this.file_owner = file_owner;
    }

    public String getFile_path() {
        return file_path;
    }

    @Override
    public String toString(){
        return "id="+id+", name="+file_name+", owner="+file_owner;
    }



    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }


}
