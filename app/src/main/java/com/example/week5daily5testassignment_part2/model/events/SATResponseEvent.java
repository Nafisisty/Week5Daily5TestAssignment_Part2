package com.example.week5daily5testassignment_part2.model.events;

import com.example.week5daily5testassignment_part2.model.school.SATResponse;

public class SATResponseEvent {

    private SATResponse[] satResponse;

    public SATResponseEvent(SATResponse[] satResponse) {
        this.satResponse = satResponse;
    }

    public SATResponse[] getSatResponse() {
        return satResponse;
    }

    public void setSatResponse(SATResponse[] satResponse) {
        this.satResponse = satResponse;
    }
}
