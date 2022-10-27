package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties( ignoreUnknown = true )
public class DataElementWrapper
{
    @JsonProperty
    private List<DataElement> dataElements = new ArrayList<>();

    public DataElementWrapper()
    {
    }

    public List<DataElement> getDataElements()
    {
        return dataElements;
    }

    public void setDataElements( List<DataElement> dataElements )
    {
        this.dataElements = dataElements;
    }
}
