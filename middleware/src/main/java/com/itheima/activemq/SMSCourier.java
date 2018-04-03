package com.itheima.activemq;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

import com.aliyuncs.exceptions.ClientException;

/**
 * ClassName:SMSCourier <br/>
 * Function: <br/>
 * Date: 2018年4月2日 下午4:55:07 <br/>
 */
@Component
public class SMSCourier implements MessageListener {

  
    @Override
    public void onMessage(Message message) {
        
        try {
            MapMessage mapMessage = (MapMessage) message;
            String telephone = mapMessage.getString("tel");
            String name = mapMessage.getString("name");
            String address = mapMessage.getString("address");
            String customer = mapMessage.getString("customer");
            System.out.println("已发送短信通知快递员"+customer+":"+address);
            //SmsCourierUtils.sendSms(telephone, name, address, customer);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
