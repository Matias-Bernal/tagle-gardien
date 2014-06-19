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
package servidor.Persistencia;

public class ManipuladorPersistencia extends Thread {
	/*
	 * private PersistenceManager pm; private Transaction tx;
	 * 
	 * public ManipuladorPersistencia(){ }
	 * 
	 * public synchronized void initPersistencia(){ pm =
	 * SingletonPersistencia.getInstance().getPM(); tx =
	 * pm.currentTransaction(); while (tx.isActive()) { try { sleep(5000);// 5
	 * seg tx = pm.currentTransaction(); } catch (Exception ex){ } }
	 * if(!tx.isActive()) try { tx.begin(); } catch (Exception ex){
	 * initPersistencia(); } else initPersistencia(); }
	 * 
	 * public boolean commit(){ try { tx.commit(); } catch (RuntimeException e)
	 * { e.printStackTrace(); return false; } return true; }
	 * 
	 * public boolean close(){ try { pm.close(); } catch (RuntimeException e) {
	 * e.printStackTrace(); return false; } return true; }
	 * 
	 * public void rollback(){ if (tx.isActive()) tx.rollback(); }
	 * 
	 * public void hacerPersistente(Object obj)throws Exception{ try{
	 * pm.makePersistent(obj); } catch(Exception e) { throw e; } }
	 * 
	 * public void borrar(Object obj)throws Exception{ try{
	 * pm.deletePersistent(obj); } catch(Exception e){ throw e; } }
	 * 
	 * public Vector getAll(Class clase)throws Exception{ Vector objetos=new
	 * Vector(); try{ Class clienteClass = clase; Extent clnCliente =
	 * pm.getExtent(clienteClass, false); Query query =
	 * pm.newQuery(clnCliente,""); Collection
	 * coleccion=(Collection)query.execute(); objetos.addAll(coleccion); }
	 * catch(Exception e){ throw e; } return objetos; }
	 * 
	 * public Vector getObjectsSubc(Class clase,String filter)throws Exception{
	 * Vector objetos=new Vector(); try{ Class clienteClass = clase; Extent
	 * clnCliente = pm.getExtent(clienteClass, true); Query query =
	 * pm.newQuery(clnCliente,filter); Collection
	 * coleccion=(Collection)query.execute(); objetos.addAll(coleccion); }
	 * catch(Exception e) { throw e; } return objetos; }
	 * 
	 * public Vector getObjects(Class clase,String filter)throws Exception{
	 * Vector objetos=new Vector(); try{ Class clienteClass = clase; Extent
	 * clnCliente = pm.getExtent(clienteClass, false); Query query =
	 * pm.newQuery(clnCliente,filter); Collection
	 * coleccion=(Collection)query.execute(); objetos.addAll(coleccion); }
	 * catch(Exception e) { throw e; } return objetos; }
	 * 
	 * public Vector getAllOrdered(Class clase, String ordering)throws
	 * Exception{ Vector objetos=new Vector(); try{ Class clienteClass = clase;
	 * Extent clnCliente = pm.getExtent(clienteClass, false); Query query =
	 * pm.newQuery(clnCliente,""); query.setOrdering(ordering); Collection
	 * coleccion=(Collection)query.execute(); objetos.addAll(coleccion); }
	 * catch(Exception e){ throw e; } return objetos; }
	 * 
	 * public Vector getObjectsOrdered(Class clase,String filter, String
	 * ordering)throws Exception{ Vector objetos=new Vector(); try{ Class
	 * clienteClass = clase; Extent clnCliente = pm.getExtent(clienteClass,
	 * false); Query query = pm.newQuery(clnCliente,filter);
	 * query.setOrdering(ordering); Collection
	 * coleccion=(Collection)query.execute(); objetos.addAll(coleccion); }
	 * catch(Exception e) { throw e; } return objetos; }
	 * 
	 * public Vector getAllOrderedSubc(Class clase, String ordering)throws
	 * Exception{ Vector objetos=new Vector(); try{ Class clienteClass = clase;
	 * Extent clnCliente = pm.getExtent(clienteClass,true); Query query =
	 * pm.newQuery(clnCliente,""); query.setOrdering(ordering); Collection
	 * coleccion=(Collection)query.execute(); objetos.addAll(coleccion); }
	 * catch(Exception e){ throw e; } return objetos; }
	 * 
	 * public Vector getObjectsOrderedSubc(Class clase,String filter, String
	 * ordering)throws Exception{ Vector objetos=new Vector(); try{ Class
	 * clienteClass = clase; Extent clnCliente = pm.getExtent(clienteClass,
	 * true); Query query = pm.newQuery(clnCliente,filter);
	 * query.setOrdering(ordering); Collection
	 * coleccion=(Collection)query.execute(); objetos.addAll(coleccion); }
	 * catch(Exception e) { throw e; } return objetos; }
	 * 
	 * public Transaction getTx() { return tx; }
	 */

}
