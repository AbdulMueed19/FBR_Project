package com.example.fbr_semester_project;

public class Approval {
    private int FBRid;
    private String FBRstatus;
    private String FBRComments;

    public Approval(int FBRid, String FBRComments, String FBRstatus) {
        this.FBRid = FBRid;
        this.FBRstatus = FBRstatus;
        this.FBRComments = FBRComments;
    }

    public int getFBRid() {
        return FBRid;
    }

    public void setFBRid(int FBRid) {
        this.FBRid = FBRid;
    }

    public String getFBRstatus() {
        return FBRstatus;
    }

    public void setFBRstatus(String FBRstatus) {
        this.FBRstatus = FBRstatus;
    }

    public String getFBRComments() {
        return FBRComments;
    }

    public void setFBRComments(String FBRComments) {
        this.FBRComments = FBRComments;
    }
}
