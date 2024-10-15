package com.example.oolab1;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class RentRequest {

    private String begin;
    private String end;

    // Getters and Setters
    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
