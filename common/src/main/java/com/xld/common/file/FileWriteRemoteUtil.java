package com.xld.common.file;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xld.common.config.FileUrlConfig;
import com.xld.common.other.LogUtil;
import org.springframework.stereotype.Component;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * 文件操作工具类（向文件服务器写文件）
 * @author xld
 */
@Component
public class FileWriteRemoteUtil {

	/**
	 * 上传单个文件
	 * @param fileName 文件名称
	 * @param uploadFile 文件
	 * @param fileDir 文件保存目录，如“/userImage/”
	 * @return 操作成功：返回文件相对保存路径，操作失败：返回null
	 */
	public static FileResultDTO uploadFile(String fileName, File uploadFile, String fileDir){
		try {
			FileInputStream uploadFileInputStream = new FileInputStream(uploadFile);
			
			List<FileDataEntity> fileList = new ArrayList<>();
			FileDataEntity fileDataEntity = new FileDataEntity(fileName, uploadFileInputStream);
			fileList.add(fileDataEntity);

			List<FileResultDTO> fileResultList = uploadFileList(fileList, fileDir);
			if(fileResultList != null && fileResultList.size() > 0){
				return fileResultList.get(0);
			}
		} catch (Exception e) {
			LogUtil.printErrorLog(e);
		}
		return null;
	}
	
	/**
	 * 上传单个文件
	 * @param fileName 文件名称
	 * @param fileInputStream 文件流
	 * @param fileDir 文件保存目录，如“/userImage/”
	 * @return 操作成功：返回文件相对保存路径，操作失败：返回null
	 */
	public static FileResultDTO uploadFile(String fileName, InputStream fileInputStream, String fileDir){
		List<FileDataEntity> fileList = new ArrayList<>();
		FileDataEntity fileDataEntity = new FileDataEntity(fileName, fileInputStream);
		fileList.add(fileDataEntity);

		List<FileResultDTO> fileResultList = uploadFileList(fileList, fileDir);
		if(fileResultList != null && fileResultList.size() > 0){
			return fileResultList.get(0);
		}
		return null;
	}
	
	/**
	 * 上传多个文件
	 * @param fileList 待上传的文件列表
	 * @param fileDir 文件保存目录，如“/userImage/”
	 * @return 操作成功：返回文件相对保存路径结果列表数据，操作失败：返回null
	 */
	public static List<FileResultDTO> uploadFileList(List<FileDataEntity> fileList, String fileDir){
		List<FileResultDTO> fileResultDTOList = new ArrayList<>();
		try {
			URL url = new URL(FileUrlConfig.HANDLE_URL +FileUrlConfig.UPLOAD_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			//post方式不能使用缓存
			connection.setUseCaches(false);
			// 设置请求头信息
			connection.setRequestProperty("Connection", "Keep-Alive");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
			// 设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			connection.setRequestProperty("Content-Type","multipart/form-data; boundary="+ BOUNDARY);
			
			//参数数据
			//文本数据部分
			StringBuilder sb = new StringBuilder();
			sb.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"token\"\r\n\r\n" + FileUrlConfig.FILE_REMOTE_TOKEN);
			sb.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"fileDir\"\r\n\r\n" + fileDir);
			sb.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"hash\"\r\n\r\n" + true);

			byte[] head = sb.toString().getBytes("UTF-8");
			// 获得输出流
			OutputStream out = new DataOutputStream(connection.getOutputStream());
			// 输出表头
			out.write(head);
			// 文件数据部分：
			// 把文件以流文件的方式推入到url中
			if(fileList != null && fileList.size() > 0){
				for (int i = 0; i < fileList.size(); i++) {
					FileDataEntity fileEntity = fileList.get(i);
					String fileName = fileEntity.getFileName();
					InputStream fileInputStream = fileEntity.getFileInputStream();
					
					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data;name=\"fileList\";filename=\"" + fileName + "\"\r\n");
					strBuf.append("Content-Type:application/octet-stream\r\n\r\n");
					out.write(strBuf.toString().getBytes());
	                      
					DataInputStream in = new DataInputStream(fileInputStream);
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			
			// 结尾部分
			// 定义最后数据分隔线
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
			out.write(foot);
			
			out.flush();
			out.close();
			
			//读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sbf = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "UTF-8");
                sbf.append(lines);
            }
            
            reader.close();
            // 断开连接
            connection.disconnect();
            //返回结果
            JSONObject resultJson = JSONObject.parseObject(sbf.toString());
			LogUtil.printInfoLog("文件上传返回结果"+resultJson);

            if(resultJson != null){
    			if(resultJson.getIntValue("code") == 1){
    				LogUtil.printInfoLog(resultJson.toString());
    				//JSON数组
    				JSONArray arrayResult = (JSONArray) resultJson.get("data");

    				for (int i = 0; i < arrayResult.size(); i ++){
						JSONObject result = (JSONObject) arrayResult.get(i);

						FileResultDTO fileResultDTO = new FileResultDTO();
						fileResultDTO.setUrlRelative((String) result.get("urlRelative"));
						fileResultDTO.setUrlAbsolutely((String) result.get("urlAbsolutely"));
						fileResultDTO.setHash((String) result.get("hash"));
						fileResultDTOList.add(fileResultDTO);
					}
    				return fileResultDTOList;
    			}
    		}
		} catch (Exception ex) {
			LogUtil.printErrorLog(ex);
		}
		return null;
	}
	
	/**
	 * 删除单个文件
	 * @param deleteFile 待删除的文件相对路径
	 * @return 操作成功：返回true，操作失败：返回false
	 */
	public static boolean deleteFile(String deleteFile, String deleteUrl){
		List<String> fileList = new ArrayList<String>();
		fileList.add(deleteFile);
		
		List<String> resultList = deleteFileList(fileList,deleteUrl);
        return resultList != null && resultList.size() > 0;
    }
	
	/**
	 * 删除多个文件
	 * @param deleteFile 待删除的文件相对路径数组
	 * @return 操作成功：返回删除成功的文件路径结果列表数据，操作失败：返回null
	 */
	public static List<String> deleteFileList(List<String> deleteFile, String deleteUrl){
        try {
			URL url = new URL(FileUrlConfig.HANDLE_URL+FileUrlConfig.DELETE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
			//设置请求方式
            connection.setRequestMethod("POST");
			//设置接收数据的格式
            connection.setRequestProperty("Accept", "application/json");
			//设置发送数据的格式
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            
            //提交参数
            JSONObject params = new JSONObject();
            params.put("token", FileUrlConfig.FILE_REMOTE_TOKEN);
            params.put("deleteFileList", deleteFile);
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            out.write(params.toString());
            
            out.flush();
            out.close();

            //读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            StringBuffer sbf = new StringBuffer();
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "UTF-8");
                sbf.append(lines);
            }
            
            reader.close();
            // 断开连接
            connection.disconnect();
            
            JSONObject resultJson = JSONObject.parseObject(sbf.toString());
            
    		if(resultJson != null){
    			if(resultJson.getIntValue("code") == 1){
    				JSONObject jsonData = resultJson.getJSONObject("data");
    				JSONArray resultData = jsonData.getJSONArray("deleteUrlList");
    				if(resultData != null && resultData.size() > 0){
    					List<String> resultList = new ArrayList<String>();
    					for (int i = 0; i < resultData.size(); i++) {
    						String deleteSuccessFile = (String) resultData.get(i);
    						resultList.add(deleteSuccessFile);
						}
    					return resultList;
    				}
    			}
    		}
        } catch (Exception ex) {  
            LogUtil.printErrorLog(ex);
        }  
        return null;
	}
}
