package ua.nure.hrynyshyn.controllers.servlets.adminServlets.routeSupport;

import ua.nure.hrynyshyn.core.entities.railway.realEstate.Route;
import ua.nure.hrynyshyn.core.moderating.Administrator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GrynyshynRoman on 15.08.2016.
 */
@WebServlet(name = "createRouteServlet", urlPatterns = "/createRoute")
public class createRouteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Administrator administrator=new Administrator();
        Route route=new Route();
        route.setDepartStation_ID(Integer.parseInt(request.getParameter("deptStationID")));

        long deptDate=0;
        long deptTime=0;
        long destDate=0;
        long destTime=0;
        try {
            deptDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("deptDate")).getTime();
            deptTime=new SimpleDateFormat("H:mm").parse(request.getParameter("deptTime")).getTime();
            destDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("destDate")).getTime();
            destTime=new SimpleDateFormat("H:mm").parse(request.getParameter("destTime")).getTime();
        }catch (ParseException e){
            System.err.println(e);
        }
        route.setDepartTime(deptTime+deptDate);
        route.setDestStation_ID(Integer.parseInt(request.getParameter("destStationID")));
        route.setDestTime(destDate+destTime);
        administrator.createRoute(route);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
