package Controllers;

import Entities.Productos;
import Errores.Errores;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "ApiController", 
        urlPatterns = {"/api"})
public class ApiController extends HttpServlet {

    private final Gson gson = new Gson();

    // Métodos de nuestro endpoint "/api"
    
    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) 
            throws ServletException, IOException {
       
        String listaJson = gson.toJson(Productos.getListado());
        enviar(resp, listaJson);
    }

    @Override
    protected void doPost(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String body = inputStreamToString(req.getInputStream());
        Productos producto = gson.fromJson(body, Productos.class); 
        boolean exito = producto.addProduct();
        
        String error = obtenerError(exito);
        enviar(resp, error);
    }

    @Override
    protected void doPut(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String body = inputStreamToString(req.getInputStream());
        Productos producto = new Gson().fromJson(body, Productos.class);
        boolean encontrado = producto.updateProduct();
        
        String error = obtenerError(encontrado);        
        enviar(resp, error);
    }

    @Override
    protected void doDelete(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
        
        String nombre = req.getParameter("nombre");
        boolean encontrado = Productos.deleteProduct(nombre);

        String error = obtenerError(encontrado);        
        enviar(resp, error);
    }
    
    // Métodos de la clase ApiController
    
    private static String inputStreamToString(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        return scanner.hasNext()
                ? scanner.useDelimiter("\\A").next()
                : "";
    }
    
    private String obtenerError(boolean encontrado){
                Errores error = encontrado 
                ? new Errores(0) 
                : new Errores(1);        
        return gson.toJson(error);
    }
    
    private void enviar(HttpServletResponse resp, String objeto) 
            throws IOException{
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(objeto);
        out.flush();
    }
}
