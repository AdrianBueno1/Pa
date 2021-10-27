import backend from '../../backend';
import * as actionTypes from './actionTypes';

export const buyCompleted = (saleId) => ({
    type: actionTypes.BUY_COMPLETED,
    saleId
});

export const buy = (sessionId, seats, creditCard, onSuccess, 
    onErrors) => dispatch =>
    backend.shoppingService.buy(sessionId, seats, creditCard, ({id}) => {
        dispatch(buyCompleted(id));
        onSuccess();
    },
    onErrors);

const findSalesCompleted = saleSearch => ({
    type: actionTypes.FIND_SALES_COMPLETED,
    saleSearch
});
    
const clearSalesSearch = () => ({
    type: actionTypes.CLEAR_SALES_SEARCH
});
    
export const findSales = criteria => dispatch => {
    
    dispatch(clearSalesSearch());
    backend.shoppingService.findSales(criteria, 
        result => dispatch(findSalesCompleted({criteria, result})));
    
}    
    
export const previousFindSalesResultPage = criteria => 
    findSales({page: criteria.page-1});
    
export const nextFindSalesResultPage = criteria => 
    findSales({page: criteria.page+1});
    
export const deliver = (saleId, creditCard, onSuccess, onErrors) => dispatch =>
    backend.shoppingService.deliver(saleId, creditCard, onSuccess, onErrors);