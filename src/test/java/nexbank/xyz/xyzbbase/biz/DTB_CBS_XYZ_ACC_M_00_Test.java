package nexbank.xyz.xyzbbase.biz;

import nexcore.framework.channel.web.testcase.AbstractJacksonBasedTestCase;
import org.junit.Test;

/**
* [DU]샘플계좌원장.
* <pre>
* [DU]샘플계좌원장
* </pre>
* @author admin (Administrator)
* @since 2019-10-01 18:20:05
*/
public class DTB_CBS_XYZ_ACC_M_00_Test extends AbstractJacksonBasedTestCase {
	public DTB_CBS_XYZ_ACC_M_00_Test() {

	}

	/**
	 * Test method for.
	 * @author Administrator(admin)
	 * @since 2019-10-01 18:20:11
	 */
	@Test
	public void s001_T20191001182007579() throws Exception {
		org.jdom2.Element element = doServiceCall();
	}
}
