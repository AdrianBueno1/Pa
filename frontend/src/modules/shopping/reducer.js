import {combineReducers} from 'redux';

import * as actionTypes from './actionTypes';

const initialState = {
    lastSaleId: null,
    saleSearch: null,
};

const lastSaleId = (state = initialState.lastSaleId, action) => {

    switch (action.type) {

        case actionTypes.BUY_COMPLETED:
            return action.saleId;

        default:
            return state;

    }

}

const saleSearch = (state = initialState.saleSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_SALES_COMPLETED:
            return action.saleSearch;

        case actionTypes.CLEAR_SALES_SEARCH:
            return initialState.saleSearch;

        default:
            return state;

    }

}

const reducer = combineReducers({
    lastSaleId,
    saleSearch,
});

export default reducer;


