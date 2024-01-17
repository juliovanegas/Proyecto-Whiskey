package com.softux.scraping.softux;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
class Producto {

	private @Id @GeneratedValue Long id;
	private String producto;
	private String marca;
	private String presentacion;
	private String tamano;
	private String url;
	private String canal;
	private String precio;

	Producto() {
	}

	Producto(String producto, String marca, String presentacion, String tamano, String url, String canal,
			String precio) {

		this.producto = producto;
		this.marca = marca;
		this.presentacion = presentacion;
		this.tamano = tamano;
		this.url = url;
		this.canal = canal;
		this.precio = precio;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Producto))
			return false;
		Producto product = (Producto) o;
		return Objects.equals(this.id, product.id) && Objects.equals(this.producto, product.producto)
				&& Objects.equals(this.marca, product.marca) && Objects.equals(this.presentacion, product.presentacion) 
				&& Objects.equals(this.tamano, product.tamano) && Objects.equals(this.url, product.url) 
				&& Objects.equals(this.canal, product.canal)  && Objects.equals(this.precio, product.precio) ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.producto, this.marca,this.presentacion,this.tamano,this.url,this.canal,this.precio);
	}

	@Override
	public String toString() {
		return "Producto{" + "id=" + this.id + ", producto='" + this.producto + '\'' + ", marca='" + this.marca + '\'' + ", presentacion='" + this.presentacion + '\'' +
				", tamano='" + this.tamano + '\''+", canal='" + this.canal + '\''+", precio='" + this.precio + '\'' + '}';
	}
}
