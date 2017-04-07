# PrintUtils
Android蓝牙打印机，带你真正了解各种打印格式。

  
#### 具体请参见我的博客 http://www.jianshu.com/p/ec0cdcd62595

效果图如下：

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/1467310-b58bab95db2fda1b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

具体用法是：
（1）手机通过蓝牙连接打印机
（2）从BluetoothSocket中getOutputStream(), 然后设置一下即可，在PrintUtils中设置以下代码即可
```
    private static OutputStream outputStream = null;

    public static void setOutputStream(OutputStream outputStream) {
        PrintUtils.outputStream = outputStream;
    }
  ```
（3）打印，示例如下：
```
PrintUtils.selectCommand(PrintUtils.RESET);
PrintUtils.selectCommand(PrintUtils.LINE_SPACING_DEFAULT);
PrintUtils.selectCommand(PrintUtils.ALIGN_CENTER);
PrintUtils.printText("美食餐厅\n\n");
PrintUtils.selectCommand(PrintUtils.DOUBLE_HIGHT_WIDTH);
PrintUtils.printText("桌号：1号桌\n\n");
PrintUtils.selectCommand(PrintUtils.NORMAL);
PrintUtils.selectCommand(PrintUtils.ALIGN_LEFT);
PrintUtils.printText(PrintUtils.printTwoData("订单编号", "201507161515\n"));
PrintUtils.printText(PrintUtils.printTwoData("点菜时间", "2016-02-16 10:46\n"));
PrintUtils.printText(PrintUtils.printTwoData("上菜时间", "2016-02-16 11:46\n"));
PrintUtils.printText(PrintUtils.printTwoData("人数：2人", "收银员：张三\n"));

PrintUtils.printText("--------------------------------\n");
PrintUtils.selectCommand(PrintUtils.BOLD);
PrintUtils.printText(PrintUtils.printThreeData("项目", "数量", "金额\n"));
PrintUtils.printText("--------------------------------\n");
PrintUtils.selectCommand(PrintUtils.BOLD_CANCEL);
PrintUtils.printText(PrintUtils.printThreeData("面", "1", "0.00\n"));
PrintUtils.printText(PrintUtils.printThreeData("米饭", "1", "6.00\n"));
PrintUtils.printText(PrintUtils.printThreeData("铁板烧", "1", "26.00\n"));
PrintUtils.printText(PrintUtils.printThreeData("一个测试", "1", "226.00\n"));
PrintUtils.printText(PrintUtils.printThreeData("牛肉面啊啊", "1", "2226.00\n"));
PrintUtils.printText(PrintUtils.printThreeData("牛肉面啊啊啊牛肉面啊啊啊", "888", "98886.00\n"));

PrintUtils.printText("--------------------------------\n");
PrintUtils.printText(PrintUtils.printTwoData("合计", "53.50\n"));
PrintUtils.printText(PrintUtils.printTwoData("抹零", "3.50\n"));
PrintUtils.printText("--------------------------------\n");
PrintUtils.printText(PrintUtils.printTwoData("应收", "50.00\n"));
PrintUtils.printText("--------------------------------\n");

PrintUtils.selectCommand(PrintUtils.ALIGN_LEFT);
PrintUtils.printText("备注：不要辣、不要香菜");
PrintUtils.printText("\n\n\n\n\n");
 ```
