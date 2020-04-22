package org.sang.component;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.sang.model.People;
import org.sang.service.PeopleService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PoiUtils {
    PeopleService peopleService;

    /**
     *功能描述：导出所有人物信息到Excel
     * @author Chenergy
     * @date 2020/03/09
     * @param
     * @return
     */
    public static ResponseEntity<byte []> exportPeople2Excel(List<People> peopleList){
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try{
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("人员信息");
            //3.2设置文档管理员
            dsi.setManager("Chenergy");
            //3.3设置组织机构
            dsi.setCompany("qcctweb");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("人员信息表");
            //4.2设置文档标题
            si.setSubject("人员信息");
            //4.3设置文档作者
            si.setAuthor("XXX集团");
            //4.4设置文档备注
            si.setComments("Carpe Diem!");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("人员生日信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 5 * 256);
            sheet.setColumnWidth(1, 15 * 256);
            sheet.setColumnWidth(2, 30 * 256);
            sheet.setColumnWidth(3, 30 * 256);
            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);

            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("ID");
            cell0.setCellStyle(headerStyle);

            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("姓名");
            cell1.setCellStyle(headerStyle);

            HSSFCell cell2 = headerRow.createCell(2);
            cell2.setCellValue("阳历生日");
            cell2.setCellStyle(headerStyle);

            HSSFCell cell3 = headerRow.createCell(3);
            cell3.setCellValue("阴历生日");
            cell3.setCellStyle(headerStyle);

            //6.装数据
            for(int i = 0; i<peopleList.size(); i++){
                HSSFRow row = sheet.createRow(i + 1);
                People people = peopleList.get(i);
                row.createCell(0).setCellValue(people.getId());
                row.createCell(1).setCellValue(people.getName());
                row.createCell(2).setCellValue(people.getSolarCalendarBirthday());
                row.createCell(3).setCellValue(people.getLunarCalendarBirthday());
            }

            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", new String("人员生日信息表.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        }catch(IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    /**
     *功能描述：根据Excel导入人物信息
     * @author Chenergy
     * @date 2020/04/08
     * @param
     * @return
     */
    public static List<People> importPeople2List(MultipartFile file){
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        if (file.isEmpty()) {

            System.out.print("文件为空！");
        }
        //实体类集合
        List<People> peopleList = new ArrayList<>();
        try{
            //根据路径获取这个操作excel的实例
            HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
            //根据页面index 获取sheet页(工作表)
            HSSFSheet sheet = wb.getSheetAt(0);

            HSSFRow row = null;
            //循环sesheet页中数据从第二行开始，第一行是标题
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                //获取每一行数据
                row = sheet.getRow(i);
                People people = new People();
                people.setName(row.getCell(1).getStringCellValue());
                people.setSolarCalendarBirthday(row.getCell(2).getStringCellValue());
                people.setLunarCalendarBirthday(row.getCell(3).getStringCellValue());
                peopleList.add(people);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peopleList;
    }
}
