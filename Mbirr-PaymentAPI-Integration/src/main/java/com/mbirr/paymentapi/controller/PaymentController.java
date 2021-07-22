/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbirr.paymentapi.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

/**
 *
 * @author ermias
 */
public class PaymentController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (request.getParameter("name") != null && request.getParameter("ammount") != null) {

                String name = request.getParameter("name");
                int ammountincent = Integer.parseInt(request.getParameter("ammount")) * 100;
                String tel = request.getParameter("phone_no");
                String contentSignature = null;

                JSONObject sender = new JSONObject();
                sender.put("name", name);
                sender.put("account_number", "66012470");
                sender.put("mobile_number", tel);
                sender.put("language", "en");

                JSONObject beneficiary = new JSONObject();
                beneficiary.put("name", "test web portal");
                beneficiary.put("account_number", "66012571");
                beneficiary.put("mobile_number", "0987786556");
                beneficiary.put("language", "en");

                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());

                JSONObject transaction = new JSONObject();
                transaction.put("simulation_only", "false");
                transaction.put("client_transaction_id", UUID.randomUUID().toString());
                transaction.put("timestamp", new SimpleDateFormat("yyyy-MM-dd").format(timestamp));
                transaction.put("amount_in_cents", ammountincent);
                transaction.put("operation_type", "Transfer");

                JSONObject requestJson = new JSONObject();
                requestJson.put("sender", sender);
                requestJson.put("beneficiary", beneficiary);
                requestJson.put("transaction", transaction);
                requestJson.put("end_user_message", "We've refunded your payment. Thank you.");

                System.out.println(requestJson);
                String finalJson = requestJson.toString();
                System.out.println(finalJson);

                String privateKey = "-----BEGIN PRIVATE KEY-----"
					+ "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALFf/kJIAtYxzIvr"
					+ "iMMTWKJVY8spyA+/pLbjitznHx+ABtqUkn3YbfoBKX2tQVuH86gO+2lZGFUrT0G4"
					+ "KxX7mCg5UyWMcdFVFzoJStyHTEuIxWV8vFh7IDRcHZOHil4If2h75SzyXpPxVjzV"
					+ "AXBuCLW98eM26///eVmOitmHW0mdAgMBAAECgYANDw0wIg8bZ/UwQ/oAqrb21KSR"
					+ "O5VAG5Lr6Bq8IsP21L0scI3MeBe4tUcxuoS6UWsN73RxEB8rfhHKu91oM+rCw/oJ"
					+ "8PH4QlJ2nOeQFP4CmZXriqrfuW6RCKRiB+ggxCHnH64JW8SCzODY7fdq3hG3wwLM"
					+ "dl1WryH8iaqBR9oe3QJBANsTw/l0LSxyuzOKazjNaLIFM04Z8xoRoqhD7QVuAKaV"
					+ "qoJZldq5eJFgC0lewF2KUugxBQNXDEJYlhbPNTXh2b8CQQDPRPTcer0iFAZSsQ6J"
					+ "wWNdRpUnxJjUBBebeZTgwYxuIjv0SLyCB/mcJaL9viCE+c48meYmwKshvdRpTJiu"
					+ "CZujAkEAy2EhGS8iVNY6NhH1kmkXHdU4GPR8PCJNF9rfaqABmKTvA035kXGHnaZF"
					+ "NBrziKNGbmo7lis0pU8qHwjEBD6kbwJAezDY+FJbJ24PdAaYRXgTvtS8wi4vR5RH"
					+ "E7lnq05eUPc3+zFgGUj0KsKT5Yyjd2WiFpLCIDZTgHJ7VTqZZJeBUQJAZ3ypMzOH"
					+ "7BK7Z17WcMCA6aRJ+U4RsoQxXh4vbkrnlraac6uV9BQKiaU0+ws2WckhpMXr1pNp"
					+ "3JGRhEoOwNwjpg=="
					+ "-----END PRIVATE KEY-----";

                try {
                    privateKey = privateKey.replaceAll("\\n", "").replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "");
                    PKCS8EncodedKeySpec kCS8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    PrivateKey pk = keyFactory.generatePrivate(kCS8EncodedKeySpec);
                    byte[] data = finalJson.getBytes("UTF8");
                    Signature signature = Signature.getInstance("SHA1withRSA");
                    signature.initSign(pk);
                    signature.update(data);
                    byte[] signatureBytes = signature.sign();
                    contentSignature = Base64.getEncoder().encodeToString(signatureBytes);
                } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e) {
                    System.out.println(e);
                }

                URL url = new URL("https://demo-api.mbirr.com/transactionEngine/payment");
                HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();

                httpUrlConnection.setConnectTimeout(60000);
                httpUrlConnection.setReadTimeout(60000);
                httpUrlConnection.setDoOutput(true);
                httpUrlConnection.setUseCaches(true);
                httpUrlConnection.setDoInput(true);
                httpUrlConnection.setRequestProperty("Content-Signature", contentSignature);

                OutputStream outputStream = httpUrlConnection.getOutputStream();
                byte[] byte_value = requestJson.toJSONString().getBytes("UTF-8");
                outputStream.write(byte_value);
                outputStream.flush();
                outputStream.close();

                String jsonResponse = null;
                String server_responce = null;
                int statusCode = 0;
                switch (httpUrlConnection.getResponseCode()) {
                    case 200:
                        InputStream inputStream = httpUrlConnection.getInputStream();
                        byte[] val_rspns = new byte[2048];
                        int i = 0;
                        StringBuilder stringBuilder = new StringBuilder();
                        while ((i = inputStream.read(val_rspns)) != -1) {
                            stringBuilder.append(new String(val_rspns, 0, i));
                        }
                        server_responce = httpUrlConnection.getResponseMessage();
                        statusCode = httpUrlConnection.getResponseCode();
                        jsonResponse = stringBuilder.toString();
                        inputStream.close();
                        break;
                    case 499:
                        InputStream inputStream1 = httpUrlConnection.getErrorStream();
                        byte[] val_rspns1 = new byte[2048];
                        int i1 = 0;
                        StringBuilder stringBuilder1 = new StringBuilder();
                        while ((i1 = inputStream1.read(val_rspns1)) != -1) {
                            stringBuilder1.append(new String(val_rspns1, 0, i1));
                        }
                        server_responce = httpUrlConnection.getResponseMessage();
                        statusCode = httpUrlConnection.getResponseCode();
                        jsonResponse = stringBuilder1.toString();
                        inputStream1.close();
                        break;
                    case 500:
                        InputStream inputStream2 = httpUrlConnection.getErrorStream();
                        byte[] val_rspns2 = new byte[2048];
                        int i2 = 0;
                        StringBuilder stringBuilder2 = new StringBuilder();
                        while ((i2 = inputStream2.read(val_rspns2)) != -1) {
                            stringBuilder2.append(new String(val_rspns2, 0, i2));
                        }
                        server_responce = httpUrlConnection.getResponseMessage();
                        statusCode = httpUrlConnection.getResponseCode();
                        jsonResponse = stringBuilder2.toString();
                        inputStream2.close();
                        break;
                    default:
                        break;
                }

                session.setAttribute("server_responce", server_responce);
                session.setAttribute("statusCode", statusCode);
                session.setAttribute("jsonResponse", jsonResponse);

                System.out.println(server_responce);
                System.out.println(statusCode);
                System.out.println(jsonResponse);

                httpUrlConnection.disconnect();

                response.sendRedirect("response.jsp");
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
