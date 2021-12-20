package com.filizuzulmez.turkiyefinans.model;

import java.util.List;

public class Section {

    private String sectionName;
    private List<Result> sectionItems;

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<Result> getSectionItems() {
        return sectionItems;
    }

    public void setSectionItems(List<Result> sectionItems) {
        this.sectionItems = sectionItems;
    }
}
