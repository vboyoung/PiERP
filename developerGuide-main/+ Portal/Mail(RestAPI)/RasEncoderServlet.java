package com.ras;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.kebi.crypto.RSACipher;

//@WebServlet("/RasEncoderServlet")
public class RasEncoderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RasEncoderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cn = request.getParameter("cn");
		if ("publickeyEncrypt".equals(cn)) {
			try {
				String publicKey = request.getParameter("publickey");
				String mailId = request.getParameter("mailId");
				cn = "username=" + mailId;
				String action = request.getParameter("action");
				cn += "&action=" + action;

				if(request.getParameter("type") != null && request.getParameter("type").length() > 0){
					String type = request.getParameter("type");
					cn += "&type=" + type;
				}
				if(request.getParameter("password") != null && request.getParameter("password").length() > 0){
					String password = request.getParameter("password");
					cn += "&password=" + URLEncoder.encode(password, "UTF-8");
				}
				
				RSACipher rsa2 = new RSACipher();
				// encodedPublicKey 나라비전에서 주는 걸로 선언해주시면됩니다.
				String hexString = rsa2.encryptToHexString(cn, publicKey);
				// hexString 값을 내려주면됨
				System.out.println("hex: " + hexString);
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put("hexString", hexString);
				PrintWriter out = null;
				JSONObject jsonobj = new JSONObject();
				try {
					jsonobj.put("hexString", hexString);
					String msg = jsonobj.toString();
					response.setContentType("text/plain;charset=UTF-8");
					out = response.getWriter();
					out.println("callback(" + msg + ")");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					out.close();
				}
			} catch (GeneralSecurityException e) {
				System.out.println("Error at SSOAndroidAuthenticationFilter.RSACipher()" + e);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
	}

}
