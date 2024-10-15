package com.example.oolab1;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class RentRequest {

    private String begin;
    private String end_date;

    // Getters and Setters
    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end_date;
    }

    public void setEnd(String end_date) {
        this.end_date = end_date;
    }
}
