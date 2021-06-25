package nexbank.xyz.xyzsbase.biz;

import java.math.BigDecimal;

import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.component.streotype.BizUnitBind;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.log.ILog;
import nexcore.framework.core.component.streotype.BizAttribute;

/**
 * AAA테이블 테스트.
 * <pre>
 * AAA테이블 테스트
 * </pre>
 * 
 * @author admin (Administrator)
 * @since 2021-04-26 15:35:30
 */
@BizUnit(value = "AAA테이블 테스트", type = "FU")
public class FAAA extends nexbank.fwk.base.FunctionUnit {

	@BizUnitBind
	private DAAA2_00 dAAA2_00;
	
	@BizUnitBind
	private DAAA_00 dAAA_00;
	
	/**
	 * AAA테이블 조회.
	 * <pre>
	 * AAA테이블 조회
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * <pre>
	 * </pre>
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * <pre>
	 * </pre>
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-26 15:35:51
	 */
	@BizAttribute(isShared = true)
	@BizMethod("AAA테이블 조회")
	public IDataSet selectAAA(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    ILog     log          = getLog(onlineCtx);
	
		// @@ 메소드 호출 (AAA SELECT ALL DAAA2_00.s002)
	    IDataSet s002Res = dAAA_00.s002(requestData, onlineCtx);
	    
		responseData = s002Res;
		return responseData;
	}

	/**
	 * AAA2 테이블 등록.
	 * <pre>
	 * AAA2 테이블 등록
	 * </pre>
	 * @param requestData 요청정보 DataSet 객체
	 * @param onlineCtx 요청 컨텍스트 정보
	 * @return 처리결과 DataSet 객체
	 * 
	 * @author admin (Administrator)
	 * @since 2021-04-26 15:36:16
	 */
	@BizAttribute(isShared = true)
	@BizMethod("AAA2 테이블 등록")
	public IDataSet insertAAA2(IDataSet requestData, IOnlineContext onlineCtx) {
	    IDataSet responseData = new DataSet();
	    ILog     log          = getLog(onlineCtx);
	    
		/**********************************************************************/
		// 유닛 메소드 호출 - AAA2 INSERT (DAAA2_00.i001)
	    // TODO 아래 구문이 멤버변수로 선언되어야 합니다.
	    // @BizUnitBind
	    // private DAAA2_00 dAAA2_00;
		
		// @@ 입력 데이터셋 생성
		// TODO 호출되는 메소드에서 입력 데이터셋의 변경이 없을 경우는 새로운 DataSet을 만들지 않아도 됩니다.    
		// 예) IDataSet i001Req = requestData;
	    IDataSet i001Req = new DataSet();
		i001Req.put("A", requestData.get("A")); // A
		i001Req.put("B", requestData.get("B")); // B
		i001Req.put("C", requestData.get("C") + "-"+Thread.currentThread()); // C
		i001Req.put("MM", requestData.get("MM")); // MM
		i001Req.put("LAST_TIME", requestData.get("LAST_TIME")); // LAST_TIME

		// @@ 메소드 호출 (AAA2 INSERT DAAA2_00.i001)
	    IDataSet i001Res = dAAA2_00.i001(i001Req, onlineCtx);
	    
	    // @@ 출력 데이터 셋 처리
		// TODO 출력 데이터셋의 각 필드별 개별 처리가 필요없는 경우 아래 소스 블럭 제거
		int expectedRow = i001Res.getInt("EXPECTED_ROW"); // EXPECTED_ROW
		/**********************************************************************/
	    
	    return responseData;
	}

}