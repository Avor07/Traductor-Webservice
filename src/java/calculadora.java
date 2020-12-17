/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.dataaccess.webservicesserver.NumberConversion;
import com.example.CalculatorService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Iterator;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.json.JSONObject;

/**
 *
 * @author ortiz
 */
@WebServlet(urlPatterns = {"/calculadora"})
public class calculadora extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/www.dataaccess.com/webservicesserver/numberconversion.wso.wsdl")
    private NumberConversion service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/calculator-webservice.mybluemix.net/calculator.wsdl")
    private CalculatorService service;

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
//        response.setContentType("text/plain");
//         
//        PrintWriter out = response.getWriter();
//        
//        String strJson = request.getParameter("numero1");
//        String strJson1 = request.getParameter("formdata");
//        JSONObject json = new JSONObject(strJson);
//       int nro= json.getInt("numero1");
////      int nro1=Integer.parseInt(request.getParameter("nro2"));
//        String respuesta = "";
//        int respuest = add(6,5);
//        respuesta += respuest;
//        out.println("<h1>"+respuesta+"</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = "";
        if (br != null) {
            line = br.readLine();
        }
        JSONObject obj = new JSONObject(line);
        int suma = add(Integer.parseInt(obj.get("numero1").toString()), Integer.parseInt(obj.get("numero2").toString()));
        String resultado = "" + suma;
        BigInteger integer = new BigInteger(resultado);
        String greetings1 = "" + numberToWords(integer);
        String objectToReturn = "{ Suma: '"+resultado+"', Traduccion: '"+greetings1+"' }";
        JSONObject ob = new JSONObject(objectToReturn);
        response.setContentType("application/json");
        out.println(ob);
        //response.getWriter().write(greetings);

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

    private int add(int intA, int intB) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.example.Calculator port = service.getCalculatorPort();
        return port.add(intA, intB);
    }

    private String numberToWords(java.math.BigInteger ubiNum) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        com.dataaccess.webservicesserver.NumberConversionSoapType port = service_1.getNumberConversionSoap();
        return port.numberToWords(ubiNum);
    }

}
