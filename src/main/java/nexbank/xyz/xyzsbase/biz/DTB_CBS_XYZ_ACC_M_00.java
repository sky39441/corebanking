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

/**
 * 샘플 - 계좌 원장.
 * <pre>
 * 샘플 - 계좌 원장
 * </pre>
 * 
 * @author admin (Administrator)
 * @since 2021-03-16 14:09:32
 */
@BizUnit(value = "샘플 - 계좌 원장", type = "DU")
public class DTB_CBS_XYZ_ACC_M_00 extends nexcore.framework.biz.online.DataUnit {

	/**
	 * 샘플 - 계좌 원장 SELECT.
	 * <pre>
	 * 샘플 - 계좌 원장 SELECT
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 *	- field : ACC_NAME [계좌명] (string)
	 *	- field : ACC_PASSWD [계좌비밀번호] (string)
	 *	- field : ACC_BALANCE [계좌잔액] (bigDecimal)
	 *	- field : CRE_DTM [계좌생성일시] (string)
	 *	- field : UPD_DTM [계좌수정일시] (string)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-03-16 14:09:32
	 */
	@BizMethod("샘플 - 계좌 원장 SELECT")
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
	 * 샘플 - 계좌 원장 SELECT ALL.
	 * <pre>
	 * 샘플 - 계좌 원장 SELECT ALL
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- recordSet : LIST [샘플 - 계좌 원장 LIST ALL]
	 *		- field : ACC_NO [계좌번호] (string)
	 *		- field : ACC_NAME [계좌명] (string)
	 *		- field : ACC_PASSWD [계좌비밀번호] (string)
	 *		- field : ACC_BALANCE [계좌잔액] (bigDecimal)
	 *		- field : CRE_DTM [계좌생성일시] (string)
	 *		- field : UPD_DTM [계좌수정일시] (string)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-03-16 14:09:32
	 */
	@BizMethod("샘플 - 계좌 원장 SELECT ALL")
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
	 * 샘플 - 계좌 원장 INSERT.
	 * <pre>
	 * 샘플 - 계좌 원장 INSERT
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 *	- field : ACC_NAME [계좌명] (string)
	 *	- field : ACC_PASSWD [계좌비밀번호] (string)
	 *	- field : ACC_BALANCE [계좌잔액] (bigDecimal)
	 *	- field : CRE_DTM [계좌생성일시] (string)
	 *	- field : UPD_DTM [계좌수정일시] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-03-16 14:09:32
	 */
	@BizMethod("샘플 - 계좌 원장 INSERT")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    
	    // Auto-generated execute db block
	    int row = dbInsert("i001", requestData, onlineCtx);
	    
	    // Auto-generated make response block
	    responseData.put("EXPECTED_ROW", row);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	
	    return responseData;
	}

	/**
	 * 샘플 - 계좌 원장 UPDATE.
	 * <pre>
	 * 샘플 - 계좌 원장 UPDATE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 *	- field : ACC_NAME [계좌명] (string)
	 *	- field : ACC_PASSWD [계좌비밀번호] (string)
	 *	- field : ACC_BALANCE [계좌잔액] (bigDecimal)
	 *	- field : CRE_DTM [계좌생성일시] (string)
	 *	- field : UPD_DTM [계좌수정일시] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-03-16 14:09:32
	 */
	@BizMethod("샘플 - 계좌 원장 UPDATE")
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
	 * 샘플 - 계좌 원장 DELETE.
	 * <pre>
	 * 샘플 - 계좌 원장 DELETE
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [EXPECTED_ROW] (int)
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-03-16 14:09:32
	 */
	@BizMethod("샘플 - 계좌 원장 DELETE")
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