package nexbank.xyz.corebanking.biz;

import nexbank.common.CommonArea;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;

import org.apache.commons.logging.Log;


/**
 * [PU]샘플디퍼드업무.
 * <pre>
 * 
 * </pre>
 * 
 * @author nexbank (nexbank)
 * @since 2016-08-09 17:15:38
 */
@BizUnit(value="[PU]샘플디퍼드업무", type="PU")
public class PXYZ9800 extends nexbank.fwk.base.ProcessUnit  {

	@BizUnitBind
	private DTB_CBS_XYZ_ACNG_M_00 dTB_CBS_XYZ_ACNG_M_00;
	
	/**
	 * 부점별수신잔고.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:15:58
	 */
	@BizMethod("부점별수신잔고")
	public IDataSet pXYZ98001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    Log log = getLog(onlineCtx);
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    //부점코드 
	    String BR_CD = ca.getAcno().substring(0, 3); 
	    //상품코드
	    String PRD_CD = ca.getAcno().substring(3, 5); 
	    long BALANCE = 0;
	    // 현금출금
	    if(ca.getCashWtch() > 0){
	    	BALANCE -= ca.getCashWtch();
	    	if(log.isDebugEnabled()) {
	    		log.debug("출금액 : ["+ca.getCashWtch()+"]");
	    	}
	    }
	    // 현금입금
	    if(ca.getCashMnrc() > 0){
	    	BALANCE += ca.getCashMnrc();
	    	if(log.isDebugEnabled()) {
	    		log.debug("입금액 : ["+ca.getCashMnrc()+"]");
	    	}
	    }
	    	
	    IDataSet dTB_CBS_XYZ_ACNG_M_00Req = new DataSet();
	    dTB_CBS_XYZ_ACNG_M_00Req.put("BR_CD", BR_CD);
	    dTB_CBS_XYZ_ACNG_M_00Req.put("PRD_CD", PRD_CD);
	    dTB_CBS_XYZ_ACNG_M_00Req.put("BALANCE", BALANCE);
//	    DTB_CBS_XYZ_ACNG_M_00 dTB_CBS_XYZ_ACNG_M_00 = (DTB_CBS_XYZ_ACNG_M_00)lookupDataUnit(DTB_CBS_XYZ_ACNG_M_00.class);
	    IDataSet dTB_CBS_XYZ_ACNG_M_00Res = dTB_CBS_XYZ_ACNG_M_00.u001(dTB_CBS_XYZ_ACNG_M_00Req, onlineCtx);
	    
//	    if(true) {
//	    	throw new BizRuntimeException("XYZE0000", new String[]{"부점별 수신잔고 처리"});
//	    }
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.
	    return responseData;
	}
   /* 
    * 이 클래스는 Singleton 객체로 수행됩니다. 
    * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
    */

}
