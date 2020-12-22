package co.micol.notice.vo;

import java.sql.Date;

public class NoticeVo {
	private int noticeId;
	private String noticeWriter;
	private String noticeTitle;
	private String noticeContent;
	private String noticeAttech;
	private int noticeHit;
	private Date noticeDate;
	
	public NoticeVo() {
		// TODO Auto-generated constructor stub
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public int getNoticeHit() {
		return noticeHit;
	}

	public void setNoticeHit(int noticeHit) {
		this.noticeHit = noticeHit;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeAttech() {
		return noticeAttech;
	}

	public void setNoticeAttech(String noticeAttech) {
		this.noticeAttech = noticeAttech;
	}
		
}
