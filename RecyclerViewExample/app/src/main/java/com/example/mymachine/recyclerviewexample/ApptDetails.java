package com.example.mymachine.recyclerviewexample;

/**
 * Created by myMachine on 11/16/2016.
 */

public class ApptDetails {

    private String apptSalonName;
    private String apptSalonAddress;
    private String apptDate;
    private String apptTime;

    public ApptDetails(){

    }

    public ApptDetails(String apptSalonName, String apptSalonAddress, String apptDate, String apptTime){

        this.apptSalonName= apptSalonName;
        this.apptSalonAddress= apptSalonAddress;
        this.apptDate= apptDate;
        this.apptTime= apptTime;
    }





    public String getApptSalonName() {
        return apptSalonName;
    }

    public void setApptSalonName(String apptSalonName) {
        this.apptSalonName = apptSalonName;
    }

    public String getApptSalonAddress() {
        return apptSalonAddress;
    }

    public void setApptSalonAddress(String apptSalonAddress) {
        this.apptSalonAddress = apptSalonAddress;
    }

    public String getApptDate() {
        return apptDate;
    }

    public void setApptDate(String apptDate) {
        this.apptDate = apptDate;
    }

    public String getApptTime() {
        return apptTime;
    }

    public void setApptTime(String apptTime) {
        this.apptTime = apptTime;
    }
}
