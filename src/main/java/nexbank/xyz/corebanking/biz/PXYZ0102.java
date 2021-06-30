package nexbank.xyz.corebanking.biz;

import nexbank.common.CommonArea;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.exception.BizRuntimeException;
import nexcore.framework.core.util.DateUtils;

import org.apache.commons.logging.Log;


/**
 * [PU]샘플계좌출금.
 * <pre>
 * 
 * </pre>
 * 
 * @author 차지환 (jihwancha)
 * @since 2016-08-09 17:20:20
 */
@BizUnit(value="[PU]샘플계좌출금", type="PU")
public class PXYZ0102 extends nexbank.fwk.base.ProcessUnit  {

	@BizUnitBind
	private FXYZ0100 fXYZ0100;
	@BizUnitBind
	private DTB_CBS_XYZ_ACC_M_00 dTB_CBS_XYZ_ACC_M_00;
	@BizUnitBind
	private DTB_CBS_XYZ_ACC_H_00 dTB_CBS_XYZ_ACC_H_00;
	
	/**
	 * 계좌출금.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO (string) [계좌번호]
	 *	- field : AMOUNT (long) [금액]
	 *	- field : DESCRIPTION (string) [메시지]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:20:21
	 */
	@BizMethod("계좌출금")
	public IDataSet pXYZ01201(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	    try{
		    Log log = getLog(onlineCtx);
		    
		    double AMOUNT = requestData.getDouble("AMOUNT");
		    
		    // 계좌 FU
//		    FXYZ0100 fXYZ0100 = (FXYZ0100)lookupFunctionUnit(FXYZ0100.class);
		    
		    // 출금계좌 존재여부 및 비밀번호 체크
		    requestData.put("ACC_PASSWD_CHECK_YN", "Y");
		    IDataSet fXYZ01CheckAccNoRes = fXYZ0100.checkAccNo(requestData, onlineCtx);
//		    
		    // 출금 계좌가 존재하지 않습니다.
		    if(!"Y".equals(fXYZ01CheckAccNoRes.getString("EXIST_ACC_YN"))){
		    	// 출금계좌가 존재하지 않습니다.
		    	throw new BizRuntimeException("XYZE0007");
		    }
		    if(!"Y".equals(fXYZ01CheckAccNoRes.getString("VALID_ACC_PASSWD_YN"))){
		    	// 비밀번호가 일치하지 않습니다.
		    	throw new BizRuntimeException("XYZE0008");
		    }

		    // CommonArea
		    CommonArea ca = getCommonArea(onlineCtx);
		    if(log.isInfoEnabled()){
		    	log.info("CommonArea 고객번호 조회 : " + ca.getCustNo());
		    }

		    IDataSet dTB_CBS_XYZ_ACC_M_00_S002Res = dTB_CBS_XYZ_ACC_M_00.s002(requestData, onlineCtx);
		    double ACC_BALANCE = dTB_CBS_XYZ_ACC_M_00_S002Res.getDouble("ACC_BALANCE");
		    
		    if(AMOUNT > ACC_BALANCE){
		    	throw new BizRuntimeException("XYZE0013");
		    }
		    
		    // 금액 수정
		    requestData.put("KIND", "O"); // 유형
		    requestData.put("AMOUNT", -AMOUNT);
		    requestData.put("ACC_BALANCE", ACC_BALANCE-AMOUNT);
		    requestData.put("CRE_DTM", DateUtils.getDateString("yyyyMMddHHmmssSSS"));
		    requestData.put("UPD_DTM", DateUtils.getDateString("yyyyMMddHHmmssSSS"));
		    IDataSet dTB_CBS_XYZ_ACC_M_00_U001Res = dTB_CBS_XYZ_ACC_M_00.u001(requestData, onlineCtx);
//		    IDataSet dTB_CBS_XYZ_ACC_M_00_U001Res = dbioUpdate("TB_CBS_XYZ_ACC_M", requestData, onlineCtx);
		    if(dTB_CBS_XYZ_ACC_M_00_U001Res.getInt("EXPECTED_ROW") < 1){
		    	//출금처리가 실패하였습니다.
		    	throw new BizRuntimeException("XYZE0005");
		    }
		    
		    // 출금이력 등록
	//	    requestData.put("kind", ca.getFrstTrnmChnlCd()); // 채널
		    requestData.put("AMOUNT", AMOUNT);
		    IDataSet dTB_CBS_XYZ_ACC_H_00_I001Res = dTB_CBS_XYZ_ACC_H_00.i001(requestData, onlineCtx);
		    
		    // 출금이력 등록 결과 확인
		    if(dTB_CBS_XYZ_ACC_H_00_I001Res.getInt("EXPECTED_ROW") < 1){
		    	// 출금이력 등록이 실패하였습니다.
		    	throw new BizRuntimeException("XYZE0006");
		    }
		    
		    // 회계 모듈 호출
		    IDataSet fFWK09CashReq = new DataSet();
		    fFWK09CashReq.put("ACC_NO", requestData.getString("ACC_NO"));
		    fFWK09CashReq.put("KIND", "O");
		    fFWK09CashReq.put("AMOUNT", AMOUNT);
		    
		    callSharedMethodByDirect("nexbank.fwk.fwksbase", "FFWK0903.cash", fFWK09CashReq, onlineCtx);

		    return responseData;
	    }
	    catch(BizRuntimeException e){
	    	throw e;
	    }
	    catch(Exception e){
	    	throw new BizRuntimeException("ECOM0000", new String[]{"출금"}, e);
	    }
	}
   /* 
    * 이 클래스는 Singleton 객체로 수행됩니다. 
    * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
    */

}
