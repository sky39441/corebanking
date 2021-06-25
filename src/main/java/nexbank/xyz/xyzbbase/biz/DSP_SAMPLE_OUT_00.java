package nexbank.xyz.xyzbbase.biz;

import java.util.Map;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;


/**
 * [DU]프로시저 샘플.
 * <pre>
 * 프로시저 샘플
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-09 17:28:37
 */
@BizUnit(value="[DU]프로시저 샘플", type="DU")
public class DSP_SAMPLE_OUT_00 extends nexbank.fwk.base.DataUnit {
	
	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public DSP_SAMPLE_OUT_00(){
		super();
	}

	/**
	 * 프로시저 샘플.
	 * <pre>
	 * 프로시저 샘플
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx   요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin(admin)
	 * @since 2016-08-09 17:28:37
	 */
	@BizMethod("프로시저 샘플")
	public IDataSet dP001(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	
	    Map param = requestData.toMap();

	    dbProcedure("P001", param, onlineCtx);

	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    responseData.put("P_OUTVAL", param.get("P_OUTVAL"));
	
	    return responseData;
	}
  
}
