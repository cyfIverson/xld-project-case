package com.xld.common.other;


import com.alibaba.fastjson.JSON;
import com.xld.common.bean.ENUM.HttpStatusEnum;
import com.xld.common.result.Results;
import com.xld.common.time.DateUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Excel工具类
 * @author sunfangwei
 */
public class ExcelUtil {

	/**
	 * 得到Workbook对象
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkBook(MultipartFile file) throws IOException {
		//这样写  excel 能兼容03和07
		InputStream is = file.getInputStream();
		Workbook hssfWorkbook = null;
		try {
			hssfWorkbook = new HSSFWorkbook(is);
		} catch (Exception ex) {
			is = file.getInputStream();
			hssfWorkbook = new XSSFWorkbook(is);
		}
		return hssfWorkbook;
	}

	/**
	 * 得到错误信息
	 *
	 * @param sb
	 * @param list
	 * @param i
	 * @param obj
	 * @param name 用哪个属性名去表明不和规定的数据
	 * @param msg
	 * @throws Exception
	 */
	public static void getWrongInfo(StringBuilder sb, List list, int i, Object obj, String name, String msg) throws Exception {
		Class clazz = obj.getClass();
		Object str = null;
		//得到属性名数组
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			if (f.getName().equals(name)) {
				//用来得到属性的get和set方法
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
				//得到get方法
				Method getMethod = pd.getReadMethod();
				str = getMethod.invoke(obj);
			}
		}
		if (i == 0) {
			sb.append(msg + str + ";");
		} else if (i == (list.size() - 1)) {
			sb.append(str + "</br>");
		} else {
			sb.append(str + ";");
		}

	}

	/**
	 * 下载Excel
	 * @param response
	 * @param wb
	 * @throws IOException
	 */
	public static void downloadExcel(HttpServletResponse response, Workbook wb, String excelName) throws IOException {
		// 重置响应对象
		response.reset();
		// 当前日期，用于导出文件名称
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = excelName + sdf.format(new Date()) + ".xls";
		// 指定下载的文件名--设置响应头
		response.addHeader("Content-Disposition", "attachment;filename=" + new String(dateStr.getBytes("gb2312"), "ISO8859-1"));
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 写出数据输出流到页面
		try {
			OutputStream output = response.getOutputStream();
			BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
			wb.write(bufferedOutPut);
			bufferedOutPut.flush();
			bufferedOutPut.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载Excel, 异步
	 * @param request request
	 * @param wordBook workBook
	 * @param excelName 导出的文件名
	 * @return 成功:导出的文件地址, 失败返回null
	 */
	public static String downloadExcelForAjax(HttpServletRequest request, Workbook wordBook, String excelName) {
		createDirIfNotExists();
		String dateStr = DateUtil.longToTimeStr(DateUtil.getCurrentTimeMillis(), "yyyyMMddHHmmss");
		StringBuilder url = new StringBuilder();
		url.append(absolutePath + staticDir + fileDir);
		url.append(excelName).append("_").append(dateStr).append(".xls");

		OutputStream stream = null;
		try {
			stream = new FileOutputStream(url.toString());
			wordBook.write(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return  staticDir + fileDir + excelName +"_" + dateStr + ".xls";
	}


	/**
	 * 创建多SheetExcel
	 * @param paList
	 * @param dataMap
	 * @return
	 */
	public static Workbook sheet(List<String> paList,Map<String,List<Map<String,String>>> dataMap){
		HSSFWorkbook workbook = new HSSFWorkbook();

		for (String sheetName:dataMap.keySet()){
			int n=0;
			HSSFSheet sheet = workbook.createSheet(sheetName);
			//创建行,从0开始
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell=null;
			Map<String,Integer> dataNMap=new HashMap<>();
			for(String cName:paList){
				cell = row.createCell(n);
				cell.setCellValue(cName.split("_")[1]);
				dataNMap.put(cName.split("_")[0],n);
				n++;
			}


			int rowN=1;
			if(null==dataMap.get(sheetName)){
				continue;
			}
			for(Map<String,String> data:dataMap.get(sheetName)){
				HSSFRow dataRow = sheet.createRow(rowN);
				for(String dataKey:data.keySet()){
					Integer vKey=dataNMap.get(dataKey);
					if(null !=vKey){
						HSSFCell cell2 = dataRow.createCell(vKey);
						cell2.setCellValue(data.get(dataKey));
					}
				}
				rowN++;
			}
		}
		return workbook;
	}

	/** excel数据为空 */
	public static void isNullOfExcel(HttpServletResponse response) {
		response.setContentType("application/json");
		try (PrintWriter out = response.getWriter()) {
			out.append(JSON.toJSON(Results.result(HttpStatusEnum.DATA_ERROR)).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 绝对路径
	 **/
	private static String absolutePath = "";

	/**
	 * 静态目录
	 **/
	private static String staticDir = "/upload";

	/**
	 * 文件存放的目录
	 **/
	private static String fileDir = "/excel/";

	/**
	 * 创建文件夹路径
	 */
	private static void createDirIfNotExists() {
		if (!absolutePath.isEmpty()) {
			return;
		}

		//获取跟目录
		File file = null;
		try {
			file = new File(ResourceUtils.getURL("classpath:").getPath());
		} catch (FileNotFoundException e) {
			throw new RuntimeException("获取根目录失败，无法创建上传目录！");
		}
		if (!file.exists()) {
			file = new File("");
		}

		absolutePath = file.getAbsolutePath();

		File upload = new File(absolutePath, staticDir + fileDir);
		if (!upload.exists()) {
			upload.mkdirs();
		}
	}

	/**
	 * 读取sheet对应坐标的数据并转换为BigDecimal格式
	 * @param formulaEvaluator 公式执行器
	 * @param sheet 表
	 * @param row 行号
	 * @param cellNum 列号
	 * @return BigDecimal格式的数据
	 */
	public static BigDecimal getSheetData(HSSFFormulaEvaluator formulaEvaluator, Sheet sheet, int row, int cellNum){
		BigDecimal bigDecimal;
		Cell cell = sheet.getRow(row).getCell(cellNum);
        String dataStr;

        if (cell == null) {
            dataStr = "0.0";
        } else {
            if (cell.getCellTypeEnum().equals(CellType.FORMULA)) {
                cell = formulaEvaluator.evaluateInCell(cell);
            }
        }

		dataStr = cell.toString();
		if (dataStr == null || "".equals(dataStr)) {
			dataStr = "0.0";
		}
		bigDecimal = new BigDecimal(dataStr);
		return bigDecimal;
	}

}

