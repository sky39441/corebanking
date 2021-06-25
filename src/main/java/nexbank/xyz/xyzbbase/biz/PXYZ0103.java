package nexbank.xyz.xyzbbase.biz;

import org.apache.commons.logging.Log;

import nexbank.common.CommonArea;
import nexbank.common.OutboundHeader;
import nexbank.fwk.outbound.OutboundTarget;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.OutboundTimeoutCallback;
import nexcore.framework.core.data.TransformType;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.ByteArrayWrap;
import nexcore.framework.core.util.DateUtils;



/**
 * [PU]샘플계좌이체.
 * <pre>
 * 
 * </pre>
 * 
 * @author 차지환 (jihwancha)
 * @since 2016-08-09 17:19:36
 */
@BizUnit(value="[PU]샘플계좌이체", type="PU")
public class PXYZ0103 extends nexbank.fwk.base.ProcessUnit {

	@BizUnitBind
	private FXYZ0100 fXYZ0100;
	
	@BizUnitBind
	private DATB_CBS_XYZ_POC_00 dATB_CBS_XYZ_POC_00;

	@BizUnitBind
	private DTB_CBS_XYZ_TRLOG_00 dTB_CBS_XYZ_TRLOG_00;

	/**
	 * 당행 이체.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- dataSet : WITHDRAW [출금정보]
	 *		- field : ACC_NO [출금계좌번호] (string)
	 *		- field : ACC_PASSWD [출금계좌비밀번호] (string)
	 *		- field : DESCRIPTION [출금계좌메시지] (string)
	 *	- dataSet : DEPOSIT [입금정보]
	 *		- field : ACC_NO [입금계좌번호] (string)
	 *		- field : DESCRIPTION [입금계좌메시지] (string)
	 *	- field : AMOUNT [금액] (long)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:19:36
	 */
	@BizMethod("당행 이체")
	public IDataSet pXYZ01301(IDataSet requestData, IOnlineContext onlineCtx){
		Log        log          = getLog(onlineCtx);
	    IDataSet   responseData = new DataSet();
	    CommonArea ca           = getCommonArea(onlineCtx);

	    // CommonArea 고객번호 설정
	    ca.setCustNo("11111111");
	    if(log.isInfoEnabled()){
	    	log.info("CommonArea 고객번호 설정 : " + ca.getCustNo());
	    }
	    
	    
	    // 이체 금액
	    long amount = requestData.getLong("AMOUNT");
	    
	    // 출금계좌 입금 금액 설정
	    IDataSet withdrawDataSet = requestData.getDataSet("WITHDRAW");
	    withdrawDataSet.put("AMOUNT", amount);
	    
	    // 입금계좌 금액 설정
	    IDataSet depositDataSet = requestData.getDataSet("DEPOSIT");
	    depositDataSet.put("AMOUNT", amount);

	    try{
		    
//	    	/**********************************************************************/
//	    	// ■■■■■■■■■■  NOTX 이체 이력 테이블 저장
//		    IDataSet i001Req = new DataSet();
//			i001Req.put("TX_IX",       ca.getTxId()); // TX_IX
//			i001Req.put("TX_KIND",     "당행이체"); // TX_KIND
//			i001Req.put("AMOUNT",      amount); // AMOUNT
//			i001Req.put("TX_DATETIME", DateUtils.getDateString  ("yyyyMMddHHmmss")); // TX_DATETIME
//			i001Req.put("ACC_NO1",     withdrawDataSet.getString("ACC_NO")); // ACC_NO1
//			i001Req.put("ACC_NO2",     depositDataSet.getString ("ACC_NO"));  // ACC_NO2
//			i001Req.put("DESC_1",      withdrawDataSet.getString("DESCRIPTION"));  // DESC_1
//			i001Req.put("DESC_2",      depositDataSet.getString ("DESCRIPTION"));   // DESC_2
//
//			// notx 로 이체 이력 생성. 에러 발생해도 ROLLBACK 되지 않도록 한다.
//			dTB_CBS_XYZ_TRLOG_00.i001(i001Req, onlineCtx);
//			/**********************************************************************/
//			
//					
//			
//			/**********************************************************************/
//		    // ■■■■■■■■■■   MariaDB 에 XA 로 저장
//			// 유닛 메소드 호출 - mariadb 에 등록 (DATB_CBS_XYZ_POC_00.i001)
//		    IDataSet i001Req2 = new DataSet();
//			i001Req2.put("DESC",   withdrawDataSet.get("DESCRIPTION")+"->"+depositDataSet.get("DESCRIPTION")); // 적요
//			i001Req2.put("AMOUNT", requestData.get("AMOUNT")); // 금액
//			i001Req2.put("GUID",   ca.getGlobId()); // GUID
//
//		    dATB_CBS_XYZ_POC_00.i001(i001Req2, onlineCtx);
//			/**********************************************************************/

		    
		    /**********************************************************************/
		    // ■■■■■■■■■■ 출금 서비스 호출
		    callService("XYZ01201", withdrawDataSet, onlineCtx);
		    /**********************************************************************/
		    
		    /**********************************************************************/
		    // ■■■■■■■■■■ 입금 서비스 호출
		    callService("XYZ01101", depositDataSet, onlineCtx);
		    /**********************************************************************/
		
		    return responseData;
	    }
	    catch(Exception e){
	    	throw new BizRuntimeException("ECOM0000", new String[]{"당행이체"}, e);
	    }
	}

