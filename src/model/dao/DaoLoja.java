package model.dao;

import db.DB;
import model.dao.impl.EstoqueDaoJDBC;

public class DaoLoja {

    public static EstoqueDao criarEstoqueDao() {
        return new EstoqueDaoJDBC(DB.getConnection());
    }

}
