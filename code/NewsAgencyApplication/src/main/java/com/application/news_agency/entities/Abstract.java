package com.application.news_agency.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abstracts")
public class Abstract {
	@Id
	private int id;
	@Column(name = "abstract_location_file")
	private String abstractLocationFileName;
	
}
