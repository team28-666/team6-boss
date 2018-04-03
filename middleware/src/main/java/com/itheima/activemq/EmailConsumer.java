package com.itheima.activemq;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

import com.itheima.utils.NetMailUtils;

@Component
public class EmailConsumer implements MessageListener{

	@Override
	public void onMessage(Message msg) {
        MapMessage mapMessage = (MapMessage) msg;
		try {
			String email = mapMessage.getString("email");
			String emailBody = mapMessage.getString("emailBody");
			/*发送本地邮件*/
			//MailUtils.sendMail(email, "激活邮件", emailBody);
			/*发送在线邮件*/
			NetMailUtils.sendEmil(email, "激活邮件", emailBody);
			System.out.println("MQ發送正常");
		} catch (JMSException e) {
			e.printStackTrace();
			System.out.println("MQ發送異常");
		}
	}

}
