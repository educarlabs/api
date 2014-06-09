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

	
	// Noticias recientes		
	public static void main (String[] args) throws Exception {

		String request = "https://api.educ.ar/0.9/noticias/recientes/";

		request = NoticiasRecientes.addParameter(request,"key","xxxxxxxxxxxxxxxxxxxxxxxx");

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
