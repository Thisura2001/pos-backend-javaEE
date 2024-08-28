package lk.ijse.posbackendjavaee.Bo;

import lk.ijse.posbackendjavaee.Bo.impl.CustomerBoImpl;
import lk.ijse.posbackendjavaee.Bo.impl.ItemBoImpl;
import lk.ijse.posbackendjavaee.Bo.impl.OrderBoImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private void BOFactory(){}

    public static BOFactory getBOFactory(){
        return (boFactory == null)?boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,ITEM,ORDER,ORDER_DETAIL
    }

    public SuperBO BOTypes(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return (SuperBO) new CustomerBoImpl();
            case ITEM:
                return (SuperBO) new ItemBoImpl();
            case ORDER:
                return (SuperBO) new OrderBoImpl();
            case ORDER_DETAIL:
                return (SuperBO) new OrderBoImpl();
            default:
                return null;
        }
    }
}

