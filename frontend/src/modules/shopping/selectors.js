const getModuleState = state => state.shopping;

export const getLastSaleId = state =>
    getModuleState(state).lastSaleId;

export const getSaleSearch = state =>
    getModuleState(state).saleSearch;