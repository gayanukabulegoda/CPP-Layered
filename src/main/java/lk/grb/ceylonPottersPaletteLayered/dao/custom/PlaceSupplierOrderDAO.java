package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dao.SuperDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierOrderDto;
import lk.grb.ceylonPottersPaletteLayered.entity.SupplierOrder;

public interface PlaceSupplierOrderDAO extends SuperDAO {
    boolean placeSupplierOrder(SupplierOrder entity);
}
