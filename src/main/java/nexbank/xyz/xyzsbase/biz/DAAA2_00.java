package nexbank.xyz.xyzsbase.biz;

import java.util.Map;

import nexcore.framework.core.component.streotype.BizAttribute;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.log.ILog;

/**
 * DAAA2_00.
 * <pre>
 * DAAA2_00
 * </pre>
 * 
 * @author admin (Administrator)
 * @since 2021-04-26 15:29:46
 */
@BizUnit(value = "DAAA2_00", type = "DU")
public class DAAA2_00 extends nexbank.fwk.base.DataUnit {

	/**
	 * AAA2 SELECT.
	 * <pre>
	 * AAA2 SELECT
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : A [A] (string)
	 *	- field : B [B] (string)
	 *	- field : C [C] (string)
	 *	- field : MM [MM] (bigDecimal)
	 *	- field : LAST_TIME [LAST_TIME] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : A [A] (string)
	 *	- field : B [B] (string)
	 *	- field : C [C] (string)
	 *	- field : MM [MM] (bigDecimal)
	 *	- field : LAST_TIME [LAST_TIME] (string)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-26 15:29:46
	 */
	@BizMethod("AAA2 SELECT")
	public IDataSet s001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    IRecord record = dbSelectSingle("S001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    if (record != null){
	        responseData.putAll(record);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * AAA2 SELECT ALL.
	 * <pre>
	 * AAA2 SELECT ALL
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- recordSet : LIST [AAA2 LIST ALL]
	 *		- field : A [A] (string)
	 *		- field : B [B] (string)
	 *		- field : C [C] (string)
	 *		- field : MM [MM] (bigDecimal)
	 *		- field : LAST_TIME [LAST_TIME] (string)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-26 15:29:46
	 */
	@BizMethod("AAA2 SELECT ALL")
	public IDataSet s002(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    IRecordSet recordset = dbSelect("S002", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("LIST", recordset);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * AAA2 INSERT.
	 * <pre>
	 * AAA2 INSERT
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : A [A] (string)
	 *	- field : B [B] (string)
	 *	- field : C [C] (string)
	 *	- field : MM [MM] (bigDecimal)
	 *	- field : LAST_TIME [LAST_TIME] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-26 15:29:46
	 */
	@BizMethod("AAA2 INSERT")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbInsert("I001", requestData, onlineCtx);
	    
	    requestData.put("B",  requestData.getString("B") + "-NOTX");
	    requestData.put("MM", requestData.getInt("MM")   + 9);
	   
//	    int row2 = dbInsert("I001", requestData, "NOTX", onlineCtx);
	    int row2 = dbInsert("I001", requestData, "TX2", onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("EXPECTED_ROW", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * AAA2 UPDATE.
	 * <pre>
	 * AAA2 UPDATE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : A [A] (string)
	 *	- field : B [B] (string)
	 *	- field : C [C] (string)
	 *	- field : MM [MM] (bigDecimal)
	 *	- field : LAST_TIME [LAST_TIME] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-26 15:29:46
	 */
	@BizMethod("AAA2 UPDATE")
	public IDataSet u001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbUpdate("U001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("EXPECTED_ROW", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * AAA2 DELETE.
	 * <pre>
	 * AAA2 DELETE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-26 15:29:46
	 */
	@BizMethod("AAA2 DELETE")
	public IDataSet d001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbDelete("D001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("EXPECTED_ROW", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

}