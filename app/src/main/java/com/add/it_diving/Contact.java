package com.add.it_diving;

public class Contact {

    private String Name;
    private String PHone;
    private String imageUri;

    public Contact(String name, String PHone, String imageUri) {
        Name = name;
        this.PHone = PHone;
        this.imageUri = imageUri;
    }

    public Contact() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPHone() {
        return PHone;
    }

    public void setPHone(String PHone) {
        this.PHone = PHone;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
