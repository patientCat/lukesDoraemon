package org.luke.doraemon.common.constant;

public enum JobCenterName implements ValueEnum<String>{
    ExampleJobCenter("ExampleJobCenter"),
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
