package nexbank.fwk.fwksbase.biz;

import java.lang.reflect.InvocationTargetException;

//import nexbank.fwk.monitoring.PrometheusManager;
import nexcore.framework.core.component.streotype.BizAttribute;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.DataSet;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.service.txfilter.IServiceFilter;


/**
 * [FU]Prometheus 정보를 넣기 위한 전/후 처리용 FU.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin (admin)
 * @since 2021-02-26
 */
@BizUnit(value="[FU]Prometheus 거래 Count", type="FU")
public class FFWK0907 extends nexbank.fwk.base.FunctionUnit {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */
	
	/**
	 * Default Constructor
	 */
	public FFWK0907(){
		super();
	}

	@BizMethod("Transaction Count")
	@BizAttribute(isShared=true)
	public IDataSet txTotalCount(IDataSet requestData, IOnlineContext onlineCtx) {
		
		IDataSet responseData = new DataSet();
		
//		PrometheusManager.getInstance().increaseGauge(PrometheusManager.TX_TOTAL_COUNT);
		
		return responseData;
	}
	
	@BizMethod("Success Count")
	@BizAttribute(isShared=true)
	public IDataSet txSuccessCount(IDataSet requestData, IOnlineContext onlineCtx) {
		
		IDataSet responseData = new DataSet();
		
//		PrometheusManager.getInstance().increaseGauge(PrometheusManager.TX_OK_COUNT);
		
		return responseData;
	}
	
	@BizMethod("Error Count")
	@BizAttribute(isShared=true)
	public IDataSet txErrorCount(IDataSet requestData, IOnlineContext onlineCtx) {
		
		IDataSet responseData = new DataSet();
		
		// 예외 정보 분석
	    Throwable throwable = (Throwable)requestData.get(IServiceFilter.THROWABLE_KEY);
	    
	    // TODO: throwable 을 가지고 BIZ 에러인지 FWK 에러인지 판단
//	    if (throwable instanceof InvocationTargetException) { 
//	    	PrometheusManager.getInstance().increaseGauge(PrometheusManager.TX_BIZ_ERROR_COUNT);
//        }
//        else /*if(exception instanceof RuntimeException)*/{
//        	PrometheusManager.getInstance().increaseGauge(PrometheusManager.TX_FWK_ERROR_COUNT);
//        }
		
		return responseData;
	}
  
}
