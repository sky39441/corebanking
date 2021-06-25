
package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;


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
public class DATB_CBS_XYZ_POC_00 extends nexbank.fwk.base.DataUnit  {
	
	/**
	 * mariadb 에 등록.
	 * <pre>
	 * mariadb 에 등록
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 *	- field : DESC [적요]
	 *	- field : AMOUNT [금액]
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : EXPECTED_ROW [반영건수]
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2018-09-17 17:21:53
	 */
	@BizMethod("mariadb 에 등록")
	public IDataSet i001(IDataSet requestData, IOnlineContext onlineCtx) {
		IDataSet responseData = new DataSet();
		
		dbInsert("I001", requestData, "MARIADB", onlineCtx);
		
		return responseData;
	}
	
}