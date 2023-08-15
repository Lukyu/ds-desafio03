package com.lucastamb.dsdesafio03.entity;

import java.time.LocalDate;
import java.util.Objects;

import com.lucastamb.dsdesafio03.dto.ClientDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	private String cpf;
	
	private Double income;
	
	@PastOrPresent
	private LocalDate birthDate;
	
	private Integer children;

	public Client(Long id, @NotBlank String name, String cpf, Double income, @PastOrPresent LocalDate birthDate, Integer children) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public Client(ClientDTO dto) {
		super();
		this.name = dto.getName();
		this.cpf = dto.getCpf();
		this.income = dto.getIncome();
		this.birthDate = dto.getBirthDate();
		this.children = dto.getChildren();
	}

	public Client() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getChildren() {
		return children;
	}

	public void setChildren(Integer children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", cpf=" + cpf + ", income=" + income + ", birthDate="
				+ birthDate + ", children=" + children + "]";
	}
	
}
