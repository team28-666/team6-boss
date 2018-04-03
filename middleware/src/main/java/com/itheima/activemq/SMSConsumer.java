package com.itheima.activemq;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.utils.SmsUtils;

/**
 * ClassName:SmsConsumer <br/>
 * Function: <br/>
 * Date: 2018年3月25日 上午10:23:34 <br/>
 */
@Component
public class SMSConsumer implements MessageListener {

    @Override
    public void onMessage(Message msg) {
        MapMessage mapMessage = (MapMessage) msg;
        try {
            String tel = mapMessage.getString("tel");
            String code = mapMessage.getString("code");

            System.out.println(tel + "====" + code);

            try {
				SmsUtils.sendSms(tel, code);
			} catch (ClientException e) {
				e.printStackTrace();
			}

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
