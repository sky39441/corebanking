package nexbank.xyz.corebanking.biz;

import java.util.Map;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.data.IResultMessage;
import nexcore.framework.core.data.ResultMessage;
import nexcore.framework.core.exception.BizRuntimeException;

/**
 * 이체 이력.
 * <pre>
 * 이체이력 관리
 * </pre>
 * 
 * @author admin (Administrator)
 * @since 2018-09-17 21:01:28
 */
@BizUnit(value="이체 이력", type="DU")
public class DTB_CBS_XYZ_TRLOG_00 extends nexbank.fwk.base.DataUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DTB_CBS_XYZ_TRLOG_00(){
		super();
	}

	/**
	 * TB_CBS_XYZ_TRLOG SELECT.
	 * <pre>
	 * TB_CBS_XYZ_TRLOG SELECT
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : TX_IX [TX_IX] (string)
	 *	- field : TX_KIND [TX_KIND] (string)
	 *	- field : AMOUNT [AMOUNT] (bigDecimal)
	 *	- field : TX_DATETIME [TX_DATETIME] (string)
	 *	- field : ACC_NO1 [ACC_NO1] (string)
	 *	- field : ACC_NO2 [ACC_NO2] (string)
	 *	- field : DESC_1 [DESC_1] (string)
	 *	- field : DESC_2 [DESC_2] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : TX_IX [TX_IX] (string)
	 *	- field : TX_KIND [TX_KIND] (string)
	 *	- field : AMOUNT [AMOUNT] (bigDecimal)
	 *	- field : TX_DATETIME [TX_DATETIME] (string)
	 *	- field : ACC_NO1 [ACC_NO1] (string)
	 *	- field : ACC_NO2 [ACC_NO2] (string)
	 *	- field : DESC_1 [DESC_1] (string)
	 *	- field : DESC_2 [DESC_2] (string)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-17 21:01:30
	 */
	@BizMethod("TB_CBS_XYZ_TRLOG SELECT")
	public IDataSet s001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    IRecord record = dbSelectSingle("s001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    if (record != null){
	        responseData.putAll(record);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * TB_CBS_XYZ_TRLOG SELECT ALL.
	 * <pre>
	 * TB_CBS_XYZ_TRLOG SELECT ALL
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- recordSet : LIST [TB_CBS_XYZ_TRLOG LIST ALL]
	 *		- field : TX_IX [TX_IX] (string)
	 *		- field : TX_KIND [TX_KIND] (string)
	 *		- field : AMOUNT [AMOUNT] (bigDecimal)
	 *		- field : TX_DATETIME [TX_DATETIME] (string)
	 *		- field : ACC_NO1 [ACC_NO1] (string)
	 *		- field : ACC_NO2 [ACC_NO2] (string)
	 *		- field : DESC_1 [DESC_1] (string)
	 *		- field : DESC_2 [DESC_2] (string)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-17 21:01:30
	 */
	@BizMethod("TB_CBS_XYZ_TRLOG SELECT ALL")
	public IDataSet s002(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    IRecordSet recordset = dbSelect("s002", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    if (recordset.getRecordCount() > 0){
	        responseData.put("LIST", recordset);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * TB_CBS_XYZ_TRLOG SELECT FOR UPDATE.
	 * <pre>
	 * TB_CBS_XYZ_TRLOG SELECT FOR UPDATE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : TX_IX [TX_IX] (string)
	 *	- field : TX_KIND [TX_KIND] (string)
	 *	- field : AMOUNT [AMOUNT] (bigDecimal)
	 *	- field : TX_DATETIME [TX_DATETIME] (string)
	 *	- field : ACC_NO1 [ACC_NO1] (string)
	 *	- field : ACC_NO2 [ACC_NO2] (string)
	 *	- field : DESC_1 [DESC_1] (string)
	 *	- field : DESC_2 [DESC_2] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : TX_IX [TX_IX] (string)
	 *	- field : TX_KIND [TX_KIND] (string)
	 *	- field : AMOUNT [AMOUNT] (bigDecimal)
	 *	- field : TX_DATETIME [TX_DATETIME] (string)
	 *	- field : ACC_NO1 [ACC_NO1] (string)
	 *	- field : ACC_NO2 [ACC_NO2] (string)
	 *	- field : DESC_1 [DESC_1] (string)
	 *	- field : DESC_2 [DESC_2] (string)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-17 21:01:30
	 */
	@BizMethod("TB_CBS_XYZ_TRLOG SELECT FOR UPDATE")
	public IDataSet s003(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    IRecord record = dbSelectSingle("s003", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    if (record != null){
	        responseData.putAll(record);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * TB_CBS_XYZ_TRLOG INSERT.
	 * <pre>
	 * TB_CBS_XYZ_TRLOG INSERT
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : TX_IX [TX_IX] (string)
	 *	- field : TX_KIND [TX_KIND] (string)
	 *	- field : AMOUNT [AMOUNT] (bigDecimal)
	 *	- field : TX_DATETIME [TX_DATETIME] (string)
	 *	- field : ACC_NO1 [ACC_NO1] (string)
	 *	- field : ACC_NO2 [ACC_NO2] (string)
	 *	- field : DESC_1 [DESC_1] (string)
	 *	- field : DESC_2 [DESC_2] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-17 21:01:30
	 */
	@BizMethod("TB_CBS_XYZ_TRLOG INSERT")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();

	    int row = dbInsert("i001", requestData, "NOTX", onlineCtx);
	    

	    
	    responseData.put("EXPECTED_ROW", row);
	    
	    return responseData;
	}

	/**
	 * TB_CBS_XYZ_TRLOG UPDATE.
	 * <pre>
	 * TB_CBS_XYZ_TRLOG UPDATE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : TX_IX [TX_IX] (string)
	 *	- field : TX_KIND [TX_KIND] (string)
	 *	- field : AMOUNT [AMOUNT] (bigDecimal)
	 *	- field : TX_DATETIME [TX_DATETIME] (string)
	 *	- field : ACC_NO1 [ACC_NO1] (string)
	 *	- field : ACC_NO2 [ACC_NO2] (string)
	 *	- field : DESC_1 [DESC_1] (string)
	 *	- field : DESC_2 [DESC_2] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-17 21:01:30
	 */
	@BizMethod("TB_CBS_XYZ_TRLOG UPDATE")
	public IDataSet u001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbUpdate("u001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("EXPECTED_ROW", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * TB_CBS_XYZ_TRLOG DELETE.
	 * <pre>
	 * TB_CBS_XYZ_TRLOG DELETE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-17 21:01:30
	 */
	@BizMethod("TB_CBS_XYZ_TRLOG DELETE")
	public IDataSet d001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbDelete("d001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("EXPECTED_ROW", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}
  
}