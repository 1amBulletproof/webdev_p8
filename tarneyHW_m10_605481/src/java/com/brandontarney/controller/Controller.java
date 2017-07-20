/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brandontarney.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.year;
import java.time.LocalDate;
import static org.eclipse.jdt.internal.compiler.parser.Parser.name;
import static sun.security.jgss.GSSUtil.login;

import com.brandontarney.model.HikeInfo;
import com.brandontarney.model.Rates;
import com.brandontarney.model.Rates.HIKE;
import com.brandontarney.model.BookingDay;
import com.brandontarney.model.BadRateException;

/**
 *
 * @author Tarney
 */
public class Controller extends HttpServlet {

    public static final String INFO = "hike_info";
    public static final String DATE = "datepicker";
    public static final String DURATION = "duration";
    public static final String PARTY_SIZE = "people";
    public static final String HIKE = "hike";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();

        HikeInfo info = (HikeInfo) session.getAttribute(INFO);
        if (info == null) {
            info = new HikeInfo();
            session.setAttribute(INFO, info);
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/query.jsp");
            dispatcher.forward(request, response);
        } else {
            String queryString = request.getQueryString();
            System.out.println(queryString);
            int partySize = Integer.parseInt(request.getParameter(PARTY_SIZE));
            int duration = Integer.parseInt(request.getParameter(DURATION));

            try {
                HIKE hike = HikeQueryParser.getHike(queryString);
                int[] monthDayYear = HikeQueryParser.getDate(queryString);
                int month = monthDayYear[0];
                int day = monthDayYear[1];
                int year = monthDayYear[2];
                // CALCULATE RATE
                Rates rate = this.getRate(hike, duration, year, month, day);
                //STORE RATE & SUCCESS & PARTY_SIZE in HikeInfo BEAN/MODEL
                info.setRate(rate);
                info.setPartySize(partySize);
                //SELECT THE CORRECT JSP (QUERY IF MISSING VALUES, RESULT WITH VALID RATE< ERROR OTHERWISE)
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/result.jsp");
                dispatcher.forward(request, response);

            } catch (BadQueryStringException exception) {
                info.setDetails(exception.getMessage());
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/error.jsp");
                dispatcher.forward(request, response);
                // direct to the query or ERROR Page? createUserErrorPage(response, exception.getMessage());
            }
        }
    }

    /**
     * Compute the rate associated with a hike
     *
     * @param rate preliminary rate information of hike
     * @param duration duration of hike
     * @param year time of hike
     * @param month time of hike
     * @param day time of hike
     *
     * @return Final rate information for hike
     */
    public static Rates getRate(
            Rates.HIKE hike, int duration, int year, int month, int day) {

        BookingDay startDate = new BookingDay(year, month, day);

        Rates rate = new Rates(hike);
        rate.setBeginDate(startDate);
        rate.setDuration(duration);

        LocalDate today = LocalDate.now();
        int todayDay = today.getDayOfMonth();
        int todayMonth = today.getMonthValue();
        int todayYear = today.getYear();

        BookingDay todayBookingDay = new BookingDay(todayYear, todayMonth, todayDay);

        return rate;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
