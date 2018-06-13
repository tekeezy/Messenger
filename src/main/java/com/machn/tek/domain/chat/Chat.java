package com.machn.tek.domain.chat;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.springframework.data.annotation.CreatedDate;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long chatNo;
	
	@CreatedDate
    private Date reg_date;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="chatNo")
	private List<ChatUsers> chatUsers;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="chatNo")
	private List<Message> message;
	
}
