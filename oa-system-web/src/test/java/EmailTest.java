import lombok.extern.slf4j.Slf4j;
import org.hret.OaSystemApplication;
import org.hret.entity.utils.inform.JavaMailUntil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @version oa-system
 * @developer breath
 * @User breath
 * @date 2024/4/8 10:41
 */
@SpringBootTest(classes = OaSystemApplication.class)
@Slf4j
public class EmailTest {

    @Autowired
    private JavaMailUntil javaMailUntil;

    @Test
    public void sendEmail() throws MessagingException {
        log.info("发送邮件");
        //	创建Session会话
        Session session = JavaMailUntil.createSession();

        //	创建邮件对象
        MimeMessage message = new MimeMessage(session);
        message.setSubject("sendEmailTestDemo1");
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = formatter.format(now);

        message.setText("Hello, breath\n\nCurrent Time: " + currentTime);
        message.setFrom(new InternetAddress("w521shuiwuyue@163.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("3425167158@qq.com"));

        //	发送
        Transport.send(message);
    }
}
