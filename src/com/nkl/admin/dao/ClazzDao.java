package com.nkl.admin.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.nkl.admin.domain.Clazz;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

public class ClazzDao {

	public int addClazz(Clazz clazz, Connection conn){
		String sql = "INSERT INTO clazz(clazz_id,clazz_name,note) values(null,?,?)";
		Object[] params = new Object[] {
			clazz.getClazz_name(),
			clazz.getNote()

		};
		return BaseDao.executeUpdate(sql, params, conn );
	}

	public int delClazz(String clazz_id, Connection conn){
		String sql = "DELETE FROM score WHERE clazz_id=?";

		Object[] params = new Object[] { new Integer(clazz_id)};
		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int delClazzs(String[] clazz_ids, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <clazz_ids.length; i++) {
			sBuilder.append("?");
			if (i !=clazz_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String sql = "DELETE FROM clazz WHERE clazz_id IN(" +sBuilder.toString()+")";

		Object[] params = clazz_ids;

		return BaseDao.executeUpdate(sql, params, conn);
	}

	public int updateClazz(Clazz clazz, Connection conn){
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE clazz SET clazz_id = " + clazz.getClazz_id() +" ");
		if (!StringUtil.isEmptyString(clazz.getClazz_name())) {
			sBuilder.append(" ,clazz_name = '" + clazz.getClazz_name() +"' ");
		}
		if (!StringUtil.isEmptyString(clazz.getNote())) {
			sBuilder.append(" ,note = '" + clazz.getNote() +"' ");
		}
		sBuilder.append(" where clazz_id = " + clazz.getClazz_id() +" ");

		Object[] params = null;
		return BaseDao.executeUpdate(sBuilder.toString(), params, conn);
	}

	public Clazz getClazz(Clazz clazz, Connection conn){
		Clazz _clazz=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM clazz WHERE 1=1");
		if (clazz.getClazz_id()!=0) {
			sBuilder.append(" and clazz_id = " + clazz.getClazz_id() +" ");
		}
		if (!StringUtil.isEmptyString(clazz.getClazz_name())) {
			sBuilder.append(" and clazz_name = '" + clazz.getClazz_name() +"' ");
		}

		List<Object> list = BaseDao.executeQuery(Clazz.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			 _clazz = (Clazz)list.get(0);
		}
		return _clazz;
	}

	public List<Clazz>  listClazzs(Clazz clazz, Connection conn){
		List<Clazz> clazzs = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT * FROM clazz WHERE 1=1");

		if (clazz.getClazz_id()!=0) {
			sBuilder.append(" and clazz_id = " + clazz.getClazz_id() +" ");
		}
		if (!StringUtil.isEmptyString(clazz.getClazz_name())) {
			sBuilder.append(" and clazz_name like '%" + clazz.getClazz_name() +"%' ");
		}
		sBuilder.append(" order by clazz_id asc) t");

		if (clazz.getStart() != -1) {
			sBuilder.append(" limit " + clazz.getStart() + "," + clazz.getLimit());
		}

		List<Object> list = BaseDao.executeQuery(Clazz.class.getName(), sBuilder.toString(), null, conn);
		if (list != null && list.size() > 0) {
			clazzs = new ArrayList<Clazz>();
			for (Object object : list) {
				clazzs.add((Clazz)object);
			}
		}
		return clazzs;
	}

	public int  listClazzsCount(Clazz clazz, Connection conn){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM clazz WHERE 1=1");

		if (clazz.getClazz_id()!=0) {
			sBuilder.append(" and clazz_id = " + clazz.getClazz_id() +" ");
		}
		if (!StringUtil.isEmptyString(clazz.getClazz_name())) {
			sBuilder.append(" and clazz_name like '%" + clazz.getClazz_name() +"%' ");
		}

		long count = (Long)BaseDao.executeQueryObject(sBuilder.toString(), null, conn);
		sum = (int)count;
		return sum;
	}

}
