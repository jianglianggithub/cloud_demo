package com;

public class Vo {
    private String cur;
    private String size;

    @Override
    public String toString() {
        return "Vo{" +
                "cur='" + cur + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