	/**
	 * 타행 이체.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- dataSet : WITHDRAW [출금정보]
	 *		- field : ACC_NO [출금계좌번호] (string)
	 *		- field : ACC_PASSWD [출금계좌비밀번호] (string)
	 *		- field : DESCRIPTION [출금계좌메시지] (string)
	 *	- dataSet : DEPOSIT [입금정보]
	 *		- field : BANK_NO [입금은행번호] (string)
	 *		- field : ACC_NO [입금계좌번호] (string)
	 *		- field : DESCRIPTION [입금계좌메시지] (string)
	 *	- field : AMOUNT [금액] (long)
	 *	- field : TIMEOUT_YN [타임아웃발생여부] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:19:36
	 */
	@BizMethod("타행 이체")
	public IDataSet pXYZ01310(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    try{
		    // 이체 금액
		    long amount = requestData.getLong("AMOUNT");
		    
		    // 출금계좌 존재여부 및 비밀번호 체크
		    IDataSet withdrawDataSet = requestData.getDataSet("WITHDRAW");
		    withdrawDataSet.put("AMOUNT", amount);
		    withdrawDataSet.put("ACC_PASSWD_CHECK_YN", "Y");
		    
		    IDataSet fXYZ01CheckAccNoRes = fXYZ0100.checkAccNo(withdrawDataSet, onlineCtx);
		    
		    // 출금 계좌가 존재하지 않습니다.
		    if(!"Y".equals(fXYZ01CheckAccNoRes.getString("EXIST_ACC_YN"))){
		    	// 출금계좌가 존재하지 않습니다.
		    	throw new BizRuntimeException("XYZE0007");
		    }
		    if(!"Y".equals(fXYZ01CheckAccNoRes.getString("VALID_ACC_PASSWD_YN"))){
		    	// 비밀번호가 일치하지 않습니다.
		    	throw new BizRuntimeException("XYZE0008");
		    }
		    
		    // 출금 서비스 호출
		    callService("XYZ01201", withdrawDataSet, onlineCtx);
		    
		    IDataSet depositDataSet = requestData.getDataSet("DEPOSIT");
		    depositDataSet.put("WITHDRAW_ACC_NO", withdrawDataSet.get("ACC_NO"));
		    depositDataSet.put("AMOUNT", amount);
		    depositDataSet.put("TIMEOUT_YN", requestData.get("TIMEOUT_YN"));
		    
		    // 타행 입금 요청 호출
		    callService("XYZ01311", depositDataSet, onlineCtx);
		    
		    // 더미 리턴 설정
		    setDummyReturn(onlineCtx, true);
		    setDummyReturnReleaseOnFail(onlineCtx, true);
		    
		    return responseData;
	    }
	    catch(BizRuntimeException e){
	    	throw e;
	    }
	    catch(Exception e){
	    	throw new BizRuntimeException("ECOM0000", new String[]{"타행이제"}, e);
	    }
	}

