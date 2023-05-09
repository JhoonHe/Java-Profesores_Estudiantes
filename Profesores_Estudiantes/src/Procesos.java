import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Procesos {

	ArrayList<String> listaProfesores;
	ArrayList<String> listaEstudiantes;
	ArrayList<ArrayList<String>> listaGeneralEstudiantes;

	public Procesos() {
		listaProfesores = new ArrayList<String>();
		listaGeneralEstudiantes = new ArrayList<>();
	}

	public void iniciar() {
		
		String menu = obtenerMenu();
		int opcion = 0;
		
		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(menu)); 
			validarOpcion(opcion);
		}while(opcion != 7);
	}

	private String obtenerMenu() {

		String menu = "";

		menu += "***** Bienvenido *****";
		menu += "\nPor favor ingrese la opcion deseada:\n";
		menu += "\n1- Registrar profesores";
		menu += "\n2- Registrar estudiantes asociados al profesor";
		menu += "\n3- Consultar lista total de profesores y sus estudiantes asociados";
		menu += "\n4- Consultar un profesor e imprimir la lista de estudiantes asociados";
		menu += "\n5- Consultar un profesor e indicar la cantidad de estudiantes asociados";
		menu += "\n6- Consultar un estudiante e indicar cual es su director de grupo";
		menu += "\n7- Salir\n\n";
		
		return menu;
	}
	
	private void validarOpcion(int opcion) {
		
		switch (opcion) {
			
		case 1: registrarProfesores();
				break;
		
		case 2: if (!listaProfesores.isEmpty()) {
					registrarEstudiantes();
					break;
				}
				System.out.println("No se encuentran profesores registrados");
				break;
			
		case 3: if (!listaProfesores.isEmpty()) {
					if (listaEstudiantes == null) {						
						System.out.println("No hay estudiantes registrados");
						break;
					}
					consultarListaProfesoresYEstudiantes();
					break;
				}
				System.out.println("No se encuentran profesores registrados");
				break;
			
		case 4: if (!listaProfesores.isEmpty()) {
					if (listaEstudiantes == null) {
						System.out.println("No hay estudiantes registrados");
						break;
					}
					consultaProfesorPorNombre();
					break;
				}
				System.out.println("No se encuentran profesores registrados");
				break;
		
		case 5: if (!listaProfesores.isEmpty()) {
					if(listaEstudiantes == null) {
						System.out.println("No hay estudiantes registrados");
						break;
					}
					consultaCantidadEstudiantesPorProfesor();
					break;
				}
				System.out.println("No se encuentran profesores registrados");
				break;
			
		case 6: if (!listaProfesores.isEmpty()) {
					if (listaEstudiantes == null) {
						System.out.println("No hay estudiantes registrados");
						break;
					}
					consultarEstudiante();
					break;
				}
				System.out.println("No se encuentran profesores registrados");
				break;
			
		case 7: JOptionPane.showMessageDialog(null, "Gracias por usar el programa", "Cerrando programa", JOptionPane.CLOSED_OPTION);
				break;	
			
		default: JOptionPane.showMessageDialog(null, "Opcion incorrecta");
		
		}
	}

	private void registrarProfesores() {
		
		String nombre = "";
		
		System.out.println("Registrar profesores");
		
		int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de profesores a registar"));
		
		for (int i = 0 ; i < cantidad ; i++) {
				
			nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor " + (i+1)).toLowerCase();
				
			while(listaProfesores.contains(nombre)) {
				nombre = JOptionPane.showInputDialog("¡Nombre repetido! \nIngrese nuevamente el nombre del profesor " + (i+1)).toLowerCase();
			}
			
			listaProfesores.add(nombre);
		}
		System.out.println("Registro exitoso");
	}
	
	private void registrarEstudiantes () {
		
		String nombreEst = "";
		
		System.out.println("\n<<<< Registro de Estudiantes >>>>");
		
		for (int i = 0; i < listaProfesores.size(); i++) {

			JOptionPane.showMessageDialog(null,
					"Ingrese los estudiantes" + " para el profesor " + listaProfesores.get(i));

			listaEstudiantes = new ArrayList<String>();

			int cant = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de estudiantes"));

			for (int j = 0; j < cant; j++) {

				nombreEst = JOptionPane.showInputDialog("Ingrese el nombre del estudiante " + (j + 1)).toLowerCase();
				
				while(listaEstudiantes.contains(nombreEst)) {
					nombreEst = JOptionPane.showInputDialog("¡Nombre repetido!\nIngrese nuevamente el nombre del estudiante " + (j + 1)).toLowerCase();
				}
				
				listaEstudiantes.add(nombreEst);
			}

			listaGeneralEstudiantes.add(listaEstudiantes);

		}
		System.out.println("Registro de estudiantes Exitoso!\n");
	}
	
	private void consultarListaProfesoresYEstudiantes() {
		
		System.out.println("\n<<<< Lista de Profesores y Estudiantes >>>>");
		
		ArrayList<String> listaTemporal;

		for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {

			listaTemporal = listaGeneralEstudiantes.get(i);
			System.out.print("Profesor: " + listaProfesores.get(i) + " | Estudiantes: ");
			System.out.print("[");

			for (int j = 0; j < listaTemporal.size(); j++) {
				System.out.print(listaTemporal.get(j));

				if (j < listaTemporal.size() - 1) {
					System.out.print(",");
				}
			}
			
			System.out.print("]\n");
		}
	}
	
	private void consultaProfesorPorNombre() {
		
		System.out.println("\n<<<< Consulta de Profesor >>>>\n");
		
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del profesor").toLowerCase();

		if (listaProfesores.contains(nombre)) {

			int posicion = listaProfesores.indexOf(nombre);
			ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);

			System.out.print("Profesor: " + nombre + " = ");
			System.out.print("[");

			for (int j = 0; j < listaEstudiantesTemporal.size(); j++) {

				System.out.print(listaEstudiantesTemporal.get(j));

				if (j < listaEstudiantesTemporal.size() - 1) {
					System.out.print(",");
				}
			}
			
			System.out.print("]\n");

		} else {
			System.out.println("No se encuentra el profesor " + nombre + "\n");
		}
	}
	
	private void consultaCantidadEstudiantesPorProfesor() {

		System.out.println("\n<<<< Consulta cantidad de estudiantes de Profesores >>>> \n");
		
		String nombre = JOptionPane
				.showInputDialog("Ingrese el nombre del profesor para saber" + "la cantidad de estudiantes asociados").toLowerCase();

		if (listaProfesores.contains(nombre)) {
			int posicion = listaProfesores.indexOf(nombre);
			ArrayList<String> listaEstudiantesTemporal = listaGeneralEstudiantes.get(posicion);
			System.out.println("La cantidad de estudiantes asociados al profesor " + nombre + " es: "
					+ listaEstudiantesTemporal.size());
		} else {
			System.out.println("No se encuentra al profesor " + nombre + "\n");
		}
	}

	private void consultarEstudiante() {
		
		System.out.println("\n<<<< Consulta de Estudiante  >>>> \n");
		
		ArrayList<String> listaTemporal;

		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del estudiante a consultar").toLowerCase();

		for (int i = 0; i < listaGeneralEstudiantes.size(); i++) {

			listaTemporal = listaGeneralEstudiantes.get(i);
			
			if (listaTemporal.contains(nombre)) {

				for (int j = 0; j < listaTemporal.size(); j++) {

					if (listaTemporal.get(j).equalsIgnoreCase(nombre)) {
						System.out.println("Encuentra al nombre: " + nombre + " pertenece al " + " grupo de "
								+ listaProfesores.get(i));
					}
				}
			} else {
				System.out.println("No se encuentra el nombre en el grupo de " +listaProfesores.get(i));
			}
		}
	}

}
