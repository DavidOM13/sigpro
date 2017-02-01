package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dao.ActividadDAO;
import dao.ActividadPropiedadDAO;
import dao.ActividadPropiedadValorDAO;
import pojo.Actividad;
import pojo.ActividadPropiedad;
import pojo.ActividadPropiedadValor;
import pojo.ActividadPropiedadValorId;
import pojo.ActividadTipo;
import utilities.Utils;

/**
 * Servlet implementation class SActividad
 */
@WebServlet("/SActividad")
public class SActividad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	class stactividad{
		Integer id;
		String nombre;
		String descripcion;
		String usuarioCreo;
		String usuarioActualizo;
		String fechaCreacion;
		String fechaActualizacion;
		String fechaInicio;
		String fechaFin;
		Integer actividadtipoid;
		String actividadtiponombre;
		int estado;
	}

	class stdatadinamico {
		String id;
		String tipo;
		String label;
		String valor;
	}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SActividad() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession sesionweb = request.getSession();
		String usuario = sesionweb.getAttribute("usuario")!= null ? sesionweb.getAttribute("usuario").toString() : null;
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String, String>>(){}.getType();
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		;
		Map<String, String> map = gson.fromJson(sb.toString(), type);
		String accion = map.get("accion");
		String response_text="";
		if(accion.equals("getActividadsPagina")){
			int pagina = map.get("pagina")!=null  ? Integer.parseInt(map.get("pagina")) : 0;
			int numeroCooperantes = map.get("numeroactividads")!=null  ? Integer.parseInt(map.get("numeroactividads")) : 0;
			String filtro_nombre = map.get("filtro_nombre");
			String filtro_usuario_creo = map.get("filtro_usuario_creo");
			String filtro_fecha_creacion = map.get("filtro_fecha_creacion");
			String columna_ordenada = map.get("columna_ordenada");
			String orden_direccion = map.get("orden_direccion");
			List<Actividad> actividads = ActividadDAO.getActividadsPagina(pagina, numeroCooperantes, filtro_nombre, filtro_usuario_creo, filtro_fecha_creacion,
					columna_ordenada, orden_direccion);
			List<stactividad> stactividads=new ArrayList<stactividad>();
			for(Actividad actividad:actividads){
				stactividad temp =new stactividad();
				temp.descripcion = actividad.getDescripcion();
				temp.estado = actividad.getEstado();
				temp.fechaActualizacion = Utils.formatDate(actividad.getFechaActualizacion());
				temp.fechaCreacion = Utils.formatDate(actividad.getFechaCreacion());
				temp.id = actividad.getId();
				temp.nombre = actividad.getNombre();
				temp.usuarioActualizo = actividad.getUsuarioActualizo();
				temp.usuarioCreo = actividad.getUsuarioCreo();
				temp.actividadtipoid = actividad.getActividadTipo().getId();
				temp.actividadtiponombre = actividad.getActividadTipo().getNombre();
				stactividads.add(temp);
			}

			response_text=new GsonBuilder().serializeNulls().create().toJson(stactividads);
	        response_text = String.join("", "\"actividads\":",response_text);
	        response_text = String.join("", "{\"success\":true,", response_text,"}");
		}
		else if(accion.equals("getActividads")){
			List<Actividad> actividads = ActividadDAO.getActividads();
			List<stactividad> stactividads=new ArrayList<stactividad>();
			for(Actividad actividad:actividads){
				stactividad temp =new stactividad();

				temp.descripcion = actividad.getDescripcion();
				temp.estado = actividad.getEstado();
				temp.fechaActualizacion = Utils.formatDate(actividad.getFechaActualizacion());
				temp.fechaCreacion = Utils.formatDate(actividad.getFechaCreacion());
				temp.id = actividad.getId();
				temp.nombre = actividad.getNombre();
				temp.usuarioActualizo = actividad.getUsuarioActualizo();
				temp.usuarioCreo = actividad.getUsuarioCreo();
				temp.actividadtipoid = actividad.getActividadTipo().getId();
				temp.actividadtiponombre = actividad.getActividadTipo().getNombre();
				stactividads.add(temp);
			}

			response_text=new GsonBuilder().serializeNulls().create().toJson(stactividads);
	        response_text = String.join("", "\"actividads\":",response_text);
	        response_text = String.join("", "{\"success\":true,", response_text,"}");
		}
		else if(accion.equals("guardarActividad")){
			try{
				boolean result = false;
				boolean esnuevo = map.get("esnuevo").equals("true");
				int id = map.get("id")!=null ? Integer.parseInt(map.get("id")) : 0;
				if(id>0 || esnuevo){
					String nombre = map.get("nombre");
					String descripcion = map.get("descripcion");
					int actividadtipoid = Integer.parseInt(map.get("actividadtipoid"));
					
					Date fechaInicio = Utils.dateFromString(map.get("fechainicio"));
					Date fechaFin = Utils.dateFromString(map.get("fechafin"));
					Long snip = Utils.String2Long(map.get("snip"));
					Integer programa= Utils.getParameterInteger(map, "programa");
					Integer subprograma= Utils.getParameterInteger(map, "subprograma");
					Integer proyecto= Utils.getParameterInteger(map, "proyecto");
					Integer actividadObra= Utils.getParameterInteger(map, "actividadobra");
					Integer objetoId = Utils.getParameterInteger(map, "objetoid");
					Integer objetoTipo = Utils.getParameterInteger(map, "objetotipo");
					
					ActividadTipo actividadTipo= new ActividadTipo();
					actividadTipo.setId(actividadtipoid);

					type = new TypeToken<List<stdatadinamico>>() {
					}.getType();

					List<stdatadinamico> datos = (map.get("datadinamica")!=null && map.get("datadinamica").compareTo("{}")!=0) ?  gson.fromJson(map.get("datadinamica"), type) : new ArrayList<stdatadinamico>();

					Actividad actividad;
					if(esnuevo){
						actividad = new Actividad(actividadTipo, nombre, descripcion, fechaInicio, fechaFin, 
								0, usuario, null, new Date(), null, 1, snip, programa, subprograma, proyecto, actividadObra, 
								objetoId, objetoTipo,
								null,null);
					}
					else{
						actividad = ActividadDAO.getActividadPorId(id);
						actividad.setNombre(nombre);
						actividad.setDescripcion(descripcion);
						actividad.setUsuarioActualizo(usuario);
						actividad.setFechaActualizacion(new DateTime().toDate());
						actividad.setFechaInicio(fechaInicio);
						actividad.setFechaFin(fechaFin);
					}
					result = ActividadDAO.guardarActividad(actividad);

					Set<ActividadPropiedadValor> valores_temp = actividad.getActividadPropiedadValors();
					actividad.setActividadPropiedadValors(null);
					if (valores_temp!=null){
						for (ActividadPropiedadValor valor : valores_temp){
							ActividadPropiedadValorDAO.eliminarTotalActividadPropiedadValor(valor);
						}
					}

					for (stdatadinamico data : datos) {
						ActividadPropiedad actividadPropiedad = ActividadPropiedadDAO.getActividadPropiedadPorId(Integer.parseInt(data.id));
						ActividadPropiedadValorId idValor = new ActividadPropiedadValorId(actividad.getId(),Integer.parseInt(data.id));
						ActividadPropiedadValor valor = new ActividadPropiedadValor(idValor, actividad, actividadPropiedad,null, null, null, null, 
								usuario, null, new DateTime().toDate(), null, 1); 

						switch (actividadPropiedad.getDatoTipo().getId()){
							case 1:
								valor.setValorString(data.valor);
								break;
							case 2:
								valor.setValorEntero(Integer.parseInt(data.valor));
								break;
							case 3:
								valor.setValorDecimal(new BigDecimal(data.valor));
								break;
							case 4:

								break;
							case 5:
								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
								valor.setValorTiempo(sdf.parse(data.valor));
								break;
						}
						result = (result && ActividadPropiedadValorDAO.guardarActividadPropiedadValor(valor));
					}
					response_text = String.join("","{ \"success\": ",(result ? "true" : "false"),", "
							+ "\"id\": " + actividad.getId() +" }");
				}
				else
					response_text = "{ \"success\": false }";
			}
			catch (Throwable e){
				response_text = "{ \"success\": false }";
			}
		}
		else if(accion.equals("borrarActividad")){
			int id = map.get("id")!=null ? Integer.parseInt(map.get("id")) : 0;
			if(id>0){
				Actividad actividad = ActividadDAO.getActividadPorId(id);
				actividad.setUsuarioActualizo(usuario);
				response_text = String.join("","{ \"success\": ",(ActividadDAO.eliminarActividad(actividad) ? "true" : "false")," }");
			}
			else
				response_text = "{ \"success\": false }";
		}
		else if(accion.equals("numeroActividads")){
			String filtro_nombre = map.get("filtro_nombre");
			String filtro_usuario_creo = map.get("filtro_usuario_creo");
			String filtro_fecha_creacion = map.get("filtro_fecha_creacion");
			response_text = String.join("","{ \"success\": true, \"totalactividads\":",ActividadDAO.getTotalActividads(filtro_nombre, filtro_usuario_creo, filtro_fecha_creacion).toString()," }");
		}
		else if(accion.equals("numeroActividadesPorObjeto")){
			int objetoId = map.get("objetoid")!=null  ? Integer.parseInt(map.get("objetoid")) : 0;
			int objetoTipo = map.get("tipo")!=null  ? Integer.parseInt(map.get("tipo")) : 0;
			response_text = String.join("","{ \"success\": true, \"totalactividads\":",ActividadDAO.getTotalActividadsPorObjeto(objetoId, objetoTipo).toString()," }");
		}
		else if(accion.equals("getActividadesPaginaPorObjeto")){
			int pagina = map.get("pagina")!=null  ? Integer.parseInt(map.get("pagina")) : 0;
			int objetoId = map.get("objetoid")!=null  ? Integer.parseInt(map.get("objetoid")) : 0;
			int objetoTipo = map.get("tipo")!=null  ? Integer.parseInt(map.get("tipo")) : 0;
			int numeroActividads = map.get("numeroactividads")!=null  ? Integer.parseInt(map.get("numeroactividads")) : 0;
			List<Actividad> actividads = ActividadDAO.getActividadsPaginaPorObjeto(pagina, numeroActividads, objetoId, objetoTipo);
			List<stactividad> stactividads=new ArrayList<stactividad>();
			for(Actividad actividad:actividads){
				stactividad temp =new stactividad();
				temp.descripcion = actividad.getDescripcion();
				temp.estado = actividad.getEstado();
				temp.fechaActualizacion = Utils.formatDate(actividad.getFechaActualizacion());
				temp.fechaCreacion = Utils.formatDate(actividad.getFechaCreacion());
				temp.fechaInicio = Utils.formatDate(actividad.getFechaInicio());
				temp.fechaFin = Utils.formatDate(actividad.getFechaFin());
				temp.id = actividad.getId();
				temp.nombre = actividad.getNombre();
				temp.usuarioActualizo = actividad.getUsuarioActualizo();
				temp.usuarioCreo = actividad.getUsuarioCreo();
				temp.actividadtipoid = actividad.getActividadTipo().getId();
				temp.actividadtiponombre = actividad.getActividadTipo().getNombre();
				stactividads.add(temp);
			}

			response_text=new GsonBuilder().serializeNulls().create().toJson(stactividads);
	        response_text = String.join("", "\"actividades\":",response_text);
	        response_text = String.join("", "{\"success\":true,", response_text,"}");
		}
		else if(accion.equals("obtenerActividadPorId")){
			Integer id = map.get("id")!=null ? Integer.parseInt(map.get("id")) : 0;
			Actividad actividad = ActividadDAO.getActividadPorId(id);
		
			response_text = String.join("","{ \"success\": ",(actividad!=null && actividad.getId()!=null ? "true" : "false"),", "
				+ "\"id\": " + (actividad!=null ? actividad.getId():"0") +", "
				+ "\"nombre\": \"" + (actividad!=null ? actividad.getNombre():"Indefinido") +"\" }");
			


		}
		else{
			response_text = "{ \"success\": false }";
		}

		response.setHeader("Content-Encoding", "gzip");
		response.setCharacterEncoding("UTF-8");


        OutputStream output = response.getOutputStream();
		GZIPOutputStream gz = new GZIPOutputStream(output);
        gz.write(response_text.getBytes("UTF-8"));
        gz.close();
        output.close();
	}
}