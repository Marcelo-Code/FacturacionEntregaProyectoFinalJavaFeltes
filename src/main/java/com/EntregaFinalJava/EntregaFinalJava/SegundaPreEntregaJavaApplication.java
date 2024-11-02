package com.EntregaFinalJava.EntregaFinalJava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.EntregaFinalJava.EntregaFinalJava.Model.Auto;
import com.EntregaFinalJava.EntregaFinalJava.Model.Categoria;
import com.EntregaFinalJava.EntregaFinalJava.Model.Usuario;
import com.EntregaFinalJava.EntregaFinalJava.Service.AutoService;
import com.EntregaFinalJava.EntregaFinalJava.Service.CategoriaService;
import com.EntregaFinalJava.EntregaFinalJava.Service.UsuarioService;

@SpringBootApplication
public class SegundaPreEntregaJavaApplication implements CommandLineRunner {

	@Autowired
	AutoService autoService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	CategoriaService categoriaService;

	public static void main(String[] args) {
		SpringApplication.run(SegundaPreEntregaJavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Definimos Categorías

		Categoria c1 = new Categoria("Sedán", "Auto de tres volúmenes, cómodo y espacioso para familias.");
		Categoria c2 = new Categoria("SUV", "Vehículo alto y robusto, versátil para diversos terrenos.");
		Categoria c3 = new Categoria("Hatchback", "Auto compacto con cinco puertas, ideal para ciudad.");
		Categoria c4 = new Categoria("Deportivo", "Auto enfocado en velocidad y rendimiento.");
		Categoria c5 = new Categoria("Camioneta (Pickup)",
				"Vehículo con cabina y área de carga, útil para trabajo y ocio.");
		Categoria c6 = new Categoria("Coupé", "Auto de dos puertas y diseño elegante, con un enfoque en el estilo.");
		Categoria c7 = new Categoria("Convertible", "Auto descapotable, ideal para disfrutar del aire libre.");
		Categoria c8 = new Categoria("Monovolumen",
				"Vehículo espacioso con múltiples asientos, perfecto para familias grandes.");
		Categoria c9 = new Categoria("Compacto", "Auto pequeño y eficiente, fácil de manejar y estacionar.");
		Categoria c10 = new Categoria("Todoterreno",
				"Vehículo diseñado para manejar terrenos difíciles, como barro y rocas.");

		// Definimos usuarios

		Usuario u1 = new Usuario("Juan Perez", "juanPerez@gmail.com", "12345678");
		Usuario u2 = new Usuario("Maria Lopez", "mariaLopez@hotmail.com", "23456789");
		Usuario u3 = new Usuario("Carlos Garcia", "carlosGarcia@yahoo.com", "34567890");
		Usuario u4 = new Usuario("Laura Martinez", "lauraMartinez@gmail.com", "45678901");
		Usuario u5 = new Usuario("Luis Hernandez", "luisHernandez@outlook.com", "56789012");
		Usuario u6 = new Usuario("Ana Torres", "anaTorres@gmail.com", "67890123");
		Usuario u7 = new Usuario("Pedro Sanchez", "pedroSanchez@yahoo.com", "78901234");
		Usuario u8 = new Usuario("Sofia Ramirez", "sofiaRamirez@hotmail.com", "89012345");
		Usuario u9 = new Usuario("Diego Morales", "diegoMorales@gmail.com", "90123456");
		Usuario u10 = new Usuario("Lucia Jimenez", "luciaJimenez@outlook.com", "01234567");

		// Damos de alta usuarios

		this.usuarioService.createUser(u1);
		this.usuarioService.createUser(u2);
		this.usuarioService.createUser(u3);
		this.usuarioService.createUser(u4);
		this.usuarioService.createUser(u5);
		this.usuarioService.createUser(u6);
		this.usuarioService.createUser(u7);
		this.usuarioService.createUser(u8);
		this.usuarioService.createUser(u9);
		this.usuarioService.createUser(u10);

		// Damos de alta categorías en SQL

		this.categoriaService.createCategory(c1);
		this.categoriaService.createCategory(c2);
		this.categoriaService.createCategory(c3);
		this.categoriaService.createCategory(c4);
		this.categoriaService.createCategory(c5);
		this.categoriaService.createCategory(c6);
		this.categoriaService.createCategory(c7);
		this.categoriaService.createCategory(c8);
		this.categoriaService.createCategory(c9);
		this.categoriaService.createCategory(c10);

		// Definimos autos

		Auto a1 = new Auto("Toyota", "Corolla", 2020, 20000.00, c1, u1);
		Auto a2 = new Auto("Ford", "Focus", 2019, 18000.00, c1, u2);
		Auto a3 = new Auto("Volkswagen", "Vento", 2021, 22000.00, c1, u3);
		Auto a4 = new Auto("Chevrolet", "Onix", 2022, 16000.00, c1, u4);
		Auto a5 = new Auto("Honda", "CR-V", 2021, 30000.00, c2, u5);
		Auto a6 = new Auto("Nissan", "Kicks", 2020, 25000.00, c2, u6);
		Auto a7 = new Auto("Peugeot", "3008", 2022, 35000.00, c2, u7);
		Auto a8 = new Auto("Jeep", "Compass", 2023, 40000.00, c2, u8);
		Auto a9 = new Auto("Volkswagen", "Polo", 2020, 15000.00, c3, u9);
		Auto a10 = new Auto("Renault", "Sandero", 2019, 14000.00, c3, u10);
		Auto a11 = new Auto("Fiat", "Argo", 2021, 15500.00, c3, u1);
		Auto a12 = new Auto("Toyota", "Yaris", 2022, 17500.00, c3, u2);
		Auto a13 = new Auto("Ford", "Mustang", 2021, 45000.00, c4, u3);
		Auto a14 = new Auto("Chevrolet", "Camaro", 2020, 48000.00, c4, u4);
		Auto a15 = new Auto("Porsche", "911", 2022, 90000.00, c4, u5);
		Auto a16 = new Auto("Nissan", "Z", 2023, 60000.00, c4, u6);
		Auto a17 = new Auto("Toyota", "Hilux", 2021, 40000.00, c5, u7);
		Auto a18 = new Auto("Ford", "Ranger", 2022, 38000.00, c5, u8);
		Auto a19 = new Auto("Chevrolet", "S10", 2020, 35000.00, c5, u9);
		Auto a20 = new Auto("Volkswagen", "Amarok", 2021, 42000.00, c5, u10);
		Auto a21 = new Auto("Audi", "A5", 2022, 50000.00, c6, u1);
		Auto a22 = new Auto("Mercedes-Benz", "C-Class", 2021, 55000.00, c6, u2);
		Auto a23 = new Auto("BMW", "Serie 4", 2023, 60000.00, c6, u3);
		Auto a24 = new Auto("Volkswagen", "Scirocco", 2019, 25000.00, c6, u4);
		Auto a25 = new Auto("Mini", "Cooper", 2020, 30000.00, c7, u5);
		Auto a26 = new Auto("Fiat", "500", 2021, 25000.00, c7, u6);
		Auto a27 = new Auto("Mazda", "MX-5", 2022, 35000.00, c7, u7);
		Auto a28 = new Auto("BMW", "Z4", 2023, 65000.00, c7, u8);
		Auto a29 = new Auto("Renault", "Kangoo", 2021, 20000.00, c8, u9);
		Auto a30 = new Auto("Peugeot", "Partner", 2022, 21000.00, c8, u10);

		// Damos de alta autos en SQL

		this.autoService.createCar(a1);
		this.autoService.createCar(a2);
		this.autoService.createCar(a3);
		this.autoService.createCar(a4);
		this.autoService.createCar(a5);
		this.autoService.createCar(a6);
		this.autoService.createCar(a7);
		this.autoService.createCar(a8);
		this.autoService.createCar(a9);
		this.autoService.createCar(a10);
		this.autoService.createCar(a11);
		this.autoService.createCar(a12);
		this.autoService.createCar(a13);
		this.autoService.createCar(a14);
		this.autoService.createCar(a15);
		this.autoService.createCar(a16);
		this.autoService.createCar(a17);
		this.autoService.createCar(a18);
		this.autoService.createCar(a19);
		this.autoService.createCar(a20);
		this.autoService.createCar(a21);
		this.autoService.createCar(a22);
		this.autoService.createCar(a23);
		this.autoService.createCar(a24);
		this.autoService.createCar(a25);
		this.autoService.createCar(a26);
		this.autoService.createCar(a27);
		this.autoService.createCar(a28);
		this.autoService.createCar(a29);
		this.autoService.createCar(a30);
	}
}