package nexbank;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import nexcore.framework.core.util.JDOMUtil;

import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


public class AbsTestCase extends TestCase {
	
	protected String httpUrlPrefix = "http://127.0.0.1:8088/web";
	
	protected byte[] call(String urlStr, byte[] requestData){
		return call(urlStr, requestData, "Firefox", "application/octet-stream");
	}
	
	protected byte[] call(String urlStr, byte[] requestData, String userAgent, String contentType){
		URL url = null;
		HttpURLConnection conn = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		ByteArrayOutputStream baos = null;
		try {
			url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setConnectTimeout(10 * 1000);
			conn.setReadTimeout(30 * 1000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", userAgent);
			conn.setRequestProperty("Content-type", contentType);
			
			bos = new BufferedOutputStream(conn.getOutputStream());
			bos.write(requestData);
			bos.flush();
	
			int responseCode = conn.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new IOException("ResponseCode is not OK. responseCode=[" + responseCode + "].\\n" + conn.getResponseMessage());
			}
			
			bis = new BufferedInputStream(conn.getInputStream());
			baos = new ByteArrayOutputStream();
			byte[] buf = new byte[512];
			int len = -1;
			while ((len = bis.read(buf)) != -1) {
				baos.write(buf, 0, len);
			}
			baos.flush();
			return baos.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally{
			try{
				if(bos != null){
					bos.close();
				}
				if(bis != null){
					bis.close();
				}
				if(conn != null){
					conn.disconnect();
				}
			}
			catch(Exception ignore){
			}
		}
	}
	
	protected byte[] readData(String filename){
		URL url = this.getClass().getResource(filename);
		if(url == null){
			url = ClassLoader.getSystemResource(filename);
		}
		if(url == null){
			throw new RuntimeException("Can not find resource. " + filename);
		}
		
		InputStream is = null;
		try{
			is = url.openStream();
			return IOUtils.toByteArray(is);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	private Connection dbconn = null;
	
	protected Connection getDbConnection() throws Exception {
		if(dbconn == null){
			String dirverName = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			String user = "demo_fwk";
			String password = "demo_fwk";
			
			Class.forName(dirverName);
			dbconn =  DriverManager.getConnection(url, user, password);
		}
		return dbconn;
	}
	
	protected List<Map> executeQuery(String sql) throws Exception {
		java.sql.Connection conn = getDbConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try{
			List<Map> list = new ArrayList<Map>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<String> columns = new ArrayList<String>();
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i=1; i<=rsmd.getColumnCount(); i++){
				columns.add(rsmd.getColumnName(i));
			}
			while(rs.next()){
				Map map = new LinkedHashMap();
				for(String column : columns){
					map.put(column, rs.getString(column));
				}
				list.add(map);
			}
			return list;
		}
		finally{
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
		}
	}
	
	public void tearDown(){
		if(dbconn != null){
			try {
				dbconn.close();
			} catch (SQLException e) {
			}
		}
	}

	protected String jDOMDocument2String(Document document, String encoding) {
		Format format = Format.getCompactFormat();
        format.setLineSeparator("\n");
        format.setTextMode(Format.TextMode.PRESERVE);
        format.setEncoding("UTF-8");
        XMLOutputter out = new XMLOutputter(format);
        return out.outputString(document);
	}

	protected Document getJDOMDocument(String requestData) throws JDOMException, IOException {
		return JDOMUtil.buildDocument(requestData);
	}
	
}
