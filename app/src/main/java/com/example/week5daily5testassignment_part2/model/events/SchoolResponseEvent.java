package com.example.week5daily5testassignment_part2.model.events;

import com.example.week5daily5testassignment_part2.model.school.SchoolResponse;

public class SchoolResponseEvent {

    private SchoolResponse[] schoolResponse;


    public SchoolResponseEvent(SchoolResponse[] schoolResponse) {
        this.schoolResponse = schoolResponse;
    }


    public SchoolResponse[] getSchoolResponse() {
        return schoolResponse;
    }

    public void setSchoolResponse(SchoolResponse[] schoolResponse) {
        this.schoolResponse = schoolResponse;
    }
}
