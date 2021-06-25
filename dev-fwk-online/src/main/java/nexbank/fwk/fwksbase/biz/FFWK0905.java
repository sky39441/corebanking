package nexbank.fwk.fwksbase.biz;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nexcore.framework.core.component.streotype.BizAttribute;
import nexcore.framework.core.component.streotype.BizMethod;
import nexcore.framework.core.component.streotype.BizUnit;
import nexcore.framework.core.data.IDataSet;
import nexcore.framework.core.data.IOnlineContext;
import nexcore.framework.core.data.IRecord;
import nexcore.framework.core.data.IRecordHeader;
import nexcore.framework.core.data.IRecordSet;
import nexcore.framework.core.service.txfilter.IServiceFilter;
import nexcore.framework.core.util.StringUtils;


/**
 * [FU]데이터가공처리.
 * <pre>
 * 
 * </pre>
 * 
 * @author admin (admin)
 * @since 2016-08-09 17:03:03
 */
@BizUnit(value="[FU]데이터가공처리", type="FU")
public class FFWK0905 extends nexbank.fwk.base.FunctionUnit  {

	/**
	 * 이 클래스는 Singleton 객체로 수행됩니다. 
	 * 여기에 필드를 선언하여 사용하면 동시성 문제를 일으킬 수 있습니다.
	 */

	/**
	 * Default Constructor
	 */
	public FFWK0905(){
		super();
	}

	/**
	 * 응답데이터 마스킹처리.
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
	 * @since 2016-08-09 17:03:07
	 */
	@BizMethod("응답데이터 마스킹처리")
	@BizAttribute(isShared=true)
	public IDataSet responseDataMasking(IDataSet requestData, IOnlineContext onlineCtx) {
//	    IDataSet responseData = new DataSet();
		
		IDataSet requestDataSet  = (IDataSet)requestData.get(IServiceFilter.REQUEST_DATASET_KEY);
		IDataSet responseDataSet = (IDataSet)requestData.get(IServiceFilter.RESPONSE_DATASET_KEY);
		
		// 항목명 기준 마스킹 샘플
		_maskingResponseDataByName(responseDataSet, onlineCtx);
		
		// 메타데이터 기반 마스킹 처리 샘플
		//_maskingResponseDataByMetadata(responseDataSet, onlineCtx);

	    // 처리 결과값을 responseData에 넣어서 리턴하십시요 
	    return requestData;
	}
	
	/**
	 * 항목명 기준 마스킹 샘플
	 */
	private void _maskingResponseDataByName(IDataSet responseData, IOnlineContext onlineCtx) {
		// 마스킹 설정 조회
		Map<String, String> maskingConfig = getMaskingConfig(onlineCtx);
		
		// 마스킹
		if(maskingConfig != null && maskingConfig.size() > 0){
			_maskingDataSet(maskingConfig, responseData);
		}
	}
	
	private Map<String, String> getMaskingConfig(IOnlineContext onlineCtx){
		//FIXME 마스킹 설정을 조회 및 생성하는 로직 작성 필요.
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("ACC_NO", "#####*****");
		return map;
	}

    private void _maskingDataSet(Map<String, String> maskingConfig, IDataSet dataSet) {
    	Set<String> fieldKeys = dataSet.keySet();
    	if(fieldKeys != null){
    		for(String fieldKey : fieldKeys){
    			Object value = dataSet.get(fieldKey);
    			if(value != null) {
    				if(value instanceof IRecordSet){
    					_maskingRecordSet(maskingConfig, (IRecordSet)value);
    				}
    				else {
			    		String maskingFormat = maskingConfig.get(fieldKey);
			    		if(maskingFormat != null){
			    			dataSet.put(fieldKey, _toMaskingValue(maskingFormat, dataSet.getString(fieldKey)));
			    		}
    				}
    			}
	    	}
    	}
    }
    
