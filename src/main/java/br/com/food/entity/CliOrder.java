package br.com.food.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "cliOrder")
public class CliOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4460956101928420476L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clientOrden_id")
	private int id;

	private String date;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ordenitem_id")
	private List<OrderItem> ordemItems;

	private String deliveryAddress;

	private String contact;

	private double total;

	private String status;

	private String lastUpdate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<OrderItem> getOrdemItems() {
		return ordemItems;
	}

	public void setOrdemItems(List<OrderItem> ordemItems) {
		this.ordemItems = ordemItems;
	}

	public CliOrder() {
	}

	@Override
	public String toString() {
		return "ClientOrden [id=" + id + ", date=" + date + ", customer=" + customer + ", ordemItems=" + ordemItems
				+ ", deliveryAddress=" + deliveryAddress + ", contact=" + contact + ", total=" + total + ", status="
				+ status + ", lastUpdate=" + lastUpdate + "]";
	}

}
