package servidor.CargaDatos;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Vector;

import servidor.SingletonConexion;
import servidor.GestionarEntidad.ControlEntidad;
import servidor.GestionarMarca.ControlMarca;
import servidor.GestionarModelo.ControlModelo;
import servidor.GestionarOrden.ControlOrden;
import servidor.GestionarReclamante.ControlReclamante;
import servidor.GestionarReclamo.ControlReclamo;
import servidor.GestionarRecurso.ControlRecurso;
import servidor.GestionarRegistrante.ControlRegistrante;
import servidor.GestionarUsuario.ControlUsuario;
import servidor.GestionarVehiculo.ControlVehiculo;
import common.DTOs.EntidadDTO;
import common.DTOs.MarcaDTO;
import common.DTOs.ModeloDTO;
import common.DTOs.OrdenDTO;
import common.DTOs.ReclamanteDTO;
import common.DTOs.ReclamoDTO;
import common.DTOs.RecursoDTO;
import common.DTOs.RegistranteDTO;
import common.DTOs.UsuarioDTO;
import common.DTOs.VehiculoDTO;

public class CargaDatos {

	
	
	public static void main(String[] args) {

		try {
			// Creando, seteando e inicializando el Servidor
			SingletonConexion c = new SingletonConexion();

			//CARGA DEL PRIMER USUARIO
			cargarPrimerUsuario();
			
			//CARGA DE MARCAS Y MODELOS
			cargarMarcas_Modelos();
			
			// CARGA DE ENTIDADES DE TAGLE
			cargarEntidades();

/*		
			ControlOrden corden = new ControlOrden();
			OrdenDTO orden = new OrdenDTO();
			orden.setFecha_apertura(null);
			orden.setEstado("SIN RECLAMO");
			orden.setId(new Long(0));
			orden.setNumero_orden("1234");
			orden.setFecha_cierre(null);
			orden.setRecurso(null);
			
			try{
				corden.agregarOrden(orden);
			}catch(Exception e){
				System.out.println("no cargo orden");
				e.printStackTrace();
			}

			
			ControlRecurso crecurso = new ControlRecurso();
			Vector<RecursoDTO> recursos = crecurso.obtenerRecursos();
			System.out.println(recursos.isEmpty());
*/			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void cargarPrimerUsuario(){
		ControlUsuario cusuario;
		try {
			cusuario = new ControlUsuario();
			UsuarioDTO usuario = new UsuarioDTO("Admin", "it10", "admin@it10.com", "Administrativo");
			cusuario.agregarUsuario(usuario);
		} catch (Exception e) {
			System.out.println("Error al cargar el primer usuario");
			e.printStackTrace();
		}

	}
	
	public static void cargarMarcas_Modelos(){
		try {
			ControlMarca cmarca = new ControlMarca();
			ControlModelo cmodelo = new ControlModelo();

			MarcaDTO marca1 = new MarcaDTO("RENAULT");
			marca1.setId(cmarca.agregarMarca(marca1));
			
			MarcaDTO marca2 = new MarcaDTO("NISSAN");
			marca2.setId(cmarca.agregarMarca(marca2));
	
			ModeloDTO modelo = new ModeloDTO("CLIO MIO", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("SANDERO", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("SANDERO STEPWAY ", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("SANDERO GT LINE", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("LOGAN", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("SYMBOL", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("KANGOO 2", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("FLUENCE", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("MEGANE III", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("DUSTER", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("KOLEOS", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("LATITUDE", marca1);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("MARCH", marca2);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("VERSA", marca2);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("TIIDA", marca2);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("SENTRA", marca2);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("370 Z", marca2);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("MURANO", marca2);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("X-TRAIL", marca2);
			cmodelo.agregarModelo(modelo);
			
			modelo = new ModeloDTO("NP 300", marca2);
			cmodelo.agregarModelo(modelo);

			modelo = new ModeloDTO("FRONTIER", marca2);
			cmodelo.agregarModelo(modelo);
		} catch (Exception e) {
			System.out.println("Error al cargar los modelos");
			e.printStackTrace();
		}
	}

	public static void cargarEntidades(){
		try {
			ControlEntidad centidad = new ControlEntidad();
			EntidadDTO entidad = new EntidadDTO("RENAULT");
			centidad.agregarEntidad(entidad);
			entidad = new EntidadDTO("NISSAN");
			centidad.agregarEntidad(entidad);
		} catch (Exception e) {
			System.out.println("Error al cargar entidades");
			e.printStackTrace();
		}

	}

}
