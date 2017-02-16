package servlets;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Date;
import java.util.List;

import java.util.zip.GZIPOutputStream;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ActividadDAO;
import dao.ComponenteDAO;
import dao.ProductoDAO;
import dao.ProyectoDAO;
import pojo.Actividad;
import pojo.Componente;
import pojo.Producto;
import pojo.Proyecto;
import utilities.Utils;

/**
 * Servlet implementation class SGantt
 */
@WebServlet("/SGantt")
public class SGantt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SGantt() {
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
		String items = "";
		List<Proyecto> proyectos = ProyectoDAO.getProyectos("admin");
		
		for (Proyecto proyecto : proyectos){
			Date fechaPrimeraActividad = null;
			List<Componente> componentes = ComponenteDAO.getComponentesPaginaPorProyecto(0, 0, proyecto.getId(),
					null, null, null, null, null, usuario);
			for (Componente componente :componentes){
				List<Producto> productos = ProductoDAO.getProductosPagina(0, 0, componente.getId(),
						null, null, null, null, null, usuario);
				for (Producto producto : productos){
					List<Actividad> actividades = ActividadDAO.getActividadsPaginaPorObjeto(0, 0, producto.getId(), 3, 
							null,null, null, null, null, usuario);
					for (Actividad actividad : actividades){
						if (fechaPrimeraActividad==null) {
							fechaPrimeraActividad = actividad.getFechaInicio();
						}
						items = String.join(",", construirItem(actividad.getNombre(), 3, true, actividad.getFechaInicio(), actividad.getFechaFin()),items);
					}
					items = String.join(",",construirItem(producto.getNombre(),2, true, fechaPrimeraActividad, null),items);
				}
				items = String.join(",",construirItem(componente.getNombre(),1, true, fechaPrimeraActividad, null),items);
			}
			items = String.join(",",construirItem(proyecto.getNombre(),null, true, fechaPrimeraActividad, null),items);
		}
		
		items = String.join("","{\"items\" : [", items.substring(0,items.length()-1),"]}");
		response.setHeader("Content-Encoding", "gzip");
		response.setCharacterEncoding("UTF-8");


        OutputStream output = response.getOutputStream();
		GZIPOutputStream gz = new GZIPOutputStream(output);
        gz.write(items.getBytes("UTF-8"));
        gz.close();
        output.close();
		
	}
	
	private String construirItem(String content,Integer identation,boolean isExpanded,Date start,Date finish){
		return String.join("", "{\"content\" :\"",content,"\",",
				identation!=null ? "\"identation\" :" : "", identation!=null ? identation.toString() :"",identation!=null ? "," : "", 
				"\"isExpanded\" :\"" ,isExpanded ? "true" : "false","\",",
				start !=null ? "\"start\" :\"" : "", start!=null ? Utils.formatDateHour24(start) :"", start!=null ? "\"" : "",
			    start!=null && finish!=null ? "," : "",
				finish!=null ? "\"finish\" :\"" : "",finish!=null ? Utils.formatDateHour24(start) : "",finish!=null ?"\"":"",
				"}"
			);
	}

}
