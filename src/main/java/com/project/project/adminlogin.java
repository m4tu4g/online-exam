
package com.project.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "adminlogin", urlPatterns = {"/adminlogin"})
public class adminlogin extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>OES</title>");            
            out.println("</head>");
            
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            String driverName="com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://lcpbq9az4jklobvq.cbetxkdyhwsb.us-east-1.rds.amazonaws.com/ehjn4n6t5lh0zafz";
            String dbuser = "vt6q8mzm255je024";
            String dbpass = "xgabcm5xp3gan7n0";
            String sql = "select * from adminlog where email=? and password=?";
            
            String id = "";
            String emailid = "";
            
            try{
                if(email!=null){
                    
                    Class.forName(driverName);
                    conn=DriverManager.getConnection(url, dbuser, dbpass);
                    ps=conn.prepareStatement(sql);
                    ps.setString(1, email);
                    ps.setString(2, pass);
                    
                    rs=ps.executeQuery();
                    
                    if(rs.next()){
                        HttpSession hs = request.getSession();
                        hs.setAttribute("id", id);
                        hs.setAttribute("email", emailid);
                        RequestDispatcher rd = request.getRequestDispatcher("adminhome.jsp");
                        rd.forward(request, response);
                    }
                    else{
                        HttpSession hs = request.getSession();
                        RequestDispatcher d = request.getRequestDispatcher("adminlogin.jsp");
                        hs.setAttribute("err", "User Credentials Incorrect");
                        d.forward(request,response);
                        rs.close();
                        ps.close();
                    }
                }
                
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
            catch(Exception e){
                
            }
        }
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
