package biz.vumobile.contentprovider;

import android.provider.BaseColumns;

/**
 * Created by touhid on 12/30/17.
 */

public class ContactModel {

    String id;
    String name;
    String number;

    public ContactModel(String id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
