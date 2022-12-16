/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diseno
 */
@WebServlet(name = "PagesController", urlPatterns = {"/pages"})
public class PagesController extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = null;
        String action = req.getParameter("action");
        
        switch(action){
            case "add":
                dispatcher = req.getRequestDispatcher("agregar.html");
                break;
            case "update":
                dispatcher = req.getRequestDispatcher("modificar.html");
                break;
            case "delete":
                dispatcher = req.getRequestDispatcher("borrar.html");
                break;
            default:
                dispatcher = req.getRequestDispatcher("index.html");
                break;
        }
        dispatcher.forward(req, resp);
    }

    
}
