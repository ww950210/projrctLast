package com.example.demo.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000116694629";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCleubG7vemSHyUhkLHuWPrHAYqdMnoRncgkOKRKPetfBjGpTUz487o3ZeTd0n4oKCgEVvea5SCufkuP3xCB1y2T6osu45UeiquiCWxDgSnBLdfjmKE277s40KHBpTogNnHcue9dhZt5gwuVtWDl+6Oa3dF4/+uvSYRYZXLg9mUR0j2Ri3TzbIToGf0V00bLbnsazhmUsm+ollgyh1UeHHFLp0uc6gjsbA4pPBQ34ks+wHZSkOM94RV3upldtqO7IDPrKwHD/yWjfejkwh91tGj7xDcL3eh0pINEQjVtoPtwTXsEmgU0N1YZ7eic6ejs19VPlizB2n3c9QD+BHcJAMNAgMBAAECggEAdFTKF9+2yXb73Y3lNHLoA0hYLVCdAGVJKeOw40thktxCrqNFm4zy0FG3VTvvDJIfWWfapaoLZTtIKXMH74MtchzfDUNs84ZOqxObK7UnICL4pnx6VdwFhJLfGf1OgYTTlJr6vv5dFjU7KvhwcWpFkmmKF+AIxtbCBEYOAFxYgRvajMUxOA5bEn90l7QMlTnNHuKiUimcLuYSVCRiuLM4e/6yDpu6X1wxaxt4/QiM1qpcuICZfU3xV6KGXtdUdYY2tInDkY1opQtKd6GPef/CISY2EjZsB+VOIFaLP0MgFg+hChyjXXf5DaPqExW+d0fj7EWKLlSaDmUqzIB4Iu6JAQKBgQDQrQHQuQhx48DlBwb6u26QBu05uC4FgtjRdqYw71w4qyBkilWcr4GppcCmzd/pcbSYKimrQ0oqnsv2Qw26w79Cucdx57KN0/4//b0htXNoAaej80p8OroFRLSr9FUyCsvXHXm6cbVImwh/cgVBbzPDPekxFIss6SdQh9JY0l6/VQKBgQDLAhm+yADBOhVCV7qJ4z7F1LDMBcgMa8h1DfnVYosf5tnpcTtgsilxQ0uSO44r8CITm9mX/QAVXHWD4pcRw6ZDI0XdjgA/q+nTGl3it8R5JIWeSzKa82yH81Hw6xRr1wwAYYYRG//DHdmZDlZi7qD5uo2HWPgJHnvZOVFN+nyE2QKBgQCNjh7iIPK6l14dvtOcOJM6vogkFG2brXQ5Ozxni6fKhUNXEIQc4u2ZUGhMfvG6oTh51Hgpxl7EZK5FYp0oqWrP78h820jDbyBU9mJbQISF9EFtNTe85/WSZ1YMI5W7eZlnVoZh1Iq+MvTjx0wLZEz/z7qlYaM7lvnlZg1p1WMSdQKBgQC051VL5myNxBWnvgez0nZg0o0N8mT3dKD4dpDYr2zs68nqNgZEv98gWGiikkG6M84aDTrVLxaA8piiAyy+HswtbeRXb7AXDTpb7VYWr838aihMDBS3cg6YA6NUfhf/lW3TdRSEcJ/1HseGlfkzRhTbFXwDTawSbEDPLT2fsIR/2QKBgB31KTkoEtMYkxdtIYrKyhXJkOKWQqcl/ZI65NOmMv1Ju3+C2G6HTx5pbmREvj/MMPoGslDE6GeNwCCBlx2APgCQrtePhgYCdTIPrbZCI+yb9zpNlHLB02zhjKaAonMEbJ7mo8qfOrSaBGXoUdLnr6v/1bvRfMcabNX4K4zAWMbb";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlQDolgnRP4yD31tbtcbEtQlGwa7MfMMs7lALNU21ph9PsM11ulnp7tmHp7v0PzHETtGtHbxvWCB8e8ZlrfbdmGs7tDpquJxT/yuvUX3Di9D5aQsDbK/deozwn9WI67gmHzgo0w85GSGJmIQ1ibpUyahkAkK5WeVMqVl7o+o3BsSo8+Zb0d/ZbrXeHiwVWrYmMFak6FaT4Trp6vALHlO3ZJEsc3jXMWvWP6BjUWbZHkROYovOcNxmOaU2cKf8vrqbZgvzSHykelD0GxcTku3Sa7AHafoQRZHQ+V4NZNoy2X7VCTVD64DuC8tX14vvl1UaZaZ9O761T+CKkQ4LF35rRwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:9999/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
	
	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

