package nexbank.fwk.fwksbase.biz;

import nexbank.common.CommonArea;
import nexcore.framework.core.component.streotype.BizAttribute;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;


/**
 * [FU]회계처리.
 * <pre>
 * 
 * </pre>
 * 
 * @author nexbank (nexbank)
 * @since 2016-08-09 17:03:36
 */
@BizUnit(value="[FU]회계처리", type="FU")
public class FFWK0903 extends nexbank.fwk.base.FunctionUnit  {

	/* 
    * 이 클래스는 Singleton 객체로 수행됩니다. 
    * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
    */

	/**
	 * Default Constructor
	 */
	public FFWK0903(){
		super();
	}

	/**
	 * 회계처리.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : ACC_NO () [계좌번호]
	 *	- field : KIND () [입/출금구분]
	 *	- field : AMOUNT () [금액]
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:03:40
	 */
	@BizMethod("회계처리")
	@BizAttribute(isShared=true)
	public IDataSet cash(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    CommonArea ca = getCommonArea(onlineCtx);
	    
	    String ACC_NO = requestData.getString("ACC_NO");
	    String KIND = requestData.getString("KIND");
	    long AMOUNT = requestData.getLong("AMOUNT");
	    
	    ca.setAcno(ACC_NO);
	    if("I".equals(KIND)){ //입금
//	    	AcngIncsData o = new AcngIncsData();
//		    o.setAcngBaseCd("K");        //회계기준코드
//		    o.setBzopDt(ca.getBzopDt()); //영업일자 
//		    o.setBrCd(ca.getBrCd());     //부점코드 
//		    o.setAcctCd("01");           //계정과목코드 
//	    	o.setCachAltrDvcd(""); 
//	    	o.setInptOtptDvcd(KIND);
//	    	o.setTrnAmt(AMOUNT);
	    	
	    	ca.setCashMnrc(AMOUNT);
	    	ca.setAcngCcnt(ca.getAcngCcnt() + 1);
//	    	ca.addAcngIncsData(o);
	    }
	    else if("O".equals(KIND)){ //출금
//	    	AcngIncsData o = new AcngIncsData();
//		    o.setAcngBaseCd("K");        //회계기준코드
//		    o.setBzopDt(ca.getBzopDt()); //영업일자 
//		    o.setBrCd(ca.getBrCd());     //부점코드 
//		    o.setAcctCd("01");           //계정과목코드 
//	    	o.setCachAltrDvcd(""); 
//	    	o.setInptOtptDvcd(KIND);
//	    	o.setTrnAmt(AMOUNT);

	    	ca.setCashWtch(AMOUNT);
	    	ca.setAcngCcnt(ca.getAcngCcnt() + 1);
//	    	ca.addAcngIncsData(o);
	    }
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return responseData;
	}

}
