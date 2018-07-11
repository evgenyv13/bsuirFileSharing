package com.filesharing.mvc.mvcModel;

public class FileObjects {
    public int id=-1;
    public String fileName;
    public String fileExtension;
    private boolean isFileOwner = false;
    private boolean isFile = false;

    public int getId() {
        return id;
    }

    public boolean isFileOwner() {
        return isFileOwner;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFileOwner(boolean fileOwner) {
        isFileOwner = fileOwner;
    }

    public boolean getBtnToDelate() {
        if((isFileOwner==true)&&(isFile)==true) return true;
        else return false;
    }


    public void setFile(boolean file) {
        isFile = file;
    }

    public FileObjects(String fileName, String fileExtension) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    @Override
    public String toString() {
        return fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public String getFileName() {
        return fileName;
    }
}