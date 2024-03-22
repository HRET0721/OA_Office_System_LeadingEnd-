package org.hret.pojo;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Author:HRET Milky Way
 * Date:2024/1/18
 * version:1.0
 * EasyPoi工具类
 * @author HRET
 */
public class EasyPoiUtils {

    /**
     * 导出Excel
     *
     * @param list      数据集合
     * @param title     表格标题
     * @param sheetName sheet页名称
     * @param pojoClass 实体类
     * @param fileName  文件名称
     * @param response  响应
     */
    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName), pojoClass, list);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    /**
     * 下载Excel
     *
     * @param fileName 文件名称
     * @param response 响应
     * @param workbook 工作簿
     */
    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");

            //设置信息头，告诉浏览器内容为excel类型
            response.setHeader("content-Type", "application/vnd.ms-excel");
            //设置下载名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));

            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

}
