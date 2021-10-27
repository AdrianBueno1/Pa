import {config, appFetch} from './appFetch';

export const buy = (sessionId, seats, creditCard, onSuccess, 
    onErrors) =>
    appFetch(`/shopping/sessions/${sessionId}/buy`, 
        config('POST', {seats, creditCard}), onSuccess, onErrors);


export const findSales = ({page}, onSuccess) => 
    appFetch(`/shopping/sales?page=${page}`, config('GET'), onSuccess);


export const deliver = (saleId, creditCard, onSuccess, onErrors) =>
    appFetch(`/shopping/sales/${saleId}/deliverTickets`, 
        config('POST', {creditCard}), onSuccess, onErrors);