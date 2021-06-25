package nexbank.xyz.xyzbbase.biz;

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
 * DAAA_00.
 * <pre>
 * DAAA_00
 * </pre>
 * 
 * @author admin (Administrator)
 * @since 2021-04-08 10:26:25
 */
@BizUnit(value = "DAAA_00", type = "DU")
public class DAAA_00 extends nexbank.fwk.base.DataUnit {

	/**
	 * AAA SELECT.
	 * <pre>
	 * AAA SELECT
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : A [A] (string)
	 *	- field : B [B] (string)
	 *	- field : C [C] (string)
	 *	- field : MM [MM] (bigDecimal)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : A [A] (string)
	 *	- field : B [B] (string)
	 *	- field : C [C] (string)
	 *	- field : MM [MM] (bigDecimal)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-08 10:26:25
	 */
	@BizMethod("AAA SELECT")
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
	 * AAA SELECT ALL.
	 * <pre>
	 * AAA SELECT ALL
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- recordSet : LIST [AAA LIST ALL]
	 *		- field : A [A] (string)
	 *		- field : B [B] (string)
	 *		- field : C [C] (string)
	 *		- field : MM [MM] (bigDecimal)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-08 10:26:25
	 */
	@BizMethod("AAA SELECT ALL")
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
	 * AAA INSERT.
	 * <pre>
	 * AAA INSERT
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : A [A] (string)
	 *	- field : B [B] (string)
	 *	- field : C [C] (string)
	 *	- field : MM [MM] (bigDecimal)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-08 10:26:25
	 */
	@BizMethod("AAA INSERT")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbInsert("I001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("EXPECTED_ROW", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * AAA UPDATE.
	 * <pre>
	 * AAA UPDATE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : A [A] (string)
	 *	- field : B [B] (string)
	 *	- field : C [C] (string)
	 *	- field : MM [MM] (bigDecimal)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-08 10:26:25
	 */
	@BizMethod("AAA UPDATE")
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
	 * AAA DELETE.
	 * <pre>
	 * AAA DELETE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-08 10:26:25
	 */
	@BizMethod("AAA DELETE")
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