package dao;

import java.util.List;

import org.hibernate.Session;

import pojo.Actividad;
import pojo.Componente;
import pojo.ComponentePropiedadValor;
import pojo.Desembolso;
import pojo.LineaBase;
import pojo.Meta;
import pojo.Producto;
import pojo.ProductoPropiedadValor;
import pojo.Proyecto;
import pojo.Subcomponente;
import pojo.SubcomponentePropiedadValor;
import pojo.Subproducto;
import utilities.CHibernateSession;
import utilities.CLogger;
import org.hibernate.query.Query;


public class LineaBaseDAO {
	public static List<LineaBase> getLineasBaseById(Integer proyectoid){
		List<LineaBase> ret = null;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			Query<LineaBase> criteria = session.createQuery("FROM LineaBase lb where lb.proyecto.id=:proyectoid", LineaBase.class);
			criteria.setParameter("proyectoid", proyectoid);
			ret = criteria.getResultList();
		}catch(Throwable e){
			CLogger.write("1", LineaBaseDAO.class, e);
		}finally{
			session.close();
		}
		return ret;
	}
	
	public static boolean guardarLineaBase(LineaBase lineaBase){
		boolean ret = false;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			
			session.saveOrUpdate(lineaBase);
			session.flush();
			session.getTransaction().commit();
			session.close();
			ret = true;
			ret = ret && lineaBasePEP(lineaBase) >= 0;
			if(ret)
				ret = ret && lineaBasePEPPropiedadValor(lineaBase) >=0;
			if(ret)
				ret = ret && lineaBaseComponentes(lineaBase) >=0;
			if(ret)
				ret = ret && lineaBaseComponentesPropiedadValor(lineaBase) >=0;
			if (ret)
				ret = ret && lineaBaseSubcomponentes(lineaBase) >=0;
			if(ret)
				ret = ret && lineaBaseSubcomponentesPropiedadValor(lineaBase) >=0;
			if (ret)
				ret = ret && lineaBaseProducto(lineaBase) >=0;
			if(ret)
				ret = ret && lineaBaseProductoPropiedadValor(lineaBase) >=0;
			if (ret)
				ret = ret && lineaBaseSubproducto(lineaBase) >=0;
			if(ret)
				ret = ret && lineaBaseSubproductoPropiedadValor(lineaBase) >=0;
			if (ret)
					ret = ret && lineaBaseActividad(lineaBase) >=0;
			if(ret)
				ret = ret && lineaBaseActividadPropiedadValor(lineaBase) >=0;
			if (ret)
				ret = ret && lineaBaseDesembolsos(lineaBase) >=0;
			if (ret)
				ret = ret && lineaBaseMetas(lineaBase) >=0;
			if (ret )
				ret = ret && lineaBaseMetasPlanificado(lineaBase) >= 0;
		}
		catch(Throwable e){
			CLogger.write("2", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBasePEP (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.proyecto", 
							"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
							"where id = ?1",
							"and estado = 1",
							"and actual = 1");
			
			Query<Proyecto> criteria = session.createNativeQuery(query, Proyecto.class);
			criteria.setParameter(1, lineaBase.getProyecto().getId());
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("3", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBasePEPPropiedadValor (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.proyecto_propiedad_valor", 
							"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
							"where proyectoid = ?1",
							"and estado = 1",
							"and actual = 1");
			
			Query<Proyecto> criteria = session.createNativeQuery(query, Proyecto.class);
			criteria.setParameter(1, lineaBase.getProyecto().getId());
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("3", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseComponentes (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.componente", 
							"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
							"where treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ",
							"and estado = 1",
							"and actual = 1");
			
			Query<Componente> criteria = session.createNativeQuery(query, Componente.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("4", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseComponentesPropiedadValor (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.componente_propiedad_valor",
						"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
						"where componenteid in ( ",
							"select c.id from sipro_history.componente c",
							"where c.treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ", 
							"and c.estado = 1 and c.actual = 1",
						")",
						"and actual = 1");
			
			Query<ComponentePropiedadValor> criteria = session.createNativeQuery(query, ComponentePropiedadValor.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("4", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseSubcomponentes (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.subcomponente", 
							"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
							"where treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ",
							"and estado = 1",
							"and actual = 1");
			
			Query<Subcomponente> criteria = session.createNativeQuery(query, Subcomponente.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("5", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseSubcomponentesPropiedadValor (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","select * from sipro_history.subcomponente_propiedad_valor", 
						"where subcomponenteid in ( ",
							"select c.id from sipro_history.subcomponente c",
							"where c.treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ", 
							"and c.estado = 1 and c.actual = 1",
						")",
						"and actual = 1");
			
			Query<SubcomponentePropiedadValor> criteria = session.createNativeQuery(query, SubcomponentePropiedadValor.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("4", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseProducto (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.producto", 
							"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
							"where treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ",
							"and estado = 1",
							"and actual = 1");
			
			Query<Producto> criteria = session.createNativeQuery(query, Producto.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("6", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseProductoPropiedadValor (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.producto_propiedad_valor",
						"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
						"where productoid in ( ",
							"select p.id from sipro_history.producto p",
							"where p.treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ", 
							"and p.estado = 1 and p.actual = 1",
						")",
						"and actual = 1");
			
			Query<ProductoPropiedadValor> criteria = session.createNativeQuery(query, ProductoPropiedadValor.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("4", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseSubproducto (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.subproducto", 
							"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
							"where treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ",
							"and estado = 1",
							"and actual = 1");
			
			Query<Subproducto> criteria = session.createNativeQuery(query, Subproducto.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("7", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseSubproductoPropiedadValor (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.subproducto_propiedad_valor",
						"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
						"where subproductoid in ( ",
							"select p.id from sipro_history.subproducto p",
							"where p.treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ", 
							"and p.estado = 1 and p.actual = 1",
						")",
						"and actual = 1");
			
			Query<ProductoPropiedadValor> criteria = session.createNativeQuery(query, ProductoPropiedadValor.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("4", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseActividad (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.actividad", 
							"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
							"where treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ",
							"and estado = 1",
							"and actual = 1");
			
			Query<Actividad> criteria = session.createNativeQuery(query, Actividad.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("8", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseActividadPropiedadValor (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.actividad_propiedad_valor",
						"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
						"where actividadid in ( ",
							"select a.id from sipro_history.actividad a",
							"where a.treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ", 
							"and a.estado = 1 and a.actual = 1",
						")",
						"and actual = 1");
			
			Query<ProductoPropiedadValor> criteria = session.createNativeQuery(query, ProductoPropiedadValor.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("4", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseDesembolsos (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.desembolso",
				"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
				"where proyectoid = ?1 ",
				"and estado = 1");
			
			Query<Desembolso> criteria = session.createNativeQuery(query, Desembolso.class);
			criteria.setParameter(1, lineaBase.getProyecto().getId());
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("8", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}

	private static Integer lineaBaseMetas (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.meta", 
				"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
				"where objeto_tipo = 3",
				"and estado = 1",
				"and actual = 1",
				"and objeto_id in (",
					"select c.id from sipro_history.producto c",
				    "where c.estado = 1",
				    "and c.actual = 1",
				    "and c.treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ",
				")");
			
			Query<Meta> criteria = session.createNativeQuery(query, Meta.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("8", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
	
	private static Integer lineaBaseMetasPlanificado (LineaBase lineaBase){
		Integer ret = -1;
		Session session = CHibernateSession.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			String query = String.join(" ","update sipro_history.meta", 
				"set linea_base = CONCAT(ifnull(linea_base,''),'|lb',",lineaBase.getId().toString(),",'|')",
				"where objeto_tipo = 3",
				"and estado = 1",
				"and actual = 1",
				"and objeto_id in (",
					"select c.id from sipro_history.producto c",
				    "where c.estado = 1",
				    "and c.actual = 1",
				    "and c.treepath like '" + lineaBase.getProyecto().getTreePath().trim() + "%' ",
				")");
			
			Query<Meta> criteria = session.createNativeQuery(query, Meta.class);
			ret =   criteria.executeUpdate();
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		catch(Throwable e){
			CLogger.write("8", LineaBaseDAO.class, e);
		}
		finally{
			session.close();
		}
		return ret;
	}
}
