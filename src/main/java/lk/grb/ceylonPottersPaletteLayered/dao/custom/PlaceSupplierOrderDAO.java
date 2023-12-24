package lk.grb.ceylonPottersPaletteLayered.dao.custom;

import lk.grb.ceylonPottersPaletteLayered.dao.SuperDAO;
import lk.grb.ceylonPottersPaletteLayered.dto.SupplierOrderDto;

public interface PlaceSupplierOrderDAO extends SuperDAO {
    boolean placeSupplierOrder(SupplierOrderDto supplierOrderDto);
}
