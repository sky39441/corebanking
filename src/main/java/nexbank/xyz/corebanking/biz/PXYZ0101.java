package nexbank.xyz.corebanking.biz;

import org.apache.commons.logging.Log;

import nexbank.common.CommonArea;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;


/**
 * [PU]샘플계좌입금.
 * <pre>
 * 
 * </pre>
 * 
 * @author 차지환 (jihwancha)
 * @since 2016-08-09 17:20:53
 */
@BizUnit(value="[PU]샘플계좌입금", type="PU")
public class PXYZ0101 extends nexbank.fwk.base.ProcessUnit {

	// 유효성 체크
	@BizUnitBind
	private FXYZ0100 fXYZ0100;
	
	// 계좌 마스터
	@BizUnitBind
	private DTB_CBS_XYZ_ACC_M_00 dTB_CBS_XYZ_ACC_M_00;
	
	// 계좌 이력
	@BizUnitBind
	private DTB_CBS_XYZ_ACC_H_00 dTB_CBS_XYZ_ACC_H_00;

	/**
	 * 계좌입금.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO [계좌번호] (string)
	 *	- field : AMOUNT [금액] (long)
	 *	- field : DESCRIPTION [메시지] (string)
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:20:53
	 */
	@BizMethod("계좌입금")
	public IDataSet pXYZ01101(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    try {
		    Log log = getLog(onlineCtx);
		    
//		    Thread.sleep(3000);

		    // 이체 금액
		    long amount = requestData.getLong("AMOUNT");
		    
		    // 계좌 FU
//		    FXYZ0100 fXYZ0100 = (FXYZ0100)lookupFunctionUnit(FXYZ0100.class);
		    
		    // 입금 계좌번호 존재여부 확인
		    IDataSet fXYZ01CheckAccNoRes01 = fXYZ0100.checkAccNo(requestData, onlineCtx);
		    
		    // 입금 계좌가 존재하지 않습니다.
		    if(!"Y".equals(fXYZ01CheckAccNoRes01.getString("EXIST_ACC_YN"))){
		    	// 입금계좌가 존재하지 않습니다.
		    	throw new BizRuntimeException("XYZE0009");
		    }
		    
		    // CommonArea
		    CommonArea ca = getCommonArea(onlineCtx);
		    
		    if(amount >= 50000000){
		    	ca.setAprvDmndTrnAmt(amount);
		    	addAprvMsgCd(onlineCtx, "ACOM0007");
		    	if(log.isWarnEnabled()){
		    		log.warn("고액거래로 책임자 승인 코드(ACOM0007) 등록함.");
		    	}
		    }
	
		    // 계좌원장 DU
		    IDataSet dTB_CBS_XYZ_ACC_M_00_S002Res = dTB_CBS_XYZ_ACC_M_00.s002(requestData, onlineCtx);
		    Double accBal = dTB_CBS_XYZ_ACC_M_00_S002Res.getDouble("ACC_BALANCE");
	    	if(log.isInfoEnabled()){
	    		log.info("계좌 입금 처리 시작.");
	    	}
	
		    // 금액 수정
		    requestData.put("KIND", "I"); // 유형
		    requestData.put("ACC_BALANCE", accBal+amount);
		    requestData.put("CRE_DTM", DateUtils.getDateString("yyyyMMddHHmmssSSS"));
		    requestData.put("UPD_DTM", DateUtils.getDateString("yyyyMMddHHmmssSSS"));
		    
		    IDataSet dTB_CBS_XYZ_ACC_M_00_U001Res = dTB_CBS_XYZ_ACC_M_00.u001(requestData, onlineCtx);

		    if(dTB_CBS_XYZ_ACC_M_00_U001Res.getInt("EXPECTED_ROW") < 1){
		    	// 계좌입금을 실패하였습니다.
		    	throw new BizRuntimeException("XYZE0003");
		    }
	    	if(log.isInfoEnabled()){
	    		log.info("계좌 입금 처리 완료.");
	    	}
		    
	    	if(log.isInfoEnabled()){
	    		log.info("계좌 이력 등록 시작.");
	    	}
	
		    // 입금이력 등록
		    IDataSet dTB_CBS_XYZ_ACC_H_00_I001Res = dTB_CBS_XYZ_ACC_H_00.i001(requestData, onlineCtx);
		    
		    // 입금이력 등록 결과 확인
		    if(dTB_CBS_XYZ_ACC_H_00_I001Res.getInt("EXPECTED_ROW") < 1){
		    	// 입금이력 등록이 실패하였습니다.
		    	throw new BizRuntimeException("XYZE0004");
		    }
	
	    	if(log.isInfoEnabled()){
	    		log.info("계좌 이력 등록 완료.");
	    	}
	    	
		    // 회계 모듈 호출
		    IDataSet fFWK09CashReq = new DataSet();
		    fFWK09CashReq.put("ACC_NO", requestData.getString("ACC_NO"));
		    fFWK09CashReq.put("KIND", "I");
		    fFWK09CashReq.put("AMOUNT", requestData.getLong("AMOUNT"));

		    callSharedMethodByDirect("nexbank.fwk.fwksbase", "FFWK0903.cash", fFWK09CashReq, onlineCtx);

		    return responseData;
	    }
	    catch(BizRuntimeException e){
	    	throw e;
	    }
	    catch(Exception e){
	    	throw new BizRuntimeException("ECOM0000", new String[]{"입금"}, e);
	    }
	}

}
