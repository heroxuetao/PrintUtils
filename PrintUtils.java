package com.restaurant.diandian.merchant.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class PrintUtils {

    /**
     * 打印纸一行最大的字节
     */
    private static final int LINE_BYTE_SIZE = 32;

    private static final int LEFT_LENGTH = 20;

    private static final int RIGHT_LENGTH = 12;

    /**
     * 左侧汉字最多显示几个文字
     */
    private static final int LEFT_TEXT_MAX_LENGTH = 8;

    /**
     * 小票打印菜品的名称，上限调到8个字
     */
    public static final int MEAL_NAME_MAX_LENGTH = 8;

    private static OutputStream outputStream = null;

    public static OutputStream getOutputStream() {
        return outputStream;
    }

    public static void setOutputStream(OutputStream outputStream) {
        PrintUtils.outputStream = outputStream;
    }


    /**
     * 打印文字
     *
     * @param text 要打印的文字
     */
    public static void printText(String text) {
        try {
            byte[] data = text.getBytes("gbk");
            outputStream.write(data, 0, data.length);
            outputStream.flush();
        } catch (IOException e) {
            //Toast.makeText(this.context, "发送失败！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 设置打印格式
     *
     * @param command 格式指令
     */
    public static void selectCommand(byte[] command) {
        try {
            outputStream.write(command);
            outputStream.flush();
        } catch (IOException e) {
            //Toast.makeText(this.context, "发送失败！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 复位打印机
     */
    public static final byte[] RESET = {0x1b, 0x40};

    /**
     * 左对齐
     */
    public static final byte[] ALIGN_LEFT = {0x1b, 0x61, 0x00};

    /**
     * 中间对齐
     */
    public static final byte[] ALIGN_CENTER = {0x1b, 0x61, 0x01};

    /**
     * 右对齐
     */
    public static final byte[] ALIGN_RIGHT = {0x1b, 0x61, 0x02};

    /**
     * 选择加粗模式
     */
    public static final byte[] BOLD = {0x1b, 0x45, 0x01};

    /**
     * 取消加粗模式
     */
    public static final byte[] BOLD_CANCEL = {0x1b, 0x45, 0x00};

    /**
     * 宽高加倍
     */
    public static final byte[] DOUBLE_HEIGHT_WIDTH = {0x1d, 0x21, 0x11};

    /**
     * 宽加倍
     */
    public static final byte[] DOUBLE_WIDTH = {0x1d, 0x21, 0x10};

    /**
     * 高加倍
     */
    public static final byte[] DOUBLE_HEIGHT = {0x1d, 0x21, 0x01};

    /**
     * 字体不放大
     */
    public static final byte[] NORMAL = {0x1d, 0x21, 0x00};

    /**
     * 设置默认行间距
     */
    public static final byte[] LINE_SPACING_DEFAULT = {0x1b, 0x32};

    /**
     * 设置行间距
     */
//	public static final byte[] LINE_SPACING = {0x1b, 0x32};//{0x1b, 0x33, 0x14};  // 20的行间距（0，255）


//	final byte[][] byteCommands = {
//			{ 0x1b, 0x61, 0x00 }, // 左对齐
//			{ 0x1b, 0x61, 0x01 }, // 中间对齐
//			{ 0x1b, 0x61, 0x02 }, // 右对齐
//			{ 0x1b, 0x40 },// 复位打印机
//			{ 0x1b, 0x4d, 0x00 },// 标准ASCII字体
//			{ 0x1b, 0x4d, 0x01 },// 压缩ASCII字体
//			{ 0x1d, 0x21, 0x00 },// 字体不放大
//			{ 0x1d, 0x21, 0x11 },// 宽高加倍
//			{ 0x1b, 0x45, 0x00 },// 取消加粗模式
//			{ 0x1b, 0x45, 0x01 },// 选择加粗模式
//			{ 0x1b, 0x7b, 0x00 },// 取消倒置打印
//			{ 0x1b, 0x7b, 0x01 },// 选择倒置打印
//			{ 0x1d, 0x42, 0x00 },// 取消黑白反显
//			{ 0x1d, 0x42, 0x01 },// 选择黑白反显
//			{ 0x1b, 0x56, 0x00 },// 取消顺时针旋转90°
//			{ 0x1b, 0x56, 0x01 },// 选择顺时针旋转90°
//	};

    /**
     * 打印两列
     *
     * @param leftText  左侧文字
     * @param rightText 右侧文字
     * @return
     */
    @SuppressLint("NewApi")
    public static String printTwoData(String leftText, String rightText) {
        StringBuilder sb = new StringBuilder();
        int leftTextLength = getBytesLength(leftText);
        int rightTextLength = getBytesLength(rightText);
        sb.append(leftText);

        // 计算两侧文字中间的空格
        int marginBetweenMiddleAndRight = LINE_BYTE_SIZE - leftTextLength - rightTextLength;

        for (int i = 0; i < marginBetweenMiddleAndRight; i++) {
            sb.append(" ");
        }
        sb.append(rightText);
        return sb.toString();
    }

    /**
     * 打印三列
     *
     * @param leftText   左侧文字
     * @param middleText 中间文字
     * @param rightText  右侧文字
     * @return
     */
    @SuppressLint("NewApi")
    public static String printThreeData(String leftText, String middleText, String rightText) {
        StringBuilder sb = new StringBuilder();
        // 左边最多显示 LEFT_TEXT_MAX_LENGTH 个汉字 + 两个点
        if (leftText.length() > LEFT_TEXT_MAX_LENGTH) {
            leftText = leftText.substring(0, LEFT_TEXT_MAX_LENGTH) + "..";
        }
        int leftTextLength = getBytesLength(leftText);
        int middleTextLength = getBytesLength(middleText);
        int rightTextLength = getBytesLength(rightText);

        sb.append(leftText);
        // 计算左侧文字和中间文字的空格长度
        int marginBetweenLeftAndMiddle = LEFT_LENGTH - leftTextLength - middleTextLength / 2;

        for (int i = 0; i < marginBetweenLeftAndMiddle; i++) {
            sb.append(" ");
        }
        sb.append(middleText);

        // 计算右侧文字和中间文字的空格长度
        int marginBetweenMiddleAndRight = RIGHT_LENGTH - middleTextLength / 2 - rightTextLength;

        for (int i = 0; i < marginBetweenMiddleAndRight; i++) {
            sb.append(" ");
        }

        // 打印的时候发现，最右边的文字总是偏右一个字符，所以需要删除一个空格
        sb.delete(sb.length() - 1, sb.length()).append(rightText);
        return sb.toString();
    }

    /**
     * 获取数据长度
     *
     * @param msg
     * @return
     */
    @SuppressLint("NewApi")
    private static int getBytesLength(String msg) {
        return msg.getBytes(Charset.forName("GB2312")).length;
    }

    /**
     * 格式化菜品名称，最多显示MEAL_NAME_MAX_LENGTH个数
     *
     * @param name
     * @return
     */
    public static String formatMealName(String name) {
        if (TextUtils.isEmpty(name)) {
            return name;
        }
        if (name.length() > MEAL_NAME_MAX_LENGTH) {
            return name.substring(0, 8) + "..";
        }
        return name;
    }

}
