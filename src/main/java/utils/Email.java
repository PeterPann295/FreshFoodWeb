package utils;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import model.Customer;
import model.Order;


public class Email {
    // Email: tungletest1.email@gmail.com
    // Password: nebeekfipcstxcox
    static final String from = "21130354@st.hcmuaf.edu.vn";
    static final String password = "hlnfcwgpwaixelic";

    public static boolean sendEmail(String to, String tieuDe, String noiDung) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // Tiêu đề email
            msg.setSubject(tieuDe);

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Quy định email nhận phản hồi
            // msg.setReplyTo(InternetAddress.parse(from, false))

            // Nội dung

            MimeMultipart multipart = new MimeMultipart();

            // Phần văn bản của email


            // Phần HTML của email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h4>Xin chào bạn </h4>" + "\r\n" + "<p> " + noiDung + "</p>" +  " </body></html>";
            htmlPart.setContent(htmlContent, "text/HTML; charset=UTF-8");

            // Thêm các phần vào MimeMultipart
            multipart.addBodyPart(htmlPart);


            msg.setContent(multipart, "text/HTML; charset=UTF-8");

            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendEmaiQuenMatKhau(Customer customer, String resetCode) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getEmail(), false));

            // Tiêu đề email
            msg.setSubject("Quên Mật Khẩu");

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Quy định email nhận phản hồi
            // msg.setReplyTo(InternetAddress.parse(from, false))

            // Nội dung

            MimeMultipart multipart = new MimeMultipart();

            // Phần văn bản của email


            // Phần HTML của email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h4>Xin chào " + customer.getFullName() + " </h4>" + "\r\n" + "<p> " + " Mã xác nhận là " + resetCode + "." + "</p>" + "\r\n" + "Chúc bạn một ngày vui vẻ," +  " </body></html>";
            htmlPart.setContent(htmlContent, "text/HTML; charset=UTF-8");

            // Thêm các phần vào MimeMultipart
            multipart.addBodyPart(htmlPart);


            msg.setContent(multipart, "text/HTML; charset=UTF-8");

            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }

    //	public static boolean sendEmailXacNhanDonHang(String to, String noiDung) {
//		// Properties : khai báo các thuộc tính
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
//		props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//
//		// create Authenticator
//		Authenticator auth = new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				// TODO Auto-generated method stub
//				return new PasswordAuthentication(from, password);
//			}
//		};
//
//		// Phiên làm việc
//		Session session = Session.getInstance(props, auth);
//
//		// Tạo một tin nhắn
//		MimeMessage msg = new MimeMessage(session);
//
//		try {
//			// Kiểu nội dung
//			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//
//			// Người gửi
//			msg.setFrom(from);
//
//			// Người nhận
//			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
//
//			// Tiêu đề email
//			msg.setSubject("Xác Nhận Mua Hàng Thành Công");
//
//			// Quy đinh ngày gửi
//			msg.setSentDate(new Date());
//
//			// Quy định email nhận phản hồi
//			// msg.setReplyTo(InternetAddress.parse(from, false))
//
//			// Nội dung
//			msg.setContent(noiDung, "text/HTML; charset=UTF-8");
//
//			// Gửi email
//			Transport.send(msg);
//			System.out.println("Gửi email thành công");
//			return true;
//		} catch (Exception e) {
//			System.out.println("Gặp lỗi trong quá trình gửi email");
//			e.printStackTrace();
//			return false;
//		}
//	}
//
    public static boolean sendEmailXacNhanDonHang(Order order) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(order.getCustomer().getEmail(), false));

            // Tiêu đề email
            msg.setSubject("Xác Nhận Mua Hàng Thành Công");

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Quy định email nhận phản hồi
            // msg.setReplyTo(InternetAddress.parse(from, false))

            // Nội dung
            MimeMultipart multipart = new MimeMultipart();

            // Phần văn bản của email


            // Phần HTML của email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h4>Xin chào " + order.getCustomer().getFullName() + " </h4>" + "\r\n" + "<p> " + "Cảm ơn bạn đã mua hàng bên chúng tôi"+ "." + "\n" + StringFilter.xacThucDonHang(order) + "</p>" + "\r\n" + "Chú bạn một ngày vui vẻ," +  " </body></html>";
            htmlPart.setContent(htmlContent, "text/HTML; charset=UTF-8");

            // Thêm các phần vào MimeMultipart
            multipart.addBodyPart(htmlPart);


            msg.setContent(multipart, "text/HTML; charset=UTF-8");
            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {


    }

}