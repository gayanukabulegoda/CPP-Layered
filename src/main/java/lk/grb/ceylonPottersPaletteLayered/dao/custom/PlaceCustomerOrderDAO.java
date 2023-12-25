package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dao.SuperDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.CustomerOrderDto;
import lk.grb.ceylonPottersPaletteLayered.entity.CustomerOrder;
import lk.grb.ceylonPottersPaletteLayered.util.CrudUtil;

public interface PlaceCustomerOrderDAO extends SuperDAO {
    boolean placeCustomerOrder(CustomerOrder entity);
}
