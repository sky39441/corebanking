package nexbank.xyz.corebanking.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.util.DateUtils;


/**
 * [DU]샘플계좌원장.
 * <pre>
 * 
 * </pre>
 * 
 * @author 차지환 (jihwancha)
 * @since 2016-08-09 17:28:20
 */
@BizUnit(value="[DU]샘플계좌원장", type="DU")
public class DTB_CBS_XYZ_ACC_M_00 extends nexbank.fwk.base.DataUnit  {

	/**
	 * 계좌목록조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO_LIKE () [검색대상 계좌번호]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:20
	 */
	@BizMethod("계좌목록조회")
	public IDataSet s001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // 조회 쿼리 실행.
	    IRecordSet rs = dbSelect("S001", requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("LIST", rs);
	    return responseData;
	}
	
   /**
	 * 계좌상세조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO () [ACC_NO]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO () [ACC_NO]
	 *	- field : ACC_NAME () [ACC_NAME]
	 *	- field : ACC_PASSWD () [ACC_PASSWD]
	 *	- field : ACC_BALANCE () [ACC_BALANCE]
	 *	- field : CRE_DTM () [CRE_DTM]
	 *	- field : UPD_DTM () [UPD_DTM]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:20
	 */
	@BizMethod("계좌상세조회")
	public IDataSet s002(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // 조회 쿼리 실행.
	    IRecord record = dbSelectSingle("S002", requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    if(record != null){
	    	responseData.putAll(record.toMap());
	    }
	    
	    return responseData;
	}

	/**
	 * 입력.
	 * <pre>
	 * 입력
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [ACC_NO]
	 *	- field : ACC_NAME [ACC_NAME]
	 *	- field : ACC_PASSWD [ACC_PASSWD]
	 *	- field : ACC_BALANCE [ACC_BALANCE]
	 *	- field : CRE_DTM [CRE_DTM]
	 *	- field : UPD_DTM [UPD_DTM]
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [반영건수]
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2020-12-22 01:46:57
	 */
	@BizMethod("입력")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		
		// 등록 쿼리 수행
		String yyyyMMddHHmmssSSS = DateUtils.getDateString("yyyyMMddHHmmssSSS");
		requestData.put("CRE_DTM", yyyyMMddHHmmssSSS);
		requestData.put("UPD_DTM", yyyyMMddHHmmssSSS);
		int row = dbInsert("I001", requestData, onlineCtx);
		
		// 처리 결과값을 responseData에 넣어서 리턴하십시요 
		responseData.put("EXPECTED_ROW", row);
		
		return responseData;
	    
	}

	/**
	 * 입력.
	 * <pre>
	 * 입력
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [ACC_NO]
	 *	- field : ACC_NAME [ACC_NAME]
	 *	- field : ACC_PASSWD [ACC_PASSWD]
	 *	- field : ACC_BALANCE [ACC_BALANCE]
	 *	- field : CRE_DTM [CRE_DTM]
	 *	- field : UPD_DTM [UPD_DTM]
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [반영건수]
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2020-12-22 01:46:57
	 */
	@BizMethod("입력")
	public IDataSet u001(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		
		// 등록 쿼리 수행
		String yyyyMMddHHmmssSSS = DateUtils.getDateString("yyyyMMddHHmmssSSS");
		requestData.put("CRE_DTM", yyyyMMddHHmmssSSS);
		requestData.put("UPD_DTM", yyyyMMddHHmmssSSS);
		int row = dbInsert("U001", requestData, onlineCtx);
		
		// 처리 결과값을 responseData에 넣어서 리턴하십시요 
		responseData.put("EXPECTED_ROW", row);
		
		return responseData;
	    
	}
	
//	/**
//	 * <p>계좌등록.</p>
//	 *
//	 * @author 차지환 (jihwancha)
//	 *
//	 * @param requestData 요청정보 DataSet 객체
//	 * <pre>
//	 *	- field : ACC_NO [ACC_NO] 
//	 *	- field : ACC_NAME [ACC_NAME] 
//	 *	- field : ACC_PASSWD [ACC_PASSWD] 
//	 *	- field : ACC_BALANCE [ACC_BALANCE] 
//	 *	- field : CRE_DTM [CRE_DTM] 
//	 *	- field : UPD_DTM [UPD_DTM] 
//	 * </pre>
//	 * @param onlineCtx   Request 정보
//	 * @return 처리결과 DataSet 객체
//	 * <pre>
//	 *	- field : EXPECTED_ROW [반영건수] 
//	 * </pre>
//	 */
//	@BizMethod()
//	@BizAlias("계좌등록")
//	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx){
//		 IDataSet responseData = new DataSet();
//		    
//	    // 등록 쿼리 수행
//	    String yyyyMMddHHmmssSSS = DateUtils.getCurrentDate("yyyyMMddHHmmssSSS");
//	    requestData.put("CRE_DTM", yyyyMMddHHmmssSSS);
//	    requestData.put("UPD_DTM", yyyyMMddHHmmssSSS);
//	    int row = dbInsert("I001", requestData, onlineCtx);
//	
//	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.put("EXPECTED_ROW", row);
//	
//	    return responseData;
//	}
//
//	/**
//	 * <p>계좌잔액수정.</p>
//	 *
//	 * @author 차지환 (jihwancha)
//	 *
//	 * @param requestData 요청정보 DataSet 객체
//	 * <pre>
//	 *	- field : ACC_NO [계좌번호] 
//	 *	- field : AMOUNT [금액] 
//	 * </pre>
//	 * @param onlineCtx   Request 정보
//	 * @return 처리결과 DataSet 객체
//	 * <pre>
//	 *	- field : EXPECTED_ROW [반영건수] 
//	 * </pre>
//	 */
//	@BizMethod()
//	@BizAlias("계좌잔액수정")
//	public IDataSet u001(IDataSet requestData, IOnlineContext onlineCtx){
//		IDataSet responseData = new DataSet();
//		
//		IDataSet param = (IDataSet)requestData.clone();
//		
//	    String yyyyMMddHHmmssSSS = DateUtils.getCurrentDate("yyyyMMddHHmmssSSS");
//	    param.put("CRE_DTM", yyyyMMddHHmmssSSS);
//	    param.put("UPD_DTM", yyyyMMddHHmmssSSS);
//
//	    boolean withdraw = "O".equals(param.getString("KIND")); //출금여부
//		long amount = param.getLong("AMOUNT");
//		
//		// 출금
//		if(withdraw){
//			param.put("AMOUNT", -amount);
//		}
//		 
//	    // 등록 쿼리 수행
//	    int row = dbInsert("U001", param, onlineCtx);
//	
//	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.put("EXPECTED_ROW", row);
//	
//	    return responseData;
//	}

}
