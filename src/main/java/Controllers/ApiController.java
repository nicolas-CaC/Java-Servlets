/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Entities.Productos;
import Errores.Errores;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diseno
 */
@WebServlet(name = "ApiController", urlPatterns = {"/api"})
public class ApiController extends HttpServlet {

    Productos prod1 = new Productos("Juguete", "Hasbro", 5000);
    Productos prod2 = new Productos("Harina", "Pureza", 1000);
    
    private Gson gson = new Gson();
    List<Productos> lista = new ArrayList<>();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) 
            throws ServletException, IOException {
       
        String listaJson = gson.toJson(Productos.getListado());
        
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(listaJson);
        out.flush();
    }

    @Override
    protected void doPost(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String body = inputStreamToString(req.getInputStream());
        Productos producto = gson.fromJson(body, Productos.class);
//        System.out.println(body);
        
        producto.addProduct();
        
        Errores error = new Errores(0);
        String listaJson = gson.toJson(error);
        
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(listaJson);
        out.flush();
    }

    @Override
    protected void doPut(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
        
        Errores error;
        boolean encontrado;
        
        String body = inputStreamToString(req.getInputStream());
        Productos producto = new Gson().fromJson(body, Productos.class);
        encontrado = producto.updateProduct();
        
        error = encontrado 
                ? new Errores(0) 
                : new Errores(1);
        
        String listaJson = new Gson().toJson(error);
        
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(listaJson);
        out.flush();
    }

    @Override
    protected void doDelete(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
        
        Errores error;
        boolean encontrado;
        
        String nombre = req.getParameter("nombre");
        encontrado = Productos.deleteProduct(nombre);
        
        error = encontrado 
                ? new Errores(0) 
                : new Errores(1);
        
        String listaJson = gson.toJson(error);
        
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(listaJson);
        out.flush();
    }
    
    // METODOS
    
    private static String inputStreamToString(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        return scanner.hasNext()
                ? scanner.useDelimiter("\\A").next()
                : "";
    }
    
}
