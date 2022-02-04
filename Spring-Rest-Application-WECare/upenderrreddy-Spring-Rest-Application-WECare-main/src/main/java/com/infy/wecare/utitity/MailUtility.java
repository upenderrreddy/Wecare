package com.infy.wecare.utitity;


import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class MailUtility {
	@Autowired
	private JavaMailSender javaMailSender;
	public static int noOfQuickServiceThreads = 20;
	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads);
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public void sendSchedulingEmail(String userName, String coachName, String email, int bookingId, String bookingSlot,
			LocalDate appointmentDate) {
		logger.info("Inside sendEmail1() method of {}", this.getClass());
		quickService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					SimpleMailMessage msg = new SimpleMailMessage();
					msg.setTo(email);
					msg.setSubject("Your appointment with booking id " + bookingId + " has been successfully scheduled.");
					msg.setText("Dear " + userName + " \nYour appointment with " + coachName
							+ " has been scheduled successfully. \nYou can visit your coach any time from "
							+ bookingSlot + " on " + appointmentDate + ". \n \n \nThanks and Regards \nTeam WeCARE");
					javaMailSender.send(msg);
					logger.info("Booking : {} has been actually sent",bookingId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		logger.info("Booking : {} has been sent",bookingId);
	}
	public void sendCancellingEmail(String userName, String coachName, String email, int bookingId, String bookingSlot,
			LocalDate appointmentDate) {
		logger.info("Inside sendEmail2() method of {}", this.getClass());
		quickService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					SimpleMailMessage msg = new SimpleMailMessage();
					msg.setTo(email);
					msg.setSubject(
							"Your appointment with booking id " + bookingId + " has been successfully cancelled.");
					msg.setText("Dear " + userName + " \nYour appointment with " + coachName + " from " + bookingSlot
							+ " on " + appointmentDate
							+ " has been cancelled successfully. \n \n \nThanks and Regards \nTeam WeCARE");
					javaMailSender.send(msg);
					logger.info("Cancel : {} has been actually sent",bookingId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		logger.info("Cancel : {} has been sent",bookingId);
	}
	public void sendReschedulingEmail(String userName, String coachName, String email, int bookingId,
			String bookingSlot, LocalDate appointmentDate) {
		logger.info("Inside sendEmail3() method of {}", this.getClass());
		quickService.submit(new Runnable() {
			@Override
			public void run() {
				try {
					SimpleMailMessage msg = new SimpleMailMessage();
					msg.setTo(email);
					msg.setSubject(
							"Your appointment with booking id " + bookingId + " has been successfully rescheduled.");
					msg.setText("Dear " + userName + " \nYour appointment with " + coachName
							+ " has been rescheduled successfully for " + bookingSlot + " on " + appointmentDate
							+ ". \n \n \nThanks and Regards \nTeam WeCARE");
					javaMailSender.send(msg);
					logger.info("Reschedule : {} has been actually sent",bookingId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		logger.info("Reschedule : {} has been sent",bookingId);
	}
}
