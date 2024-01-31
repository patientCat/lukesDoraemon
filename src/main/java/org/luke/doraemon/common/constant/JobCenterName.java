package org.luke.doraemon.common.constant;

public enum JobCenterName implements ValueEnum<String>{
    ExampleJobCenter("ExampleJobCenter"),
    StatusJobCenter("StatusJobCenter"),
    ;
    JobCenterName(final String name){
        this.value = name;
    }
    private final String value;
    @Override
    public String getValue() {
        return value;
    }
}
