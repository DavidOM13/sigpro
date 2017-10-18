package dao;

import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import pojo.ObjetoPrestamo;
import pojo.Prestamo;
import utilities.CHibernateSession;
import utilities.CLogger;

public class PrestamoDAO {

	public static Prestamo getPrestamoPorObjetoYTipo(int objetoId, int objetoTipo){
		Session session = CHibernateSession.getSessionFactory().openSession();
		Prestamo ret = null;
		try{
			Query<Prestamo> criteria = session.createQuery("SELECT p FROM Prestamo p "
					+ "INNER JOIN p.objetoPrestamos op "
					+ " where op.id.objetoId = :objId "
					+ " and op.id.objetoTipo = :objTipo "
					+ " and p.id=op.prestamo.id"
					+ " and p.estado = 1 ", Prestamo.class);
			criteria.setParameter("objId", objetoId);
			criteria.setParameter("objTipo", objetoTipo);
			ret = criteria.getSingleResult();
		} catch (NoResultException e){
		}
		catch(Throwable e){
			CLogger.write("1", PrestamoDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	public static ObjetoPrestamo getObjetoPrestamo(int idPrestamo){
		Session session = CHibernateSession.getSessionFactory().openSession();
		ObjetoPrestamo ret = null;
		try{
			Query<ObjetoPrestamo> criteria = session.createQuery("SELECT op FROM Prestamo p "
					+ "INNER JOIN p.objetoPrestamos op "
					+ " where op.id.prestamoid= :idPrestamo "
					+ " and op.estado= 1", ObjetoPrestamo.class);
			criteria.setParameter("idPrestamo", idPrestamo);
			ret = criteria.getSingleResult();
		} catch (NoResultException e){
		}
		catch(Throwable e){
			e.printStackTrace();
			CLogger.write("1", PrestamoDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}

	public static boolean guardarPrestamo(Prestamo prestamo,ObjetoPrestamo objetoPrestamo){
		boolean ret = false;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(prestamo);
			
			objetoPrestamo.setEstado(1);
			objetoPrestamo.setPrestamo(prestamo);
			objetoPrestamo.getId().setPrestamoid(prestamo.getId());
			objetoPrestamo.setFechaCreacion(prestamo.getFechaCreacion());
			objetoPrestamo.setUsuarioCreo(prestamo.getUsuarioCreo());
			
			session.saveOrUpdate(objetoPrestamo);
			session.getTransaction().commit();
			ret = true;
		}
		catch(Throwable e){
			e.printStackTrace();
			CLogger.write("2", PrestamoDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	public static List<Prestamo> getPrestamosPagina(Integer pagina, Integer elementosPorPagina, String filtro_nombre, Long filtro_codigo_presupuestario, 
			String filtro_numero_prestamo, String filtro_usuario_creo, String filtro_fecha_creacion, String columna_ordenada,
			String orden_direccion, String usuario){
		List<Prestamo> ret = null;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			String query = "SELECT p FROM Prestamo p where p.estado=1 ";
			String query_a="";
			if(filtro_nombre!=null && filtro_nombre.trim().length()>0)
				query_a = String.join("",query_a, " p.proyectoPrograma LIKE '%",filtro_nombre,"%' ");
			if(filtro_codigo_presupuestario!=null)
				query_a = String.join("",query_a, " p.codigoPresupuestario= ",filtro_codigo_presupuestario.toString());
			if(filtro_numero_prestamo!=null && filtro_numero_prestamo.trim().length()>0)
				query_a = String.join("",query_a, " p.numeroPrestamo LIKE '%",filtro_numero_prestamo,"%' ");
			if(filtro_usuario_creo!=null && filtro_usuario_creo.trim().length()>0)
				query_a = String.join("",query_a,(query_a.length()>0 ? " OR " :""), " p.usuarioCreo LIKE '%", filtro_usuario_creo,"%' ");
			if(filtro_fecha_creacion!=null && filtro_fecha_creacion.trim().length()>0)
				query_a = String.join("",query_a,(query_a.length()>0 ? " OR " :""), " str(date_format(p.fechaCreacion,'%d/%m/%YYYY')) LIKE '%", filtro_fecha_creacion,"%' ");
			query = String.join(" ", query, (query_a.length()>0 ? String.join("","AND (",query_a,")") : ""));
//			if(usuario!=null)
//				query = String.join("", query, " AND p.id in (SELECT u.id.prestamoid from PrestamoUsuario u where u.id.usuario=:usuario )");
			
			query = columna_ordenada!=null && columna_ordenada.trim().length()>0 ? String.join(" ",query,"ORDER BY",columna_ordenada,orden_direccion ) :
				String.join(" ", query, "ORDER BY fecha_creacion ASC");
			
			Query<Prestamo> criteria = session.createQuery(query, Prestamo.class);
			criteria.setFirstResult(((pagina-1)*(elementosPorPagina)));
//			if(usuario != null){
//				criteria.setParameter("usuario", usuario);
//			}
			ret = criteria.getResultList();
		}catch(Exception e){
			ret = null;
			CLogger.write("3", PrestamoDAO.class, e);
		}
		
		return ret;
	}
	
	public static Long getTotalPrestamos(String filtro_nombre, Long filtro_codigo_presupuestario, 
			String filtro_numero_prestamo, String filtro_usuario_creo, String filtro_fecha_creacion, String usuario){
		Long ret=0L;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			String query = "SELECT count(p.id) FROM Prestamo p WHERE p.estado=1 ";
			String query_a="";
			if(filtro_nombre!=null && filtro_nombre.trim().length()>0)
				query_a = String.join("",query_a, " p.proyectoPrograma LIKE '%",filtro_nombre,"%' ");
			if(filtro_codigo_presupuestario!=null)
				query_a = String.join("",query_a, " p.codigoPresupuestario= ",filtro_codigo_presupuestario.toString());
			if(filtro_numero_prestamo!=null && filtro_numero_prestamo.trim().length()>0)
				query_a = String.join("",query_a, " p.numeroPrestamo LIKE '%",filtro_numero_prestamo,"%' ");
			if(filtro_usuario_creo!=null && filtro_usuario_creo.trim().length()>0)
				query_a = String.join("",query_a,(query_a.length()>0 ? " OR " :""), " p.usuarioCreo LIKE '%", filtro_usuario_creo,"%' ");
			if(filtro_fecha_creacion!=null && filtro_fecha_creacion.trim().length()>0)
				query_a = String.join("",query_a,(query_a.length()>0 ? " OR " :""), " str(date_format(p.fechaCreacion,'%d/%m/%YYYY')) LIKE '%", filtro_fecha_creacion,"%' ");
			query = String.join(" ", query, (query_a.length()>0 ? String.join("","AND (",query_a,")") : ""));
//			if(usuario!=null)
//				query = String.join("", query, " AND p.id in (SELECT u.id.prestamoid from PrestamoUsuario u where u.id.usuario=:usuario )");
			Query<Long> criteria = session.createQuery(query,Long.class);
//			if(usuario != null){
//				criteria.setParameter("usuario", usuario);	
//			}			
			ret = criteria.getSingleResult();
		}
		catch(Throwable e){
			CLogger.write("5", ProyectoDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}

}
