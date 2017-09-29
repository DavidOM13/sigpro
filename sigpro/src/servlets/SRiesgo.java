package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import dao.ColaboradorDAO;
import dao.RiesgoDAO;
import dao.RiesgoPropiedadDAO;
import dao.RiesgoPropiedadValorDAO;
import pojo.Colaborador;
import pojo.Riesgo;
import pojo.RiesgoPropiedad;
import pojo.RiesgoPropiedadValor;
import pojo.RiesgoPropiedadValorId;
import pojo.RiesgoTipo;
import utilities.CLogger;
import utilities.Utils;

/**
 * Servlet implementation class SRiesgo
 */
@WebServlet("/SRiesgo")
public class SRiesgo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	class striesgo{
		Integer id;
		String nombre;
		String descripcion;
		String usuarioCreo;
		String usuarioActualizo;
		String fechaCreacion;
		String fechaActualizacion;
		Integer riesgotipoid;
		String riesgotiponombre;
		String impactoProyectado;
		Integer impacto;
		Integer puntuacionImpacto;
		Integer probabilidad;
		String gatillosSintomas;
		String respuesta;
		Integer colaboradorid;
		String colaboradorNombre;
		String riesgosSecundarios;
		Integer ejecutado;
		String fechaEjecucion;
		int estado;
		List<HashMap <String,Object>> camposdinamicos;
	}
	
	class stdatadinamico {
		String id;
		String tipo;
		String label;
		String valor;
		String valor_f;
	}

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SRiesgo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String response_text = "{ \"success\": false }";

		response.setHeader("Content-Encoding", "gzip");
		response.setCharacterEncoding("UTF-8");

        OutputStream output = response.getOutputStream();
		GZIPOutputStream gz = new GZIPOutputStream(output);
        gz.write(response_text.getBytes("UTF-8"));
        gz.close();
        output.close();
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
		if(accion.equals("getRiesgosPorObjeto")){
			int objetoId = map.get("objetoid")!=null && map.get("objetoid").length()>0 ?
					Integer.parseInt(map.get("objetoid")): 0;
			int objetoTipo = map.get("objetotipo")!=null && map.get("objetotipo").length()>0 ? 
					Integer.parseInt(map.get("objetotipo")) : 0 ;
			List<Riesgo> riesgos = RiesgoDAO.getRiesgosPorObjeto(objetoId,objetoTipo);
			List<striesgo> striesgos=new ArrayList<striesgo>();
			for(Riesgo riesgo:riesgos){
				striesgo temp =new striesgo();
				temp.descripcion = riesgo.getDescripcion();
				temp.estado = riesgo.getEstado();
				temp.fechaActualizacion = Utils.formatDateHour(riesgo.getFechaActualizacion());
				temp.fechaCreacion = Utils.formatDateHour(riesgo.getFechaCreacion());
				temp.id = riesgo.getId();
				temp.nombre = riesgo.getNombre();
				temp.usuarioActualizo = riesgo.getUsuarioActualizo();
				temp.usuarioCreo = riesgo.getUsuarioCreo();
				temp.riesgotipoid = riesgo.getRiesgoTipo().getId();
				temp.riesgotiponombre = riesgo.getRiesgoTipo().getNombre();
				temp.impactoProyectado = riesgo.getImapctoProyectado();
				temp.impacto = riesgo.getImpacto();
				temp.puntuacionImpacto = riesgo.getPuntuacionImpacto();
				temp.probabilidad = riesgo.getProbabilidad();
				temp.riesgosSecundarios = riesgo.getRiesgosSegundarios();
				temp.gatillosSintomas = riesgo.getGatillosSintomas();
				temp.respuesta = riesgo.getRespuesta();
				temp.ejecutado = riesgo.getEjecutado();
				temp.fechaEjecucion = Utils.formatDate(riesgo.getFechaEjecucion());
				
				if (riesgo.getColaborador()!=null ){
					temp.colaboradorid = riesgo.getColaborador().getId();
					temp.colaboradorNombre = String.join(" ", riesgo.getColaborador().getPnombre(),
						riesgo.getColaborador().getSnombre() !=null ? riesgo.getColaborador().getSnombre() : "",
						riesgo.getColaborador().getPapellido()!=null ? riesgo.getColaborador().getPapellido() : "",
						riesgo.getColaborador().getSapellido()!=null ? riesgo.getColaborador().getSapellido() : "");
				}
				
				List<RiesgoPropiedad> riesgopropiedades = RiesgoPropiedadDAO.getRiesgoPropiedadesPorTipoRiesgo(temp.riesgotipoid);

				List<HashMap<String,Object>> campos = new ArrayList<>();
				for(RiesgoPropiedad riesgopropiedad:riesgopropiedades){
					HashMap <String,Object> campo = new HashMap<String, Object>();
					campo.put("id", riesgopropiedad.getId());
					campo.put("nombre", riesgopropiedad.getNombre());
					campo.put("tipo", riesgopropiedad.getDatoTipo().getId());
					RiesgoPropiedadValor riesgoPropiedadValor = RiesgoPropiedadValorDAO.getValorPorRiesgoYPropiedad(riesgopropiedad.getId(), temp.id);
					if (riesgoPropiedadValor !=null ){
						switch ((Integer) campo.get("tipo")){
							case 1: 
								campo.put("valor", riesgoPropiedadValor.getValorString());
								break;
							case 2:
								campo.put("valor", riesgoPropiedadValor.getValorEntero());
								break;
							case 3:
								campo.put("valor", riesgoPropiedadValor.getValorDecimal());
								break;
							case 4:
								campo.put("valor", riesgoPropiedadValor.getValorEntero()==1 ? true : false);
								break;	
							case 5:
								campo.put("valor", Utils.formatDate(riesgoPropiedadValor.getValorTiempo()));
								break;
						}
					}
					else{
						campo.put("valor", "");
					}
					campos.add(campo);
				}
				temp.camposdinamicos = campos;
				striesgos.add(temp);
			}
			
			response_text=new GsonBuilder().serializeNulls().create().toJson(striesgos);
	        response_text = String.join("", "\"riesgos\":",response_text);
	        response_text = String.join("", "{\"success\":true,", response_text,"}");
		} 
		else if(accion.equals("getRiesgos")){
			List<Riesgo> riesgos = RiesgoDAO.getRiesgos();
			List<striesgo> striesgos=new ArrayList<striesgo>();
			for(Riesgo riesgo:riesgos){
				striesgo temp =new striesgo();
				temp.descripcion = riesgo.getDescripcion();
				temp.estado = riesgo.getEstado();
				temp.fechaActualizacion = Utils.formatDate(riesgo.getFechaActualizacion());
				temp.fechaCreacion = Utils.formatDate(riesgo.getFechaCreacion());
				temp.id = riesgo.getId();
				temp.nombre = riesgo.getNombre();
				temp.usuarioActualizo = riesgo.getUsuarioActualizo();
				temp.usuarioCreo = riesgo.getUsuarioCreo();
				temp.riesgotipoid = riesgo.getRiesgoTipo().getId();
				temp.riesgotiponombre = riesgo.getRiesgoTipo().getNombre();
				temp.impactoProyectado = riesgo.getImapctoProyectado();
				temp.impacto = riesgo.getImpacto();
				temp.puntuacionImpacto = riesgo.getPuntuacionImpacto();
				temp.probabilidad = riesgo.getProbabilidad();
				temp.gatillosSintomas = riesgo.getGatillosSintomas();
				temp.respuesta = riesgo.getRespuesta();
				temp.riesgosSecundarios = riesgo.getRiesgosSegundarios();
				temp.ejecutado = riesgo.getEjecutado();
				temp.fechaEjecucion = Utils.formatDate(riesgo.getFechaEjecucion());
				temp.colaboradorid = riesgo.getColaborador().getId();
				if (riesgo.getColaborador()!=null )
				temp.colaboradorNombre = String.join(" ", riesgo.getColaborador().getPnombre(),
						riesgo.getColaborador().getSnombre() !=null ? riesgo.getColaborador().getSnombre() : "",
						riesgo.getColaborador().getPapellido()!=null ? riesgo.getColaborador().getPapellido() : "",
						riesgo.getColaborador().getSapellido()!=null ? riesgo.getColaborador().getSapellido() : ""	
				);
							
				striesgos.add(temp);
			}
			
			response_text=new GsonBuilder().serializeNulls().create().toJson(striesgos);
	        response_text = String.join("", "\"riesgos\":",response_text);
	        response_text = String.join("", "{\"success\":true,", response_text,"}");
		}
		else if(accion.equals("guardarRiesgos")){
			try{
				boolean result = false;
				int objetoId = Utils.String2Int(map.get("objetoId"));
				int objetoTipo = Utils.String2Int(map.get("objetoTipo"));
				String sriesgos = map.get("riesgos");
				JsonParser parser = new JsonParser();
				JsonArray riesgos = parser.parse(sriesgos).getAsJsonArray();
				ArrayList<Integer> ids=new ArrayList<Integer>();
				for(int i=0; i<riesgos.size(); i++){
					JsonObject objeto = riesgos.get(i).getAsJsonObject();
					Integer id = objeto.get("id").isJsonNull() ? null : objeto.get("id").getAsInt();
					String nombre = objeto.get("nombre").isJsonNull() ? null : objeto.get("nombre").getAsString();
					String descripcion = objeto.get("descripcion").isJsonNull() ? null : objeto.get("descripcion").getAsString();
					int riesgotipoid = objeto.get("riesgotipoid").isJsonNull() ? 0 : objeto.get("riesgotipoid").getAsInt();
				    String impactoProyectado = objeto.get("impactoProyectado").isJsonNull() ? null : objeto.get("impactoProyectado").getAsString();
				    Integer impacto = objeto.get("impacto").isJsonNull() ? null : objeto.get("impacto").getAsInt(); 
				    Integer puntuacionImpacto = objeto.get("puntuacionImpacto").isJsonNull() ? null : objeto.get("puntuacionImpacto").getAsInt();
				    Integer probabilidad = objeto.get("probabilidad").isJsonNull() ? null : objeto.get("probabilidad").getAsInt();
				    String gatillosOSintomas = objeto.get("gatillosSintomas").isJsonNull() ? null : objeto.get("gatillosSintomas").getAsString();
				    String respuesta = objeto.get("respuesta").isJsonNull() ? null : objeto.get("respuesta").getAsString();
				    String riesgosSecundarios = objeto.get("riesgosSecundarios").isJsonNull() ? null : objeto.get("riesgosSecundarios").getAsString();
				    Integer ejecutado = objeto.get("ejecutado").isJsonNull() ? null : objeto.get("ejecutado").getAsInt();
				    Date fechaEjecucion =  objeto.get("fechaEjecucion").isJsonNull() ? null : Utils.stringToDateZ(objeto.get("fechaEjecucion").getAsString());
				    
				    Colaborador colaborador = null;
				    Integer colobaradorId = objeto.get("colaboradorid").isJsonNull() || objeto.get("colaboradorid").getAsString().length()==0 ? null : objeto.get("colaboradorid").getAsInt(); 
				    if (colobaradorId!=null){
				    	colaborador = ColaboradorDAO.getColaborador(colobaradorId);
				    }
				    
				    RiesgoTipo riesgoTipo= new RiesgoTipo();
					riesgoTipo.setId(riesgotipoid);
					
					type = new TypeToken<List<stdatadinamico>>() {
					}.getType();
	
					List<stdatadinamico> datos = gson.fromJson(objeto.get("camposdinamicos"), type);
					Riesgo riesgo = new Riesgo(colaborador, riesgoTipo, nombre, descripcion, usuario, null, new Date(), null, 1,
							impactoProyectado, impacto, puntuacionImpacto, probabilidad, gatillosOSintomas, respuesta,
							riesgosSecundarios, ejecutado, fechaEjecucion, null, null);
					if(id!=0){
						riesgo.setId(id);
						riesgo.setUsuarioActualizo(usuario);
						riesgo.setFechaActualizacion(new Date());
					}
					
					result = RiesgoDAO.guardarRiesgo(riesgo,objetoId, objetoTipo);
					ids.add(riesgo.getId());
					List<RiesgoPropiedadValor> valores_temp = RiesgoPropiedadValorDAO.getRiesgoPropiedadadesValoresPorRiesgo(riesgo.getId());
					riesgo.setRiesgoPropiedadValors(null);
					if (valores_temp!=null){
						for (RiesgoPropiedadValor valor : valores_temp){
							RiesgoPropiedadValorDAO.eliminarRiesgoPropiedadValor(valor);
						}
					}
					
					for (stdatadinamico data : datos) {
						if (data.valor!=null && data.valor.length()>0 && data.valor.compareTo("null")!=0){
							RiesgoPropiedad riesgoPropiedad = RiesgoPropiedadDAO.getRiesgoPropiedadPorId(Integer.parseInt(data.id));
							RiesgoPropiedadValorId idValor = new RiesgoPropiedadValorId(riesgo.getId(),Integer.parseInt(data.id));
							
							if(riesgoPropiedad != null && idValor != null){
								RiesgoPropiedadValor valor = new RiesgoPropiedadValor(idValor, riesgo, 
										riesgoPropiedad, 1, usuario, new DateTime().toDate());
								if(valor!=null){
									switch (riesgoPropiedad.getDatoTipo().getId()){
									case 1:
										valor.setValorString(data.valor);
										break;
									case 2:
										valor.setValorEntero(Utils.String2Int(data.valor, null));
										break;
									case 3:
										valor.setValorDecimal(Utils.String2BigDecimal(data.valor, null));
										break;
									case 4:
										valor.setValorEntero(Utils.String2Boolean(data.valor, null));
										break;
									case 5:
										valor.setValorTiempo(data.valor.compareTo("")!=0 ? Utils.stringToDateZ(data.valor) : null);
										break;
									}
									result = (result && RiesgoPropiedadValorDAO.guardarRiesgoPropiedadValor(valor));
								}
							}
						}
					}
				}
				List<Riesgo> delete_riesgos = RiesgoDAO.getRiesgosNotIn(objetoId, objetoTipo, ids);
				for(Riesgo driesgo : delete_riesgos){
					RiesgoDAO.eliminarRiesgo(driesgo);
				}
				String sids="";
				for(int i=0; i<ids.size(); i++)
					sids = sids + "," +ids.get(i);
				response_text = String.join("","{ \"success\": ",(result ? "true" : "false"),", \"ids\":\"",(sids.length()>0 ? sids.substring(1, sids.length()-1) : ""),"\" }");
			}
			catch (Throwable e){
				CLogger.write("1", SRiesgo.class, e);
				response_text = "{ \"success\": false }";
			}
		}
		else if(accion.equals("borrarRiesgo")){
			int id = map.get("id")!=null ? Integer.parseInt(map.get("id")) : 0;
			if(id>0){
				Riesgo riesgo = RiesgoDAO.getRiesgoPorId(id);
				riesgo.setUsuarioActualizo(usuario);
				response_text = String.join("","{ \"success\": ",(RiesgoDAO.eliminarRiesgo(riesgo) ? "true" : "false")," }");
			}
			else
				response_text = "{ \"success\": false }";
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