	/**
	 * 입금요청.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:19:37
	 */
	@BizMethod("입금요청")
	public IDataSet pXYZ01311(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    Log log = getLog(onlineCtx);
	    
	    // Common Area
	    CommonArea ca = getCommonArea(onlineCtx);

	    requestData.put("_GUID", ca.getGlobId());
	    requestData.put("_IPAD", ca.getIpad());

	    
//	    // 타행 입금 항목
//	    IDataSet depositDataSet = new DataSet();
//	    depositDataSet.put("BANK_NO", requestData.getString("DEPOSIT_BANK_NO"));
//	    depositDataSet.put("ACC_NO", requestData.getString("DEPOSIT_ACC_NO"));
//	    depositDataSet.put("DESCRIPTION", requestData.getString("DEPOSIT_DESCRIPTION"));
//	    depositDataSet.put("AMOUNT", requestData.getLong("AMOUNT"));
	    
	    // 1. 전문레이아웃기준으로 전문 생성
	    ByteArrayWrap bytearray = 
	    	transformByteArrayForXio(TransformType.FLAT, "IO_XYZB01310", requestData, onlineCtx);
	    
	    log.info("데이타셋 >>>>>>>>> \n[" +requestData+"]");
	    log.info("전문     >>>>>>>>> \n[" +bytearray+"]");
	    
	    
	    // 타행 입금요청 타임아웃 거래 Timer 등록
	    // 타행 입금요청 타임아웃 거래는 타임아웃 발생시 원계좌에 입금처리를 수행한다.
//	    String delayAsyncKey = callDelayAsyncService("XYZ01313", 20, requestData, onlineCtx);
	    
	    OutboundHeader header = new OutboundHeader(onlineCtx);
//	    header.setSprChrsCntn(delayAsyncKey);
	    if("Y".equals(requestData.getString("TIMEOUT_YN"))){
	    	header.setOtsdRespTxId(null);
	    }
	    else {
	    	header.setOtsdRespTxId("XYZ01312");
	    }
	    
	    // 3. 전문 송신 (XA)
	    OutboundTimeoutCallback timeoutCallback = new OutboundTimeoutCallback();
	    timeoutCallback.setSeconds(20);
	    timeoutCallback.setTxId("XYZ01313");
	    timeoutCallback.setRequestData(requestData);
	    sendOutboundAfterCommit(OutboundTarget.FEP, header, bytearray, timeoutCallback, null, onlineCtx);
	   
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.	
	    return responseData;
	}

	/**
	 * 입금요청응답.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : INPUT_DATA [입력전문] (binary)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:19:37
	 */
	@BizMethod("입금요청응답")
	public IDataSet pXYZ01312(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    // Common Area
	    CommonArea ca = getCommonArea(onlineCtx);

	    // 지연비동기 아이디
//	    String delayAsyncKey = DELAY_ASYNC_KEY_MAP.remove(ca.getGlobId());
	    String delayAsyncKey = ca.getSprChrsCntn();
	    
	    // 타행 입금 요청 응답을 수신하였으므로, 타행 입금요청 타임아웃 거래 Timer를 삭제한다.
	    boolean removed = removeDelayAsyncService(delayAsyncKey, onlineCtx);
	    // 취소 성공
	    if(removed) {
	    	// 단말에 타행이체 성공 결과를 전송한다.
		    OutboundHeader header = new OutboundHeader(onlineCtx);
		    header.setResponse();
		    header.setTxId("XYZ01310");
		    //TODO 계좌이체가 성공하였습니다.
		    header.setOkResultMessage("NCOM0000", null);
		    sendOutboundAfterCommit(OutboundTarget.MCI, header, null, onlineCtx);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.	
	    return responseData;
	}

	/**
	 * 입금요청 타임아웃.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:19:37
	 */
	@BizMethod("입금요청 타임아웃")
	public IDataSet pXYZ01313(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    String orgGUID = requestData.getString("_GUID");
	    String orgIPAD = requestData.getString("_IPAD");
	    
	    // 입금 정보
	    IDataSet depositDataSet = new DataSet();
	    depositDataSet.put("ACC_NO", requestData.getString("WITHDRAW_ACC_NO"));
	    depositDataSet.put("DESCRIPTION", "타행이체 실패로 입금");
	    depositDataSet.put("AMOUNT", requestData.getLong("AMOUNT"));
	    
	    // 입금 서비스 호출
	    callService("XYZ01101", depositDataSet, onlineCtx);
	    
	    // MCI에 응답전문 송신
	    OutboundHeader header = new OutboundHeader(onlineCtx);
	    header.setResponse();
	    
	    // TODO GUID 변경해야 함.
	    header.setGlobId(orgGUID);
	    header.setIpAddr(orgIPAD);
	    header.setTxId("XYZ01310");

	    //TODO 계좌이체가 실패하였습니다.
	    header.setFailResultMessage("XYZE0011", null, null);
	    
	    // 전문 송신
	    sendOutboundAfterCommit(OutboundTarget.MCI, header, null, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.	
	    return responseData;
	}
	
   /* 
    * 이 클래스는 Singleton 객체로 수행됩니다. 
    * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
    */

}
