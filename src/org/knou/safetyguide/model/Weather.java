package org.knou.safetyguide.model;

/**
 * Created by namhyeon on 2015-07-20.
 */
public class Weather {
    private String class1;
    private String class2;
    private String class3;
    private String code;
    private String name;
    private String type;
    private String desc;
    private String sample;
    private String copyright;
    private String url;

    public Weather() {  }
    public Weather(String class1, String class2, String class3) {
        super();
        this.class1 = class1;
        this.class2 = class2;
        this.class3 = class3;
    }

    public String getClass1() {
        return class1;
    }
    public void setClass1(String class1) {
        this.class1 = class1;
    }

    public String getClass2() {
        return class2;
    }
    public void setClass2(String class2) {
        this.class2 = class2;
    }

    public String getClass3() {
        return class3;
    }
    public void setClass3(String class3) {
        this.class3 = class3;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) { this.code = code; }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSample() {
        return sample;
    }
    public void setSample(String sample) {
        this.sample = sample;
    }

    public String getCopyright() {
        return copyright;
    }
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
}
