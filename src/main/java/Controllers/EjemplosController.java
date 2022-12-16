package Controllers;

import Entities.Productos;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "EjemplosController", urlPatterns = {"/Ejemplos"})
public class EjemplosController extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(
            HttpServletRequest request, 
            HttpServletResponse response) 
            throws ServletException, IOException {
        
        Productos shampoo = new Productos("Shampoo", "Plusbelle", 1000);
        String producto = gson.toJson(shampoo);
        
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(producto);
        out.flush();
    } 

    @Override
    protected void doPost(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
        
        Productos shampucito = new Productos("Shampucito", "J&J", 850);
        String producto = gson.toJson(shampucito);
        
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(producto);
        out.flush();
    }

    @Override
    protected void doPut(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
        
        Productos detergente = new Productos("Detergente", "Magistral", 1250);
        String producto = gson.toJson(detergente);
       
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(producto);
        out.flush();
    }

    @Override
    protected void doDelete(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
        
        Productos pelota = new Productos("Balón", "Adidas", 10250);
        String producto = gson.toJson(pelota);
        
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(producto);
        out.flush();
    }
    
    
    
    
    
    

}
