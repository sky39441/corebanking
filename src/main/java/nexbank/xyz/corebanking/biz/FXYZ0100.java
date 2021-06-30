package nexbank.xyz.corebanking.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;


/**
 * [FU]샘플계좌유효성체크.
 * <pre>
 * 
 * </pre>
 * 
 * @author 차지환 (jihwancha)
 * @since 2016-08-09 17:28:06
 */
@BizUnit(value="[FU]샘플계좌유효성체크", type="FU")
public class FXYZ0100 extends nexbank.fwk.base.FunctionUnit  {

	@BizUnitBind
	private DTB_CBS_XYZ_ACC_M_00 dTB_CBS_XYZ_ACC_M_00;
	
	/**
	 * 계좌존재여부.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO () [계좌번호]
	 *	- field : ACC_PASSWD_CHECK_YN () [계좌비밀번호체크여부]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXIST_ACC_YN () [존재여부]
	 *	- field : VALID_ACC_PASSWD_YN () [비밀번호유효여부]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:06
	 */
	@BizMethod("계좌존재여부")
	public IDataSet checkAccNo(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	    
	
	    // 계좌원장 DU
//	    DTB_CBS_XYZ_ACC_M_00 dTB_CBS_XYZ_ACC_M_00 = (DTB_CBS_XYZ_ACC_M_00)lookupDataUnit(DTB_CBS_XYZ_ACC_M_00.class);
	    
	    // 계좌상세 조회
	    IDataSet dTB_CBS_XYZ_ACC_M_00_S001Res = dTB_CBS_XYZ_ACC_M_00.s002(requestData, onlineCtx);
	    String ACC_NO = dTB_CBS_XYZ_ACC_M_00_S001Res.getString("ACC_NO");

	    boolean existAccYn = ACC_NO != null && ACC_NO.trim().length() > 0;
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("EXIST_ACC_YN", existAccYn ? "Y" : "N");
	    
	    // 계좌비밀번호체크여부
	    if(existAccYn && "Y".equals(requestData.getString("ACC_PASSWD_CHECK_YN"))){
	    	String accPasswd = dTB_CBS_XYZ_ACC_M_00_S001Res.getString("ACC_PASSWD");
		    responseData.put("VALID_ACC_PASSWD_YN", accPasswd.equals(requestData.getString("ACC_PASSWD")) ? "Y" : "N");
	    }
	
	    return responseData;
	}
	
   /* 
    * 이 클래스는 Singleton 객체로 수행됩니다. 
    * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
    */

}
