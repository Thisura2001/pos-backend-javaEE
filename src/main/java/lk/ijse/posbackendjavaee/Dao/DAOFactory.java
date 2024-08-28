package lk.ijse.posbackendjavaee.Dao;

import lk.ijse.posbackendjavaee.Dao.Impl.CustomerDaoImpl;
import lk.ijse.posbackendjavaee.Dao.Impl.ItemDaoImpl;
import lk.ijse.posbackendjavaee.Dao.Impl.OrderDaoImpl;
import lk.ijse.posbackendjavaee.Dao.Impl.OrderDetailDaoImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDAOFactory(){
        return (daoFactory == null)?daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        CUSTOMER,iTEM,ORDER,ORDER_DETAIL
    }

    public SuperDAO DAOTypes(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return (SuperDAO) new CustomerDaoImpl();
            case ORDER:
                return (SuperDAO) new OrderDaoImpl();
            case ORDER_DETAIL:
                return (SuperDAO) new OrderDetailDaoImpl();
            default:
                return null;
        }
    }
}

