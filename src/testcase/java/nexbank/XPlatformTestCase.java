package nexbank;

import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;

public class XPlatformTestCase extends AbsTestCase {

	/**
	 * 정상
	 * @throws Exception 
	 */
	public void testNormal() throws Exception{
		byte[] requestData = getRequestData("FWK02010", false);
		byte[] responseData = call(httpUrlPrefix + "/FWK/FWK.xpl", requestData, "MiPlatform 3.2;win32;1680x1050", "text/xml;charset=euc-kr");

		String responseDataStr = new String(responseData); 
		int rootstart = responseDataStr.indexOf("<Root ");
		int rootend = responseDataStr.indexOf(">", rootstart+1);
		responseDataStr = responseDataStr.substring(0, rootstart) + "<Root>"+ responseDataStr.substring(rootend + 1, responseDataStr.length());
		
		Document responseDom = getJDOMDocument(responseDataStr);
		List responseParams = responseDom.getRootElement().getChild("Parameters").getChildren("Parameter");
		for(int i=0; i<responseParams.size(); i++){
			Element param = (Element)responseParams.get(i);
			if("ncStartDate".equals(param.getAttributeValue("id")) || "ncEndDate".equals(param.getAttributeValue("id"))){
				param.setText("");
			}
		}
		responseDataStr = jDOMDocument2String(responseDom, "UTF-8");

//		FileWriter fw = new FileWriter("C:/projects/framework_demo/workspace/dev-demo/src/testcase/nexbank/fwk/fwkbtest/biz/PFWK02010XPlatformTestCase_response.txt");
//		fw.write(responseDataStr);
//		fw.flush();
//		fw.close();
		
		String responseDataDefaultStr = new String(readData("xplatform_response.xml"));
		assertEquals("응답데이터", responseDataStr, responseDataDefaultStr);
	}
	
	/**
	 * 요청데이타 오류
	 */
	public void testInvalid() throws Exception {
		byte[] requestData = getRequestData(null, false);
		byte[] responseData = call(httpUrlPrefix + "/FWK/FWK.xpl", requestData, "MiPlatform 3.2;win32;1680x1050", "text/xml;charset=euc-kr");

		String result = null;
		String responseDataStr = new String(responseData); 
		int rootstart = responseDataStr.indexOf("<Root ");
		int rootend = responseDataStr.indexOf(">", rootstart+1);
		responseDataStr = responseDataStr.substring(0, rootstart) + "<Root>"+ responseDataStr.substring(rootend + 1, responseDataStr.length());
		Document responseDom = getJDOMDocument(responseDataStr);
		List responseParams = responseDom.getRootElement().getChild("Parameters").getChildren("Parameter");
		for(int i=0; i<responseParams.size(); i++){
			Element param = (Element)responseParams.get(i);
			if("ncMsgFlag".equals(param.getAttributeValue("id"))){
				result = param.getText();
			}
		}

		assertEquals("처리상태", "ERROR", result);
	}
	
	/**
	 * 업무로직 오류
	 */
	public void testError() throws Exception {
		byte[] requestData = getRequestData("FWK02010", true);
		byte[] responseData = call(httpUrlPrefix + "/FWK/FWK.xpl", requestData, "MiPlatform 3.2;win32;1680x1050", "text/xml;charset=euc-kr");

		String result = null;
		String messageId = null;
		String responseDataStr = new String(responseData);
//		System.out.println(responseDataStr);
		int rootstart = responseDataStr.indexOf("<Root ");
		int rootend = responseDataStr.indexOf(">", rootstart+1);
		responseDataStr = responseDataStr.substring(0, rootstart) + "<Root>"+ responseDataStr.substring(rootend + 1, responseDataStr.length());
		Document responseDom = getJDOMDocument(responseDataStr);
		List responseParams = responseDom.getRootElement().getChild("Parameters").getChildren("Parameter");
		for(int i=0; i<responseParams.size(); i++){
			Element param = (Element)responseParams.get(i);
			if("ncMsgFlag".equals(param.getAttributeValue("id"))){
				result = param.getText();
			}
			else if("ncMsgId".equals(param.getAttributeValue("id"))){
				messageId = param.getText();
			}
		}

		assertEquals("처리상태", "ERROR", result);
		assertEquals("처리메시지아이디", "업무로직 에러가 발생하였습니다.", messageId);
	}
		
	private byte[] getRequestData(String txId, boolean throwBizRuntimeException) throws Exception{
		byte[] requestData = readData("xplatform_request.xml");
		Document requestDom = getJDOMDocument(new String(requestData));
		
		List params = requestDom.getRootElement().getChild("params").getChildren("param");
		for(int i=0; i<params.size(); i++){
			Element param = (Element)params.get(i);
			if("ncTrId".equals(param.getAttributeValue("id"))){
				param.setText(txId);
			}
		}
		List datasets = requestDom.getRootElement().getChildren("dataset");
		for(int i=0; i<datasets.size(); i++){
			Element dataset = (Element)datasets.get(i);
			if("ncFieldMap".equals(dataset.getAttributeValue("id"))){
				dataset.getChild("record").getChild("throwBizRuntimeException").setText(String.valueOf(throwBizRuntimeException));
			}
		}
		
		return jDOMDocument2String(requestDom, "UTF-8").getBytes();
	}

}