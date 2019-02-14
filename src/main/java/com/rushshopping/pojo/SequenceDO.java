package com.rushshopping.pojo;

public class SequenceDO {
    private String name;

    private Integer currentValue;

    private Integer step;

    private Integer maxValue;

    private Integer initValue;

    public SequenceDO(String name, Integer currentValue, Integer step, Integer maxValue, Integer initValue) {
        this.name = name;
        this.currentValue = currentValue;
        this.step = step;
        this.maxValue = maxValue;
        this.initValue = initValue;
    }

    public SequenceDO() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getInitValue() {
        return initValue;
    }

    public void setInitValue(Integer initValue) {
        this.initValue = initValue;
    }
}