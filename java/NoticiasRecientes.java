/**********************************************************************************
 * 
 * API Publica educ.ar
 *
 * Ejemplo de llamado a endpoint: Noticias Recientes
 *
 * Dependecias:
 * - commons-httpclient-3.0
 * - commons-logging-1.1.3
 * - commons-codec-1.6
 **********************************************************************************/

import java.io.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import java.net.URLEncoder;

public class NoticiasRecientes {

	public static void main (String[] args) throws Exception {

		String request = "https://api.educ.ar/0.9/noticias/recientes/";

		request = NoticiasRecientes.addParameter(request,"key","xxxxxxxxxxxxxxxxxxxxxxxx");
	
		/*
		IMPORTANTE:
	
		Se debe instalar el certificado en el keystore de java
		@see: http://wiki.cacert.org/FAQ/ImportRootCert#Java

		Certificate fingerprint (SHA1): 5C:15:98:59:60:75:81:F7:7D:90:00:B5:19:25:C7:53:18:6D:37:EA

		# -----------------------------------------------------------------------------
		# Linux: Debian/Ubuntu

		# Agregar cert (previamente exportado desde browser)

		$ JAVA_HOME=$(readlink -f /usr/bin/java | sed "s:bin/java::")
		$ sudo keytool -import -v -trustcacerts -alias api_educar \
		  -file ../php/api_server.crt -keystore "$JAVA_HOME/lib/security/cacerts" \
		  -keypass changeit -storepass changeit

		# Listar certs instalados 

		$ keytool -keystore "$JAVA_HOME\jre\lib\security\cacerts" -storepass changeit -list


		# Borrar cert instalado
		$ sudo keytool -delete -alias api_educar -keystore "$JAVA_HOME/lib/security/cacerts" -storepass changeit


		# -----------------------------------------------------------------------------
		# Windows: 

		Ir a  "Java Control Panel", "Secure" tab y clickear en "Certificates". Luego tab "System" y seleccionar "Secure CA" o "Secure 			Sites CA" del drop down de opciones.		
		*/
		
		HttpClient client = new HttpClient();				
	        GetMethod method = new GetMethod(request);

	        int statusCode = client.executeMethod(method);	

	        InputStream rstream = null;

	        rstream = method.getResponseBodyAsStream();

		BufferedReader br = new BufferedReader(new InputStreamReader(rstream));

		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	
	}

	public static String addParameter(String URL, String name, String value)  {
		int qpos = URL.indexOf('?');
		int hpos = URL.indexOf('#');
		char sep = qpos == -1 ? '?' : '&';
		String seg = sep + encodeUrl(name) + '=' + encodeUrl(value);
		return hpos == -1 ? URL + seg : URL.substring(0, hpos) + seg
		+ URL.substring(hpos);
	}

	public static String encodeUrl(String url) {
		try  {
			return URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalArgumentException(uee);
		}
	}
}
