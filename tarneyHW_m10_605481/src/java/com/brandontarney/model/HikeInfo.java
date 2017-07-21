/* Class (javabean) HikeInfo
 * - encapsulates the hike info as our model
 * @author  Brandon Tarney
 * @since   7/17/2017
 */
package com.brandontarney.model;

import com.brandontarney.model.Rates;

public class HikeInfo {

    private Rates rate = null;
    private int partySize = -1;
    private boolean success = false;
    private String details = "";

    public void setRate(Rates newRate) {
        if (newRate.isValidDates() == true) {
            this.success = true;
        } else {
            this.success = false;
        }
        this.rate = newRate;
    }

    public Rates getRate() throws BadRateException {
        if (this.rate == null) {
            throw new BadRateException("Rate not set");
        }
        return this.rate;
    }

    public void setPartySize(int size) {
        this.partySize = size;
    }

    public int getPartySzie() {
        return this.partySize;
    }

    public void setSuccess(boolean result) {
        this.success = result;
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setDetails(String newDetails) {
        this.details = newDetails;
    }

    public String getDetails() {
        if (this.rate != null && this.details.equals("")) {
            return this.rate.getDetails();
        } else {
            return this.details;
        }
    }
}

