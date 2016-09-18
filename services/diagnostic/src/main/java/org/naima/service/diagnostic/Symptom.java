package org.naima.service.diagnostic;

public class Symptom {
    private String id;
    private String title;
    private String name;
    private String format;

    public String getTitle() {
        return title;
    }
    public String getName() {
        return name;
    }
    public String getFormat() {
        return format;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
