package servlets;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.codec.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dao.ComponenteDAO;
import dao.PrestamoMetasDAO;

import dao.ProductoDAO;
import dao.ProyectoDAO;
import pojo.Componente;
import pojo.Producto;
import pojo.Proyecto;
import utilities.CMariaDB;
import utilities.Utils;
import utilities.CPdf;

@WebServlet("/SPrestamoMetas")
public class SPrestamoMetas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	class stprestamo{
		String nombre;
		Integer objeto_id;
		Integer objeto_tipo;
		Integer unidadDeMedida;
		BigDecimal lineaBase;
		BigDecimal metaFinal;
		Integer nivel;
		stanio[] anios; 
	}
	
	class stanio{
		BigDecimal[] enero;
		BigDecimal[] febrero;
		BigDecimal[] marzo;
		BigDecimal[] abril;
		BigDecimal[] mayo;
		BigDecimal[] junio;
		BigDecimal[] julio;
		BigDecimal[] agosto;
		BigDecimal[] septiembre;
		BigDecimal[] octubre;
		BigDecimal[] noviembre;
		BigDecimal[] diciembre;
		BigDecimal anio;
	}

    public SPrestamoMetas() {
        super();

    }

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
		String accion = map.get("accion")!=null ? map.get("accion") : "";
		String response_text = "";

		if(accion.equals("getMetasProducto")){
			Integer idPrestamo = Utils.String2Int(map.get("idPrestamo"),0);
			Integer anoInicial = Utils.String2Int(map.get("anoInicial"),0);
			Integer anoFinal = Utils.String2Int(map.get("anoFinal"),0);
			
			Proyecto proyecto = ProyectoDAO.getProyectoPorId(idPrestamo, usuario);
			
			if(proyecto != null){
				List<stprestamo> lstPrestamo = new ArrayList<>();
				stprestamo tempPrestamo = null;
			
				tempPrestamo = new stprestamo();
				if(CMariaDB.connect()){
						Connection conn = CMariaDB.getConnection();
						ArrayList<Integer> componentes = PrestamoMetasDAO.getEstructuraArbolComponentes(idPrestamo, conn);
						
						tempPrestamo.nombre = proyecto.getNombre();
						tempPrestamo.objeto_id = proyecto.getId();
						tempPrestamo.objeto_tipo = 1;
						tempPrestamo.nivel = 1;
						
						ArrayList<ArrayList<BigDecimal>> presupuestoPrestamo = new ArrayList<ArrayList<BigDecimal>>();
						
						tempPrestamo = getMetas(presupuestoPrestamo, anoInicial, anoFinal, tempPrestamo);
						
						lstPrestamo.add(tempPrestamo);
						
						for(Integer componente:componentes){
							tempPrestamo = new stprestamo();
							Componente objComponente = ComponenteDAO.getComponentePorId(componente, usuario);
							tempPrestamo.nombre = objComponente.getNombre();
							tempPrestamo.objeto_id = objComponente.getId();
							tempPrestamo.objeto_tipo = 2;
							tempPrestamo.nivel = 2;
							
							 presupuestoPrestamo = new ArrayList<ArrayList<BigDecimal>>();
							
							tempPrestamo = getMetas(presupuestoPrestamo, anoInicial, anoFinal, tempPrestamo);
							
							lstPrestamo.add(tempPrestamo);							
							ArrayList<Integer> productos = PrestamoMetasDAO.getEstructuraArbolProducto(idPrestamo, objComponente.getId(), conn);
							for(Integer producto: productos){
								tempPrestamo = new stprestamo();
								Producto objProducto = ProductoDAO.getProductoPorId(producto);
								tempPrestamo.nombre = objProducto.getNombre();
								tempPrestamo.objeto_id = objProducto.getId();
								tempPrestamo.objeto_tipo = 3;
								tempPrestamo.nivel = 3;
								tempPrestamo.lineaBase = new BigDecimal(0);
								tempPrestamo.metaFinal = new BigDecimal(0);
								
								presupuestoPrestamo = PrestamoMetasDAO.getMetasPorProducto(producto, 
										anoInicial, anoFinal, conn);
								
								tempPrestamo = getMetas(presupuestoPrestamo, anoInicial, anoFinal, tempPrestamo);
								lstPrestamo.add(tempPrestamo);
							
							} 
							
						}
						
						CMariaDB.close();
						response_text=new GsonBuilder().serializeNulls().create().toJson(lstPrestamo);
				        response_text = String.join("", "\"prestamo\":",response_text);
				        response_text = String.join("", "{\"success\":true,", response_text, "}");
					}else{
						response_text = String.join("", "{\"success\":false}");
					}
				}
		
		}else if(accion.equals("guardarProyectoMeta")){
//			
//			stmeta metavalor = new stmeta();
//			metavalor.id = map.get("id")!=null ? Integer.parseInt(map.get("id")) : 0; 
//			metavalor.fecha = map.get("fecha")!=null ? map.get("fecha") : "";
//			metavalor.valor = map.get("valor")!=null ? map.get("valor") : "";
//			metavalor.tipoMetaId = map.get("tipoMetaId")!=null ? Integer.parseInt(map.get("tipoMetaId")) : 0; 
//			metavalor.unidadMedidaId = map.get("unidadMedidaId")!=null ? Integer.parseInt(map.get("unidadMedidaId")) : 0; 
//			metavalor.objetoId = map.get("objetoId")!=null ? Integer.parseInt(map.get("objetoId")) : 0;
//			metavalor.objetoTipoId = map.get("objetoTipoId")!=null ? Integer.parseInt(map.get("objetoTipoId")) : 0;
//						
//			metavalor = guardarProductoMetaValor(metavalor, usuario);
//			
//			response_text=new GsonBuilder().serializeNulls().create().toJson(metavalor);
//	        response_text = String.join("", "\"metavalor\":",response_text);
//	        response_text = String.join("", "{\"success\":true,", response_text,"}");
//	        
//	        response.setHeader("Content-Encoding", "gzip");
//			response.setCharacterEncoding("UTF-8");
//
//	        OutputStream output = response.getOutputStream();
//			GZIPOutputStream gz = new GZIPOutputStream(output);
//	        gz.write(response_text.getBytes("UTF-8"));
//	        gz.close();
//	        output.close();
		}else if(accion.equals("congelarPrestamoMeta")){
//			Integer proyectoId = map.get("proyectoid")!=null ? Integer.parseInt(map.get("proyectoid")) : 0;
//			Boolean exito = false;
//			
//			exito = congelarPrestamoMeta(proyectoId, usuario);
//			
//			response_text=new GsonBuilder().serializeNulls().create().toJson(exito);
//	        response_text = String.join("", "\"metavalor\":",response_text);
//	        response_text = String.join("", "{\"success\":true,", response_text,"}");
//	        
//	        response.setHeader("Content-Encoding", "gzip");
//			response.setCharacterEncoding("UTF-8");
//
//	        OutputStream output = response.getOutputStream();
//			GZIPOutputStream gz = new GZIPOutputStream(output);
//	        gz.write(response_text.getBytes("UTF-8"));
//	        gz.close();
//	        output.close();
		}else if (accion.equals("exportarExcel")){
//			int proyectoId = Utils.String2Int(map.get("proyectoid"), 0);
//			int anioInicio = Utils.String2Int(map.get("fechaInicio"), 0);
//			int anioFin = Utils.String2Int(map.get("fechaFin"), 0);
//			int agrupacion = Utils.String2Int(map.get("agrupacion"), 0);
//			String filas = map.get("filas");
//			String columnas = map.get("columnas");
//			
//	        byte [] outArray = exportarExcel(proyectoId, anioInicio, anioFin, agrupacion, filas, columnas, usuario);
//		
//			response.setContentType("application/ms-excel");
//			response.setContentLength(outArray.length);
//			response.setHeader("Expires:", "0"); 
//			response.setHeader("Content-Disposition", "attachment; MetasPrestamo_.xls");
//			OutputStream outStream = response.getOutputStream();
//			outStream.write(outArray);
//			outStream.flush();

		}else if(accion.equals("exportarPdf")){
			//Creamos el documento de pdf
			CPdf archivo = new CPdf("Metas de Pr�stamo");
			String path = archivo.ExportPdf();
			File file=new File(path);
			if(file.exists()){
		        FileInputStream is = null;
		        try {
		        	is = new FileInputStream(file);
		        }
		        catch (Exception e) {
		        	
		        }
		        //
		        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		        
		        int readByte = 0;
		        byte[] buffer = new byte[2024];

                while(true)
                {
                    readByte = is.read(buffer);
                    if(readByte == -1)
                    {
                        break;
                    }
                    outByteStream.write(buffer);
                }
                
                file.delete();
                
                is.close();
                outByteStream.flush();
                outByteStream.close();
                
		        byte [] outArray = Base64.encode(outByteStream.toByteArray());
				response.setContentType("application/pdf");
				response.setContentLength(outArray.length);
				response.setHeader("Expires:", "0"); 
				response.setHeader("Content-Disposition", "in-line; 'PrestamoMestas.pdf'");
				OutputStream outStream = response.getOutputStream();
				outStream.write(outArray);
				outStream.flush();
			}
			
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
	
	private stprestamo getMetas (ArrayList<ArrayList<BigDecimal>> presupuestoPrestamo,
			int anoInicial, int anoFinal, stprestamo prestamo){
		
		stanio[] anios = inicializarStanio(anoInicial, anoFinal);
		if(presupuestoPrestamo.size() > 0){
			
			
			for(ArrayList<BigDecimal> objprestamopresupuesto : presupuestoPrestamo){
				
				stanio aniotemp = new stanio(); 
				aniotemp = inicializarStanio(aniotemp);
				aniotemp.enero[0] = objprestamopresupuesto.get(0);
				aniotemp.enero[1] = objprestamopresupuesto.get(1);
				aniotemp.febrero[0] = objprestamopresupuesto.get(2);
				aniotemp.febrero[1] = objprestamopresupuesto.get(3);
				aniotemp.marzo[0] = objprestamopresupuesto.get(4);
				aniotemp.marzo[1] = objprestamopresupuesto.get(5);
				aniotemp.abril[0] = objprestamopresupuesto.get(6);
				aniotemp.abril[1] = objprestamopresupuesto.get(7);
				aniotemp.mayo[0] = objprestamopresupuesto.get(8);
				aniotemp.mayo[1] = objprestamopresupuesto.get(9);
				aniotemp.junio[0] = objprestamopresupuesto.get(10);
				aniotemp.junio[1] = objprestamopresupuesto.get(11);
				aniotemp.julio[0] = objprestamopresupuesto.get(12);
				aniotemp.julio[1] = objprestamopresupuesto.get(13);
				aniotemp.agosto[0] = objprestamopresupuesto.get(14);
				aniotemp.agosto[1] = objprestamopresupuesto.get(15);
				aniotemp.septiembre[0] = objprestamopresupuesto.get(16);
				aniotemp.septiembre[1] = objprestamopresupuesto.get(17);
				aniotemp.octubre[0] = objprestamopresupuesto.get(18);
				aniotemp.octubre[1] = objprestamopresupuesto.get(19);
				aniotemp.noviembre[0] = objprestamopresupuesto.get(20);
				aniotemp.noviembre[1] = objprestamopresupuesto.get(21);
				aniotemp.diciembre[0] = objprestamopresupuesto.get(22);
				aniotemp.diciembre[1] = objprestamopresupuesto.get(23);
				int pos = anoFinal- objprestamopresupuesto.get(24).intValue();
				aniotemp.anio = new BigDecimal(anoInicial + pos);
				prestamo.unidadDeMedida = Integer.parseInt(objprestamopresupuesto.get(25).toString());
				prestamo.lineaBase = prestamo.lineaBase.add(objprestamopresupuesto.get(26));
				prestamo.metaFinal = prestamo.metaFinal.add(objprestamopresupuesto.get(27));
				anios[pos] = aniotemp;
			}
		}
			prestamo.anios = anios;
			return prestamo;
	}
	
	private stanio[] inicializarStanio (int anioInicial, int anioFinal){
		
		int longitudArrelgo = anioFinal - anioInicial+1;
		
		stanio[] anios = new stanio[longitudArrelgo];
		
		for (int i = 0;i <longitudArrelgo; i++){
			stanio temp = new stanio();
			temp = inicializarStanio(temp);
			temp.anio = new BigDecimal(anioInicial + i);
			anios[i] = temp;
		}
		return anios;		
	}
	
	private stanio inicializarStanio(stanio anio){
		
			anio.enero = new BigDecimal[2];
			anio.febrero = new BigDecimal[2];
			anio.marzo = new BigDecimal[2];
			anio.abril = new BigDecimal[2];
			anio.mayo = new BigDecimal[2];
			anio.junio = new BigDecimal[2];
			anio.julio = new BigDecimal[2];
			anio.agosto = new BigDecimal[2];
			anio.septiembre = new BigDecimal[2];
			anio.octubre = new BigDecimal[2];
			anio.noviembre = new BigDecimal[2];
			anio.diciembre = new BigDecimal[2];
			
		return anio;		
	}
	
//
//	private stproductometa getFechaInicioFinProducto(stproductometa productometa){
//		Date fechaInicio = null;
//		Date fechaFin = null;
//		List <Actividad> actividades = getActividadesProducto(productometa.id);
//		for(Actividad actividad : actividades){
//			//actividad = ActividadDAO.getFechasActividad(actividad);
//			if (fechaInicio == null || fechaInicio.after(actividad.getFechaInicio())){
//				fechaInicio = actividad.getFechaInicio();
//			}
//			if (fechaFin == null || fechaFin.before(actividad.getFechaFin())){
//				fechaFin = actividad.getFechaFin();
//			}
//		}
//		productometa.fechaInicio = Utils.formatDate(fechaInicio);
//		productometa.fechaFin = Utils.formatDate(fechaFin);
//		return productometa;
//	}
//	
	
}