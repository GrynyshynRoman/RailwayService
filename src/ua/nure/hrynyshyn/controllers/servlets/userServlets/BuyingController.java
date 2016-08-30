package ua.nure.hrynyshyn.controllers.servlets.userServlets;

import ua.nure.hrynyshyn.core.DBSupport.searchEngine.SearchResult;
import ua.nure.hrynyshyn.core.entities.service.Ticket;
import ua.nure.hrynyshyn.core.entities.service.User;
import ua.nure.hrynyshyn.core.supportClasses.BuyingProcess;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by GrynyshynRoman on 30.08.2016.
 */
@WebServlet(name = "BuyTicket", urlPatterns = "/buyTicket")
public class BuyingController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        SearchResult result=(SearchResult)session.getAttribute("selectedResult");
        int carriageNumber=Integer.parseInt(request.getParameter("carriageNumber"));
        Ticket ticket=new Ticket();
        ticket.setUser_ID(user.getUser_ID());
        ticket.setTrain_ID(result.getTrain().getTrain_ID());
        ticket.setCarriageNumber(carriageNumber);
        ticket.setDeptStation_ID(result.getDepartStation().getStation_ID());
        ticket.setDeptTime(result.getDepartTime());
        ticket.setDestStation_ID(result.getDestStation().getStation_ID());
        ticket.setDestTime(result.getDestTime());
        ticket.setPrice(result.getPrice());
        boolean isSuccess=BuyingProcess.buy(ticket);
        if (isSuccess){
            response.sendRedirect("buyingSuccess.jsp");
        }else {
            response.sendRedirect("buyingError");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
