### 人员生日信息的Excel导出、导入

##### 0x01 人员生日信息导出

###### 1.1 后端接口实现

后端实现主要是将查询到的人员生日信息数据集合转为可以下载的ResponseEntity<byte[]>，“src\main\java\org\sang\component\PoiUtils.java”里的代码如下：

```
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
```
配置完成后，在下载请求接口中调用该方法即可“src\main\java\org\sang\controller\PeopleController.java里的代码如下：

```
    @GetMapping("/exportPeople")
    public ResponseEntity<byte []> exportPeople() {
        return PoiUtils.exportPeople2Excel(peopleService.getAllPeople());
    }
```

###### 1.2 前端实现

前端实现较为简单，在peopleControlPanel.html增加以下代码：

```
    <div style="width:200px;height:100%;">
        <button class="btn btn-primary form-control" style="height:50px"><a th:href="@{'/exportPeople'}">导出用户</a></button>
    </div>

```
当用户点击“导出用户”按钮时，即可向服务器发起“GET exportPeople”请求，下载文件。

注：也可在浏览器直接访问“localhost:8089/exportPeople”。

##### 0x02 人员生日信息导入

###### 2.1 后端接口实现

对于前段而言，生日信息导入就是文件上传，对于后端而言，则是获取上传的文件进行解析，并将解析出来的数据保存到数据库。

后端主要是获取前端上传文件的流，然后进行解析，“src\main\java\org\sang\component\PoiUtils.java”里的代码如下：

```
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
```

配置完成后，在下载请求接口中调用该方法即可“src\main\java\org\sang\controller\PeopleController.java里的代码如下：

```
    @RequestMapping(value = "/importPeople", method = RequestMethod.POST)
    public String importPeople(MultipartFile file){
        List <People>peopleList = PoiUtils.importPeople2List(file);
        System.out.print("peopleList.size() is" + peopleList.size());
        for(int i = 0; i < peopleList.size();i++)
        {
            peopleService.addPeople(peopleList.get(i));
        }
        return "0";
    }

```

###### 2.2 前端实现

前端主要是一个Excel表格的上传，在peopleControlPanel.html增加以下代码：

```
    <div>
        <form enctype="multipart/form-data" method="post" action="/importPeople">    
            请选择包含人物信息的Excel表格：<input type="file" name="file"/><br/>    
            <input type="submit" value="上传"/>
        </form>
    </div>
```

##### 0x03 小结

在本文介绍了如何在Spring Boot中进行简单的Excel导入、导出操作。虽然代码可以实现基本功能，但尚未做下面工作（将在后面的博文中进行修复）：

1. Excel文件内容校验
2. 接口访问次数限制

##### 0x04 参考资料

* [spring boot 导入excel数据到mysql](https://www.javatt.com/p/28620)
