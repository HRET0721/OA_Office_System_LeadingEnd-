package com.breath.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hret.entity.utils.inform.JavaMailUntil;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class MqListener {
    @Autowired
    private JavaMailUntil javaMailUntil;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "shifts.lazy.queue", durable = "true"),
            exchange = @Exchange(name = "shifts.lazy.direct", type = ExchangeTypes.DIRECT),
            key = {"shifts.lazy"}
    ))
    public void listenShiftsDirect(String msg) throws InterruptedException, MessagingException {

        System.out.println("消费者addAdShiftsMq.direct 收到了 addAdShiftsMq.queue的消息：【" + msg +"】");
        log.info("发送邮件");
        //	创建Session会话
        Session session = JavaMailUntil.createSession();

        //	创建邮件对象
        MimeMessage message = new MimeMessage(session);
        message.setSubject("sendEmailTestDemo1");
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = formatter.format(now);

        message.setText("Hello, breath\n\nyou have one error in 'insert into ad_shifts '\n\nCurrent Time: " + currentTime);
        message.setFrom(new InternetAddress("w521shuiwuyue@163.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("3425167158@qq.com"));

        //	发送
        Transport.send(message);
    }


}
