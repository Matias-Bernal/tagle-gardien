/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/
package servidor.GestionarUsuarioRepuesto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.Vector;

import servidor.Assemblers.UsuarioRepuestoAssembler;
import servidor.Persistencia.AccesoBD;
import servidor.Persistencia.Dominio.UsuarioRepuesto;
import comun.DTOs.UsuarioRepuestoDTO;
import comun.GestionarUsuarioRepuesto.IControlUsuarioRepuesto;

public class ControlUsuarioRepuesto extends UnicastRemoteObject implements IControlUsuarioRepuesto {
	
	private static final long serialVersionUID = 1L;

	public ControlUsuarioRepuesto() throws RemoteException {
		super();
	}

	@Override
	public Long agregarUsuario(UsuarioRepuestoDTO usuarioRepuestoDTO) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Long id = new Long(0);
		try {
			accesoBD.iniciarTransaccion();
			UsuarioRepuestoAssembler usuarioAssemb = new UsuarioRepuestoAssembler(accesoBD);
			UsuarioRepuesto nuevoUsuario = usuarioAssemb.getUsuarioRepuestoNuevo(usuarioRepuestoDTO);
			accesoBD.hacerPersistente(nuevoUsuario);
			id = nuevoUsuario.getId();
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return id;
	}

	@Override
	public void eliminarUsuario(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			UsuarioRepuestoAssembler usuarioAssemb = new UsuarioRepuestoAssembler(accesoBD);
			UsuarioRepuesto usuarioRepuesto = usuarioAssemb.getUsuarioRepuesto(buscarUsuario(id));
			accesoBD.eliminar(usuarioRepuesto);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public void modificarUsuario(Long id, UsuarioRepuestoDTO modificado)
			throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		try {
			accesoBD.iniciarTransaccion();
			UsuarioRepuestoAssembler usuarioAssemb = new UsuarioRepuestoAssembler(accesoBD);
			UsuarioRepuesto usuarioRepuesto = usuarioAssemb.getUsuarioRepuesto(buscarUsuario(id));

			usuarioRepuesto.setNombre_usuario(modificado.getNombre_usuario());
			usuarioRepuesto.setClave(modificado.getClave());
			usuarioRepuesto.setEmail(modificado.getEmail());
			usuarioRepuesto.setTipo(modificado.getTipo());

			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
	}

	@Override
	public Vector<UsuarioRepuestoDTO> obtenerUsuarios() throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<UsuarioRepuestoDTO> usuariosDTO = new Vector<UsuarioRepuestoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			@SuppressWarnings("unchecked")
			Vector<UsuarioRepuesto> UsuarioRepuestos = new Vector<UsuarioRepuesto> (accesoBD.buscarPorFiltro(UsuarioRepuesto.class, ""));
			for (int i = 0; i < UsuarioRepuestos.size(); i++) {
				UsuarioRepuestoDTO usuarioRepuestoDTO = new UsuarioRepuestoDTO();
				usuarioRepuestoDTO.setId(UsuarioRepuestos.elementAt(i).getId());
				usuarioRepuestoDTO.setNombre_usuario(UsuarioRepuestos.elementAt(i).getNombre_usuario());
				usuarioRepuestoDTO.setClave(UsuarioRepuestos.elementAt(i).getClave());
				usuarioRepuestoDTO.setEmail(UsuarioRepuestos.elementAt(i).getEmail());
				usuarioRepuestoDTO.setTipo(UsuarioRepuestos.elementAt(i).getTipo());
				usuariosDTO.add(usuarioRepuestoDTO);
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return usuariosDTO;
	}

	@Override
	public Vector<UsuarioRepuestoDTO> obtenerUsuarios(String tipo) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Vector<UsuarioRepuestoDTO> usuariosDTO = new Vector<UsuarioRepuestoDTO>();
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "tipo.equals(\""+tipo+"\")";
			Collection usuarios = accesoBD.buscarPorFiltro(UsuarioRepuesto.class, filtro);
			UsuarioRepuestoAssembler usuarioAssemb = new UsuarioRepuestoAssembler(accesoBD);
			for (int i = 0; i < usuarios.size(); i++) {
				usuariosDTO.add(usuarioAssemb.getUsuarioRepuestoDTO((UsuarioRepuesto)(usuarios.toArray()[i])));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return usuariosDTO;
	}
	
	@Override
	public boolean login(String nombre_usuario, String password) throws Exception {
		if (existeUsuario(nombre_usuario)){
			UsuarioRepuestoDTO usuarioRepuestoDTO = buscarUsuario(nombre_usuario);
			return (usuarioRepuestoDTO.getClave().equals(password));
		}else{
			return false;
		}

	}

	@Override
	public boolean existeUsuario(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Boolean existe = null;
		try {
			accesoBD.iniciarTransaccion();
			existe = ((UsuarioRepuesto) accesoBD.buscarPorId(UsuarioRepuesto.class, id) != null);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return existe;
	}

	@Override
	public boolean existeUsuario(String nombre_usuario) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		Collection usuarios = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_usuario.equals(\""+nombre_usuario+"\")";
			usuarios = accesoBD.buscarPorFiltro(UsuarioRepuesto.class, filtro);
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return (usuarios!=null && usuarios.size()>=1);
	}

	@Override
	public UsuarioRepuestoDTO buscarUsuario(Long id) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		UsuarioRepuestoDTO usuarioRepuestoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			UsuarioRepuestoAssembler usuarioAssemb = new UsuarioRepuestoAssembler(accesoBD);
			usuarioRepuestoDTO = usuarioAssemb.getUsuarioRepuestoDTO((UsuarioRepuesto) accesoBD.buscarPorId(UsuarioRepuesto.class, id));
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return usuarioRepuestoDTO;
	}

	@Override
	public UsuarioRepuestoDTO buscarUsuario(String nombre_usuario) throws Exception {
		AccesoBD accesoBD = new AccesoBD();
		UsuarioRepuestoDTO usuarioRepuestoDTO = null;
		try {
			accesoBD.iniciarTransaccion();
			String filtro = "nombre_usuario.equals(\""+nombre_usuario+"\")";
			Collection usuarios = accesoBD.buscarPorFiltro(UsuarioRepuesto.class, filtro);
			UsuarioRepuestoAssembler usuarioAssemb = new UsuarioRepuestoAssembler(accesoBD);
			if (usuarios.size()>=1 ) {
				usuarioRepuestoDTO = usuarioAssemb.getUsuarioRepuestoDTO((UsuarioRepuesto)(usuarios.toArray()[0]));
			}
			accesoBD.concretarTransaccion();
		} catch (Exception e) {
			accesoBD.rollbackTransaccion();
		}
		return usuarioRepuestoDTO;
	}
}