/** HikeQueryParser Class
 * <p>
 * Contains static methods to extract parameters from an HTTP form query</p>
 *
 * @author Brandon Tarney
 * @since 6/28/2017
 */
package com.brandontarney.controller;

import com.brandontarney.model.Rates.HIKE;

public class HikeQueryParser {

    public static HIKE getHike(String queryString) throws BadQueryStringException {
        int queryStringOrder = 0;
        HIKE hikeEnum;

        String[] queryValues = queryString.split("&");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for hike)");
        }

        String[] hikeKeyValuePairString = queryValues[queryStringOrder].split("=");
        String hikeEnumStr = hikeKeyValuePairString[1].toUpperCase();

        switch (hikeEnumStr) {
            case "HELLROARING":
                hikeEnum = HIKE.HELLROARING;
                break;
            case "GARDINER":
                hikeEnum = HIKE.GARDINER;
                break;
            case "BEATEN":
                hikeEnum = HIKE.BEATEN;
                break;
            default:
                throw new BadQueryStringException("Invalid hike value");
        }
        return hikeEnum;
    }

    public static int getYear(String queryString) throws BadQueryStringException {
        int queryStringOrder = 1;
        int hikeYear;

        String[] queryValues = queryString.split("&");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for year)");
        }

        String[] yearKeyValuePairString = queryValues[queryStringOrder].split("=");
        String hikeYearString = yearKeyValuePairString[1];

        try {
            hikeYear = Integer.parseInt(hikeYearString);
        } catch (NumberFormatException e) {
            throw new BadQueryStringException("Invalid year value");
        }

        return hikeYear;
    }

    public static int getMonth(String queryString) throws BadQueryStringException {
        int queryStringOrder = 2;
        int hikeMonth;

        String[] queryValues = queryString.split("&");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for month)");
        }

        String[] monthKeyValuePairString = queryValues[queryStringOrder].split("=");
        String hikeMonthString = monthKeyValuePairString[1];

        try {
            hikeMonth = Integer.parseInt(hikeMonthString);
        } catch (NumberFormatException e) {
            throw new BadQueryStringException("Invalid month value");
        }

        return hikeMonth;
    }

    public static int getDay(String queryString) throws BadQueryStringException {
        int queryStringOrder = 3;
        int hikeDay;

        String[] queryValues = queryString.split("&");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for day)");
        }

        String[] dayKeyValuePairString = queryValues[queryStringOrder].split("=");
        String hikeDayString = dayKeyValuePairString[1];

        try {
            hikeDay = Integer.parseInt(hikeDayString);
        } catch (NumberFormatException e) {
            throw new BadQueryStringException("Invalid day value");
        }

        return hikeDay;
    }

    public static int getDuration(String queryString) throws BadQueryStringException {
        int queryStringOrder = 2;
        int hikeDuration;

        String[] queryValues = queryString.split("&");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for duration)");
        }

        String[] durationKeyValuePairString = queryValues[queryStringOrder].split("=");
        String hikeDurationString = durationKeyValuePairString[1];

        try {
            hikeDuration = Integer.parseInt(hikeDurationString);
        } catch (NumberFormatException e) {
            throw new BadQueryStringException("Invalid duration value");
        }

        return hikeDuration;
    }

    public static int getPartySize(String queryString) throws BadQueryStringException {
        int queryStringOrder = 3;
        int partySize;

        String[] queryValues = queryString.split("&");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for party size)");
        }

        //System.out.println(queryString);
        String[] partySizeKeyValuePairString = queryValues[queryStringOrder].split("=");
        String hikePartySizeString = partySizeKeyValuePairString[1];

        try {
            partySize = Integer.parseInt(hikePartySizeString);
        } catch (NumberFormatException e) {
            throw new BadQueryStringException("Invalid duration value");
        }

        return partySize;
    }

    public static int[] getDate(String queryString) throws BadQueryStringException {
        int queryStringOrder = 1;
        int[] monthDayYear = new int[3];

        //System.out.println(queryString);

        String[] queryValues = queryString.split("&");
        if (queryValues.length <= queryStringOrder) {
            throw new BadQueryStringException("Not enough parameters (looking for party size)");
        }

        //System.out.println(queryString);
        String[] datePairString = queryValues[queryStringOrder].split("=");
        String dateString = datePairString[1];
        //System.out.println(dateString);

        String[] monthDayYearStr = dateString.split("%2F");

        try {
            monthDayYear[0] = Integer.parseInt(monthDayYearStr[0]);
            monthDayYear[1] = Integer.parseInt(monthDayYearStr[1]);
            monthDayYear[2] = Integer.parseInt(monthDayYearStr[2]);
        } catch (NumberFormatException e) {
            throw new BadQueryStringException("Invalid day, month, and/or year value");
        }

        return monthDayYear;
    }
}
