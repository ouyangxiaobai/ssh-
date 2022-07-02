package com.nkl.common.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

public class Struts2ExcelResultSupport extends StrutsResultSupport {

	private static final long serialVersionUID = 3831749858786507698L;
	private static final Logger logger = Logger
			.getLogger(Struts2ExcelResultSupport.class);
	private static final String contentType = "application/vnd.ms-excel";
	private static final String actionKey = "action";
	private static final String EXCEL_EXPORT_NAME = "exportExcelName";

	/**
	 * @Title: doExecute
	 * @Description: TODO
	 * @param arg0
	 * @param arg1
	 * @throws Exception
	 * @see org.apache.struts2.dispatcher.StrutsResultSupport#doExecute(java.lang.String,
	 *      com.opensymphony.xwork2.ActionInvocation)
	 */

	@SuppressWarnings("unchecked")
	@Override
	protected void doExecute(String location, ActionInvocation invocation)
			throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setContentType(contentType);
		String fileName = (String) invocation.getStack().findValue(
				EXCEL_EXPORT_NAME);

		if ((fileName == null) || (fileName.length() == 0)) {
			fileName = invocation.getProxy().getActionName() + "_"
					+ System.currentTimeMillis() + ".xls";
		}

		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes("GBK"), "ISO-8859-1") + "\"");

		InputStream in = null;
		try {
			in = Struts2ExcelResultSupport.class.getClassLoader().getResourceAsStream(location.trim());
			XLSTransformer transformer = new XLSTransformer();
			Map beans = PropertyUtils.describe(invocation.getAction());
			beans.put(actionKey, invocation.getAction());

			HSSFWorkbook workbook = (HSSFWorkbook) transformer.transformXLS(in,
					beans);

			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
		} catch (Exception e) {
			logger.error("导出excel出错，e:" + e);
		} finally {
			if (in != null)
				in.close();
		}
	}

}