    private void _maskingRecordSet(Map<String, String> maskingConfig, IRecordSet recordSet){
    	if(recordSet != null && recordSet.getRecordCount() > 0 && recordSet.getHeaderCount() > 0){
			for(int col=0; col<recordSet.getHeaderCount(); col++){
				IRecordHeader header = recordSet.getHeader(col);
				String maskingFormat = maskingConfig.get(header.getId());
				if(maskingFormat != null){
					for(int row=0; row<recordSet.getRecordCount(); row++){
						IRecord record = recordSet.getRecord(row);
						String value = record.getString(col);
						if(value != null){
		    				record.set(col, _toMaskingValue(maskingFormat, value));
						}
					}
				}
				else {
					for(int row=0; row<recordSet.getRecordCount(); row++){
						IRecord record = recordSet.getRecord(row);
						Object obj = record.get(col);
						if(obj != null && obj instanceof IRecordSet){
							_maskingRecordSet(maskingConfig, (IRecordSet)obj);
						}
					}
				}
			}
		}
    }
    
    private String _toMaskingValue(String maskingFormat, String value){
    	return StringUtils.makeFormat(maskingFormat, value);
    }
    
//    /**
//     * 메타데이터 기반 마스킹 처리 샘플
//     */
//    private void _maskingResponseDataByMetadata(IDataSet responseData, IOnlineContext onlineCtx)  {
//    	IMethodMetaData methodMetaData = (IMethodMetaData) onlineCtx.getAttribute("nexcore.method.metadata");
//		if(methodMetaData != null){
//			IIoMetaData ioMetaData = methodMetaData.getOutputIoMetaData();
//			List<Object> mdList = null;
//			if(methodMetaData.isFixedLengthed()){
//				mdList = ioMetaData.getFlMetaDataList();
//			}
//			else {
//				mdList = ioMetaData.getFieldMetaDataList();
//			}
//			if(mdList != null) {
//				for(Object md : mdList){
//					if (md instanceof FieldMetaData) {
//						_maskingField((FieldMetaData)md, responseData);
//					}
//					else if (md instanceof IRecordSetMetaData) {
//						IRecordSetMetaData rsmd = (IRecordSetMetaData) md;
//						IRecordSet recordSet = responseData.getRecordSet(rsmd.getId());
//						_maskingRecordSet(rsmd, recordSet);
//					}
//				}
//			}
//		}
//    }
 
//	protected void _maskingField(FieldMetaData fmd, IDataSet responseData)  {
//		if("21".equals(fmd.getFormattingXd())){
//			String v = responseData.getField(fmd.getId());
//			if(!StringUtils.isEmpty(v)){
//				responseData.putField(fmd.getId(), _toMaskingValue(fmd.getFormattingLogic(), v));
//			}
//		}
//	}
//	
//	protected void _maskingRecordSet(IRecordSetMetaData rsmd, IRecordSet recordSet)  {
//		if (recordSet == null) {
//			return;
//		}
//
//		List<Object> mdList = rsmd.getFieldMetaDataList();
//		if(mdList != null && mdList.size() > 0){
//			for (int i = 0; i < recordSet.getRecordCount(); i++) {
//				IRecord record = recordSet.getRecord(i);
//				_maskingRecord(mdList, record);
//			}
//		}
//	}
//	
//	protected void _maskingRecord(List<Object> mdList, IRecord record)  {
//		for(Object md : mdList){
//			if (md instanceof FieldMetaData) {
//				_recrodFieldMaksing((FieldMetaData)md, record);
//			}
//			else if (md instanceof IRecordSetMetaData) {
//				IRecordSetMetaData rsmd = (IRecordSetMetaData) md;
//				IRecordSet recordSet = record.getRecordSet(rsmd.getId());
//				_maskingRecordSet(rsmd, recordSet);
//			}
//		}
//	}
//	
//	protected void _recrodFieldMaksing(FieldMetaData fmd, IRecord record) {
//		if("21".equals(fmd.getFormattingXd())){
//			String v = record.get(fmd.getId());
//			if(!StringUtils.isEmpty(v)){
//				record.set(fmd.getId(), _toMaskingValue(fmd.getFormattingLogic(), v));
//			}
//		}
//	}
	
}
