package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.core.component.streotype.BizAttribute;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecordSet;


/**
 * [DU]샘플상품.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-09 17:13:46
 */
@BizUnit(value="[DU]샘플상품", type="DU")
public class DTB_CBS_XYZ_PRDCD_M_00 extends nexbank.fwk.base.DataUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DTB_CBS_XYZ_PRDCD_M_00(){
		super();
	}

	/**
	 * 상품목록조회.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- recordSet : LIST [상품목록]
	 *		- field : PRD_CD () [상품코드]
	 *		- field : PRD_NAME () [상품명]
	 *		- field : PRD_RATE () [PRD_RATE]
	 *		- field : CRE_DTM () [생성일시]
	 *		- field : UPD_DTM () [수정일시]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:14:22
	 */
	@BizMethod("상품목록조회")
	@BizAttribute(isShared=true)
	public IDataSet s001(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();

	    IRecordSet rs = dbSelect("S001", requestData, onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("LIST", rs);
	    return responseData;
	}
  
}
