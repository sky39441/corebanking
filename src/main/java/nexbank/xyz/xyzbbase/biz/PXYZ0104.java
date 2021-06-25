package nexbank.xyz.xyzbbase.biz;

import java.util.HashMap;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;


/**
 * [PU]온디멘드배치.
 * <pre>
 * 
 * </pre>
 * 
 * @author nexbank (nexbank)
 * @since 2016-08-09 17:19:20
 */
@BizUnit(value="[PU]온디멘드배치", type="PU")
public class PXYZ0104 extends nexbank.fwk.base.ProcessUnit  {

	/**
	 * 온디멘드배치 실행.
	 * <pre>
	 * 
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 * </pre>
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 *	- field : JOB_EXE_ID (string) [잡실행아이디]
	 * </pre>
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:19:21
	 */
	@BizMethod("온디멘드배치 실행")
	public IDataSet pXYZ01401(IDataSet requestData, IOnlineContext onlineCtx){
	    IDataSet responseData = new DataSet();
	
	    // call on-demand batch job
	    callBatchJobNow("BPOC1900", new HashMap<String,String>(0), onlineCtx);
	    
	    // 처리 결과값을 responseData에 넣어서 리턴하십시요
//	    responseData.setOkResultMessage("NCOM0000", null); //정상처리 되었습니다.	
	    return responseData;
	}
   /* 
    * 이 클래스는 Singleton 객체로 수행됩니다. 
    * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
    */

}
